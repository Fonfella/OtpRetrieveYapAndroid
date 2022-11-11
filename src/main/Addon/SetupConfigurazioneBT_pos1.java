package main.Addon;

import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import junit.framework.Assert;
import org.openqa.selenium.By;


@Action(name = "MPOS Android configurazione BT_pos1")
public class SetupConfigurazioneBT_pos1 implements AndroidAction {
    Methods method = new Methods();
    Play play = new Play();

    @Parameter(direction = ParameterDirection.INPUT)
    public int times;


    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        AndroidDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        String pos = "//*[contains(@text,'P162')]";
        String riprova = "//*[contains(@text,'RIPROVA')]";
        String asscocia = "//android.widget.Button[@text = 'Associa' or @text = 'OK']";
        String associatoOk = "//android.widget.TextView[@text = 'Associato']";


//        String [] parameters = parameterOption.split(",");
//        String deviceName = parameters[0];
//        String platformVersion = parameters[1];
//        String udid = parameters[2];

        driver.findElementByXPath(pos).click();

        for (int i = 0; i < times; i++) {
             try {
                try {
                    if (driver.findElementByXPath(asscocia).isDisplayed()) {
                        driver.findElementByXPath(asscocia).click();
                     //   method.callFinger(deviceName, platformVersion, udid, address);
                        play.playFingerBot();
                        driver.findElementByXPath(associatoOk).isDisplayed();
                       // driver.findElementByXPath(riprova).click();
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Assert.assertTrue(method.waitElement(helper, By.xpath(riprova), 20));
                driver.findElementByXPath(riprova).click();
//                try {
//                    if (driver.findElementByXPath(associatoOk).isDisplayed()) {
//                        break;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                driver.findElementByXPath(pos).click();
                driver.findElementByXPath(asscocia).click();
                //method.callFinger(deviceName, platformVersion, udid, address);
                 play.playFingerBot();
                driver.findElementByXPath(associatoOk).isDisplayed();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        return ExecutionResult.PASSED;
    }
}
