Feature: 修改一个已存在的资源分组

  Background:
    Given I click the "ResourceGroupsPage" button
    Then I will see the "resourceGroups.ListPage" page

  Scenario Outline:
    Given There is a resourceGroup with name "AutoTest1" , type "仪表盘" , owner "admin"
    And There is a resourceGroup with name "NewAutoTest" , type "仪表盘" , owner "admin"
    And I set the parameter "SearchInput" with value "<ResourceGroupName>"
    And I wait table element "SearchResultTable-1.1" change text to "<ResourceGroupName>"
    And I click the table "TableEditButton-1" button
    And I will see the "resourceGroups.EditPage" page
    Then I set the parameter "ResourceGroupName" with value "<NewResourceGroupName>"
    And I set the parameter "ResourceGroupDes" with value "<NewResourceGroupDes>"
    And I click the "SaveButton" button
    Then I will see the <Result>

  Examples:
    |ResourceGroupName|NewResourceGroupName|NewResourceGroupDes|Result|
    |AutoTest1|NewAutoTest|NewDes|success message "保存成功"|
    |AutoTest1|NewAutoTest|NewDes|error message "保存失败: 资源组名称已存在\n错误码: FE_546"|
    |AutoTest1|       |NewDes|error message "填写资源分组名称"|

