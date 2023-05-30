package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PreparationHome_2 {
	@Test
	public void usersShouldBeAblleToOpenAutotechnotesSite() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.google.com");
		driver.findElement(By.xpath("//span[contains(text(), 'Sign in')]")).click();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("mahchisty@gmail.com");
		driver.findElement(By.xpath("//span[contains(text(), 'Next')]")).click();

		Thread.sleep(3000);
//		driver.quit();

	}
}
