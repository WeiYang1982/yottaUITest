Feature: 修改一个已经存在的用户分组

  Background:
    Given Delete a "userGroup" with "{'name':['NewAutoTest','AutoTest']}"
    And Create a "userGroup" with "{'name':'AutoTest','owner':['admin'],'role':['admin']}"
    And open the "userGroups.ListPage" page for uri "/account/usergroups/"

  @userGroups
  Scenario Outline:
    Given I set the parameter "SearchInput" with value "<UserGroupName>"
    And I wait table element "SearchResultTable-1.2" change text to "<UserGroupName>"
    And I click the table "TableEditButton-1" button
    And I will see the "userGroups.EditPage" page
    When I set the parameter "UserGroupName" with value "<NewUserGroupName>"
    And I set the parameter "UserGroupDes" with value "<NewUserGroupDes>"
    And I click the "BasicSaveButton" button
    Then I will see the <Result>

  @smoke @all
  Examples:
    |UserGroupName|NewUserGroupName|NewUserGroupDes|Result|
    |AutoTest     |NewAutoTest     |NewDes         |success message "更新成功"|

  @all
  Examples:
    |UserGroupName|NewUserGroupName|NewUserGroupDes|Result|
    |AutoTest     |                |NewDes         |error message "分组名 不能为空"|
    |AutoTest     |admin           |NewDes         |error message "用户组已存在\n错误码: FE_536"|

# 等待bug修复之后再继续调试  http://pha.yottabyte.cn/T3484
  Scenario Outline:
    Given There is a "thereIsAUserGroup" with "{'name':'AutoTest','owner':['admin'],'role':['admin']}"
    And I set the parameter "SearchInput" with value "autoTest"
    And I wait table element "SearchResultTable-1.2" change text to "autoTest"
    And I click the table "TableEditButton-1" button
    And I will see the "userGroups.EditPage" page
    When I cancel selection "<RoleName>" from the "UserGroupRole"
    And I choose the "<NewRoleName>" from the "UserGroupRole"
    And I click the "AdvancedSaveButton" button
    Then I will see the <Result>

  Examples:
    |RoleName|NewRoleName      |Result|
#    |admin   |autotest         |success message "更新成功"|
#    |admin   |admin,autotest   |success message "更新成功"|

  Examples:
    |RoleName|NewRoleName|Result|
#    |admin   |           |error message "角色 不能为空"|