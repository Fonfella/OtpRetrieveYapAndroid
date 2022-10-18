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
import org.openqa.selenium.By;

@Action(name = "Insert Pan NexiPay" )
public class InsertPan implements AndroidAction {
    @Parameter(direction = ParameterDirection.INPUT)
    public String PAN = "4532200085801459";
    @Parameter(direction = ParameterDirection.INPUT)
    public String ValuePan_1 = "//android.widget.EditText[1]";

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        AndroidDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();
        String[] strPAN = PAN.split("");
        int count = 0;

        for(int i = 0; i< strPAN.length; i++){
            count = i+1;
            String strPath = ValuePan_1.replaceAll("1", String.valueOf(count));
            driver.findElement(By.xpath(strPath)).sendKeys(strPAN[i]);
        }

        report.result("Inserimento valori completato");
        return ExecutionResult.PASSED;

    }
}

