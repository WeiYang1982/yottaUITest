Feature: 修改报表分组

  Background:
    Given I insert into table "Report" which columnName in "name,owner,domain,frequency,lastupdate,triggertime,count,domain_id,crontab,enabled,report_type" and values in "自动化测试用例,34|AutoTestTmp|86bb700c6f5e48b094bbc73dd8f46a6a,ops,day,2018-08-23 15:10:05,001130,0,1,0,1,pdf"
    And open the "report.ListPage" page for uri "/reports/"

  @report
  Scenario Outline:
    When the data name is "<name>" then i click the "分组" button
    Then I choose the "<group>" from the "ChangeGroup"
    And I click the "Ensure" button
    And I will see the success message "保存成功"

  @all @smoke
    Examples:
      | name    | group          |
      | 自动化测试用例 | hunter_roles_m |