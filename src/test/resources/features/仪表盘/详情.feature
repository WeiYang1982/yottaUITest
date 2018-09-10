@dashboard @all
Feature: 仪表盘详情页

  Background:
    Given I delete from "DashBoardGroup" where "{'name':'sxjautotest'}"
    Then I insert into table "DashBoardGroup" with "{'name':'sxjautotest','domain_id':'1','creator_id':'1','group':'default_DashBoardGroup'}"
    Then I insert into table "DashBoardGroup" with "{'name':'autotest','domain_id':'1','creator_id':'1','group':'default_DashBoardGroup'}"
    Then open the "dashboard.ListPage" page for uri "/dashboard/"
    Then I click the detail which name is "sxjautotest"
    Then I will see the "dashboard.DetailPage" page

  @smoke
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
      | first   | AddButton,MoveButton,RefreshButton,SaveAsReportButton,NightModeButton,FullScreenButton | AddButton,MoveButton,RefreshButton |

  @smoke
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
      | first   | autotest      |

  @smoke
  Scenario Outline: 跳转到其他仪表盘
    Then I set the parameter "TagName" with value "<tagName>"
    Then I click the "EnsureCreateTagButton" button
    Then I choose the "<dashboardName>" from the "DropdownList"
    Then the page's title will be "autotest | 仪表盘"

    Examples:
      | tagName | dashboardName |
      | first   | autotest      |

  @smoke
  Scenario Outline: 回收站操作
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
      | tagName | num | dashboardName |
      | first   | [ 1 ] | autotest      |


  @smoke
  Scenario Outline: 标签页删除
    Then I set the parameter "TagName" with value "<tagName>"
    Then I click the "EnsureCreateTagButton" button
    Then I click the "CloseTag" button
    Then I will see the "DropDownLinkButton" result will be "<num>"
    Then I click the "DeleteTag" button
    And I click the "EnsureDeleteTagButton" button
    Then I will see the success message "当前仪表盘无开启状态标签页, 请新建或者恢复关闭状态标签页"

    Examples:
      | tagName | num |
      | first   | [ 1 ] |

  Scenario Outline: 新建标签页失败
    Then I set the parameter "TagName" with value "<tagName>"
    Then I click the "EnsureCreateTagButton" button
    Then I will see the success message "<message>"

    Examples:
      | tagName | message |
      |         | 标签页名称不能为空                   |
      | t e s t | 名称格式有误, 仅支持汉字，数字，字母，中划线及下划线 |

  Scenario Outline: 添加过滤项以及输入项失败
    Then I set the parameter "TagName" with value "<tagName>"
    Then I click the "EnsureCreateTagButton" button
    Then I click the "AddEventButton" button
    Then I choose the "<eventList>" from the "EventList"
    Then I set the parameter "<titleName>" with value "<title>"
    Then I click the "<ensure>" button
    Then I will see the success message "<message>"

    Examples:
      | tagName | eventList | titleName   | title | ensure             | message     |
      | first   | 添加过滤项     | FilterTitle |       | EnsureCreateFilter | 请输入过滤项标题    |
      | first   | 添加输入项     | InputTitle  |       | EnsureCreateInput  | 请输入输入项标题    |
      | first   | 添加过滤项     | FilterTitle | test  | EnsureCreateFilter | 请输入过滤项标识    |
      | first   | 添加输入项     | InputTitle  | test  | EnsureCreateInput  | 请输入输入项token |

  @all
  Scenario Outline: 添加下拉菜单类型的过滤项
    Then I set the parameter "TagName" with value "<tagName>"
    Then I click the "EnsureCreateTagButton" button
    Then I click the "AddEventButton" button
    Then I choose the "<eventList>" from the "EventList"
    Then I set the parameter "FilterTitle" with value "<title>"
    Then I set the parameter "FilterToken" with value "<token>"
    Then I set the parameter "FilterField" with value "<field>"
    Then I choose the "<inputType>" from the "InputType"
    Then I set the parameter "ChoiceValue" with value "<choiceValue>"
    Then I click the "AddChoiceValueButton" button
    Then I choose the "<choiceValue>" from the "DefaultDropdownList"
    Then I click the "EnsureCreateFilter" button
    Then take a screenshot

    Examples:
      | tagName | eventList | title | token | field | inputType | choiceValue |
      | first   | 添加过滤项     | test  | token |       | 下拉菜单      | default_value |

  @all
  Scenario Outline: 添加动态菜单类型的过滤项
    Then I set the parameter "TagName" with value "<tagName>"
    Then I click the "EnsureCreateTagButton" button
    Then I click the "AddEventButton" button
    Then I choose the "<eventList>" from the "EventList"
    Then I set the parameter "FilterTitle" with value "<title>"
    Then I set the parameter "FilterToken" with value "<token>"
    Then I set the parameter "FilterField" with value "<field>"
    Then I choose the "<inputType>" from the "InputType"
    Then I set the parameter "DynamicField" with value "<dynamicField>"
    Then I set the parameter "SearchInput" with value "<search>"
    Then I click the "TimeRange" button
    Then I click the "ThisMonth" button
    Then I click the "SearchInputButton" button
    Then I will see the success message "动态字段搜索完成"
    Then I choose the "<choiceValue>" from the "DefaultDropdownList"
    Then I click the "EnsureCreateFilter" button
    Then take a screenshot

    Examples:
      | tagName | eventList | title | token | field | inputType | dynamicField | search | choiceValue |
      | first   | 添加过滤项     | test  | token |       | 动态菜单      | appname      | * \| stats count() by appname | java        |


