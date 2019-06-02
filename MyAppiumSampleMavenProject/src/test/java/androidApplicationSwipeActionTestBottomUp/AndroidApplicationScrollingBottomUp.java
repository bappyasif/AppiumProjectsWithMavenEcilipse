package androidApplicationSwipeActionTestBottomUp;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AndroidApplicationScrollingBottomUp {

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

			TestOnBottomUpScrolling();

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


	public  static void TestOnBottomUpScrolling() throws Exception {

		// DesiredCapabilities Settings
		DesiredCapabilities dCap = new DesiredCapabilities();

		dCap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android-Emulator");
		dCap.setCapability(MobileCapabilityType.UDID, "emulator-5554");

		dCap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dCap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");

		//dCap.setCapability("appPackage", "com.example.android.teatime");
		//dCap.setCapability("appActivity", "com.example.android.teatime.MenuActivity");
		
		//dCap.setCapability("appPackage", "com.android.contacts");
		//dCap.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
		
		dCap.setCapability("appPackage", "edu.galileo.android.moviemanager");
		dCap.setCapability("appActivity", "edu.galileo.android.moviemanager.activities.MainActivity");
		
		// Setting Up Appium Server
		URL appiumURL = new URL("http://0.0.0.0:4723/wd/hub");
		androidDriver = new AndroidDriver<WebElement>(appiumURL, dCap);
		Thread.sleep(2000);
		System.out.println("      Application Started       ");

		// Test Begins
		//jsScrollDown();
		//ScrollDown();
		scrollDown(); // Work On This For Scrolling. New Preferred Method For Scrolling.
		//SwipeToBottom();
		//SwipeFromBottomToUp();
		//SwipeFromBottomToTop();

	}
	
	
	private static void scrollDown() {
	    //if pressX was zero it didn't work for me
	    int pressX = androidDriver.manage().window().getSize().width / 2;
	    // 4/5 of the screen as the bottom finger-press point
	    int bottomY = androidDriver.manage().window().getSize().height * 4/5;
	    // just non zero point, as it didn't scroll to zero normally
	    int topY = androidDriver.manage().window().getSize().height / 8;
	    //scroll with TouchAction by itself
	    scroll(pressX, bottomY, pressX, topY);
	}

	/*
	 * don't forget that it's "natural scroll" where 
	 * fromY is the point where you press the and toY where you release it
	 */
	private static void scroll(int fromX, int fromY, int toX, int toY) {
	    TouchAction touchAction = new TouchAction(androidDriver);
	    touchAction.longPress(PointOption.point(fromX, fromY)).moveTo(PointOption.point(toX, toY)).release().perform();
	}
	
	
	public static void jsScrollDown() throws Exception {

	    JavascriptExecutor js = (JavascriptExecutor) androidDriver;
	    HashMap<String, String> scrollObject = new HashMap<String, String>();
	    scrollObject.put("direction", "down");
	    js.executeScript("mobile: scroll", scrollObject);

	}
	
	
	public static void ScrollDown() throws Exception {

	    //The viewing size of the device
	    org.openqa.selenium.Dimension size = androidDriver.manage().window().getSize();

	    //Starting y location set to 80% of the height (near bottom)
	    int starty = (int) (size.height * 0.80);
	    //Ending y location set to 20% of the height (near top)
	    int endy = (int) (size.height * 0.20);
	    //x position set to mid-screen horizontally
	    int startx = size.width / 2;

	    new TouchActions(androidDriver)
	            .down(startx, endy)
	            .move(startx, starty)
	            .release()
	            .build()
	            .perform();

	}


	public static void SwipeFromBottomToTop() {

		org.openqa.selenium.Dimension dimension = androidDriver.manage().window().getSize();

		System.out.println("Mobilee Window Screen Size : " +dimension);

		// Specifying X , Y , Coordinates
		int startY = (int) (dimension.height*0.50);
		int endY = (int) (dimension.height*0.20);

		int startX = dimension.width/2;

		// Commencing Swipe
		TouchAction touchAction = new TouchAction(androidDriver);
		touchAction.press(PointOption.point(startX, endY))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
		.moveTo(PointOption.point(startX, startY))
		.perform()
		.release();

	}
	
	
	public static void SwipeToBottom() {
		// TODO Auto-generated method stub
		org.openqa.selenium.Dimension dim = androidDriver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width/2;
		int top_y = (int)(height*0.80);
		int bottom_y = (int)(height*0.20);
		//System.out.println("coordinates :" + x + "  "+ top_y + " "+ bottom_y);
		TouchAction ts = new TouchAction(androidDriver);
		
		ts.press(PointOption.point(x, top_y))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
		.moveTo(PointOption.point(x, bottom_y)).release().perform();
		
		//ts.press(PointOption.point(x, top_y)).moveTo(PointOption.point(x, bottom_y)).release().perform();
	}
	
	
	public static void SwipeFromBottomToUp() {
		// TODO Auto-generated method stub
		org.openqa.selenium.Dimension dim = androidDriver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width/2;
		int top_y = (int)(height*0.80);
		int bottom_y = (int)(height*0.20);
		//System.out.println("coordinates :" + x + "  "+ top_y + " "+ bottom_y);
		TouchAction ts = new TouchAction(androidDriver);
		ts.press(PointOption.point(x, top_y))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
		.moveTo(PointOption.point(x, bottom_y)).release().perform();
		//ts.press(PointOption.point(x, top_y)).moveTo(x, bottom_y).release().perform();
	}
	
	
	public static void SwipeFromLeftToRight() {

		org.openqa.selenium.Dimension dimension = androidDriver.manage().window().getSize();

		System.out.println("Mobilee Window Screen Size : " +dimension);

		// Specifying X , Y , Coordinates
		int startX = (int) (dimension.width*0.50);
		int endX = (int) (dimension.width*0.20);

		int startY = dimension.height/2;

		// Commencing Swipe
		TouchAction touchAction = new TouchAction(androidDriver);
		touchAction.press(PointOption.point(endX, startY))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
		.moveTo(PointOption.point(startX, startY))
		.perform()
		.release();

	}
	
	
	public static void SwipeFromRightToLeft() {

		org.openqa.selenium.Dimension dimension = androidDriver.manage().window().getSize();

		System.out.println("Mobilee Window Screen Size : " +dimension);

		// Specifying X , Y , Coordinates
		int startX = (int) (dimension.width*0.50);
		int endX = (int) (dimension.width*0.20);

		int startY = dimension.height/2;

		// Commencing Swipe
		TouchAction touchAction = new TouchAction(androidDriver);
		touchAction.press(PointOption.point(startX, startY))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
		.moveTo(PointOption.point(endX, startY))
		.perform()
		.release();

	}

}
