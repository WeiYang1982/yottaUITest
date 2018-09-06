@topology
Feature: 拓扑图分组

  Background:
    Given I insert into table "Topology" with "{'name':'sxjautotest','domain_id':'1','creator_id':'1','category':'0','group':'default_Topology'}"
    Then open the "topology.ListPage" page for uri "/topology/"

  @all
  Scenario Outline:
    Given the data name is "<name>" then i click the "分组" button
    Then I cancel selection "<group>" from the "GroupInput"
    And I click the "EnsureButton" button
    Then I will see the error message "请选择拓扑图分组"
    Then I click the "Ensure" button
    And I choose the "<group>" from the "GroupInput"
    Then I click the "EnsureButton" button
    Then I will see the success message "保存成功"

  @smoke
    Examples:
      | name        | group            |
      | sxjautotest | default_Topology |
