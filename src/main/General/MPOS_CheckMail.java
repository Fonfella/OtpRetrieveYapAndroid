package main.General;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.GenericAction;
import io.testproject.java.sdk.v2.addons.helpers.AddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import main.Addon.Methods;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.FlagTerm;
import java.util.Properties;

@Action(name="MPOS Check mail Ricevuta")
public class MPOS_CheckMail implements GenericAction {
    //decommentare prima di push addon
    @Parameter(defaultValue = "")
    public String user;

    @Parameter(defaultValue = "")
    public String password;


    @Override
    public ExecutionResult execute(AddonHelper helper) throws FailureException {


        String a = "0";
        String b = null;

        // Get report object
        ActionReporter report = helper.getReporter();
        String host = "imap.gmail.com";
        String mailStoreType = "imaps";

        //commentare prima del push
        String user = "nexi.mpos@gmail.com";
        String password = "uytbzodjblpazcrx";

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
            javax.mail.Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            //     System.out.println("messages.length---" + messages.length);
            if (messages.length != 0) {
                for (int i = 0, n = messages.length; i < n; i++) {
                    Message message = messages[i];
                    message.setFlag(Flags.Flag.SEEN, true);
                    System.out.println("---------------------------------");
                    report.result("Email Number " + (i + 1));
                    report.result("Subject: " + message.getSubject());
                    report.result("From: " + message.getFrom()[0]);
                    System.out.println("Text: " + message.getContent().toString());

                    String contentType = message.getContentType();
                    String messageContent="";

                    if (contentType.contains("multipart")) {
                        Multipart multiPart = (Multipart) message.getContent();
                        int numberOfParts = multiPart.getCount();
                        for (int partCount = 0; partCount < numberOfParts; partCount++) {
                            MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                            messageContent = part.getContent().toString();
                        }
                    }
                    else if (contentType.contains("text/plain")
                            || contentType.contains("text/html")) {
                        Object content = message.getContent();
                        if (content != null) {
                            messageContent = content.toString();
                        }
                    }

                    if (message.getSubject().equals("Ricevuta pagamento a SETEFI SPA")) {
                        System.out.println("Messaggio correttamente ricevuto! mittente: " + message.getFrom()[0]);
                        a = "0";
                        report.result("Messaggio correttamente ricevuto!" + "Mittente: " + message.getFrom()[0]);
                    } else {
                        System.out.println("Messaggio NON ricevuto!!");
                        report.result("Messaggio Non Ricevuto");
                        a = "1";
                    }

                }
            } else {
                System.out.println("Nessun messaggio da analizzare!!");
                a = "1";
                report.result("NESSUN messaggio visualizzato");
            }

            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (a.equals("0")) {
            return ExecutionResult.PASSED;
        } else {
            return ExecutionResult.FAILED;
        }

    }
}