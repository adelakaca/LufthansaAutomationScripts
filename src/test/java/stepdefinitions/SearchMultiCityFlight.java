package stepdefinitions;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.MultiCityPage;
import utils.Utilities;

public class SearchMultiCityFlight {
	
   WebDriver driver; 
   MultiCityPage multiCityPage;
   HomePage homePage;
   Utilities utilities;

	
	public SearchMultiCityFlight() {
	    this.driver = DriverFactory.getDriver();
	    this.multiCityPage = new MultiCityPage(driver);
	        
    }
	

	@When("I select the Multi-City dropdown option for Multi-City Flight")
	public void i_select_the_dropdown_option_for_multi_city_flight() {
		multiCityPage.clickOnSelectFlightType();
        multiCityPage.clickOnMultiCityFlightType();
	   
	}
	
	@And("I enter {string} as the departure airport for the first leg")
	public void i_enter_as_the_departure_airport_for_the_first_leg(String departureAirport) {
		multiCityPage.enterDepartureAirportForFirstLeg(departureAirport);
	    
	}
	
	@And("I enter {string} as the destination airport for the first leg")
	public void i_enter_as_the_destination_airport_for_the_first_leg(String destinationAirport) {
	    multiCityPage.enterDestinationAirportForFirstLeg(destinationAirport);
	    
	}
	
	@And("I select a valid future date for the first leg departure")
	public void i_select_a_valid_future_date_for_the_first_leg_departure() {
		multiCityPage.clickOnCalendarPickerForLeg1();
        multiCityPage.selectFutureDate(multiCityPage.calendarFieldLeg1);
        multiCityPage.clickOnContinueButton();
		
	    
	}
	
	@And("I add a second leg with {string} as the departure airport")
	public void i_add_a_second_leg_with_as_the_departure_airport(String departureAirport) {
		multiCityPage.enterDepartureAirportForSecondLeg(departureAirport);
	   
	}
	
	@And("I enter {string} as the destination airport for the second leg")
	public void i_enter_as_the_destination_airport_for_the_second_leg(String destinationAirport) {
		multiCityPage.enterDestinationAirportForSecondLeg(destinationAirport);
	    
	}
	
	@And("I select a valid future date for the second leg departure")
	public void i_select_a_valid_future_date_for_the_second_leg_departure() {
		multiCityPage.clickOnCalendarPickerForLeg2();
        multiCityPage.selectFutureDate(multiCityPage.calendarFieldLeg2);
        multiCityPage.clickOnContinueButton();
		
	}
	
	
	@And("I click the Search Flights button for MultiCity flight")
	public void i_click_the_Search_Flights_button_for_multi_city_flight() {
		multiCityPage.searchFlightForMultiCityFlight();
	    
	}
	
	@And("I choose the cheapest available flight for each leg")
	public void i_choose_the_cheapest_available_flight_for_each_leg() {
		 multiCityPage.chooseCheapestAvailableFlights();
		
	}
	
	@Then("I verify that the total price matches the sum of the individual flight prices")
	public void i_verify_that_the_total_price_matches_the_sum_of_the_individual_flight_prices() {
		multiCityPage.verifyTotalPriceMatching();
	    
	}



}
