@topology
Feature: 删除拓扑图

  Background:
    Given I insert into table "Topology" with "{'name':'sxjautotest','domain_id':'1','creator_id':'1','category':'0','group':'default_Topology'}"
    Then open the "topology.ListPage" page for uri "/topology/"

  Scenario Outline:
    Given the data name is "<name>" then i click the "删除" button
    Then I click the "Ensure" button
    Then I will see the success message "删除成功"

  @all @smoke
    Examples:
      | name        |
      | sxjautotest |
