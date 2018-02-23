Feature: 导入一个资源包

  Background:
    Given I click the "ResourceGroupsPage" button
    Then I will see the "resourceGroups.ListPage" page

  Scenario Outline: 导入资源包的正常流程测试
    Given I click the "UploadButton" button
    When I upload a file with name "<InputFileName>"
    And I click the "<ButtonType>" button
    And I choose the "<Owner>" from the "ResourceGroupOwner"
    And I click the "OKButton" button
    Then I will see the success message "导入成功"

  Examples:
    |InputFileName|ButtonType|Owner|
    |C:\Users\A\Desktop\11.tar|BothSaveButton|admin|
    |C:\Users\A\Desktop\11.tar|NotCoverButton|hunter_roles_manager|
    |C:\Users\A\Desktop\11.tar|CoverButton|admin,autotest|
    |C:\Users\A\Desktop\11.tar||autotest|