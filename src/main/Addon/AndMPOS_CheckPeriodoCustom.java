package main.Addon;

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

@Action(name="MPOS Android Check PeriodoSelezionato Custom")
public class AndMPOS_CheckPeriodoCustom implements AndroidAction {
    //decommentare prima di push addon
    Methods methods = new Methods();

    @Parameter(defaultValue = "")
    public String primoPeriodo_Giorno;

    @Parameter(defaultValue = "")
    public String GetCurrenteDate;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String periodoDaVerificare;


    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        AndroidDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        String [] array = GetCurrenteDate.split("[.]");
        String meseDiRifemento = array[1];
        int meseDiRifementoNUM = Integer.parseInt(meseDiRifemento);

        String periodoIniziale = primoPeriodo_Giorno+"."+String.valueOf(meseDiRifementoNUM)+"."+array[2];

       // String string1 = string.replaceAll("[A-Z][a-z]+", String.valueOf(unMesePrimaRiremento));
        periodoDaVerificare = periodoIniziale +" - "+GetCurrenteDate;

        driver.findElement(By.id("it.nexi.mpos:id/2131362209")).click();
        driver.findElement(By.xpath("//*[@text='1']")).click();

        String xpathSecondoGiorno = "//*[@text='sost']";
        String xpath = xpathSecondoGiorno.replaceAll("sost", array[0]);
        driver.findElement(By.xpath(xpath)).click();
        driver.findElement(By.id("it.nexi.mpos:id/2131362244")).click();

        report.result("periodoDaVerificare = " +periodoDaVerificare);
        return ExecutionResult.PASSED;
    }
}