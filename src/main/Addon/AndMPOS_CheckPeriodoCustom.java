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
    public String giornoDiRiferimentoPeriodo;

    @Parameter(defaultValue = "")
    public String GetCurrenteDate;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String periodoDaVerificare;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String periodoDaVerificareLettere;

    @Parameter(defaultValue = "")
    public String bottoneInizioXpath;

    @Parameter(defaultValue = "")
    public String bottoneOkXpath;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        AndroidDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        String [] array = GetCurrenteDate.split("[.]");
        String meseDiRifemento = array[1];

        //metodo per sapere mese in lettere
        String meseInLettere = methods.getMeseInLettere(meseDiRifemento);

        //return meseDiriferimento
        String periodoIniziale = giornoDiRiferimentoPeriodo+"."+ meseDiRifemento +"."+array[2];

        periodoDaVerificare = periodoIniziale +" - "+GetCurrenteDate;

        //preparazione format string to check
        String prep = periodoDaVerificare.replaceAll(meseDiRifemento, meseInLettere);
        periodoDaVerificareLettere = prep.replaceAll("\\.", " ");

        //clicco sul bottone INIZIO
        driver.findElement(By.xpath(bottoneInizioXpath)).click();

        //seleziono e clicco primo giorno
        String xpathGiornoUno = methods.createXpathGiornoDaSelezionare(giornoDiRiferimentoPeriodo);
        driver.findElement(By.xpath(xpathGiornoUno)).click();

        //seleziono e clicco secondo giorno
        String xpathSecondoGiorno = methods.createXpathGiornoDaSelezionare(array[0]);
        driver.findElement(By.xpath(xpathSecondoGiorno)).click();

        //click ok button
        driver.findElement(By.xpath(bottoneOkXpath)).click();

        //stampo nel report il periodo selezionato da verificare
        report.result("periodoDaVerificare = " +periodoDaVerificare);
        report.result("Mese lettere = "+periodoDaVerificareLettere);
        return ExecutionResult.PASSED;
    }
}