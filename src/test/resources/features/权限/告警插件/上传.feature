Feature: 上传告警插件

  Background:
    Given I click the "AlertPluginsPage" button
    And I will see the "alertPlugins.ListPage" page

  @smoke @alertPlugins @all
  Scenario: 上传同名告警插件成功
    Given I click the "UploadButton" button
    When I upload a file with name "/src/test/resources/testdata/alertPlugins/hengshuiyinhang_socket.py"
    Then I will see the "VerifyMessage" result will be "存在同名组件"
    And I click the "UploadSubmitButton" button
    And I click the "MessageOKButton" button
    Then I will see the success message "上传成功"

  @all @alertPlugins
  Scenario Outline: 上传告警插件失败
    Given I click the "UploadButton" button
    When I upload a file with name "<InputFileName>"
    Then I will see the error message "<ErrorMessage>"

  Examples:
    |InputFileName                                                        |ErrorMessage|
    |/src/test/resources/testdata/alertPlugins/testAlertPlugins.txt       |仅支持 .py 格式文件|
    |/src/test/resources/testdata/alertPlugins/testAlertPlugins.py        |插件有误，请检查后重试。|
