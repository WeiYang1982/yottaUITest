Feature: 删除知识

  Background:
    Given I insert into table "Knowledge" with "{'name':'sunxj1','code':'sunxj1','creator_id':'1','creator_name':'owner','description':'3','domain_id':'1','solution':'test','group':'default_Knowledge'}"
    And open the "knowledge.ListPage" page for uri "/knowledge/"

  @knowledge
  Scenario Outline:删除知识
    Given the data name is "<Name>" then i click the "删除" button
    Then I click the "EnsureDeleteButton" button
    Then I will see the success message "<SuccessMsg>"

  @smoke @all
    Examples:
      | Name   | SuccessMsg |
      | sunxj1 | 删除成功       |
