package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.JdbcUtils;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class VerifyTextSameWithSQL {

    @Then("^I will see the \"([^\"]*)\" text will be same with the Sql \"([^\"]*)\"$")
    public void iWillSeeTheTextWillBeSameWithTheSql(String elementName, String searchSql) {
        WebElement element = GetElementFromPage.getWebElementWithName(elementName);
        String expText = element.getText();
        List<String> acuText = JdbcUtils.query(searchSql);
        for (String s :acuText) {
            assertEquals(expText, s);
        }
    }
}
