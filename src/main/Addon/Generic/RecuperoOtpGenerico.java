package main.Addon.Generic;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.GenericAction;
import io.testproject.java.sdk.v2.addons.helpers.AddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import main.Addon.Methods;
import main.Addon.SpecificMethod;

@Action(name = "Recupero OTP Generico")
public class RecuperoOtpGenerico implements GenericAction {
    SpecificMethod generate = new SpecificMethod();

    @Parameter(direction = ParameterDirection.INPUT)
    public String URI;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String OTP_1;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String OTP_2;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String OTP_3;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String OTP_4;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String OTP_5;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String OTP_6;


    public ExecutionResult execute(AddonHelper helper) throws FailureException {
        ActionReporter report = helper.getReporter();
        String [] array_otp = generate.getFinalOtp(URI);

        OTP_1 = array_otp[0];
        OTP_2 = array_otp[1];
        OTP_3 = array_otp[2];
        OTP_4 = array_otp[3];
        OTP_5 = array_otp[4];
        OTP_6 = array_otp[5];

        report.result("Codice OTP trovato: " + OTP_1 + OTP_2 + OTP_3 + OTP_4 + OTP_5 + OTP_6);
        return ExecutionResult.PASSED;
    }
}
