Feature:查询定时任务

  Background:
    Given I click the "TimedTaskPage" button
    Then I will see the "timedTask.ListPage" page

  Scenario Outline: 根据定时任务分组进行查询
    Given I choose the "<group>" from the "GroupList"

    Examples:
      | group |
      | sxj   |