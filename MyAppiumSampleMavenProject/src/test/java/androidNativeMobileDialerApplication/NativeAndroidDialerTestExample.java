package androidNativeMobileDialerApplication;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class NativeAndroidDialerTestExample {

	// This will show you Focused App in Your Emulator : 
	// adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'" 

	// More Commands : 	
	// emulator -list-avds
	// emulator -avd @name-of-your-emulator
	// ${ANDROID_SDK}/tools/emulator
	// ${ANDROID_SDK}\tools\bin>uiautomatorviewer.bat
	
	static AndroidDriver<WebElement> androidDriver;
	static String DialNumber = "+8801915645093";

	
	public static void main(String[] args) {
		
		try {
			
			DialerTest();
			
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
	
	
	public  static void DialerTest() throws MalformedURLException, InterruptedException {
		
		// DesiredCapabilities Settings
		DesiredCapabilities dCap = new DesiredCapabilities();
		
		dCap.setCapability("deviceName", "Android-Emulator");
		dCap.setCapability("udID", "emulator-5554");
		
		dCap.setCapability("platformName", "Android");
		dCap.setCapability("platformVersion", "7.1.1");
		
		dCap.setCapability("appPackage", "com.android.dialer");
		dCap.setCapability("appActivity", "com.android.dialer.DialtactsActivity");
		
		// Setting Up Appium Server
		URL appiumURL = new URL("http://0.0.0.0:4723/wd/hub");
		androidDriver = new AndroidDriver<WebElement>(appiumURL, dCap); //Using Android Driver For This 

		Thread.sleep(2000);
		System.out.println("      Application Started       ");
		
		// Capturing Screenshot For Dialer
		WebElement mobileDialer = androidDriver.findElement(By.id("com.android.dialer:id/floating_action_button"));
		mobileDialer.click();
		
		// Lets Dial Some Numbers
		
				
		WebElement numberZero = androidDriver.findElement(By.id("com.android.dialer:id/zero"));
		numberZero.click();
		androidDriver.findElement(By.id("com.android.dialer:id/eight")).click();
		androidDriver.findElement(By.id("com.android.dialer:id/eight")).click();
		androidDriver.findElement(By.id("com.android.dialer:id/zero")).click();
		androidDriver.findElement(By.id("com.android.dialer:id/one")).click();
		androidDriver.findElement(By.id("com.android.dialer:id/nine")).click();
		androidDriver.findElement(By.id("com.android.dialer:id/one")).click();
		androidDriver.findElement(By.id("com.android.dialer:id/five")).click();
		androidDriver.findElement(By.id("com.android.dialer:id/six")).click();
		androidDriver.findElement(By.id("com.android.dialer:id/four")).click();
		androidDriver.findElement(By.id("com.android.dialer:id/five")).click();
		androidDriver.findElement(By.id("com.android.dialer:id/zero")).click();
		androidDriver.findElement(By.id("com.android.dialer:id/nine")).click();
		androidDriver.findElement(By.id("com.android.dialer:id/three")).click();
		
		// Making Call
		Thread.sleep(2000);
 
		WebElement dialedNumber = androidDriver.findElement(By.id("com.android.dialer:id/digits"));
		String numberDialed = dialedNumber.getText();
		System.out.println("Calling Number : " +numberDialed);
		
		// Trying to Use SendKeys instead OF Dialing All These Numbers Sequentially
		androidDriver.findElement(By.id("com.android.dialer:id/digits")).clear();
		androidDriver.findElement(By.id("com.android.dialer:id/digits")).sendKeys(DialNumber);
		androidDriver.findElement(By.id("com.android.dialer:id/dialpad_floating_action_button")).click();
		
		Thread.sleep(2000);
	}

}

/**
 * 
 
 		//androidDriver.findElement(By.id("com.android.dialer:id/dialpad_key_letters")).click();
		//androidDriver.findElement(By.className("android.widget.TextView")).click();
		//AppiumDriver<MobileElement> appiumDriver;
		//appiumDriver = new AppiumDriver<MobileElement>(appiumURL,dCap);
		//TouchActions action = new TouchActions(androidDriver);
		//WebElement numberZero = androidDriver.findElementById("com.android.dialer:id/zero");
		//action.longPress(numberZero).release().perform();
		//action.longPress(numberZero,duration*1000).release().perform();
		//action.longPress(numberZero);
		
		// WebElement Zero = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='0']"));
         //new TouchAction(androidDriver)
         //.longPress(LongPressOptions.longPressOptions().withElement((ElementOption.element(Zero))))
         //.release().perform();
		
		//androidDriver.findElement(By.name("+")).click();
		//WebElement numberZero = androidDriver.findElement(By.xpath("android.widget.TextView[@text='0]"));
		//numberZero.click();

 
 * 
 */
