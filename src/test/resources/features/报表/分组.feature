Feature: 修改报表分组

  Background:
    Given I insert into table "Report" with "{'name':'自动化测试用例','owner':'1|owner|86bb700c6f5e48b094bbc73dd8f46a6a','domain':'ops','frequency':'day','triggertime':'001130','count':'0','domain_id':'0','crontab':'0','enabled':'1','repoprt_type':'pdf'}"
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