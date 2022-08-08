package main.Runners;

import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.v2.Runner;

import main.Addon.AndroidRecuperoOtpNP;
import main.Addon.GenerateRandomePhoneNumber;


public class ActionRunner {


    private final static String devToken = "W0hQvAJRinhXIj4f9Zki62bmf9aDL1pUrp2OZiK9rm81";

    public static void main(String[] args) throws Exception{
        DriverSettings driverSettings = new DriverSettings(DriverType.Appium_Android);

        try(Runner runner = new Runner(devToken, driverSettings)){

            GenerateRandomePhoneNumber addon = new GenerateRandomePhoneNumber();

            AndroidDriver driver = runner.getDriver(addon);

            //Click all the menu items of the menu
            runner.run(addon);

        }
    }

}
