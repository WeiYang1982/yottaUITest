Feature:查询定时任务

  Background:
    Given I insert into table "SavedSchedule" with "{'name':'sxjAutoTest','domain_id':'1','owner_id':'1','request_method':'GET','vis_type':'STATS_NEW','chart_type':'table','last_run_timestamp':'0','start_trigger_time':'1535614615','check_interval':'1','request':'yField=&owner_name=owner&defaultColor=%2319B8FF&xAxisRotate=&byStacks=&toLongitudeField=&sid=&legendPosition=&fromLongitudeField=&toLatitudeField=&time_range=now/d%2Cnow&yCharts=&ySmooths=&cur_ByField=&outlierField=&query=tag%3A%22sample04061424%22%20%7C%20top%201%20apache.resp_len&filter_field=&fromField=&size=20000&category=events&yFields=&colorValues=&cur_ByFields=&source_group=all&lowerField=&upperField=&legendEllipsis=&page=0&field=&token=1f0d166ab3e82d812fa2ed26aebdb0ac&yRanges=&mapType=&toField=&act=search&weightField=&labelField=&xField=&usetable=true&fromLatitudeField=&order=desc&xAxisSort=&owner_id=1','category':'1','crontab':'0','enabled':'1','group':'default_SavedSchedule'}"
    And open the "timedTask.ListPage" page for uri "/schedule/"

  @timedTask
  Scenario Outline: 根据定时任务分组进行查询
    Given I choose the "<group>" from the "GroupList"
    Then I will see the list of "" contains "" or I see the "Names" contains "<name>"

  @all @smoke
    Examples:
      | group                 | name        |
      | default_SavedSchedule | sxjAutoTest |

  @timedTask
  Scenario Outline: 根据搜索内容进行查询
    Given I set the parameter "SearchInput" with value "<inputName>"
    Then I will see the list of "" contains "" or I see the "Names" contains "<inputName>"

  @all @smoke
    Examples:
      | inputName |
      | sxj       |
