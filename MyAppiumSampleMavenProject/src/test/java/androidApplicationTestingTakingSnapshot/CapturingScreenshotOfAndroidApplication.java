package androidApplicationTestingTakingSnapshot;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CapturingScreenshotOfAndroidApplication {

	// This will show you Focused App in Your Emulator : 
	// adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'" 

	// More Commands : 	
	// emulator -list-avds
	// emulator -avd @name-of-your-emulator
	// ${ANDROID_SDK}/tools/emulator
	// ${ANDROID_SDK}\tools\bin>uiautomatorviewer.bat

	static AndroidDriver<WebElement> androidDriver;
	static DateFormat df;


	public static void main(String[] args) {

		try {

			TestingScreenshotCastingOnAndroid();

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


	public static void  TestingScreenshotCastingOnAndroid() throws InterruptedException, IOException {

		// DesiredCapabilities Settings
		DesiredCapabilities dCap = new DesiredCapabilities();

		dCap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android-Emulator");
		dCap.setCapability(MobileCapabilityType.UDID, "emulator-5554");

		dCap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dCap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");

		dCap.setCapability("appPackage", "com.example.android.teatime");
		dCap.setCapability("appActivity", "com.example.android.teatime.MenuActivity");

		// Setting Up Appium Server
		URL appiumURL = new URL("http://0.0.0.0:4723/wd/hub");
		androidDriver = new AndroidDriver<WebElement>(appiumURL, dCap); //Using Android Driver For This 

		Thread.sleep(2000);
		System.out.println("      Application Started       ");

		// Commencing Testing

		// Ordering All Available Tea Options
		androidDriver.findElements(By.id("com.example.android.teatime:id/image")).get(1).click();
		GreenTea();
		NavigationControl();
		CaptureScreenshot();

		androidDriver.findElements(By.id("com.example.android.teatime:id/image")).get(0).click();
		BlackTea();
		NavigationControl();
		CaptureScreenshot();

		androidDriver.findElements(By.id("com.example.android.teatime:id/image")).get(2).click();
		WhiteTea();
		NavigationControl();
		CaptureScreenshot();

		androidDriver.findElements(By.id("com.example.android.teatime:id/image")).get(3).click();
		OolongTea();
		CaptureScreenshot();
		NavigationControl();


		SwipeToBottom();
		androidDriver.findElements(By.id("com.example.android.teatime:id/image")).get(5).click();
		HoneyLemonTea();
		CaptureScreenshot();
		NavigationControl();

		SwipeToBottom();
		androidDriver.findElements(By.id("com.example.android.teatime:id/image")).get(4).click();
		ChamomileTea();
		CaptureScreenshot();
		NavigationControl();

	}
	
	
	public static void ChamomileTea() {
		
		androidDriver.findElement(By.id("com.example.android.teatime:id/tea_size_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(1).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/milk_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(4).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/sugar_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(4).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/increment_button")).click();
		String totalBill = androidDriver.findElement(By.id("com.example.android.teatime:id/cost_text_view")).getText();
		System.out.println("Chamomile Tea Bill Amount : " +totalBill);
		androidDriver.findElement(By.id("com.example.android.teatime:id/brew_tea_button")).click();

		
	}
	
	
	public static void HoneyLemonTea() {
		
		androidDriver.findElement(By.id("com.example.android.teatime:id/tea_size_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(0).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/milk_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(3).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/sugar_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(3).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/increment_button")).click();
		String totalBill = androidDriver.findElement(By.id("com.example.android.teatime:id/cost_text_view")).getText();
		System.out.println("HoneyLemon Tea Bill Amount : " +totalBill);
		androidDriver.findElement(By.id("com.example.android.teatime:id/brew_tea_button")).click();

		
	}
	
	
	public static void OolongTea() {
		
		androidDriver.findElement(By.id("com.example.android.teatime:id/tea_size_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(0).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/milk_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(2).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/sugar_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(2).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/increment_button")).click();
		String totalBill = androidDriver.findElement(By.id("com.example.android.teatime:id/cost_text_view")).getText();
		System.out.println("Oolong Tea Bill Amount : " +totalBill);
		androidDriver.findElement(By.id("com.example.android.teatime:id/brew_tea_button")).click();

		
	}
	
	
	public static void WhiteTea() {
		
		androidDriver.findElement(By.id("com.example.android.teatime:id/tea_size_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(2).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/milk_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(3).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/sugar_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(4).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/increment_button")).click();
		String totalBill = androidDriver.findElement(By.id("com.example.android.teatime:id/cost_text_view")).getText();
		System.out.println("White Tea Bill Amount : " +totalBill);
		androidDriver.findElement(By.id("com.example.android.teatime:id/brew_tea_button")).click();

		
	}
	
	
	public static void GreenTea() {
		
		androidDriver.findElement(By.id("com.example.android.teatime:id/tea_size_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(0).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/milk_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(4).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/sugar_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(3).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/increment_button")).click();
		String totalBill = androidDriver.findElement(By.id("com.example.android.teatime:id/cost_text_view")).getText();
		System.out.println("Green Tea Bill Amount : " +totalBill);
		androidDriver.findElement(By.id("com.example.android.teatime:id/brew_tea_button")).click();

		
	}


	public static void BlackTea() {

		androidDriver.findElement(By.id("com.example.android.teatime:id/tea_size_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(0).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/milk_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(3).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/sugar_spinner")).click();
		androidDriver.findElements(By.id("android:id/text1")).get(2).click();
		androidDriver.findElement(By.id("com.example.android.teatime:id/increment_button")).click();
		String totalBill = androidDriver.findElement(By.id("com.example.android.teatime:id/cost_text_view")).getText();
		System.out.println("Black Tea Bill Amount : " +totalBill);
		androidDriver.findElement(By.id("com.example.android.teatime:id/brew_tea_button")).click();


	}


	public static void NavigationControl() {

		androidDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		androidDriver.navigate().back();
		androidDriver.navigate().back();

	}


	public static void CaptureScreenshot() throws IOException {

		//folder_name="screenshot";
		String folder_name = "E:\\eclipse\\Saved App UI Screenchot\\ScreenshotsTesting";
		File f=((TakesScreenshot)androidDriver).getScreenshotAs(OutputType.FILE);
		//Date format fot screenshot file name
		df=new  SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		//create dir with given folder name
		//new File(folder_name).mkdir();
		//Setting file name
		String file_name=df.format(new Date())+".png";
		//coppy screenshot file into screenshot folder.
		FileUtils.copyFile(f, new File(folder_name + "/" + file_name));

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
		//ts.press(PointOption.point(x, top_y)).moveTo(x, bottom_y).release().perform();
	}


}
