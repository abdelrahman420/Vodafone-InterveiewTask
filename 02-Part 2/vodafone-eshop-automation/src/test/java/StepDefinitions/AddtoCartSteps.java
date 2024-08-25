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
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }

    @Given("I open Chrome browser")
    public void openChromeBrowser() {
        test = extent.createTest("Open Chrome Browser", "Opening the Chrome browser");
        System.out.println("Inside Step - open Chrome browser");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @When("I open the Vodafone E-shop homepage to Login")
    public void openVodafoneEshop() {
        test = extent.createTest("Open Vodafone E-shop", "Opening the Vodafone E-shop homepage");
        System.out.println("Inside Step - Open Vodafone E-Shop");
        driver.get("https://eshop.vodafone.com.eg/en");
	    
	// Reject cookie consent
	WebElement rejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler")); 
	js.executeScript("arguments[0].click();", rejectAllButton);

	// Open login menu
	WebElement loginMenuButton = driver.findElement(By.xpath("//*[@id=\"userProfileMenu\"]/button"));
	js.executeScript("arguments[0].click();", loginMenuButton);
	}
    

    @And("I enter {string} and {string} to login")
    public void login(String number, String password) {
        test = extent.createTest("Login", "Logging into Vodafone E-shop");
        System.out.println("Inside Step - Login");
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(number);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitBtn")));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitBtn")));
        submitButton.click();
    }

    @And("I add {string} and {string} to the cart by selecting from the page")
    public void addItemsBySelection(String item1, String item2) {
        test = extent.createTest("Add Items by Selection", "Adding two items to the cart by selecting from the page");
        System.out.println("Inside Step - Add Items by Selection");

        // Select and add the first item
        WebElement firstItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='mainText' and contains(text(), '" + item1 + "')]")));
        js.executeScript("arguments[0].click();", firstItem);
        
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.add-to-cart")));
        WebElement firstAddToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.add-to-cart")));
        js.executeScript("arguments[0].click();", firstAddToCartButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-box-container.system-alert p")));
        
        // Navigate back to the homepage
        WebElement homeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/']")));
        js.executeScript("arguments[0].click();", homeButton);

        // Select and add the second item
        WebElement secondItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='mainText' and contains(text(), '" + item2 + "')]")));
        js.executeScript("arguments[0].click();", secondItem);
        
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.add-to-cart")));
        WebElement secondAddToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.add-to-cart")));
        js.executeScript("arguments[0].click();", secondAddToCartButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-box-container.system-alert p")));
    }

    @And("I search and add {string} to the cart using the search bar")
    public void addItemBySearch(String item3) {
        test = extent.createTest("Add Item by Search", "Adding one item to the cart using the search bar");
        System.out.println("Inside Step - Add Item by Search");

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchInput")));
        searchInput.sendKeys(item3);
        searchInput.sendKeys(Keys.ENTER);

        WebElement searchedItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@id='mainText' and contains(text(), '" + item3 + "')]")));
        js.executeScript("arguments[0].click();", searchedItem);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.add-to-cart")));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.add-to-cart")));
        js.executeScript("arguments[0].click();", addToCartButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-box-container.system-alert p")));
    }

    @Then("I verify that {string} and {string} and {string} are added to the cart and remove them")
    public void verifyCartItems(String item1, String item2, String item3) {
        test = extent.createTest("Verify and Remove Cart Items", "Verifying that items are added to the cart and then removing them");
        System.out.println("Inside Step - Verify and Remove Cart Items");

        // Click on the cart button and wait for the cart page to load
        WebElement cartButton = driver.findElement(By.xpath("/html/body/vf-root/main/section[1]/vf-nav-bar/nav/div/div[3]/vf-cart/div/button"));
	js.executeScript("arguments[0].click();", cartButton);

        // Remove the first item
        WebElement removeCartItem1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='cart-card']//p[contains(text(), '" + item1 + "')]/ancestor::div[@class='cartCard-details']//div[@class='cart-actions']/p")));
        js.executeScript("arguments[0].click();", removeCartItem1);
        wait.until(ExpectedConditions.invisibilityOf(removeCartItem1));
        System.out.println("Item " + item1 + " removed successfully.");
        driver.navigate().refresh();
        
        // Remove the second item
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-card']//p[contains(text(), '" + item2 + "')]/ancestor::div[@class='cartCard-details']//div[@class='cart-actions']/p")));
        WebElement removeCartItem2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='cart-card']//p[contains(text(), '" + item2 + "')]/ancestor::div[@class='cartCard-details']//div[@class='cart-actions']/p")));
        js.executeScript("arguments[0].click();", removeCartItem2);
        wait.until(ExpectedConditions.invisibilityOf(removeCartItem2));
        System.out.println("Item " + item2 + " removed successfully.");
        driver.navigate().refresh();

        // Remove the third item
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-card']//p[contains(text(), '" + item3 + "')]/ancestor::div[@class='cartCard-details']//div[@class='cart-actions']/p")));
        WebElement removeCartItem3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='cart-card']//p[contains(text(), '" + item3 + "')]/ancestor::div[@class='cartCard-details']//div[@class='cart-actions']/p")));
        js.executeScript("arguments[0].click();", removeCartItem3);
        wait.until(ExpectedConditions.invisibilityOf(removeCartItem3));
        System.out.println("Item " + item3 + " removed successfully.");
        
    }
}
