Feature: 编辑指定用户

  Background:
    Given Delete a "user" with "{'name':['AutoTestNew','AutoTest','AutoTestTmp']}"
    And Create a "user" with "{'name':'AutoTest','fullname':'','email':'AutoTest@yottabyte.cn','telephone':'','password':'qqqqq11111','userGroup':['admin']}"
    And open the "users.ListPage" page for uri "/account/users/"

  @users
  Scenario Outline: 编辑用户信息
    Given I need "<NeedRun>" create a "user" with "{'name':'AutoTestNew','fullname':'','email':'AutoTestNew@yottabyte.cn','telephone':'','password':'qqqqq11111','userGroup':['admin']}"
    And I set the parameter "SearchInput" with value "<UserName>"
    And I wait table element "SearchResultTable-1.2" change text to "<UserName>"
    And I click the table "TableSeeDetailButton-1" button
    And I will see the "users.DetailPage" page
    And I click the "EditButton" button
    And I will see the "users.EditPage" page
    When I set the parameter "UserName" with value "<NewUserName>"
    And I set the parameter "FullName" with value "<FullName>"
    And I set the parameter "Email" with value "<Email>"
    And I set the parameter "Telephone" with value "<Telephone>"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @smoke @all
  Examples: 编辑用户成功
    |NeedRun|UserName|NewUserName|FullName   |Email                    |Telephone  |Result|
    |N      |AutoTest|AutoTestNew|FullName   |AutoTest@yottabyte.cn    |13111111111|success message "更新成功"|
    |N      |AutoTest|AutoTest   |NewFullName|AutoTest@yottabyte.cn    |13111111111|success message "更新成功"|
    |N      |AutoTest|AutoTest   |NewFullName|AutoTestNew@yottabyte.cn |13111111111|success message "更新成功"|
    |N      |AutoTest|AutoTest   |NewFullName|AutoTest@yottabyte.cn    |13111112222|success message "更新成功"|

  @all
  Examples:  编辑用户失败
    |NeedRun|UserName|NewUserName|FullName|Email                    |Telephone  |Result|
    |Y      |AutoTest|AutoTestNew|        |AutoTest@yottabyte.cn    |           |error message "用户名已存在\n错误码: FE_532"|
    |N      |AutoTest|           |        |AutoTest@yottabyte.cn    |           |error message "用户名 不能为空"|
    |N      |AutoTest|AutoTestNew|        |                         |           |error message "邮箱地址 不能为空"|
    |Y      |AutoTest|AutoTestTmp|        |AutoTestNew@yottabyte.cn |           |error message "邮件名已存在\n错误码: FE_533"|
    |Y      |AutoTest|AutoTestNew|        |AutoTestTmp@yottabyte.cn |           |error message "用户名已存在\n错误码: FE_532"|

  @users
  Scenario Outline:
    Given I set the parameter "SearchInput" with value "<UserName>"
    And I wait table element "SearchResultTable-1.2" change text to "<UserName>"
    And I click the table "TableSeeDetailButton-1" button
    And I will see the "users.DetailPage" page
    And I click the "EditButton" button
    And I will see the "users.EditPage" page
    When I click the "ModifyPassword" button
    And I set the parameter "NewPassword" with value "<NewPassword>"
    And I set the parameter "RepeatPassword" with value "<RepeatPassword>"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all
  Examples:
    |UserName|NewPassword|RepeatPassword|Result|
    |AutoTest|qqqqq11111 |qqqqq11111    |success message "更新成功"|

  @all
  Examples:
    |UserName|NewPassword|RepeatPassword|Result|
    |AutoTest|qqqqq11111 |              |error message "重复密码 不能为空"|
    |AutoTest|           |qqqqq11111    |error message "新密码 不能为空"|
    |AutoTest| 112       |qqqqq11111    |error message "新密码输入有误，请重新输入"|
    |AutoTest|qqqqq      |qqqqq         |error message "新密码输入有误，请重新输入"|