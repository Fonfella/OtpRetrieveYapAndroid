package main.iOS;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.IOSAction;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.drivers.IOSDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import main.Addon.Methods;
import main.Addon.Play;


@Action(name = "MPOS iOS configurazione BT_pos1")
public class iOS_SetupConfigurazioneBT_pos1 implements IOSAction {
    Methods method = new Methods();
    Play play = new Play();
    
    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {
        IOSDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        play.playFingerBot();



        return ExecutionResult.PASSED;
    }
}
