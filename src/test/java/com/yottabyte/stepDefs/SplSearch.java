package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;


public class SplSearch {

    private static Logger logger = Logger.getLogger(SplSearch.class);

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
}
