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

@Action(name = "Gestione path location")
public class SettingPathLocation implements GenericAction {

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String local_Path;

    public ExecutionResult execute(AddonHelper helper) throws FailureException {
        ActionReporter report = helper.getReporter();

        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        if (isWindows) {
            report.result("windows");
            local_Path= "C:\\TESTPROJECT-IMG";
        } else {
            report.result("OSX");
            local_Path = "/Users/testfactorys/TESTPROJECT-IMG";
        }

        return ExecutionResult.PASSED;
    }
}
