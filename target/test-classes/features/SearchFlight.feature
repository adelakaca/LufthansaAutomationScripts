Feature: Flight Search Functionality

  Scenario: Check departure and destination airport codes
    When I enter departure as "Tirana - Mother Teresa Apt. Rinas - TIA" and destination as "Rome- Leonardo Da Vinci - FCO"
    And I select the departure date as one week after today date
    And I select the return date as 2 days after the departure date
    And I click the Continue button
    And I click the Search Flight button
    Then I verify that each result option contains the correct departure and destination airport codes
    
    
   