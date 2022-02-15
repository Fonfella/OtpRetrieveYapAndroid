package main.Addon;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSElement;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.IOSAction;
import io.testproject.java.sdk.v2.addons.IOSElementAction;
import io.testproject.java.sdk.v2.addons.WebAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
import io.testproject.java.sdk.v2.drivers.IOSDriver;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.NoSuchProviderException;
import java.util.ArrayList;

import java.util.Properties;


import javax.mail.*;
import javax.mail.Flags.Flag;

import javax.mail.internet.ContentType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.ParseException;
import javax.mail.search.FlagTerm;

@Action(name="Android Yap verifica mail lista movimenti")
public class YapCheckMailLIstaMovimenti implements AndroidAction {

    @Parameter(defaultValue = "utenteverificalista@gmail.com")
    public String user;

    @Parameter(defaultValue = "pippopippo01")
    public String password;


    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {

        String a = "0";
        String b = null;

        //    AndroidDriver driver = helper.getDriver();
        // Get report object
        ActionReporter report = helper.getReporter();

        AndroidDriver driver = helper.getDriver();

        String host = "imap.gmail.com";
        String mailStoreType = "imaps";

        //da decommentare per utilizzo in debug
    //    String user = "utenteverificalista@gmail.com";
    //    String password = "pippopippo01";

        String reportDetails1 = "";
        String reportDetails2 = "";

        try {

            // create properties
            Properties properties = new Properties();

            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.starttls.enable", "true");
            properties.put("mail.imap.ssl.trust", host);

            Session emailSession = Session.getDefaultInstance(properties);

            // create the imap store object and connect to the imap server
            Store store = emailSession.getStore(mailStoreType);

            store.connect(host, user, password);

            // create the inbox object and open it
            Folder inbox = store.getFolder("Inbox");

            inbox.open(Folder.READ_WRITE);

            // retrieve the messages from the folder in an array and print it
            javax.mail.Message[] messages = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));
            //     System.out.println("messages.length---" + messages.length);
            if (messages.length != 0) {
                for (int i = 0, n = messages.length; i < n; i++) {
                    Message message = messages[i];
                    message.setFlag(Flag.SEEN, true);
                    System.out.println("---------------------------------");
                    System.out.println("Email Number " + (i + 1));
                    System.out.println("Subject: " + message.getSubject());
                    System.out.println("From: " + message.getFrom()[0]);
                    System.out.println("Text: " + message.getContent().toString());

                    if (message.getSubject().equals("YAP Elenco movimenti")) {
                        System.out.println("Messaggio Ricevuto: " + message.getSubject());
                        reportDetails1 ="Messaggio Ricevuto, oggetto: " + message.getSubject();
                        System.out.println("mittente: " + message.getFrom()[0]);
                        reportDetails2 ="Mittente: " + message.getFrom()[0];
                        a = "0";
                    } else {
                        System.out.println("Messaggio NON ricevuto!!");
                        report.result("Messaggio Non Ricevuto");
                        a="1";
                    }

                }
            } else {
                System.out.println("Nessun messaggio da analizzare!!");
                a="1";
                report.result("Nessun Messaggio DA LEGGERE prensente");
            }

            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (a.equals("0")) {
            report.result(reportDetails1 + "\n" +
                          reportDetails2);
            return ExecutionResult.PASSED;
        } else {
            return ExecutionResult.FAILED;
        }

    }





}