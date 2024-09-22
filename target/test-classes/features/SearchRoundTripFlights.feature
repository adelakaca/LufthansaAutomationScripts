Feature: Search for Round-Trip Flights with Specific Filters on Lufthansa Homepage

  Scenario: Search for Round-Trip Flights and Validate Filtered Results
    When I enter "Dublin (DUB)" as the departure airport
    And I enter "Frankfurt (FRA)" as the destination airport
    And I select valid future dates for both the departure and return flights
    And I click on the Search Flights button
    And I click on the Filters button
    And I apply the following filters:
      | Filter                | Value                         |
      | Non-stop flights only | No Preference                 |
      | Specific airline      | Lufthansa                     |
      | Departure time        | 00:00 AM to 11:59 PM          |
      | Return time           | 12:00 PM to 17:59 PM          |
    Then I ensure that the displayed results comply with the applied filter criteria
    And I validate that the number of results after filtering is decreased