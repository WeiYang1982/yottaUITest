Feature: [授权]授权组内管理权限

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
    When I click the "{'TabButton':'<Tab>'}" button
    And I check "读取" from the "{'GroupManagement':['AutoTestForAuth']}"
    And I check "<CheckBoxes>" from the "{'IntraGroupManagement':['AutoTestForAuth']}"
    And I click the "SaveButton" button
    Then I will see the success message "保存成功"

    Examples:
      |Tab       |CheckBoxes |
#      |用户分组   |读取       |
#      |Agent 管理 |读取       |
#      |监控       |读取      |
#      |仪表盘     |读取      |
#      |字典      |读取       |
#      |知识      |读取       |
#      |搜索宏     |读取      |
#      |字段提取   |读取      |
#      |报表      |读取       |
#      |定时任务  |读取       |
#      |已存搜索  |读取       |
#      |日志来源  |读取       |
#      |拓扑图    |读取       |
#      |趋势图    |读取       |

    Examples:
      |Tab       |CheckBoxes    |
#      |用户分组   |读取,编辑,删除  |
#      |Agent 管理 |读取,编辑,删除  |
#      |监控       |读取,编辑,删除  |
#      |仪表盘     |读取,编辑,删除  |
#      |字典       |读取,编辑,删除  |
#      |知识       |读取,编辑,删除  |
#      |搜索宏     |读取,编辑,删除  |
#      |字段提取   |读取,编辑,删除  |
#      |报表       |读取,编辑,删除  |
#      |定时任务   |读取,编辑,删除  |
#      |已存搜索   |读取,编辑,删除  |
#      |日志来源   |读取,编辑,删除  |
#      |拓扑图     |读取,编辑,删除  |
      |趋势图     |读取,分配,删除  |