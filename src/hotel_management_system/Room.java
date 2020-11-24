package hotel_management_system;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Sujan
 */
public class Room extends Main_Window {

    public void add_new_rooms() {
        if (roomIdin.getText().equals("")) {
            roomWarning.setText("You can not change room id");
            roomIdin.setBackground(new Color(197, 232, 240));
        } else if (jComboBoxRoomtype.getSelectedIndex() == 0) {
            jComboBoxRoomtype.requestFocus();
            jComboBoxRoomtype.setBackground(new Color(197, 232, 240));
            roomWarning.setText("Please select the room type");
        } else if (jComboBoxroomBedtype.getSelectedIndex() == 0) {
            jComboBoxroomBedtype.requestFocus();
            jComboBoxroomBedtype.setBackground(new Color(197, 232, 240));
            roomWarning.setText("Please select the bed type");
        } else if (roomInprice.getText().equals("")) {
            roomInprice.setBackground(new Color(197, 232, 240));
            roomWarning.setText("Please fillout the room price!");
        } else {
            try {
                ps = conn.prepareStatement("INSERT INTO `room`(`id`,`type`,`bedtype`,`price`)values(?,?,?,?)");
                ps.setString(1, roomIdin.getText());
                ps.setString(2, jComboBoxRoomtype.getSelectedItem().toString());
                ps.setString(3, jComboBoxroomBedtype.getSelectedItem().toString());
                double roomprice = Double.valueOf(roomInprice.getText());
                ps.setDouble(4, roomprice);
                ps.executeUpdate();
                //JOptionPane.showMessageDialog(null, "New room created ", "Success!", HEIGHT);
                clearRoomForm();
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public void clearRoomForm() {
        jComboBoxRoomtype.setSelectedIndex(0);
        jComboBoxroomBedtype.setSelectedIndex(0);
        roomWarning.setText("");
        roomInprice.setText("");
        roomInprice.setBackground(new Color(255, 255, 255));

    }

    //====================
    //show data to the j table for confermation
    public void showRooms_data_ontable() {
        try {
            String qry = "select * from `room` order by id desc";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            AddRoomTableIn.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    //========================================
    //FOR UPDATE ROOM DATAS
    //========================================
    public void update_Rooms() {
        if (roomIdin.getText().equals("")) {
            roomWarning.setText("You can not change room id");
            roomIdin.setBackground(new Color(197, 232, 240));
        } else if (jComboBoxRoomtype.getSelectedIndex() == 0) {
            jComboBoxRoomtype.requestFocus();
            jComboBoxRoomtype.setBackground(new Color(197, 232, 240));
            roomWarning.setText("Please select the room type");
        } else if (jComboBoxroomBedtype.getSelectedIndex() == 0) {
            jComboBoxroomBedtype.requestFocus();
            jComboBoxroomBedtype.setBackground(new Color(197, 232, 240));
            roomWarning.setText("Please select the bed type");
        } else if (roomInprice.getText().equals("")) {
            roomInprice.setBackground(new Color(197, 232, 240));
            roomWarning.setText("Please fillout the room price!");
        } else {
            try {
                UpdateQuery = "UPDATE `room` SET `type`=?,`bedtype`=?,`price`=? WHERE `id`='" + roomIdin.getText() + "'";
                ps = conn.prepareStatement(UpdateQuery);
                ps.setString(1, jComboBoxRoomtype.getSelectedItem().toString());
                ps.setString(2, jComboBoxroomBedtype.getSelectedItem().toString());
                double roomprice = Double.valueOf(roomInprice.getText());
                ps.setDouble(3, roomprice);
                ps.executeUpdate();
                //JOptionPane.showMessageDialog(null, "Guest Data Updated...");
                clearRoomForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
    //====================================
    //delete code
    //====================================
    public void delete_rooms() {
        try {
            String Rid = roomIdin.getText();
            String sql2 = "DELETE FROM `room` WHERE `id`=" + Rid;
            stmt = conn.prepareStatement(sql2);
            stmt.executeUpdate(sql2);

            showRooms_data_ontable();
            clearRoomForm();

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    //====================================
}
