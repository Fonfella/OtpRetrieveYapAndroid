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

@Action(name="Yap Cerca Qualcosa TimeLine")
public class CheckTimeLine implements AndroidAction {

    private static final float HORIZONTAL_MARGIN_PERCENTAGE = 0.2f;
    private static final float VERTICAL_MARGIN_PERCENTAGE = 0.1f;

    @Parameter(defaultValue = "Non Vuoto")
    private String message;

    @Parameter(defaultValue = "20")
    private int numeroTentaivi;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

        AndroidDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();

        int xStart;
        int xEnd;
        int yStart;
        int yEnd;

//       message = "aaaaa";
//       numeroTentaivi = 10;

        String val = "//android.widget.TextView[@text = '"+message+"']";

        MobileElement elt = (MobileElement) driver.findElementById("it.nexi.yap.stg:id/container_timeline");
        Rectangle targetRectangle = elt.getRect();
        TouchAction action = new TouchAction(driver);
        xStart = Math.round(targetRectangle.getX() + targetRectangle.getWidth() / 2);
        xEnd = xStart;
        yStart = Math.round(targetRectangle.getY() + (1 - VERTICAL_MARGIN_PERCENTAGE) * targetRectangle.getHeight());
        yEnd = Math.round(targetRectangle.getY() + VERTICAL_MARGIN_PERCENTAGE * targetRectangle.getHeight());

        int i = 0;
        do {
            try {
                if (driver.findElementByXPath(val).isDisplayed()) {
                    Thread.sleep(1000);
                    driver.findElementByXPath(val).click();
                    report.result("Evento Trovato");
                    break;
                }
            } catch (Exception exc) {
                action.longPress(PointOption.point(xStart, yStart))
                        .moveTo(PointOption.point(xEnd, yEnd))
                        .release()
                        .perform();
            }
            i++;
            System.out.println("I searching the object in Timeline, attempt number: " + i + " - " + val);
        } while (i <= numeroTentaivi);

        return ExecutionResult.PASSED;
    }
}
