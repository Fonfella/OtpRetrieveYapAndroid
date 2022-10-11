package main.Addon.ios;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.IOSAction;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.drivers.IOSDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import javafx.scene.input.TouchEvent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;

@Action(name="Scroll Element To Direction")
public class NP_ScrollElementToDirection implements IOSAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String direzione;

    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {

        IOSDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        JavascriptExecutor js = driver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", direzione);
        js.executeScript("mobile: swipe", scrollObject);
        
        report.result("Azione Eseguita");
        return ExecutionResult.PASSED;
    }
}
