package main.Addon;

import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import okhttp3.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Methods {


    public String getCorretFormat(String string) {
        String lower =  string.toLowerCase();
        String [] array_valore1;
        array_valore1 = lower.split("^.{0,3}");
        String string1 = array_valore1[1].replaceAll("'", "20").trim();
        String [] arrayPerConversione = string1.split(" ");
        // capitalize first letter
        String Capitalize = arrayPerConversione[1].substring(0, 1).toUpperCase()+ arrayPerConversione[1].substring(1);

        String giorno = arrayPerConversione[0];

//        if (arrayPerConversione[0].equals("0")) {
//            String [] val = arrayPerConversione[0].split("0");
//             giorno = val[1];
//        }

        if (arrayPerConversione[0].charAt(0) == '0' ) {
            String [] val = arrayPerConversione[0].split("0");
            giorno = val[1];
        }


        String finalFirst = giorno+" "+Capitalize+" "+arrayPerConversione[2];
        return finalFirst;
    }

    public boolean waitElement (AndroidAddonHelper helper, By we, int time){
        AndroidDriver driver = helper.getDriver();
        WebDriverWait wait =new WebDriverWait(driver, time);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(we)).isDisplayed();

    }

    public String getMeseInLettere (String meseNumero) {
        String meseInLettere = null;
        switch (meseNumero){
            case "1":
                meseInLettere = "Gen";
                break;
            case "2":
                meseInLettere = "Feb";
                break;
            case "3":
                meseInLettere = "Mar";
                break;
            case "4":
                meseInLettere = "Apr";
                break;
            case "5":
                meseInLettere = "Mag";
                break;
            case "6":
                meseInLettere = "Giu";
                break;
            case "7":
                meseInLettere = "Lug";
                break;
            case "8":
                meseInLettere = "Ago";
                break;
            case "9":
                meseInLettere = "Set";
                break;
            case "10":
                meseInLettere = "Ott";
                break;
            case "11":
                meseInLettere = "Nov";
                break;
            case "12":
                meseInLettere = "Dic";
                break;
        }
      return meseInLettere;
    }

    public String createXpathGiornoDaSelezionare(String string) {
        String xpath = "//*[@text='sost']";
        String xpathFinale = xpath.replaceAll("sost", string);
        return xpathFinale;
    }


    public String callFinger (String deviceName, String platformVersion, String udid, String address) throws IOException {
        if (address == null) {
            address = "http://localhost:8080/fingerBot";
        } else {
            address = "http://"+address+":8080/fingerBot";
        }

        String bodyFinger = "{\n" +
                "    \"deviceName\": \""+deviceName+"\",\n" +
                "    \"platformVersion\": \""+platformVersion+"\",\n" +
                "    \"udid\": \""+udid+"\"\n" +
                "}";

        OkHttpClient newClient = new OkHttpClient().newBuilder()
                .connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, bodyFinger);
        Request request = new Request.Builder()
                .url(address)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        String resStr;
        try {
            Response responseBody = newClient.newCall(request).execute();
            resStr = responseBody.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resStr;
    }
}




