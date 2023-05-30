package home_practice;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PreparationAddContact {
	WebDriverWait wait;
	WebDriver driver;
	// Element Library
	private static final By ADD_CONTACT_LOCATOR = By.linkText("Add Contact");
	private static final By LIST_CONTACT_PAGE_LOCATOR = By.linkText("List Contacts");
	private static final By SEARCH_FIELD_LOCATOR = By.xpath("//input[@placeholder='Search Customers...']");
	private static final By SEARCH_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Search')]");
	private static final String DELETE_BUTTON_XPATH = "//*[text()='%s']/ancestor::tr//a[contains(text(),'Delete')]";
	private static final By OK_BUTTON_LOCATOR = By.xpath("//button[contains(text(), 'OK')]");
	By CONTACT_LOCATOR = By.xpath("//h2[contains(text(), 'Contacts' )]");
	By FULL_NAME_FIELD_LOCATOR = By.id("account");
	By COMPANY_FIELD_LOCATOR = By.id("company");
	By EMAIL_LOCATOR = By.id("email");
	By PHONE_LOCATOR = By.id("phone");
	By ADDRESS_LOCATOR = By.id("address");
	By CITY_LOCATOR = By.id("city");
	By ZIP_LOCATOR = By.id("zip");
	By SUBMIT_BUTTON_LOCATOR = By.id("submit");
	By CHISTY_EMAIL_LOCATOR = By.xpath("//h5[contains(text(), 'chisty@gmail.com')]");
	By DELET_BUTTON_LOCATOR = By.xpath("//*[@id=\"uid2736\" and contains(text (), ' Delete')]");
	By LIST_CONTACT_BUTTON_LOCATOR = By.linkText("List Contacts");
	By ACTUAL_NAME_LOCATOR = By.xpath("//span[@class=\"thumb-info-inner\"]");

	@Test
	public void usersShouldBeAbleToAddAndDeleteContact() throws InterruptedException {
		// Setup
		System.setProperty("webdriver.chrome.driver", "/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get("https://techfios.com/test/billing/?ng=admin/");
		driver.findElement(By.id("username")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.id("password")).sendKeys("abc123");
		driver.findElement(By.name("login")).click();

		driver.findElement(By.xpath("//*[contains(text (), 'CRM' )]")).click();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_CONTACT_LOCATOR));
		driver.findElement(ADD_CONTACT_LOCATOR).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_LOCATOR));
		driver.findElement(CONTACT_LOCATOR).isDisplayed();
		Assert.assertTrue("Page didn't open! Test failed!!", driver.findElement(CONTACT_LOCATOR).isDisplayed());

		Random rnd = new Random();
		String name = "John" + rnd.nextInt(999);
		System.out.println("Name :" + name);

		type(FULL_NAME_FIELD_LOCATOR, name);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FULL_NAME_FIELD_LOCATOR));
//		driver.findElement(FULL_NAME_FIELD_LOCATOR).sendKeys(name);
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(COMPANY_FIELD_LOCATOR));
//		driver.findElement(COMPANY_FIELD_LOCATOR).sendKeys("QA");
		type(COMPANY_FIELD_LOCATOR, "QA");

		wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_LOCATOR));
		driver.findElement(EMAIL_LOCATOR).sendKeys(name + "@gmail.com");

		wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_LOCATOR));
		driver.findElement(PHONE_LOCATOR).sendKeys("123 456 7890");

		type(ADDRESS_LOCATOR, "West Minster Dr");

		wait.until(ExpectedConditions.visibilityOfElementLocated(CITY_LOCATOR));
		driver.findElement(CITY_LOCATOR).sendKeys("Houston");

//		wait.until(ExpectedConditions.visibilityOfElementLocated(ZIP_LOCATOR));
//		driver.findElement(ZIP_LOCATOR).sendKeys("77084");

		waitForElement(driver, 30, ZIP_LOCATOR);
		driver.findElement(ZIP_LOCATOR).sendKeys("77084");

		waitForElement(driver, 30, By.id("country"));
		By countryDropDownLocator = By.id("country");
		WebElement countryDropDownElement = driver.findElement(countryDropDownLocator);
		Select select = new Select(countryDropDownElement);
		select.selectByVisibleText("Angola");

		waitForElement(driver, 30, SUBMIT_BUTTON_LOCATOR);
		driver.findElement(SUBMIT_BUTTON_LOCATOR).click();

		waitForElement(driver, 30, By.linkText("Post"));
		driver.findElement(By.linkText("Post")).isDisplayed();
//		Assert.assertTrue("Test failed!", driver.findElement(CHISTY_EMAIL_LOCATOR).isDisplayed());
		WebElement ACTUAL_NAME_ELEMENT = driver.findElement(ACTUAL_NAME_LOCATOR);
		ACTUAL_NAME_ELEMENT.getText();
		Assert.assertEquals("Contact was not found!", name, ACTUAL_NAME_ELEMENT.getText());
		
		//Delete and validation deletion
		//Go to List contact page
		driver.findElement(LIST_CONTACT_PAGE_LOCATOR).click();
		type(SEARCH_FIELD_LOCATOR, name);
		driver.findElement(SEARCH_BUTTON_LOCATOR).click();
		
		String dymanicDeletXpath = String.format(DELETE_BUTTON_XPATH, name);
		
		driver.findElement(By.xpath(dymanicDeletXpath)).click();
		driver.findElement(OK_BUTTON_LOCATOR).click(); 
		
		type(SEARCH_FIELD_LOCATOR, name);
		driver.findElement(SEARCH_BUTTON_LOCATOR).click();
		Assert.assertFalse("Name was not deleted!", driver.getPageSource().contains(name));
	}	
	
	public void type(By by, String text) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		driver.findElement(by).sendKeys(text);
	}

	private void waitForElement(WebDriver driver, int maxTime, By by) {
		WebDriverWait wait = new WebDriverWait(driver, maxTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	@AfterMethod
	public void close() {
		driver.quit();
	}
	
}
