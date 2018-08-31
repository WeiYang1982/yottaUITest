Feature:复制定时任务

  Background:
    Given I insert into table "SavedSchedule" with "{'name':'sxjAutoTest','domain_id':'1','owner_id':'1','request_method':'GET','vis_type':'STATS_NEW','chart_type':'table','last_run_timestamp':'0','start_trigger_time':'1535614615','check_interval':'1','request':'yField=&owner_name=owner&defaultColor=%2319B8FF&xAxisRotate=&byStacks=&toLongitudeField=&sid=&legendPosition=&fromLongitudeField=&toLatitudeField=&time_range=now/d%2Cnow&yCharts=&ySmooths=&cur_ByField=&outlierField=&query=tag%3A%22sample04061424%22%20%7C%20top%201%20apache.resp_len&filter_field=&fromField=&size=20000&category=events&yFields=&colorValues=&cur_ByFields=&source_group=all&lowerField=&upperField=&legendEllipsis=&page=0&field=&token=1f0d166ab3e82d812fa2ed26aebdb0ac&yRanges=&mapType=&toField=&act=search&weightField=&labelField=&xField=&usetable=true&fromLatitudeField=&order=desc&xAxisSort=&owner_id=1','category':'1','crontab':'0','enabled':'1','group':'default_SavedSchedule'}"
    And open the "timedTask.ListPage" page for uri "/schedule/"

  @timedTask
  Scenario Outline:
    When the data name is "<name>" then i click the "复制" button
    Then I will see the success message "复制成功"

  @all @smoke
    Examples:
      | name        |
      | sxjAutoTest |