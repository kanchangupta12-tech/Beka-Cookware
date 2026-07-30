Feature: Negative and Edge cases
  Scenario: Setting the cart quantity to zero removes the item
    Given user navigates to the application
    When user adds product to the cart
    And user opens the cart page and update the quantity to zero
    Then user validates the cart is empty

  Scenario: Increasing the cart quantity to large number and validate the price
    Given user navigates to the application
    When user adds product to the cart
    And user opens the cart page and update the quantity to ten
    Then user validates the price is updated for ten quantity

  Scenario: Searching special characters yields no products
    Given user navigates to the application
    When user search a using special characters
    Then user gets no results message