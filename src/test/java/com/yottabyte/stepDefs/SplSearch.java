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


public class SplSearch {

    private Logger logger = GetLogger.getLogger();
    private WebDriver webDriver = LoginBeforeAllTests.getWebDriver();

    @And("^I will see \"([^\"]*)\" rows and \"([^\"]*)\" of \"([^割]*)\" in the table$")
    public void checkRowsNum(String rows, String columns, String spl) {
        WebElement table = GetElementFromPage.getWebElementWithName("DetailTable");
        List<WebElement> trList = table.findElements(By.tagName("tr"));
        List<WebElement> thList = table.findElements(By.tagName("th"));

        // 分页参数
        List<WebElement> page = webDriver.findElements(By.className("number"));

        // 判断列数是否相符
        if (!"".equals(columns)) {
            int columnNum = Integer.parseInt(columns);
            if (thList.size() != columnNum) {
                logger.error("搜索语句：" + spl + "\n期望列数为" + columns + "\n实际列数为" + thList.size());
                assert (false);
            }
        }
        // 判断行数是否相符
        if (!"".equals(rows)) {
            int realRowNum = 0;
            for (int i = 0; i < page.size(); i++) {
                page.get(i).click();
                trList = table.findElements(By.tagName("tr"));
                realRowNum += trList.size();
            }

            int expect = Integer.parseInt(rows.split("/+")[1]);
            if ((rows.startsWith("+") && !(realRowNum >= expect)) || realRowNum != expect) {
                logger.error("搜索语句：" + spl + "\n期望行数为" + rows + "\n实际行数为" + realRowNum);
                assert (false);
            }
        }
    }

    @Then("^I will see the top \"([^\"]*)\" of \"([^\"]*)\" is \"([^\"]*)\" when search \"([^割]*)\"$")
    public void checkValue(String rowNum, List<String> name, List<String> value, String spl) {

    }

    @Then("^I will see the number of log is \"([^\"]*)\" when search \"([^割]*)\"$")
    public void checkLogNum(String logNum, String spl) {

    }

    @Then("^I will see the result order by \"([^\"]*)\" when search \"([^割]*)\"$")
    public void checkSequence(String field, String columName) {

    }

    @Then("^I will see the \"([^\"]*)\" of \"([^割]*)\" will between \"([^\"]*)\"$")
    public void searchResult(String elementName, String spl, List<String> range) {
        WebElement element = GetElementFromPage.getWebElementWithName(elementName);
        String text = element.getText();
        //事件数不为空
        if (!"".equals(text) && text != null) {
            Long realNum = Long.parseLong(text.replace("(", "").replace(")", ""));
            // 当事件数为某个范围时
            if (range.size() == 2) {
                String lowerLimit = range.get(0);
                String topLimit = range.get(1);
                // 判断搜索事件数是否在范围内
                if (!(realNum >= Long.parseLong(lowerLimit) && realNum <= Long.parseLong(topLimit))) {
                    logger.error("搜索语句:" + spl + "\n期望事件数：" + lowerLimit + "至" + topLimit + "\n实际事件数：" + realNum);
                    assert (false);
                }
            } else if (range.size() == 1) {
                // 当事件数固定时
                if (realNum == Long.parseLong(range.get(0))) {
                    assert (true);
                    logger.error("搜索语句:" + spl + "\n期望事件数：" + Long.parseLong(range.get(0)) + "\n实际事件数：" + realNum);
                } else {
                    logger.error("搜索语句:" + spl + "\n期望事件数：" + Long.parseLong(range.get(0)) + "\n实际事件数：" + realNum);
                    assert (false);
                }
            } else {
                return;
            }
        } else {
            //事件数为空
            assert ("无".equals(range.get(0)));
        }
        if (spl.contains("es(_duration)")) {
            this.esSearch();
        }
    }

    /**
     * index=* tag:"sample04061424" | transaction apache.status maxevents=10 | bucket min_timestamp span=5m as ts | stats avg(_duration) as base_len, count() as base_count, es(_duration) by ts
     */
    public void esSearch() {
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
                    assert (false);
                }
            }
            i++;
        }
    }
}
