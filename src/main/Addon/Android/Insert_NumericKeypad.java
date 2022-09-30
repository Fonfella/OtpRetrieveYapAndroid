package main.Addon.Android;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import main.Addon.Methods;
import org.openqa.selenium.By;

@Action(name="Insert Numeric Keypad")
public class Insert_NumericKeypad implements AndroidAction {
    Methods method = new Methods();

    @Parameter(direction = ParameterDirection.INPUT)
    public String NUMERO;

    @Parameter(direction = ParameterDirection.INPUT)
    public String KEYPAD_CLASS;



    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        AndroidDriver driver = helper.getDriver();
        for(int i=0; i<NUMERO.length();i++){
            String cifra = NUMERO.substring(i,i+1);
            driver.findElement(By.xpath("//"+KEYPAD_CLASS+"[@text='"+cifra+"']")).click();
        }
        return ExecutionResult.PASSED;
    }
}
