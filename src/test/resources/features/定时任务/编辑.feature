Feature:编辑定时任务

  Background:
    Given I click the "TimedTaskPage" button
    Then I will see the "timedTask.ListPage" page

  Scenario Outline:编辑定时任务
    Given I click the "Edit" button
    Then I will see the "timedTask.EditPage" page
    Then I set the parameter "Name" with value "<name>"
    Then I set the parameter "Describe" with value "<describe>"
    Then I choose the "<user>" from the "User"
    Then I choose the "<resource>" from the "Resource"
    Then I choose the "<taskGroup>" from the "TaskGroup"
    Then I set the parameter "Period" with value "<period>"
    Then I set the "StartTime" with "<startTime>"
    Then I click the "SaveButton" button
    Then I will see the success message "<result>"

  @all @smoke
    Examples:
      | name        | describe | user  | resource | taskGroup | period | startTime | result |
      | sxjautotest |          | sunxc | all_     | sxj       | 10     | 16:37:55  | 保存成功   |

  @all
    Examples:
      | name | describe | user | resource | taskGroup | period | startTime | result  |
      |      |          |      |          |           |        |           | 名称 不能为空 |



