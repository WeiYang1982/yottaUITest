Feature: 禁用报表

  Background:
    Given I insert into table "Report" with "{'name':'自动化测试用例','owner':'1|owner|86bb700c6f5e48b094bbc73dd8f46a6a','domain':'ops','frequency':'day','triggertime':'001130','count':'0','domain_id':'0','crontab':'0','enabled':'1','repoprt_type':'pdf','group':'default_Report'}"
    Then open the "report.ListPage" page for uri "/reports/"

  Scenario Outline:
    Then I disabled the data "<name>"
    Then I will see the success message "<result>"

  @all
    Examples:
      | name    | result |
      | 自动化测试用例 | 禁用成功   |
      | 自动化测试用例 | 启用成功   |