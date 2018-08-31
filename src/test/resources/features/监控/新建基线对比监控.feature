Feature: 新建基线对比监控

  Background:
    Given Delete a "alert" with "{'name':['AutoTest']}"
    And open the "alert.ListPage" page for uri "/alerts/"

  @alert
  Scenario Outline: 创建一个新的告警-基线对比监控-定时执行
    Given I click the "CreateAlert" button
    And I will see the "alert.CreatePage" page
    When I set the parameter "AlertName" with value "<AlertName>"
    And I set the parameter "AlertDes" with value "alertDes"
    And I choose the "<AlertGroup>" from the "AlertGroups"
    And I choose the "<AlertUser>" from the "AlertUsers"
    And I choose the "<AlertSource>" from the "AlertSources"
    And I set the parameter "SearchContent" with value "<SearchContent>"
    And I switch the "AlertEnable" button to "disable"
    And I choose the "基线对比监控" from the "AlertTypes"
    And I click the "AlertPlanTimeButton" button
    And I set the parameter "AlertPlanTimeInput" with value "<AlertPlanTime>"
    And I choose the "<TimeUnits>" from the "AlertPlanTimeUnits"
    And I set the parameter "AlertTriggerFieldsInput" with value "<AlertTriggerFields>"
    And I set the parameter "AlertTriggerInput" with value "<AlertTrigger>"
    And I choose the "<AlertTriggerTimeUnits>" from the "AlertTriggerHourOrMinute"
    And I choose the "<ConditionTypes>" from the "Conditions"
    And I set the parameter "AlertLevelInput" with value "<AlertLevelInput>"
    And I choose the "<AlertLevel>" from the "AlertLevelUnit"
    And I choose the "<BaseTime>" from the "BaseLineTimeSelectors"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all @smoke
  Examples: 创建字段统计监控成功
    | AlertName | AlertGroup    | AlertUser | AlertSource | SearchContent | AlertPlanTime | TimeUnits | AlertTriggerFields | AlertTrigger | AlertTriggerTimeUnits | ConditionTypes | AlertLevelInput | AlertLevel |  BaseTime |  Result                 |
    | AutoTest  | default_Alert | owner     | 所有日志     | *             | 2             | 分钟      |  apache.resp.len   |  50          | 小时内                 | 大于            | 100             | 低         | 过去七天   | success message "保存成功" |
    | AutoTest  | default_Alert | owner     | 所有日志     | *             | 2             | 分钟      |  apache.resp.len   |  50          | 小时内                 | 小于            | 500             | 高         | 上一周     | success message "保存成功" |



  @alert
  Scenario Outline: 创建一个新的告警-基线对比监控-定时执行
    Given I click the "CreateAlert" button
    And I will see the "alert.CreatePage" page
    When I set the parameter "AlertName" with value "<AlertName>"
    And I set the parameter "AlertDes" with value "alertDes"
    And I choose the "<AlertGroup>" from the "AlertGroups"
    And I choose the "<AlertUser>" from the "AlertUsers"
    And I choose the "<AlertSource>" from the "AlertSources"
    And I set the parameter "SearchContent" with value "<SearchContent>"
    And I switch the "AlertEnable" button to "disable"
    And I choose the "基线对比监控" from the "AlertTypes"
    And I click the "AlertPlanTimeButton" button
    And I set the parameter "AlertPlanTimeInput" with value "<AlertPlanTime>"
    And I choose the "<TimeUnits>" from the "AlertPlanTimeUnits"
    And I set the parameter "AlertTriggerFieldsInput" with value "<AlertTriggerFields>"
    And I set the parameter "AlertTriggerInput" with value "<AlertTrigger>"
    And I choose the "<AlertTriggerTimeUnits>" from the "AlertTriggerHourOrMinute"
    And I choose the "<ConditionTypes>" from the "Conditions"
    And I set the parameter "AlertLevelInput" with value "<AlertLevelInput>"
    And I set the parameter "BaseLineRightInput" with value "<AlertLevelInputRight>"
    And I choose the "<AlertLevel>" from the "AlertLevelUnit"
    And I choose the "<BaseTime>" from the "BaseLineTimeSelectors"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all @smoke
  Examples: 创建字段统计监控成功
    | AlertName | AlertGroup    | AlertUser | AlertSource | SearchContent | AlertPlanTime | TimeUnits | AlertTriggerFields | AlertTrigger | AlertTriggerTimeUnits | ConditionTypes | AlertLevelInput | AlertLevelInputRight | AlertLevel |  BaseTime |  Result                 |
    | AutoTest  | default_Alert | owner     | 所有日志     | *             | 5             | 分钟      |  apache.resp.len   |   1          | 小时内                 | 在区间外        |  50             |           150        |中          | 过去七天   | success message "保存成功" |
    | AutoTest  | default_Alert | owner     | 所有日志     | *             | 5             | 分钟      |  apache.status     |   50         | 小时内                 | 在区间内        |  1              |           120        |高          | 上一周     | success message "保存成功" |
    | AutoTest  | default_Alert | owner     | 所有日志     | *             | 5             | 分钟      |  apache.resp.len   |   50         | 小时内                 | 在区间外        |  99             |           101        |低          | 过去六天   | success message "保存成功" |