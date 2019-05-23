package androidApplicationHandlingVerticalScroll;

import java.awt.Point;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class HandlingVerticalScrollingExampleTest {

	// This will show you Focused App in Your Emulator : 
	// adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'" 

	// More Commands : 	
	// emulator -list-avds
	// emulator -avd @name-of-your-emulator
	// ${ANDROID_SDK}/tools/emulator
	// ${ANDROID_SDK}\tools\bin>uiautomatorviewer.bat

	static AndroidDriver<WebElement> androidDriver;

	//static AppiumDriver<MobileElement> appiumDriver;

	public static void main(String[] args) {

		try {

			VerticalScroling();

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


	public static void VerticalScroling() throws MalformedURLException, InterruptedException {

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

		// Attention Notes: 

		// Unfortunately Miwok App has No Unique Identifier Locator available at this time...
		// Lets carry on with another one of built Application Tutorial Called Sunshine. 
		// Nope, Don't Bother Got It Working After All. 

		// When Resource ID's are same for all the Elements in an Application then find that Element
		// By ID and then Use GET Method to point to intended Element that we seek.

		// com.example.android.miwok:id/default_text_view
		// com.example.android.miwok:id/default_text_view
		// com.example.android.miwok:id/miwok_text_view
		// com.example.android.miwok:id/miwok_text_view
		// This case Our Index Is Also Same so which means we need to address them through their text

		//androidDriver.findElementById("com.example.android.miwok:id/default_text_view").findElement(By.linkText("two")).click();
		//androidDriver.findElement(By.tagName("two")).click();
		//androidDriver.findElement(By.id("com.example.android.miwok:id/default_text_view")).findElement(By.name("two")).click();
		//androidDriver.findElement(By.name("two")).click();
		//androidDriver.findElementByName("two").click();

		//appiumDriver.findElement(By.id("7")).click();
		//appiumDriver.findElement(By.id("com.example.android.miwok:id/default_text_view")).findElement(By.id("7")).click();
		//appiumDriver.findElement(By.id("com.example.android.miwok:id/default_text_view")).click();
		//appiumDriver.findElement(By.id("com.example.android.miwok:id/default_text_view")).findElementByAccessibilityId("7").click();
		//appiumDriver.findElementByClassName("android.widget.TextView").findElementByPartialLinkText("two").click();

		//appiumDriver.findElementByClassName("android.view.ViewGroup").findElementById("2").click();
		//appiumDriver.findElements(By.className("android.view.ViewGroup")).get(2).click();

		// android.widget.LinearLayout
		//appiumDriver.findElementsByClassName("android.widget.TextView").contains("two");
		//appiumDriver.findElementByClassName("android.widget.TextView").findElementByName("two").click();

		androidDriver.findElementsByClassName("android.widget.TextView").get(13).click();
		//androidDriver.findElementsByClassName("android.widget.TextView").get(23).click(); // This need Scrolling to locate that element.
		WebElement scrollElement = androidDriver.findElementsByClassName("android.widget.TextView").get(23);
		// Lets create an list element
		AndroidElement list = (AndroidElement) androidDriver.findElementsByClassName("android.widget.TextView");

		//TouchActions action = new TouchActions(androidDriver);
		//action.scroll(scrollElement, 10, 100);
		//action.perform();

		//scrollElement.click();

		// Using instance() method
		MobileElement element = (MobileElement) androidDriver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.android.vending:id/data_view\")).scrollIntoView("
						+ "new UiSelector().textContains(\"ten\").instance(23))"));

		// UIScrollable getChildByText()
		MobileElement anotherElement = (MobileElement) androidDriver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.android.vending:id/tab_recycler_view\")).getChildByText("
						+ "new UiSelector().className(\"android.widget.TextView\"), \"Games We Are Playing\")"));

		//Perform the action on the element
		anotherElement.click();

		// new TouchAction(driver).press(PointOption.point(550, 640)).waitAction().moveTo(PointOption.point(550, 60)).release().perform();

		// Java Script Solution To Scroll To an Element
		JavascriptExecutor js = (JavascriptExecutor) androidDriver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		scrollObject.put("element", ((RemoteWebElement) element).getId());
		js.executeScript("mobile: scroll", scrollObject);

		// Java Script Solution To Scroll In General
		JavascriptExecutor jScript = (JavascriptExecutor) androidDriver;
		HashMap<String, String> scrobJect = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrobJect);

		// This another approach to Solve This conundrum
		MobileElement topCharts = (MobileElement) androidDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text = 'Top Charts']"));
		org.openqa.selenium.Point point = topCharts.getLocation();
		int startY = point.y;
		int endY = point.y;

		int startX = (int) ((androidDriver.manage().window().getSize().getWidth()) * 0.80);
		int endX = (int) ((androidDriver.manage().window().getSize().getWidth()) * 0.20);

		TouchAction actions = new TouchAction(androidDriver);
		//actions.press(startX, startY).waitAction(Duration.ofSeconds(2)).moveTo(endX, endY).release().perform();

		// Another Approach But Change Accordingly
		MobileElement elementMobile = (MobileElement) androidDriver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.android.vending:id/items\")).setAsHorizontalList().scrollIntoView("
						+ "new UiSelector().descriptionContains(\"Family\"))"));


		// Another Approach Identify Elememt using Text
		MobileElement elementSelect = (MobileElement) androidDriver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.android.vending:id/data_view\")).scrollIntoView("
						+ "new UiSelector().text(\"Unblock Me FREE\"))"));

		//OR

		//Identify Element using Content Description
		MobileElement elementScroll = (MobileElement) androidDriver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.android.vending:id/data_view\")).scrollIntoView("
						+ "new UiSelector().description(\"App: Unblock Me FREE\"))"));

		//Perform the action on the element
		System.out.println(element.getLocation());


		//Perform the action on the element
		System.out.println(element.getAttribute("id"));

		// Scrolling Action
		//MobileElement listItems = (MobileElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())."
		//		+ "scrollIntoView(new UiSelector().id(23))"));

		//MobileElement listItem = (MobileElement) androidDriver.findElement(MobileBy.AndroidUIAutomator(null));
		// Getting Locater Position Found
		//System.out.println("Location Found : " +listItems.getLocation());

		// Clicking Locator
		//listItems.click();

		Thread.sleep(2000);
	}

}
