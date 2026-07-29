Feature: Navigation & Categories

  Scenario: Choose category and validate product grid
    Given user navigates to the application
    Then user clicks on cookware category
    And choose any product set category and validates the product displayed count
    Then user choose any subcategories subCategory and validates the product displayed count
