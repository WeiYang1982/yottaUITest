Feature: 新建事件数监控并填写高级设置

  Background:
    Given I click the "AlertsListPage" button
    Then I will see the "alert.ListPage" page
    And There is a "thereIsNoAlert" with "{'name':'AutoTest'}"

  @alert
  Scenario Outline: 创建一个事件数监控-高级配置-扩展搜索
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
    And I click the "AdvancedConfigTab" button
    And I set the parameter "ExSearchContent" with value "<ExSearchContent>"
    And I choose the "<ExAlertSources>" from the "ExAlertSources"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all
  Examples: 创建监控成功
    | AlertName | AlertDes | AlertGroup       | AlertUser | AlertSource | SearchContent     | AlertPlanTime | TimeUnits | AlertTrigger | AlertTriggerTimeUnits | AlertLevelInput | AlertLevel | ExSearchContent   | ExAlertSources | Result                 |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          | logtype:"apache"  |   所有日志      |success message "保存成功" |

  @all
  Examples: 创建监控失败
    | AlertName | AlertDes | AlertGroup       | AlertUser | AlertSource | SearchContent     | AlertPlanTime | TimeUnits | AlertTrigger | AlertTriggerTimeUnits | AlertLevelInput | AlertLevel | ExSearchContent   | ExAlertSources | Result                 |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          |                   |   所有日志      |error message "请填写扩展搜索内容" |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          | logtype:"apache"  |                |error message "请填写扩展搜索日志来源" |

  @alert
  Scenario Outline: 创建一个事件数监控-高级配置-抑制告警-固定时间
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
    And I click the "AdvancedConfigTab" button
    And I click the "SuppressButton" button
    And I set the parameter "FixedPeriodInput" with value "<FixedPeriodInput>"
    And I choose the "<FixedPeriodUnits>" from the "FixedPeriodUnits"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all
  Examples: 创建监控成功
    | AlertName | AlertDes | AlertGroup       | AlertUser | AlertSource | SearchContent     | AlertPlanTime | TimeUnits | AlertTrigger | AlertTriggerTimeUnits | AlertLevelInput | AlertLevel | FixedPeriodInput   | FixedPeriodUnits |Result                 |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          | 1                  |  天内            |success message "保存成功" |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          | 20                 |  分钟内          |success message "保存成功" |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          | 1                  |  小时内          |success message "保存成功" |

  @all
  Examples: 创建监控失败
    | AlertName | AlertDes | AlertGroup       | AlertUser | AlertSource | SearchContent     | AlertPlanTime | TimeUnits | AlertTrigger | AlertTriggerTimeUnits | AlertLevelInput | AlertLevel | FixedPeriodInput   | FixedPeriodUnits |Result                 |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          |                    |  小时内          |error message "告警抑制的初始值（第一次告警）非法" |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          |  a                 |  小时内          |error message "告警抑制的初始值（第一次告警）非法" |

  @alert
  Scenario Outline: 创建一个事件数监控-高级配置-抑制告警-倍增时间
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
    And I click the "AdvancedConfigTab" button
    And I click the "SuppressButton" button
    And I set the parameter "FixedPeriodInput" with value "<FixedPeriodInput>"
    And I choose the "<FixedPeriodUnits>" from the "FixedPeriodUnits"
    And I click the "CheckBox" button
    And I set the parameter "CancelSuppressInput" with value "<CancelSuppressInput>"
    And I choose the "<CancelSuppressUnits>" from the "CancelSuppressUnits"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all
  Examples: 创建监控成功
    | AlertName | AlertDes | AlertGroup       | AlertUser | AlertSource | SearchContent     | AlertPlanTime | TimeUnits | AlertTrigger | AlertTriggerTimeUnits | AlertLevelInput | AlertLevel | FixedPeriodInput   | FixedPeriodUnits |CancelSuppressInput | CancelSuppressUnits | Result                 |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          | 10                 |  分钟内          |      50            |    分钟后            |success message "保存成功" |

  @all
  Examples: 创建监控失败
    | AlertName | AlertDes | AlertGroup       | AlertUser | AlertSource | SearchContent     | AlertPlanTime | TimeUnits | AlertTrigger | AlertTriggerTimeUnits | AlertLevelInput | AlertLevel | FixedPeriodInput   | FixedPeriodUnits |CancelSuppressInput | CancelSuppressUnits | Result                 |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          | 10                 |  分钟内          |                    |    分钟后            |error message "告警抑制的最大时间（取消抑制）非法" |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          | 10                 |  分钟内          |      a             |    分钟后            |error message "告警抑制的最大时间（取消抑制）非法" |


  @alert
  Scenario Outline: 创建一个事件数监控-高级配置-启用效果插图
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
    And I click the "AdvancedConfigTab" button
    And I set the parameter "ExSearchContent" with value "<ExSearchContent>"
    And I choose the "<ExAlertSources>" from the "ExAlertSources"
    And I click the "GraphEnable" button
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all
  Examples: 创建监控成功
    | AlertName | AlertDes | AlertGroup       | AlertUser | AlertSource | SearchContent     | AlertPlanTime | TimeUnits | AlertTrigger | AlertTriggerTimeUnits | AlertLevelInput | AlertLevel | ExSearchContent                     | ExAlertSources | Result                 |
    | AutoTest  | alertDes | default_Alert    | owner     | 所有日志     | *                 | 5             | 分钟       | 5           | 分钟内                 | 3               | 低          | * \| timechart count() by hostname  |   所有日志      |success message "保存成功" |
