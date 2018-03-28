Feature: 创建一个用户

  Background:
    Given I click the "UserGroupsPage" button
    And I will see the "userGroups.ListPage" page
    And There is a "thereIsAUserGroup" with "name(String):autoTest;owner(List):admin;role(List):admin"
    And I will see the "PublicNavBarPage" page
    And I click the "UsersPage" button
    Then I will see the "users.ListPage" page
    And There is a "thereIsNoUser" with "name(String):autoTest"

  @usergroups
  Scenario Outline: test1
    Given need run condition "<NeedRun>" There is a "thereIsAUser" with "name(String):11;fullname(String): ;email(String):1@a.com;telephone(String): ;password(String):qqqqq11111;userGroup(List):admin"
    And I click the "CreateUser" button
    And I will see the "users.CreatePage" page
    When I set the parameter "UserName" with value "<UserName>"
    And I set the parameter "FullName" with value "<FullName>"
    And I set the parameter "Email" with value "<Email>"
    And I set the parameter "Telephone" with value "<Telephone>"
    And I set the parameter "Password" with value "<Password>"
    And I choose the "<UserGroups>" from the "UserGroups"
    And I click the "CreateButton" button
    Then I will see the <Result>

  @smoke @all
  Examples:
    |NeedRun|UserName|FullName        |Email                |Telephone  |Password  |UserGroups|Result|
    |N      |autoTest|autoTestFullName|autoTest@yottabyte.cn|13111111111|qqqqq11111|admin     |success message "创建成功"|
    
