Feature: 修改一个已存在的资源分组

  Background:
    Given I click the "ResourceGroupsPage" button
    Then I will see the "resourceGroups.ListPage" page
    And There is a "thereIsNoResourceGroup" with "{'name':'AutoTestNew'}"

  @resourceGroups @smoke
  Scenario Outline:
    Given There is a "thereIsAResourceGroup" with "{'name':'AutoTest','type':['仪表盘'],'owner':['admin']}"
    Given need run condition "<NeedRun>" There is a "thereIsAResourceGroup" with "{'name':'AutoTestNew','type':['仪表盘'],'owner':['admin']}"
    And I set the parameter "SearchInput" with value "<ResourceGroupName>"
    And I wait table element "SearchResultTable-1.1" change text to "<ResourceGroupName>"
    And I click the table "TableEditButton-1" button
    And I will see the "resourceGroups.EditPage" page
    Then I set the parameter "ResourceGroupName" with value "<NewResourceGroupName>"
    And I set the parameter "ResourceGroupDes" with value "<NewResourceGroupDes>"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all
  Examples:
    |NeedRun|ResourceGroupName|NewResourceGroupName|NewResourceGroupDes|Result|
    |N      |AutoTest         |AutoTestNew         |NewDes             |success message "保存成功"|

  @all
  Examples:
    |NeedRun|ResourceGroupName|NewResourceGroupName|NewResourceGroupDes|Result|
    |Y      |AutoTestNew      |AutoTest            |NewDes             |error message "保存失败: 资源组名称已存在\n错误码: FE_546"|
    |N      |AutoTest         |                    |NewDes             |error message "填写资源分组名称"|

