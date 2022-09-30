package main.Addon.Generic;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.GenericAction;
import io.testproject.java.sdk.v2.addons.helpers.AddonHelper;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import main.Addon.Methods;

@Action(name = "Generate Random Phone Number Generic")
public class GenerateRandomePhoneNumberGeneric implements GenericAction {
    Methods method = new Methods();
//
   @Parameter(direction = ParameterDirection.INPUT)
    public String prefiz;

    @Parameter(direction = ParameterDirection.INPUT)
    public int type;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String finalNumber;

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String criptoNumber;


    public ExecutionResult execute(AddonHelper helper) throws FailureException {
        ActionReporter report = helper.getReporter();
        finalNumber = method.generatePhoneNumber(prefiz, type);
        report.result("Il numero creato è: " +finalNumber);
        String crypto = finalNumber.substring(finalNumber.length()-2);
        criptoNumber = "*****"+crypto;
        return ExecutionResult.PASSED;
    }
}
