package week6.day3;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewOppHA {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// *Salesforce crendentials:*
		// Username: ramkumar.ramaiah@testleaf.com
		// Password: Password#123
		
		//1. Login to https://login.salesforce.com
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
		builder.moveToElement(salesElement).click().perform();
		
		//4. Click on Opportunity tab 
		WebElement opportunityElement = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		builder.moveToElement(opportunityElement).click().perform();
		
		//5. Click on New button
		driver.findElement(By.xpath("//a[@class='forceActionLink']")).click();
		
		//6. Enter Opportunity name as 'Salesforce Automation by *Your Name*,Get the text and Store it 
		WebElement oppnameElemet = driver.findElement(By.xpath("(//input[@class='slds-input'])[4]"));
		oppnameElemet.sendKeys("Salesforce Automation by Praveena", Keys.ENTER);
		String oppnameText = oppnameElemet.getAttribute("value");
		
		//7. Choose close date as Today
		WebElement closedateIconElement = driver.findElement(By.xpath("(//lightning-button-icon)[4]"));
		builder.moveToElement(closedateIconElement).click().perform();
		WebElement todayCalenderElement = driver.findElement(By.xpath("//button[text()='Today']"));
		System.out.println(todayCalenderElement.isDisplayed());
		todayCalenderElement.click();
		
		//8. Select 'Stage' as Need Analysis
		WebElement comboStageElement = driver.findElement(By.xpath("(//button[contains(@class,'slds-combobox__input')])[1]"));
		System.out.println(comboStageElement.isDisplayed());
		builder.moveToElement(comboStageElement).click().perform();
		List<WebElement> comboStageVal = driver.findElements(By.xpath("//label[text()='Stage']/following-sibling::div[1]//div//div[2]"));
		for (WebElement webElement : comboStageVal) {
			String text=webElement.getText();
			if(text.contains("Needs Analysis"))
			{
				Thread.sleep(3000);
				webElement.click();
			}
		}
		
		//9. click Save and VerifyOppurtunity Name"
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand']")));
		String resultOppText = driver.findElement(By.xpath("(//lightning-formatted-text)[6]")).getText();
		if(resultOppText.equals(oppnameText)) System.out.println("Opportunity with name "+ oppnameText + " is verified to be correct");
		else System.out.println("Opportunity with name "+ oppnameText +"is verified to be incorrect");
	}

}
