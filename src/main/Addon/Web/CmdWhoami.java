package main.Addon.Web;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.WebAction;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Action(name="WEB_Whoami_Windows")
public class CmdWhoami implements WebAction {

    @Parameter(direction = ParameterDirection.OUTPUT)
    public String userName;

    @Override
    public ExecutionResult execute(WebAddonHelper helper) throws FailureException {

       // WebDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        String machine = null;
        String line;
        String [] var = null;

        if (isWindows) {
            report.result("windows");
            machine = "windows";
        } else {
            report.result("OSX");
            machine = "OSX";
        }

        if (machine.equals("windows")) {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "whoami");
            builder.redirectErrorStream(true);
            Process p = null;

            try {
                p = builder.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while (true) {
                try {
                    line = r.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (line == null) { break; }
                var = line.split("\\\\");
            }
        }
//parte mac da provare
        if (machine.equals("OSX")) {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "whoami");
            builder.redirectErrorStream(true);
            Process p = null;

            try {
                p = builder.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while (true) {
                try {
                    line = r.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (line == null) { break; }
                var = line.split("\\\\");
            }
        }



        report.result(userName = var[1]);
        return ExecutionResult.PASSED;
    }
}