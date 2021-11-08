package main.Addon;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.WebAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;

@Action(name="Otp Retrieve Yap Fonfella")
public class OtpRetrieveYapFonfella implements AndroidAction {

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {


        AndroidDriver driver = helper.getDriver();


        String startUrl = "https://stgapi.nexi.it/mfa/getlastotp?user=%2B39";
        String url = startUrl + "3486896752"; //telephoneNumber parameter
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String myResponse = response.body().string();
            if (myResponse.contains("Nessun OTP trovato")) {
           System.out.println("[DETAILS] Nessun OTP Trovato");
            } else {
                String[] result = myResponse.split("OTP");
                String[] result1 = result[1].split("</html>");
                String var = result1[0].trim();
               System.out.println("[DETAILS] I found OTP: "+var+", for user:") ;
            //+telephonNumber);

                driver.findElementById("it.nexi.yap.stg:id/input_password").sendKeys(var);
                System.out.println("[DETAILS] I use OTP: "+var);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ExecutionResult.PASSED;

        }



}
