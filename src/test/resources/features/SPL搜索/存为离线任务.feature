@offlineTask @all
Feature: 存为离线任务

  Background:
    Given open the "splSearch.SearchPage" page for uri "/search/"

  @smoke
  Scenario Outline: 实时窗口离线任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "ThirtySeconds" button
    And I click the "SearchButton" button
    Then I click the "SaveAsReport" button
    Then I click the "OfflineTask" button
    Then I will see the success message "实时窗口搜索模式无法进行该操作!"

    Examples:
      | splQuery                                  |
      | * \| transaction apache.status maxspan=1s |

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
      | splQuery                                  | message |
      | * \| transaction apache.status maxspan=1s | 请填写名称！  |

  @smoke
  Scenario Outline: 新建离线任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "<time>" button
    And I click the "SearchButton" button
    Then I click the "SaveAsReport" button
    Then I click the "OfflineTask" button
    Then I set the parameter "OfflineTaskName" with value "<name>"
    Then I click the "EnsureCreateOfflineTask" button
    Then open the "splSearch.OfflineTaskPage" page for uri "/offlinetask/"
    Then I will see the "FirstData" result will be "<name>"

    Examples:
      | splQuery                                                                                                                                                                                                               | time           | name                       |
      | * \| transaction apache.status maxspan=1s                                                                                                                                                                              | WholeTime      | offlineAutoTest            |
      | * \| transaction apache.status maxspan=1s                                                                                                                                                                              | RecentSevenDay | transactionofflineAutoTest |
      | * \| transaction apache.status maxspan=1s                                                                                                                                                                              | ThisMonth      | transactionofflineAutoTest |
      | apache.status:>=405                                                                                                                                                                                                    | WholeTime      | offlineAutoTest1           |
      | * \| stats count(apache.resp_len) as count_len, max(apache.resp_len) as max_len, min(apache.resp_len) as min_len, sum(apache.status) as sum_len, avg(apache.resp_len) as avg_len by apache.geo.city \| sort by max_len | WholeTime      | offlineAutoTest2           |

  @smoke
  Scenario Outline: 新建最近类型的离线任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "<time>" button
    Then I set the parameter "TimeInput" with value "<beginTime>"
    And I choose the "天前" from the "DaysDropDown"
    Then I click the "ApplyButton" button
    And I click the "SearchButton" button
    Then I click the "SaveAsReport" button
    Then I click the "OfflineTask" button
    Then I set the parameter "OfflineTaskName" with value "<name>"
    Then I click the "EnsureCreateOfflineTask" button
    Then open the "splSearch.OfflineTaskPage" page for uri "/offlinetask/"
    Then I will see the "FirstData" result will be "<name>"

    Examples:
      | splQuery                                  | time                | beginTime | name            |
      | * \| transaction apache.status maxspan=1s | RecentlyRadioButton | 1000      | offlineAutoTest |

  @smoke
  Scenario Outline: 新建实时类型的离线任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "<time>" button
    Then I set the parameter "TimeInput" with value "<beginTime>"
    Then I click the "ApplyButton" button
    And I click the "SearchButton" button
    Then I click the "SaveAsReport" button
    Then I click the "OfflineTask" button
    Then I will see the success message "实时窗口搜索模式无法进行该操作!"

    Examples:
      | splQuery                                  | time           | beginTime |
      | * \| transaction apache.status maxspan=1s | RealTimeButton | 1000      |

  @smoke
  Scenario Outline: 新建自定义时间类型的离线任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I trigger the button "CustomTime"
    And I click the "SearchButton" button
    Then take a screenshot
    Then I click the "SaveAsReport" button
    Then I click the "OfflineTask" button
    Then I set the parameter "OfflineTaskName" with value "<name>"
    Then I click the "EnsureCreateOfflineTask" button
    Then open the "splSearch.OfflineTaskPage" page for uri "/offlinetask/"
    Then I will see the "FirstData" result will be "<name>"
    Then the data name is "<name>" then i click the "暂停" button

    Examples:
      | splQuery                                  | name     |
      | * \| transaction apache.status maxspan=1s | autotest |

