package main.Addon;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;

@Action(name = "Insert Value")
public class Insert_Value implements AndroidAction {
    Methods method = new Methods();

   @Parameter(direction = ParameterDirection.INPUT)
    public String value;

    @Parameter(direction = ParameterDirection.INPUT)
    public String element;


    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

            method.insertValueByClassName(helper, value, element);

        return ExecutionResult.PASSED;
    }




}
