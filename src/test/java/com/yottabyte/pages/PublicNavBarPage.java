package com.yottabyte.pages;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.ElementExist;
import com.yottabyte.utils.GetLogger;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * 导航栏页面元素
 */
public class PublicNavBarPage extends PageTemplate {

    public PublicNavBarPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(partialLinkText = "搜索")
    private WebElement locateSearchPage;

    @FindBy(partialLinkText = "监控")
    private WebElement alertsListPage;

    @FindBy(partialLinkText = "知识")
    private WebElement knowledgePage;

    @FindBy(partialLinkText = "定时任务")
    private WebElement timedTaskPage;

    @FindBy(id = "yw-nav-set")
    private WebElement systemSetButton;

    @FindBy(partialLinkText = "资源分组")
    private WebElement resourceGroupsPage;

    @FindBy(partialLinkText = "用户分组")
    private WebElement userGroupsPage;

    @FindBy(partialLinkText = "用户管理")
    private WebElement usersPage;

    @FindBy(partialLinkText = "告警插件")
    private WebElement alertPluginsPage;

    @FindBy(partialLinkText = "角色权限")
    private WebElement rolesPage;

    @FindBy(partialLinkText = "关联搜索")
    private WebElement customApplicationPage;

    @FindBy(className = "el-loading-mask")
    private WebElement loadingElement;

    public WebElement getTimedTaskPage() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.elementToBeClickable(timedTaskPage));
        return timedTaskPage;
    }

    public WebElement getLocateSearchPage() {
        return locateSearchPage;
    }

    public WebElement getAlertsListPage() {
        return alertsListPage;
    }

    public WebElement getKnowledgePage() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.elementToBeClickable(knowledgePage));
        return knowledgePage;
    }

    public WebElement getResourceGroupsPage() {
        waitElementToBeClickable(resourceGroupsPage);
        return resourceGroupsPage;
    }

    public WebElement getUserGroupsPage() {
        waitElementToBeClickable(userGroupsPage);
        return userGroupsPage;
    }

    public WebElement getUsersPage() {
        waitElementToBeClickable(usersPage);
        return usersPage;
    }

    public WebElement getAlertPluginsPage() {
        waitElementToBeClickable(alertPluginsPage);
        return alertPluginsPage;
    }

    public WebElement getRolesPage() {
        waitElementToBeClickable(rolesPage);
        return rolesPage;
    }

    public WebElement getCustomApplicationPage() {
        waitElementToBeClickable(customApplicationPage);
        return customApplicationPage;
    }

    private void waitElementToBeClickable(WebElement element) {
        ExpectedCondition expectedCondition = ExpectedConditions.elementToBeClickable(systemSetButton);
        WaitForElement.waitForElementWithExpectedCondition(webDriver, expectedCondition);
        systemSetButton.click();
        if (ElementExist.isElementExist(webDriver, loadingElement)) {
            WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.invisibilityOf(loadingElement));
        }
        WaitForElement.waitForElementWithExpectedCondition(webDriver, expectedCondition);
        expectedCondition = ExpectedConditions.elementToBeClickable(element);
        WaitForElement.waitForElementWithExpectedCondition(webDriver, expectedCondition);
    }

    @Override
    protected void load() {
        if (LoginBeforeAllTests.getCookie() != null) {
            webDriver.get("http://alltest.rizhiyi.com/dashboard/");
            webDriver.manage().addCookie(LoginBeforeAllTests.getCookie());
        }else {
            LoginBeforeAllTests.login();
        }
    }

    @Override
    protected void isLoaded() throws Error {
        super.isLoaded();
        try {
            WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOf(getLocateSearchPage()));
        }catch (Exception e){
            GetLogger.getLogger().error("can not load % with error %", this.getClass().getSimpleName(), e);
        }

    }
}
