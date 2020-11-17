package hotel_management_system;

import static hotel_management_system.Main_Window.guest_address;
import static hotel_management_system.Main_Window.guest_email;
import static hotel_management_system.Main_Window.guest_fullname;
import static hotel_management_system.Main_Window.guest_id;
import static hotel_management_system.Main_Window.guest_identitity;
import static hotel_management_system.Main_Window.guest_in_image;
import static hotel_management_system.Main_Window.guest_purpose_visit;
import static hotel_management_system.Main_Window.guest_tel;
import static hotel_management_system.Main_Window.guest_warning_text;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Sujan
 */
public class Guest extends Main_Window {

//==============================================================================
    //WEB CAME IMAGE PROCESSING... GETTING GUEST PIC FROM WEB CAM
//==============================================================================  
    public static String guestImgPath = null;

    //to get saved guest image & set the guest lable...
    public void setGuestImage() {
        String guestImgName = guest_id.getText();
        guestImgPath = "src/GuestPicture/" + guestImgName + ".jpg";
        File file = new File(guestImgPath);

        File selectedFile = file.getAbsoluteFile();
        String path = selectedFile.getAbsolutePath();
        guest_in_image.setIcon(ResizeImage(path, null));
        guestImgPath = path;
    }

    //Resize image
    public static ImageIcon ResizeImage(String ImgPath, byte[] pic) {
        ImageIcon myImage = null;
        if (ImgPath != null) {
            myImage = new ImageIcon(ImgPath);
        } else {
            myImage = new ImageIcon(pic);
        }

        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(guest_in_image.getWidth(), guest_in_image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }
//==============================================================================
    //INSERTING GUEST DATA TO THE DATABASE
//==============================================================================

    public void insertGuest_Data() {
        if (guestImgPath == null) {
            guest_warning_text.setText("Please Upload the Profile picture");
        } else if (guest_fullname.getText().equals("")) {
            guest_warning_text.setText("Please fillout the guest name");
            guest_fullname.setBackground(new Color(197, 232, 240));
            guest_fullname.requestFocus();
        } else if (guest_identitity.getText().equals("")) {
            guest_warning_text.setText("Please fillout the guest identity");
            guest_identitity.setBackground(new Color(197, 232, 240));
            guest_identitity.requestFocus();
        } else if (guest_tel.getText().equals("")) {
            guest_warning_text.setText("Please fillout the guest telephone");
            guest_tel.setBackground(new Color(197, 232, 240));
            guest_tel.requestFocus();
        } else if (guest_email.getText().equals("")) {
            guest_warning_text.setText("Please fillout the guest email");
            guest_email.setBackground(new Color(197, 232, 240));
            guest_email.requestFocus();
        } else if (guest_purpose_visit.getText().equals("")) {
            guest_warning_text.setText("Please fillout the visit purpose");
            guest_purpose_visit.setBackground(new Color(197, 232, 240));
            guest_purpose_visit.requestFocus();
        } else if (guest_address.getText().equals("")) {
            guest_warning_text.setText("Please fillout the guest address");
            guest_address.setBackground(new Color(197, 232, 240));
            guest_address.requestFocus();
        } else {
            try {

                ps = conn.prepareStatement("INSERT INTO `guest`(`guest_id`,`guest_name`,`gidentity`,`gtel`,`gemail`,`gvisit`,`gaddress`,`gimage`)values(?,?,?,?,?,?,?,?)");
                ps.setString(1, guest_id.getText());
                ps.setString(2, guest_fullname.getText());
                ps.setString(3, guest_identitity.getText());
                ps.setString(4, guest_tel.getText());
                ps.setString(5, guest_email.getText());
                ps.setString(6, guest_purpose_visit.getText());
                ps.setString(7, guest_address.getText());
                InputStream img = new FileInputStream(new File(guestImgPath));
                ps.setBlob(8, img);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "New Guest Data Saved ", "Success!", HEIGHT);
                clearGuestForm();
            } catch (SQLException | FileNotFoundException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    //===============================
    public void clearGuestForm() {
        guest_fullname.setText("");
        guest_identitity.setText("");
        guest_tel.setText("");
        guest_email.setText("");
        guest_purpose_visit.setText("");
        guest_address.setText("");
        guest_in_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/addimg.png")));
        guest_in_image.revalidate();
        guestImgPath = null;
        guest_warning_text.setText("");
        
        guest_id.setBackground(new Color(255, 255, 255));
        guest_fullname.setBackground(new Color(255, 255, 255));
        guest_identitity.setBackground(new Color(255, 255, 255));
        guest_tel.setBackground(new Color(255, 255, 255));
        guest_email.setBackground(new Color(255, 255, 255));
        guest_purpose_visit.setBackground(new Color(255, 255, 255));
        guest_address.setBackground(new Color(255, 255, 255));
        guest_address.setBackground(new Color(255, 255, 255));

    }

    //=====================================
    //  RETRIVE DATA AND SET TO THE JTABLE
    public void showGuest_data() {
        try {
            String qry = "select `guest_id`,`guest_name`,`gtel` from `guest`";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            jTableGuest.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    //======================================

    public void searchGuset_byID() {
        if (guest_id.getText().equals("")) {
            guest_warning_text.setText("Do not change/empty guest ID");
            guest_id.setBackground(new Color(197, 232, 240));
        } else {
            String guestID = guest_id.getText();
            try {
                String qry = "select * from `guest` where `guest_id` = '" + guestID + "' ";
                ps = conn.prepareStatement(qry);
                rs = ps.executeQuery(qry);
                if (rs.next()) {
                    if (guestID == null ? rs.getString("guest_id") == null : guestID.equals(rs.getString("guest_id"))) {
                        String Name = rs.getString("guest_name");
                        String Nic = rs.getString("gidentity");
                        String Gtel = rs.getString("gtel");
                        String Gemail = rs.getString("gemail");
                        String GVisitPurpose = rs.getString("gvisit");
                        String Gaddress = rs.getString("gaddress");
                        byte[] img = rs.getBytes("gimage");
                        ImageIcon image = new ImageIcon(img);
                        Image im = image.getImage();
                        Image myImg = im.getScaledInstance(guest_in_image.getWidth(), guest_in_image.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon newImage = new ImageIcon(myImg);
                        guest_in_image.setIcon(newImage);
                        guest_fullname.setText(Name);
                        guest_identitity.setText(Nic);
                        guest_tel.setText(Gtel);
                        guest_email.setText(Gemail);
                        guest_purpose_visit.setText(GVisitPurpose);
                        guest_address.setText(Gaddress);
                        //close............................
                    }
                } else {
                    guest_warning_text.setText("Please check the guest ID!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    //=========================================================================
    public void guestJTable_RowClicked() {
        try {
            int index = jTableGuest.getSelectedRow();  //i changed the editing colume in jetable
            String guestID = (jTableGuest.getModel().getValueAt(index, 0).toString());

            String qry = "select * from `guest` where `guest_id` = '" + guestID + "' ";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            if (rs.next()) {
                guest_id.setText(guestID);
                String Name = rs.getString("guest_name");
                guest_fullname.setText(Name);

                String Nic = rs.getString("gidentity");
                guest_identitity.setText(Nic);

                String Gtel = rs.getString("gtel");
                guest_tel.setText(Gtel);

                String Gemail = rs.getString("gemail");
                guest_email.setText(Gemail);

                String GVisitPurpose = rs.getString("gvisit");
                guest_purpose_visit.setText(GVisitPurpose);

                String Gaddress = rs.getString("gaddress");
                guest_address.setText(Gaddress);

                byte[] img = rs.getBytes("gimage");
                ImageIcon image = new ImageIcon(img);
                Image im = image.getImage();
                Image myImg = im.getScaledInstance(guest_in_image.getWidth(), guest_in_image.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImg);
                guest_in_image.setIcon(newImage);

                //close............................ 
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
//==============================================================================

    public void UpdateGuest_information() {
        if (guest_id.getText().length()!=8) {
            guest_warning_text.setText("Please check the guest ID must 8 characters!");
            guest_id.setBackground(new Color(197, 232, 240));
            guest_id.requestFocus();
        } 
        else if (guest_fullname.getText().equals("")) {
            guest_warning_text.setText("Please fillout the guest name");
            guest_fullname.setBackground(new Color(197, 232, 240));
            guest_fullname.requestFocus();
        } else if (guest_identitity.getText().equals("")) {
            guest_warning_text.setText("Please fillout the guest identity");
            guest_identitity.setBackground(new Color(197, 232, 240));
            guest_identitity.requestFocus();
        } else if (guest_tel.getText().equals("")) {
            guest_warning_text.setText("Please fillout the guest telephone");
            guest_tel.setBackground(new Color(197, 232, 240));
            guest_tel.requestFocus();
        } else if (guest_email.getText().equals("")) {
            guest_warning_text.setText("Please fillout the guest email");
            guest_email.setBackground(new Color(197, 232, 240));
            guest_email.requestFocus();
        } else if (guest_purpose_visit.getText().equals("")) {
            guest_warning_text.setText("Please fillout the visit purpose");
            guest_purpose_visit.setBackground(new Color(197, 232, 240));
            guest_purpose_visit.requestFocus();
        } else if (guest_address.getText().equals("")) {
            guest_warning_text.setText("Please fillout the guest address");
            guest_address.setBackground(new Color(197, 232, 240));
            guest_address.requestFocus();
        }
        else {
            //no image
            if (guestImgPath == null) {
                try {
                    UpdateQuery = "UPDATE `guest` SET `guest_name`=?,`gidentity`=?,`gtel`=?,`gemail`=?,`gvisit`=?,`gaddress`=? WHERE `guest_id`='" + guest_id.getText() + "'";
                    ps = conn.prepareStatement(UpdateQuery);

                    ps.setString(1, guest_fullname.getText());
                    ps.setString(2, guest_identitity.getText());
                    ps.setString(3, guest_tel.getText());
                    ps.setString(4, guest_email.getText());
                    ps.setString(5, guest_purpose_visit.getText());
                    ps.setString(6, guest_address.getText());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Guest Data Updated...");
                    clearGuestForm();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } else {
                //if have new image..
                try {
                    UpdateQuery = "UPDATE `guest` SET `guest_name`=?,`gidentity`=?,`gtel`=?,`gemail`=?,`gvisit`=?,`gaddress`=?,`gimage`=? WHERE `guest_id`='" + guest_id.getText() + "'";
                    ps = conn.prepareStatement(UpdateQuery);

                    ps.setString(1, guest_fullname.getText());
                    ps.setString(2, guest_identitity.getText());
                    ps.setString(3, guest_tel.getText());
                    ps.setString(4, guest_email.getText());
                    ps.setString(5, guest_purpose_visit.getText());
                    ps.setString(6, guest_address.getText());
                    
                    InputStream img = new FileInputStream(new File(guestImgPath));
                    ps.setBlob(7, img);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Guest  Data Updated...");
                    clearGuestForm();
                } catch (SQLException | FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }

        }
    }
//==============================================================================

}
