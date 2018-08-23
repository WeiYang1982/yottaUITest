Feature:复制定时任务

  Background:
    Given I click the "TimedTaskPage" button
    Then I will see the "timedTask.ListPage" page

  @timedTask
  Scenario Outline:
    When the data name is "<name>" then i click the "复制" button
    Then I will see the success message "复制成功"

    @all @smoke
    Examples:
      | name        |
      | sxjautotest |