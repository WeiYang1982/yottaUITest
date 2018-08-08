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
import java.util.Map;

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
                int realRowNum = 20 * (Integer.parseInt(page.get(page.size() - 1).getText()) - 1) + trList.size() - 1;
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
        List<Map<String, String>> tdList = GetElementFromPage.getWebElementWithName("TdList");
        boolean flag = false;
        for (int i = 0; i < name.size(); i++) {
            if (value.size() == 1 && value.get(0).startsWith(">")) {
                String str = value.get(0);
                int base = Integer.parseInt(str.substring(1, str.length()));
                for (Map<String, String> map : tdList) {
                    if (!map.containsKey(name.get(0)))
                        continue;
                    int status = Integer.parseInt(map.get(name.get(0)));
                    if (status < base) {
                        logger.error("\n搜索语句：" + spl + "\n有一行的" + name.get(i) + "的值小于" + base);
                        assertTrue(false);
                    }
                }

            } else if (("x".equals(rowNum) || "".equals(rowNum)) && name.size() == value.size()) {
                for (Map<String, String> map : tdList) {
                    if (!map.containsKey(name.get(i))) {
                        continue;
                    }
                    if ("x".equals(rowNum) && map.get(name.get(i)).equals(value.get(i))) {
                        flag = true;
                    } else if (!"x".equals(rowNum) && !map.get(name.get(i)).equals(value.get(i))) {
                        logger.error("\n搜索语句：" + spl + "\n有一行的" + name.get(i) + "的值不等于" + value.get(i));
                        assertTrue(false);
                    }
                }
                if (flag)
                    flag = false;
                else if ("x".equals(rowNum)) {
                    logger.error("\n搜索语句：" + spl + "\n没有任意一行" + name.get(i) + "的值等于" + value.get(i));
                    assertTrue(false);
                }
            } else if (tdList.size() == 1 && value.size() == 4) {
                String[] datas = tdList.get(0).get("top(len)").split("\n");
                for (int j = 0; j < datas.length; j++) {
                    if (!datas[j].equals(value.get(j))) {
                        logger.error("\n搜索语句：" + spl + "\n第" + j + "行期望结果：" + value.get(j) + "\n实际结果：" + datas[j]);
                        assertTrue(false);
                    }
                }
            } else {
                int loopNum = Integer.parseInt(rowNum);
                for (int j = 0; j < loopNum; j++) {
                    if (loopNum == 1) {
                        if (i == 0) {
                            if (!tdList.get(i + name.size() * j).get(name.get(i)).equals(value.get(i))) {
                                logger.error("\n搜索语句：" + spl + "\n第1行期望结果：" + value.get(i) + "\n实际结果：" + tdList.get(i + name.size() * j).get(name.get(i)));
                                assertTrue(false);
                            }
                            break;
                        } else {
                            if (!tdList.get(i + j).get(name.get(i)).equals(value.get(i))) {
                                logger.error("\n搜索语句：" + spl + "\n第" + j + "行期望结果：" + value.get(i) + "\n实际结果：" + tdList.get(i + j).get(name.get(i)));
                                assertTrue(false);
                            }
                            break;
                        }
                    } else {
                        if (!tdList.get(i + name.size() * j).get(name.get(i)).equals(value.get(i + name.size() * j))) {
                            assertTrue(false);
                        }
                    }
                }
            }
        }
    }

    @Then("^I will see the number of log is \"([^\"]*)\" when search \"([^割]*)\"$")
    public void checkLogNum(String logNum, String spl) {
        WebElement table = GetElementFromPage.getWebElementWithName("SearchTable");
        int totalLogNum = Integer.parseInt(logNum);
        List<WebElement> pages = webDriver.findElements(By.className("number"));
        pages.get(pages.size() - 1).click();
        int totalPage = Integer.parseInt(pages.get(pages.size() - 1).getText());
        int realTotalLogNum = 20 * (totalPage - 1) + table.findElements(By.tagName("tr")).size();
        if (realTotalLogNum != totalLogNum) {
            logger.error("\n搜索语句：" + spl + "\n期望日志条数：" + logNum + "\n实际日志条数：" + realTotalLogNum);
            assertTrue(false);
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
        List<Map<String, String>> table = GetElementFromPage.getWebElementWithName("TdList");
        if (field.size() == 1) {
            int i = 0;
            for (Map<String, String> map : table) {
                String name = field.get(0).substring(1, field.get(0).length() - 1);
                if (!"".equals(map.get(field.get(0)))) {
                    if (field.get(0).startsWith("+") && map.containsKey(name)) {
                        if (Integer.parseInt(map.get(field.get(0))) >= i) {
                            i = Integer.parseInt(map.get(field.get(0)));
                        } else {
                            logger.error("\n搜索语句：" + spl + "\n未按" + field.get(0) + "排序");
                            assertTrue(false);
                        }
                    } else if (field.get(0).startsWith("-") && map.containsKey(name)) {
                        if (Integer.parseInt(map.get(field.get(0))) <= i) {
                            i = Integer.parseInt(map.get(field.get(0)));
                        } else {
                            logger.error("\n搜索语句：" + spl + "\n未按" + field.get(0) + "排序");
                            assertTrue(false);
                        }
                    }
                }
            }
        } else {
            // 当按两个字段值进行排序时
            for (int j = 0; j < field.size(); j++) {
                for (int i = 0; i < table.size() / 2 - 2; i++) {
                    String name = field.get(j).substring(1, field.get(j).length());
                    if (!"".equals(table.get(i * field.size() + j).get(name)) && !"".equals(table.get(i * field.size() + j + 2).get(name))) {
                        int before = Integer.parseInt(table.get(i * field.size() + j).get(name));
                        int after = Integer.parseInt(table.get(i * field.size() + j + 2).get(name));
                        if (after < before && j == 0) {
                            logger.error("\n搜索语句：" + spl + "\n未按" + field.get(j) + "排序");
                            assertTrue(false);
                        } else if (j == 1 && after < before) {
                            if (Integer.parseInt(table.get(i * field.size() + j - 1).get("apache.status")) > Integer.parseInt(table.get(i * field.size() + j + 1).get("apache.status"))) {
                                logger.error("\n搜索语句：" + spl + "\n未按" + field.get(j) + "排序");
                                assertTrue(false);
                            }
                        }
                    }
                }
            }
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
