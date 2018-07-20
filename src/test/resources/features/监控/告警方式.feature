Feature: 新建事件数监控并填写告警方式

  Background:
    Given I click the "AlertsListPage" button
    Then I will see the "alert.AlertsListPage" page
    And There is a "thereIsNoAlert" with "{'name':'AutoTest'}"

  @alert
  Scenario Outline: 创建一个事件数监控-告警方式
    Given I click the "CreateAlert" button
    And I will see the "alert.AlertsCreatePage" page
    When I set the parameter "AlertName" with value "<AlertName>"
    And I set the parameter "AlertDes" with value "AlertDes"
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
    And I click the "AlertNoteTypeTab" button
    And I add a "<FunctionName>" with paramter "<Parameter>"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all
  Examples: 创建监控成功
    | AlertName | FunctionName  |   Parameter   |  Result                 |
    | AutoTest  | rsyslogAlert  |{'address':'alltest.rizhiyi.com:514','protocol':['UDP'],'level':['INFO'],'facility':'local0','condision':[''],'content':'{{ alert.name }}\|{{ alert.strategy.trigger.start_time\|date:\'Y-m-d H:i:s\' }}\|{{ alert.strategy.trigger.end_time\|date:\'Y-m-d H:i:s\' }}\|{{ alert.search.query }}'}  | success message "保存成功" |

