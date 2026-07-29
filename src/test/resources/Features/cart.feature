Feature: Cart functionality
  Scenario: Validate cart functionality without adding product
    Given user navigates to the application
    When user click on cart menu
    Then user validates empty cart message
    And clicks on continue shopping and homepage is displayed


  Scenario: Add a product and validate the cart
    Given user navigates to the application
    When user adds product to the cart
    Then user validates the cart counter
    And user validates the valid pricing format

  Scenario: Add a product and update the quantity in cart
    Given user navigates to the application
    When user adds product to the cart
    And user opens the cart page and update the quantity
    Then user validates the cart counter and the recalculated prices


