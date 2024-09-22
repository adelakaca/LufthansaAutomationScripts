Feature: Special Offers Functionality

Scenario: Check Special Offers 
 When I click on Book and Prepare
 And I click on Offers and Destinations
 And I change the value of the Sort By dropdown to 'Price' 
 Then I verify that the listed options are sorted according to the alphabet
 And  I change the value of the Sort By dropdown to 'Alphabet' 
 Then I verify that the listed options are sorted according to the price
 
 