package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ThereIsAResourceGroup {
    @Given("^need run condition \"([^\"]*)\" There is a resourceGroup with name \"([^\"]*)\" , type \"([^\"]*)\" , owner \"([^\"]*)\"$")
    public void thereIsAResourceGroupWithNameTypeOwner(String needRun, String resourceGroupsName, List<String> typeName, List<String> ownerName) {
        if("N".equals(needRun)){
            System.out.println("skip this steps");
        }else if ("Y".equals(needRun)){
            SetKeyWithValue setKeyWithValue = new SetKeyWithValue();
            setKeyWithValue.iSetTheParameterWithValue1("SearchInput",resourceGroupsName);
            WebElement searchResult = GetElementFromPage.getWebElementWithName("SearchResult");
            String text = searchResult.getText();
            if ("暂无数据".equals(text)){
                WebElement createButton = GetElementFromPage.getWebElementWithName("CreateResourceGroup");
                createButton.click();
                IWillSeeNewPage page = new IWillSeeNewPage();
                page.iWillSeeNewPage("resourceGroups.CreatePage");
                setKeyWithValue.iSetTheParameterWithValue1("ResourceGroupName",resourceGroupsName);
                IChooseValueFromSelectList chooseValueFromSelectList = new IChooseValueFromSelectList();
                chooseValueFromSelectList.iChooseTheFromThe(typeName,"ResourceGroupType");
                chooseValueFromSelectList.iChooseTheFromThe(ownerName,"ResourceGroupOwner");
                ClickSomeButton clickSomeButton = new ClickSomeButton();
                clickSomeButton.iClickTheButton("CreateButton");
                GetElementFromPage.getWebElementWithName("OKButton").click();
                page.iWillSeeNewPage("resourceGroups.ListPage");
            }else if (text.equals(resourceGroupsName)){
                System.out.println("There is a resource groups");
                setKeyWithValue.iSetTheParameterWithValue1("SearchInput","");
            }
        }else {
            System.out.println("Please check needRun " + needRun);
        }
    }

    @Given("^There is a resourceGroup with name \"([^\"]*)\" , type \"([^\"]*)\" , owner \"([^\"]*)\"$")
    public void thereIsAResourceGroupWithNameTypeOwner(String resourceGroupsName, List<String> typeName, List<String> ownerName){
        thereIsAResourceGroupWithNameTypeOwner("Y",resourceGroupsName,typeName,ownerName);
    }
}
