Feature: 创建一个资源分组

  Background:
    Given I click the "ResourceGroupsPage" button
    Then I will see the "ResourceGroupsListPage" page
    And I click the "CreateResourceGroup" button
    Then I will see the "ResourceGroupsCreatePage" page


  Scenario Outline: 成功为一个指定角色创建一个指定类型的资源分组
    Given I set the parameter "ResourceGroupName" with value "<Name>"
    And I choose the "<Type>" from the "ResourceGroupType"
    And I set the parameter "ResourceGroupDes" with value "<Des>"
    And I choose the "<Owner>" from the "ResourceGroupOwner"
    And I click the "CreateButton" button
    Then I will see the success message "创建成功"

    Examples:
    |Name|Type|Des|Owner|
    |AutoTest1|仪表盘|Des1|admin|
