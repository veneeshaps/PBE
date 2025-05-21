Feature: Generate QR Code for Event
#there is no invalid event is as it is directly fetching from database in ui

  @api @positive
  Scenario Outline: Successfully generate QR code for a valid event ID
    When I send a GET request with event ID "<eventId>"
    Then the status code is <expectedStatusCode>
    And the response body should contain a url
    Examples:
      |eventId|expectedStatusCode|
      |1003    |200               |

  @api @negative
  Scenario Outline: system does not generate QR code for an invalid event ID
    When I send a GET request with invalid event ID "<eventId>"
    Then the status code is <expectedStatusCode>
    And the response body should contain an error message
    Examples:
      |eventId|expectedStatusCode|
      |`1234  |400               |
      |!@#$%^ |400               |
      |23456  |404               |


