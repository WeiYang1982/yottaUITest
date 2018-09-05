@sourceGroups
Feature: 日志来源的展示结构

  Background:
    Given I insert into table "SourceGroup" with "{'name':'autotest','domain_id':'1','owner_id':'1','group':'default_SourceGroup'}"
    Then I insert into table "SourceGroup" with "{'name':'sxjautotest','domain_id':'1','owner_id':'1','tag':'tag','group':'default_SourceGroup'}"
    Then open the "splSearch.SearchPage" page for uri "/search/"

  Scenario Outline: 判断日志来源是否可以点击
    Then I click the "ArrowDown" button
    Then I will see the "<nodeName>" node is "<attribute>"

  @all @smoke
    Examples:
      | nodeName    | attribute |
      | autotest    | disabled  |
      | sxjautotest |           |

