package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.GetLogger;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class SplSearch {

    private Logger logger = GetLogger.getLogger();
    private WebDriver webDriver = LoginBeforeAllTests.getWebDriver();

    @And("^I will see \"([^\"]*)\" rows and \"([^\"]*)\" columns of \"([^割]*)\" in the table$")
    public void checkRowsNum(String rows, String columns, String spl) {
        if (!"".equals(rows) || !"".equals(columns)) {
            WebElement table = GetElementFromPage.getWebElementWithName("DetailTable");
            List<WebElement> thList = table.findElements(By.tagName("th"));

            // 分页参数
            List<WebElement> page = webDriver.findElements(By.className("number"));

            // 判断列数是否相符
            if (!"".equals(columns)) {
                int columnNum = Integer.parseInt(columns);
                if (thList.size() != columnNum) {
                    logger.error("\n搜索语句：" + spl + "\n期望列数为" + columns + "\n实际列数为" + thList.size());
                    assert (false);
                }
            }
            // 判断行数是否相符
            if (!"".equals(rows)) {
                page.get(page.size() - 1).click();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<WebElement> trList = table.findElements(By.tagName("tr"));
                // 总行数
                int realRowNum = 20 * (page.size() - 1) + trList.size() - 1;
                int expect;
                if (rows.startsWith("+")) {
                    expect = Integer.parseInt(rows.substring(1, rows.length()));
                } else {
                    expect = Integer.parseInt(rows);
                }
                if (realRowNum < expect || realRowNum != expect) {
                    logger.error("\n搜索语句：" + spl + "\n期望行数为" + rows + "\n实际行数为" + realRowNum);
                    assertTrue(false);
                }
            }
        }
    }

    @Then("^I will see the top \"([^\"]*)\" of \"([^\"]*)\" is \"([^\"]*)\" when search \"([^割]*)\"$")
    public void checkValue(String rowNum, List<String> name, List<String> value, String spl) {
        WebElement table = GetElementFromPage.getWebElementWithName("DetailTable");
        List<WebElement> tdList = table.findElements(By.tagName("td"));
        // 分页参数
        List<WebElement> page = webDriver.findElements(By.className("number"));
        boolean flag = false;
        // header
        for (int i = 0; i < name.size(); i++) {
            if ("".equals(rowNum) || "x".equals(rowNum)) {
                for (WebElement td : tdList) {
                    // 任意一行结果匹配即可
                    if ("x".equals(rowNum) && td.getAttribute("data-col-name").equals(name.get(i)) && td.getText().equals(value.get(i))) {
                        flag = true;
                        break;
                    } else if (name.size() == value.size()) {
                        if (td.getAttribute("data-col-name").equals(name.get(i)) && !td.getText().equals(value.get(i))) {
                            logger.error("\n搜索语句：" + spl + "\n" + name.get(i) + "列期望结果为：" + value.get(i) + "\n实际结果为：" + td.getText());
                            break;
                        }
                    }
                }
            } else {
                int topRowNum = Integer.parseInt(rowNum);
                for (int j = 0; j < topRowNum; j++) {
                    if (tdList.get(j).getAttribute("data-col-name").equals(name.get(i)) && !tdList.get(j).getText().equals(value.get(i))) {
                        logger.error("\n搜索语句：" + spl + "\n" + name.get(i) + "列期望结果为：" + value.get(i) + "\n实际结果为：" + tdList.get(j).getText());
                    }
                }
            }
        }
        if (!flag) {
            logger.error("\n搜索语句：" + spl + "\n没有一行与之匹配");
        }

    }

    @Then("^I will see the number of log is \"([^\"]*)\" when search \"([^割]*)\"$")
    public void checkLogNum(String logNum, String spl) {
        WebElement table = GetElementFromPage.getWebElementWithName("SearchTable");
        int totalLogNum = Integer.parseInt(logNum);
        List<WebElement> pages = table.findElements(By.className("number"));

        if (pages.size() == 1 && totalLogNum != table.findElements(By.tagName("tr")).size()) {
            logger.error("\n搜索语句：" + spl + "\n期望日志条数：" + logNum + "\n实际日志条数：" + table.findElements(By.tagName("tr")).size());
            assertTrue(false);
        } else {
            pages.get(pages.size() - 1).click();
            int totalPage = Integer.parseInt(pages.get(pages.size()).getText());
            int realTotalLogNum = 20 * (totalPage - 1) + table.findElements(By.tagName("tr")).size();
            if (realTotalLogNum != totalLogNum) {
                logger.error("\n搜索语句：" + spl + "\n期望日志条数：" + logNum + "\n实际日志条数：" + realTotalLogNum);
                assertTrue(false);
            }
        }
    }

    /**
     * 判断是否按照某一字段进行排序
     *
     * @param field
     * @param spl
     */
    @Then("^I will see the result order by \"([^\"]*)\" when search \"([^割]*)\"$")
    public void checkSequence(List<String> field, String spl) {
        WebElement table = GetElementFromPage.getWebElementWithName("DetailTable");
        // 分页参数
        List<WebElement> page = webDriver.findElements(By.className("number"));
        // 下一页按钮
        WebElement nextPage = webDriver.findElements(By.className("btn-next")).get(1);
        int pageNum = 0;
        while (pageNum < page.size()) {
            nextPage.click();
            List<WebElement> header = table.findElements(By.tagName("th"));
            List<WebElement> tr = table.findElements(By.tagName("tr"));
            for (String columnName : field) {
                String name = columnName.substring(1, columnName.length());
                for (int i = 0; i < header.size(); i++) {
                    for (int j = 0; j < tr.size() - 1; j++) {
                        // 正序
                        if (columnName.startsWith("+") && name.equals(header.get(i))) {
                            if (Integer.parseInt(tr.get(j).findElements(By.tagName("td")).get(i).getText()) > Integer.parseInt(tr.get(j + 1).findElements(By.tagName("td")).get(i).getText())) {
                                logger.error("\n搜索语句：" + spl + "\n未按" + columnName + "进行排序");
                                assertTrue(false);
                            }
                        } else if (Integer.parseInt(tr.get(j).findElements(By.tagName("td")).get(i).getText()) < Integer.parseInt(tr.get(j + 1).findElements(By.tagName("td")).get(i).getText())) {
                            // 逆序
                            logger.error("\n搜索语句：" + spl + "\n未按" + columnName + "进行排序");
                            assertTrue(false);
                        }
                    }
                }
            }
            pageNum++;
        }
    }

    @Then("^I will see the \"([^\"]*)\" of \"([^割]*)\" will between \"([^\"]*)\"$")
    public void searchResult(String elementName, String spl, List<String> range) {
        if (range.size() != 0) {
            WebElement element = GetElementFromPage.getWebElementWithName(elementName);
            //事件数不为空
            if (element != null) {
                String text = element.getText();
                Long realNum = Long.parseLong(text.replace("(", "").replace(")", ""));
                // 当事件数为某个范围时
                if (range.size() == 2) {
                    String lowerLimit = range.get(0);
                    String topLimit = range.get(1);
                    // 判断搜索事件数是否在范围内
                    if (!(realNum >= Long.parseLong(lowerLimit) && realNum <= Long.parseLong(topLimit))) {
                        logger.error("\n搜索语句:" + spl + "\n期望事件数：" + lowerLimit + "至" + topLimit + "\n实际事件数：" + realNum);
                        assertTrue(false);
                    }
                } else if (range.size() == 1) {
                    // 当事件数固定时
                    if (realNum != Long.parseLong(range.get(0))) {
                        logger.error("\n搜索语句:" + spl + "\n期望事件数：" + Long.parseLong(range.get(0)) + "\n实际事件数：" + realNum);
                        assertTrue(false);
                    }
                } else {
                    return;
                }
            } else {
                //事件数为空
                assertTrue("无".equals(range.get(0)));
            }
            if (spl.contains("es(_duration)")) {
                this.esSearch();
            }
        }
    }

    /**
     * index=* tag:"sample04061424" | transaction apache.status maxevents=10 | bucket min_timestamp span=5m as ts | stats avg(_duration) as base_len, count() as base_count, es(_duration) by ts
     */
    private void esSearch() {
        int i = 0;
        WebElement table = GetElementFromPage.getWebElementWithName("DetailTable");
        List<WebElement> tdList = table.findElements(By.tagName("td"));
        List<WebElement> thList = table.findElements(By.tagName("th"));
        for (WebElement th : thList) {
            if (th.getText().contains("_es._duration")) {
                if ((tdList.get(i).getText() != null || "".equals(tdList.get(i).getText()))) {
                    logger.info(th.getText() + "的计算值为" + tdList.get(i).getText());
                    continue;
                } else {
                    logger.error("es未计算出数据");
                    assertTrue(false);
                }
            }
            i++;
        }
    }
}
