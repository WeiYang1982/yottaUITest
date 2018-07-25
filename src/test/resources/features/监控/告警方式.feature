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
    And I choose the "default_Alert" from the "AlertGroups"
    And I choose the "所有日志" from the "AlertSources"
    And I set the parameter "SearchContent" with value "*"
    And I switch the "AlertEnable" button to "disable"
    And I set the parameter "AlertTriggerInput" with value "5"
    And I set the parameter "AlertLevelInput" with value "3"
    And I click the "AddThresholdButton" button
    And I set the parameter "MiddleLevelInput" with value "100"
    And I click the "AlertNoteTypeTab" button
    And I add a "<FunctionName>" with parameter "<Parameter>"
    And I click the "SaveButton" button
    Then I will see the <Result>

  @all
  Examples: 创建监控成功
    | AlertName | FunctionName  |   Parameter   |  Result                 |
    | AutoTest  | rsyslogType   |{'address':'alltest.rizhiyi.com:514','protocol':['UDP'],'level':['INFO'],'facility':'local0','condition':[''],'content':'{{ alert.name }}\|{{ alert.strategy.trigger.start_time\|date:\'Y-m-d H:i:s\' }}\|{{ alert.strategy.trigger.end_time\|date:\'Y-m-d H:i:s\' }}\|{{ alert.search.query }}'}  | success message "保存成功" |
    | AutoTest  | emailType     |{'title':'auto test alert.','email':['autotest@yottabyte.cn'],'condition':[''],'content':''}  | success message "保存成功" |
    | AutoTest  | forwardType   |{'address':'http://192.168.1.82:511111/','condition':['']}  | success message "保存成功" |
    | AutoTest  | forwardType   |{'address':'http://192.168.1.82:511111/','condition':['低']}  | success message "保存成功" |
    | AutoTest  | pingHostType  |{'address':'192.168.1.82','condition':['']}  | success message "保存成功" |

