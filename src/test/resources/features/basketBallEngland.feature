Feature: Register as a member on the Basketball England website



Scenario: Successful supporter registration
  Given The user is on the BasketBall regitration page
  And The user fills in all the required fields
  And The user press "Confirm and join"
  Then The account should be created


  Scenario: Registration with missing last name
    Given The user is on the BasketBall regitration page
    When The user fills in all the required fields except last name
    And The user press "Confirm and join"
    Then The user should see an error message last name is required
    And The account should not be created

    Scenario: Registration with missing first name
      Given The user is on the BasketBall regitration page
      When The user fills  in all the required fields except first name
      And The user press "Confirm and join"
      Then The user should see an error message first name is required
      And The account should not be created


      Scenario: Registration with unchecked terms and conditions
        Given The user is on the BasketBall regitration page
        And The user fills in all the required fields without accepting terms
        But The user does not accept the terms and conditions
        And The user press "Confirm and join"
        Then The user should see an error message about Terms and Conditions
        And The account should not be created

      Scenario: Registration with unmatched password
        Given The user is on the BasketBall regitration page
        And The user fills in all the required fields but enter mismatching passwords
        Then The account should not be created
