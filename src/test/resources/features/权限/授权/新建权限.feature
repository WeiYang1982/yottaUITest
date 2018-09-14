Feature: [授权]新建相关授权
  
  Background: 
    Given Delete a "role" with "{'name':['AutoTestForAuth','AutoTestNew']}"
    And Create a "role" with "{'name':'AutoTestForAuth','RoleDes':'','ResourceGroups':['日志来源']}"
    And open the "roles.ListPage" page for uri "/account/roles/"

  @authorization
  Scenario Outline:
    Given I set the parameter "SearchInput" with value "AutoTestForAuth"
    And I wait table element "SearchResultTable-1.1" change text to "AutoTestForAuth"
    And I click the table "TableAuthorizeButton-1" button
    And I will see the "roles.AuthorizationPage" page
    When I click the "{'TabButton':'<Tab>'}" button
    And I click the "<CreateButton>" button
    And I click the "SaveButton" button
    Then I will see the success message "保存成功"

  @all
  Examples:
    |  Tab      | CreateButton                            |
    |用户分组    |{'CreateGroupButton':'新建用户分组'}       |
    |Agent 管理 |{'CreateGroupButton':'新建 Agent 分组'}    |
    |监控       |{'CreateGroupButton':'新建监控分组'}       |
    |仪表盘     |{'CreateGroupButton':'新建仪表盘分组'}      |
    |字典       |{'CreateGroupButton':'新建字典分组'}       |
    |知识       |{'CreateGroupButton':'新建知识分组'}       |
    |搜索宏     |{'CreateGroupButton':'新建搜索宏分组'}     |
    |字段提取   |{'CreateGroupButton':'新建字段提取分组'}   |
    |报表       |{'CreateGroupButton':'新建报表分组'}       |
    |定时任务    |{'CreateGroupButton':'新建定时任务分组'}   |
    |已存搜索    |{'CreateGroupButton':'新建已存搜索分组'}   |
    |日志来源    |{'CreateGroupButton':'新建日志来源分组'}   |
    |拓扑图      |{'CreateGroupButton':'新建拓扑图分组'}     |
    |趋势图      |{'CreateGroupButton':'新建趋势图分组'}     |

  @all
  Examples:
    |  Tab      | CreateButton                         |
    |用户分组    |{'CreateUnitButton':'新建用户'}        |
    |Agent 管理 |{'CreateUnitButton':'新建 Agent'}      |
    |监控       |{'CreateUnitButton':'新建监控'}        |
    |仪表盘     |{'CreateUnitButton':'新建仪表盘'}       |
    |字典       |{'CreateUnitButton':'新建字典'}        |
    |知识       |{'CreateUnitButton':'新建知识'}        |
    |搜索宏     |{'CreateUnitButton':'新建宏'}         |
    |字段提取   |{'CreateUnitButton':'新建字段提取'}    |
    |报表       |{'CreateUnitButton':'新建报表'}        |
    |定时任务    |{'CreateUnitButton':'新建定时任务'}    |
    |已存搜索    |{'CreateUnitButton':'新建已存搜索'}    |
    |日志来源    |{'CreateUnitButton':'新建日志来源'}    |
    |拓扑图      |{'CreateUnitButton':'新建拓扑图'}     |
    |趋势图      |{'CreateUnitButton':'新建趋势图'}     |