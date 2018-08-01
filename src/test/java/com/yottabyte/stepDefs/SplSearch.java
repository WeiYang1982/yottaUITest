package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.GetLogger;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SplSearch {

//    private static Logger logger = LoggerFactory.getLogger(SplSearch.class);

    @Then("^I will see the \"([^\"]*)\" will between \"([^\"]*)\" and \"([^\"]*)\"$")
    public void searchResult(String elementName, String topLimit, String lowerLimit) {
        Logger logger = GetLogger.getLogger();
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

    public static void main(String args[]) {
        Logger logger = GetLogger.getLogger();
        logger.error("ssss");
        logger.debug("dddd");
    }

}
