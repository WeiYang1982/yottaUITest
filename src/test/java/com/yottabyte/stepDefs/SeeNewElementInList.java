package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SeeNewElementInList {
    @Then("I will see the \"([^\"]*)\" in the \"([^\"]*)\"")
    public void iWillSeeNewElement(String name, String elementName) throws Exception {
        List<WebElement> list = GetElementFromPage.getWebElementsWithName(elementName);
        if (list != null && list.size() != 0) {
            for (WebElement webElement : list) {
                if (name.equalsIgnoreCase(webElement.getText())) {
                    assertTrue(true);
                    break;
                } else {
                    System.out.println("该列表下无"+name);
                    assertTrue(false);
                }
            }
        } else {
            throw new NoSuchMethodException("获取不到" + elementName + "的内容！");
        }
    }
}
