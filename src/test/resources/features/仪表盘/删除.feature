@dashboard @all @smoke
Feature: 仪表盘删除

  Background:
    Given I insert into table "DashBoardGroup" with "{'name':'sxjautotest','domain_id':'1','creator_id':'1','group':'default_DashBoardGroup'}"
    Then open the "dashboard.ListPage" page for uri "/dashboard/"

  Scenario Outline:
    Given the data name is "<name>" then i click the "删除" button
    Then I click the "EnsureDeleteButton" button
    Then I will see the success message "仪表盘删除成功"

    Examples:
      | name        |
      | sxjautotest |

