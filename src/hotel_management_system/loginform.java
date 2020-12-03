package hotel_management_system;

import static hotel_management_system.Main_Window.lbl_userType;
import static hotel_management_system.Main_Window.userNamelbl;
import static hotel_management_system.Main_Window.user_password_username;
import static hotel_management_system.Main_Window.user_profile_addres1;
import static hotel_management_system.Main_Window.user_profile_addres2;
import static hotel_management_system.Main_Window.user_profile_fullname;
import static hotel_management_system.Main_Window.user_profile_image;
import static hotel_management_system.Main_Window.user_profile_nic;
import static hotel_management_system.Main_Window.user_profile_type;
import static hotel_management_system.Main_Window.user_profile_username;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Sujan
 */
public class loginform extends javax.swing.JFrame {

    Connection conn=null;
    String encrip_pwd=null;
    PreparedStatement st;
    ResultSet rs;
    private static String name;
    static void startWampAppk(){
        
        try {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\wamp64\\wampmanager.exe");
                       
            } catch (IOException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
            }
    }
    public loginform() {
        URL iconUrl = getClass().getResource("sujan.png");
        ImageIcon imageicon=new ImageIcon(iconUrl);
        this.setIconImage(imageicon.getImage());
        setResizable(false);
        initComponents();
        //startWampAppk();
        conn=Db_connection.connect();
    }
    User setprofile = new User();
    
