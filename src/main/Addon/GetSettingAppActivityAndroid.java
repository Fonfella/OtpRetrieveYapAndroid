package main.Addon;

import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;

@Action(name="MPOS Android get appActivity Settings")
public class GetSettingAppActivityAndroid implements AndroidAction {
    //decommentare prima di push addon

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String AppActivity;


    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        AndroidDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();
       String a = (String) ((io.testproject.java.sdk.v2.drivers.AndroidDriver<?>) driver).getCapabilities().getCapability("appium:deviceManufacturer");
        System.out.println(a);

            if (a.equals("samsung") ) {
                AppActivity="com.android.settings.Settings";
            } else {
                AppActivity="com.android.settings.HWSettings";
            }

            report.result("AppActivity:" +AppActivity);
        return ExecutionResult.PASSED;
    }
}