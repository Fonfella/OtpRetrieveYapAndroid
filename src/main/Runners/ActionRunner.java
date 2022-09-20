package main.Runners;

import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.v2.Runner;
import main.Addon.AndMPOS_CheckPeriodoDefault;
import main.Addon.AndroidCheckMailRicevuta;


public class ActionRunner {


    private final static String devToken = "rgcLJ3OzoYsJZXSgNI-vt73WpAOjnMHzV0tJhjU9xhU1";

    public static void main(String[] args) throws Exception{
        DriverSettings driverSettings = new DriverSettings(DriverType.Appium_Android);

        try(Runner runner = new Runner(devToken, driverSettings)){

            AndMPOS_CheckPeriodoDefault addon = new AndMPOS_CheckPeriodoDefault();

            AndroidDriver driver = runner.getDriver(addon);

            //Click all the menu items of the menu
            runner.run(addon);

        }
    }
}
