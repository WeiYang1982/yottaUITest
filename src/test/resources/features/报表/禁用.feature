Feature: 禁用报表

  Background:
    Given I insert into table "Report" which columnName in "name,owner,domain,frequency,lastupdate,triggertime,count,domain_id,crontab,enabled,report_type" and values in "自动化测试用例,1|owner|86bb700c6f5e48b094bbc73dd8f46a6a,ops,day,2018-08-23 15:10:05,001130,0,1,0,1,pdf"
    Then open the "report.ListPage" page for uri "/reports/"

  Scenario Outline:
    Then I disabled the data "<name>"
    Then I will see the success message "<result>"

  @all
    Examples:
      | name    | result |
      | 自动化测试用例 | 禁用成功   |