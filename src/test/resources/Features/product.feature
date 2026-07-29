Feature: Product detail page
  Scenario: Validate product name, price, description, images
    Given user navigates to the application
    When user selects a product
    Then user validates product image is displayed
    And validates product name, price and description is displayed

  Scenario: Validate locale-specific price format
    Given user navigates to the application
    When user switches language and selects a product
    When user selects a product
    Then user validates the product price
    And validates the test image gallery