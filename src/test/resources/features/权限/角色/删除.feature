Feature: 删除角色
  Background:
    Given Create a "role" with "{'name':'AutoTest','RoleDes':'','ResourceGroups':['日志来源']}"
    And open the "roles.ListPage" page for uri "/account/roles/"

  @all @smoke @role
  Scenario: 删除角色成功
    Given I set the parameter "SearchInput" with value "AutoTest"
    And I wait table element "SearchResultTable-1.1" change text to "AutoTest"
    When I click the table "TableDeleteButton-1" button
    And I click the "MessageBoxOKButton" button
    Then I will see the success message "删除成功"