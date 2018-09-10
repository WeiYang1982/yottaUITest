Feature: 创建一个用户分组

  Background:
    Given Delete a "userGroup" with "{'name':['AutoTest','><script>alert(1)</script>']}"
    And Create a "role" with "{'name':'AutoTestForUserGroup','RoleDes':'','ResourceGroups':['日志来源']}"
    And open the "userGroups.ListPage" page for uri "/account/usergroups/"

  @smoke @userGroups
  Scenario Outline:
    Given I need "<NeedRun>" create a "userGroup" with "{'name':'AutoTest','owner':['admin'],'role':['admin']}"
    And I click the "CreateUserGroup" button
    Then I will see the "userGroups.CreatePage" page
    When I set the parameter "UserGroupName" with value "<UserGroupName>"
    When I set the parameter "UserGroupDes" with value "<UserGroupDes>"
    And I choose the "<Owner>" from the "UserGroupOwner"
    And I choose the "<Role>" from the "UserGroupRole"
    And I click the "CreateButton" button
    Then I will see the <Result>

  @all
  Examples: 创建成功
    |NeedRun|UserGroupName|UserGroupDes|Owner         |Role                      |Result|
    |N      |AutoTest     |des1        |admin         |AutoTestForUserGroup      |success message "创建成功"|
    |N      |AutoTest     |            |admin,autotest|AutoTestForUserGroup      |success message "创建成功"|
    |N      |AutoTest     |des1        |admin         |admin,AutoTestForUserGroup|success message "创建成功"|
    |N      |><script>alert(1)</script>|des1|admin|admin|success message "创建成功"|

  @all
  Examples: 创建失败及为空校验
    |NeedRun|UserGroupName|UserGroupDes|Owner   |Role                 |Result|
    |N      |             |des         |admin   |AutoTestForUserGroup |error message "分组名 不能为空"|
    |N      |AutoTest     |des         |        |AutoTestForUserGroup |error message "拥有者 不能为空"|
    |N      |AutoTest     |            |admin   |        |error message "角色 不能为空"|
    |Y      |AutoTest     |des         |admin   |admin   |error message "用户组已存在\n错误码: FE_536"|