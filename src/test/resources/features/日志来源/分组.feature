@sourceGroups
Feature:

  Background:
    Given I insert into table "SourceGroup" with "{'name':'sxjautotest','domain_id':'1','owner_id':'1','group':'default_SourceGroup'}"
    Then open the "sourceGroup.ListPage" page for uri "/sources/sourcegroups/"

  @all
  Scenario Outline:
    Given the data name is "<name>" then i click the "分组" button
    Then I cancel selection "<group>" from the "GroupInput"
    And I click the "EnsureButton" button
    Then I will see the error message "<errorMsg>"
    Then I click the "Ensure" button
    And I choose the "<group>" from the "GroupInput"
    Then I click the "EnsureButton" button
    Then I will see the success message "<successMsg>"

  @smoke
    Examples:
      | name        | group               | errorMsg                                       | successMsg |
      | sxjautotest | default_SourceGroup | 保存失败: 没有参数, 参数：[resource_group_ids]\n错误码: FE_3 | 保存成功       |