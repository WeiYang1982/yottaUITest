Feature: 新建字段统计监控

  Background:
    Given I click the "AlertsListPage" button
    Then I will see the "alert.AlertsListPage" page
    And There is a "thereIsNoAlert" with "{'name':'AutoTest'}"

  @alert
  Scenario Outline: 创建一个新的告警-字段统计监控-定时执行
    Given I click the "CreateAlert" button
    And I will see the "alert.AlertsCreatePage" page
    When I set the parameter "AlertName" with value "<AlertName>"
    And I set the parameter "AlertDes" with value "alertDes"
    And I choose the "<AlertGroup>" from the "AlertGroups"
    And I choose the "<AlertUser>" from the "AlertUsers"
    And I choose the "<AlertSource>" from the "AlertSources"
    And I set the parameter "SearchContent" with value "<SearchContent>"
    And I switch the "AlertEnable" button to "disable"
    And I choose the "字段统计监控" from the "AlertTypes"
    And I click the "AlertPlanTimeButton" button
    And I set the parameter "AlertPlanTimeInput" with value "<AlertPlanTime>"
    And I choose the "<TimeUnits>" from the "AlertPlanTimeUnits"
    And I set the parameter "AlertTriggerFieldsInput" with value "<AlertTriggerFields>"
    And I set the parameter "AlertTriggerInput" with value "<AlertTrigger>"
    And I choose the "<AlertTriggerTimeUnits>" from the "AlertTriggerHourOrMinute"
    And I choose the "<ConditionTypes>" from the "ConditionTypes"
    And I choose the ">" from the "Conditions"
    And I set the parameter "AlertLevelInput" with value "<AlertLevelInput>"
    And I choose the "<AlertLevel>" from the "AlertLevelUnit"
    And I click the "AddThresholdButton" button
    And I set the parameter "MiddleLevelInput" with value "<MiddleLevelInput>"
    And I click the "AddThresholdButton" button
    And I set the parameter "HighLevelInput" with value "<HighLevelInput>"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all @smoke
    Examples: 创建字段统计监控成功
      | AlertName     | AlertGroup     | AlertUser | AlertSource | SearchContent | AlertPlanTime | TimeUnits | AlertTriggerFields  |AlertTrigger | AlertTriggerTimeUnits | ConditionTypes  |AlertLevelInput | AlertLevel | MiddleLevelInput | HighLevelInput | Result |
      | AutoTest      | default_Alert  | owner     | 所有日志     | *             | 2             | 分钟       | apache.resp_len 50  |50           | 小时内                | 独立数           |1000            | 低         | 2700             | 3000           |success message "保存成功" |
      | AutoTest_sum  | default_Alert  | owner     | 所有日志     | *             | 2             | 分钟       | apache.resp_len 50  |50           | 小时内                | 总计             |1000            | 低         | 101800000        | 101855742      |success message "保存成功" |
      | AutoTest_avg  | default_Alert  | owner     | 所有日志     | *             | 2             | 分钟       | apache.resp_len 50  |50           | 小时内                | 平均数           |3000            | 低         | 4000             | 5000           |success message "保存成功" |
      | AutoTest_max  | default_Alert  | owner     | 所有日志     | *             | 2             | 分钟       | apache.resp_len 50  |50           | 小时内                | 最大数           |1               | 低         | 20               | 30             |success message "保存成功" |
      | AutoTest_min  | default_Alert  | owner     | 所有日志     | *             | 2             | 分钟       | apache.status       |5            | 小时内                | 最小数           |199             | 低         | 404              | 500            |success message "保存成功" |
