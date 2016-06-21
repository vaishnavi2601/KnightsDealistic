package ucf.knightsdealistic;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Swathi on 4/23/2015.
 */
public class Mail {

    String subject;
    String reciever;
    String textMessage;
    Session session;

    protected void sendMail(String eA,String s,String tM) {
        reciever=eA;
        textMessage=tM;
        subject=s;
        final String username = "knightsdealistic@gmail.com";
        final String password = "teamwork99";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


        //   pdialog = ProgressDialog.show(context, "", "Sending mail...", true);
        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("knightsdealistic@gmail.com"));
                message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(reciever));
                message.setSubject(subject);
                message.setContent(textMessage, "text/html; charset=utf-8");
               /* Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", "seproject@gmail.com", "teamwork99");
                transport.sendMessage(message, message.getAllRecipients());*/
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //    pdialog.dismiss();


        }
    }
}
