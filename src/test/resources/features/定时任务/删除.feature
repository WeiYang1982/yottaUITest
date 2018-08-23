Feature:删除定时任务

  Background:
    Given I click the "TimedTaskPage" button
    Then I will see the "timedTask.ListPage" page

  @timedTask
  Scenario Outline:
    When the data name is "<name>" then i click the "删除" button
    Then I click the "EnsureDelete" button
    Then I will see the success message "删除成功"

    @all @smoke
    Examples:
      | name        |
      | sxjautotest |