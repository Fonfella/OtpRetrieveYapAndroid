package main.Addon;


import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;

@Action(name="Android Verifica Saldo")
public class VerificaSaldo implements AndroidAction {

    @Parameter(defaultValue = "55")
    public String valoreCorrente;

    @Parameter(defaultValue = "55")
    public String valorePrecedente;

    @Parameter(defaultValue = "5")
    public String valoreRicarica;

    @Parameter(defaultValue = "somma", description = "opzionale")
    public String operazione;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

//        valorePrecedente = "138,00";
//        valoreRicarica = "15";
//        operazione="differenza";
//        valoreCorrente = "128";

        if (operazione.equals(null) || operazione.length() == 0) {
            operazione = "somma";
        }

        AndroidDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();

        String [] impsenzavirgolaCorrente = valoreCorrente.split(",");
        String var1 = impsenzavirgolaCorrente[0];

        String [] impsenzavirgolaPrecedente = valorePrecedente.split(",");
        String var2 = impsenzavirgolaPrecedente[0];

        int a = Integer.parseInt(var1);
        int b = Integer.parseInt(var2);
        int c = Integer.parseInt(valoreRicarica);
        String o = null;

        if (operazione.equals("somma") || operazione.equals("")) {
            o = var2 + "+" + valoreRicarica + "=" + var1;
            if (a == b + c) {
                report.result("Il saldo è correttamente aggiornato ["+o+"]");
                return ExecutionResult.PASSED;
            }
        }

        if (operazione.equals("differenza") || operazione.equals("sottrazione")) {
            o = var2 + "-" + valoreRicarica + "=" + var1;
            if (a == b - c ) {
                report.result("Il saldo è correttamente aggiornato ["+o+"]");
                return ExecutionResult.PASSED;
            }
        }

        report.result("IL saldo NON e' Aggiornato["+o+"]");
        return ExecutionResult.FAILED;
    }
}
