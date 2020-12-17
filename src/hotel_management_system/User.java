package hotel_management_system;

import static hotel_management_system.Main_Window.User_image_lbl;
import static hotel_management_system.Main_Window.new_user_fullname_in;
import static hotel_management_system.Main_Window.new_username_in;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Sujan
 */
public class User extends Main_Window {

    public static String ImgPath = null;
    //Resize image

    public static ImageIcon ResizeImage(String ImgPath, byte[] pic) {
        ImageIcon myImage = null;
        if (ImgPath != null) {
            myImage = new ImageIcon(ImgPath);
        } else {
            myImage = new ImageIcon(pic);
        }

        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(User_image_lbl.getWidth(), User_image_lbl.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }

    //==========================********************************************====================
    public static void chooiceUserImg() {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
        int result = file.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            User_image_lbl.setIcon(ResizeImage(path, null));
            ImgPath = path;
        } else {
            System.out.println("No File selected..");
        }
        //ChoiceNewUserImg(); // to chooice the user profile picture
        new_username_in.requestFocus();
        new_username_in.setBackground(new Color(197, 232, 240));
    }

    
    //==========================********************************************====================
    //==========================********************************************====================
    //============================TO INSERTING NEW USER DATA INTO THE DATABASE==========
    public void input_new_user() {
        //checking if the user name aleady created o not....
//        if (chUname()) {
//            JOptionPane.showMessageDialog(null, "Sorry! username already created..", "Not Allowed", HEIGHT);
//        } else {
            //=========================================
            String encryptePwd = null;
            String Utype = "Staff";
            String Uaddress = new_user_addres1_in.getText().concat("\n" + new_user_addres2_in.getText());
            //=========================================
            //VERIFYING INPUTS...
            //=========================================
            if (ImgPath == null) {
                new_user_warning_text.setText("Please Upload the Profile picture");
            } else if (new_username_in.getText().equals("")) {
                new_user_warning_text.setText("Please fillout the user name");
                new_username_in.setBackground(new Color(197, 232, 240));
                new_username_in.requestFocus();
            } else if (new_user_fullname_in.getText().equals("")) {
                new_user_warning_text.setText("Please fillout the fullname");
                new_user_fullname_in.setBackground(new Color(197, 232, 240));
                new_user_fullname_in.requestFocus();
            } else if (jPassword_newUser.getText().equals("")) {
                new_user_warning_text.setText("Please fillout the password");
                jPassword_newUser.setBackground(new Color(197, 232, 240));
                jPassword_newUser.requestFocus();
            } else if (new_user_nic_in.getText().equals("")) {
                new_user_warning_text.setText("Please fillout the nic number");
                new_user_nic_in.setBackground(new Color(197, 232, 240));
                new_user_nic_in.requestFocus();
            } else if (new_user_addres1_in.getText().equals("")) {
                new_user_warning_text.setText("Please fillout the Address");
                new_user_addres1_in.setBackground(new Color(197, 232, 240));
                new_user_addres1_in.requestFocus();
            } else if (jComboAc_type.getSelectedItem().toString().equals("Admin Account")) {
                Utype = "Admin";
            } else if (new_user_addres2_in.getText().isEmpty()) {
                Uaddress = new_user_addres1_in.getText();
            } else {
                try {
//====================================================================================================
                    //ENCRYPT THE USER PASSWORD...
                    String userPwd = jPassword_newUser.getText();
                    byte[] byte_pwd = userPwd.getBytes();
                    MessageDigest md5 = MessageDigest.getInstance("md5");
                    md5.update(byte_pwd);
                    byte[] BytePass = md5.digest();
                    encryptePwd = DatatypeConverter.printHexBinary(BytePass);
//====================================================================================================
                    //INSERTING THE NEW USER DATA...
//====================================================================================================
                    ps = conn.prepareStatement("INSERT INTO `users`(`username`,`fullname`,`usertype`,`password`,`nic`,`address`,`userImage`)values(?,?,?,?,?,?,?)");
                    ps.setString(1, new_username_in.getText());
                    ps.setString(2, new_user_fullname_in.getText());
                    ps.setString(3, Utype);
                    ps.setString(4, encryptePwd);
                    ps.setString(5, new_user_nic_in.getText());
                    ps.setString(6, Uaddress);
                    InputStream img = new FileInputStream(new File(ImgPath));
                    ps.setBlob(7, img);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "New user Created Successfully...", "Success!", HEIGHT);
                    //new_user_warning_text.setText("User Account created...");
                    clearNewUserForm();
                } catch (SQLException | FileNotFoundException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                //}
            }
        }
    }

    //===========================================================================================
    public void clearNewUserForm() {
        new_username_in.setText("");
        new_user_fullname_in.setText("");
        jPassword_newUser.setText("");
        new_user_nic_in.setText("");
        new_user_addres1_in.setText("");
        new_user_addres2_in.setText("");
        jComboAc_type.setSelectedIndex(0);
        User_image_lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/addimg.png")));
        User_image_lbl.revalidate();
        ImgPath = null;
        new_user_warning_text.setText("");
    }

    //==========================********************************************====================
    public boolean chUname() {
        String checkUserName = new_username_in.getText();
        PreparedStatement st;
        ResultSet rs1;

        try {
            String query = "SELECT * FROM `users` WHERE `username`=?";
            st = conn.prepareStatement(query);
            st.setString(1, checkUserName);
            rs = st.executeQuery();
            return !rs.next(); //JOptionPane.showMessageDialog(null,"Not Allowed", "Sorry! username already created..", HEIGHT);
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }
    
    //==========================********************************************====================
        //DELETE USER ACCOUNTS
    //==========================********************************************====================
    public void delete_User_Accounts() {
        if (new_username_in.getText().isEmpty()) {
            new_user_warning_text.setText("Please type the Username");

            new_username_in.setBackground(new Color(197, 232, 240));
            new_username_in.requestFocus();
            new_username_in.setText("");
        } else {
            String dlUsername = new_username_in.getText();
            //GET USER NAME AND PASSWORD FROM THE DATABASE 
            try {
                String query = "SELECT * FROM `users` WHERE `username`=?";
                ps = conn.prepareStatement(query);
                ps.setString(1, dlUsername);

                rs = ps.executeQuery();
                if (rs.next()) {
                    try {
                        try {
                            String DeleteQuery = "DELETE FROM `users` WHERE username='" + new_username_in.getText() + "'";
                            ps = conn.prepareStatement(DeleteQuery);
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "User account deleted...");
                            new_user_warning_text.setText("");
                            new_username_in.setBackground(new Color(197, 232, 240));
                            new_username_in.requestFocus();
                            new_username_in.setText("");
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                } else {
                    new_user_warning_text.setText("Invalid Username");

                    new_username_in.setBackground(new Color(197, 232, 240));
                    new_username_in.requestFocus();
                    new_username_in.setText("");
                    //JOptionPane.showMessageDialog(null, "Please check the Username");
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
    //==========================********************************************====================
        //SET USER PROFILE
    //==========================********************************************====================
    //==========================********************************************====================
            //TO HIDE PROFILE SPERATERS AND SET UNEDITABLE
            //jSeparator11
    public void setUserProfileFixed(){
        jSeparator10.setVisible(false);
        jSeparator11.setVisible(false);
        jSeparator12.setVisible(false);
        jSeparator14.setVisible(false);
        user_profile_fullname.setEditable(false);
        user_profile_nic.setEditable(false);   
        user_profile_addres1.setEditable(false);  
        user_profile_addres2.setEditable(false);
        jButton_user_profile_save.setVisible(false);
        
        
    }
    
    //=====================================================================================
    //==========================********************************************====================
            //TO HIDE PROFILE SPERATERS AND SET UNEDITABLE
            //jSeparator11
    public void setUserProfile_editable(){
        jSeparator10.setVisible(true);
        jSeparator11.setVisible(true);
        jSeparator12.setVisible(true);
        jSeparator14.setVisible(true);
        user_profile_fullname.setEditable(true);
        user_profile_fullname.requestFocus();
        user_profile_fullname.setBackground(new Color(197, 232, 240));
        
        user_profile_nic.setEditable(true);  
        user_profile_nic.setBackground(new Color(197, 232, 240));
        
        user_profile_addres1.setEditable(true);
        user_profile_addres1.setBackground(new Color(197, 232, 240));
        
        user_profile_addres2.setEditable(true);
        user_profile_addres2.setBackground(new Color(197, 232, 240));
        
        jButton_user_profile_save.setVisible(true);
        comEditCh.setSelected(true);
        
    }
    
    //=====================================================================================
     public static String Imgprofile = null;
    //Resize image

    public static ImageIcon ResizeImage2(String Imgprofile, byte[] pic) {
        ImageIcon myImage = null;
        if (Imgprofile != null) {
            myImage = new ImageIcon(Imgprofile);
        } else {
            myImage = new ImageIcon(pic);
        }

        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(user_profile_image.getWidth(), user_profile_image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }

    //==========================********************************************====================
    public static void chooiceUserImgUpdate(){
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
        int result = file.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            user_profile_image.setIcon(ResizeImage2(path, null));
            Imgprofile = path;
        } else {
            System.out.println("No File selected..");
        }
    }
    //==========================********************************************====================
        //TO UPDATE USER DATA
    //==========================********************************************====================
    public void userDataUpdate(){
         String Ad=user_profile_addres1.getText().concat("\n" + user_profile_addres2.getText());
       if(user_profile_fullname.getText().isEmpty()){
           user_profile_fullname.setBackground(new Color(252,169,182));
           user_profile_fullname.requestFocus();
       }
       else if(user_profile_nic.getText().isEmpty()){
           user_profile_nic.setBackground(new Color(252,169,182));
           user_profile_nic.requestFocus();
       }
       else if(user_profile_addres1.getText().isEmpty()){
           user_profile_addres1.setBackground(new Color(252,169,182));
           user_profile_addres1.requestFocus();
       }
       else if(user_profile_addres2.getText().isEmpty()){
           Ad=user_profile_addres1.getText();
       }
       else{
           if(Imgprofile==null){
                try {
                   String UpdateQuery = "UPDATE users SET `fullname` =? ,`nic`=?,`address`=?  WHERE username ='"+user_profile_username.getText()+"'";
                   ps =conn.prepareStatement(UpdateQuery);
                   ps.setString(1,user_profile_fullname.getText());
                   ps.setString(2,user_profile_nic.getText());
                   ps.setString(3,Ad);
                   ps.executeUpdate();
                   setUserProfileFixed();
                   
                   user_profile_fullname.setBackground(new Color(255,255,255));
                   user_profile_nic.setBackground(new Color(255,255,255));
                   user_profile_addres1.setBackground(new Color(255,255,255));
                   user_profile_addres2.setBackground(new Color(255,255,255));
                   JOptionPane.showMessageDialog(null, "Your new data saved");

                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
           }
           else{
               try {
                   String UpdateQuery = "UPDATE users SET `fullname` =? ,`nic`=?,`address`=?,`userImage`=?  WHERE username ='"+user_profile_username.getText()+"'";
                   ps =conn.prepareStatement(UpdateQuery);
                   ps.setString(1,user_profile_fullname.getText());
                   ps.setString(2,user_profile_nic.getText());
                   ps.setString(3,Ad);
                   InputStream img = new FileInputStream(new File(Imgprofile));
                   ps.setBlob(4, img);
                   ps.executeUpdate();
                   setUserProfileFixed();
                   
                   user_profile_fullname.setBackground(new Color(255,255,255));
                   user_profile_nic.setBackground(new Color(255,255,255));
                   user_profile_addres1.setBackground(new Color(255,255,255));
                   user_profile_addres2.setBackground(new Color(255,255,255));
                   JOptionPane.showMessageDialog(null, "Your new data saved");

                } catch (SQLException | FileNotFoundException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
           }
           
       }
    }
      //==========================********************************************====================
    //==========================********************************************====================
   //==========================********************************************====================
    
}

