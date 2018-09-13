@offlineTask @all @smoke
Feature: 离线任务列表

  Background:
    Given open the "splSearch.SearchPage" page for uri "/search/"

  Scenario Outline: 暂停离线任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "<time>" button
    And I click the "SearchButton" button
    Then I click the "SaveAsReport" button
    Then I click the "OfflineTask" button
    Then I set the parameter "OfflineTaskName" with value "<name>"
    Then I click the "EnsureCreateOfflineTask" button
    Then open the "splSearch.OfflineTaskPage" page for uri "/offlinetask/"
    Then the data name is "<name>" then i click the "暂停" button
    Then I will see the error message "暂停成功"

    Examples:
      | splQuery                                  | time      | name            |
      | * \| transaction apache.status maxspan=1s | WholeTime | offlineAutoTest |

  Scenario Outline: 恢复离线任务
    Then open the "splSearch.OfflineTaskPage" page for uri "/offlinetask/"
    Then the data name is "<name>" then i click the "恢复" button
    Then I will see the error message "恢复成功"
    Then I click the "EnsureButton" button
    Then the data name is "<name>" then i click the "暂停" button

    Examples:
      | name            |
      | offlineAutoTest |

  Scenario: 取消离线任务
    Then open the "splSearch.OfflineTaskPage" page for uri "/offlinetask/"
    Then the data name is "offlineAutoTest" then i click the "取消" button
    Then I will see the error message "此操作将中断 [offlineAutoTest], 是否继续?"
    Then I click the "EnsureButton" button

  Scenario: 加载离线任务
    Then open the "splSearch.OfflineTaskPage" page for uri "/offlinetask/"
    Then the data name is "offlineAutoTest" then i click the "加载" button
    Then switch to another window
    Then take a screenshot

  Scenario: 删除已中断的离线任务
    Then open the "splSearch.OfflineTaskPage" page for uri "/offlinetask/"
    Then the data name is "offlineAutoTest" then i click the "删除" button
    Then I click the "EnsureButton" button
    Then I will see the success message "删除成功"

  Scenario: 删除完成的离线任务
    Then open the "splSearch.OfflineTaskPage" page for uri "/offlinetask/"
    Then the data name is "autotest" then i click the "删除" button
    Then I click the "EnsureButton" button
    Then I will see the success message "删除成功"

  Scenario Outline: 验证正在运行中的任务无删除按钮以及删除处于暂停状态的数据
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "<time>" button
    And I click the "SearchButton" button
    Then I click the "SaveAsReport" button
    Then I click the "OfflineTask" button
    Then I set the parameter "OfflineTaskName" with value "<name>"
    Then I click the "EnsureCreateOfflineTask" button
    Then open the "splSearch.OfflineTaskPage" page for uri "/offlinetask/"
    Then I will see the "DeleteButton" is not exist
    Then the data name is "<name>" then i click the "暂停" button
    Then I click the "EnsureButton" button
    Then the data name is "<name>" then i click the "删除" button
    Then I click the "EnsureButton" button
    Then I will see the success message "删除成功"

    Examples:
      | splQuery                                  | time      | name            |
      | * \| transaction apache.status maxspan=1s | WholeTime | offlineAutoTest |


  Scenario: 删除失败的任务
    Given open the "splSearch.OfflineTaskPage" page for uri "/offlinetask/"
    Then the data name is "autotest" then i click the "恢复" button
    Then the data name is "autotest" then i click the "删除" button
    Then I click the "EnsureButton" button
    Then I will see the success message "删除成功"