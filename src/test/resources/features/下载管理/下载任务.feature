@offlineTask @all
Feature: 下载任务

  Background:
    Given open the "splSearch.SearchPage" page for uri "/search/"

  Scenario Outline: 新建下载任务
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    And I wait element "SearchStatus" change text to "搜索完成!"
    Then I click the "<downloadButton>" button
    Then I set the parameter "DownloadName" with value "<name>"
    Then I set the parameter "MaxLineNum" with value "<maxLineNum>"
    Then I choose the "<unit>" from the "MaxLineDropdown"
    Then I choose the "<type>" from the "DocumentType"
    Then I choose the "<encode>" from the "DocumentEncode"
    Then I click the "CreateDownloadTask" button
    Then I will see the success message "<message>"

  @smoke
    Examples: 新建成功
      | splQuery                                                                                                                                              | downloadButton | name      | maxLineNum | unit | type | encode | message                |
      | * \| stats extend_stat(apache.resp_len), count(apache.resp_len), max(apache.resp_len), min(apache.resp_len), sum(apache.status), avg(apache.resp_len) | Download       | autotest  | 1          |      | CSV  |        | 提交成功，请到设置-下载管理页查看下载状态！ |
      | * \| stats extend_stat(apache.resp_len), count(apache.resp_len), max(apache.resp_len), min(apache.resp_len), sum(apache.status), avg(apache.resp_len) | Download       | autotest  | 1          | GB   | JSON |        | 提交成功，请到设置-下载管理页查看下载状态！ |
      | * \| transaction apache.status maxspan=1s                                                                                                             | DownloadEvent  | autotest1 | 1          | MB   | JSON |        | 提交成功，请到设置-下载管理页查看下载状态！ |
      | starttime="now-1M/M-1d/w" endtime="now" tag:sample04061424                                                                                            | DownloadEvent  | autotest2 | 1          | KB   | JSON |        | 提交成功，请到设置-下载管理页查看下载状态！ |
      | starttime="now-1M/M-1d/w" endtime="now" tag:sample04061424                                                                                            | DownloadEvent  | autotest2 | 1          |      | TXT  |        | 提交成功，请到设置-下载管理页查看下载状态！ |

    Examples: 新建失败
      | splQuery                                                                                                                                              | downloadButton | name                                                                                                                             | maxLineNum | unit | type | encode | message                                            |
      | * \| stats extend_stat(apache.resp_len), count(apache.resp_len), max(apache.resp_len), min(apache.resp_len), sum(apache.status), avg(apache.resp_len) | Download       |                                                                                                                                  |            |      |      |        | 请输入文件名称                                            |
      | * \| stats extend_stat(apache.resp_len), count(apache.resp_len), max(apache.resp_len), min(apache.resp_len), sum(apache.status), avg(apache.resp_len) | Download       | autotest                                                                                                                         | 1          |      | CSV  |        | 下载任务出错: Duplicate file name: autotest\n错误码: SS_700 |
      | * \| stats extend_stat(apache.resp_len), count(apache.resp_len), max(apache.resp_len), min(apache.resp_len), sum(apache.status), avg(apache.resp_len) | Download       | autotest4                                                                                                                        | 4          | GB   | CSV  |        | 下载任务出错: Exceed the maximum size\n错误码: SS_700       |
      | * \| stats extend_stat(apache.resp_len), count(apache.resp_len), max(apache.resp_len), min(apache.resp_len), sum(apache.status), avg(apache.resp_len) | Download       | autotest5                                                                                                                        |            |      |      |        | 请输入文件类型                                            |
      | * \| stats extend_stat(apache.resp_len), count(apache.resp_len), max(apache.resp_len), min(apache.resp_len), sum(apache.status), avg(apache.resp_len) | Download       | ￥#@1                                                                                                                             |            |      | CSV  |        | 名称格式有误，仅支持中文，数字，字母，中划线以及下划线的组合。                    |
      | * \| stats extend_stat(apache.resp_len), count(apache.resp_len), max(apache.resp_len), min(apache.resp_len), sum(apache.status), avg(apache.resp_len) | Download       | 撒打发打发时发生的发顺丰阿的斯顿发反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复方法撒放滴滴答答滴滴答答滴滴答答的地方 |            |      | CSV  |        | 名称太长，请输入小于254个字符的名称。                               |