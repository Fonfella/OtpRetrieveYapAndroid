package main.Runners;

import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.v2.Runner;

import io.testproject.java.sdk.v2.drivers.IOSDriver;
import main.Addon.ios.NP_GestionePOPUP;
import main.Addon.ios.NP_ScrollElementToDirection;


public class ActionRunner_iOS {

    private static Runner runner;
    private static IOSDriver driver;


    private final static String devToken = "rgcLJ3OzoYsJZXSgNI-vt73WpAOjnMHzV0tJhjU9xhU1";

    public static void main(String[] args) throws Exception{
        DriverSettings driverSettings = new DriverSettings(DriverType.Appium_iOS);

        runner = Runner.createIOS(devToken,
                "00008030-00162D011E9B402E",
                "iPhone di mdonzella",
                "it.cartasi.mobile.iPhone.enterprise");

        driver = runner.getDriver();
        //   try(Runner runner = new Runner(devToken, driverSettings)){

        NP_GestionePOPUP addoniOS = new NP_GestionePOPUP();

        IOSDriver driver = runner.getDriver();

        //Click all the menu items of the menu
        runner.run(addoniOS);

    }
}
