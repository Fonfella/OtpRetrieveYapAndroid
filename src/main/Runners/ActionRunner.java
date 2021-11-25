package main.Runners;

import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.v2.Runner;

import io.testproject.java.sdk.v2.drivers.IOSDriver;
import main.Addon.OtpRetrieveYap;

import static io.appium.java_client.remote.IOSMobileCapabilityType.BUNDLE_ID;


public class ActionRunner {
    private static Runner runner;
    private static IOSDriver driver;


    private final static String devToken = "nLX1e4exBULyHTBJeXZRJoBqQ7LCv8qsY2d-2QHK36Y1";

    public static void main(String[] args) throws Exception{
        DriverSettings driverSettings = new DriverSettings(DriverType.Appium_iOS);

        runner = Runner.createIOS(devToken, "78d622e53acab9f934065b66b3f36f2b5422605f", "iPhone di testAutomation", "it.nexi.yap.enterprise");
        driver = runner.getDriver();
     //   try(Runner runner = new Runner(devToken, driverSettings)){

            OtpRetrieveYap addon = new OtpRetrieveYap();

            IOSDriver driver = runner.getDriver(addon);

            //Click all the menu items of the menu
            runner.run(addon);

        }
    }

//}
