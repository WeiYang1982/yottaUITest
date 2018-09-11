@smoke @spl @all
Feature: 存为报表

  Background:
    Given open the "splSearch.SearchPage" page for uri "/search/"

  Scenario Outline: 实时窗口离线任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "ThirtySeconds" button
    And I click the "SearchButton" button
    Then I click the "SaveAsReport" button
    Then I click the "OfflineTask" button
    Then I will see the success message "实时窗口搜索模式无法进行该操作!"

    Examples:
      | splQuery |
#      | * \| transaction apache.status maxspan=1s |

  Scenario Outline: 新建离线任务失败
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "WholeTime" button
    And I click the "SearchButton" button
    Then I click the "SaveAsReport" button
    Then I click the "OfflineTask" button
    Then I click the "EnsureCreateOfflineTask" button
    Then I will see the success message "<message>"

    Examples:
      | splQuery | message |
#      | * \| transaction apache.status maxspan=1s | 请填写名称！  |

  Scenario Outline: 新建离线任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "WholeTime" button
    And I click the "SearchButton" button
    Then I click the "SaveAsReport" button
    Then I click the "OfflineTask" button
    Then I set the parameter "OfflineTaskName" with value "<name>"
    Then I click the "EnsureCreateOfflineTask" button
    Then open the "splSearch.OfflineTaskPage" page for uri "/offlinetask/"
    Then I will see the "FirstData" result will be "<name>"

    Examples:
      | splQuery                                  | name            |
      | * \| transaction apache.status maxspan=1s | offlineAutoTest |
