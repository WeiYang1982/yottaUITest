Feature: 删除知识

  Background:
    Given I click the "KnowledgePage" button
    And I will see the "knowledge.ListPage" page

  @knowledge
  Scenario Outline:删除知识
    Given I delete the data in "TableBody" which "<TableHeader>" is "<Name>"
    Then I will see the success message "<SuccessMsg>"

  @smoke @all
    Examples:
      | TableHeader | Name         | SuccessMsg |
      | 事件代码       | sxjautotest3 | 删除成功       |
      | 分组          | sxj          | 删除成功       |
      | 标签          | 200          | 删除成功       |
