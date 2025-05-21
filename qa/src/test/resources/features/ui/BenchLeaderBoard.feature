@ui
Feature: Bench Leaderboard functionality
  Background:
    Given The user signs in by clicking onto their account link
    And That user is on the landing page
    Then user navigates to Bench Leaderboard

  @smoke @positive
  Scenario: The user can change the Score Breakdown
    When The user click on Score Breakdown button
    Then Score Breakdown form should be visible
    Then user enters "Codility" percentage as "60"
    And user enters "Gen AI Kata" percentage as "20"
    And user enters "Tech Talk" percentage as "20"
    And user clicks on save button

  @smoke @negative
  Scenario: User submits invalid percentage inputs in the Score Breakdown form
    When The user click on Score Breakdown button
    Then Score Breakdown form should be visible
    Then user enters "Codility" percentage as "20"
    And user enters "Gen AI Kata" percentage as "20"
    And user enters "Tech Talk" percentage as "20"
    And user clicks on save button
    Then an error message should be displayed saying "Invalid percentages entered!"

  @smoke
  Scenario: Verify the user can see all profiles under a specific domain from the dropdown
    When I select the All Domains dropdown
    Then I select "Java" from the dropdown
    Then the profiles displayed on the table should belong to the "Java" domain
    And the total number of profiles displayed should match the available count for the "Java" domain
    When I select ".NET" from the dropdown
    Then the profiles displayed on the table should belong to the ".NET" domain
    And the total number of profiles displayed should match the available count for the ".NET" domain

  @smoke
  Scenario: Validate file download and success message after clicking the export button
    When I click on the Export button
    Then a "File Downloaded successfully!" message should appear confirming the file download
