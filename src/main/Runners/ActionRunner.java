package main.Runners;

import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.v2.Runner;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import main.Addon.ClearInput;

public class ActionRunner {


    private final static String devToken = "U--LKi5tve35kNidu4uRp5IzAWrhL7PNVp3jEn4Y55U1";

    public static void main(String[] args) throws Exception{
        DriverSettings driverSettings = new DriverSettings(DriverType.Chrome);

        try(Runner runner = new Runner(devToken, driverSettings)){

            ClearInput clickMenuLinks = new ClearInput();

            WebDriver driver = runner.getDriver(clickMenuLinks);
            driver.navigate().to("http://eaapp.somee.com/");

            //Click all the menu items of the menu
            runner.run(clickMenuLinks);

        }
    }

}
