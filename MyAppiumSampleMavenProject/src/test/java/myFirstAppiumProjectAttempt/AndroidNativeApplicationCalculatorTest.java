package myFirstAppiumProjectAttempt;

import java.net.MalformedURLException;
import java.net.URL;import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AndroidNativeApplicationCalculatorTest {
	
	// We can Select Different Drivers For This
	WebDriver webDriver;
	static AppiumDriver<MobileElement> appiumDriver;
	AndroidDriver androidDriver;
	
	// adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'" 
	// This will show you Focused App in Your Emulator.
	
	// Some More CMD For Emulator	
	// emulator -list-avds
	// emulator -avd @name-of-your-emulator
	// ${ANDROID_SDK}/tools/emulator
	
	public static void main(String[] args) {
		
		try {
			OpenCalculator();
		} catch (MalformedURLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
	}

	
	public static void OpenCalculator() throws MalformedURLException {
		
		// Add DesiredCapapbilities
		
		DesiredCapabilities dCap = new DesiredCapabilities();
		
		dCap.setCapability("deviceName", "emulator-5554");
		dCap.setCapability("udID", "emulator-5554");
		dCap.setCapability("platformName", "Android");
		dCap.setCapability("platformVersion", "7.1.1");
		
		
		dCap.setCapability("appPackage", "com.a	ndroid.calculator2");
		dCap.setCapability("appActivity", "com.android.calculator2.Calculator");
		dCap.setCapability("", "");
		
		URL appiumURL = new URL("http://0.0.0.0:4723/wd/hub");
		appiumDriver =  new AppiumDriver<MobileElement>(appiumURL, dCap);
		
		System.out.println("      Application Started         ");
		
		// Let's Add Two Numbers
		MobileElement firstNumber = appiumDriver.findElement(By.id("com.android.calculator2:id/digit_2"));
		MobileElement sumOperator = appiumDriver.findElement(By.id("com.android.calculator2:id/op_add"));
		
		MobileElement secondNumber = appiumDriver.findElement(By.id("com.android.calculator2:id/digit_7"));
		MobileElement operatorEqual = appiumDriver.findElement(By.id("com.android.calculator2:id/eq"));
		
		MobileElement sumResult = appiumDriver.findElement(By.id("com.android.calculator2:id/result"));
		MobileElement sumResultAnotherCall = appiumDriver.findElement(By.className("android.widget.TextView"));
		
		// Let's Call these Identifiers
		firstNumber.click();
		sumOperator.click();
		secondNumber.click();
		operatorEqual.click();
		
		String resultOutput = sumResult.getText();
		String resultExtracted = sumResultAnotherCall.getText();
		
		System.out.println("Result Is : " +resultOutput);
		System.out.println("Result Is : " +resultExtracted);
		
		//MobileElement showButton = appiumDriver.findElement(By.xpath("android.widget.Button[@resource-id=com.android.calculator2:id/op_add]"));
		//System.out.println(showButton.getText());
		
		
		
		System.out.println("Test Completed...");
		
		appiumDriver.close();
	}

}
