package androidApplicationHandlingVerticalScroll;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AndroidVericalScrollingAnotherAttempt {

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

			AndroidVerticalScrollHandlingTest();

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


	public static void AndroidVerticalScrollHandlingTest() throws MalformedURLException, InterruptedException {

		// DesiredCapabilities Settings
		DesiredCapabilities dCap = new DesiredCapabilities();

		dCap.setCapability("deviceName", "Android-Emulator");
		dCap.setCapability("udID", "emulator-5554");

		dCap.setCapability("platformName", "Android");
		dCap.setCapability("platformVersion", "7.1.1");

		dCap.setCapability("appPackage", "com.example.android.miwok");
		dCap.setCapability("appActivity", "com.example.android.miwok.MainActivity");

		// Setting Up Appium Server
		URL appiumURL = new URL("http://0.0.0.0:4723/wd/hub");
		androidDriver = new AndroidDriver<WebElement>(appiumURL, dCap); //Using Android Driver For This 

		Thread.sleep(2000);
		System.out.println("      Application Started       ");

		// Starting Vertical Scroll Test
		// Lets Begin With Numbers
		androidDriver.findElementsByClassName("android.widget.TextView").get(13).click();
		Thread.sleep(2000);
		SwipeToBottom();
		androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.ListView/android.widget.RelativeLayout[7]/android.widget.LinearLayout/android.widget.TextView[2]\r\n" + 
				"")).click(); // Selects Ten
		androidDriver.findElementsByClassName("android.widget.TextView").get(17).click(); // Selects Ten
		//androidDriver.findElements(By.className("android.widget.TextView")).get(17).click();

		// Lets Begin With Family
		androidDriver.findElementsByClassName("android.widget.TextView").get(2).click();
		androidDriver.findElementsByClassName("android.widget.TextView").get(10).click();
		Thread.sleep(2000);
		SwipeToBottom();
		androidDriver.findElementsByClassName("android.widget.TextView").get(16).click();
		Thread.sleep(2000);
		androidDriver.findElementsByClassName("android.widget.TextView").get(17).click();

		// Lets Begin With Colors
		androidDriver.findElementsByClassName("android.widget.TextView").get(3).click();
		androidDriver.findElementsByClassName("android.widget.TextView").get(10).click();
		Thread.sleep(2000);
		SwipeToBottom();
		androidDriver.findElementsByClassName("android.widget.TextView").get(16).click();
		Thread.sleep(2000);
		androidDriver.findElementsByClassName("android.widget.TextView").get(17).click();

		// Lets Begin With Phrases
		androidDriver.findElementsByClassName("android.widget.TextView").get(4).click();
		Thread.sleep(2000);
		androidDriver.findElementsByClassName("android.widget.TextView").get(14).click();
		SwipeToBottom();
		androidDriver.findElementsByClassName("android.widget.TextView").get(16).click();


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


/**
 * 
 * 
 
 
		//androidDriver.findElementByClassName("android.widget.TextView").findElement(By.name("ten")).click(); //Locator Strategy 'name' is not supported for this session
		// com.example.android.miwok:id/default_text_view
		//androidDriver.findElements(By.id("com.example.android.miwok:id/default_text_view")).get(23).click(); // As this Element is not visible in splash screen, it does not get clicked.
		//androidDriver.findElementsByClassName("android.widget.TextView").get(13).click();

  		// Using instance() method
		MobileElement element = (MobileElement) androidDriver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.example.android.miwok:id/default_text_view\")).scrollIntoView("
						+ "new UiSelector().text(\"na’aacha\").instance(23))"));

		element.click();
		System.out.println(element);

		// UIScrollable getChildByText()
		MobileElement anotherElement = (MobileElement) androidDriver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.example.android.miwok:id/miwok_text_view\")).getChildByText("
						+ "new UiSelector().className(\"android.widget.TextView\"), \"na’aacha\")"));

		anotherElement.click();
		System.out.println(anotherElement);

		// TouchAction Method
		WebElement scrolElement = androidDriver.findElement(By.className("android.widget.TextView[@text = 'ten']"));
		TouchActions action = new TouchActions(androidDriver);
		action.scroll(scrolElement, 10, 100);
		action.perform();


		// Java Script Solution To Scroll In General
		JavascriptExecutor jScript = (JavascriptExecutor) androidDriver;
		HashMap<String, String> scrobJect = new HashMap<String, String>();
		scrobJect.put("direction", "down");
		//jScript.executeScript("mobile: scroll", ImmutableMap.of("direction", "down"));
		jScript.executeScript("mobile: scroll", scrobJect.put("direction", "down"));





 */
