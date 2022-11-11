package main.General;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.GenericAction;
import io.testproject.java.sdk.v2.addons.helpers.AddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;


@Action(name = " MPOS Android Verifica Soglia MIN/MAX")
public class AndroidVerificaSogliaMinMax implements GenericAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String LastValue;

    @Parameter(direction = ParameterDirection.INPUT)
    public String Soglia;

    @Parameter(direction = ParameterDirection.INPUT)
    public String MaxMin;

    @Override
    public ExecutionResult execute(AddonHelper helper) throws FailureException {
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
                    report.result("PASSED: la transazione "+LastValue+" è superiore alla soglia minima accettabile ("+Soglia+").");
                    return  ExecutionResult.PASSED;
                }else{
                    report.result("FAILED: la transazione "+LastValue+" è al di sotto della soglia minima accettabile ("+Soglia+").");
                    return  ExecutionResult.FAILED;
                }
            case "MAX":                 //Se si preme 2 verrà settata la soglia massima (tutti i valori stampati saranno inferiori alla soglia)
                if( floatSoglia >= floatNum){
                    report.result("PASSED: la transazione "+LastValue+" è inferiore alla soglia massima ("+Soglia+").");
                    return  ExecutionResult.PASSED;
                }else{
                    report.result("FAILED: la transazione "+LastValue+" supera la soglia massima accettabile ("+Soglia+").");
                    return  ExecutionResult.FAILED;
                }
        }
        report.result("Unexpeted error: Riprova");
        return  ExecutionResult.FAILED;
    }
}
