package main.Runners;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.v2.Runner;
import main.Addon.GetSettingAppActivityAndroid;
import main.iOS.ActivateApp;
import main.iOS.iOSMPOS_CheckPeriodoCustom;
import main.iOS.iOS_SetupConfigurazioneBT_pos1;


public class ActionRunner_iOS {
    private static Runner runner;
    private static IOSDriver driver;


    private final static String devToken = "rgcLJ3OzoYsJZXSgNI-vt73WpAOjnMHzV0tJhjU9xhU1";

    public static void main(String[] args) throws Exception{
        DriverSettings driverSettings = new DriverSettings(DriverType.Appium_iOS);

        runner = Runner.createIOS(devToken,
                "00008030-00162D011E9B402E",
                "iPhone di mdonzella",
                "it.nexi.mpos.enterprise");

        driver = runner.getDriver();
        //   try(Runner runner = new Runner(devToken, driverSettings)){

        iOS_SetupConfigurazioneBT_pos1 addoniOS = new iOS_SetupConfigurazioneBT_pos1();

        IOSDriver driver = runner.getDriver();

        //Click all the menu items of the menu
        runner.run(addoniOS);

    }
}

