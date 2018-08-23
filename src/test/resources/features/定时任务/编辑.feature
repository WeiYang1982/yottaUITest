Feature: 编辑定时任务

  Background:
    Given I click the "TimedTaskPage" button
    Then I will see the "timedTask.ListPage" page

  @timedTask
  Scenario Outline: 编辑定时任务
    When the data name is "<dataName>" then i click the "编辑" button
    Then I will see the "timedTask.EditPage" page
    Then I set the parameter "Name" with value "<name>"
    Then I set the parameter "Describe" with value "<describe>"
    Then I choose the "<user>" from the "User"
    Then I choose the "<resource>" from the "Resource"
    Then I choose the "<taskGroup>" from the "TaskGroup"
    Then I set the parameter "Period" with value "<period>"
    Then I set the parameter "StartTime" with value "<startTime>"
    Then I click the "EnsureButton" button
    Then I click the "SaveButton" button
    Then I will see the success message "<result>"

  @all @smoke
    Examples:
      | dataName    | name        | describe | user  | resource | taskGroup | period | startTime | result |
      | sxjautotest | sxjautotest |          | sunxc | all_     | sxj       | 10     | 16:37:55  | 保存成功   |

  @all
    Examples:
      | name | describe | user | resource | taskGroup | period | startTime | result  |
      |      |          |      |          |           |        |           | 名称 不能为空 |

  @timedTask
  Scenario Outline: 成功编辑定时任务的结果处理方式
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
    Then I will see the alertErrorMessage "验证成功"
    Then I set the parameter "FirstDataMapping" with value "<firstDataMapping>"
    Then I set the parameter "SecondDataMapping" with value "<secondDataMapping>"
    Then I click the "SaveButton" button
    Then I will see the success message "保存成功"

  @all @smoke
    Examples:
      | connectName | userName | password     | host          | port | dbType | dbName         | tableName | firstDataMapping | secondDataMapping |
      | sxjtest     | root     | rizhiyi&2014 | 192.168.1.140 | 3306 | mysql  | rizhiyi_system | tyf       | count            | percent           |

  @timedTask
  Scenario Outline: 未成功编辑定时任务的结果处理方式
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
    Then I will see the success message "<result>"

  @all
    Examples:
      | connectName | userName | password     | host          | port | dbType | dbName         | tableName | result                             |
      |             |          |              |               |      |        |                |           | SCHEDULED.CONNECTION_NAME 不能为空     |
      | sxj         |          |              |               |      |        |                |           | SCHEDULED.USERNAME 不能为空            |
      | sxj         | root     |              |               |      |        |                |           | SCHEDULED.PASSWORD 不能为空            |
      | sxj         | root     | rizhiyi&2014 |               |      |        |                |           | SCHEDULED.HOST 不能为空                |
      | sxj         | root     | rizhiyi&2014 | 192.168.1.140 |      |        |                |           | SCHEDULED.PORT 不能为空                |
      | sxj         | root     | rizhiyi&2014 | 192.168.1.140 | 3306 |        |                |           | SCHEDULED.DATEBASE_TYPE 不能为空       |
      | sxj         | root     | rizhiyi&2014 | 192.168.1.140 | 3306 | mysql  |                |           | SCHEDULED.DATEBASE_NAME 不能为空       |
      | sxj         | root     | rizhiyi&2014 | 192.168.1.140 | 3306 | mysql  | rizhiyi_system |           | SCHEDULED.DATEBASE_TABLE_NAME 不能为空 |






