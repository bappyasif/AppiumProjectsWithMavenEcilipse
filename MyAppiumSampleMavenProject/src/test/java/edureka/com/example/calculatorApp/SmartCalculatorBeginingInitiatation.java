package edureka.com.example.calculatorApp;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class SmartCalculatorBeginingInitiatation {

	// This will show you Focused App in Your Emulator :
	// adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'"

	// More Commands :
	// emulator -list-avds
	// emulator -avd @name-of-your-emulator
	// ${ANDROID_SDK}/tools/emulator
	// ${ANDROID_SDK}\tools\bin>uiautomatorviewer.bat

	// Using APK :
	// ${ANDROID_SDK}/adb install {Path Of Your TUA}

	static AppiumDriver<MobileElement> appiumDriver;

	public static void SmartCalculatorEnvironmentSetup() throws MalformedURLException {

		DesiredCapabilities dCap = new DesiredCapabilities();

		dCap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android-Emulator");
		dCap.setCapability(MobileCapabilityType.UDID, "emulator-5554");

		dCap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dCap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");

		dCap.setCapability("appPackage", "com.ststudio.smart.calculator");
		dCap.setCapability("appActivity", "com.st.calculator.main.MainActivity");
		dCap.setCapability("", "");

		URL appiumURL = new URL("http://0.0.0.0:4723/wd/hub");
		appiumDriver = new AppiumDriver<MobileElement>(appiumURL, dCap);

		System.out.println("      Application Started         ");

		// Sample Operation

		MobileElement first_Number = appiumDriver.findElement(By.id("com.ststudio.smart.calculator:id/kb_8"));
		MobileElement second_Number = appiumDriver.findElement(By.id("com.ststudio.smart.calculator:id/kb_2"));

		MobileElement operation_Sign = appiumDriver.findElement(By.id("com.ststudio.smart.calculator:id/kb_multiply"));
		MobileElement operation_Equal = appiumDriver.findElement(By.id("com.ststudio.smart.calculator:id/kb_equal"));
		MobileElement operation_Total = appiumDriver.findElement(By.id("com.ststudio.smart.calculator:id/text"));

		first_Number.click();
		operation_Sign.click();
		second_Number.click();
		operation_Equal.click();

		System.out.println("Console Output : " + operation_Total.getText());

	}

	public void TearDown() {

		appiumDriver.quit();
		System.out.println("Test Completed");

	}

	public static void main(String[] args) {

		SmartCalculatorBeginingInitiatation created_Object = new SmartCalculatorBeginingInitiatation();
		try {
			created_Object.SmartCalculatorEnvironmentSetup();
			created_Object.TearDown();
			// SmartCalculatorEnvironmentSetup();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
/**
 *
 * // From Appium Recorded Script For JUnit Platform.
 * 
 * MobileElement el1 = ((MobileElement)
 * web_Driver).findElementById("com.ststudio.smart.calculator:id/kb_8");
 * el1.click(); MobileElement el2 = ((MobileElement)
 * web_Driver).findElementById("com.ststudio.smart.calculator:id/kb_multiply");
 * el2.click(); MobileElement el3 = ((MobileElement)
 * web_Driver).findElementById("com.ststudio.smart.calculator:id/kb_2");
 * el3.click(); MobileElement el4 = ((MobileElement)
 * web_Driver).findElementById("com.ststudio.smart.calculator:id/kb_equal");
 * el4.click();
 * 
 * 
 */
