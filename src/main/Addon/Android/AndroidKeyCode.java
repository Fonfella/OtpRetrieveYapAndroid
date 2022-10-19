package main.Addon.Android;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import main.Addon.Methods;
import org.openqa.selenium.By;

@Action(name = "KeyCode AndroidAction")
public class AndroidKeyCode implements AndroidAction {

   @Parameter(direction = ParameterDirection.INPUT)
    public String action;

    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

        AndroidDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        switch (action) {
            case "back":
                driver.navigate().back();
                break;
            case "enter":
                driver.pressKey(new KeyEvent(AndroidKey.ENTER));
                break;
            case "home":
                driver.pressKey(new KeyEvent(AndroidKey.HOME));
                break;
        }

        report.result("Azione Eseguita");
        return ExecutionResult.PASSED;
    }
}
