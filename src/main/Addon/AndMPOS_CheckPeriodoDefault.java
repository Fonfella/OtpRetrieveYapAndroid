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

import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.FlagTerm;
import java.util.Properties;

@Action(name="MPOS Android Check PeriodoSelezionato Default")
public class AndMPOS_CheckPeriodoDefault implements AndroidAction {
    //decommentare prima di push addon
    Methods methods = new Methods();

    @Parameter(defaultValue = "")
    public String primoPeriodo; //GET TEXT ELEMENTO NEI FILTRI CAMPO INIZIO

    @Parameter(defaultValue = "")
    public String secondoPeriodo; //GET TEXT ELEMENTO NEI FILTRI CAMPO FINE

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String periodoDaVerificare;


    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        AndroidDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();
        //DOM 21 AGO '22 inputParameter_1
        //MAR 20 SET '22 inputParameter_2
        String finalFirst = methods.getCorretFormat(primoPeriodo);
        String finalSecond = methods.getCorretFormat(secondoPeriodo);
        periodoDaVerificare = (finalFirst + " - " + finalSecond);

        report.result(periodoDaVerificare);
        return ExecutionResult.PASSED;
    }
}