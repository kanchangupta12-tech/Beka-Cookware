Feature: Navigation & Categories

  Scenario Outline: Choose category and validate product grid
    Given user navigates to the application
    Then user clicks on cookware category
    And choose any product set "<product>" and validates the product displayed count
    Then user choose any subcategories "<subproduct>" and validates the product displayed count
    Examples:
      | product     | subCategory |
      | Frying pans | Frying pans |