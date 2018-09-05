@sourceGroups
Feature: 删除日志来源

  Background:
    Given I insert into table "SourceGroup" with "{'name':'sxjautotest','domain_id':'1','owner_id':'1','group':'default_SourceGroup'}"
    Then open the "sourceGroup.ListPage" page for uri "/sources/sourcegroups/"

  Scenario Outline:
    Given the data name is "<name>" then i click the "删除" button
    Then I click the "Ensure" button
    Then I will see the success message "删除成功"

  @all @smoke
    Examples:
      | name        |
      | sxjautotest |