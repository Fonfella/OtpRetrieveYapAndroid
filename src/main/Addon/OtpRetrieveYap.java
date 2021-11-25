package main.Addon;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSElement;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.IOSAction;
import io.testproject.java.sdk.v2.addons.IOSElementAction;
import io.testproject.java.sdk.v2.addons.WebAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
import io.testproject.java.sdk.v2.drivers.IOSDriver;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;

@Action(name="Yap OTP Retrieve IOS")
public class OtpRetrieveYap implements IOSAction {

    @Parameter(defaultValue = "")
    public String myResponse;


    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {

        String a = "0";
        String b = null;

        //    AndroidDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();

        IOSDriver driver = helper.getDriver();

            if (myResponse.contains("Nessun OTP trovato")) {
                report.result("Nessun OTP trovato");
                b = "0";
            } else {
                String[] result = myResponse.split("OTP");
                String[] result1 = result[1].split("</html>");
                String var = result1[0].trim();
                //          System.out.println("[DETAILS] I found OTP: "+var+", for user:") ;
                //+telephonNumber);

                driver.findElementByXPath("//XCUIElementTypeOther[2]/XCUIElementTypeButton").sendKeys(var);
                report.result("codice OTP trovato: " + var + "\n" );
                b="1";
            }

        if (a == b) {
            return ExecutionResult.FAILED;
        }
        return ExecutionResult.PASSED;
    }


}