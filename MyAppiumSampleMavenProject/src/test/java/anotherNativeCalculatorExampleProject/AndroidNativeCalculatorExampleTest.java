package anotherNativeCalculatorExampleProject;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AndroidNativeCalculatorExampleTest {

	// This will show you Focused App in Your Emulator : 
	// adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'" 

	// More Commands : 	
	// emulator -list-avds
	// emulator -avd @name-of-your-emulator
	// ${ANDROID_SDK}/tools/emulator
	// ${ANDROID_SDK}\tools\bin>uiautomatorviewer.bat

	//static AppiumDriver<MobileElement> appiumDriver; // Will Be Using Android Driver Rather Than Appium Driver.

	static AndroidDriver<WebElement> driverAndroid; // As mentioned earlier we can use Android Driver as well.


	public static void main(String[] args) {
		
		try {

			CalculatorOperartion();

		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println(ex.getMessage());
			System.out.println(ex.getCause());

			ex.getStackTrace();
		} finally {

			driverAndroid.quit();
			System.out.println("Test Completed...");

		}
	}


	public static void CalculatorOperartion() throws MalformedURLException, InterruptedException {

		// Defining DesiredCapabilities
		DesiredCapabilities dCap = new DesiredCapabilities();

		dCap.setCapability("deviceName", "Android-Emulator");
		dCap.setCapability("udID", "emulator-5554");
		dCap.setCapability("platformName", "Android");
		dCap.setCapability("platformVersion", "7.1.1");


		dCap.setCapability("appPackage", "com.android.calculator2");
		dCap.setCapability("appActivity", "com.android.calculator2.Calculator");
		dCap.setCapability("", "");

		URL appiumURL = new URL("http://0.0.0.0:4723/wd/hub");
		driverAndroid = new AndroidDriver<WebElement>(appiumURL, dCap); //Using Android Driver For This 

		Thread.sleep(2000);
		System.out.println("      Application Started       ");
		
		// Using UIAutomatorViewer
		WebElement firstNumber = driverAndroid.findElement(By.id("com.android.calculator2:id/digit_5"));
		String firstValue = driverAndroid.findElement(By.id("com.android.calculator2:id/digit_5")).getText();
		//String showFirst = firstNumber.getText(); // this show entire WebElement Entity 
		System.out.println("First Number : " +firstValue);
		firstNumber.click();
		
		// Selecting First Real Number
		WebElement operatorSign = driverAndroid.findElement(By.id("com.android.calculator2:id/op_sub"));
		String operationSign = driverAndroid.findElement(By.id("com.android.calculator2:id/op_sub")).getText();
		String operatorClicked = operatorSign.getText();
		System.out.println("Operator Selected : " +operatorSign);
		System.out.println("Operator Selected : " +operationSign);
		operatorSign.click();
		
		// Selecting Operator
		WebElement secondNumber = driverAndroid.findElement(By.id("com.android.calculator2:id/digit_3"));
		String secondValue = driverAndroid.findElement(By.id("com.android.calculator2:id/digit_3")).getText();
		//String showSecond = secondNumber.getText(); // // this show entire WebElement Entity.
		System.out.println("Second Number : " +secondValue);
		secondNumber.click();
		
		// Selecting Second Real Number
		WebElement operationTotal = driverAndroid.findElement(By.id("com.android.calculator2:id/result"));
		String resultValue = operationTotal.getText();
		System.out.println("Calculation Result in Minimal View: " +resultValue);
		//operationTotal.click();
		
		// Selecting Equal Operator Sign
		WebElement operationEqual = driverAndroid.findElement(By.id("com.android.calculator2:id/eq"));
		String resultEqual = operationEqual.getText();
		System.out.println("Equal Sign Is Selected: " +resultEqual);
		operationEqual.click();
		
		// They both have same resource ID!!
		WebElement resultTotal = driverAndroid.findElement(By.id("com.android.calculator2:id/result"));
		String operationValue = operationTotal.getText();
		System.out.println("Calculation Result in Full View: " +operationValue);
		
		// Lets Do a Condition Check
		if (resultValue.equals(operationValue)) {
			
			System.out.println("Test Passed");
			
		} else {
			
			System.out.println("Test Failed");
			
		}
		
		Thread.sleep(2000);

	}

}
