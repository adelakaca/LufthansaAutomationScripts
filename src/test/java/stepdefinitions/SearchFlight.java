package stepdefinitions;

import org.openqa.selenium.WebDriver;

import Factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.HomePage;
import utils.Utilities;

public class SearchFlight {
	WebDriver driver;
	HomePage homePage;
	Utilities utils = new Utilities();
	public String departureDate;
    public String returnDate;
    
    public SearchFlight() {
    	
    	this.driver = DriverFactory.getDriver();
        this.homePage = new HomePage(driver);
    	
    }
	

	@When("I enter departure as {string} and destination as {string}")
	public void i_enter_departure_as_and_destination_as(String departure, String destination) {
		homePage.enterDepartureAndDestination(departure, destination);
	    
	}
	
	@And("I select the departure date as one week after today date")
	public void i_select_the_departure_date_as_one_week_after_today_date() {
		departureDate = utils.getDateOneWeekFromToday();
		homePage.selectDateFromCalendar(departureDate);
		
	    
	}
	
	@And("I select the return date as {int} days after the departure date")
	public void i_select_the_return_date_as_days_after_the_departure_date(Integer daysAfterDeparture) {
		returnDate = utils.getDateTwoDaysAfter(departureDate);
		homePage.selectDateFromCalendar(returnDate);
	   
	}
	
	@And("I click the Continue button")
	public void i_click_the_continue_button() {
		homePage.clickOnContinueButton();
	   
	}
	
	@And("I click the Search Flight button")
	public void i_click_the_search_flight_button() {
		homePage.clickOnSearchFlightButton();
	    
	}
	
	@Then("I verify that each result option contains the correct departure and destination airport codes")
	public void i_verify_that_each_result_option_contains_the_correct_departure_and_destination_airport_codes() {
	   
	     String expectedDepartureCode = "TIA";
	     String expectedArrivalCode = "FCO";
         homePage.verifyAirportCodes(expectedDepartureCode, expectedArrivalCode);
	        
	     
	    }
	
	@Then("I verify that the correct departure and return dates are displayed at the top of the results screen")
	public void i_verify_that_the_correct_departure_and_return_dates_are_displayed_at_the_top_of_the_results_screen() {
		String displayedDepartureDate = homePage.getDisplayedDepartDate();
	    String displayedReturnDate = homePage.getDisplayedReturnDate();
	    homePage.verifyDates(displayedDepartureDate, displayedReturnDate);
	    
	}


}
