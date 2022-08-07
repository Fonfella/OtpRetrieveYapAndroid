package main.Addon;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;

import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;

import java.io.IOException;

@Action(name="OTP Insert")
public class OTP_Insert  implements AndroidAction{

    Methods method = new Methods();

    @Parameter(direction = ParameterDirection.INPUT)
    public String element;

    @Parameter(direction = ParameterDirection.INPUT)
    public String URI;

    @Parameter(direction = ParameterDirection.INPUT)
    public String email;


    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        AndroidDriver driver = helper.getDriver();
        String OTP = "";
        try {
            OTP = method.restOTP(URI+email);
        } catch (IOException e) {
            e.printStackTrace();
        }

        method.insertValueByClassName(helper, OTP, element);


        return ExecutionResult.PASSED;
    }




}

