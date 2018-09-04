@topology
Feature: 搜索拓扑图

  Background:
    Given I insert into table "Topology" with "{'name':'sxjautotest','domain_id':'1','creator_id':'1','category':'0','group':'default_Topology'}"
    Given I insert into table "Topology" with "{'name':'未分组','domain_id':'1','creator_id':'1','category':'0'}"
    Then open the "topology.ListPage" page for uri "/topology/"

  Scenario Outline:
    Given I choose the "<group>" from the "GroupDropdownList"
    Then I will see the column number "2" contains "<group>"
    Then I choose the "全部资源" from the "GroupDropdownList"
    Then I set the search input with "<name>"
    Then I will see the column number "1" contains "<name>"

  @all @smoke
    Examples:
      | group            | name |
      | default_Topology | 未分组  |
