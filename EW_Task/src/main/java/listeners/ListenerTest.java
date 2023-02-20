package listeners;

import org.testng.ITestListener;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import base.WebDriverBase;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;

public class ListenerTest extends WebDriverBase implements ITestListener{

	

   
    public void onTestFailure(ITestResult arg0) {					
		/*
		 * Screenshot screenshot= new
		 * AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).
		 * takeScreenshot(driver); try { ImageIO.write(screenshot.getImage(), "jpg", new
		 * File(System.getProperty("user.dir")+"/Screenshots/Failed.jpg")); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
    	 
    }		


    public void onTestSuccess(ITestResult arg0) {					
    	
		/*
		 * Screenshot screenshot= new
		 * AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).
		 * takeScreenshot(driver); try { ImageIO.write(screenshot.getImage(), "jpg", new
		 * File(System.getProperty("user.dir")+"/Screenshots/Passed.jpg")); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
    }


	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}


	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}


	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}


	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}
