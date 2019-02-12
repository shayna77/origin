package com.test.base_project;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class NewTest {
	private WebDriver driver;
	private String email = "md7343@gmail.com";
	private String password = "wishtrip77";
	private String url = "https://www.wishtrip.com/";
	private String expectedTitle = "WishTrip - My Wish-List";

	@Test
	public void wishTripScenario() {
		login();
		goToMyWishListPage();
		verifyTitle(expectedTitle);
		goToHomePage();
	}

	private void goToHomePage() {
		homeButton.click();
		System.out.println("Back in Home page");
	}

	private void verifyTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("Expected title is " + expectedTitle + " and actual title is " + actualTitle);
	}

	private void goToMyWishListPage() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		List<WebElement> titleTabs = wait.until(
		ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class='valign toggle-fade']")));
		WebElement heartButton = titleTabs.get(2);
		wait.until(ExpectedConditions.elementToBeClickable(heartButton));
		heartButton.click();
		System.out.println("In My WishList page");
	}

	private void login() {
		loginButton.click();
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		submitButton.get(0).click();
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	@FindBy(how = How.CSS, using = "[class='white-text modal-trigger page-navbar__btn']")
	private WebElement loginButton;
	@FindBy(how = How.ID, using = "login-email")
	private WebElement emailField;
	@FindBy(how = How.ID, using = "login-password")
	private WebElement passwordField;
	@FindBy(how = How.CSS, using = "a.waves-effect.waves-accent-color")
	private List<WebElement> submitButton;
	@FindBy(how = How.CSS, using = "[ng-href='home']")
	private WebElement homeButton;
}