    public void makeProfileUneditable(){
        setprofile.setUserProfileFixed();
    }
    //==================================================================================
    void logginIntoSystem(){
        
        if(text_username.getText().isEmpty() || text_username.getText().equals("username")){
            logStatusLbl.setText("Please Enter The Username");
            text_username.requestFocus();
        }
        else if(user_pwd.getText().isEmpty() || user_pwd.getText().equals("typepassword")){
            logStatusLbl.setText("Please Enter The Password");
            user_pwd.requestFocus();
        }
        else if(conn==null){
            logStatusLbl.setText("Please start up the server application");
            
            try {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\wamp64\\wampmanager.exe");
                       
            } catch (IOException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
            }
            logStatusLbl.setText("");
        }
        else{
             try {
                String username=text_username.getText();
                String userpwd=user_pwd.getText();
                
                //CHANGE THE USER PASSWORD INTO ENCRYPT TYPE
                byte[] byte_pwd=userpwd.getBytes();
                MessageDigest md5=MessageDigest.getInstance("md5");
                md5.update(byte_pwd);
                byte[] BytePass=md5.digest();
                encrip_pwd=DatatypeConverter.printHexBinary(BytePass);
                
                //GET USER NAME AND PASSWORD FROM THE DATABASE 
                String query="SELECT * FROM `users` WHERE `username`=? AND `password`=?";
                st=conn.prepareStatement(query);
                st.setString(1,username);
                st.setString(2,encrip_pwd);
                
                rs=st.executeQuery();
                
                if(rs.next()){
                    Main_Window m=new Main_Window();
                  //  m.setSize(400,200);
                    m.setVisible(true);
                    m.pack();
                    m.setLocationRelativeTo(null);
                    this.hide();
                    setUserProfile();  //to seting user profile
                    makeProfileUneditable(); //to make feilds un editable...
                    String uType=rs.getString("usertype");
                    if(uType.equals("Admin")){
                        
                        setLogUserName(rs.getString("fullname"));
                        lbl_userType.setText("Admin :");
                        userNamelbl.setText(loginform.getLogUserName());
                        //setLogUserName("Admin : "+rs.getString("fullname"));
                        //userNamelbl.setText("Admin : "+rs.getString("fullname"));
                        
                    }
                    else{
                        lbl_userType.setText("Staff :");
                        setLogUserName(rs.getString("fullname"));
                        userNamelbl.setText(loginform.getLogUserName());
                        //setLogUserName("Staff : "+rs.getString("fullname"));
                        //userNamelbl.setText("Staff : "+rs.getString("fullname"));
                    }
                    
                    
                    
                   // pwdUserId.setText(username);
                   // pwdOld.setText(encrip_pwd);
                }
                else{
                    logStatusLbl.setText("Invalid Username/Password");
                    text_username.requestFocus();
                    text_username.setText("");
                    user_pwd.setText("");
                }
            } catch (NoSuchAlgorithmException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
    
    public  void setLogUserName(String name){
        loginform.name = name;
    }
    
    public static String getLogUserName(){
        return name;
    }
    //==================================================================================
    public void setUserProfile(){
        try {
            user_profile_username.setText(rs.getString("username"));
            user_password_username.setText(rs.getString("username"));
            user_profile_fullname.setText(rs.getString("fullname"));
            user_profile_type.setText(rs.getString("usertype")+" Account");
            user_profile_nic.setText(rs.getString("nic"));
            
            //to get address line by line
            String[] a1= rs.getString("address").split("\n");
            user_profile_addres1.setText(a1[0]);
            user_profile_addres2.setText(a1[1]);
            
            //to get and setting user profile image
            byte[] img=rs.getBytes("userImage");
                ImageIcon image=new ImageIcon(img);
                Image im=image.getImage();
                Image myImg=im.getScaledInstance(user_profile_image.getWidth(),user_profile_image.getHeight(),Image.SCALE_SMOOTH);
                ImageIcon newImage=new ImageIcon(myImg);
                user_profile_image.setIcon(newImage);
        } catch (SQLException ex) {
            Logger.getLogger(loginform.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //=====================================================================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        log = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        title2 = new javax.swing.JLabel();
        logStatusLbl = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        password = new javax.swing.JLabel();
        usename = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        user_pwd = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        text_username = new javax.swing.JTextField();
        show_pwd = new javax.swing.JCheckBox();
        title = new javax.swing.JLabel();
        log_button1 = new javax.swing.JLabel();
        log_button = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        log.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        title1.setForeground(new java.awt.Color(255, 255, 255));
        title1.setText("Hotel Management System");
        log.add(title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, 40));
        log.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 660, 10));

        title2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        title2.setForeground(new java.awt.Color(255, 255, 255));
        title2.setText("Gamage Nest and Rest");
        log.add(title2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, 30));

        logStatusLbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        logStatusLbl.setForeground(new java.awt.Color(255, 0, 0));
        log.add(logStatusLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, 460, 40));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0,150));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        password.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        password.setForeground(new java.awt.Color(255, 255, 255));
        password.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/icons8_password_26px.png"))); // NOI18N
        password.setText(" Password");
        password.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordMouseClicked(evt);
            }
        });

        usename.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        usename.setForeground(new java.awt.Color(255, 255, 255));
        usename.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/icons8_user_male_circle_26px_1.png"))); // NOI18N
        usename.setText(" Username");
        usename.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        user_pwd.setBackground(new java.awt.Color(0, 0, 0));
        user_pwd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        user_pwd.setForeground(new java.awt.Color(255, 255, 255));
        user_pwd.setText("typepassword");
        user_pwd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 1)));
        user_pwd.setMaximumSize(new java.awt.Dimension(165, 22));
        user_pwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_pwdMouseClicked(evt);
            }
        });
        user_pwd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                user_pwdKeyPressed(evt);
            }
        });

        text_username.setBackground(new java.awt.Color(0, 0, 0));
        text_username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        text_username.setForeground(new java.awt.Color(255, 255, 255));
        text_username.setText("username");
        text_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 1)));
        text_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                text_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                text_usernameFocusLost(evt);
            }
        });
        text_username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                text_usernameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                text_usernameMouseEntered(evt);
            }
        });
        text_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_usernameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                text_usernameKeyTyped(evt);
            }
        });

        show_pwd.setBackground(new java.awt.Color(255, 255, 255));
        show_pwd.setForeground(new java.awt.Color(255, 255, 255));
        show_pwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_pwdActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Show Password");

        log_button1.setFont(new java.awt.Font("Segoe Script", 1, 18)); // NOI18N
        log_button1.setForeground(new java.awt.Color(255, 255, 255));
        log_button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/sujanlog.png"))); // NOI18N
        log_button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                log_button1MouseClicked(evt);
            }
        });

        log_button.setFont(new java.awt.Font("Segoe Script", 1, 18)); // NOI18N
        log_button.setForeground(new java.awt.Color(255, 255, 255));
        log_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/Artboard@3x.png"))); // NOI18N
        log_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                log_buttonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(user_pwd, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(show_pwd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(title))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(log_button1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(log_button, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(usename)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(text_username, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(150, 150, 150)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usename, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(text_username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(password))
                    .addComponent(user_pwd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(show_pwd)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(log_button1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(log_button, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        log.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 630, 320));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/login.gif"))); // NOI18N
        log.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(log, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(log, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void log_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_log_buttonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_log_buttonMouseClicked

    private void text_usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text_usernameFocusGained
        //text_username.setText("");
    }//GEN-LAST:event_text_usernameFocusGained

    private void text_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text_usernameFocusLost
        //text_username.setText("username");
    }//GEN-LAST:event_text_usernameFocusLost

    private void text_usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_usernameKeyPressed
        //when press enter key then focus on next jtext feild
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           user_pwd.requestFocus(); 
           user_pwd.setText("");
        }
        //
    }//GEN-LAST:event_text_usernameKeyPressed

    private void text_usernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_usernameKeyTyped
        // TODO add your handling code here:
       // user_pwd.requestFocus();
    }//GEN-LAST:event_text_usernameKeyTyped

    private void text_usernameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_text_usernameMouseEntered
        // TODO add your handling code here:
        //user_pwd.requestFocus();
    }//GEN-LAST:event_text_usernameMouseEntered

    private void show_pwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_pwdActionPerformed
        // SHOWING PASSWORD FEILD TEXT
        if(show_pwd.isSelected()){
            user_pwd.setEchoChar((char)0);
        }
        else{
            user_pwd.setEchoChar('\u25cf');
            
        }
    }//GEN-LAST:event_show_pwdActionPerformed

    private void text_usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_text_usernameMouseClicked
        text_username.setText("");
        logStatusLbl.setText("");
    }//GEN-LAST:event_text_usernameMouseClicked

    private void user_pwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_pwdMouseClicked
         user_pwd.setText("");
         logStatusLbl.setText("");
    }//GEN-LAST:event_user_pwdMouseClicked

    private void log_button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_log_button1MouseClicked
        logginIntoSystem();
    }//GEN-LAST:event_log_button1MouseClicked

    private void user_pwdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_pwdKeyPressed
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           logginIntoSystem();
           //logStatusLbl.setText("");
        }
    }//GEN-LAST:event_user_pwdKeyPressed

    private void passwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseClicked
//        try {
//            String my=user_pwd.getText();
//            byte[] byte_pwd=my.getBytes();
//                MessageDigest md5=MessageDigest.getInstance("md5");
//                md5.update(byte_pwd);
//                byte[] BytePass=md5.digest();
//                encrip_pwd=DatatypeConverter.printHexBinary(BytePass);
//                user_pwd.setText(encrip_pwd);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"Sujan");
//        }
    }//GEN-LAST:event_passwordMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(loginform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginform().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLabel5;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel log;
    public static javax.swing.JLabel logStatusLbl;
    public static javax.swing.JLabel log_button;
    public static javax.swing.JLabel log_button1;
    private javax.swing.JLabel password;
    public static javax.swing.JCheckBox show_pwd;
    public static javax.swing.JTextField text_username;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel usename;
    public static javax.swing.JPasswordField user_pwd;
    // End of variables declaration//GEN-END:variables
}
