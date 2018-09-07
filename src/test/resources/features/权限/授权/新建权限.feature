Feature: 新建相关授权
  
  Background: 
    Given Delete a "userGroup" with "{'name':['AutoTest']}"
    And Delete a "role" with "{'name':['AutoTest','AutoTestNew']}"
    And Create a "userGroup" with "{'name':'AutoTest','owner':['admin'],'role':['admin']}"
    And Create a "role" with "{'name':'AutoTest','RoleDes':'','ResourceGroups':['日志来源']}"
    And open the "roles.ListPage" page for uri "/account/roles/"

  @authorization
  Scenario Outline:
    Given I set the parameter "SearchInput" with value "AutoTest"
    And I wait table element "SearchResultTable-1.1" change text to "AutoTest"
    And I click the table "TableAuthorizeButton-1" button
    And I will see the "roles.AuthorizationPage" page
    When I click the "{'TabButton':'<Tab>'}" button
    And I click the "<CreateButton>" button
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all
  Examples:
    |  Tab      | CreateButton                            |  Result  |
    |用户分组    |{'CreateGroupButton':'新建用户分组'}       |success message "保存成功"|
    |Agent 管理 |{'CreateGroupButton':'新建 Agent 分组'}    |success message "保存成功"|
    |监控       |{'CreateGroupButton':'新建监控分组'}       |success message "保存成功"|
    |仪表盘     |{'CreateGroupButton':'新建仪表盘分组'}      |success message "保存成功"|
    |字典       |{'CreateGroupButton':'新建字典分组'}       |success message "保存成功"|
    |知识       |{'CreateGroupButton':'新建知识分组'}       |success message "保存成功"|
    |搜索宏     |{'CreateGroupButton':'新建搜索宏分组'}     |success message "保存成功"|
    |字段提取   |{'CreateGroupButton':'新建字段提取分组'}   |success message "保存成功"|
    |报表       |{'CreateGroupButton':'新建报表分组'}       |success message "保存成功"|
    |定时任务    |{'CreateGroupButton':'新建定时任务分组'}   |success message "保存成功"|
    |已存搜索    |{'CreateGroupButton':'新建已存搜索分组'}   |success message "保存成功"|
    |日志来源    |{'CreateGroupButton':'新建日志来源分组'}   |success message "保存成功"|
    |拓扑图      |{'CreateGroupButton':'新建拓扑图分组'}     |success message "保存成功"|
    |趋势图      |{'CreateGroupButton':'新建趋势图分组'}     |success message "保存成功"|

  @all
  Examples:
    |  Tab      | CreateButton                         |  Result  |
    |用户分组    |{'CreateUnitButton':'新建用户'}        |success message "保存成功"|
    |Agent 管理 |{'CreateUnitButton':'新建 Agent'}      |success message "保存成功"|
    |监控       |{'CreateUnitButton':'新建监控'}        |success message "保存成功"|
    |仪表盘     |{'CreateUnitButton':'新建仪表盘'}       |success message "保存成功"|
    |字典       |{'CreateUnitButton':'新建字典'}        |success message "保存成功"|
    |知识       |{'CreateUnitButton':'新建知识'}        |success message "保存成功"|
    |搜索宏     |{'CreateUnitButton':'新建宏'}         |success message "保存成功"|
    |字段提取   |{'CreateUnitButton':'新建字段提取'}    |success message "保存成功"|
    |报表       |{'CreateUnitButton':'新建报表'}        |success message "保存成功"|
    |定时任务    |{'CreateUnitButton':'新建定时任务'}    |success message "保存成功"|
    |已存搜索    |{'CreateUnitButton':'新建已存搜索'}    |success message "保存成功"|
    |日志来源    |{'CreateUnitButton':'新建日志来源'}    |success message "保存成功"|
    |拓扑图      |{'CreateUnitButton':'新建拓扑图'}     |success message "保存成功"|
    |趋势图      |{'CreateUnitButton':'新建趋势图'}     |success message "保存成功"|