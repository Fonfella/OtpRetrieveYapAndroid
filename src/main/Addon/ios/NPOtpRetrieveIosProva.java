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
import main.Addon.Methods;
import main.Addon.SpecificMethod;
import org.openqa.selenium.By;

import java.io.IOException;

@Action(name="Recupero NP OTP IOS Parametrico prova")
public class NPOtpRetrieveIosProva implements IOSAction {

    SpecificMethod generate = new SpecificMethod();

    @Parameter(direction = ParameterDirection.INPUT)
    public String URI;

    @Parameter(direction = ParameterDirection.INPUT)
    public String insertValue;


    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {
        String a = "0";
        String b = null;

        IOSDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        String [] array_otp = generate.getFinalOtp(URI);

        String OTP = array_otp[0]+
                array_otp[1]+
                array_otp[2]+
                array_otp[3]+
                array_otp[4]+
                array_otp[5];

        if (OTP != null) {
            driver.findElement(By.xpath(insertValue)).sendKeys(OTP);
            driver.findElement(By.xpath("//*[@value='CONFERMA']")).click();
            report.result("OTP: " + OTP);
            return ExecutionResult.PASSED;
        } else {
            report.result("Codice non Trovato");
            return ExecutionResult.FAILED;
        }
    }
}
