Feature: 新建事件数监控及页面的各个检查项

  Background:
    Given Delete a "alert" with "{'name':['AutoTest']}"
    And open the "alert.ListPage" page for uri "/alerts/"

  @alert
  Scenario Outline: 创建一个新的告警-事件数监控-定时执行
    Given I click the "CreateAlert" button
    And I will see the "alert.CreatePage" page
    When I set the parameter "AlertName" with value "<AlertName>"
    And I set the parameter "AlertDes" with value "<AlertDes>"
    And I choose the "<AlertGroup>" from the "AlertGroups"
    And I choose the "<AlertUser>" from the "AlertUsers"
    And I choose the "<AlertSource>" from the "AlertSources"
    And I set the parameter "SearchContent" with value "<SearchContent>"
    And I switch the "AlertEnable" button to "disable"
    And I choose the "事件数监控" from the "AlertTypes"
    And I click the "AlertPlanTimeButton" button
    And I set the parameter "AlertPlanTimeInput" with value "<AlertPlanTime>"
    And I choose the "<TimeUnits>" from the "AlertPlanTimeUnits"
    And I set the parameter "AlertTriggerInput" with value "<AlertTrigger>"
    And I choose the "<AlertTriggerTimeUnits>" from the "AlertTriggerHourOrMinute"
    And I choose the "计数" from the "ConditionTypes"
    And I choose the ">" from the "Conditions"
    And I set the parameter "AlertLevelInput" with value "<AlertLevelInput>"
    And I choose the "<AlertLevel>" from the "AlertLevelUnit"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @smoke @all
  Examples: 创建事件数监控成功
    | AlertName | AlertDes | AlertGroup       | AlertUser | AlertSource | SearchContent     | AlertPlanTime | TimeUnits | AlertTrigger | AlertTriggerTimeUnits | AlertLevelInput | AlertLevel | Result                 |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          | success message "保存成功" |
    | AutoTest  |          | default_Alert,11 | owner     | 所有日志     | *                 | 3             | 分钟       | 5           | 小时内                 | 3               | 中          | success message "保存成功" |
    | AutoTest  |          | default_Alert    | test1     | 所有日志     | *                 | 3             | 分钟       | 5           | 分钟内                 | 3               | 高          | success message "保存成功" |

  @all
  Examples: 创建事件数监控失败
    | AlertName | AlertDes | AlertGroup    | AlertUser | AlertSource | SearchContent | AlertPlanTime | TimeUnits | AlertTrigger | AlertTriggerTimeUnits | AlertLevelInput | AlertLevel | Result |
#    |           | alertDes | default_Alert | owner     | 所有日志     | *             | 10            | 分钟      | 10           | 分钟内                 | 100             | 低          | error message "请填写监控名称"                                |
#    | AutoTest  | alertDes |               | owner     | 所有日志     | *             | 10            | 分钟      | 10           | 分钟内                 | 100             | 低          | error message "请选择监控分组"                                |
#    | AutoTest  | alertDes | default_Alert | owner     |             | *             | 10            | 分钟      | 10           | 分钟内                 | 100             | 低          | error message "请填写日志来源"                                |
#    | AutoTest  | alertDes | default_Alert | owner     | 所有日志     |               | 10            | 分钟      | 10           | 分钟内                 | 100             | 低          | error message "请填写搜索内容"                                |
#    | AutoTest  | alertDes | default_Alert | owner     | 所有日志     | *             |               | 分钟      | 10           | 分钟内                 | 100             | 低          | error message "请填写执行计划"                                |
#    | AutoTest  | alertDes | default_Alert | owner     | 所有日志     | *             | 10            | 分钟      |              | 分钟内                 | 100             | 低          | error message "请正确填写触发条件"                              |
#    | AutoTest  | alertDes | default_Alert | owner     | 所有日志     | *             | 10            | 分钟      | a            | 分钟内                 | 100             | 低          | error message "请正确填写数字型触发条件"                              |
#    | AutoTest  | alertDes | default_Alert | owner     | 所有日志     | *             | 10            | 分钟      | 10           | 分钟内                 |                 | 低          | error message "请检查并填写数字类型阈值！低、中、高全部填写，或任填其一；阈值等级不可重复。" |
#    | AutoTest  | alertDes | default_Alert | owner     | 所有日志     | *             | 10            | 分钟      | 10           | 分钟内                 | a               | 低          | error message "请正确填写数字型阈值" |

  @alert
  Scenario Outline: 创建一个新的告警-事件数监控-crontab
    Given I click the "CreateAlert" button
    And I will see the "alert.CreatePage" page
    When I set the parameter "AlertName" with value "<AlertName>"
    And I set the parameter "AlertDes" with value "<AlertDes>"
    And I choose the "<AlertGroup>" from the "AlertGroups"
    And I choose the "<AlertUser>" from the "AlertUsers"
    And I choose the "<AlertSource>" from the "AlertSources"
    And I set the parameter "SearchContent" with value "<SearchContent>"
    And I switch the "AlertEnable" button to "disable"
    And I choose the "事件数监控" from the "AlertTypes"
    And I click the "AlertPlanCrontabButton" button
    And I set the parameter "AlertPlanCrontabInput" with value "<AlertPlanCrontab>"
    And I set the parameter "AlertTriggerInput" with value "<AlertTrigger>"
    And I choose the "<AlertTriggerTimeUnits>" from the "AlertTriggerHourOrMinute"
    And I choose the "计数" from the "ConditionTypes"
    And I choose the ">" from the "Conditions"
    And I set the parameter "AlertLevelInput" with value "<AlertLevelInput>"
    And I choose the "低" from the "AlertLevelUnit"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all @smoke
  Examples: 创建成功
    | AlertName | AlertDes | AlertGroup | AlertUser | AlertSource | SearchContent | AlertPlanCrontab | AlertTrigger | AlertTriggerTimeUnits | AlertLevelInput | Result |
#    | AutoTest  | alertDes | default_Alert | owner     | 所有日志     | where_es      | 0 0-5 9 * *      | 10           | 分钟内                 | 100             | success message "保存成功" |


  @alert @smoke @all
  Scenario: 创建一个新的告警-事件数监控-设置三种监控级别
    Given I click the "CreateAlert" button
    And I will see the "alert.CreatePage" page
    When I set the parameter "AlertName" with value "AutoTest"
    And I set the parameter "AlertDes" with value "alertDes"
    And I choose the "default_Alert" from the "AlertGroups"
    And I choose the "owner" from the "AlertUsers"
    And I choose the "所有日志" from the "AlertSources"
    And I set the parameter "SearchContent" with value "*"
    And I switch the "AlertEnable" button to "disable"
    And I choose the "事件数监控" from the "AlertTypes"
    And I click the "AlertPlanTimeButton" button
    And I set the parameter "AlertPlanTimeInput" with value "5"
    And I choose the "分钟" from the "AlertPlanTimeUnits"
    And I set the parameter "AlertTriggerInput" with value "5"
    And I choose the "分钟内" from the "AlertTriggerHourOrMinute"
    And I choose the "计数" from the "ConditionTypes"
    And I choose the ">" from the "Conditions"
    And I set the parameter "AlertLevelInput" with value "3"
    And I choose the "低" from the "AlertLevelUnit"
    And I click the "AddThresholdButton" button
    And I set the parameter "MiddleLevelInput" with value "10"
    And I click the "AddThresholdButton" button
    And I set the parameter "HighLevelInput" with value "20"
    And I click the "SaveButton" button
    Then I will see the success message "保存成功"

