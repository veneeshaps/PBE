Feature: View list of employees

  @api @EmployeeList
  Scenario: Successfully retrieve list of employees
    Given the endpoint "GET_EMPLOYEE" is configured
    When I send a GET request
    Then the response status code should be 200
    And the response content type should be "application/json; charset=utf-8"
    And the response should contain 46 profiles
    And each profile should contain the following fields:
      | employeeId            |
      | email                 |
      | firstName             |
      | lastName              |
      | domainId              |
      | roleId                |
      | dateOfJoining         |
      | isDeactivated         |
      | createdAt             |
      | updatedAt             |
      | domain                |
      | role                  |
      | attendances           |
      | scores                |
      | createdScheduledEvents|
      | trainingBatches       |
