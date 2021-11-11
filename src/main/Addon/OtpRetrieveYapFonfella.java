package main.Addon;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.WebAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
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

@Action(name="Otp Retrieve Yap Fonfella")
public class OtpRetrieveYapFonfella implements AndroidAction {

    @Parameter(defaultValue = "https://stgapi.nexi.it/mfa/getlastotp?user=%2B39")
    public String startUrl;

    @Parameter(defaultValue = "3486896752")
    public String numeroTelefono;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

        String a = "0";
        String b = null;

        AndroidDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();

        String numeroTelefono = "3486896752";
        String startUrl = "https://stgapi.nexi.it/mfa/getlastotp?user=%2B39";
        String url = startUrl + numeroTelefono; //telephoneNumber parameter
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String myResponse = response.body().string();
            if (myResponse.contains("Nessun OTP trovato")) {
                report.result("Nessun OTP trovato");
                b = "0";
            } else {
                String[] result = myResponse.split("OTP");
                String[] result1 = result[1].split("</html>");
                String var = result1[0].trim();
                //          System.out.println("[DETAILS] I found OTP: "+var+", for user:") ;
                //+telephonNumber);

                driver.findElementById("it.nexi.yap.stg:id/input_password").sendKeys(var);
                report.result("codice OTP trovato: " + var + "\n" + "per utente = " + numeroTelefono);
                b="1";
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        if (a == b) {
            return ExecutionResult.FAILED;
        }
        return ExecutionResult.PASSED;
    }


}
