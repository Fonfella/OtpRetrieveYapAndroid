package main.Runners;

import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.v2.Runner;

import io.testproject.java.sdk.v2.drivers.IOSDriver;
import main.Addon.VerificaSaldo;


public class ActionRunner {


    private final static String devToken = "pPoj9COuCUjyu03q06zbS3gHo3syqsZrxr8f3_7lnVQ1";

    public static void main(String[] args) throws Exception{
        DriverSettings driverSettings = new DriverSettings(DriverType.Appium_iOS);

        try(Runner runner = new Runner(devToken, driverSettings)){

            VerificaSaldo addon = new VerificaSaldo();

            IOSDriver driver = runner.getDriver(addon);

            //Click all the menu items of the menu
            runner.run(addon);

        }
    }

}
