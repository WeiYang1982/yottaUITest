Feature: 删除知识

  Background:
    Given I insert into table "Knowledge" with "{'name':'sunxj1','code':'sunxj1','creator_id':'1','creator_name':'owner','description':'3','domain_id':'1','solution':'test','group':'default_Knowledge'}"
    Then I click the "KnowledgePage" button
    And I will see the "knowledge.ListPage" page

  @knowledge
  Scenario Outline:删除知识
    Given I delete the data in "TableBody" which "<TableHeader>" is "<Name>"
    Then I will see the success message "<SuccessMsg>"

  @smoke @all
    Examples:
      | TableHeader | Name              | SuccessMsg |
      | 名称          | sunxj1            | 删除成功       |
      | 分组          | default_Knowledge | 删除成功       |
