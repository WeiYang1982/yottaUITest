Feature: 删除指定的资源分组

  Background:
    Given I click the "ResourceGroupsPage" button
    Then I will see the "resourceGroups.ListPage" page

  @smoke @all @resourcegroups
  Scenario:
    Given There is a "thereIsAResourceGroup" with "name(String):AutoTest;type(List):仪表盘;owner(List):admin"
    When I set the parameter "SearchInput" with value "AutoTest"
    And I wait table element "SearchResultTable-1.1" change text to "AutoTest"
    And I click the table "TableDeleteButton-1" button
    And I click the "MessageBoxOKButton" button
    Then I will see the success message "删除成功"