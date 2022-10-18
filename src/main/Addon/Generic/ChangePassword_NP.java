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
import main.Addon.SpecificMethod;
import org.apache.commons.lang3.RandomStringUtils;


//cambiuare implements in GenericAction
@Action(name = "Set Password Iniziale NexiPay")
public class ChangePassword_NP implements GenericAction {

   @Parameter(direction = ParameterDirection.INPUT)
    public String password;


    public ExecutionResult execute(AddonHelper helper) throws FailureException {
        ActionReporter report = helper.getReporter();
        SpecificMethod specificMethod = new SpecificMethod();
        String token = specificMethod.getToken();

        for (int i = 0; i < 6; i++) {
            String generatedString = RandomStringUtils.randomAlphanumeric(8);
            specificMethod.setPassword(token, generatedString);
        }
        String response = specificMethod.setPassword(token, password);

        report.result("Password Ripristinata!");
        report.result("response: "+response);
        return ExecutionResult.PASSED;
    }
}
