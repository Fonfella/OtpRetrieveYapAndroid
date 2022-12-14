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

import java.util.ArrayList;

@Action(name="WEB_GetText")
public class GetText implements WebAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String xpathElement;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String text;

    @Override
    public ExecutionResult execute(WebAddonHelper helper) throws FailureException {

        WebDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        for (int i = 0; i<5; i++) {
            try {
                text =driver.findElement(By.xpath(xpathElement)).getText();
                if (text != null) {
                    report.result("testo: "+ text);
                    break;
                } else {
                    return ExecutionResult.FAILED;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ExecutionResult.PASSED;
    }
}