package main.Addon.Web;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.WebAction;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

@Action(name="WEB_Clear And Insert Text")
public class ClearAndInsertText implements WebAction {

    @Parameter(direction = ParameterDirection.INPUT)
    public String xpathElement;

    @Parameter(direction = ParameterDirection.INPUT)
    public String keys;


    @Override
    public ExecutionResult execute(WebAddonHelper helper) throws FailureException {

        WebDriver driver = helper.getDriver();

        WebElement textBox = driver.findElement(By.xpath(xpathElement));

        for (int i = 0; i < 8; i++) {
            textBox.sendKeys(Keys.BACK_SPACE);
        }

        textBox.sendKeys(keys);

        return ExecutionResult.PASSED;
    }
}