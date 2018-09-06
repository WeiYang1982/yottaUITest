Feature: 给指定用户分组

  Background:
    Given Delete a "user" with "{'name':['AutoTest']}"
    And Create a "user" with "{'name':'AutoTest','fullname':'','email':'AutoTest@yottabyte.cn','telephone':'','password':'qqqqq11111','userGroup':['admin']}"
    And Create a "userGroup" with "{'name':'AutoTest','owner':['admin'],'role':['admin']}"
    And Create a "userGroup" with "{'name':'AutoTest1','owner':['admin'],'role':['admin']}"
    And open the "users.ListPage" page for uri "/account/users/"

  @users
  Scenario Outline: 为用户增加用户分组
    Given I set the parameter "SearchInput" with value "AutoTest"
    And I wait table element "SearchResultTable-1.2" change text to "AutoTest"
    When I click the table "TableChangeGroupButton-1" button
    And I choose the "<UserGroups>" from the "UserGroups"
    And I click the "ConfirmButton" button
    Then I will see the success message "保存成功"

  @all @smoke
  Examples:
    |UserGroups|
    |AutoTest  |
    |AutoTest,AutoTest1|


  @users
  Scenario Outline: 为用户取消用户分组
    Given I set the parameter "SearchInput" with value "AutoTest"
    And I wait table element "SearchResultTable-1.2" change text to "AutoTest"
    When I click the table "TableChangeGroupButton-1" button
    And I cancel selection "<UserGroups>" from the "UserGroups"
    And I click the "ConfirmButton" button
    Then I will see the <Result>

  @all
  Examples:
    |UserGroups|Result|
    |admin     |error message "保存失败: 没有参数, 参数：[user_group_ids]\n错误码: FE_3"|
