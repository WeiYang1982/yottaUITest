@offlineTask @all @smoke
Feature: 下载列表

  Background:
    Given open the "splSearch.OfflineTaskPage" page for uri "/download/"

  Scenario Outline:
    Given the data name is "<name>" then i click the "下载" button
    Then I embedding the file "<name>" into report
    Then the data name is "<name>" then i click the "删除" button

    Examples:
      | name           |
      | autotest.csv   |
      | autotest.json  |
      | autotest1.json |
      | autotest2.json |
      | autotest2.txt  |

