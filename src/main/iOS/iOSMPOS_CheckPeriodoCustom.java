package main.iOS;

import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.IOSAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.drivers.IOSDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import main.Addon.Methods;
import org.openqa.selenium.By;

@Action(name="MPOS iOS Check PeriodoSelezionato Custom")
public class iOSMPOS_CheckPeriodoCustom implements IOSAction {
    //decommentare prima di push addon
    Methods methods = new Methods();

    @Parameter(defaultValue = "")
    public String giornoDiRiferimentoPeriodo;

    @Parameter(defaultValue = "")
    public String GetCurrenteDate;

    @Parameter(defaultValue = "")
    public String formattingDataConZero;

    @Parameter(defaultValue = "")
    public String bottoneInizioXpath;

    @Parameter(defaultValue = "")
    public String bottoneFineXpath;
    @Parameter(defaultValue = "")
    public String pickerWheelXpath_Giorno;

    @Parameter(defaultValue = "")
    public String pickerWheelXpath_Mese;

    @Parameter(defaultValue = "")
    public String pickerWheelXpath_Anno;

    @Parameter(defaultValue = "")
    public String bottoneOkXpath;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String periodoDaVerificare;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String periodoDaVerificareLettere;


    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {
        IOSDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        String [] array = GetCurrenteDate.split("[.]");

        //gestione giorno con 0 primo carattere
        String giorno = array[0];
        if (array[0].charAt(0) == '0' ) {
            String [] val = array[0].split("0");
            giorno = val[1];
        }

        String meseDiRifemento = array[1];
        String anno = array[2];

        //metodo per sapere mese in lettere
        String meseInLettere = methods.getMeseInLettere(meseDiRifemento);
        String Long_meseInLettere = methods.getLongMeseInLettere(meseDiRifemento);

        //return meseDiriferimento
        String periodoIniziale = giornoDiRiferimentoPeriodo+"."+ meseDiRifemento +"."+array[2];
        String periodoSecondario = giorno +"."+ meseDiRifemento +"."+ array[2];

        String finalFirst = methods.getCorretFormatiOS(periodoIniziale, formattingDataConZero);
        String finalSecond = methods.getCorretFormatiOS(periodoSecondario, formattingDataConZero);
        periodoDaVerificare = finalFirst +" - "+finalSecond;

        //preparazione format string to check
        String prep = periodoDaVerificare.replaceAll(meseDiRifemento, meseInLettere);
        periodoDaVerificareLettere = prep.replaceAll("\\.", " ");

        //clicco sul bottone INIZIO
        driver.findElement(By.xpath(bottoneInizioXpath)).click();

        //seleziono e clicco primo giorno
//        String xpathGiornoUno = methods.createXpathGiornoDaSelezionare(giornoDiRiferimentoPeriodo);
//        driver.findElement(By.xpath(xpathGiornoUno)).click();

        driver.findElement(By.xpath(pickerWheelXpath_Giorno)).sendKeys(giornoDiRiferimentoPeriodo);
        driver.findElement(By.xpath(pickerWheelXpath_Mese)).sendKeys(Long_meseInLettere);
        driver.findElement(By.xpath(pickerWheelXpath_Anno)).sendKeys(anno);
        driver.findElement(By.xpath(bottoneOkXpath)).click();

        //click bottone fine
        driver.findElement(By.xpath(bottoneFineXpath)).click();
        driver.findElement(By.xpath(pickerWheelXpath_Giorno)).sendKeys(giorno);
        driver.findElement(By.xpath(pickerWheelXpath_Mese)).sendKeys(Long_meseInLettere);
        driver.findElement(By.xpath(pickerWheelXpath_Anno)).sendKeys(anno);



        //seleziono e clicco secondo giorno
//        String xpathSecondoGiorno = methods.createXpathGiornoDaSelezionare(giorno);
//        driver.findElement(By.xpath(xpathSecondoGiorno)).click();

        //click ok button
        driver.findElement(By.xpath(bottoneOkXpath)).click();




        //stampo nel report il periodo selezionato da verificare
        report.result("periodoDaVerificare = " +periodoDaVerificare);
        report.result("Mese lettere = "+periodoDaVerificareLettere);
        return ExecutionResult.PASSED;
    }
}