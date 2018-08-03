Feature: 新建事件数监控并填写发送条件

  Background:
    Given I click the "AlertsListPage" button
    Then I will see the "alert.ListPage" page
    And There is a "thereIsNoAlert" with "{'name':'AutoTest'}"

  @alert @all @smoke
  Scenario Outline: 创建一个发送条件的监控
    Given I click the "CreateAlert" button
    And I will see the "alert.CreatePage" page
    When I set the parameter "AlertName" with value "AutoTest"
    And I choose the "default_Alert" from the "AlertGroups"
    And I choose the "所有日志" from the "AlertSources"
    And I set the parameter "SearchContent" with value "*"
    And I switch the "AlertEnable" button to "disable"
    And I set the parameter "AlertTriggerInput" with value "5"
    And I set the parameter "AlertLevelInput" with value "3"
    And I click the "AddThresholdButton" button
    And I set the parameter "MiddleLevelInput" with value "100"
    And I click the "AddThresholdButton" button
    And I set the parameter "HighLevelInput" with value "1000"
    And I click the "AlertNoteTypeTab" button
    And I add a "<Type>" with parameter "<Parameter>"
    And I click the "SaveButton" button
    Then I will see the success message "保存成功"

  Examples:
    |     Type    |  Parameter  |
    |  emailType  |{'title':'auto test alert.','email':['autotest@yottabyte.cn'],'condition':['低'],'content':''}|
    |  emailType  |{'title':'auto test alert.','email':['autotest@yottabyte.cn'],'condition':['中'],'content':''}|
    |  emailType  |{'title':'auto test alert.','email':['autotest@yottabyte.cn'],'condition':['高'],'content':''}|
    |  emailType  |{'title':'auto test alert.','email':['autotest@yottabyte.cn'],'condition':[''],'content':''}|
    |  emailType  |{'title':'auto test alert.','email':['autotest@yottabyte.cn'],'condition':['低','中','高'],'content':''}|


  @alert @all
  Scenario Outline: 创建三个相同告警方式不同发送条件的监控
    Given I click the "CreateAlert" button
    And I will see the "alert.CreatePage" page
    When I set the parameter "AlertName" with value "AutoTest"
    And I choose the "default_Alert" from the "AlertGroups"
    And I choose the "所有日志" from the "AlertSources"
    And I set the parameter "SearchContent" with value "*"
    And I switch the "AlertEnable" button to "disable"
    And I set the parameter "AlertTriggerInput" with value "5"
    And I set the parameter "AlertLevelInput" with value "3"
    And I click the "AddThresholdButton" button
    And I set the parameter "MiddleLevelInput" with value "100"
    And I click the "AddThresholdButton" button
    And I set the parameter "HighLevelInput" with value "1000"
    And I click the "AlertNoteTypeTab" button
    And I add a "<Type>" with parameter "<Parameter1>"
    And I add a "<Type>" with parameter "<Parameter2>"
    And I add a "<Type>" with parameter "<Parameter3>"
    And I click the "SaveButton" button
    Then I will see the success message "保存成功"

  Examples:
    |     Type    |  Parameter1  |  Parameter2  |  Parameter3  |
    |  emailType  |{'title':'auto test alert.','email':['autotest@yottabyte.cn'],'condition':['低'],'content':''}|{'title':'auto test alert.','email':['autotest1@yottabyte.cn'],'condition':['中'],'content':''}|{'title':'auto test alert.','email':['autotest2@yottabyte.cn'],'condition':['高'],'content':''}|
    | forwardType |{'address':'http://192.168.1.82:511111/','condition':['低']}                                  |{'address':'http://192.168.1.82:511111/','condition':['中']}                                   |{'address':'http://192.168.1.82:511111/','condition':['高']}|