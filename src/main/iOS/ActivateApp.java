package main.iOS;


import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.sdk.v2.addons.IOSAction;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.drivers.IOSDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;


@Action(name="iOS ActivateApp")
public class ActivateApp implements IOSAction {
    //com.apple.Preferences
    @Parameter(defaultValue = "")
    public String bundleId;

    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {

        IOSDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();

        driver.activateApp(bundleId);

        return ExecutionResult.PASSED;
    }
}
