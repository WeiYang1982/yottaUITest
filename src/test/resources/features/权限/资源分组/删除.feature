Feature: 删除指定的资源分组

  Background:
    Given open the "resourceGroups.ListPage" page for uri "/account/resourcegroups/"

  @smoke @all @resourceGroups
  Scenario:
    Given There is a "thereIsAResourceGroup" with "{'name':'AutoTest','type':['仪表盘'],'owner':['admin']}"
    When I set the parameter "SearchInput" with value "AutoTest"
    And I wait table element "SearchResultTable-1.1" change text to "AutoTest"
    And I click the table "TableDeleteButton-1" button
    And I click the "MessageBoxOKButton" button
    Then I will see the success message "删除成功"