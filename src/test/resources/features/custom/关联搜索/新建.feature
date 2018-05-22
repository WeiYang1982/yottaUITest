Feature: 创建一个关联搜索
  Background:
    Given I click the "CustomApplicationPage" button
    And I will see the "customApplication.ListPage" page

  @all @smoke @application
  Scenario:
    Given I click the "CreateButton" button
    And I will see the "customApplication.CreatePage" page
    And I set the parameter "ApplicationName" with value "AutoTest"
    And I set the parameter "ApplicationDes" with value "Des"
    And I click the "AutoSearchSwitch" button
    And I click the "CloseStepFrame" button
    And I wait element "MessageInfo" change text to "确定删除该步骤么？"
    And I click the "MessageBoxOKButton" button
    And I click the "SaveButton" button
    Then I will see the success message "应用保存成功!"


