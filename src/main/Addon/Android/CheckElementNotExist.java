package main.Addon.Android;

import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;

@Action(name = "Check Element Not Exist")
public class CheckElementNotExist implements AndroidAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String element;

    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        ActionReporter report = helper.getReporter();
        AndroidDriver driver = helper.getDriver();

        boolean verify = true;
        try {
            if (driver.findElementByXPath(element).isDisplayed()) {
                verify = false;
            }
        } catch (Exception e)  {
            e.printStackTrace();
        }
        if (verify == false){
            report.result("Elemento Trovato, KO!");
            return ExecutionResult.FAILED;
        }
        report.result("Elemento NON Trovato, OK!");
        return ExecutionResult.PASSED;
    }
}