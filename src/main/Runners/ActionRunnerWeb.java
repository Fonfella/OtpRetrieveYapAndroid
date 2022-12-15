package main.Runners;

import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.v2.Runner;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import main.Addon.Web.*;


public class ActionRunnerWeb {


    private final static String devToken = "rgcLJ3OzoYsJZXSgNI-vt73WpAOjnMHzV0tJhjU9xhU1";

    public static void main(String[] args) throws Exception {
        DriverSettings driverSettings = new DriverSettings(DriverType.Chrome);

        try (Runner runner = new Runner(devToken, driverSettings)) {

            CmdWhoami addon = new CmdWhoami();

            WebDriver driver = runner.getDriver(addon);

            //Click all the menu items of the menu
            runner.run(addon);

        }
    }

}
