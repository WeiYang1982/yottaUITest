Feature: 禁用一个用户

  Background:
    Given I click the "UsersPage" button
    And I will see the "users.ListPage" page
    And There is a "thereIsNoUser" with "{'name':'AutoTest'}"
    And There is a "thereIsAUser" with "{'name':'AutoTest','fullname':'','email':'AutoTest@yottabyte.cn','telephone':'','password':'qqqqq11111','userGroup':['admin']}"

  @all @smoke @users
  Scenario: 禁用一个用户成功
    Given I set the parameter "SearchInput" with value "AutoTest"
    And I wait table element "SearchResultTable-1.2" change text to "AutoTest"
    When I click the table "TableDisableButton-1" button
    Then I will see the "UserStatus" result will be "已禁用"
