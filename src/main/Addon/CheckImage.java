package main.Addon;


import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Action(name="ruota foto e Verifica Immagine")
public class CheckImage implements AndroidAction {

    @Parameter(defaultValue = "SETEFINSPA,VLE GIULIO RICHARD 7,20143,Data 03/11/21,TOTALE,0")
    public String values;

    @Parameter(defaultValue = "C:\\CartellaLavoro\\provaScontrino.jpeg")
    public String folderPath;


    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

        String viewReceipt = "true";
        String failedCount = "false";
        List finalReport = new LinkedList();



        AndroidDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();

        ITesseract image = new Tesseract();
        image.setDatapath("tessdata");
        image.setLanguage("ita");

        //paramenter section
     //
    //    String folderPath = "\\\\ASCSBCWTL283\\condivisa\\provaScontrino.jpeg";
        //  String folderPath = "C:\\CartellaLavoro\\Scontrino.jpg";
        //File f = new File ("\\\\10.10.10.123\\Addons\\readme.txt");
        // String folderPath = "\\\\10.130.144.92\\Shared\\TM_Export\\TM004341_BC1941015\\TMROBOT_VisionImages\\P61_TEST_PRG_R05\\ImageLightOn_P\\2020-09-25\\source\\provaScontrino.jpg";

        //esempio OK
      // values = "SETEFINSPA,VLE GIULIO RICHARD 7,20143,Data 03/11/21,TOTALE,0";

        // esempio KO
      //  values = "SETEFINSPA,VLE GIULIiiiO RICHARD 7,20143,Data 03/11/21,TOTALE,0";
        FilePermission permission = new FilePermission(folderPath, "read");
        permission = new FilePermission(folderPath, "write");
        File file = new File(folderPath);
        file.setExecutable(false);
        file.setReadable(true);
        file.setWritable(true);
        System.out.println("File permissions changed.");
        //parte per rotazione
        BufferedImage bm = null;
        try {
            bm = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final double rads = Math.toRadians(90);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(bm.getWidth() * cos + bm.getHeight() * sin);
        final int h = (int) Math.floor(bm.getHeight() * cos + bm.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(w, h, bm.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads,0, 0);
        at.translate(-bm.getWidth() / 2, -bm.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(bm,rotatedImage);

        try {
            ImageIO.write(rotatedImage, "JPG", new File("\\\\ASCSBCWTL283\\condivisa\\rotatedImage.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //fine aggiunta

        // String[] checkObject = {"1,50", "16,90", "Pippo", "77,10", "Carlo", "CAFFE", "IVA Prezzo", "GRAZIE E ARRIVEDERCI"};
        try {
            String str = image.doOCR(new File("\\\\ASCSBCWTL283\\condivisa\\rotatedImage.jpg"));
            if (viewReceipt.equals("true")) {
                System.out.println("Data from Image is: " +str);
            }
            ArrayList aList= new ArrayList(Arrays.asList(values.split(",")));
            for(int i=0; i<aList.size(); i++) {


                System.out.println("[DETAILS] value to check  --> " +aList.get(i));
                ArrayList<String> ar = new ArrayList<String>();
                ar.add((String) aList.get(i));
                if (str.contains((CharSequence) aList.get(i))) {
                    System.out.println ("[DETAILS] check N. " + i + ", IS PRESENT Checked Status Passed");
                    finalReport.add("Check N. " + i + " - elemento trovato ("+aList.get(i)+") -> PASSED!");
                    finalReport.add("\n");
                } else {
                    finalReport.add("Check N. " + i + " - elemento NON trovato ("+aList.get(i)+") -> FAILED");
                    finalReport.add("\n");
                    failedCount = "true";
                }
            }
        } catch (TesseractException e) {
            e.printStackTrace();
        }


        if (failedCount.equals("true")) {
            report.result(String.valueOf(finalReport));
            return ExecutionResult.FAILED;
        }
        report.result(String.valueOf(finalReport));
        return ExecutionResult.PASSED;
    }

}
