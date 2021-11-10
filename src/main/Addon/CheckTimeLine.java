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
import org.openqa.selenium.Rectangle;
import java.util.concurrent.TimeUnit;

@Action(name="Yap Check TimeLine")
public class CheckTimeLine implements AndroidAction {

    private static final float HORIZONTAL_MARGIN_PERCENTAGE = 0.2f;
    private static final float VERTICAL_MARGIN_PERCENTAGE = 0.1f;
    private static final String det = "[DETAILS] ";

    @Parameter(defaultValue = "Diego Lettieri")
    private String user;

    @Parameter(defaultValue = "+1,00€")
    private String amount;

    @Parameter(defaultValue = "")
    private String message;

    @Parameter(defaultValue = "HAI CHIESTO")
    private String type;

    public void setUser(String user) { this.user = user; }

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

        AndroidDriver driver = helper.getDriver();

        int xStart;
        int xEnd;
        int yStart;
        int yEnd;

        MobileElement el1 = null;
        MobileElement date = null;
        try {
            if (driver.findElementByXPath("//*[@resource-id='it.nexi.yap.stg:id/text_message']").isDisplayed() == true) {
                el1 = (MobileElement) driver.findElementByXPath("//*[@resource-id='it.nexi.yap.stg:id/text_message']");
                message = el1.getText();
                date = (MobileElement) driver.findElementById("it.nexi.yap.stg:id/text_date");
            }
        } catch (Exception e) {
            el1 = (MobileElement) driver.findElementById("it.nexi.yap.stg:id/text_date");
        }


