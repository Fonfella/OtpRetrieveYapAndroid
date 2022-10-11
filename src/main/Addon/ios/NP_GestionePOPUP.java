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
import org.openqa.selenium.Dimension;

@Action(name="Gestione POP-UP IOS")
public class NP_GestionePOPUP implements IOSAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String ElementVisibility;


    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {
        String a = "0";
        String b = null;

        IOSDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        for (int i = 0; i < 5; i++) {
            try {
                if (driver.findElementByXPath(ElementVisibility).isDisplayed()) {
                    b = "1";
                    report.result("Pagina Caricata Correttamente");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(3500);
                if (driver.findElementByXPath("//*[@label='Non ora']").isDisplayed()) {
                    driver.findElementByXPath("//*[@label='Non ora']").click();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (driver.findElementByAccessibilityId("25x25 0115 close 100pt").isDisplayed()) {
                    driver.findElementByXPath("25x25 0115 close 100pt").click();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (a == b) {
            report.result("Pagina Non Caricata!!!");
            return ExecutionResult.FAILED;
        }
        return ExecutionResult.PASSED;
    }
}
