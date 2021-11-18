package main.Addon;


import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import org.openqa.selenium.Rectangle;
import java.util.concurrent.TimeUnit;

@Action(name="Yap Android Dettaglio Eventi")
public class AndroidDettaglioEventi implements AndroidAction {


    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

        AndroidDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();
        try {
            MobileElement elb = (MobileElement)driver.findElementById ( "it.nexi.yap.stg:id/snackbar_text" );
            report.result ("Dettaglio Evento -> ["+elb.getText ()+"]");
        } catch(Exception e) {
            report.result("[WARNING] Dettaglio Evento: KO" );
        }
        return ExecutionResult.PASSED;
    }
}
