Feature: 复制一个角色

  Background:
    Given Delete a "role" with "{'name':['AutoTestCopy']}"
    And Create a "role" with "{'name':'AutoTest','RoleDes':'','ResourceGroups':['日志来源']}"
    And open the "roles.ListPage" page for uri "/account/roles/"

  @all @smoke @role
  Scenario: 复制角色成功
    Given I set the parameter "SearchInput" with value "AutoTest"
    And I wait table element "SearchResultTable-1.1" change text to "AutoTest"
    And I click the table "TableCopyButton-1" button
    And I will see the "roles.CreatePage" page
    And I set the parameter "RoleName" with value "AutoTestCopy"
    When I click the "CreateButton" button
    Then I will see the success message "创建成功"
