@all
Feature: 编辑一个告警

  Background:
    Given I click the "AlertsListPage" button
    Then I will see the "alert.ListPage" page
    And There is a "thereIsAnAlert" with "{'alertName':'AutoTest','alertGroup':['default_Alert'],'alertSource':['所有日志'],'alertLevel':['1','10']}"

  Scenario:
