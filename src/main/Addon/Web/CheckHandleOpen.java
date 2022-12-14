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

import java.util.ArrayList;

@Action(name="WEB_Check Link Open")
public class CheckHandleOpen implements WebAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String titolo;


    @Override
    public ExecutionResult execute(WebAddonHelper helper) throws FailureException {

        WebDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();


        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        String currentTitle= driver.getTitle();
        report.result(currentTitle);

        if (titolo.equals(currentTitle)) {
            report.result("Link aperto correttamente: "+currentTitle);
            driver.close();
        } else {
            report.result("Link Non Visualizzato!!!: KO");
            return ExecutionResult.FAILED;
        }

        driver.switchTo().window(tabs2.get(0));

        return ExecutionResult.PASSED;
    }
}