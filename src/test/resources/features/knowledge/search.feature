Feature: 切换资源分组
  Background: 
    Given I click the "KnowledgePage" button
    And I will see the "knowledge.ListPage" page
    
  Scenario Outline:
    Then I choose the "<GroupList>" from the "GroupList"
    And I set the parameter "SearchInput" with value "<Search>"
    Then I will see the list of "<TableHeader>" contains "<GroupList>" or I see the "SearchList" contains "<Search>"

  Examples:
    |GroupList|Search|TableHeader|
    |sunxj_knowledge||分组         |
    |11             ||分组         |
    |               |2|          |

