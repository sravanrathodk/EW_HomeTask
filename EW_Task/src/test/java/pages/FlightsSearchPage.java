package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.WebDriverBase;

public class FlightsSearchPage extends WebDriverBase{
	
	WebElement departureairport, destinationairport, departuredate; 

	
	public WebElement getDepartureairport() {
		return driver.findElement(By.xpath("//input[@aria-label='Departure airport']"));
	}

	public WebElement getDestinationairport() {
		return driver.findElement(By.xpath("//input[@aria-label='Destination airport']"));
	}

	public WebElement getDeparturedate() {
		return driver.findElement(By.xpath("//input[contains(@name,'datepicker_input')]"));
	}
	
}
