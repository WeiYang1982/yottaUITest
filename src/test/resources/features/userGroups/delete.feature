Feature: 删除一个用户分组

  Background:
    Given I click the "UserGroupsPage" button
    Then I will see the "userGroups.ListPage" page

  @smoke @usergroups @all
  Scenario:
    Given There is a "thereIsAUserGroup" with "name(String):autoTest;owner(List):admin;role(List):admin"
    And I set the parameter "SearchInput" with value "autoTest"
    And I wait table element "SearchResultTable-1.2" change text to "autoTest"
    And I click the table "TableDeleteButton-1" button
    And I click the "MessageBoxOKButton" button

    Then I will see the success message "删除成功"
