package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 查询新增结果是否已出现在列表中
 */
public class SeeNewElementInList {
    @Then("I will see the \"([^\"]*)\" in the \"([^\"]*)\"")
    public void iWillSeeNewElement(String name, String elementName) throws Exception {
        // 获取当前列表页下的所有数据
        List<WebElement> list = GetElementFromPage.getWebElementsWithName(elementName);
        if (list != null && list.size() != 0) {
            boolean flag = false;
            for (WebElement webElement : list) {
                if (name.equalsIgnoreCase(webElement.getText())) {
                    flag = true;
                    assertTrue(flag);
                    break;
                }
            }
            if (!flag) {
                System.out.println("该列表下无" + name);
                assertTrue(flag);
            }
        } else {
            throw new NoSuchMethodException("获取不到" + elementName + "的内容！");
        }
    }
}
