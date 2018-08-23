Feature:禁用定时任务

  Background:
    Given I click the "TimedTaskPage" button
    Then I will see the "timedTask.ListPage" page

  @timedTask
  Scenario:
    Given I click the "SwitchButton" button
    Then I will see the success message "禁用成功"
  