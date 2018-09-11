@dashboard @all @smoke
Feature: 仪表盘搜索

  Background:
    Given I insert into table "DashBoardGroup" with "{'name':'sxjautotest','domain_id':'1','creator_id':'1','group':'default_DashBoardGroup'}"
    Then open the "dashboard.ListPage" page for uri "/dashboard/"

  Scenario Outline:
    Given search "<searchData>" and I will see the column number "<columnNum>" contains "<name>"

    Examples:
      | searchData                         | columnNum | name                   |
      | {'group':'default_DashBoardGroup'} | 2         | default_DashBoardGroup |
      | {'input':'sxjautotest'}            | 1         | sxjautotest            |