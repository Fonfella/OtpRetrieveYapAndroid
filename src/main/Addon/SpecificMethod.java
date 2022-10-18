package main.Addon;

import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import okhttp3.*;
import org.junit.Assert;
import org.openqa.selenium.By;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.rmi.server.RMISocketFactory;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class SpecificMethod {
    static Methods method = new Methods();

    TrustManager[] trustAllCerts = new TrustManager[] {};
    SSLContext sslContext = null;

    public void manageOtpForSomeAction(AndroidDriver driver,
                                              AndroidAddonHelper helper,
                                              String INSERT_1,
                                              String INSERT_2,
                                              String INSERT_3,
                                              String INSERT_4,
                                              String INSERT_5,
                                              String INSERT_6,
                                              String [] array_otp) {

        driver.findElementByXPath(INSERT_1).sendKeys(array_otp[0]);
        Assert.assertTrue(method.waitElement(helper, By.xpath(INSERT_2),10));
        driver.findElementByXPath(INSERT_2).sendKeys(array_otp[1]);
        Assert.assertTrue(method.waitElement(helper,By.xpath(INSERT_3),10));
        driver.findElementByXPath(INSERT_3).sendKeys(array_otp[2]);
        Assert.assertTrue(method.waitElement(helper,By.xpath(INSERT_4),10));
        driver.findElementByXPath(INSERT_4).sendKeys(array_otp[3]);
        Assert.assertTrue(method.waitElement(helper,By.xpath(INSERT_5),10));
        driver.findElementByXPath(INSERT_5).sendKeys(array_otp[4]);
        Assert.assertTrue(method.waitElement(helper,By.xpath(INSERT_6),10));
        driver.findElementByXPath(INSERT_6).sendKeys(array_otp[5]);

    }

    public void manageScreenSceglieneUno (AndroidDriver driver,
                                          String SCEGLIENE_UNO,
                                          String SELEZIONA_NUMERO,
                                          String BOTTONE_CONTINUA
                                          ) {
        try {
            Thread.sleep(3000);
            if (driver.findElement(By.xpath(SCEGLIENE_UNO)).isDisplayed()== true) {
                driver.findElement(By.xpath(SCEGLIENE_UNO)).click();
                driver.findElement(By.xpath(SELEZIONA_NUMERO)).click();
                driver.findElement(By.xpath(BOTTONE_CONTINUA)).click();
            }
        } catch (Exception e) {
            System.out.println("oggetto scegliene uno non trovato, interazione skippata");
        }
    }

    public String[] getFinalOtp (String URI) {

        String OTP = "";
        try {
            OTP = method.restOTP(URI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("L'OTP OPERAZIONE NUMERO è: " + OTP);
        String[] array_otp = OTP.split("(?<=\\G.{1})");
        return array_otp;
    }




    public String getToken () {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };


        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }


        OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();

        newBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
        newBuilder.hostnameVerifier((hostname, session) -> true);

        OkHttpClient newClient = newBuilder.build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&" +
                "client_id=21538efa-f030-487c-8172-3c41ef95021f" +
                "&client_secret=4d4ff39c-6e66-450a-a697-9fc5452a4765");
        Request request = new Request.Builder()
                .url("https://stgsecgw.private.nexicloud.it:8443/oauth/v2/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        String result;
        try {
            Response responseBody = newClient.newCall(request).execute();
            String resStr = responseBody.body().string();

            Pattern p = Pattern.compile("\"(access_token|token_type)\":\"((\\\\\"|[^\"])*)\"");
            Matcher m = p.matcher(resStr);
            ArrayList al = new ArrayList();
            while (m.find()) {
                al.add(m.group());
            }

            String token = (String) al.get(0);
            String[] finale = token.split("\"access_token\":");


            result = finale[1].replaceAll("^\"|\"$", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public String setPassword(String token, String varPassword) {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };


        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }



        OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();
        newBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
        newBuilder.hostnameVerifier((hostname, session) -> true);


        OkHttpClient newClient = newBuilder.build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"password\": \"" + varPassword + "\",\n    \"send_mail\": false\n}");

        Request request = new Request.Builder()
                .url("https://stglbiam-ms.private.nexicloud.it:443/ext-idm/ms15/account/16426966/set_password")
                .method("PUT", body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + token)
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
