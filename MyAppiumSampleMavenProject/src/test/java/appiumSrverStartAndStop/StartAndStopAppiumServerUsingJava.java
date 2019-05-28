package appiumSrverStartAndStop;

import java.io.File;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class StartAndStopAppiumServerUsingJava {
	
	static String Appium_Node_Path="C:\\Program Files\\nodejs\\node.exe";
    static String Appium_JS_Path="C:\\Users\\BappY\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\appium.js";
    static AppiumDriverLocalService appiumService;
    static String service_url;

    
    
    
    public static void appiumStart() throws Exception{
    	appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().
                usingPort(2856).usingDriverExecutable(new File(Appium_Node_Path)).
                withAppiumJS(new File(Appium_JS_Path)));
    	appiumService.start();
        Thread.sleep(25000);
        service_url = appiumService.getUrl().toString();
        System.out.println("Appium Server : " +service_url);
    }

    public static void appiumStop() throws Exception{
        appiumService.stop();
    }

}
