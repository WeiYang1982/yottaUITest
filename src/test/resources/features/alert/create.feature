Feature: 新建告警页面的各个检查项
  Background:
    Given I click the "AlertsListPage" button
    Then I will see the "alert.AlertsListPage" page

  @alert
  Scenario Outline: 创建一个新的告警
    Given I click the "CreateAlert" button
    And I will see the "alert.AlertsCreatePage" page
    And I set the parameter "AlertName" with value "<alertName>"
    And I set the parameter "AlertDes" with value "<alertDes>"
    And I choose the "<alertGroup>" from the "AlertGroups"
    And I choose the "<alertUser>" from the "AlertUsers"
    And I choose the "<alertSource>" from the "AlertSources"
    And I set the parameter "SearchContent" with value "<searchContent>"
    And I click the "AlertEnable" button
    And I choose the "<alertType>" from the "AlertTypes"
    And I click the "<string>" button


  Examples:
    |alertName|alertDes|alertGroup   |alertUser|alertSource|searchContent|alertType   |
    |AutoTest |alertDes|default_Alert|owner    |所有日志    |*            |Spl统计监控  |
