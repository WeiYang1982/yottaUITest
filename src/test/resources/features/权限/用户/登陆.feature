Feature: 用户登陆

  Background:
    Given I logout current user
#  @login
#  Scenario: login with correct username and password
#
#    Given open the "LoginPage" page for uri "/auth/login/"
#    When I set the parameter "getUsername" with value "owner"
#    And I set the parameter "Password" with value "all123456"
#    And I click the "LoginButton" button
#    Then I will see the "DashboardPage" page
#    And the page's title will be "仪表盘"

  @users
  Scenario Outline: 登陆失败
    Given open the "LoginPage" page for uri "/auth/login/"
    When I set the parameter "Username" with value "<usernameValue>"
    And I set the parameter "Password" with value "<passwordValue>"
    And I click the "LoginButton" button
    Then I will see the error message contains "<errorMessage>"

  @all
  Examples:
    |usernameValue|passwordValue|errorMessage|
    |             | all123456   |请输入用户名|
    |owner        |             |请输入密码|
    |owner        |asd          |用户名或密码错误|
    |qweax        |asd          |登录名不存在|