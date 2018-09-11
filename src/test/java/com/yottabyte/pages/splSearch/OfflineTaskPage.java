package com.yottabyte.pages.splSearch;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.ElementExist;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author sunxj
 */
public class OfflineTaskPage extends PageTemplate {
    public OfflineTaskPage(WebDriver driver) {
        super(driver);
        isLoaded();
    }

    @FindBy(className = "el-table_1_column_1")
    private List<WebElement> tdList;

    @FindBy(className = "el-table__empty-text")
    private WebElement emptyElement;

    public WebElement getFirstData() {


//        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("el-table_1_column_1")));
        return tdList.get(1);
    }

    @Override
    protected void isLoaded() throws Error {
        WebDriverWait wait = new WebDriverWait(webDriver, 10, 1000);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElements(By.className("el-table_1_column_1")).size() > 1;
            }
        });
    }
}
