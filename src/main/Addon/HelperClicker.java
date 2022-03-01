package main.Addon;


import com.google.common.collect.ImmutableList;
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
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;

@Action(name="Android HelperCliker")
public class HelperClicker implements AndroidAction {

    @Parameter(defaultValue = "0.9")
    private double xPct;

    @Parameter(defaultValue = "0.5")
    private double yPct;

    @Parameter(defaultValue = "")
    private String propertyValue;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) {

//        double xPct = 0.5;
//        double yPct = 0.8;
//        String propertyValue = "//*[@resource-id='it.nexi.yap.stg:id/family_description']";

        AndroidDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();

        MobileElement elb = (MobileElement)driver.findElementByXPath(propertyValue);


        Rectangle elRect = elb.getRect();
        Point point = new Point(
                elRect.x + (int)(elRect.getWidth() * xPct),
                elRect.y + (int)(elRect.getHeight() * yPct)
        );

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), point.x, point.y));
        tap.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(input, Duration.ofMillis(200)));
        tap.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(tap));

        report.result("coordinate usate: "+point);
        return ExecutionResult.PASSED;
    }
}
