@sourceGroup
Feature: 编辑日志来源

  Background:
    Given I insert into table "SourceGroup" with "{'name':'sxjautotest','domain_id':'1','owner_id':'1','group':'default_SourceGroup'}"
    Then open the "sourceGroup.ListPage" page for uri "/sources/sourcegroups/"

  Scenario Outline:
    Given the data name is "sxjautotest" then i click the "编辑" button
    Then I will see the "sourceGroup.CreatePage" page
    Then I set the parameter "Name" with value "<name>"
    Then I set the parameter "Describe" with value "<describe>"
    Then I choose the "<sourceGroup>" from the "SourceGroup"
    Then I set the parameter "Hostname" with value "<hostname>"
    Then I set the parameter "Appname" with value "<appname>"
    Then I set the parameter "Tag" with value "<tag>"
    Then I set the parameter "Spl" with value "<spl>"
    Then I click the "EnsureCreateButton" button
    Then I will see the success message "<message>"

  @smoke
    Examples: 编辑成功
      | name          | describe | sourceGroup         | hostname | appname | tag     | spl                               | message |
      | sunxjautotest | change   | default_SourceGroup | 192*     | apache* | apache* | tag:apache* AND NOT logtype:other | 更新成功    |

    Examples: 编辑失败
      | name          | describe | sourceGroup         | hostname | appname | tag | spl | message               |
      |               |          |                     |          |         |     |     | 名称 不能为空               |
      | sunxjautotest |          | default_SourceGroup |          |         |     |     | 日志分组名已存在\n错误码: FE_545 |