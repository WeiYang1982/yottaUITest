Feature:spl搜索新增定时任务

  Background:
    Given I click the "LocateSearchPage" button
    And I will see the "splSearch.SearchPage" page

  @smoke @spl
  Scenario Outline: 根据生成的事件个数进行判断
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    And I wait element "SearchStatus" change text to "搜索完成!"
    Then I click the "SaveAsOther" button
    Then I click the "TimedTask" button
    Then I set the parameter "TaskName" with value "<name>"
    Then I set the parameter "Describe" with value "<describe>"
    Then I choose the "<users>" from the "UserComboBox"
    Then I choose the "<groups>" from the "GroupComboBox"
    Then I set the parameter "Period" with value "<period>"
    Then I set the "StartTime" with "<startTime>"
    Then I click the "Ensure" button
    Then I will see the success message "<message>"

  @smoke @all
    Examples:
      | splQuery                                                                                                                          | name    | describe | users | groups | period | startTime | message |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts | sxjtest | autotest | owner | sunxc2 | 1      | 15:36:55  | 保存成功    |

  @all
    Examples:
      | splQuery                                                                                                                          | name    | describe | users | groups | period | startTime | message              |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts |         |          |       |        |        |           | 请填写名称！               |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts | sxjtest | autotest |       |        |        |           | 请选择分组                |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts | sxjtest | autotest | owner | sunxc2 |        |           | 定时模式下, 时间间隔不能为零或空    |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts | sxjtest | autotest | owner | sunxc2 | 1.5    |           | 定时模式下, 时间间隔应该为正整数    |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts | sxjtest | autotest | owner | sunxc2 | 1      |           | 请输入开始时间              |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts | sxjtest | autotest | owner | sunxc2 | 1      | 15:36:55  | 定时任务已存在[错误码: FE_537] |

