package handlingAndroidAppTextAreasCheckBoxesAndRadioButton;

import java.awt.List;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;

public class HandlingTextAreasTestForAndroidAppliacation {

	// This will show you Focused App in Your Emulator : 
	// adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'" 

	// More Commands : 	
	// emulator -list-avds
	// emulator -avd @name-of-your-emulator
	// ${ANDROID_SDK}/tools/emulator
	// ${ANDROID_SDK}\tools\bin>uiautomatorviewer.bat

	static AndroidDriver<WebElement> androidDriver;

	static String petName = "Good Doggy";
	static String petBreed = "Blood Hound";
	static String petWeight = "30";

	public static void main(String[] args) {

		try {

			TestOnTextBoxAreas();

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


	public static void TestOnTextBoxAreas() throws MalformedURLException, InterruptedException {

		// DesiredCapabilities Settings
		DesiredCapabilities dCap = new DesiredCapabilities();

		dCap.setCapability("deviceName", "Android-Emulator");
		dCap.setCapability("udID", "emulator-5554");

		dCap.setCapability("platformName", "Android");
		dCap.setCapability("platformVersion", "7.1.1");

		dCap.setCapability("appPackage", "com.example.android.pets");
		dCap.setCapability("appActivity", "com.example.android.pets.CatalogActivity");

		//dCap.setCapability("automationName",  "uiautomator2");

		// Setting Up Appium Server
		URL appiumURL = new URL("http://0.0.0.0:4723/wd/hub");
		androidDriver = new AndroidDriver<WebElement>(appiumURL, dCap); //Using Android Driver For This 

		Thread.sleep(2000);
		System.out.println("      Application Started       ");

		// Creating Pet
		CreatePet();

		// Testing Other App Options
		GenerateDummy();
		AnotherDummy(); // Reason Was To Get Details For Them But List Isn't Compatible
		GenerateDummy();

		// HOw Many Are There
		ListElements();
		
		// Deleting Pets
		DeletePets();

	}

	/**
	 * Wanted To List Their Details Individually But Seems Like It's Not Compatible!!
	 * @throws InterruptedException
	 */
	public static void GenerateDummy() throws InterruptedException {

		androidDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")).click();
		androidDriver.findElements(By.id("com.example.android.pets:id/title")).get(0).click();

		// App Generated Dummy Data
		//String dummyName = androidDriver.findElements(By.id("com.example.android.pets:id/name")).get(3).getText();
		//String dummyBreed = androidDriver.findElements(By.id("com.example.android.pets:id/summary")).get(4).getText();

		//System.out.println("Dummy Pet Name Is : " +dummyName +" And Breed Is : " +dummyBreed);
		System.out.println("Dummy Created");
		Thread.sleep(2000);

	}

	/**
	 * Wanted To Separate Out Details Individually As They Share Common ID But Throws Error.
	 * @throws InterruptedException
	 */
	public static void AnotherDummy() throws InterruptedException {

		androidDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")).click();
		androidDriver.findElements(By.id("com.example.android.pets:id/title")).get(0).click();

		// App Generated Dummy Data
		//String dummyName = androidDriver.findElements(By.id("com.example.android.pets:id/name")).get(5).getText();
		//String dummyBreed = androidDriver.findElements(By.id("com.example.android.pets:id/summary")).get(6).getText();

		//System.out.println("Dummy Pet Name Is : " +dummyName +" And Breed Is : " +dummyBreed);
		System.out.println("Another Dummy");
		Thread.sleep(2000);

	}


	public static void CreatePet() {

		// Creating Pets
		androidDriver.findElement(By.id("com.example.android.pets:id/fab")).click();

		// Filling Up Pet's Information
		androidDriver.findElementById("com.example.android.pets:id/edit_pet_name").sendKeys(petName);
		androidDriver.findElementById("com.example.android.pets:id/edit_pet_breed").sendKeys(petBreed);

		androidDriver.findElementById("com.example.android.pets:id/spinner_gender").click();
		androidDriver.findElements(By.id("android:id/text1")).get(2).click();

		androidDriver.findElement(By.id("com.example.android.pets:id/edit_pet_weight")).sendKeys(petWeight);

		// Saving Pet Edit Information
		androidDriver.findElement(By.id("com.example.android.pets:id/action_save")).click();
		//String petSaved = androidDriver.findElement(By.id("com.example.android.pets:id/list")).getText();
		//String petSaved = androidDriver.findElement(By.linkText("Pet savd")).getText();
		//System.out.println("System Showed : " +petSaved);

		// Assertion Checks
		String nameFound = androidDriver.findElement(By.id("com.example.android.pets:id/name")).getText(); 
		Assert.assertEquals(petName, nameFound);
		System.out.println("Name Matches : " +nameFound);

		String breedFound = androidDriver.findElement(By.id("com.example.android.pets:id/summary")).getText(); 
		Assert.assertEquals(petBreed, breedFound);
		System.out.println("Breed Matches : " +breedFound);

	}


	/**
	 * Delete Pets
	 * @throws InterruptedException
	 */
	public static void DeletePets() throws InterruptedException {

		androidDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")).click();
		androidDriver.findElements(By.id("com.example.android.pets:id/title")).get(1).click();
		
		System.out.println("Pets Deleted");	

		String expectedText = "It's a bit lonely here...";
		String textFound = androidDriver.findElement(By.id("com.example.android.pets:id/empty_title_text")).getText();
		
		Thread.sleep(2000);
		Assert.assertEquals(expectedText, textFound);
		System.out.println("Pet List Is Empty : " +textFound);

	}

	/**
	 * This Was To Determine How Many Pets Exist. But Doesn't Seem To Have Compatibility Match To 
	 * Carry Out This Functionality.
	 */
	public static void ListElements() {

		//System.out.println(androidDriver.findElement(By.id("com.example.android.pets:id/list")));
		//java.util.List<WebElement> optionCount = (java.util.List<WebElement>) androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ListView"));
		//List opCount = (List) androidDriver.findElement(By.id("com.example.android.pets:id/list"));
		//System.out.println("Number Of Pets Found : " +opCount.getItems());
		//int petNumbers = androidDriver.findElement(By.id("com.example.android.pets:id/list"));
		//com.example.android.pets:id/list
		//Select s = new Select(androidDriver.findElement(By.id("com.example.android.pets:id/list")));
		//System.out.println(s.getOptions().size());

	}

}
