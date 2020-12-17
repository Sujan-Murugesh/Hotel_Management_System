package hotel_management_system;

import static hotel_management_system.Main_Window.ViewFooditem_card;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Sujan
 */
public class FoodOder extends Main_Window {

    public void setSub_total_price() {
        if (!oder_food_qty.getText().equals("") && !oder_food_unit_price.getText().equals("")) {
            String qt = oder_food_qty.getText();
            String utPr = oder_food_unit_price.getText();

            double UniPri = Double.parseDouble(utPr);
            double unitsFqty = Double.parseDouble(qt);
            double tl = (UniPri * unitsFqty);

            String tlf = String.valueOf(tl);
            oder_food_total_price.setText(tlf);
            oder_food_warning.setText("");
        }
    }
//=============================================
    //INSERT THE ORDER CARD ITEMS INTO DATABASE

    public void insert_food_order_items() {
        //food_oder
        int numrow = ViewFooditem_card.getRowCount();
        if (numrow > 0) {
            if (Foodorder_guest_id_combo.getSelectedIndex() == 0) {
                oder_food_warning.setText("Please Select the guest id");
                Foodorder_guest_id_combo.requestFocus();
                Foodorder_guest_id_combo.setBackground(new Color(197, 232, 240));
            } else if (Foodorder_reservation_id_com.getSelectedIndex() == 0) {
                oder_food_warning.setText("Please Select the Reservation Id");
                Foodorder_reservation_id_com.requestFocus();
                Foodorder_reservation_id_com.setBackground(new Color(197, 232, 240));
            } else {
                try {
                    ps = conn.prepareStatement("INSERT INTO `food_oder`(`oder_id`,`guest_id`,`resrvation_id`,`items`,`qty`,"
                            + "`price`,`order_date`,`time`)values(?,?,?,?,?,?,?,?)");

                    for (int i = 1; i <= numrow; i++) {
                        ps.setString(1, food_order_id.getText());
                        ps.setString(2, Foodorder_guest_id_combo.getSelectedItem().toString());
                        ps.setString(3, Foodorder_reservation_id_com.getSelectedItem().toString());
                        int index = i;
                        String foodnm = (ViewFooditem_card.getModel().getValueAt(index, 0).toString());
                        ps.setString(4, foodnm);

                        String fdQty = (ViewFooditem_card.getModel().getValueAt(index, 1).toString());
                        int qt = Integer.parseInt(fdQty);
                        ps.setInt(5, qt);

                        String FdPrice = (ViewFooditem_card.getModel().getValueAt(index, 3).toString());
                        double prs = Double.parseDouble(FdPrice);
                        ps.setDouble(6, prs);
                        ps.setString(7, DateLbl.getText());
                        ps.setString(8, time_lbl.getText());
                        ps.addBatch();
                    }

                } catch (NumberFormatException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        } else {
            oder_food_warning.setText("Please add the food items into the Card!");
        }
    }

    //=================================================
    //CANCEL THE THE FOOD_TEMPORY ODER
    public void canceling_oder() {

        try {
            String oid = food_order_id.getText();
            //DELETE FROM `food_oder` WHERE oder_id = "ODR10000"
            String sql2 = "DELETE FROM `food_oder` WHERE `oder_id`='"+food_order_id.getText()+"'";
            stmt = conn.prepareStatement(sql2);
            stmt.executeUpdate(sql2);

            JOptionPane.showMessageDialog(null, oid + "oredr cancelled!");

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

}
