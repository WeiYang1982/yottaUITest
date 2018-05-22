Feature: 复制一个角色

  Background:
    Given I click the "RolesPage" button
    And I will see the "roles.ListPage" page 
    And There is a "thereIsNoRole" with "{'name':'AutoTestCopy'}"
    And There is a "thereIsARole" with "{'name':'AutoTest','RoleDes':'','ResourceGroups':['日志来源']}"

  @all @smoke @role
  Scenario: 复制角色成功
    Given I set the parameter "SearchInput" with value "AutoTest"
    And I wait table element "SearchResultTable-1.1" change text to "AutoTest"
    And I click the table "TableCopyButton-1" button
    And I will see the "roles.CreatePage" page
    And I set the parameter "RoleName" with value "AutoTestCopy"
    When I click the "CreateButton" button
    Then I will see the success message "创建成功"