        String el1text = el1.getText();
        String dateText = date.getText();
        String val = "";
        try {
            if (driver.findElementByXPath("//*[@resource-id='it.nexi.yap.stg:id/button_primary']").isDisplayed() == true) {
                Thread.sleep(1000);
                driver.findElementByXPath("//*[@resource-id='it.nexi.yap.stg:id/button_primary']").click();
                 val = "//android.widget.TextView[@text = '"+message+"']";
            }
        } catch (Exception e) {
            driver.findElementByXPath("//*[@resource-id='it.nexi.yap.stg:id/button_continue']").click();
            String[] parts = el1text.split("• ");
            String part2 = parts[1]; // Hours
            String v = "//*[contains(@text,sost)]";
            String ch = "\'";
            String toCheck = ch + "Oggi • " + part2 + ch;
            val = v.replaceAll("sost", toCheck);
            el1text = toCheck;
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MobileElement variable = (MobileElement) driver.findElementById("it.nexi.yap.stg:id/view_root");
        Rectangle targetRectangle = variable.getRect();
        xStart = Math.round(targetRectangle.getX() + targetRectangle.getWidth() / 2);
        xEnd = xStart;
        yStart = Math.round(targetRectangle.getY() + (1 - VERTICAL_MARGIN_PERCENTAGE) * targetRectangle.getHeight());
        yEnd = Math.round(targetRectangle.getY() + VERTICAL_MARGIN_PERCENTAGE * targetRectangle.getHeight());
        TouchAction action = new TouchAction(driver);
        action.longPress(PointOption.point(xStart, yStart))
                .moveTo(PointOption.point(xEnd, yEnd))
                .release()
                .perform();
        // driver.findElementById ( "it.nexi.yap.stg:id/view_close" ).click ();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        MobileElement elt = (MobileElement) driver.findElementById("it.nexi.yap.stg:id/container_timeline");
        targetRectangle = elt.getRect();
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
                    break;
                }
            } catch (Exception e) {

                action = new TouchAction(driver);
                action.longPress(PointOption.point(xStart, yStart))
                        .moveTo(PointOption.point(xEnd, yEnd))
                        .release()
                        .perform();
            }
            i++;
            System.out.println(det + "I searching the object in Timeline, attempt number: " + i + " - " + val);
        } while (i <= 20);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement el2 = (MobileElement) driver.findElementById("it.nexi.yap.stg:id/text_title");
  //      user = "Diego Lettieri";
        if (user.equals("Null") == false) {
            System.out.println(det + "Check TimeLine User");
            System.out.println(det + "Expected values: [" + user + "]");
            System.out.println(det + "Actual values: [" + el2.getText() + "]");
            if (user.equals(el2.getText())) {
                System.out.println(det + "TimeLine User is OK");
            } else {
                System.out.println(det + "TimeLine User is KO");
                driver.close();
            }
        }
    //    amount = "-1,00€";
        MobileElement el3 = (MobileElement) driver.findElementById("it.nexi.yap.stg:id/text_amount");
        if (amount.equals("Null") == false) {
            System.out.println(det + "Check TimeLine Amount");
            System.out.println(det + "Expected values: [" + amount + "]");
            System.out.println(det + "Actual values: [" + el3.getText() + "]");
            if (amount.equals(el3.getText())) {
                System.out.println(det + "TimeLine Amount is OK");
            } else {
                System.out.println(det + "TimeLine Amount is KO");
                driver.close();
            }
        }

        MobileElement el4 = (MobileElement) driver.findElementById("it.nexi.yap.stg:id/text_date");
        if (dateText.contains(el4.getText())) {
            System.out.println(det + "Check TimeLine Date");
            System.out.println(det + "Expected values:: [" + dateText + "]");
            System.out.println(det + "Actual values: [" + el4.getText() + "]");
            System.out.println(det + "TimeLine Date is OK");
        } else {
            System.out.println(det + "TimeLine Date is KO");
            driver.close();
        }

        try {
            if (driver.findElementById("it.nexi.yap.stg:id/text_message").isDisplayed()) {
                MobileElement el5 = (MobileElement) driver.findElementById("it.nexi.yap.stg:id/text_message");
                if (message.equals("Null") == false) {
                    System.out.println(det + "Check TimeLine Message");
                    System.out.println(det + "Expected values:: [" + message + "]");
                    System.out.println(det + "Actual values: [" + el5.getText() + "]");
                    if (message.equals(el5.getText())) {
                        System.out.println(det + "TimeLine Message is OK");
                    } else {
                        System.out.println(det + "TimeLine Message is KO");
                        driver.close();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(det + "TimeLine Message: Not Present");
        }
        try {
            if (driver.findElementById("it.nexi.yap.stg:id/text_type").isDisplayed()) {

          //      type = "HAI CHIESTO";
                MobileElement el6 = (MobileElement) driver.findElementById("it.nexi.yap.stg:id/text_type");
                if (type.equals("Null") == false) {
                    System.out.println(det + "Check TimeLine Type");
                    System.out.println(det + "Expected values:: [" + type + "]");
                    System.out.println(det + "Actual values: [" + el6.getText() + "]");
                    if (type.equals(el6.getText())) {
                        System.out.println(det + "TimeLine Type is OK");
                    } else {
                        System.out.println(det + "TimeLine Type is KO");
                        driver.close();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(det + "TimeLine Type: Not Present");
        }
        try {
            if (driver.findElementById("it.nexi.yap.stg:id/tl_swipe_cancel").isDisplayed() == true) {
                System.out.println(det + "I Delete the Event");
                driver.findElementById("it.nexi.yap.stg:id/tl_swipe_cancel").click();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                try {
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    MobileElement elb = (MobileElement) driver.findElementById("it.nexi.yap.stg:id/snackbar_text");
                    //    driver.findElementById(warningToCheck).isDisplayed ();
                    //    MobileElement el1 = (MobileElement)driver.findElementById ( warningToCheck );
                    elb.isDisplayed();
                    System.out.println(det + "Check Warning: OK -> [" + elb.getText() + "]");
                } catch (Exception e) {
                    System.out.println("[WARNING] Check Warning: KO");
                }
                do {
                    try {
                        Thread.sleep(2000);
                        String var = "//android.widget.TextView[@text = '"+message+"']";
                        if (driver.findElementByXPath(var).isDisplayed()) {
                            Thread.sleep(2000);
                            driver.findElementByXPath(var).click();
                            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                            driver.findElementById("it.nexi.yap.stg:id/tl_swipe_delete").click();
                            System.out.println(det + "Event Deleted");
                            break;
                            //android.widget.TextView[@text = '12345']
                        }
                    } catch (Exception e) {
                        action = new TouchAction(driver);
                        action.longPress(PointOption.point(xStart, yStart))
                                .moveTo(PointOption.point(xEnd, yEnd))
                                .release()
                                .perform();
                    }
                    i++;
                } while (i <= 20);
            }
        } catch (Exception e) {
            System.out.println(det + "Event Cannot be Deleted");
        }
        return ExecutionResult.PASSED;
    }
}
