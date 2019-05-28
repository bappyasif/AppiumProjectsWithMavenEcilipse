package androidApplicationTetOnWaitAndScrolling;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AndroidApplicationScrollingAndWaitFunctionality {


	// This will show you Focused App in Your Emulator : 
	// adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'" 

	// More Commands : 	
	// emulator -list-avds
	// emulator -avd @name-of-your-emulator
	// ${ANDROID_SDK}/tools/emulator
	// ${ANDROID_SDK}\tools\bin>uiautomatorviewer.bat

	static AndroidDriver<WebElement> androidDriver;


	public static void main(String[] args) {

		try {

			TestOnScrollingAndWait();

		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println(ex.getMessage());
			System.out.println(ex.getCause());

			ex.getStackTrace();
		} finally {

			androidDriver.quit();
			System.out.println("      Test Completed       ");

		}

	}


	public static void TestOnScrollingAndWait() throws MalformedURLException, InterruptedException {

		// DesiredCapabilities Settings
		DesiredCapabilities dCap = new DesiredCapabilities();

		dCap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android-Emulator");
		dCap.setCapability(MobileCapabilityType.UDID, "emulator-5554");

		dCap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dCap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");

		dCap.setCapability("appPackage", "edu.galileo.android.moviemanager");
		dCap.setCapability("appActivity", "edu.galileo.android.moviemanager.activities.MainActivity");

		// Setting Up Appium Server
		URL appiumURL = new URL("http://0.0.0.0:4723/wd/hub");
		androidDriver = new AndroidDriver<WebElement>(appiumURL, dCap); //Using Android Driver For This 

		Thread.sleep(2000);
		System.out.println("      Application Started       ");

		// Starting Scrolling And Explicit / Implicit Test On WebElements.

		//SecondScrollingMethodOption();

		//androidDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		SecondScrollingMethodOption();	
		WaitFunctionality();		
		
		androidDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		SwipeToBottom();
		WaitFunctionality();
		
		
		try{

			FirstScrollingMethodOption();
			WaitFunctionlityOptionTwo();

		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

		WaitFunctionality();

	}

	// Using TouchAction
	public static void SecondScrollingMethodOption() {

		org.openqa.selenium.Dimension dimensionSize =  androidDriver.manage().window().getSize();

		int heightSize = dimensionSize.getHeight();
		int widthSize = dimensionSize.getWidth();

		int startX = widthSize/2;
		int topY = (int) (heightSize*0.80);
		int bottomY = (int) (heightSize*0.60);

		System.out.println("CoOrdinates: " +startX +" : " +topY +" : " +bottomY);
		// TouchAction Begins

		TouchAction touchAction = new TouchAction(androidDriver);
		touchAction.press(PointOption.point(startX, topY))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
		.moveTo(PointOption.point(startX,bottomY))
		.perform()
		.release();

		// Without WaitOption
		//touchAction.press(PointOption.point(startX, topY)).moveTo(PointOption.point(startX, bottomY)).perform().release();

	}

	// This Might Not Work Cause Now Scrolling Method Is Taken Care By TouchAction.
	public static void FirstScrollingMethodOption() {

		System.out.println("First Scrolling");
		androidDriver.findElement(By.id("edu.galileo.android.moviemanager:id/content_main"));
		AndroidElement moviesList = (AndroidElement) androidDriver.findElement(By.id("edu.galileo.android.moviemanager:id/content_main"));

		MobileElement listScroll = moviesList.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
				+ "new UiSelector().id(4));"));
		listScroll.getLocation();
		listScroll.click();
		System.out.println("First Scrolling Has Ended");
	}


	public static void WaitFunctionality() {

		// First Using Implicit Wait
		androidDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		androidDriver.findElements(By.id("edu.galileo.android.moviemanager:id/ivMovieImage")).get(2).click();
		androidDriver.navigate().back();
		System.out.println("Implicit Wait Done Testing");
	}


	public static void WaitFunctionlityOptionTwo() {

		// Now Lets Try Explicit Wait
		WebDriverWait explicitWait = new WebDriverWait(androidDriver, 2);
		//explicitWait.until(ExpectedConditions.invisibilityOf(androidDriver.findElements(By.id("edu.galileo.android.moviemanager:id/ivMovieImage")).get(3)));
		androidDriver.findElements(By.id("edu.galileo.android.moviemanager:id/ivMovieImage")).get(3).click();
		androidDriver.navigate().back();
		System.out.println("Explicit Wait Done Testing");

	}
	
	
	public static void SwipeToBottom() {
		// TODO Auto-generated method stub
		org.openqa.selenium.Dimension dim = androidDriver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width/2;
		int top_y = (int)(height*0.80);
		int bottom_y = (int)(height*0.20);
		System.out.println("coordinates :" + x + "  "+ top_y + " "+ bottom_y);
		TouchAction ts = new TouchAction(androidDriver);
		ts.press(PointOption.point(x, top_y))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
		.moveTo(PointOption.point(x, bottom_y)).release().perform();
		//ts.press(PointOption.point(x, top_y)).moveTo(x, bottom_y).release().perform();
	}

}
