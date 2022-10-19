package main.Addon.Android;

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

@Action(name = "Insert value on Keyboard AndroidAction")
public class InsertKeySix implements AndroidAction {
    Methods method = new Methods();

   @Parameter(direction = ParameterDirection.INPUT)
    public String keySix;

    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

        AndroidDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        String [] keysixArray = keySix.split("");

        for (int i = 0; i < keysixArray.length; i++) {
            String keySix = keysixArray[i];
            String xpathToPress = "//*[@text='" + keySix + "']";
            driver.findElement(By.xpath(xpathToPress)).click();
        }
        report.result("Azione Eseguita");
        return ExecutionResult.PASSED;
    }
}
