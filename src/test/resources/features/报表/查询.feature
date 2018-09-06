Feature: 查询报表

  Background:
    Given I insert into table "Report" with "{'name':'自动化测试用例','owner':'1|owner|86bb700c6f5e48b094bbc73dd8f46a6a','domain':'ops','frequency':'day','triggertime':'001130','count':'0','domain_id':'0','crontab':'0','enabled':'1','repoprt_type':'pdf','group':'default_Report'}"
    Then open the "report.ListPage" page for uri "/reports/"

  Scenario Outline: 根据分组查询
    Given I choose the "<group>" from the "GroupList"
    Then I will see the list of "" contains "" or I see the "SearchList" contains "<name>"

  @all
    Examples:
      | group          | name    |
      | default_Report | 自动化测试用例 |

  Scenario Outline: 根据输入内容查询
    Given I set the parameter "SearchIput" with value "<searchInput>"
    Then I will see the list of "" contains "" or I see the "SearchList" contains "<name>"

  @all
    Examples:
      | searchInput | name    |
      | 自动化         | 自动化测试用例 |

