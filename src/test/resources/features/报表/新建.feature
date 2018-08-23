Feature: 新建报表

  Background:
    Given I delete from "Report" where "name" in "sxjautotest,自动化测试"
    And I insert into table "Report" which columnName in "name,owner,domain,frequency,lastupdate,triggertime,count,domain_id,crontab,enabled,report_type" and values in "自动化测试用例,34|AutoTestTmp|86bb700c6f5e48b094bbc73dd8f46a6a,ops,day,2018-08-23 15:10:05,001130,0,1,0,1,pdf"
    And open the "report.ListPage" page for uri "/reports/"

  @report
  Scenario Outline:
    Given I click the "CreateButton" button
    Then I will see the "report.CreatePage" page
    And I set the parameter "Name" with value "<name>"
    And I set the parameter "Describe" with value "<describe>"
    And I choose the "<runningUser>" from the "RunningUser"
    And I choose the "<reportGroup>" from the "ReportGroup"
    And I choose the "<reportType>" from the "ReportType"
    And I choose the "<email>" from the "Email"
    And I set the parameter "Subject" with value "<subject>"
    And I set the parameter "Hour" with value "<hour>"
    And I set the parameter "Minute" with value "<minute>"
    Then I click the "NextButton" button
    Then I choose the "<chartLists>" from the "ChartList"
    Then I click the "<layout>" button
    Then I click the "Save" button
    Then I will see the success message "<result>"

  @all @smoke
    Examples: 保存成功
      | name        | describe | runningUser | reportGroup    | reportType | email               | subject   | hour | minute | chartLists                        | layout  | result |
#      | sxjautotest | autotest | AutoTestTmp | default_Report | PDF        | 15194315230@163.com | hello sxj | 11   | 30     | bin-zft,bin-zft-spl,bin-zft-local | Layout1 | 保存成功   |
#      | 自动化测试       |          | AutoTestTmp | default_Report | PDF        |                     | 我是自动化用例   | 11   | 30     | bin-zft,bin-zft-spl,bin-zft-local | Layout1 | 保存成功   |

  @all
    Examples: 保存失败
      | name    | describe | runningUser | reportGroup    | reportType | email | subject | hour | minute | chartLists | layout  | result              |
#      | 自动化测试   |          | AutoTestTmp | default_Report | PDF        |       | 我是自动化用例 | 11   | 30     |            | Layout1 | 报表内容 不能为空           |
      | 自动化测试用例 |          | AutoTestTmp | default_Report | PDF        |       | 我是自动化用例 | 11   | 30     | bin-zft    | Layout1 | 报表名称已存在 错误码: FE_580 |

  @report
  Scenario Outline:
    Given I click the "CreateButton" button
    Then I will see the "report.CreatePage" page
    And I set the parameter "Name" with value "<name>"
    And I set the parameter "Describe" with value "<describe>"
    And I choose the "<runningUser>" from the "RunningUser"
    And I choose the "<reportGroup>" from the "ReportGroup"
    And I choose the "<reportType>" from the "ReportType"
    And I choose the "<email>" from the "Email"
    And I set the parameter "Subject" with value "<subject>"
    And I set the parameter "Hour" with value "<hour>"
    And I set the parameter "Minute" with value "<minute>"
    Then I click the "NextButton" button
    Then I will see the success message "<result>"

  @all
    Examples: 执行下一步失败
      | name | describe | runningUser | reportGroup    | reportType | email | subject | hour | minute | result    |
      |      |          |             |                |            |       |         |      |        | 名称 不能为空   |
      | test |          |             |                |            |       |         |      |        | 报表分组 不能为空 |
      | test |          |             | default_Report |            |       |         |      |        | 邮件主题 不能为空 |
      | test |          |             | default_Report |            |       | test    |      |        | 请输入小时     |
      | test |          |             | default_Report |            |       | test    | 11   |        | 请输入分钟     |
