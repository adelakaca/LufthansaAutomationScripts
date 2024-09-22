package stepdefinitions;

import org.openqa.selenium.WebDriver;

import Factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.MultiCityPage;
import pages.RoundTripPage;

public class SearchRoundTripFlights {
	
	WebDriver driver; 
	RoundTripPage roundTripPage;
	
	
	public SearchRoundTripFlights() {
	
	    this.driver = DriverFactory.getDriver();
	    this.roundTripPage = new RoundTripPage(driver);
	    
	}
	
	
	@When("I enter {string} as the departure airport")
	public void i_enter_as_the_departure_airport(String departureAirport) {
		roundTripPage.enterDepartureAirport(departureAirport);
	   
	}
	
	@And("I enter {string} as the destination airport")
	public void i_enter_as_the_destination_airport(String destinationAirport) {
		roundTripPage.enterDestinationAirport(destinationAirport);
	  
	}
	
	@And("I select valid future dates for both the departure and return flights")
	public void i_select_valid_future_dates_for_both_the_departure_and_return_flights() {
		roundTripPage.selectValidFutureDates();
	    
	}
	
	@And("I click on the Search Flights button")
	public void i_click_on_the_Search_Flights_button() {
		roundTripPage.clickOnContinueButton();
	    roundTripPage.searchFlightButton();
	
	}
	
	@And("I click on the Filters button")
	public void i_click_on_the_Filters_button() {
		roundTripPage.clickOnFiltersButton();
		
	}
	
	
	@And("I apply the following filters:")
	public void i_apply_the_following_filters(io.cucumber.datatable.DataTable dataTable) {
		roundTripPage.applyFilters(dataTable);
		
	}
	
	@And("I ensure that the displayed results comply with the applied filter criteria")
	public void i_ensure_that_the_displayed_results_comply_with_the_applied_filter_criteria() {
		roundTripPage.resultDisplayedAccordingFiltering();
	   
	}
	
	
	@Then("I validate that the number of results after filtering is decreased")
	public void i_validate_that_the_number_of_results_after_filtering_is_decreased() {
		roundTripPage.validateNumberOfResults();
	   
	}


}
