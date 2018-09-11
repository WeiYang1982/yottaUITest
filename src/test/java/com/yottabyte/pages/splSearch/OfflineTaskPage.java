package com.yottabyte.pages.splSearch;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.ElementExist;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author sunxj
 */
public class OfflineTaskPage extends PageTemplate {
    public OfflineTaskPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "el-table_1_column_1")
    private List<WebElement> tdList;

    public WebElement getFirstData() {
        ElementExist.isElementExist(webDriver,tdList.get(1));
//        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("el-table_1_column_1")));
        return tdList.get(1);
    }
}
