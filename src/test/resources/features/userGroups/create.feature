Feature: 创建一个用户分组

  Background:
    Given I click the "UserGroupsPage" button
    Then I will see the "userGroups.ListPage" page

  @smoke @usergroups
  Scenario Outline:
#    Given need run condition "<NeedRun>" There is a userGroup with name "autotest" , owner "admin" , role "admin"
    And I click the "CreateUserGroup" button
    Then I will see the "userGroups.CreatePage" page
    When I set the parameter "UserGroupName" with value "<UserGroupName>"
    When I set the parameter "UserGroupDes" with value "<UserGroupDes>"
    And I choose the "<Owner>" from the "UserGroupOwner"
    And I choose the "<Role>" from the "UserGroupRole"
    And I click the "CreateButton" button
    Then I will see the <Result>

  Examples:
    |UserGroupName|UserGroupDes|Owner|Role|Result|
    |autotest1|des1|admin|autotest|success message "创建成功"|
    |autotest2|des1|admin,autotest|autotest|success message "创建成功"|

  @all
  Examples:
    |UserGroupName|Owner|Role|Result|
    |              |admin|autotest|error message "分组名 不能为空"|
    |autotest1||autotest|error message "拥有者 不能为空"|
    |autotest1|admin||error message "角色 不能为空"|