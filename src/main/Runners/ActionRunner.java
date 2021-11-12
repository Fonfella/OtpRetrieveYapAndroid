package main.Runners;

import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.v2.Runner;

import main.Addon.VerificaValoreForziere;


public class ActionRunner {


    private final static String devToken = "nLX1e4exBULyHTBJeXZRJoBqQ7LCv8qsY2d-2QHK36Y1";

    public static void main(String[] args) throws Exception{
        DriverSettings driverSettings = new DriverSettings(DriverType.Appium_Android);

        try(Runner runner = new Runner(devToken, driverSettings)){

            VerificaValoreForziere addon = new VerificaValoreForziere();

            AndroidDriver driver = runner.getDriver(addon);

            //Click all the menu items of the menu
            runner.run(addon);

        }
    }

}
