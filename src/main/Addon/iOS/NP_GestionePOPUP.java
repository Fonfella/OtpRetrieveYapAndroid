package main.Addon.iOS;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.IOSAction;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.drivers.IOSDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.internal.Helper;
import io.testproject.java.sdk.v2.reporters.ActionReporter;

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
                if (driver.findElementByAccessibilityId(ElementVisibility).isDisplayed()) {
                    b = "1";
                    report.result("Pagina Caricata Correttamente");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            report.result("Numero Pagine Skippate: " + i);
            driver.navigate().back();
        }

        if (a == b) {
            report.result("Pagina Non Caricata!!!");
            return ExecutionResult.FAILED;
        }
        return ExecutionResult.PASSED;
    }
}
