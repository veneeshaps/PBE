@ui
Feature: User Dashboard Functionality
  As a user
  I want to navigate to the user dashboard
  So that I can access and interact with user-specific features like attendance tracking

  Background:
    Given The user signs in by clicking onto their account link
    And That user is on the landing page

  Scenario: Navigate to User Dashboard
    Then user navigates to User Dashboard