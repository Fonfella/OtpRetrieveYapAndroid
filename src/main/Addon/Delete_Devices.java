package main.Addon;

import io.appium.java_client.android.AndroidElement;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.AndroidElementAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;

import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

@Action(name="Delete Devices")
public class Delete_Devices implements AndroidAction {
    Methods method = new Methods();

    @Parameter(direction = ParameterDirection.INPUT)
    public String VERIFICA_IDENTITA;

    @Parameter(direction = ParameterDirection.INPUT)
    public String ICONA_TRASH;

    @Parameter(direction = ParameterDirection.INPUT)
    public String CONFERMA_ELIMINA;

    @Parameter(direction = ParameterDirection.INPUT)
    public String ALTRI_DISPOSITIVI;

    @Parameter(direction = ParameterDirection.INPUT)
    public String URI;

    @Parameter(direction = ParameterDirection.INPUT)
    public String email;

    @Parameter(direction = ParameterDirection.INPUT)
    public String element;

    @Parameter(direction = ParameterDirection.INPUT)
    public String CONFERMA_OTP;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        AndroidDriver driver = helper.getDriver();
        //usa findElementsBy che ritorna al più una LISTA VUOTA, quindi di size = 0
        //così se non trova elementi non va in eccezione, all'ultimo step
        //
        while((driver.findElementsByXPath(ICONA_TRASH)).size()>0) {
            Assert.assertTrue(method.waitElement(helper,By.xpath(ICONA_TRASH) ,10));
            driver.findElementByXPath(ICONA_TRASH).click();
            helper.getReporter().result("L'icona " + ICONA_TRASH + " è stata cliccata.");
            driver.findElementByXPath(CONFERMA_ELIMINA).click();
            Assert.assertTrue(method.waitElement(helper,By.xpath(VERIFICA_IDENTITA),10));
            String OTP = "";
            try {
                OTP = method.restOTP(URI+email);
            } catch (IOException e) {
                e.printStackTrace();
            }
            helper.getReporter().result("L'OTP corrente è " + OTP);
            method.insertValueByClassName(helper, OTP, element);

            driver.findElementByXPath(CONFERMA_OTP).click();

            //new OTP_Insert().execute(helper);

            driver.findElementByXPath(ALTRI_DISPOSITIVI).click();

            //i++;


        }
        return ExecutionResult.PASSED;
    }


}
