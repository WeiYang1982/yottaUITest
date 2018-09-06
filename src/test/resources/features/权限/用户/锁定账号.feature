Feature: 登陆失败后锁定账号
  Background:
    Given Create a "user" with "{'name':'AutoTest','fullname':'','email':'AutoTest@yottabyte.cn','telephone':'','password':'qqqqq11111','userGroup':['admin']}"
    And I logout current user
    And open the "LoginPage" page for uri "/auth/login/"

  @users @all
  Scenario: 密码输入错误次数达到设定值，账户会被锁定
    Given I run a method "Login" for "10" times with parameters "{'name':'AutoTest','password':'123'}"
    When I set the parameter "Username" with value "AutoTest"
    And I set the parameter "Password" with value "123"
    And I click the "LoginButton" button
    Then I will see the error message contains "账号已锁"
