package main.Addon;


import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;

@Action(name="Android Recupero OTP NexiPay(objInsert)")
public class AndroidRecuperoOtpNP implements AndroidAction {
    //decommentare prima di push addon
    @Parameter(defaultValue = "")
    public String myResponse;

    @Parameter (defaultValue = "")
    public String insertValueUno;

    @Parameter (defaultValue = "")
    public String insertValueDue;

    @Parameter (defaultValue = "")
    public String insertValueTre;

    @Parameter (defaultValue = "")
    public String insertValueQuattro;

    @Parameter (defaultValue = "")
    public String insertValueCinque;

    @Parameter (defaultValue = "")
    public String insertValueSei;

    //  public String myResponse = "user: carta_da_attivare2@yopmail.com OTP 443283";

//    String insertValueUno = "//android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout//android.widget.EditText";
//    String insertValueDue = "//android.widget.RelativeLayout[2]//android.widget.EditText";
//    String insertValueTre = "//android.widget.RelativeLayout[3]//android.widget.EditText";
//
//    String insertValueQuattro = "//android.widget.RelativeLayout[4]//android.widget.EditText";
//    String insertValueCinque = "//android.widget.RelativeLayout[5]//android.widget.EditText";
//    String insertValueSei = "//android.widget.RelativeLayout[6]//android.widget.EditText";

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

        String a = "0";
        String b = null;

        AndroidDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();

        if (myResponse.contains("Nessun OTP trovato")) {
            report.result("Nessun OTP trovato");
            b = "0";
        } else {
            String[] result = myResponse.split("OTP");
            String[] result1 = result[1].split("</html>");
            String var = result1[0].trim();
            //          System.out.println("[DETAILS] I found OTP: "+var+", for user:") ;
            //+telephonNumber);
            String[] array=var.split("(?<=\\G.{1})");
            report.result("Codice OTP trovato: " + var);

            driver.findElementByXPath(insertValueUno).sendKeys(array[0]);
            driver.findElementByXPath(insertValueDue).sendKeys(array[1]);
            driver.findElementByXPath(insertValueTre).sendKeys(array[2]);
            driver.findElementByXPath(insertValueQuattro).sendKeys(array[3]);
            driver.findElementByXPath(insertValueCinque).sendKeys(array[4]);
            driver.findElementByXPath(insertValueSei).sendKeys(array[5]);

            b="1";
        }

        if (a == b) {
            return ExecutionResult.FAILED;
        }
        return ExecutionResult.PASSED;
    }


}
