Feature: Create a new order and place it in Swag Labs

  Background:
    Given User is logged in to Swag labs

  Scenario: User adds a product to cart in Swag Labs
    When It selects a product to add to cart
    Then The cart icon should have displayed the numbers of products added


  Scenario: User places order in Swag Labs
    When User presses the cart icon
    And Presses the Checkout button
    And The user fills out the form
    Then The order is placed successfully

  Scenario: Check if user can order without selecting an product
    When User presses the cart icon
    And Presses the Checkout button
    And The user fills out the form
    Then The order is placed successfully