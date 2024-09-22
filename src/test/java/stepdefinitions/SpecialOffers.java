package stepdefinitions;

import org.openqa.selenium.WebDriver;

import Factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BookAndPrepare;

  public class SpecialOffers {
	WebDriver driver; 
	BookAndPrepare bookAndPrepare;
	
   public SpecialOffers() {
	    this.driver = DriverFactory.getDriver();
		this.bookAndPrepare = new BookAndPrepare(driver);
		
		
    }
	
	@When("I click on Book and Prepare")
	public void i_click_on_Book_And_Prepare() {
	bookAndPrepare.clickOnBookAndPrepareButton();
	    
	}
	
	@And("I click on Offers and Destinations")
	public void i_click_on_Offers_and_Destinations(){
	bookAndPrepare.clickOnOffersAndDestinationButton();
	
	}
	
	
	@And("I change the value of the Sort By dropdown to {string}")
	public void i_change_the_value_of_the_Sort_By_dropdown_to(String toValue)  {
    bookAndPrepare.changeSortByDropdown(toValue);
	    
	}
	
	@And("I verify that the listed options are sorted according to the alphabet")
	public void i_verify_that_the_listed_options_are_sorted_according_to_the_alphabet() {
    bookAndPrepare.getListedOptions();
   	bookAndPrepare.verifyOptionsSortedAlphabetically();
   	
	}
	
	
	@Then("I verify that the listed options are sorted according to the price")
	public void i_verify_that_the_listed_options_are_sorted_according_to_the_price() {
	bookAndPrepare.verifyOptionsSortedByPrice();
	  
	}



	
	
	
	
	
	
	
	

}
