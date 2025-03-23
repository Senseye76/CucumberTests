Feature: Calculator


  Scenario Outline: Add two numbers
    Given I have two numbers <first> and <second>
    When I <operation> the two numbers
    Then I get the result <result>
    Examples:
      | first | second | operation | result |
      | 10.0  | 20.0   | add       | 30.0   |
      | 123.0 | 654.0  | add       | 777.0  |
      | -22   | 23     | add       | 1      |
      | 20    | 10     | sub       | 10     |
      | 3     | 5      | mul       | 15     |
      | 15    | 3      | div       | 5      |




  #Scenario: Subtract two numbers
    #Given I have two numbers 100.0 and 50.0
   # When I subtract the two numbers
   # Then I get the result 50.0

  #Scenario: Subtract two numbers igen
   # Given I have two numbers 100.0 and 30.0
    #When I subtract the two numbers
    #Then I get the result 70.0