Feature: Basic arithmetic

  Scenario: Add two numbers
    Given the calculator is open
    When I enter 12
    And I press add
    And I enter 7
    And I press equals
    Then the result should be 19

  Scenario: Subtract two numbers
    Given the calculator is open
    When I enter 20
    And I press subtract
    And I enter 5
    And I press equals
    Then the result should be 15

  Scenario: Multiply two numbers
    Given the calculator is open
    When I enter 3
    And I press multiply
    And I enter 4
    And I press equals
    Then the result should be 12

  Scenario: Divide two numbers
    Given the calculator is open
    When I enter 9
    And I press divide
    And I enter 3
    And I press equals
    Then the result should be 3

  Scenario: Percent of a number
    Given the calculator is open
    When I enter 50
    And I press percent
    Then the result should be 0.5

  Scenario: All clear resets to zero
    Given the calculator is open
    When I enter 99
    And I press AC
    Then the result should be 0

  Scenario: Subtract to get a negative result
    Given the calculator is open
    When I enter 5
    And I press subtract
    And I enter 10
    And I press equals
    Then the result should be -5

  Scenario: Add two numbers with decimals
    Given the calculator is open
    When I enter 2.5
    And I press add
    And I enter 3.5
    And I press equals
    Then the result should be 6

  Scenario: Perform a chained operation
    Given the calculator is open
    When I enter 10
    And I press add
    And I enter 5
    And I press subtract
    And I enter 3
    And I press equals
    Then the result should be 12
