#@api
#Feature: Employee Attendance History
#  As a user
#  I want to view my attendance history upon navigating to the user dashboard
#  So that I can track my participation by clicking the "Get Attendance" button
#
#  Background:
#    Given The user signs in by clicking onto their account link
#    And That user is on the landing page
#    Then user navigates to User Dashboard
#
#  Scenario Outline: View complete attendance history
#    When I click the "Get Attendance" button
#    Then the API should respond with status <status_code>
#    And the response should contain total counts <total_count>
#
#    Examples:
#      | status_code | total_count |
#      | 200         | 2           |
#      | 200         | 0           |
#
#  Scenario Outline: Filter attendance records by event name and presence
#    When I click the "Get Attendance" button
#    And I filter attendance records by event name "<event_name>" and presence "<presence>"
#    Then the API should respond with status <status_code>
#    And the response should contain total counts <total_count>
#
#    Examples:
#      | email                  | event_name   | presence | status_code | total_count |
#      | employee14@company.com | GenAI-kata   | true     | 200         | 1           |
#      | employee14@company.com | GenAI-kata   | false    | 200         | 0           |
#      | employee14@company.com |              | --       | 200         | 2           |
#
#  Scenario Outline: Filter attendance records by start date and end date
#    When I click the "Get Attendance" button
#    And I filter attendance records by start date "<start_date>" and end date "<end_date>"
#    Then the API should respond with status <status_code>
#    And the response should contain total counts <total_count>
#
#    Examples:
#      | email                  | start_date           | end_date                | status_code | total_count |
#      | employee14@company.com | 2025-04-12T00:00:00  | 2025-04-14T00:00:00     | 200         | 1           |
#
#  Scenario Outline: Filter attendance records by start date, end date, event name, and presence
#    When I click the "Get Attendance" button
#    And I filter attendance records by start date "<start_date>", end date "<end_date>", event name "<event_name>" and presence "<presence>"
#    Then the API should respond with status <status_code>
#    And the response should contain total counts <total_count>
#
#    Examples:
#      | email                  | event_name   | presence | start_date           | end_date                | status_code | total_count |
#      | employee14@company.com | GenAI-kata   | true     | 2025-04-12T00:00:00  | 2025-04-14T00:00:00     | 200         | 1           |
#      | employee14@company.com | GenAI-kata   | false    | 2025-04-12T00:00:00  | 2025-04-14T00:00:00     | 200         | 0           |
#      | employee14@company.com | GenAI-kata   | --       | 2025-04-12T00:00:00  | 2025-04-14T00:00:00     | 200         | 1           |
#      | employee14@company.com |              | --       | 2025-04-12T00:00:00  | 2025-04-14T00:00:00     | 200         | 1           |
#      | employee14@company.com |              | true     | 2025-04-12T00:00:00  | 2025-04-14T00:00:00     | 200         | 1           |
#      | employee14@company.com |              | false    | 2025-04-12T00:00:00  | 2025-04-14T00:00:00     | 200         | 0           |
#      | employee14@company.com |              | true     |                      |                         | 200         | 2           |
#      | employee14@company.com |              | false    |                      |                         | 200         | 0           |
#      | employee14@company.com | GenAI-kata   | true     |                      |                         | 200         | 1           |
#      | employee14@company.com | GenAI-kata   | false    |                      |                         | 200         | 0           |
#      | employee14@company.com | GenAI-kata   | --       |                      |                         | 200         | 1           |