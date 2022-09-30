package main.Addon.Android;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import main.Addon.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.time.LocalDate;

@Action(name="Insert Date")
public class Insert_Date implements AndroidAction {
    Methods method = new Methods();

    @Parameter(direction = ParameterDirection.INPUT)
    public int ANNO;

    @Parameter(direction = ParameterDirection.INPUT)
    public int MESE;

    @Parameter(direction = ParameterDirection.INPUT)
    public int GIORNO;

    @Parameter(direction = ParameterDirection.INPUT)
    public String INDIETRO;

    @Parameter(direction = ParameterDirection.INPUT)
    public String AVANTI;


    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        AndroidDriver driver = helper.getDriver();

        //ANNO
        String path = "//android.widget.TextView[@text = '" + ANNO + "']";

        int k = 0;
        while (driver.findElementsByXPath(path).size() == 0 && k < 20) {
            try {
                method.scrollUp(driver);
            } catch (InvalidSelectorException e) {
                System.out.println("Backward");
            }
            k++;
        }
        driver.findElementByXPath(path).click();
        //MESE
        Integer meseOggi = LocalDate.now().getMonthValue();
        Integer differenza = MESE - meseOggi;
        if (differenza > 0) {
            int i = 0;
            while (i < differenza) {
                driver.findElementByXPath(AVANTI).click();
                i++;
            }

        } else if (differenza < 0) {
            int i = 0;
            differenza = -differenza;
            while (i < differenza) {
                driver.findElementByXPath(INDIETRO).click();
                i++;
            }

        } else {//differenza ==0
            driver.findElementByXPath("//android.widget.Button[@text = 'OK']").click();
        }
        driver.findElementByXPath("//android.view.View[@text = '" + GIORNO + "']").click();


        return ExecutionResult.PASSED;
    }
}

