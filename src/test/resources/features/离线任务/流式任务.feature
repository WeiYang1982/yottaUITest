@all @offlineTask @smoke
Feature: 流式任务

  Background:
    Given open the "splSearch.SearchPage" page for uri "/search/"

  Scenario Outline: 暂停流式任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "<time>" button
    And I click the "SearchButton" button
    Then I click the "Pause" button
    Then I will see the "StatusText" result will be "<status>"

    Examples:
      | splQuery                        | time      | status |
      | * \| stats avg(apache.resp_len) | WholeTime | 已暂停!   |

  Scenario Outline: 取消流式任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "<time>" button
    And I click the "SearchButton" button
    Then I click the "Cancle" button
    Then take a screenshot

    Examples:
      | splQuery                        | time      |
      | * \| stats avg(apache.resp_len) | WholeTime |

  Scenario Outline: 恢复流式任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "<time>" button
    And I click the "SearchButton" button
    Then I click the "Pause" button
    Then I click the "Recover" button
    Then I will see the "StatusText" result will be "<status>"

    Examples:
      | splQuery                        | time      | status  |
      | * \| stats avg(apache.resp_len) | WholeTime | 正在搜索... |