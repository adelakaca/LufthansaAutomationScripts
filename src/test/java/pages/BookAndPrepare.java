package pages;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookAndPrepare {
   WebDriver webDriver;
	
    public BookAndPrepare(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
        
        
    }
	
	@FindBy(xpath="//span[@class='common-header-nav-button-inner' and contains(text(), 'Book & Prepare')]")
    WebElement BookAndPrepareButton;
    
	@FindBy(xpath="//a[contains(text(),'Offers & destinations')]")
	WebElement OffersAndDestinationButton; 
	
	@FindBy(id="sortBy")
	WebElement sortByArrow;

	@FindBy(id="sortBy-item-1")
	WebElement priceElement;
	
	@FindBy(id="sortBy-item-2")
	WebElement alphabetElement;

   public void clickOnBookAndPrepareButton() {
	   BookAndPrepareButton.click();
	       
   }
   
   public void clickOnOffersAndDestinationButton() {
	   OffersAndDestinationButton.click();
	   
   }
   
   public void clickOnSortByButton() {
	   sortByArrow.click();
	   
   }
   
   public void clickOnPriceElement() {
	   priceElement.click();

   }
   
   public void clickOnAlphabetElement() {
	   alphabetElement.click();
	   
   }
   
   public void changeSortByDropdown(String toValue) {
       clickOnSortByButton();
       switch (toValue.toLowerCase()) {
           case "Price":
               clickOnPriceElement();  // Handles "Price"
               break;
           case "Alphabet":
               clickOnAlphabetElement();  // Handles "Alphabet"
               break;
           default:
               throw new IllegalArgumentException("Invalid sorting option: " + toValue);
       }
   }
   
   public List<String> getListedOptions() {
       List<WebElement> optionElements = webDriver.findElements(By.xpath("//span[@class='city-name']"));
       List<String> options = new ArrayList<>();
       for (WebElement option : optionElements) {
           options.add(option.getText().trim());
       }
       return options;
   }
   
   public void verifyOptionsSortedAlphabetically() {
       List<String> options = getListedOptions();
       List<String> sortedOptions = new ArrayList<>(options);
       Collections.sort(sortedOptions);
       Assert.assertEquals("The options are not sorted alphabetically.", sortedOptions, options);
   }
   
   public void verifyOptionsSortedByPrice() {
       List<WebElement> priceElements = webDriver.findElements(By.xpath("//div[@class=\"price-line-tile\"]")); 
       List<Double> prices = new ArrayList<>();
       for (WebElement priceElement : priceElements) {
           String text = priceElement.getText().trim();
           try {
               String priceText = text.replace("$", "").replace(",", "");
               prices.add(Double.parseDouble(priceText));
           } catch (NumberFormatException e) {
               e.printStackTrace();
           }
       }
       List<Double> sortedPrices = new ArrayList<>(prices);
       Collections.sort(sortedPrices);
       Assert.assertEquals("The options are not sorted by price.", sortedPrices, prices);
   }
}
   
