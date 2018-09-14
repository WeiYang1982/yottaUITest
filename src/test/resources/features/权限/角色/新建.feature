Feature: 新建一个角色

  Background:
    Given Delete a "role" with "{'name':['AutoTest','AutoTestForRole']}"
    And open the "roles.ListPage" page for uri "/account/roles/"

  @role
  Scenario Outline: 创建角色同时不创建资源分组
    Given I need "<NeedRun>" create a "role" with "{'name':'AutoTest','RoleDes':'','ResourceGroups':['日志来源']}"
    And I click the "CreateRoleButton" button
    And I will see the "roles.CreatePage" page
    And I set the parameter "RoleName" with value "<RoleName>"
    And I set the parameter "RoleDes" with value "<RoleDes>"
    When I click the "CreateButton" button
    Then I will see the <Result>

  @all @smoke
    Examples:
    |NeedRun|RoleName |RoleDes|Result|
#    |N      |AutoTest |Des    |success message "创建成功"|

  @all
  Examples:
    |NeedRun|RoleName |RoleDes|Result|
#    |Y      |AutoTest |       |error message "保存失败: 角色名称已经在\n错误码: FE_590"|
#    |N      |         |RoleDes|error message "填写角色名称"|


  @role
  Scenario Outline: 创建角色同时创建资源分组
    Given
    And Delete a "resourceGroup" with "{'name':['AutoTestForRole']}"
    And open the "roles.ListPage" page for uri "/account/roles/"
    And I click the "CreateRoleButton" button
    And I will see the "roles.CreatePage" page
    And I set the parameter "RoleName" with value "<RoleName>"
    And I set the parameter "RoleDes" with value "<RoleDes>"
    And I check "<ResourceGroups>" from the "ResourceGroupCheckbox"
    When I click the "CreateButton" button
    Then I will see the <Result>

  @all
  Examples:
    |RoleName        |RoleDes|ResourceGroups |Result|
    |AutoTestForRole |Des    |all            |success message "创建成功。自动创建资源分组个数：13 ，请进入资源分组查看"|
    |AutoTestForRole |Des    |仪表盘,日志来源  |success message "创建成功。自动创建资源分组个数：2 ，请进入资源分组查看"|
    |AutoTestForRole |Des    |搜索宏          |success message "创建成功。自动创建资源分组个数：1 ，请进入资源分组查看"|