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
import main.Addon.Methods;

@Action(name = "Android Grant Manage")
public class GrantManage implements AndroidAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String grantManage;

    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        ActionReporter report = helper.getReporter();
        AndroidDriver driver = helper.getDriver();

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
