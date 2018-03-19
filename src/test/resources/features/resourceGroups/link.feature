Feature: 资源分组跳转

  Background:
    Given I click the "ResourceGroupsPage" button
    Then I will see the "resourceGroups.ListPage" page

  @all @resourcegroups @smoke
  Scenario Outline:
    Given I choose the "<GroupType>" from the "GroupTypes"
    And I wait table element "SearchResultTable-1.2" change text to "<GroupType>"
    When I click the table "TableLinkButton-1" button
    Then the page's title will be "<PageTitle>"

  Examples:
    |GroupType|PageTitle|
    |仪表盘|列表 \| 仪表盘|
    |告警|告警|
    |定时任务|定时任务|
    |日志来源|日志来源|
    |字典|字典列表|
    |字段提取|字段提取|
    |报表|报表列表|
    |知识|知识|
    |Agent 管理|Agent 管理|
