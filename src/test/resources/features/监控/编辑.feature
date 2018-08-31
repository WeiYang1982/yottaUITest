@all
Feature: 新建事件数监控并填写告警方式

  Background:
    Given I click the "AlertsListPage" button
    Then I will see the "alert.ListPage" page
    And There is a "thereIsAnAlert" with "{'alertName':'AutoTest','alertGroup':['default_Alert'],'alertSource':['所有日志'],'alertLevel':['1','10']}"

  Scenario:
