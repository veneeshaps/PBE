@api @dashboard @feedback
Feature: Validate the Dashboard Feedback APIs

  # Positive Scenarios

  @positive @pendingFeedbackEvents
  Scenario: Validate getting pending feedback events
    Given the API endpoint is "PENDING_FEEDBACK_EVENTS"
    And I set the query parameters:
      | pageNumber | 1 |
      | pageSize   | 5 |
    When I send a GET request
    Then the response status code should be 200
    And the response content type should be "application/json; charset=utf-8"
    And the response total count should be 4
    And the response should contain event name "Gen AI Kata 1"

  @positive @feedbackSentEventsByName
  Scenario: Validate getting feedback sent events by name
    Given the API endpoint is "FEEDBACK_SENT_EVENTS_BY_NAME"
    And I set the query parameters:
      | eventName  | Event1 |
      | pageNumber | 1   |
      | pageSize   | 5   |
    When I send a GET request
    Then the response status code should be 200
    And the response content type should be "application/json; charset=utf-8"
    And the response total count should be 0

  @positive @feedbackSentEvents
  Scenario: Validate getting feedback sent events
    Given the API endpoint is "FEEDBACK_SENT_EVENTS"
    And I set the query parameters:
      | pageNumber | 1 |
      | pageSize   | 5 |
    When I send a GET request
    Then the response status code should be 200
    And the response content type should be "application/json; charset=utf-8"
    And the response total count should be 1
    And the response data list should not be empty

  @positive @pendingFeedbackEventsByName
  Scenario: Validate getting pending feedback events by name
    Given the API endpoint is "PENDING_FEEDBACK_EVENTS_BY_NAME"
    And I set the query parameters:
      | eventName  | Event1 |
      | pageNumber | 1   |
      | pageSize   | 5   |
    When I send a GET request
    Then the response status code should be 200
    And the response content type should be "application/json; charset=utf-8"
    And the response total count should be 1

  # Negative Scenarios

  @negative
  Scenario: Validate missing query parameters for feedback APIs
    Given the API endpoint is "PENDING_FEEDBACK_EVENT"
    When I send a GET request without query parameters
    Then the response status code should be 400

  @negative
  Scenario: Validate invalid query parameter values
    Given the API endpoint is "PENDING_FEEDBACK_EVENTS"
    And I set the query parameters:
      | pageNumber | -1  |
      | pageSize   | -1 |
    When I send a GET request
    Then the response status code should be 500
    And the response message should contain "Invalid query parameter value"

  @negative @pagination
  Scenario: Validate out-of-bounds page number
    Given the API endpoint is "PENDING_FEEDBACK_EVENTS"
    And I set the query parameters:
      | pageNumber | 999 |
      | pageSize   | 5   |
    When I send a GET request
    Then the response status code should be 200
    And the response total count should be 4
    And the response data list should be empty

  @edge
  Scenario: Validate response with special characters in eventName
    Given the API endpoint is "PENDING_FEEDBACK_EVENTS_BY_NAME"
    And I set the query parameters:
      | eventName  | !@#$%^&*() |
      | pageNumber | 1          |
      | pageSize   | 5          |
    When I send a GET request
    Then the response status code should be 200
    And the response total count should be 0
    And the response data list should be empty

  @security
  Scenario: Validate response for authorized access
    Given the API endpoint is "FEEDBACK_SENT_EVENTS"
    And I provide a valid authorization token "Bearer valid_token_here"
    When I send a GET request
    Then the response status code should be 200
    And the response content type should be "application/json; charset=utf-8"