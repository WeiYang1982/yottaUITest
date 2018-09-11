@dashboard
Feature: 仪表盘重命名

  Background:
    Given I insert into table "DashBoardGroup" with "{'name':'sxjautotest','domain_id':'1','creator_id':'1','group':'default_DashBoardGroup'}"
    Then open the "dashboard.ListPage" page for uri "/dashboard/"

  @all
  Scenario Outline:
    Given the data name is "<name>" then i click the "重命名" button
    Then I set the parameter "DashBoardName" with value "<newName>"
    Then I click the "EnsureRenameButton" button
    Then I will see the <message>

  @smoke
    Examples: 重命名成功
      | name        | newName  | message                    |
      | sxjautotest | autotest | success message "仪表盘重命名成功" |

    Examples: 重命名失败
      | name        | newName  | message                                |
      | sxjautotest | autotest | error message "仪表盘分组名已存在\n错误码: FE_540" |