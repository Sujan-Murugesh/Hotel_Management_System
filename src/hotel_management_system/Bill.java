package hotel_management_system;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sujan
 */
public class Bill extends Main_Window {

    double reservationAmount;
    double tax;
    double discount;
    double res_Advance;
    double Total_Amount;
    double balance_resAmount;
    double subTotal;

    public void reservation_calculate_total() {
        //res_taxes   res_discount  res_advance_amount  //output -res_total_amount

        //1.CHECK 3 INPUTS ARE TRUE T D AD  1 1 1
        if (!"".equals(res_taxes.getText()) && !"".equals(res_discount.getText()) && !"".equals(res_advance_amount.getText())) {
            tax = Double.parseDouble(res_taxes.getText());
            discount = Double.parseDouble(res_discount.getText());
            res_Advance = Double.parseDouble(res_advance_amount.getText());
            reservationAmount = Double.parseDouble(res_basic_amount.getText());

            subTotal = (reservationAmount + (reservationAmount * tax / 100));

            Total_Amount = (reservationAmount + (reservationAmount * tax / 100)) - (reservationAmount * discount / 100);
            balance_resAmount = Total_Amount - res_Advance;

            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(balance_resAmount));
        } //2.CHECK IF EVERY INPUTS ARE FALSE 0 0 0
        else if ("".equals(res_taxes.getText()) && "".equals(res_discount.getText()) && "".equals(res_advance_amount.getText())) {
            res_total_amount.setText(res_basic_amount.getText());
            res_Balance_resAmount.setText(res_basic_amount.getText());
        } //3. 0 0 1
        else if ("".equals(res_taxes.getText()) && "".equals(res_discount.getText()) && !"".equals(res_advance_amount.getText())) {

            Total_Amount = Double.parseDouble(res_basic_amount.getText());
            res_Advance = Double.parseDouble(res_advance_amount.getText());
            balance_resAmount = Total_Amount - res_Advance;

            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(balance_resAmount));
        } // 4. 0 1 0
        else if ("".equals(res_taxes.getText()) && !"".equals(res_discount.getText()) && "".equals(res_advance_amount.getText())) {
            discount = Double.parseDouble(res_discount.getText());
            reservationAmount = Double.parseDouble(res_basic_amount.getText());
            Total_Amount = reservationAmount - (reservationAmount * discount / 100);
            balance_resAmount = Total_Amount;

            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(Total_Amount));
        } //5. 0 1 1
        else if ("".equals(res_taxes.getText()) && !"".equals(res_discount.getText()) && !"".equals(res_advance_amount.getText())) {
            discount = Double.parseDouble(res_discount.getText());
            res_Advance = Double.parseDouble(res_advance_amount.getText());
            reservationAmount = Double.parseDouble(res_basic_amount.getText());

            Total_Amount = reservationAmount - (reservationAmount * discount / 100);
            balance_resAmount = Total_Amount - res_Advance;

            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(balance_resAmount));
        } //6. 1 0 0
        else if (!"".equals(res_taxes.getText()) && "".equals(res_discount.getText()) && "".equals(res_advance_amount.getText())) {
            tax = Double.parseDouble(res_taxes.getText());
            reservationAmount = Double.parseDouble(res_basic_amount.getText());

            Total_Amount = reservationAmount + (reservationAmount * tax / 100);

            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(Total_Amount));
        } //7. 1 0 1
        else if (!"".equals(res_taxes.getText()) && "".equals(res_discount.getText()) && !"".equals(res_advance_amount.getText())) {
            tax = Double.parseDouble(res_taxes.getText());
            reservationAmount = Double.parseDouble(res_basic_amount.getText());
            res_Advance = Double.parseDouble(res_advance_amount.getText());

            Total_Amount = reservationAmount + (reservationAmount * tax / 100);
            balance_resAmount = Total_Amount - res_Advance;

            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(balance_resAmount));
        } //8. 1 1 0
        else if (!"".equals(res_taxes.getText()) && !"".equals(res_discount.getText()) && "".equals(res_advance_amount.getText())) {
            tax = Double.parseDouble(res_taxes.getText());
            reservationAmount = Double.parseDouble(res_basic_amount.getText());
            discount = Double.parseDouble(res_discount.getText());

            Total_Amount = (reservationAmount + (reservationAmount * tax / 100)) - (reservationAmount * discount / 100);
            balance_resAmount = Total_Amount;

            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(balance_resAmount));
        }
    }

    //===========================================================================
    //DISPLAY THE BILL ON THE TEXT AREA
    //===========================================================================
    public void create_the_reservation_bill() {
        String billno = res_bill_id.getText();//1
        String date = DateLbl.getText();//2
        String time = time_lbl.getText();//3
        String gId = res_guest_id.getText();//4
        String gName = res_guest_name.getText();//5
        String RA = res_total_amount.getText();//6
        String ads = res_advance_amount.getText();//7
        String pamt = res_Balance_resAmount.getText();//8
        String coh = res_Cash_on_hand.getText();//9
        String blce = null;//10
        double cash;
        double res_Balc;
        double advanceres;
        double resAm = Double.parseDouble(res_total_amount.getText());

        if (res_advance_amount.getText().equals("")) {
            cash = Double.parseDouble(res_Cash_on_hand.getText());
            res_Balc = cash - resAm;
            blce = String.valueOf(res_Balc);
            ads = "-------";
        } else if (!res_advance_amount.getText().equals("")) {
            cash = Double.parseDouble(res_Cash_on_hand.getText());
            advanceres = Double.parseDouble(res_advance_amount.getText());
            res_Balc = cash - advanceres;
            blce = String.valueOf(res_Balc);
            ads = res_advance_amount.getText();
        }
        //display bill on text area
        bill_display.append(""
                + "=====================================\n"
                + "                            Gamage Nest & Rest           \n"
                + "                         Gadhuwa road Mirriswatta,        \n"
                + "                               Kamburupitiya.             \n"
                + "Bill no:" + billno + "         \tTel:+94 71 810 5051\n"
                + "____________________________________________\n"
                + "Date :" + date + "                            Time:" + time + "\n"
                + "____________________________________________\n"
                + "Guest ID   :" + gId + "                      \n"
                + "Guest Name :" + gName + "                    \n"
                + "=====================================\n"
                + "  Discription\t                                              Amount          \n"
                + "____________________________________________\n"
                + "Reservation Amount...................................." + RA + "   \n"
                + "Advance Amount..........................................." + ads + ".0  \n"
                + "=====================================\n"
                + "Payable Amount..........................................." + pamt + " \n"
                + "=====================================\n"
                + "Cash on hand .............................................." + coh + ".0  \n"
                + "Balance ........................................................" + blce + " \n"
                + "____________________________________________\n"
                + "\n"
                + "    * * * WELCOME TO GAMAGE NEST & REST * * *\n"
                + "                       gamagehotel7@gmail.com          \n"
                + "=====================================");
    }
    //===========================================================================

    public void insert_reservation_bill_data() {
        //res_bill_id,guest_id,reservation_amount,tax,discount,advance_amount,	total_res_amount,payable_amount
        try {
            ps = conn.prepareStatement("INSERT INTO `reservationbill`(`res_bill_id`,`guest_id`,`reservation_amount`,`tax`,`discount`,`advance_amount`,`total_res_amount`,`payable_amount`)values(?,?,?,?,?,?,?,?)");
            ps.setString(1, res_bill_id.getText());
            ps.setString(2, res_guest_id.getText());

            double res_amount = Double.parseDouble(res_basic_amount.getText());
            ps.setDouble(3, res_amount);

            double taxes = Double.parseDouble(res_taxes.getText());
            ps.setDouble(4, taxes);

            double disct = Double.parseDouble(res_discount.getText());
            ps.setDouble(5, disct);

            double ad = Double.parseDouble(res_advance_amount.getText());
            ps.setDouble(6, ad);

            double tra = Double.parseDouble(res_total_amount.getText());
            ps.setDouble(6, tra);

            double pa = Double.parseDouble(res_Balance_resAmount.getText());
            ps.setDouble(6, pa);
            ps.executeUpdate();
            clearing_reservation_form_feilds();
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //PRINTING FOOD ORDER BILL
    public void displying_food_order_bill() {

        //bill_display1
        bill_display1.setText(bill_display1.getText() + "=======================================\n");
        bill_display1.setText(bill_display1.getText() + "\tFOOD ODER BILL\n");
        bill_display1.setText(bill_display1.getText() + "=======================================\n");
        bill_display1.setText(bill_display1.getText() + "\n");
        DefaultTableModel getItems = (DefaultTableModel) ViewFooditem_card.getModel();
        bill_display1.setText(bill_display1.getText() + "Item" + " \t\t" + "Qty" + "\t" + "Unitprice" + "\t" + "Amount" + "\n");
        bill_display1.setText(bill_display1.getText() + "\n");

        for (int i = 0; i < ViewFooditem_card.getRowCount(); i++) {
            String Item = ViewFooditem_card.getValueAt(i, 0).toString();
            String Qty = ViewFooditem_card.getValueAt(i, 1).toString();
            String Unitprice = ViewFooditem_card.getValueAt(i, 2).toString();
            String Amount = ViewFooditem_card.getValueAt(i, 3).toString();
            bill_display1.setText(bill_display1.getText() + Item + " \t\t" + Qty + "\t" + Unitprice + "\t" + Amount + "\n");
        }
        bill_display1.setText(bill_display1.getText() + "\n");
        bill_display1.setText(bill_display1.getText() + "=======================================\n");
        bill_display1.setText(bill_display1.getText() + "\tTHANK YOU FOR YOUR ORDERING!\n");
        bill_display1.setText(bill_display1.getText() + "=======================================\n");
        Dynamic_Panel.removeAll();
        Dynamic_Panel.removeAll();
        Dynamic_Panel.repaint();
        Dynamic_Panel.revalidate();
        Dynamic_Panel.add(Billing_And_Report.add(bill_base.add(Food_order_bill)));
        Dynamic_Panel.repaint();
        Dynamic_Panel.revalidate();
    }

    public void clearing_reservation_form_feilds() {
        res_bill_id.setText("");
        res_guest_id.setText("");
        res_guest_name.setText("");
        res_guest_email.setText("");
        res_reservation_id.setText("");
        res_staying_days.setText("");
        res_basic_amount.setText("");
        res_total_amount.setText("");
        res_Balance_resAmount.setText("");
        res_taxes.setText("");
        res_discount.setText("");
        res_advance_amount.setText("");
        res_Cash_on_hand.setText("");
        bill_display.setText("");
        reservation_bill_warning.setText("");

    }
}
