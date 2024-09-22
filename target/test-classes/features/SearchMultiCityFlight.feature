Feature: Multi-City Flight Booking with Price Validation

  Scenario: Check Multi-City Flight Booking and Price Validation
 
    When I select the Multi-City dropdown option for Multi-City Flight
    And I enter "Dublin (DUB)" as the departure airport for the first leg
    And I enter "New York (JFK)" as the destination airport for the first leg
    And I select a valid future date for the first leg departure
    And I add a second leg with "New York (JFK)" as the departure airport
    And I enter "London (LHR)" as the destination airport for the second leg
    And I select a valid future date for the second leg departure
    And I click the Search Flights button for MultiCity flight
    And I choose the cheapest available flight for each leg
    Then I verify that the total price matches the sum of the individual flight prices