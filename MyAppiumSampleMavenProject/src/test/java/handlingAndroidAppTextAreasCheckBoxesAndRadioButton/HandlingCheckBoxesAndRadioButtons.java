package handlingAndroidAppTextAreasCheckBoxesAndRadioButton;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class HandlingCheckBoxesAndRadioButtons {

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

			TestOnCheckBoxesAndRadioButons();

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


	public static void TestOnCheckBoxesAndRadioButons() throws MalformedURLException, InterruptedException {

		// DesiredCapabilities Settings
		DesiredCapabilities dCap = new DesiredCapabilities();

		dCap.setCapability("deviceName", "Android-Emulator");
		dCap.setCapability("udID", "emulator-5554");

		dCap.setCapability("platformName", "Android");
		dCap.setCapability("platformVersion", "7.1.1");

		dCap.setCapability("appPackage", "com.example.android.shushme");
		dCap.setCapability("appActivity", "com.example.android.shushme.MainActivity");

		// Setting Up Appium Server
		URL appiumURL = new URL("http://0.0.0.0:4723/wd/hub");
		androidDriver = new AndroidDriver<WebElement>(appiumURL, dCap); //Using Android Driver For This 

		Thread.sleep(2000);
		System.out.println("      Application Started       ");

		// com.example.android.shushme:id/enable_switch
		// com.example.android.shushme:id/location_permission_checkbox
		// com.example.android.shushme:id/ringer_permissions_checkbox
		// className = android.widget.Button .Get(5) For Add New Location Click Button

		// Let's Begin
		androidDriver.findElement(By.id("com.example.android.shushme:id/enable_switch")).click();
		Thread.sleep(2000);
		androidDriver.findElement(By.id("com.example.android.shushme:id/location_permission_checkbox")).click();
		Thread.sleep(2000);
		androidDriver.findElementsByClassName("android.widget.Button").get(1).click();
		Thread.sleep(2000);

		androidDriver.findElement(By.id("com.example.android.shushme:id/ringer_permissions_checkbox")).click();
		Thread.sleep(2000);
		androidDriver.findElement(By.id("android:id/navigationBarBackground")).click();
		androidDriver.navigate().back();
		Thread.sleep(2000);

		//androidDriver.findElementsByClassName("android.widget.Button").get(5).click();
		//androidDriver.findElementByAccessibilityId("ADD NEW LOCATION").click();

		androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout"
				+ "/android.widget.LinearLayout/android.widget.FrameLayout"
				+ "/android.view.ViewGroup/android.widget.FrameLayout[2]"
				+ "/android.widget.RelativeLayout/android.widget.LinearLayout[2]"
				+ "/android.widget.Button")).click();			 

		Thread.sleep(2000);

		//androidDriver.findElementByAccessibilityId("Select this location").click();
		
		androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout"
				+ "/android.widget.LinearLayout/android.widget.FrameLayout"
				+ "/android.widget.LinearLayout/android.widget.FrameLayout"
				+ "/android.widget.FrameLayout/android.widget.FrameLayout[2]"
				+ "/android.widget.LinearLayout/android.widget.LinearLayout")).click();

		
		Thread.sleep(2000);
		// com.google.android.gms:id/select_marker_location
		//androidDriver.findElement(By.id("com.google.android.gms:id/confirm_button")).click();
		String adressExpected = androidDriver.findElement(By.id("com.google.android.gms:id/place_address")).getText();
		System.out.println("Location Selected : " +adressExpected );
		androidDriver.findElement(By.id("com.google.android.gms:id/confirm_button")).click();
		String addressSelected = androidDriver.findElementById("com.example.android.shushme:id/address_text_view").getText();

		if(addressSelected.contentEquals(adressExpected)) {
			System.out.println("Address Matched : " +addressSelected);
		} else {
			System.out.println("Address Mismatched");
		}

	}

}
