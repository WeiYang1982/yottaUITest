Feature:spl搜索新增定时任务

  Background:
    Given open the "splSearch.SearchPage" page for uri "/search/"

  @smoke @spl @all
  Scenario Outline: 生成表格类型的定时任务
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
    Then I set the parameter "StartTime" with value "<startTime>"
    Then I click the "EnsureButton" button
    Then I click the "Ensure" button
    Then I will see the success message "<message>"

  @smoke @all
    Examples:
      | splQuery                                      | name    | describe | users | groups                | period | startTime | message |
      | tag:"sample04061424" \| top 1 apache.resp_len | sxjAutoTest | autotest | owner | default_SavedSchedule | 1      | 15:36:55  | 保存成功    |

  @all
    Examples:
      | splQuery                                                                                                                          | name    | describe | users | groups                | period | startTime | message                |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts |         |          |       |                       |        |           | 请填写名称！                 |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts | sxjtest | autotest |       |                       |        |           | 请选择分组                  |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts | sxjtest | autotest | owner | default_SavedSchedule |        |           | 定时模式下, 时间间隔不能为零或空      |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts | sxjtest | autotest | owner | default_SavedSchedule | 1.5    |           | 定时模式下, 时间间隔应该为正整数      |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts | stest   | autotest | owner | default_SavedSchedule | 1      |           | 请输入开始时间                |
      | index=schedule schedule_name:bar_resp_len \| bucket timestamp span=1h as ts \| stats max(max_resp_len) as max_resp_len_hour by ts | sxjtest | autotest | owner | default_SavedSchedule | 1      | 15:36:55  | 定时任务已存在\n[错误码: FE_537] |

  @smoke @spl @all
  Scenario Outline: 生成图表类型的定时任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    And I wait element "SearchStatus" change text to "搜索完成!"
    And I click the "Type" button
    And I click the "<groupType>" button
    And I click the "<type>" button
    And I click the "SearchComplete" button
    Then I click the "SaveAsOther" button
    Then I click the "TimedTask" button
    Then I set the parameter "TaskName" with value "<name>"
    Then I set the parameter "Describe" with value "<describe>"
    Then I choose the "<users>" from the "UserComboBox"
    Then I choose the "<groups>" from the "GroupComboBox"
    Then I set the parameter "Period" with value "<period>"
    Then I set the parameter "StartTime" with value "<startTime>"
    Then I click the "EnsureButton" button
    Then I click the "Ensure" button
    Then I will see the success message "保存成功"

    Examples:
      | splQuery                                                                                                                       | groupType  | type       | name               | describe | users | groups                | period | startTime |
      | (tag:heka) \|bucket timestamp timeranges=((2018-07-26:10:39:50, 2018-07-27:10:40:02)) as tr \| stats dc('appname') as ct by tr | Order      | Line       | lineAutoTest       |          | owner | default_SavedSchedule | 10     | 15:36:55  |
      | tag:"sample04061424" \| eval status = apache.status \| stats count() as cnt by status \| eval newField = cnt + status          | Orther     | Wordcloud  | wordcloudAutoTest  |          | owner | default_SavedSchedule | 15     | 15:36:55  |
      | tag:"sample04061424" \| eval status = apache.status \| stats count() as cnt by status \| eval newField = cnt + status          | Orther     | Single     | singleAutoTest     |          | owner | default_SavedSchedule | 15     | 15:36:55  |
      | tag:"sample04061424" \| eval status = apache.status \| stats count() as cnt by status \| eval newField = cnt + status          | Orther     | Liquidfill | liquidfillAutoTest |          | owner | default_SavedSchedule | 15     | 15:36:55  |
      | * \| stats avg(raw_message_length) as avg_length, count(apache.clientip) as ip_count by appname \| sort by ip_count            | Order      | Area       | areaAutoTest       |          | owner | default_SavedSchedule | 15     | 15:36:55  |
      | * \| stats avg(raw_message_length) as avg_length, count(apache.clientip) as ip_count by appname \| sort by ip_count            | Order      | Column     | columnAutoTest     |          | owner | default_SavedSchedule | 15     | 15:36:55  |
      | * \| stats avg(raw_message_length) as avg_length, count(apache.clientip) as ip_count by appname \| sort by ip_count            | Order      | Scatter    | scatterAutoTest    |          | owner | default_SavedSchedule | 15     | 15:36:55  |
      | * \| stats avg(raw_message_length) as avg_length, count(apache.clientip) as ip_count by appname \| sort by ip_count            | Dimension  | Pie        | pieAutoTest        |          | owner | default_SavedSchedule | 15     | 15:36:55  |
      | * \| stats avg(raw_message_length) as avg_length, count(apache.clientip) as ip_count by appname \| sort by ip_count            | Dimension  | Rose       | roseAutoTest       |          | owner | default_SavedSchedule | 15     | 15:36:55  |
      | * \| stats avg(raw_message_length) as avg_length, count(apache.clientip) as ip_count by appname \| sort by ip_count            | Dimension  | Bar        | barAutoTest        |          | owner | default_SavedSchedule | 15     | 15:36:55  |
      | * \| stats avg(raw_message_length) as avg_length, count(apache.clientip) as ip_count by appname \| sort by ip_count            | Compound   | Multiaxis  | multiaxisAutoTest  |          | owner | default_SavedSchedule | 15     | 15:36:55  |
      | * \| stats avg(raw_message_length) as avg_length, count(apache.clientip) as ip_count by appname \| sort by ip_count            | Connection | Chord      | chordAutoTest      |          | owner | default_SavedSchedule | 15     | 15:36:55  |
      | * \| stats avg(raw_message_length) as avg_length, count(apache.clientip) as ip_count by appname \| sort by ip_count            | Connection | Sankey     | sankeyAutoTest     |          | owner | default_SavedSchedule | 15     | 15:36:55  |
      | * \| stats avg(raw_message_length) as avg_length, count(apache.clientip) as ip_count by appname \| sort by ip_count            | Connection | Force      | forceAutoTest      |          | owner | default_SavedSchedule | 15     | 15:36:55  |

  @smoke @spl @all
  Scenario Outline: 生成循序图的定时任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    And I wait element "SearchStatus" change text to "搜索完成!"
    And I click the "Type" button
    And I click the "<groupType>" button
    And I click the "<type>" button
    And I click the "SearchComplete" button
    And I click the "Setting" button
    And I choose the "<timeSequence>" from the "SelectData"
    And I click the "Source" button
    Then I choose the "<source>" from the "SelectData"
    And I click the "Target" button
    Then I choose the "<target>" from the "SelectData"
    And I click the "Cut" button
    Then I choose the "<cut>" from the "SelectData"
    And I click the "Mark" button
    Then I choose the "<mark>" from the "SelectData"
    And I click the "Generate" button
    Then I click the "SaveAsOther" button
    Then I click the "TimedTask" button
    Then I set the parameter "TaskName" with value "<name>"
    Then I set the parameter "Describe" with value "<describe>"
    Then I choose the "<users>" from the "UserComboBox"
    Then I choose the "<groups>" from the "GroupComboBox"
    Then I set the parameter "Period" with value "<period>"
    Then I set the parameter "StartTime" with value "<startTime>"
    Then I click the "EnsureButton" button
    Then I click the "Ensure" button
    Then I will see the success message "保存成功"

    Examples:
      | splQuery                                      | groupType | type     | timeSequence | source          | target   | cut             | mark            | name             | describe | users | groups                | period | startTime |
      | *\| stats count() by hostname,apache.clientip | Other     | Sequence | hostname     | apache.clientip | hostname | apache.clientip | apache.clientip | sequenceAutoTest |          | owner | default_SavedSchedule | 15     | 15:36:55  |

  @smoke @spl @all
  Scenario Outline: 生成力图的定时任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    And I wait element "SearchStatus" change text to "搜索完成!"
    And I click the "Type" button
    And I click the "<groupType>" button
    And I click the "<type>" button
    And I click the "SearchComplete" button
    And I click the "Setting" button
    Then I choose the "<source>" from the "SelectData"
    And I click the "Target" button
    Then I choose the "<target>" from the "SelectData"
    And I click the "Weight" button
    Then I choose the "<weight>" from the "SelectData"
    And I click the "Show" button
    And I click the "StartColour" button
    And I click the "Pink" button
    And I click the "Generate" button
    Then I click the "SaveAsOther" button
    Then I click the "TimedTask" button
    Then I set the parameter "TaskName" with value "<name>"
    Then I set the parameter "Describe" with value "<describe>"
    Then I choose the "<users>" from the "UserComboBox"
    Then I choose the "<groups>" from the "GroupComboBox"
    Then I set the parameter "Period" with value "<period>"
    Then I set the parameter "StartTime" with value "<startTime>"
    Then I click the "EnsureButton" button
    Then I click the "Ensure" button
    Then I will see the success message "保存成功"

    Examples:
      | splQuery                                                                                                            | groupType  | type  | source  | target  | weight   | name          | describe | users | groups                | period | startTime |
      | * \| stats avg(raw_message_length) as avg_length, count(apache.clientip) as ip_count by appname \| sort by ip_count | Connection | Force | appname | appname | ip_count | forceAutoTest |          | owner | default_SavedSchedule | 15     | 15:36:55  |

  @smoke @spl @all
  Scenario Outline: 生成区间图的定时任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    And I wait element "SearchStatus" change text to "搜索完成!"
    And I click the "Type" button
    And I click the "<groupType>" button
    And I click the "<type>" button
    And I click the "SearchComplete" button
    And I click the "Setting" button
    Then I choose the "<xaxis>" from the "SelectData"
    And I click the "Yaxis" button
    Then I choose the "<acturalData>" from the "ActuralData"
    Then I choose the "<predictData>" from the "PredictData"
    Then I choose the "<topLimit>" from the "TopLimit"
    Then I choose the "<lowerLimit>" from the "LowerLimit"
    And I click the "Generate" button
    Then I click the "SaveAsOther" button
    Then I click the "TimedTask" button
    Then I set the parameter "TaskName" with value "<name>"
    Then I set the parameter "Describe" with value "<describe>"
    Then I choose the "<users>" from the "UserComboBox"
    Then I choose the "<groups>" from the "GroupComboBox"
    Then I set the parameter "Period" with value "<period>"
    Then I set the parameter "StartTime" with value "<startTime>"
    Then I click the "EnsureButton" button
    Then I click the "Ensure" button
    Then I will see the success message "保存成功"

    Examples:
      | splQuery                                                                                                                                                                                                                                     | groupType | type      | xaxis | acturalData | predictData | topLimit | lowerLimit | name              | describe | users | groups                | period | startTime |
      | * \| bucket timestamp span=3h as ts\| stats count(appname) as count_ by ts \| movingavg count_,5 as ma \| rollingstd count_,5 as rs\| eval lower=ma-3*rs\| eval upper=ma+3*rs \| eval outlier=if(count_>upper\|\|count_<lower, count_, null) | Compound  | rangeline | ts    | count_      | count_      | upper    | lower      | rangelineAutoTest |          | owner | default_SavedSchedule | 15     | 15:36:55  |
