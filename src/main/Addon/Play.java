package main.Addon;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Play {

public String playFingerBot () {
    String client_id = "5fuhcc7cqgrc3fx4vc9u";
    String secret = "33aee61b9b66412caaae375fd161e26a";

    //  String timestamp = "1588925778000";
    String timestamp = String.valueOf(new Date().getTime());
    String nonce = "";

    String ariaid = "";
    String callid = "";


    String Urle = "/v1.0/token?grant_type=1";
    String stringToSign = "GET\n" +
            "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855\n" +
            //  "area_id:"+ariaid+"\n" +
            //  "call_id:"+callid+"\n" +
            "\n" +
            "" + Urle + "";


    String var1 = client_id + timestamp + nonce;

    String message = var1 + stringToSign;
    System.out.println(message);


    //creazione StringToSign
    String hash = null;
    try {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        byte[] bytes = sha256_HMAC.doFinal(message.getBytes());

        // hash = encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
        hash = new HexBinaryAdapter().marshal(bytes).toUpperCase();
        System.out.println(hash);
    } catch (
            Exception e) {
        System.out.println("Error");
    }

    // curl token
    String curl = "curl --request GET \"https://openapi.tuyaeu.com/v1.0/token?grant_type=1\" --header \"client_id: " + client_id + "\" --header \"sign: " + hash + "\" --header \"t: " + timestamp + "\" --header \"sign_method: HMAC-SHA256\" --data-raw \"\"";

    String command = curl;
    Process process;
    String result;
    try {
        process = Runtime.getRuntime().exec(command);
        result = new BufferedReader(
                new InputStreamReader(process.getInputStream()))
                .lines()
                .collect(Collectors.joining("\n"));
        System.out.println(result);
    } catch (
            IOException e) {
        throw new RuntimeException(e);
    }

    Pattern p = Pattern.compile("\"(access_token|token_type)\":\"((\\\\\"|[^\"])*)\"");
    Matcher m = p.matcher(result);
    ArrayList al = new ArrayList();
    while (m.find()) {
        al.add(m.group());
    }

    String token = (String) al.get(0);
    String[] finale = token.split("\"access_token\":");
    String access_token = finale[1].replaceAll("^\"|\"$", "");
    System.out.println("Access token: " + access_token);
    //__________________________________________________________________________________

    //chiamata al command service
    String timestamp2 = String.valueOf(new Date().getTime());
    // String Urle2 = "/v1.0/devices/bf286873tjqlrvj7/commands";
    String stringToSign2 = "POST\n" +
            "832cb3fbd6c7228f026e0260e2247ed1ec7425b78756f5d36485f92dcd4b7275\n" +
            //  "area_id:"+ariaid+"\n" +
            //  "call_id:"+callid+"\n" +
            "\n" +
            "/v1.0/devices/bf286873tjqlrvj7/commands";
    String var2 = client_id + access_token + timestamp2 + nonce;
    String message2 = var2 + stringToSign2;

    //    System.out.println(stringToSign2);
    //    System.out.println(message2);

    String hash2 = null;
    try {
        Mac sha256_HMAC2 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC2.init(secret_key);
        byte[] bytes2 = sha256_HMAC2.doFinal(message2.getBytes());
        hash2 = new HexBinaryAdapter().marshal(bytes2).toUpperCase();
        System.out.println(hash2);
    } catch (
            Exception e) {
        System.out.println("Error");
    }

    //Chiamata al servizio send commands
    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
    //configurazione body
    JsonObject js = new JsonObject();
    JsonObject js1 = new JsonObject();
    JsonArray ja = new JsonArray();
    js1.addProperty("code", "switch");
    js1.addProperty("value", true);
    ja.add(js1);
    js.add("commands", ja);
    System.out.println(js.toString());
    RequestBody body = RequestBody.create(mediaType, js.toString());
    Request request = new Request.Builder()
            .url("https://openapi.tuyaeu.com/v1.0/devices/bf286873tjqlrvj7/commands")
            .post(body)
            .addHeader("client_id", client_id)
            .addHeader("access_token", access_token)
            .addHeader("sign", hash2)
            .addHeader("t", timestamp2)
            .addHeader("sign_method", "HMAC-SHA256")
            .addHeader("Content-Type", "application/json")
            .build();
    String resStr;
    try {
        Response response = client.newCall(request).execute();
        resStr = response.body().string();
        System.out.println(resStr);
    } catch (
            IOException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
    return resStr;
}
}

