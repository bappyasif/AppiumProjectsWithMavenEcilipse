package androidAppHandlingDragAndDrop;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;

public class DragAndDropHandlingExampleTest {

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
			
			DragAndDropTestExample();
			
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
	

	public static void DragAndDropTestExample() throws MalformedURLException, InterruptedException {

		// DesiredCapabilities Settings
		DesiredCapabilities dCap = new DesiredCapabilities();

		dCap.setCapability("deviceName", "Android-Emulator");
		dCap.setCapability("udID", "emulator-5554");

		dCap.setCapability("platformName", "Android");
		dCap.setCapability("platformVersion", "7.1.1");

		dCap.setCapability("appPackage", "com.example.sourabh.dragdropexample");
		dCap.setCapability("appActivity", "com.example.sourabh.dragdropexample.MainActivity");

		// Setting Up Appium Server
		URL appiumURL = new URL("http://0.0.0.0:4723/wd/hub");
		androidDriver = new AndroidDriver<WebElement>(appiumURL, dCap); //Using Android Driver For This 

		Thread.sleep(2000);
		System.out.println("      Application Started       ");
		
		// Drag & Drop Starts Here
		
		// Just to make sure that when you are resource ID is generating a common Identifier ion that 
		
		// case it's better use getAttribute() / get() method after findElements() / findElement().
		
		// Here We don't have to deal with this issue because our Identifiers are unique.
		
		// We have 3 Drag Options And 3 Drop Options To PLace Them Into.
		
		WebElement firstSource = androidDriver.findElementById("com.example.sourabh.dragdropexample:id/text1");
		WebElement firstTarget = androidDriver.findElementById("com.example.sourabh.dragdropexample:id/text5");
		
		// We need TouchAction() to to do this Drag & Drop.
		TouchAction touchAction = new TouchAction(androidDriver);
		
		touchAction.longPress(ElementOption.element(firstSource)).
		moveTo(ElementOption.element(firstTarget)).
		release().
		perform();
		
		
		// Second Drag & Drop Option
		WebElement secondSource = androidDriver.findElementById("com.example.sourabh.dragdropexample:id/text2");
		WebElement secondTarget = androidDriver.findElementById("com.example.sourabh.dragdropexample:id/text6");
		
		touchAction.longPress(ElementOption.element(secondSource)).
		moveTo(ElementOption.element(secondTarget)).
		release().
		perform();
		
		
		// Third Drag & Drop Option
		WebElement thirdSource = androidDriver.findElementById("com.example.sourabh.dragdropexample:id/text3");
		WebElement thirdTarget = androidDriver.findElementById("com.example.sourabh.dragdropexample:id/text4");
		
		touchAction.longPress(ElementOption.element(thirdSource)).
		moveTo(ElementOption.element(thirdTarget)).
		release().
		perform();
		
		System.out.println("All Drag & Drop Options Are Set Into Places");

	}

}

/**
 * 
 
 For Handling Progress Seeking Scroll Options
 
 We use Same Technique As Above slight difference is in moveTo()
 
 Previously as in here, it goes to targetElement but for this instance we need targetElement along With Coordinates.
 
 For Example For 50% coverage : touchAction.longPress(ElementOption.element(targetSource)).
		moveTo(ElementOption.element(targetSource, 250, 250)).
		release().
		perform();
		
 * 
 */
