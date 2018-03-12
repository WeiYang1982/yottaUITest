Feature: 删除指定的资源分组

  Background:
    Given I click the "ResourceGroupsPage" button
    Then I will see the "resourceGroups.ListPage" page

  @smoke @all @resourcegroups
  Scenario Outline:
    Given There is a "thereIsAResourceGroup" with "name(String):AutoTest1;type(List):仪表盘;owner(List):admin"
    When I set the parameter "SearchInput" with value "<ResourceGroupName>"
    And I click the table "TableDeleteButton-1" button
    And I click the "MessageBoxOKButton" button
    Then I will see the success message "<SuccessMessage>"

  Examples:
    |ResourceGroupName|SuccessMessage|
    |AutoTest1        |删除成功       |