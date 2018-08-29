Feature: 删除用户
  Background:
    Given Create a "user" with "{'name':'AutoTest','fullname':'','email':'AutoTest@yottabyte.cn','telephone':'','password':'qqqqq11111','userGroup':['admin']}"
    And open the "users.ListPage" page for uri "/account/users/"

  @all @smoke @users
  Scenario: 删除用户成功
    Given I set the parameter "SearchInput" with value "AutoTest"
    And I wait table element "SearchResultTable-1.2" change text to "AutoTest"
    When I click the table "TableDeleteButton-1" button
    And I click the "MessageBoxOKButton" button
    Then I will see the success message "删除成功"