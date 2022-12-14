package main.Addon.Web;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.WebAction;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;

import java.util.ArrayList;

@Action(name="WEB_Check PDF Open")
public class CheckHandleOpenPDF implements WebAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String urlPdf="https://www.nexi.it/content/dam/nexi/download/trasparenza/assicurazione/Dplus-carte-di-credito-platinum-iosiplus-excellence.pdf";


    @Override
    public ExecutionResult execute(WebAddonHelper helper) throws FailureException {

        WebDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();


        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
       String currentUrl = driver.getCurrentUrl();

        if (urlPdf.equals(currentUrl)) {
            report.result("Link PDF aperto correttamente: "+currentUrl);
            driver.close();
        } else {
            report.result("Link PDF Non Visualizzato!!!: KO");
            return ExecutionResult.FAILED;
        }

        driver.switchTo().window(tabs2.get(0));

        return ExecutionResult.PASSED;
    }
}