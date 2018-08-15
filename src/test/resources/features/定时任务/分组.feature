Feature:定时任务分组

  Background:
    Given I click the "TimedTaskPage" button
    Then I will see the "timedTask.ListPage" page

  Scenario Outline:
    Given I click the "ChangeGroup" button
    Then I choose the "<group>" from the "Group"
    Then I click the "EnsureChangeGroup" button
    Then I will see the success message "保存成功"

  @all @smoke
    Examples:
      | group      |
      | tyf资源组,A系统 |