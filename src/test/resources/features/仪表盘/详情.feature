@dashboard @all @smoke
Feature: 仪表盘详情页

  Background:
    Given I delete from "DashBoardGroup" where "{'name':'sxjautotest'}"
    Then I insert into table "DashBoardGroup" with "{'name':'sxjautotest','domain_id':'1','creator_id':'1','group':'default_DashBoardGroup'}"
    Then I insert into table "DashBoardGroup" with "{'name':'autotest','domain_id':'1','creator_id':'1','group':'default_DashBoardGroup'}"
    Then open the "dashboard.ListPage" page for uri "/dashboard/"
    Then I click the detail which name is "sxjautotest"
    Then I will see the "dashboard.DetailPage" page

  Scenario Outline: 检查按钮
    Then I set the parameter "TagName" with value "<tagName>"
    Then I click the "EnsureCreateTagButton" button
    Then I will see the "<button>" is clickable
    Then I click the "ClickableButton" button
    Then I will see the "<disabledButton>" is "disabled"
    Then I click the "ShowFilter" button
    Then I will see the "Filter" is display
    Then I click the "NightModeButton" button
    Then I click the "FullScreenButton" button
    Then take a screenshot

    Examples:
      | tagName | button | disabledButton |
#      | first   | AddButton,MoveButton,RefreshButton,SaveAsReportButton,NightModeButton,FullScreenButton | AddButton,MoveButton,RefreshButton |

  Scenario Outline: 移出标签页
    Then I set the parameter "TagName" with value "<tagName>"
    Then I click the "EnsureCreateTagButton" button
    Then I click the "MoveButton" button
    Then I check "<dashboardName>" from the "CheckBox"
    Then I click the "EnsureMoveTagButton" button
    Then open the "dashboard.ListPage" page for uri "/dashboard/"
    Then I click the detail which name is "<dashboardName>"
    Then I will see the "dashboard.DetailPage" page
    Then I will see the button "Tab" name is "<tagName>"

    Examples:
      | tagName | dashboardName |
#      | first   | autotest      |

  Scenario Outline: 跳转到其他仪表盘
    Then I set the parameter "TagName" with value "<tagName>"
    Then I click the "EnsureCreateTagButton" button
    Then I choose the "<dashboardName>" from the "DropdownList"
    Then the page's title will be "autotest | 仪表盘"

    Examples:
      | tagName | dashboardName |
#      | first   | autotest      |

  Scenario Outline: 标签页操作
    Then I set the parameter "TagName" with value "<tagName>"
    Then I click the "EnsureCreateTagButton" button
    Then I click the "CloseTag" button
    Then I will see the "DropDownLinkButton" result will be "<num>"
    Then I click the "RecoverTag" button
    Then I will see the button "Tab" name is "<tagName>"
    Then I click the "CloseTag" button
    Then I click the "MoveoutTag" button
    Then I check "<dashboardName>" from the "CheckBox"
    Then I click the "EnsureMoveTagButton" button
    Then open the "dashboard.ListPage" page for uri "/dashboard/"
    Then I click the detail which name is "<dashboardName>"
    Then I will see the "dashboard.DetailPage" page
    Then I will see the button "Tab" name is "<tagName>"

    Examples:
      | tagName | num   | dashboardName |
      | first   | [ 1 ] | autotest      |


  Scenario Outline: 标签页删除
    Then I set the parameter "TagName" with value "<tagName>"
    Then I click the "EnsureCreateTagButton" button
    Then I click the "CloseTag" button
    Then I will see the "DropDownLinkButton" result will be "<num>"
    Then I click the "DeleteTag" button
    And I click the "EnsureDeleteTagButton" button
    Then I will see the success message "标签页删除成功"

    Examples:
      | tagName | num   |
      | first   | [ 1 ] |