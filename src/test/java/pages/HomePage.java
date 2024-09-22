package pages;



import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver webDriver;
	
    public HomePage(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath="//input[@name=\"flightQuery.flightSegments[0].originCode\"]")
	WebElement fromField;
	
	@FindBy(xpath="//input[@name=\"flightQuery.flightSegments[0].destinationCode\"]")
	WebElement toField;
	
	@FindBy(xpath="//input[@name=\"flightQuery.flightSegments[0].travelDatetime\"]")
	WebElement DepartReturnField;
	
	@FindBy(xpath="//i[@class=\"icon lh lh-circle-close\"]")
	WebElement clearButton;
	
	@FindBy(xpath="//input[@name=\"flightQuery.flightSegments[0].travelDatetime\"]")
	WebElement calendarField;
	
	@FindBy(xpath="//div[@class='return refx-display-5 date refx-body-1']")
	WebElement departDateElement;
    
	@FindBy(xpath="//div[@class=\"refx-display-5 date refx-body-1\"]")
	WebElement returnDateElement;
	
	@FindBy(xpath="//button[@class=\"btn btn-primary calendar-footer-continue-button\"]")
	WebElement continueButton;
	
	@FindBy(xpath="//button[@class='btn btn-primary' and .//span[contains(text(), 'Search flights')]]")
	WebElement searchFlightButton;
	
	@FindBy(xpath="//div[@class=\"refx-display-3 departure-code\"]")
	WebElement verifyDepartureAirportCode;
	
	@FindBy(xpath="//div[@class=\"destination-city ng-star-inserted\"]")
	WebElement verifyArrivalAirportCode;
	

	
	public void enterDepartureAndDestination(String departure, String destination) {
		fromField.click();
		clearButton.click();    
        fromField.sendKeys(departure);
        fromField.sendKeys(Keys.TAB);
        
        toField.click();
        toField.sendKeys(destination);
        toField.sendKeys(Keys.TAB);
        
        calendarField.click();
      
    }
	
	public void selectDateFromCalendar(String date) {
        String dayOfMonth = date.split("/")[1]; 
        WebElement dateToSelect = webDriver.findElement(By.xpath("//td[not(contains(@class, 'disabled')) and text()='" + dayOfMonth + "']"));
        dateToSelect.click();
    }

	
	
	public void clickOnContinueButton() {
		continueButton.click();
	}
	
	public void clickOnSearchFlightButton() {
		searchFlightButton.click();
	}
	
	public String getDepartureAirportCode() {
        return verifyDepartureAirportCode.getText();
    }

    public String getArrivalAirportCode() {
        return verifyArrivalAirportCode.getText();
    }
    
    
    public String getDisplayedDepartDate() {
        return departDateElement.getText();
    }
    
    public String getDisplayedReturnDate() {
    	return returnDateElement.getText();
    }
    
    public void verifyAirportCodes(String expectedDepartureCode, String expectedArrivalCode) {
        String actualDepartureCode = getDepartureAirportCode();
        String actualArrivalCode = getArrivalAirportCode();
        Assert.assertEquals(expectedDepartureCode, actualDepartureCode);
        Assert.assertEquals(expectedArrivalCode, actualArrivalCode);
    }

   
    public void verifyDates(String expectedDepartureDate, String expectedReturnDate) {
        String displayedDepartureDate = getDisplayedDepartDate();
        String displayedReturnDate = getDisplayedReturnDate();
        Assert.assertEquals(expectedDepartureDate, displayedDepartureDate);
        Assert.assertEquals(expectedReturnDate, displayedReturnDate);
        
    }
}
	
	
	
	
	


