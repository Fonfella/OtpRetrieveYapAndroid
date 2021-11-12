package main.Addon;


import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;

@Action(name="Yap Verifica Saldo dopo Ricarica")
public class VerificaSaldo implements AndroidAction {

    @Parameter(defaultValue = "55")
    public String valorePrecedente;

    @Parameter(defaultValue = "5")
    public String valoreRicarica;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

    //    valorePrecedente = "55,00";
    //    valoreRicarica = "0";

        AndroidDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();

        String valoreCorrente = driver.findElementById("it.nexi.yap.stg:id/text_balance").getText();

        String [] impsenzavirgolaCorrente = valoreCorrente.split(",");
        String var1 = impsenzavirgolaCorrente[0];

        String [] impsenzavirgolaPrecedente = valorePrecedente.split(",");
        String var2 = impsenzavirgolaPrecedente[0];

        int a = Integer.parseInt(var1);
        int b = Integer.parseInt(var2);
        int c = Integer.parseInt(valoreRicarica);

        if (a == b + c ) {
            report.result("Il saldo è correttamente aggiornato");
            return ExecutionResult.PASSED;
        }

        report.result("IL saldo NON e' Aggiornato");
        return ExecutionResult.FAILED;
    }

}
