package main.Addon.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

@Action(name = "PopUpManager")
public class GestionePopUpAndroid implements AndroidAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String CheckPointElement;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

        AndroidDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();
        boolean verify = false;
        for(int i = 0; i < 10; i++) {
            try{
                if(driver.findElement(By.xpath(CheckPointElement)).isDisplayed()){
                    verify = true;
                    report.result("Elemento Trovato!!!");
                    break;
                }
            }catch (Exception e){
               e.printStackTrace();
            }
            driver.navigate().back();
        }
        if (verify == false){
            report.result("PAGINA NON CARICATA!!!");
            return ExecutionResult.FAILED;
        }
        return ExecutionResult.PASSED;
    }
}