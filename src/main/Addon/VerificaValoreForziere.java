package main.Addon;


import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;

@Action(name="Yap Verifica Saldo Generico")
public class VerificaValoreForziere implements AndroidAction {

    @Parameter(defaultValue = "55") //primovaloresalvato
    public String valorePrecedente;

    @Parameter(defaultValue = "5") //monete
    public String valoreRicarica;

    @Parameter(defaultValue = "5") //monete
    public String valoreCorrente;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

     //   valorePrecedente = "33";
     //   valoreRicarica = "+1";
     //   valoreCorrente = "34";

        AndroidDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();


        int a = Integer.parseInt(valorePrecedente);
        int b = Integer.parseInt(valoreCorrente);
        int c = Integer.parseInt(valoreRicarica);

        if (b == a + c ) {
            report.result("Il saldo Aggiornato Correttamente");
            return ExecutionResult.PASSED;
        }

        report.result("IL saldo NON e' Aggiornato");
        return ExecutionResult.FAILED;
    }

}
