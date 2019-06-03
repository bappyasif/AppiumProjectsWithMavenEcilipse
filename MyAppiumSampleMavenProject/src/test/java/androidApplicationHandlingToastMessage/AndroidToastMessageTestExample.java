package androidApplicationHandlingToastMessage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

public class AndroidToastMessageTestExample {

	// This will show you Focused App in Your Emulator : 
	// adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'" 

	// More Commands : 	
	// emulator -list-avds
	// emulator -avd @name-of-your-emulator
	// ${ANDROID_SDK}/tools/emulator
	// ${ANDROID_SDK}\tools\bin>uiautomatorviewer.bat

	static AndroidDriver<WebElement> androidDriver;
	File scrFile;
	static String scrShotDir = "screenshots";
	static String destFile;
	static File scrShotDirPath = new java.io.File("./"+ scrShotDir+ "//");
	
	static String mediumPriority = " A Sample Medium Priority To Do List";
	static String lowPriority = " A Sample Low Priority To Do List";
	static String highPriority = " A Sample High Priority To Do List";


	public static void main(String[] args) {

		try {

			TestOnHandlingToastMessage();
			
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


	public static void TestOnHandlingToastMessage() throws MalformedURLException, InterruptedException, TesseractException {

		// DesiredCapabilities Settings
		DesiredCapabilities dCap = new DesiredCapabilities();

		dCap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android-Emulator");
		dCap.setCapability(MobileCapabilityType.UDID, "emulator-5554");

		dCap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dCap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");

		dCap.setCapability("appPackage", "com.example.android.todolist");
		dCap.setCapability("appActivity", "com.example.android.todolist.MainActivity");

		// Setting Up Appium Server
		URL appiumURL = new URL("http://0.0.0.0:4723/wd/hub");
		androidDriver = new AndroidDriver<WebElement>(appiumURL, dCap);
		Thread.sleep(2000);
		System.out.println("      Application Started       ");

		// Begins Test
		TestCaseMediumPriority();
		androidDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		TestCaseLowPriority();
		androidDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		TestCaseHighPriority();
	}
	
	
	public static void TestCaseHighPriority() throws TesseractException {
		
		androidDriver.findElement(By.id("com.example.android.todolist:id/fab")).click();
	
		androidDriver.findElement(By.id("com.example.android.todolist:id/editTextTaskDescription")).sendKeys(highPriority);
		
		androidDriver.findElement(By.id("com.example.android.todolist:id/radButton1")).click();
		
		androidDriver.findElement(By.id("com.example.android.todolist:id/addButton")).click();
		
		//androidDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		
		ExtractingTextFromToastMessage();
		
		androidDriver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		
	}
	
	
	public static void TestCaseMediumPriority() throws TesseractException {
		
		androidDriver.findElement(By.id("com.example.android.todolist:id/fab")).click();
	
		androidDriver.findElement(By.id("com.example.android.todolist:id/editTextTaskDescription")).sendKeys(mediumPriority);
		
		androidDriver.findElement(By.id("com.example.android.todolist:id/radButton2")).click();
		
		androidDriver.findElement(By.id("com.example.android.todolist:id/addButton")).click();
		
		androidDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		
		ExtractingTextFromToastMessage();
		
		//androidDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		
	}
	
	
	public static void TestCaseLowPriority() throws TesseractException {
		
		androidDriver.findElement(By.id("com.example.android.todolist:id/fab")).click();
	
		androidDriver.findElement(By.id("com.example.android.todolist:id/editTextTaskDescription")).sendKeys(lowPriority);
		
		androidDriver.findElement(By.id("com.example.android.todolist:id/radButton3")).click();
		
		androidDriver.findElement(By.id("com.example.android.todolist:id/addButton")).click();
		
		//androidDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		
		ExtractingTextFromToastMessage();
		
		androidDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		
	}


	public static String ExtractingTextFromToastMessage() throws TesseractException {
		String imgName = CaptureScreenshot();
		String extractedTexts = null;
		File imageFile = new File(scrShotDirPath, imgName);
		System.out.println("Image name is :" + imageFile.toString());
		ITesseract instance = new Tesseract();

		File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Extracts
		// Tessdata
		// folder
		// from
		// referenced
		// tess4j
		// jar
		// for
		// language
		// support
		instance.setDatapath(tessDataFolder.getAbsolutePath()); // sets tessData
		// path

		extractedTexts = instance.doOCR(imageFile);
		System.out.println(extractedTexts);
		return extractedTexts;
	}



	public static String CaptureScreenshot() {
		File scrFile = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE); 

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		new File(scrShotDir).mkdirs(); // Create folder under project with name
		// "screenshots" if doesn't exist
		destFile = dateFormat.format(new Date()) + ".png"; // Set file name
		// using current
		// date time.
		try {
			FileUtils.copyFile(scrFile, new File(scrShotDir + "/" + destFile)); // Copy
			// paste
			// file
			// at
			// destination
			// folder
			// location
		} catch (IOException e) {
			System.out.println("Image not transfered to screenshot folder");
			e.printStackTrace();
		}
		
		System.out.println("Absolute Path : " +scrShotDirPath.getAbsolutePath());
		return destFile;
	}

}
