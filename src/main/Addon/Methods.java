package main.Addon;


import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Methods {

    public void insertValueByClassName(AndroidAddonHelper helper, String value, String element){

        AndroidDriver driver = helper.getDriver();

        helper.getReporter().result("Il Codice Fiscale da inserire è: " + value);

        List<AndroidElement> campi = driver.findElementsByClassName(element);

        helper.getReporter().result("Sono stati trovati " + campi.size() + "elementi con classe " + element);

        for (int i = 0; i < value.length(); i++) {
            campi.get(i).sendKeys(value.substring(i, i + 1));
            helper.getReporter().result("La cifra numero " + i + " è stata inserita.");
        }

    }

    public void insertValueByXPath(AndroidAddonHelper helper, String value, String element){

        AndroidDriver driver = helper.getDriver();

        helper.getReporter().result("Il Codice Fiscale da inserire è: " + value);

        List<AndroidElement> campi = driver.findElementsByXPath(element);

        helper.getReporter().result("Sono stati trovati " + campi.size() + "elementi con classe " + element);

        for (int i = 0; i < campi.size(); i++) {
            campi.get(i).click();
            i++;
//            campi.get(i).sendKeys(value.substring(i, i + 1));
//            helper.getReporter().result("La cifra numero " + i + " è stata inserita.");
            helper.getReporter().result("L'icona " + campi.get(i) + " è stata cliccata.");
        }

    }

    public String restOTP(String URI) throws IOException {
        String OTP;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URI)
                .build();
        Response response = client.newCall(request).execute();
        String resStr = response.body().string();
        Pattern p = Pattern.compile("(?<=OTP\\s)\\d{6}");
        Matcher m = p.matcher(resStr);
        ArrayList otp = new ArrayList();

        while(m.find()) {
            otp.add(m.group());
        }
        OTP = (String)otp.get(0);

        return OTP;
    }

    public void insert_request_OTP(AndroidAddonHelper helper, String element, String URI, String conferma) throws IOException {
        AndroidDriver driver = helper.getDriver();
        String OTP;
        OTP = this.restOTP(URI);
        this.insertValueByClassName(helper, OTP, element);

        driver.findElementByXPath(conferma).click();

    }
    public boolean waitElement (AndroidAddonHelper helper, By we, int time){
        AndroidDriver driver = helper.getDriver();
        WebDriverWait wait =new WebDriverWait(driver, time);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(we)).isDisplayed();

    }

    public static void scrollDown(AndroidDriver driver)
    {
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
    }

    public static void scrollUp(AndroidDriver driver)
    {
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollBackward()"));
    }

    public String generatePhoneNumber(String prefiz, int type) {
        Random rand = new Random();
        int number = rand.nextInt(type);
        String randomPhoneNumber = prefiz+number;
        return randomPhoneNumber;
    }

}
