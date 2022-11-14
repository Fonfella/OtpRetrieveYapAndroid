package main.General;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.GenericAction;
import io.testproject.java.sdk.v2.addons.helpers.AddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import main.Addon.Play;


@Action(name = " MPOS FingerPlay")
public class FingerPlay implements GenericAction {


    @Override
    public ExecutionResult execute(AddonHelper helper) throws FailureException {
        ActionReporter report = helper.getReporter();

        Play play = new Play();
        String result = play.playFingerBot();

        report.result("Finger Play Ok"+result);
        return  ExecutionResult.PASSED;
    }
}
