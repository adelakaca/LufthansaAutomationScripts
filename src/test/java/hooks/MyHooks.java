package hooks;


import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import Factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class MyHooks {
	
	WebDriver driver; 
	
	@Before(order=0)
	public void setup() {
		
		 DriverFactory.initializeBrowser("chrome"); 
	     driver = DriverFactory.getDriver();
		 driver.manage().deleteAllCookies();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 driver.get("https://www.lufthansa.com/ie/en/homepage");
		 driver.findElement(By.xpath("//div[@id='cm-allNoneBtns']//button[@id='cm-acceptAll']")).click();

		 
	}
	
	@After(order=0)
	public void tearDown() {
		
		if (driver != null) {
            driver.quit();
        }
		
	}
	
	@After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);

            File screenshotFile = new File("screenshots/" + screenshotName + ".png");
            try {
                FileUtils.writeByteArrayToFile(screenshotFile, sourcePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
