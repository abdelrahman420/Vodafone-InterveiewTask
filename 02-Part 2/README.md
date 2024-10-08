# Vodafone E-shop Automation

## Table of Contents
1. [Overview and Requirements](#overview-and-requirements)
2. [Prerequisites](#prerequisites)
3. [Steps to Automate The E-Shop](#steps-to-automate-the-e-shop)
   - [Create New Maven Project](#create-new-maven-project)
   - [Add Dependencies](#add-dependencies)
   - [Add Feature File and Write Scenario](#add-feature-file-and-write-scenario)
   - [Implement Step Definitions](#implement-step-definitions)
   - [Implement the Code Runner](#implement-the-code-runner)
   - [Test Your Project](#test-your-project)


## Overview and Requiremnets
The project automates the process of interacting with Vodafone E-shop 				

Requirements for this project:
- 1 - Open eshop.vodafone.com.eg/shop/home 
- 2 - Select 3 items and add it to the cart (2 to be selected and 1 from the search bar) 
- 3 - Don’t proceed with the order
- 4 - Make sure you are using BDD and data-driven framework 
- 5 - Make sure to generate report using Extent report or Allure reports.
- 6 - Use maven tool for ease of running

## Prerequisites

- 1 - Ensure JDK 11 or higher is installed on your machine.
- 2 - Maven is required for project build and dependencies management.
- 3 - install aproper IDE that supports java development (Iam using Eclipse IDE)

## Steps to Automate The E-Shop
### 1 - Create new maven Project

- 1 - in Eclipse click on file then new then select maven project.
 
- 2 - Click on "Create a simple project(skip archetype selection)" to check it then click next. 

- 3 - Type the name of the project and click finish.


## 2 - Add Dependencies needed for this project in the `pom.xml` file

we will need these dependencies
- 1 - Selenium WebDriver
- 2 - Cucumber
- 3 - Extent Reports 
- 4 - Junit

you can find these at https://mvnrepository.com/ and search for required dependencies.

```xml
<dependencies>
<!--  https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java  -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.23.1</version>
</dependency>

<!--  https://mvnrepository.com/artifact/io.cucumber/cucumber-java  -->
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>7.18.1</version>
</dependency>

<!--  https://mvnrepository.com/artifact/io.cucumber/cucumber-junit  -->
<dependency>
<groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit</artifactId>
    <version>7.18.1</version>
    <scope>test</scope>
</dependency>

<!--  https://mvnrepository.com/artifact/junit/junit  -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>com.aventstack</groupId>
    <artifactId>extentreports</artifactId>
<version>5.0.9</version>
<!--  Check for the latest version  -->
</dependency>

</dependencies>

```
- after adding these dependencies to the `pom.xml` file and save it the Eclipse IDE will download them automatically

## 3 - Add our feature file and Write the scenario
- 1 - under the `src/main/resources` folder make a new folder and name it  `Features`
- 2 - under the `Features` folder create a new file called `AddtoCart.features`

```Gherkin
Feature: Add items to cart

  Scenario Outline: Add two items by selection and one from search bar to the cart
    Given I open Chrome browser
    When I open the Vodafone E-shop homepage to Login
    And I enter <number> and <password> to login
    And I add <item1> and <item2> to the cart by selecting from the page
    And I search and add <item3> to the cart using the search bar
    Then I verify that <item1> and <item2> and <item3> are added to the cart and remove them

    Examples: 
      | item1                | item2                            | item3                | number       | password       |
      | "Samsung Galaxy A25" | "iPhone 15"                      | "iPhone 13 128GB"    | #Your_Number | #Your_Password |
      | "JBL Charge 5"       | "Lenovo IdeaPad 5 Laptop - 11th" | "Redmi Smart Band 2" | #Your_Number | #Your_Password |

```
- remove `Your_Number` and `Your_Password` from the feature file and add the actual number and password to login to vodafine e-shop

## 4  - Then Implement Step Definitions

- 1 - under the `src/test/java` folder creae a package named `StepDefinitions`
- 2 - create a new class under this package named `AddtoCartSteps.java`

```java
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
```

## 5 - Implement the code runner
 - create a new class under `StepDefinitions` package named `AddtoCartRunner.java`

```java
package StepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/Features", 
    glue = {"StepDefinitions"},
    monochrome = true
)
public class AddtoCartRunner {
    
}
```
## 6 - Test your project
- 1 - inside therunner file right click and choose `Run As` then select `JUnit Test`

- 2 - when the test is finished the extentreport is generated under the `target` folder write click on the file choose `Open With` and select `Web Browser`
	

	
