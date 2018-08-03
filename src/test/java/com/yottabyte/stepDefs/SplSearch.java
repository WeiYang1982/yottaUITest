package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.GetLogger;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.List;


public class SplSearch {

//    private static Logger logger = LoggerFactory.getLogger(SplSearch.class);

    Logger logger = GetLogger.getLogger();

    @Then("^I will see the \"([^\"]*)\" will between \"([^\"]*)\" and \"([^\"]*)\"$")
    public void searchResult(String elementName, String topLimit, String lowerLimit) {
        WebElement element = GetElementFromPage.getWebElementWithName(elementName);
        String text = element.getText();
        System.out.println(text);
        Long realText = Long.parseLong(element.getText().replace("(", "").replace(")", ""));
        if (realText >= Long.parseLong(lowerLimit) && realText <= Long.parseLong(topLimit)) {
            assert (true);
            logger.info(text);
            logger.debug(text);
        } else {
            logger.debug(text);
            assert (false);
        }
    }

    @And("^I will see \"([^\"]*)\" rows and \"([^\"]*)\" of \"([^割]*)\" in the table$")
    public void checkRowsNum(String rows, String columns, String spl) {

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
        // 当事件数为某个范围时
        if (range.size() == 2) {
            String lowerLimit = range.get(0);
            String topLimit = range.get(1);
            // 判断搜索事件数是否在范围内
            if (!"".equals(text) && text != null) {
                Long realText = Long.parseLong(text.replace("(", "").replace(")", ""));
                if (realText >= Long.parseLong(lowerLimit) && realText <= Long.parseLong(topLimit)) {
                    assert (true);
                } else {
                    logger.error("");
                    assert (false);
                }
            }
        } else if (range.size() == 1) {
            // 当事件数固定时
        }
        this.splAnalyze(spl);
    }

    /**
     * 根据关键字分析spl
     *
     * @param spl
     */
    public void splAnalyze(String spl) {
        if (spl.contains("es(_duration)")) {
            this.esSearch();
        }
    }


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
