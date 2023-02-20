package tests;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.WebDriverBase;
import pages.FlightsSearchPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

@Listeners(listeners.ListenerTest.class)
public class SearchFlights extends WebDriverBase {

	FlightsSearchPage fsp;

	// This test is used to search flights information using flight route and pick a random flights if found 
	// and search it from the flight number option, also check if the flight is arrived or not.
	@Test
	public void searchFlightsAndVerifyWithFlightNumber() throws InterruptedException, IOException {
		fsp = new FlightsSearchPage();
		driver.findElement(By.xpath("//span[text()='Departure airport']//ancestor::button")).click();
		Thread.sleep(5000);
		fsp.getDepartureairport().sendKeys("CGN");
		fsp.getDepartureairport().sendKeys(Keys.ENTER);

		driver.findElement(By.xpath("//span[text()='Destination airport']//ancestor::button")).click();
		Thread.sleep(5000);
		fsp.getDestinationairport().sendKeys("BER");
		fsp.getDestinationairport().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		fsp.getDeparturedate().click();

		LocalDate currentdate = LocalDate.now();
		int Day = currentdate.getDayOfMonth();
		Day = Day-1;
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='" + Day + "']//preceding-sibling::input")).click();
		driver.findElement(By.xpath("//span[text()='Show flight status']//ancestor::button")).click();
		
		int noOfFlights = driver.findElements(By.cssSelector(".o-search-flight-status__card-flight-number")).size();
		boolean flag = true;
		List<WebElement> flights;
		String[] flightss = null;
		List<String> flightNames = new ArrayList<String>();
		if (noOfFlights > 0) {
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(), "jpg",
					new File(System.getProperty("user.dir")+"/Screenshots/FlightsAndVerifyWithFlightNumber.jpg"));
			flights = driver.findElements(By.cssSelector(".o-search-flight-status__card-flight-number p"));
			for(WebElement flight:flights)
				{
				flightNames.add(flight.getText());
				}
			
			String fName = flightNames.get(0);
			
			
			driver.findElement(By.xpath("(//input[@name='search-method'])[2]")).click();
			driver.findElement(By.xpath("//input[@name='flightNumber']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@name='flightNumber']")).sendKeys(fName);
			fsp.getDeparturedate().click();
			
			int TDay = currentdate.getDayOfMonth();
			TDay = TDay-1;
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//span[text()='" + TDay + "']//preceding-sibling::input")).click();
			driver.findElement(By.xpath("//span[text()='Show flight status']//ancestor::button")).click();

			if(driver.findElement(By.cssSelector(".o-search-flight-status__card-flight-status.arrived")).isDisplayed())
			{
				Screenshot screenshot1 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
						.takeScreenshot(driver);
				ImageIO.write(screenshot1.getImage(), "jpg",
						new File(System.getProperty("user.dir")+"/Screenshots/FlightsArrived.jpg"));
			}
			
		}
		
	}
	
	@Test
	public void searchFlightWithTodayDateTest() throws IOException, InterruptedException {
		fsp = new FlightsSearchPage();

		driver.findElement(By.xpath("//span[text()='Departure airport']//ancestor::button")).click();
		Thread.sleep(5000);
		fsp.getDepartureairport().sendKeys("CGN");
		fsp.getDepartureairport().sendKeys(Keys.ENTER);

		driver.findElement(By.xpath("//span[text()='Destination airport']//ancestor::button")).click();
		Thread.sleep(5000);
		fsp.getDestinationairport().sendKeys("BER");
		fsp.getDestinationairport().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		fsp.getDeparturedate().click();

		LocalDate currentdate = LocalDate.now();
		int Day = currentdate.getDayOfMonth();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='" + Day + "']//preceding-sibling::input")).click();
		driver.findElement(By.xpath("//span[text()='Show flight status']//ancestor::button")).click();

		int noofflights = driver.findElements(By.cssSelector(".o-search-flight-status__card")).size();
		boolean flag = true;
		if (noofflights == 0) {
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(), "jpg",
					new File(System.getProperty("user.dir")+"/Screenshots/FlightsTodayButNoFlightInformation.jpg"));
			flag = false;
		}
		else
		{
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(), "jpg",
					new File(System.getProperty("user.dir")+"/Screenshots/FlightsToday_FlightInformation.jpg"));
		}
		Assert.assertTrue(flag);

	}

	@Test
	public void searchFlightWithTomorrowDateTest() throws IOException, InterruptedException {
		fsp = new FlightsSearchPage();
		driver.findElement(By.xpath("//span[text()='Departure airport']//ancestor::button")).click();
		Thread.sleep(5000);
		fsp.getDepartureairport().sendKeys("CGN");
		fsp.getDepartureairport().sendKeys(Keys.ENTER);

		driver.findElement(By.xpath("//span[text()='Destination airport']//ancestor::button")).click();
		Thread.sleep(5000);
		fsp.getDestinationairport().sendKeys("BER");
		fsp.getDestinationairport().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		fsp.getDeparturedate().click();

		LocalDate currentdate = LocalDate.now();
		int Day = currentdate.getDayOfMonth();
		int TDay = Day + 1;
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='" + TDay + "']//preceding-sibling::input")).click();
		driver.findElement(By.xpath("//span[text()='Show flight status']//ancestor::button")).click();

		int noofflights = driver.findElements(By.cssSelector(".o-search-flight-status__card")).size();
		boolean flag = true;
		if (noofflights == 0) {
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(), "jpg",
					new File(System.getProperty("user.dir")+"/Screenshots/FlightsTomorrowButNoFlightInformation.jpg"));
			flag = false;
		}
		else
		{
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(), "jpg",
					new File(System.getProperty("user.dir")+"/Screenshots/FlightsTomorrow_FlightInformation.jpg"));
		}
		Assert.assertTrue(flag);

	}

	@Test
	public void searchWithFlightNumber() throws InterruptedException, IOException {
		fsp = new FlightsSearchPage();
		driver.findElement(By.xpath("(//input[@name='search-method'])[2]")).click();
		driver.findElement(By.xpath("//input[@name='flightNumber']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='flightNumber']")).sendKeys("EW-8055");
		fsp.getDeparturedate().click();
		LocalDate currentdate = LocalDate.now();
		int Day = currentdate.getDayOfMonth();
		int TDay = Day - 2;
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='" + TDay + "']//preceding-sibling::input")).click();
		driver.findElement(By.xpath("//span[text()='Show flight status']//ancestor::button")).click();

		int noofflights = driver.findElements(By.cssSelector(".o-search-flight-status__card")).size();
		boolean flag = true;
		if (noofflights == 0) {
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(), "jpg",
					new File(System.getProperty("user.dir")+"/Screenshots/NoFlights.jpg"));
			flag = false;
			Assert.assertTrue(flag);
		} else {
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(), "jpg",
					new File(System.getProperty("user.dir")+"/Screenshots/FlightsArrived.jpg"));
		}

	}
}
