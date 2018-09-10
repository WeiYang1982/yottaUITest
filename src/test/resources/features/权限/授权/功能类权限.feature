Feature: 功能类的授权
  Background:
    Given Delete a "role" with "{'name':['AutoTestForAuth','AutoTestNew']}"
    And Create a "role" with "{'name':'AutoTestForAuth','RoleDes':'','ResourceGroups':['日志来源']}"
    And open the "roles.ListPage" page for uri "/account/roles/"

  @authorization @all
  Scenario Outline:
    Given I set the parameter "SearchInput" with value "AutoTestForAuth"
    And I wait table element "SearchResultTable-1.1" change text to "AutoTestForAuth"
    And I click the table "TableAuthorizeButton-1" button
    And I will see the "roles.AuthorizationPage" page
    And I click the "{'TabButton':'功能'}" button
    When I check "<FunctionName>" from the "FunctionCheckboxes"
    And I click the "SaveButton" button
    Then I will see the success message "保存成功"

  Examples:
    |FunctionName     |
    |可使用离线任务     |
    |可查看未分配资源   |

  @authorization @all @smoke
  Scenario:
    Given I set the parameter "SearchInput" with value "AutoTestForAuth"
    And I wait table element "SearchResultTable-1.1" change text to "AutoTestForAuth"
    And I click the table "TableAuthorizeButton-1" button
    And I will see the "roles.AuthorizationPage" page
    And I click the "{'TabButton':'功能'}" button
    When I click the "ChooseAllCheckBoxes" button
    And I click the "SaveButton" button
    Then I will see the success message "保存成功"
