Feature: [授权]授权分组管理权限

  Background:
    Given Delete a "role" with "{'name':['AutoTestForAuth','AutoTestNew']}"
    And Create a "role" with "{'name':'AutoTestForAuth','RoleDes':'','ResourceGroups':['日志来源']}"
    And Create a "userGroup" with "{'name':'AutoTestForAuth','owner':['admin'],'role':['admin']}"
    And open the "roles.ListPage" page for uri "/account/roles/"

  @authorization @all
  Scenario Outline:
    Given I set the parameter "SearchInput" with value "AutoTestForAuth"
    And I wait table element "SearchResultTable-1.1" change text to "AutoTestForAuth"
    And I click the table "TableAuthorizeButton-1" button
    And I will see the "roles.AuthorizationPage" page
    When I click the "{'TabButton':'<Tab>'}" button
    And I check "<CheckBoxes>" from the "{'GroupManagement':['AutoTestForAuth']}"
    And I click the "SaveButton" button
    Then I will see the success message "保存成功"

  Examples:
    |Tab    |CheckBoxes|
    |用户分组|读取,编辑  |