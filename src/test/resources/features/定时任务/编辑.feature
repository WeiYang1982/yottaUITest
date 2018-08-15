Feature: 编辑定时任务

  Background:
    Given I click the "TimedTaskPage" button
    Then I will see the "timedTask.ListPage" page

  Scenario Outline: 编辑定时任务
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

  Scenario Outline: 编辑定时任务的结果处理方式
    Given I click the "Edit" button
    Then I will see the "timedTask.EditPage" page
    Then I click the "ResultHandling" button
    Then I click the "AddJDBC" button
    Then I click the "Jdbc" button
    Then I set the parameter "ConnectName" with value "<connectName>"
    Then I set the parameter "UserName" with value "<userName>"
    Then I set the parameter "Password" with value "<password>"
    Then I set the parameter "Host" with value "<host>"
    Then I set the parameter "Port" with value "<port>"
    Then I choose the "<dbType>" from the "DbType"
    Then I set the parameter "DbName" with value "<dbName>"
    Then I set the parameter "TableName" with value "<tableName>"
    Then I click the "Verify" button

    Examples:
      | connectName | userName | password     | host          | port | dbType | dbName         | tableName |
      | sxjtest     | root     | rizhiyi&2014 | 192.168.1.140 | 3306 | mysql  | rizhiyi_system | tyf       |
    
    



