package pages;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import utils.Utilities;



public class MultiCityPage {
	   WebDriver webDriver;
	   Utilities utilities;
	   WebDriverWait wait;
	   private double totalPrice;
	  
	  
	    public MultiCityPage(WebDriver driver){
	        this.webDriver = driver;
	        PageFactory.initElements(driver, this);
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        this.totalPrice = 0;
	                
	    }


    @FindBy(xpath="//button[@class=\"btn btn-secondary dropdown-button dropdown-button-secondary mb-0 is-selected\"]")
    WebElement selectFlightType;
 
    @FindBy(xpath="//*[@id=\"dcep-a3660cf07-77a9-48ae-9d4a-f029519f2e81-flm-flight-tripType-item-2\"]")
    WebElement multiCityFlightType;
 
    @FindBy(xpath="//input[@name=\"flightQuery.flightSegments[0].originCode\"]")
	WebElement fromFieldLeg1;
	
	@FindBy(xpath="//input[@name=\"flightQuery.flightSegments[0].destinationCode\"]")
	WebElement toFieldLeg1;

	@FindBy(xpath="//i[@class=\"icon lh lh-circle-close\"]")
	WebElement clearButton;
	
	@FindBy(xpath="//input[@name=\"flightQuery.flightSegments[0].travelDatetime\"]")
	public WebElement calendarFieldLeg1;
	
	@FindBy(xpath="//input[@name=\"flightQuery.flightSegments[1].originCode\"]")
	WebElement fromFieldLeg2;
	
	@FindBy(xpath="//input[@name=\"flightQuery.flightSegments[1].destinationCode\"]")
	WebElement toFieldLeg2;
	
	@FindBy(xpath="//input[@name=\"flightQuery.flightSegments[1].travelDatetime\"]")
	public WebElement calendarFieldLeg2;
	
	@FindBy(xpath="//button[@class=\"btn btn-primary calendar-footer-continue-button\"]")
	public WebElement continueButton;
	
	@FindBy(xpath="//button[@class='btn btn-primary btn-multicity' and .//span[contains(text(), 'Search flights')]]")
	WebElement searchFlightForMultiCityFlight;
	
	
    public void clickOnSelectFlightType() {
	 selectFlightType.click();
	 
    }
 
    public void clickOnMultiCityFlightType(){
	 multiCityFlightType.click();
 
    }
    
    public void enterDepartureAirportForFirstLeg(String departureAirport) {
    	fromFieldLeg1.click();
    	clearButton.click();
    	fromFieldLeg1.sendKeys(departureAirport);
    
 
     }
    
    public void enterDestinationAirportForFirstLeg(String destinationAirport) {
        toFieldLeg1.click();
        toFieldLeg1.sendKeys(destinationAirport);
    	
    }
    
    public void clickOnCalendarPickerForLeg1() {
    	calendarFieldLeg1.click();
    	
    }
 
    public void selectFutureDate(WebElement calendarField) {
        String futureDate = utilities.getDateOneWeekFromToday();
        String dayOfMonth = futureDate.split("/")[1]; 
        WebElement dateToSelect = webDriver.findElement(By.xpath("//td[not(contains(@class, 'disabled')) and contains(text(),'" + dayOfMonth + "')]"));
        dateToSelect.click(); 
    }
    
    public void enterDepartureAirportForSecondLeg(String departureAirport) {
    	fromFieldLeg2.click();
    	fromFieldLeg2.sendKeys(departureAirport);
    	
    	
    }
    
    public void enterDestinationAirportForSecondLeg(String destinationAirport) {
    	toFieldLeg2.click();
        toFieldLeg2.sendKeys(destinationAirport);
        
    }
    
    public void clickOnCalendarPickerForLeg2() {
    	calendarFieldLeg2.click();
    	
    }
    
    public void clickOnContinueButton() {
    	continueButton.click();
    }
    
    public void searchFlightForMultiCityFlight() {
    	searchFlightForMultiCityFlight.click();
    }
    
    public void chooseCheapestAvailableFlights() {
    	
        List<WebElement> flightLegs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//div[@class=\"cell-content-top\"]")));

        for (WebElement leg : flightLegs) {
            List<WebElement> priceElements = leg.findElements(By.xpath("//div[@class=\"cell-content-top\"]"));
            double cheapestPrice = Double.MAX_VALUE;
            WebElement cheapestPriceElement = null;

            for (WebElement priceElement : priceElements) {
                String priceText = priceElement.getText().replace("$", "").replace(",", "");
                try {
                    double price = Double.parseDouble(priceText);
                    if (price < cheapestPrice) {
                        cheapestPrice = price;
                        cheapestPriceElement = priceElement;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Price not valid or sold out: " + priceText);
                }
            }

            if (cheapestPriceElement != null) {
                cheapestPriceElement.click();
                totalPrice += cheapestPrice;
                selectPriceInOtherLocations(cheapestPriceElement);
            } else {
                System.out.println("No available flights for this leg.");
            }
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    

    public double fetchDisplayedTotalPrice() {
        WebElement displayedTotalPriceElement = webDriver.findElement(By.xpath("//span[@class=\"price-amount\"]")); 
        String displayedTotalPriceText = displayedTotalPriceElement.getText().replace("$", "").replace(",", "");
        return Double.parseDouble(displayedTotalPriceText);
        
    }
    
    private void selectPriceInOtherLocations(WebElement selectedPriceElement) {
        List<WebElement> additionalPriceElements = webDriver.findElements(By.xpath("price-amount price-1-6-digits-display]")); 

        for (WebElement additionalElement : additionalPriceElements) {
            
            if (additionalElement.getText().contains(selectedPriceElement.getText())) {
                additionalElement.click();
                break; 
            }
        }
    }
    
    public void verifyTotalPriceMatching() {
    	double calculatedTotalPrice = getTotalPrice();
        double displayedTotalPrice = fetchDisplayedTotalPrice();
        final double TOLERANCE = 0.01; 
        Assert.assertEquals(calculatedTotalPrice, displayedTotalPrice, TOLERANCE);
    	
    	
    }
}
    
    

 
 
 
 
 
 
 
 

  