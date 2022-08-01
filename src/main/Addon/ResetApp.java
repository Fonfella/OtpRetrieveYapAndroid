package main.Addon;


import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.reporters.ActionReporter;

@Action(name="Android Reset App")
public class ResetApp implements AndroidAction {

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) {

    AndroidDriver driver = helper.getDriver();
    // Get report object
    ActionReporter report = helper.getReporter();

    driver.resetApp();
    report.result("Reset App effettuato con successo");
    return ExecutionResult.PASSED;
    }
}
