package main.Addon.ios;

import io.appium.java_client.MobileBy;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.IOSAction;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.drivers.IOSDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import org.openqa.selenium.By;

@Action(name="iOS controllo PDF")
public class NP_ControlPdf implements IOSAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String CheckPointElement;

    @Parameter(direction = ParameterDirection.INPUT)
    public String dataAccessibiliyID;


    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {

        IOSDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();


        boolean verify = false;
        for(int i = 0; i < 5; i++) {
            try {
                if (driver.findElement(By.xpath(CheckPointElement)).isDisplayed()) {
                    verify = true;
                    report.result("Elemento Trovato!!!");
                    break;
                }
                //da provare questa parte



            }catch (Exception e) {
                e.printStackTrace();
                driver.navigate().back();
                driver.findElement(new MobileBy.ByAccessibilityId(dataAccessibiliyID)).click();
            }
        }
        if (verify == false){
            report.result("PAGINA NON CARICATA!!!");
            return ExecutionResult.FAILED;
        }
        return ExecutionResult.PASSED;
    }
}