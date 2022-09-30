package main.Addon.Generic;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.GenericAction;
import io.testproject.java.sdk.v2.addons.helpers.AddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import main.Addon.SpecificMethod;

@Action(name = "Recupero OTP Generico UNICO")
public class RecuperoOtpGenericoUnico implements GenericAction {
    SpecificMethod generate = new SpecificMethod();

    @Parameter(direction = ParameterDirection.INPUT)
    public String URI;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String OTP;


    public ExecutionResult execute(AddonHelper helper) throws FailureException {
        ActionReporter report = helper.getReporter();
        String [] array_otp = generate.getFinalOtp(URI);

        OTP = array_otp[0]+
              array_otp[1]+
              array_otp[2]+
              array_otp[3]+
              array_otp[4]+
              array_otp[5];

        report.result("Codice OTP trovato: " + OTP);
        return ExecutionResult.PASSED;
    }
}
