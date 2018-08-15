Feature:删除定时任务

  Background:
    Given I click the "TimedTaskPage" button
    Then I will see the "timedTask.ListPage" page

  Scenario:
    Given I click the "Delete" button
    Then I click the "EnsureDelete" button
    Then I will see the success message "删除成功"
