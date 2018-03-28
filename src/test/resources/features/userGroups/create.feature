Feature: 创建一个用户分组

  Background:
    Given I click the "UserGroupsPage" button
    Then I will see the "userGroups.ListPage" page
    And There is a "thereIsNoUserGroup" with "name(String):AutoTest"
    And There is a "thereIsNoUserGroup" with "name(String):><script>alert(1)</script>"

  @smoke @usergroups
  Scenario Outline:
    Given need run condition "<NeedRun>" There is a "thereIsAUserGroup" with "name(String):autoTest;owner(List):admin;role(List):admin"
    And I click the "CreateUserGroup" button
    Then I will see the "userGroups.CreatePage" page
    When I set the parameter "UserGroupName" with value "<UserGroupName>"
    When I set the parameter "UserGroupDes" with value "<UserGroupDes>"
    And I choose the "<Owner>" from the "UserGroupOwner"
    And I choose the "<Role>" from the "UserGroupRole"
    And I click the "CreateButton" button
    Then I will see the <Result>

  Examples:
    |NeedRun|UserGroupName|UserGroupDes|Owner         |Role          |Result|
    |N      |AutoTest     |des1        |admin         |autotest      |success message "创建成功"|
    |N      |AutoTest     |            |admin,autotest|autotest      |success message "创建成功"|
    |N      |AutoTest     |des1        |admin         |admin,autotest|success message "创建成功"|
    |N      |><script>alert(1)</script>|des1|admin|admin|success message "创建成功"|

  @all
  Examples:
    |NeedRun|UserGroupName|UserGroupDes|Owner   |Role    |Result|
    |N      |             |des         |admin   |autotest|error message "分组名 不能为空"|
    |N      |AutoTest     |des         |        |autotest|error message "拥有者 不能为空"|
    |N      |AutoTest     |            |admin   |        |error message "角色 不能为空"|
    |Y      |AutoTest     |des         |admin   |admin   |error message "用户组已存在\n错误码: FE_536"|