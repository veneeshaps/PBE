@api @dashboard
Feature: Validate the Dashboard APIs

  # Positive scenario: Analytics API - Valid response with event data
  @positive @smoke
  Scenario: Validate the Dashboard Analytics API with valid response data
    Given I use the API endpoint for "DASHBOARD_ANALYTICS"
    When I send a GET request
    Then the response status code should be 200
    And the response content type should be "application/json; charset=utf-8"
    And the response should contain event data
    And the response should indicate upcoming events
    And the first event status metric should have status "Completed" and count greater than 0

  # Negative scenario: Analytics API - Invalid endpoint (404 Not Found)
  @negative
  Scenario: Validate response for invalid analytics API endpoint
    Given I use the API endpoint for "INVALID_ANALYTICS"
    When I send a GET request
    Then the response status code should be 404

  # Edge case: Analytics API - Unexpected fields
  @edge
  Scenario: Validate unexpected data in API response for Analytics API
    Given I use the API endpoint for "DASHBOARD_ANALYTICS"
    When I send a GET request
    Then the response status code should be 200
    And the response content type should be "application/json; charset=utf-8"
#    And the response should not contain unexpected fields

  # Metrics API - Positive scenario
  @positive @metrics @smoke
  Scenario: Validate the Dashboard Metrics API with valid metrics response
    Given I use the API endpoint for "DASHBOARD_METRICS"
    When I send a GET request
    Then the response status code should be 200
    And the response content type should be "application/json; charset=utf-8"
    And the response should contain "totalUsers" as 46
    And the response should contain "activeEvents" as 1

  # Metrics API - Negative scenario
  @negative
  Scenario: Validate response for invalid metrics API endpoint
    Given I use the API endpoint for "INVALID_METRICS"
    When I send a GET request
    Then the response status code should be 404