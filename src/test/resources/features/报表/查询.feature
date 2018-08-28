Feature: 查询报表

  Background:
    Given I insert into table "Report" which columnName in "name,owner,domain,frequency,lastupdate,triggertime,count,domain_id,crontab,enabled" and values in "自动化测试,1|owner|86bb700c6f5e48b094bbc73dd8f46a6a,ops,day,2018-08-24 15:22:57,001130,0,1,0,1"
    Then open the "report.ListPage" page for uri "/reports/"

  Scenario Outline: 根据分组查询
    Given I choose the "<group>" from the "GroupList"
    Then I will see the list of "" contains "" or I see the "SearchList" contains "<name>"

  @all
    Examples:
      | group | name  |
      | A系统   | 自动化测试 |

  Scenario Outline: 根据输入内容查询
    Given I set the parameter "SearchIput" with value "<searchInput>"
    Then I will see the list of "" contains "" or I see the "SearchList" contains "<name>"

  @all
    Examples:
      | searchInput | name  |
      | 自动化         | 自动化测试 |

