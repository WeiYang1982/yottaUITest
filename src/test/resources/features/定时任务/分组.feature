Feature:定时任务分组

  Background:
    Given I click the "TimedTaskPage" button
    Then I will see the "timedTask.ListPage" page

  @timedTask
  Scenario Outline:
    When the data name is "<name>" then i click the "分组" button
    Then I choose the "<group>" from the "Group"
    Then I click the "EnsureChangeGroup" button
    Then I will see the success message "保存成功"

  @all @smoke
    Examples:
      | name          | group      |
      | forceAutoTest | tyf资源组,A系统 |