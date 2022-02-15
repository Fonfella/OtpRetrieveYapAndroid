package main.Runners;

import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.v2.Runner;
import main.Addon.YapCheckMailLIstaMovimenti;


public class ActionRunner {

    private final static String devToken = "aNxPoEG9YVBdSO1bG4tuaaqRr2RDQFI-SlJn68uB8i01";

    public static void main(String[] args) throws Exception{
        DriverSettings driverSettings = new DriverSettings(DriverType.Appium_Android);

        try(Runner runner = new Runner(devToken, driverSettings)){

            YapCheckMailLIstaMovimenti addon = new YapCheckMailLIstaMovimenti();

            AndroidDriver driver = runner.getDriver(addon);

            //Click all the menu items of the menu
            runner.run(addon);

        }
    }

}


