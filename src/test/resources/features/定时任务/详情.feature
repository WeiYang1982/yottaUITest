Feature: 编辑定时任务

  Background:
    Given I open the page contains "AutoTest"

  Scenario Outline:
    Given I click the data name contais "<name>" in table "Table"

    Examples:
      | name     |
      | AutoTest |