package main.Addon;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;


@Action(name = " MPOS Android Verifica Soglia MIN/MAX")
public class AndroidVerificaSogliaMinMax implements AndroidAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String LastValue;

    @Parameter(direction = ParameterDirection.INPUT)
    public String Soglia;

    @Parameter(direction = ParameterDirection.INPUT)
    public String MaxMin;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        ActionReporter report = helper.getReporter();
        String[] Value = LastValue.split("\\s+");
        String StrNum = Value[0];
        StrNum = StrNum.replaceAll("," , ".");
        float floatNum = Float.parseFloat(StrNum);
        String[]  ValS= Soglia.split("\\s+");
        String StrSoglia = ValS[0];
        StrSoglia = StrSoglia.replaceAll("," , ".");
        float floatSoglia = Float.parseFloat(StrSoglia);
        boolean funziona = false;
        switch (MaxMin){
            case "MIN":                 //Se si preme 1 verrà settato la soglia minima (tutti i valori stampati saranno superiori alla soglia)
                if( floatSoglia <= floatNum){
                    report.result("Verifica soglia MINIMA OK!");
                    return  ExecutionResult.PASSED;
                }
                break;
            case "MAX":                 //Se si preme 2 verrà settata la soglia massima (tutti i valori stampati saranno inferiori alla soglia)
                if( floatSoglia >= floatNum){
                    report.result("Verifica soglia MASSIMA OK!");
                    return  ExecutionResult.PASSED;
                }
                break;
        }
        report.result("Verifica soglia ERRATA!!!");
        return  ExecutionResult.FAILED;

    }
}
