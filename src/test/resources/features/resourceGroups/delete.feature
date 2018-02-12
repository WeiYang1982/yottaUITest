Feature: 删除指定的资源分组

  Background:
    Given I click the "ResourceGroupsPage" button
    Then I will see the "resourceGroups.ListPage" page

  Scenario Outline:
    Given need run condition "<NeedRun>" There is a resourceGroup with name "AutoTest1" , type "仪表盘" , owner "admin"
    When I set the parameter "SearchInput" with value "<ResourceGroupName>"
    And I click the table "DeleteButton-1" button
    And I click the "MessageBoxOKButton" button
    Then I will see the success message "<SuccessMessage>"



  Examples:
    |NeedRun|ResourceGroupName|SuccessMessage|
    |Y      |AutoTest1        |删除成功       |