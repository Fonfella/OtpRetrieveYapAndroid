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
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

@Action(name="Gestione POP-UP IOS")
public class NP_GestionePOPUP implements IOSAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String CheckPointElement;

    @Parameter(direction = ParameterDirection.INPUT)
    public int repeatActionNumber;


    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {

        IOSDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        int numeroTentativi = repeatActionNumber -1;

        boolean verify = false;
        for(int i = 0; i < repeatActionNumber; i++) {
            try{
                Thread.sleep(3500);
                if(driver.findElement(By.xpath(CheckPointElement)).isDisplayed()){
                    verify = true;
                    report.result("Elemento Trovato!!!");
                    break;
                }
                //da provare questa parte
               if (i == numeroTentativi)  {
                   if(driver.findElement(By.xpath("//*[@name='Non ora']")).isDisplayed()){
                       driver.findElement(By.xpath("//*[@name='Non ora']")).click();
                       driver.findElement(By.xpath(CheckPointElement)).isDisplayed();
                       verify = true;
                       report.result("Elemento Trovato!!!");
                       break;
                   }
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