Feature:

  Background:
    Given I insert into table "Report" which columnName in "name,owner,domain,frequency,lastupdate,triggertime,count,domain_id,crontab,enabled,report_type" and values in "自动化测试,34|AutoTestTmp|86bb700c6f5e48b094bbc73dd8f46a6a,ops,day,2018-08-23 15:10:05,001130,0,1,0,1,pdf"
    Then open the "report.ListPage" page for uri "/reports/"

  @report
  Scenario Outline:
    When the data name is "<name>" then i click the "删除" button
    Then I click the "DeleteEnsure" button
    Then I will see the success message "删除成功"

  @all @smoke
    Examples:
      | name  |
      | 自动化测试 |