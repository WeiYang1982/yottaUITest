@sourceGroups
Feature: 新建日志来源

  Background:
    Given I delete from "SourceGroup" where "{'name':'sxjautotest'}"
    And I insert into table "SourceGroup" with "{'name':'autotest','domain_id':'1','owner_id':'1','group':'default_SourceGroup'}"
    Then open the "sourceGroup.ListPage" page for uri "/sources/sourcegroups/"

  @all
  Scenario Outline:
    Then I click the "CreateButton" button
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
    Examples: 新建成功
      | name        | describe | sourceGroup         | hostname | appname | tag     | spl                               | message |
      | sxjautotest | autotest | default_SourceGroup | 192*     | apache* | apache* | tag:apache* AND NOT logtype:other | 创建成功    |

    Examples: 新建失败
      | name     | describe | sourceGroup         | hostname | appname | tag | spl | message               |
      |          |          |                     |          |         |     |     | 名称 不能为空               |
      | test     |          |                     |          |         |     |     | 来源分组 不能为空             |
      | autotest |          | default_SourceGroup |          |         |     |     | 日志分组名已存在\n错误码: FE_545 |