package hotel_management_system;

import java.awt.Color;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Sujan
 */
public class Password extends Main_Window{
    public void changeUserPassword() {
        String NewPwd = jPassword_change_new.getText();
        String PwdCon = jPassword_change_confirm.getText();
        //
        if (!"".equals(NewPwd) && !"".equals(PwdCon) && (NewPwd.equals(PwdCon))) {

            try {
                //CHANGE THE USER PASSWORD INTO ENCRYPT TYPE
                byte[] byte_pwd = PwdCon.getBytes();
                MessageDigest md5 = MessageDigest.getInstance("md5");
                md5.update(byte_pwd);
                byte[] BytePass = md5.digest();
                String encrip_pwd = DatatypeConverter.printHexBinary(BytePass);

                String UpdateQuery = "UPDATE users SET password =? WHERE username ='" + user_password_username.getText() + "'";
                ps = conn.prepareStatement(UpdateQuery);
                ps.setString(1, encrip_pwd);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Password Changed successfully...");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Password.class.getName()).log(Level.SEVERE, null, ex);
            }
            jPassword_change_new.setText("");
            jPassword_change_confirm.setText("");
            jPassword_change_new.setBackground(new Color(255, 255, 255));
            jPassword_change_confirm.setBackground(new Color(255, 255, 255));
        } else {
            JOptionPane.showMessageDialog(null, "Your Password not Matching! TryAgain!" ,"check Inputs", 2);
            jPassword_change_new.setBackground(new Color(197, 232, 240));
            jPassword_change_confirm.setBackground(new Color(197, 232, 240));

        }
    }
}
