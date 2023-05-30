package home_practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class PreparationDeletContact {

	WebDriver driver;
//	Element library
	By CRM_BUTTON_LOCATOR = By.xpath("//*[contains(text(), 'CRM')]");
	By LIST_CONTACT_BUTTON_LOCATOR = By.linkText("List Contacts");
	By DELET_BUTTON_LOCATOR = By.xpath("//*[@id=\"uid2759\"]");
	By OK_BUTTON_LOCATOR = By.xpath("//*[contains(text(), 'OK')]");

	@Test
	public void userShouldDeletContact() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://techfios.com/test/billing/?ng=login/");
		driver.findElement(By.id("username")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.id("password")).sendKeys("abc123");
		driver.findElement(By.name("login")).click();
//		driver.manage().deleteAllCookies();
//		driver.manage().window().fullscreen();
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
//		driver.findElement(null);
		waitForWebElement(driver, 10, CRM_BUTTON_LOCATOR);
		driver.findElement(CRM_BUTTON_LOCATOR).click();

		waitForWebElement(driver, 10, LIST_CONTACT_BUTTON_LOCATOR);
		driver.findElement(LIST_CONTACT_BUTTON_LOCATOR).click();
		
		waitForWebElement(driver, 10, DELET_BUTTON_LOCATOR);
		driver.findElement(DELET_BUTTON_LOCATOR).click();
		
		waitForWebElement(driver, 10, OK_BUTTON_LOCATOR);
		driver.findElement(OK_BUTTON_LOCATOR).click();

//		Thread.sleep(3000);
//		driver.quit();
	}

	private void waitForWebElement(WebDriver driver, int maxTime, By by) {
		WebDriverWait wait = new WebDriverWait(driver, maxTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

}
