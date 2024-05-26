Feature: Login test steps for SwagLabs

  Background:
    Given Login Page

  Scenario: Login in successfully with standard user
    When User types the standard user and password
    Then Login should be successful

  Scenario: User tries to log in with locked user
    When User tries to log in with a locked account
    Then An error should occur