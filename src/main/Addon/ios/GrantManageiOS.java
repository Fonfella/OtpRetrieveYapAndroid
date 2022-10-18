package main.Addon.ios;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.IOSAction;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.drivers.IOSDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;

@Action(name="Grant Manage iOS")
public class GrantManageiOS implements IOSAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String grantManage;

    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {

        IOSDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();
        for (int i = 0; i < 5; i++) {
            try {
                driver.switchTo().alert().accept();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        report.result("Autorizzazione Concessa per: " + grantManage);
        return ExecutionResult.PASSED;
    }
}
