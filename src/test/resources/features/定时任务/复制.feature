Feature:复制定时任务

  Background:
    Given I click the "TimedTaskPage" button
    Then I will see the "timedTask.ListPage" page

  Scenario:
    Then I click the "copy" button
    Then I will see the success message "复制成功"