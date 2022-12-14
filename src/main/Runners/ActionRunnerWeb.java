package main.Runners;

import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.v2.Runner;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import main.Addon.Web.ClearAndInsertText;
import main.Addon.Web.InsertKey6Web;


public class ActionRunnerWeb {


    private final static String devToken = "rgcLJ3OzoYsJZXSgNI-vt73WpAOjnMHzV0tJhjU9xhU1";

    public static void main(String[] args) throws Exception {
        DriverSettings driverSettings = new DriverSettings(DriverType.Chrome);

        try (Runner runner = new Runner(devToken, driverSettings)) {

            ClearAndInsertText addon = new ClearAndInsertText();

            WebDriver driver = runner.getDriver(addon);

            //Click all the menu items of the menu
            runner.run(addon);

        }
    }

}
