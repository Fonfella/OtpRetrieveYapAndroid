package main.Addon.iOS;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.IOSAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import io.testproject.java.sdk.v2.drivers.IOSDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import org.openqa.selenium.By;

@Action(name="Recupero NP OTP IOS")
public class NPOtpRetrieveIos implements IOSAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String response;

    @Parameter(direction = ParameterDirection.INPUT)
    public String insertValueUno;

    @Parameter(direction = ParameterDirection.INPUT)
    public String insertValueDue;

    @Parameter(direction = ParameterDirection.INPUT)
    public String insertValueTre;

    @Parameter(direction = ParameterDirection.INPUT)
    public String insertValueQuattro;

    @Parameter(direction = ParameterDirection.INPUT)
    public String insertValueCinque;

    @Parameter(direction = ParameterDirection.INPUT)
    public String insertValueSei;

    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {
        String a = "0";
        String b = null;

//        String insertValueUno = "//XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText[1]";
//        String insertValueDue = "//XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText[2]";
//        String insertValueTre = "//XCUIElementTypeStaticText[3]";
//        String insertValueQuattro = "//XCUIElementTypeStaticText[4]";
//        String insertValueCinque = "//XCUIElementTypeStaticText[5]";
//        String insertValueSei = "//XCUIElementTypeStaticText[6]";

        IOSDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        if (response.contains("Nessun OTP trovato")) {
            report.result("Nessun OTP trovato");
            b = "0";
        } else {
            String[] result = response.split("OTP");
            String[] result1 = result[1].split("</html>");
            String var = result1[0].trim();
            //          System.out.println("[DETAILS] I found OTP: "+var+", for user:") ;
            //+telephonNumber);
            String[] array = var.split("(?<=\\G.{1})");
            report.result("Codice OTP trovato: " + var);

            report.result("Codice OTP trovato: " + var);

            driver.findElementByXPath(insertValueUno).sendKeys(array[0]);

            driver.findElementByXPath(insertValueDue).sendKeys(array[1]);

            driver.findElementByXPath(insertValueTre).sendKeys(array[2]);

            driver.findElementByXPath(insertValueQuattro).sendKeys(array[3]);

            driver.findElementByXPath(insertValueCinque).sendKeys(array[4]);

            driver.findElementByXPath(insertValueSei).sendKeys(array[5]);

            b="1";
        }
        if (a == b) {
            return ExecutionResult.FAILED;
        }
        return ExecutionResult.PASSED;
    }
}
