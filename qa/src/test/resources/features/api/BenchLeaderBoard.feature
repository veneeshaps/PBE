@api
Feature: Bench Leaderboard API Testing

  Scenario: Fetch all profiles from the bench leaderboard API
    Given the API endpoint "LEADERBOARD_BENCH" is available
    When I send a GET request
    Then the response status code should be 200
    And the response content type should be "application/json; charset=utf-8"
    And the response should contain 20 profiles
    And each profile should contain the following fields:
      | rank               |
      | name               |
      | email              |
      | domain             |
      | domainId           |
      | codilityPercentage |
      | techTalkPercentage |
      | genAIPercentage    |
      | totalPercentage    |

  Scenario: Fetch all existing domains
    Given the API endpoint "SCHEDULED_EVENT" is available
    When I send a GET request
    Then the response status code should be 200
    And the response content type should be "application/json; charset=utf-8"
    And the response should contain 5 domains
    And each domain should contain the following fields:
      | domainId   |
      | domainName |

  Scenario: Fetch the current event weightages
    Given the API endpoint "CURRENT_WEIGHTAGES" is available
    When I send a GET request
    Then the response status code should be 200
    And the response content type should be "application/json; charset=utf-8"
    And the response should contain the following fields:
      | eventId     |
      | eventName   |
      | percentage  |
    And the total percentage of all events should be less than or equal to 100

  @positive
  Scenario: Update the event weightages with valid data
    Given the Update Weightages API endpoint "/api/Weightage/update-weightages" is available
    When a POST request is made with valid weightages summing to 100
      | eventId | eventName     | percentage |
      | 1       | Codility      | 60         |
      | 2       | Gen AI Kata   | 20         |
      | 3       | Tech Talk     | 20         |
    Then the response status code should be 200
    And the response body should contain the message "Event weightages updated successfully."

  @negative
  Scenario: Update the event weightages with invalid data
    Given the Update Weightages API endpoint "/api/Weightage/update-weightages" is available
    When a POST request is made with invalid weightages not summing to 100
      | eventId | eventName     | percentage |
      | 1       | Codility      | 50         |
      | 2       | Gen AI Kata   | 30         |
      | 3       | Tech Talk     | 10         |
    Then the response status code should be 400
    And the response body should contain the message "Total weightage must equal 100%"

  Scenario: Download the exported file for a selected domain
    Given the Export API endpoint "/api/Leaderboard/bench/export" is available
    When a GET request is made to the Export API with domainId as query parameter "1"
    Then the response status code should be 200
    And the response content type should be "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    And the response body should not be empty