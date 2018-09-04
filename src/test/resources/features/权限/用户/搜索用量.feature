Feature: 查看用户的搜索用量

  Background:
    Given open the "users.UsagePage" page for uri "/usage/"

  @users @all
  Scenario:
    Given I click the "SearchButton" button
    Then I will see the error message "请输入时间范围"

  @users @all
  Scenario:
    Given I click the "DateButton" button
    And I set the parameter "StartTime" with value "2018-09-04"
    And I set the parameter "EndTime" with value "2018-09-05"
    And I click the "Confirm" button
    And I click the "SearchButton" button
    Then take a screenshot

  @users @all
  Scenario:
    Given I click the "DateButton" button
    And I set the parameter "StartTime" with value "2018-09-04"
    And I let element "StartTime" lose focus
    Then I will see the error message "开始日期需早于结束日期"

  @users @all
  Scenario:
    Given I click the "DateButton" button
    And I set the parameter "EndTime" with value "2018-09-04"
    And I let element "EndTime" lose focus
    Then I will see the error message "请输入正确的日期格式：yyyy-MM-dd"

