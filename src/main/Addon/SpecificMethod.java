package main.Addon;

import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.io.IOException;

public class SpecificMethod {
    static Methods method = new Methods();

    public void manageOtpForSomeAction(AndroidDriver driver,
                                              AndroidAddonHelper helper,
                                              String INSERT_1,
                                              String INSERT_2,
                                              String INSERT_3,
                                              String INSERT_4,
                                              String INSERT_5,
                                              String INSERT_6,
                                              String [] array_otp) {

        driver.findElementByXPath(INSERT_1).sendKeys(array_otp[0]);
        Assert.assertTrue(method.waitElement(helper, By.xpath(INSERT_2),10));
        driver.findElementByXPath(INSERT_2).sendKeys(array_otp[1]);
        Assert.assertTrue(method.waitElement(helper,By.xpath(INSERT_3),10));
        driver.findElementByXPath(INSERT_3).sendKeys(array_otp[2]);
        Assert.assertTrue(method.waitElement(helper,By.xpath(INSERT_4),10));
        driver.findElementByXPath(INSERT_4).sendKeys(array_otp[3]);
        Assert.assertTrue(method.waitElement(helper,By.xpath(INSERT_5),10));
        driver.findElementByXPath(INSERT_5).sendKeys(array_otp[4]);
        Assert.assertTrue(method.waitElement(helper,By.xpath(INSERT_6),10));
        driver.findElementByXPath(INSERT_6).sendKeys(array_otp[5]);

    }

    public void manageScreenSceglieneUno (AndroidDriver driver,
                                          String SCEGLIENE_UNO,
                                          String SELEZIONA_NUMERO,
                                          String BOTTONE_CONTINUA
                                          ) {
        try {
            Thread.sleep(3000);
            if (driver.findElement(By.xpath(SCEGLIENE_UNO)).isDisplayed()== true) {
                driver.findElement(By.xpath(SCEGLIENE_UNO)).click();
                driver.findElement(By.xpath(SELEZIONA_NUMERO)).click();
                driver.findElement(By.xpath(BOTTONE_CONTINUA)).click();
            }
        } catch (Exception e) {
            System.out.println("oggetto scegliene uno non trovato, interazione skippata");
        }
    }

    public String[] getFinalOtp (String URI) {

        String OTP = "";
        try {
            OTP = method.restOTP(URI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("L'OTP OPERAZIONE NUMERO è: " + OTP);
        String[] array_otp = OTP.split("(?<=\\G.{1})");
        return array_otp;
    }
}
