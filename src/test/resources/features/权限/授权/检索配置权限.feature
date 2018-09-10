Feature: 检索配置的授权
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
    And I click the "{'TabButton':'配置'}" button
    When I set the parameter "SearchTime" with value "<SearchTime>"
    And I click the "SaveButton" button
    Then I will see <Result>

    Examples:
      |SearchTime     |Result|
      |120s           |the success message "保存成功"|

    Examples:
      |SearchTime     |Result|
      |               |the error message "保存失败: 最大时长配置项无效, [max_search_time_range]应该为num + d/h/m/s的形式\n错误码: FE_119"|


