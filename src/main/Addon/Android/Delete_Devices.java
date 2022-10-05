package main.Addon.Android;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.AndroidElementAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;

import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import main.Addon.Methods;
import main.Addon.SpecificMethod;
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

    SpecificMethod specificMethod = new SpecificMethod();

    @Parameter(direction = ParameterDirection.INPUT)
    public String VERIFICA_IDENTITA= "//android.widget.TextView[@text = concat('VERIFICA D', \"'\", 'IDENTITÀ')]";

    @Parameter(direction = ParameterDirection.INPUT)
    public String ICONA_TRASH = "//android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageView";

    @Parameter(direction = ParameterDirection.INPUT)
    public String CONFERMA_ElIMINA = "//android.widget.Button[@text = 'CONFERMA']";

    @Parameter(direction = ParameterDirection.INPUT)
    public String URL="https://stgapi.nexi.it/mfa/getlastotp?user=";

    @Parameter(direction = ParameterDirection.INPUT)
    public String email="carta.excellence@yopmail.com";

//    @Parameter(direction = ParameterDirection.INPUT)
//    public String CONFERMA_OTP;

    @Parameter(direction = ParameterDirection.INPUT)
    public String SCEGLIENE_UNO = "//android.widget.TextView[@text = 'Scegline uno']";

    @Parameter(direction = ParameterDirection.INPUT)
    public String SELEZIONA_NUMERO = "//android.widget.TextView[@text = '********00']";

    @Parameter(direction = ParameterDirection.INPUT)
    public String BOTTONE_CONTINUA= "//android.widget.Button[@content-desc = 'CONTINUA']";

    @Parameter(direction = ParameterDirection.INPUT)
    public String VEDI_DISPOSITIVI= "//android.widget.TextView[@text = 'VEDI DISPOSITIVI']";

    //Parte OTP_Insert
    @Parameter(direction = ParameterDirection.INPUT)
    public String INSERT_1 = "//android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout//android.widget.EditText";

    @Parameter(direction = ParameterDirection.INPUT)
    public String INSERT_2 = "//android.widget.RelativeLayout[2]/android.widget.RelativeLayout//android.widget.EditText";

    @Parameter(direction = ParameterDirection.INPUT)
    public String INSERT_3 = "//android.widget.RelativeLayout[3]//android.widget.EditText";

    @Parameter(direction = ParameterDirection.INPUT)
    public String INSERT_4 = "//android.widget.RelativeLayout[4]//android.widget.EditText";

    @Parameter(direction = ParameterDirection.INPUT)
    public String INSERT_5 = "//android.widget.RelativeLayout[5]//android.widget.EditText";

    @Parameter(direction = ParameterDirection.INPUT)
    public String INSERT_6 = "//android.widget.RelativeLayout[6]//android.widget.EditText";

    @Parameter(direction = ParameterDirection.INPUT)
    public String ICONA_TRASH_PRINCIPALE = "//android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.ImageView";

    //parametri setup dispositivo in uso
    @Parameter(direction = ParameterDirection.INPUT)
    public String SCEGLINE_UN_ALTRO = "//android.widget.TextView[@content-desc = 'SCEGLINE UN ALTRO button']";

    @Parameter(direction = ParameterDirection.INPUT)
    public String DISPOSITIVO_IN_USO;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        AndroidDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        //usa findElementsBy che ritorna al più una LISTA VUOTA, quindi di size = 0
        //così se non trova elementi non va in eccezione, all'ultimo step
        AndroidElement elementDispositivi = (AndroidElement) driver.findElement(By.xpath("//*[contains(@text,'device')]"));
        String SNDispositivi = elementDispositivi.getText();
        String URI = URL + email;
        String sceglieneUnAltro = null;
        String [] array_otp;
        String[] array = SNDispositivi.split("(?<=\\G.{1})");
        int NDispositivi = Integer.parseInt(array[0]);
        elementDispositivi.click();

      //  String[] array_otp = new String[0];
        if (!(NDispositivi == 1)) {
                try {
                    if (driver.findElement(By.xpath(SCEGLINE_UN_ALTRO)).isDisplayed()) {
                        driver.findElement(By.xpath(SCEGLINE_UN_ALTRO)).click();
                        sceglieneUnAltro = "presente";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //aggiunta selezione dispositivo in uso
                try {
                    if (driver.findElement(By.xpath(DISPOSITIVO_IN_USO)).isDisplayed()) {

                        driver.findElement(By.xpath(DISPOSITIVO_IN_USO)).click();
                        driver.findElement(By.xpath(CONFERMA_ElIMINA)).click();

                        specificMethod.manageScreenSceglieneUno(driver, SCEGLIENE_UNO, SELEZIONA_NUMERO, BOTTONE_CONTINUA);

                        Assert.assertTrue(method.waitElement(helper, By.xpath(VERIFICA_IDENTITA), 10));
                        array_otp = specificMethod.getFinalOtp(URI);

                        specificMethod.manageOtpForSomeAction(driver,
                                                                 helper,
                                                                 INSERT_1,
                                                                 INSERT_2,
                                                                 INSERT_3,
                                                                 INSERT_4,
                                                                 INSERT_5,
                                                                 INSERT_6,
                                                                 array_otp);


                        driver.pressKeyCode(AndroidKeyCode.ENTER);
                        Assert.assertTrue(method.waitElement(helper, By.xpath(VEDI_DISPOSITIVI), 10));
                        driver.findElement(By.xpath(VEDI_DISPOSITIVI)).click();
                    }

                } catch (Exception e) {
                    System.out.println("Dispositivo principale in uso!!!SKIPPING");
                }

                if (sceglieneUnAltro == "presente") {
                    driver.pressKeyCode(AndroidKeyCode.BACK);
                }
                    for (int i = 0; i < NDispositivi; i++) {
                        String element = ICONA_TRASH;
                        try {
                            if (driver.findElement(By.xpath(element)).isDisplayed() == false) {
                                System.out.println("Error!!!");
                            }
                        } catch (Exception e) {
                            System.out.println("Device Secondari non trovati!!!, operazione Skippata");
                            break;
                        }

                        driver.findElement(By.xpath(element)).click();

                        driver.findElement(By.xpath(CONFERMA_ElIMINA)).click();
                        //metodo scegliene uno
                        specificMethod.manageScreenSceglieneUno(driver, SCEGLIENE_UNO, SELEZIONA_NUMERO, BOTTONE_CONTINUA);

                        Assert.assertTrue(method.waitElement(helper, By.xpath(VERIFICA_IDENTITA), 10));
                        array_otp = specificMethod.getFinalOtp(URI);

                        specificMethod.manageOtpForSomeAction(driver,
                                                                helper,
                                                                INSERT_1,
                                                                INSERT_2,
                                                                INSERT_3,
                                                                INSERT_4,
                                                                INSERT_5,
                                                                INSERT_6,
                                                                array_otp);


                        driver.pressKeyCode(AndroidKeyCode.ENTER);
                        Assert.assertTrue(method.waitElement(helper, By.xpath(VEDI_DISPOSITIVI), 10));
                        driver.findElement(By.xpath(VEDI_DISPOSITIVI)).click();
                    }
            }

        //elimina device principale
        //DECOMMENTAREEEEEEEEEEEEEEEEEEE
         Assert.assertTrue(method.waitElement(helper, By.xpath(ICONA_TRASH_PRINCIPALE), 10));
         driver.findElement(By.xpath(ICONA_TRASH_PRINCIPALE)).click();
        //parte verifica testo elimina principale
         driver.findElement(By.xpath(CONFERMA_ElIMINA)).click();
         //    Assert.assertTrue(method.waitElement(helper,By.xpath("//android.widget.TextView[@text = 'Numero di cellulare']"),10));

        //metodo scegliene uno
        specificMethod.manageScreenSceglieneUno(driver, SCEGLIENE_UNO, SELEZIONA_NUMERO, BOTTONE_CONTINUA);

        //DECOMMENTAREEEEEEEEEEEEEEEEEEE
        Assert.assertTrue(method.waitElement(helper,By.xpath(VERIFICA_IDENTITA),10));

        //metodo otp
        array_otp = specificMethod.getFinalOtp(URI);


        specificMethod.manageOtpForSomeAction(driver,
                                                helper,
                                                INSERT_1,
                                                INSERT_2,
                                                INSERT_3,
                                                INSERT_4,
                                                INSERT_5,
                                                INSERT_6,
                                                array_otp);

        driver.pressKeyCode(AndroidKeyCode.ENTER);

        report.result("Dispostivi Correttamente Eliminati!");
        return ExecutionResult.PASSED;
    }
}
