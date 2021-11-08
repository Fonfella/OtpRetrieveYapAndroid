package main.Addon;


import io.testproject.java.annotations.v2.Action;
import io.testproject.java.sdk.v2.addons.WebAction;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

@Action(name="Clear Input")
public class ClearInput implements WebAction {

    @Override
    public ExecutionResult execute(WebAddonHelper webAddonHelper) throws FailureException {

        //Get Web Driver instance
        WebDriver driver = webAddonHelper.getDriver();

        WebElement el = driver.findElement(new By.ByXPath("//div/div[2]/input"));
        el.clear();
        el.sendKeys("CIao sono la prova dell addon creato");

        return ExecutionResult.PASSED;
    }
}
