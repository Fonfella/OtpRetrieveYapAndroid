package main.Addon.Web;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.WebAction;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import org.openqa.selenium.By;

@Action(name="WEB_Check Element Not Exist")
public class CheckElementNotExist implements WebAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String element;

    @Override
    public ExecutionResult execute(WebAddonHelper helper) throws FailureException {

        WebDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

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