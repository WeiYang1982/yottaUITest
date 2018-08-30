Feature: 删除一个用户分组

  Background:
    Given Create a "userGroup" with "{'name':'AutoTest','owner':['admin'],'role':['admin']}"
    And open the "userGroups.ListPage" page for uri "/account/usergroups/"

  @smoke @userGroups @all
  Scenario:
    Given I set the parameter "SearchInput" with value "AutoTest"
    And I wait table element "SearchResultTable-1.2" change text to "AutoTest"
    And I click the table "TableDeleteButton-1" button
    And I click the "MessageBoxOKButton" button
    Then I will see the success message "删除成功"
