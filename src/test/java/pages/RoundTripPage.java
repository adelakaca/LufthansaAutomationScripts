package pages;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.datatable.DataTable;
import utils.Utilities;

public class RoundTripPage {
	 WebDriver webDriver;
	 Utilities utilities;
	 
	  public RoundTripPage(WebDriver driver){
	        this.webDriver = driver;
	        PageFactory.initElements(driver, this);
	        this.utilities = new Utilities();
	             
	    }
	  
	  @FindBy(xpath="//input[@name=\"flightQuery.flightSegments[0].originCode\"]")
	  WebElement fromField;
		
	  @FindBy(xpath="//input[@name=\"flightQuery.flightSegments[0].destinationCode\"]")
	  WebElement toField;
	  
	  @FindBy(xpath="//button[@class='btn btn-primary' and .//span[contains(text(), 'Search flights')]]")
	  WebElement searchFlightButton;
	  
	  @FindBy(xpath="//input[@name=\"flightQuery.flightSegments[0].travelDatetime\"]")
	  WebElement calendarField;
	
	  @FindBy(xpath="//button[@class=\"btn btn-primary calendar-footer-continue-button\"]")
	  WebElement continueButton;
	  
	  @FindBy(xpath="//span[@class=\"filters-button-label\"]")
	  WebElement filtersButton;
	  
	  @FindBy(id="mat-radio-31-input")
	  WebElement nonStopCheckBox;
	  
	  @FindBy(xpath="//span[@class=\"mat-slide-toggle-bar\"]")
	  WebElement airlineToggle;
	  
	  @FindBy(xpath="//label[text()='\" + airlineName + \"']/preceding-sibling::span[@class='mat-checkbox-inner-container']")
	  WebElement airlineCheckbox;
	  
	  @FindBy(xpath="//span[@class='mat-radio-outer-circle' and contains(text(), '\" + departureTime + \"')]")
	  WebElement departureTimeRadio;
	  
	  @FindBy(xpath="//span[@class='mat-radio-outer-circle' and contains(text(), '\" + returnTime + \"')]")
	  WebElement returnTimeRadio;
	  
	  @FindBy(xpath="//span[@class=\"refx-body-2 filtered-airbounds ng-star-inserted\"]")
	  WebElement numberOfResults;
	  
	  @FindBy(xpath="//div[@class=\"mat-chip-ripple\"]")
	  WebElement displayedResult;
	  
	  public void enterDepartureAirport(String departureAirport) {
		  fromField.click();
          fromField.click();
          fromField.sendKeys(departureAirport);
          fromField.sendKeys(Keys.TAB);
		  
	  }
	  
	  public void enterDestinationAirport(String destinationAirport) {
		  toField.click();
          toField.sendKeys(destinationAirport);
		  
	  }
	  
	  public void selectValidFutureDates() {
		    calendarField.click();
		    String departureDate = utilities.getDateOneWeekFromToday();
		    selectDate(departureDate);
		    String returnDate = utilities.getDateTwoDaysAfter(departureDate);
		    selectDate(returnDate);
		}
	  

		private void selectDate(String date) {
		    String dayOfMonth = date.split("/")[1]; 
		    WebElement dateToSelect = webDriver.findElement(By.xpath("//td[not(contains(@class, 'disabled')) and text()='" + dayOfMonth + "']"));
		    dateToSelect.click(); 
		}
	 
	  
	  public void clickOnContinueButton() {
		  continueButton.click();
	  }
	  
	  public void searchFlightButton() {
		  searchFlightButton.click();

	  }
	  
	  public void clickOnFiltersButton() {
		  filtersButton.click();

	  }
	  
	  
	  public void applyFilters(DataTable filterTable) {
		    List<Map<String, String>> filters = filterTable.asMaps(String.class, String.class);
		    
		    for (Map<String, String> filter : filters) {
		        String filterType = filter.get("Filter");
		        String filterValue = filter.get("Value");

		        switch (filterType) {
		            case "Non-stop flights only":
		                applyNonStopFilter(filterValue);
		                break;
		            case "Specific airline":
		                selectSpecificAirline(filterValue);
		                break;
		            case "Departure time":
		                selectDepartureTimeRadio(filterValue);
		                break;
		            case "Return time":
		                selectReturnTimeRadio(filterValue);
		                break;
		            default:
		                throw new IllegalArgumentException("Unknown filter type: " + filterType);
		        }
		    }
		}
	  
	  public void applyNonStopFilter(String filterValue) {
		  if (filterValue.equalsIgnoreCase("Yes") && !nonStopCheckBox.isSelected()) {
		        nonStopCheckBox.click(); 
		    } else if (filterValue.equalsIgnoreCase("No") && nonStopCheckBox.isSelected()) {
		        nonStopCheckBox.click(); 
		    }
		}
	  
	  public void selectSpecificAirline(String airline) {
		    if (airlineToggle.isSelected()) {
		        airlineToggle.click(); 
		    }
		    
		    selectAirline(airline); 
	  }
	  
	  public void selectAirline(String airlineName) {
		    if (!airlineCheckbox.isSelected()) {
		        airlineCheckbox.click(); 
		    }

}
	  
	  public void selectDepartureTimeRadio(String timeRange) {
		    if (timeRange.equals("00:00 AM to 11:59 PM")) {
		        if (!departureTimeRadio.isSelected()) {
		            departureTimeRadio.click(); 
		        }
		    }
	  }
	  
	  public void selectReturnTimeRadio(String timeRange) {
		    if (timeRange.equals("12:00 PM to 17:59 PM")) {
		        if (!returnTimeRadio.isSelected()) {
		            returnTimeRadio.click(); 
		        }
		    }
		}
	  
	  public void resultDisplayedAccordingFiltering() {
		  Assert.assertTrue(displayedResult.isDisplayed());
		  
	  }
	  
	  
	  public void validateNumberOfResults() {
		    String actualResultText = numberOfResults.getText();
		    String[] parts = actualResultText.split(" out of ");
		    
		    int actualCount = Integer.parseInt(parts[0].trim()); 
		    int totalCount = Integer.parseInt(parts[1].split(" ")[0].trim()); 
		    Assert.assertTrue("The number of results after filtering is greater than the total available.", actualCount <= totalCount);
		  
		  
	  }
	  
	  

}