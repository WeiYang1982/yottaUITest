Feature: 输入SPL语句，检查搜索完成后的事件数

  Background:
    Given I click the "LocateSearchPage" button
    And I will see the "SearchPage" page

  @smoke @spl
  Scenario Outline: 直接在搜索框中输入相应的spl语句来进行查询
    Given I set the parameter "SearchInput" with value "<splQuery>"
    And I click the "DateEditor" button
    And I click the "RecentSevenDay" button
    And I click the "SearchButton" button
    And I wait element "SearchStatus" change text to "搜索完成!"
    Then I will see the "SearchResult" result will be "<searchResult>"
    And The expected image "<expectImageName>" will be equal to the actual image

    Examples:
    |splQuery|searchResult|expectImageName|
    |(logtype:apache) \| eval status = apache.status \| eval resp_len =  apache.resp_len \| eval status_add0=apache.status+0 \| eval len_add0=resp_len+0 |208546|--|
#    |(logtype:apache AND tag:"sample03271112") AND [[ appname:apache \| stats es(apache.status) by apache.method \| fields apache.method]]|无搜索结果...|
#    |tag:"sample04061421" \| stats count() by hostname \| lookup user,department /data/rizhiyi/share/host_user.csv on hostname = host      |无搜索结果...|
#    |tag:"sample0406141"\|bucket timestamp span=1m as ts \| stats sum(apache.resp_len) as sum_resp_len by ts \| eval time=formatdate(ts)\| movingavg sum_resp_len,3 as moving_avg_resp_len|查询无结果。|Actual-2017-10-18.png|

#
#  @smoke @spl
#  Scenario Outline: 通过已存搜索来进行查询
#    Given I click the "LoadSavedQueryButton" button
#    And I wait for "SavedQueryWindows" will be visible
#    And I set the parameter "SavedQuerySearch" with value "<savedQueryName>"
#    And I click the "FirstSavedQueryName" button
#    And I wait element "SearchStatus" change text to "搜索完成!"
#    Then I will see the "SearchResult" result will be "<searchResult>"
#
#    Examples:
#    |savedQueryName|searchResult|
#    |alert_yctv|搜索完成! 事件数: 0|
