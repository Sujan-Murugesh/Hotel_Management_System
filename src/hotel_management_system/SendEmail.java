package hotel_management_system;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Sujan
 */
public class SendEmail extends Thread {

    private String company_Email;
    private String c_password;
    private String guest_email;
    private String e_subject;
    private String e_message;

    @Override
    public void run() {
        String DATA = new SendEmail().sendMail(getCompany_Email(), getC_password(), getGuest_email(), getE_subject(), getE_message());
        System.out.println(DATA);

        if (DATA.equals("true")) {
            JOptionPane.showMessageDialog(null, "Email Send Successfull !");
        } else {
            JOptionPane.showMessageDialog(null, "Email send Faild !");
        }
    }

    //===================================================
    public String sendMail(String Email, String Password, String ToEmail, String Subject, String bill) {

        String Msg;

        final String username = Email;
        final String password = Password;

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Email));//ur email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(ToEmail));//u will send to
            message.setSubject(Subject);
            message.setText(bill);
            Transport.send(message);
            Msg = "true";
            return Msg;

        } catch (MessagingException e) {
            return e.toString();
        }
    }

    /**
     * @param company_Email the company_Email to set
     */
    public void setCompany_Email(String company_Email) {
        this.company_Email = company_Email;
    }

    /**
     * @param c_password the c_password to set
     */
    public void setC_password(String c_password) {
        this.c_password = c_password;
    }

    /**
     * @param guest_email the guest_email to set
     */
    public void setGuest_email(String guest_email) {
        this.guest_email = guest_email;
    }

    /**
     * @param e_subject the e_subject to set
     */
    public void setE_subject(String e_subject) {
        this.e_subject = e_subject;
    }

    /**
     * @param e_message the e_message to set
     */
    public void setE_message(String e_message) {
        this.e_message = e_message;
    }

    /**
     * @return the company_Email
     */
    public String getCompany_Email() {
        return company_Email;
    }

    /**
     * @return the c_password
     */
    public String getC_password() {
        return c_password;
    }

    /**
     * @return the guest_email
     */
    public String getGuest_email() {
        return guest_email;
    }

    /**
     * @return the e_subject
     */
    public String getE_subject() {
        return e_subject;
    }

    /**
     * @return the e_message
     */
    public String getE_message() {
        return e_message;
    }

}
