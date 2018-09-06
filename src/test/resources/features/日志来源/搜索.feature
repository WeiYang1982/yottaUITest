@sourceGroups
Feature: 搜索日志来源

  Background:
    Given I insert into table "SourceGroup" with "{'name':'sxjautotest','domain_id':'1','owner_id':'1','group':'default_SourceGroup'}"
    Then open the "sourceGroup.ListPage" page for uri "/sources/sourcegroups/"

  Scenario Outline:
    Given I choose the "<group>" from the "GroupDropdownList"
    Then I will see the column number "1" contains "<name>"
    Then I choose the "全部资源" from the "GroupDropdownList"
    Then I set the search input with "<name>"
    Then I will see the column number "1" contains "<name>"

  @all @smoke
    Examples:
      | group            | name |
      | default_SourceGroup | sxjautotest  |

