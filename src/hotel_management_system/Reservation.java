package hotel_management_system;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Sujan
 */
public class Reservation extends Main_Window {

    public void confirming_reservation() {
        if (Reservation_Guest_Id_in.getText().equals("")) {
            ReservationWarning.setText("Please get the guest ID by clicking the guest table");
            GuestIdTable_reservation.requestFocus();
        } else if (Reservation_Room_id.getText().equals("") || Reservation_Price.getText().equals("")) {
            ReservationWarning.setText("Please chooice the room by clicking the Available rooms table");
            ReservationRoomAvble.requestFocus();
        } else if (resrevation_staying_days.getText().equals("")) {
            ReservationWarning.setText("Please chooice the Checkin and Checkout date and press stay days button!");
            DateIn.requestFocus();
            DateOut.requestFocus();
            reserCountBtn.requestFocus();
        } else {
            try {
                ps = conn.prepareStatement("INSERT INTO `reservation`(`reservation_id`,`room_id`,`check_in`,`check_out`,`guest_id`,`amount`)values(?,?,?,?,?,?)");
                ps.setString(1, Reservation_Id_in.getText());
                ps.setString(2, Reservation_Room_id.getText());

                SimpleDateFormat myfort = new SimpleDateFormat("MM-dd-yyyy");
                String chin = myfort.format(DateIn.getDate());
                String chout = myfort.format(DateOut.getDate());
                ps.setString(3, chin);
                ps.setString(4, chout);
                ps.setString(5, Reservation_Guest_Id_in.getText());

                double days = Double.valueOf(resrevation_staying_days.getText());
                double perdayprice = Double.valueOf(Reservation_Price.getText());
                double reservationamount = perdayprice * days;
                ps.setDouble(6, reservationamount);
                ps.executeUpdate();
                // JOptionPane.showMessageDialog(null,Reservation_Room_id.getText()+" Resereved...!");

                //===========================================
                //FOR BILLING SECTION
                //===========================================
                String outResrvationprice = String.valueOf(reservationamount);
                res_guest_id.setText(Reservation_Guest_Id_in.getText());
                res_reservation_id.setText(Reservation_Id_in.getText());
                res_staying_days.setText(resrevation_staying_days.getText());
                res_basic_amount.setText(outResrvationprice);
                setResrvation_info_billing_section();
                //===========================================
                clear_reservation_form();
                Dynamic_Panel.removeAll();
                Dynamic_Panel.removeAll();
                Dynamic_Panel.repaint();
                Dynamic_Panel.revalidate();
                Dynamic_Panel.add(Billing_And_Report);
                Dynamic_Panel.repaint();
                Dynamic_Panel.revalidate();
                txt_dynamic_title_bar.setText("Billing and Report Generating...");
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

    }

    public void setResrvation_info_billing_section() {
        try {
            String qry = "SELECT * FROM `guest` WHERE guest_id = '" + Reservation_Guest_Id_in.getText() + "'";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            if (rs.next()) {
                res_guest_name.setText(rs.getString("guest_name"));
                res_guest_address.setText(rs.getString("gaddress"));
                res_guest_email.setText(rs.getString("gemail"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //=======================================================================
    //CLEAR RESERVATION FORM
    public void clear_reservation_form() {
        Reservation_Room_id.setText("");
        resrevation_staying_days.setText("");
        Reservation_Guest_Id_in.setText("");
        Reservation_Price.setText("");
        ReservationWarning.setText("");
        reservation.revalidate();
    }
    //========================================================================
    // DELETE RESERVATIONS

    public void delete_the_reservation() {
        try {
            //res_reservation_id.getText();
            String sql = "DELETE FROM `reservation` WHERE `reservation_id`=" + res_reservation_id.getText();
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            ReservationWarning.setText("SQL error: " + e.getMessage());
        }
    }
}
