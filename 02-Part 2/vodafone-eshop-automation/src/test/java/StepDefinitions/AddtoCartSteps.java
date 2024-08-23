package StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter; 


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AddtoCartSteps {


	static WebDriver driver = null;
	static WebDriverWait wait = null;
	static JavascriptExecutor js = null;


	ExtentReports extent;
	ExtentTest test;

	@Before
	public void setUp() {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@After
	public void tearDown() {
		extent.flush(); 
		if (driver != null) {
			driver.quit();
		}
	}



	@Given("I open Chrome browser")
	public void openChromeBrowser() {
		test = extent.createTest("Open Chrome Browser", "Opening the Chrome browser");
		System.out.println("Inside Step - open Chrome browser");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	@When("I open the Vodafone E-shop homepage to Login")
	public void openVodafoneEshop() {
		test = extent.createTest("Open Vodafone E-shop", "Opening the Vodafone E-shop homepage");
		System.out.println("Inside Step - Open Vodafone E-Shop");
		driver.get("https://eshop.vodafone.com.eg/en");


		// Reject cookie consent
		WebElement rejectAllButton = driver.findElement(By.xpath("//*[@id=\"onetrust-reject-all-handler\"]")); 
		js.executeScript("arguments[0].click();", rejectAllButton);


		// Open login menu
		WebElement loginMenuButton = driver.findElement(By.xpath("//*[@id=\"userProfileMenu\"]/button"));
		js.executeScript("arguments[0].click();", loginMenuButton);
	}

	@And("I enter {string} and {string} to login")
	public void login(String number,String password) throws InterruptedException  {
		try {
			test = extent.createTest("Login", "Logging into Vodafone E-shop");
			driver.findElement(By.id("username")).sendKeys(number);
			driver.findElement(By.id("password")).sendKeys(password); 
			Thread.sleep(2000);
			driver.findElement(By.id("submitBtn")).click();
		}catch (InterruptedException e) {
			e.printStackTrace(); 
		}

	}

	@And("I add {string} and {string} to the cart by selecting from the page") 	
	public void addItemsBySelection(String item1,String item2) throws InterruptedException {
		test = extent.createTest("Add Items by Selection", "Adding two items to the cart by selecting from the page");
		System.out.println("Inside Step - add two items to the cart by selecting from the page");

		try {
			WebElement FirstItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='mainText' and contains(text(), '" + item1 + "')]")));
			js.executeScript("arguments[0].click();", FirstItem); 
			WebElement FirstAddToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.add-to-cart")));
			js.executeScript("arguments[0].click();", FirstAddToCartButton);
			Thread.sleep(7000);


			js.executeScript("arguments[0].click();",driver.findElement(By.xpath("/html/body/vf-root/main/section[2]/vf-middleware/vf-product-details-page/div[1]/div/vf-crumbs/div/div[1]/button")));


			WebElement SecondItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='mainText' and contains(text(), '" + item2 + "')]")));
			js.executeScript("arguments[0].click();", SecondItem); 
			WebElement SecondAddtoCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.add-to-cart")));
			js.executeScript("arguments[0].click();", SecondAddtoCartButton);
			Thread.sleep(3000);
		}catch (InterruptedException e) {
			e.printStackTrace(); 
		}


	}


	@And("I search and add {string} to the cart using the search bar")
	public void addItemBySearch(String item3) throws InterruptedException {
		test = extent.createTest("Add Item by Search", "Adding one item to the cart using the search bar");
		System.out.println("Inside Step - add one item to the cart using the search bar");
		try {
			WebElement searchInput = driver.findElement(By.id("searchInput"));
			searchInput.sendKeys(item3);
			searchInput.sendKeys(Keys.ENTER);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='mainText' and contains(text(), '" + item3 + "')]")));
			WebElement ThirdItem = driver.findElement(By.xpath("//p[@id='mainText' and contains(text(), '" + item3 + "')]"));
			js.executeScript("arguments[0].click();", ThirdItem);
			WebElement ThirdAddtoCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.add-to-cart")));
			js.executeScript("arguments[0].click();", ThirdAddtoCartButton);
			Thread.sleep(3000);
		}catch (InterruptedException e) {
			e.printStackTrace(); 
		}


	}

	@Then("I verify that {string} and {string} and {string} are added to the cart and remove them")
	public void verifyCartItems(String item1,String item2,String item3) throws InterruptedException {
		test = extent.createTest("Verify and Remove Cart Items", "Verifying that items are added to the cart and then removing them");
		System.out.println("Inside Step - verify that three items are added to the cart");
		try {
			WebElement cartButton = driver.findElement(By.xpath("/html/body/vf-root/main/section[1]/vf-nav-bar/nav/div/div[3]/vf-cart/div/button"));
			js.executeScript("arguments[0].click();", cartButton);


			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-card']")));


			String removeItem1XPath = String.format(
					"//div[@class='cart-card']//p[contains(text(), '%s')]/ancestor::div[@class='cartCard-details']//div[@class='cart-actions']/p",
					item1
					);
			WebElement removeItem1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(removeItem1XPath)));

			js.executeScript("arguments[0].click();", removeItem1);
			Thread.sleep(3000); 

			wait.until(ExpectedConditions.stalenessOf(removeItem1));


			String removeItem2XPath = String.format(
					"//div[@class='cart-card']//p[contains(text(), '%s')]/ancestor::div[@class='cartCard-details']//div[@class='cart-actions']/p",
					item2
					);
			WebElement removeItem2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(removeItem2XPath)));
			js.executeScript("arguments[0].click();", removeItem2);
			Thread.sleep(3000); 

			wait.until(ExpectedConditions.stalenessOf(removeItem2));


			String removeItem3XPath = String.format(
					"//div[@class='cart-card']//p[contains(text(), '%s')]/ancestor::div[@class='cartCard-details']//div[@class='cart-actions']/p",
					item3
					);
			WebElement removeItem3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(removeItem3XPath)));
			js.executeScript("arguments[0].click();", removeItem3);
			Thread.sleep(3000); 

			wait.until(ExpectedConditions.stalenessOf(removeItem3));

			driver.quit();
		}catch (InterruptedException e) {
			e.printStackTrace(); 
		}
	}
}

