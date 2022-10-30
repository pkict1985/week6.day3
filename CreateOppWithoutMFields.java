package week6.day3;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOppWithoutMFields {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// *Salesforce crendentials:*
		// Username: ramkumar.ramaiah@testleaf.com
		// Password: Password#123
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);
		Actions builder=new Actions(driver);
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password#123"); 
		driver.findElement(By.id("Login")).click();
		//2. Click on toggle menu button from the left corner
		WebElement appLauncherElement = driver.findElement(By.xpath("//button[contains(@class,'slds-icon-waffle_container')]"));
		builder.moveToElement(appLauncherElement).click().perform();
		//3. Click view All and click Sales from App Launcher
		WebElement viewAllElement=driver.findElement(By.xpath("//button[@class='slds-button' and text()='View All']"));
		builder.moveToElement(viewAllElement).click().perform();

		WebElement salesElement = driver.findElement(By.xpath("//p[@class='slds-truncate' and text()='Sales']"));
		builder.moveToElement(salesElement).perform();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",salesElement);

		//4. Click on Opportunity tab 
		WebElement opportunityElement = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		builder.moveToElement(opportunityElement).click().perform();
		
		//5. Click on New button
		driver.findElement(By.xpath("//a[@class='forceActionLink']")).click();
		//6. Choose Close date as Tomorrow Date
		WebElement closedateIconElement = driver.findElement(By.xpath("(//lightning-button-icon)[4]"));
		builder.moveToElement(closedateIconElement).click().perform();
		WebElement tomorrowCalenderElement = driver.findElement(By.xpath("//td[@class='slds-is-today']/following::td[1]"));
		tomorrowCalenderElement.click();
		
		//7. Click on save 
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand']")));
		//8. Verify the Alert message (Complete this field) displayed for Name and Stage"
		// Switching to Alert        
		
		List<WebElement> fields = driver.findElements(By.xpath("//div[@class='slds-form-element__help']//preceding-sibling::label"));
		List<WebElement> completeField = driver.findElements(By.xpath("//div[@class='slds-form-element__help']"));
		for (int i=0;i<completeField.size();i++) {
			if(completeField.get(i).getText().equals("Complete this field.")) {
				System.out.println("verified to Complete this field."+ fields.get(i).getText());
			}
		}
		WebElement errorDialog = driver.findElement(By.xpath("(//div[@role='dialog'])[2]"));
		builder.moveToElement(errorDialog).perform();
		
		WebElement closeBtn = driver.findElement(By.xpath("//lightning-button-icon[contains(@class, 'slds-popover__close')]"));
		builder.moveToElement(closeBtn).click().perform();
		
	    //driver.close();
	}
}
