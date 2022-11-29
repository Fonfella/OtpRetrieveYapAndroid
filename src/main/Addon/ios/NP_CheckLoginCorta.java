package main.Addon.ios;

import io.appium.java_client.MobileBy;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.IOSAction;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.drivers.IOSDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import org.openqa.selenium.By;

@Action(name="iOS check LoginCorta")
public class NP_CheckLoginCorta implements IOSAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String CheckPointElement;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String LoginCortaTrueorFalse;

    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {

        IOSDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        try {
            if (driver.findElement(By.xpath(CheckPointElement)).isDisplayed()) {
                report.result("Login Corta");
                LoginCortaTrueorFalse = "true";
            }
            //da provare questa parte

        }catch (Exception e) {
            e.printStackTrace();
            report.result("Login Lunga");
            LoginCortaTrueorFalse = "false";
        }

        return ExecutionResult.PASSED;
    }
}