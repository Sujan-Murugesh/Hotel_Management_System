package hotel_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Sujan
 */
public class Main_Window extends javax.swing.JFrame {

    Connection conn = null;
    Statement stmt = null;
    String UpdateQuery = null;
    PreparedStatement ps;
    ResultSet rs;
    int profilePicStatus = 0;

    private int xMouse;
    private int yMouse;

    ArrayList<String> foododeritemName = new ArrayList<>();
    ArrayList<String> foododerquantity = new ArrayList<>();
    ArrayList<String> foododerItemPrice = new ArrayList<>();
    public static String userCameraImgPath = null;

    public Main_Window() {
        URL iconUrl = getClass().getResource("sujan.png");
        ImageIcon imageicon = new ImageIcon(iconUrl);
        this.setIconImage(imageicon.getImage());
        initComponents();
        setDateandTime();
        conn = Db_connection.connect();
//------------------------------------------------
        autoId();
        roomo_autoId();
        reservation_autoID();
        reservationBill_autoID();
        FOOD_ORDERautoID();
        final_bill_autoID();
//------------------------------------------------
        Camera_panel1.setVisible(false);
        jPanelFood_order_confirm.setVisible(false);
        jToolBar1.setVisible(false);
        //jPanelFinalBillingpart.setVisible(false);
        //radioBTNgrp();
        fillFood_order_guest_combo();
        fillFood_Resrvation_id_combo();
    }
    //=========================================================================================
    static TableDesign header = new TableDesign();

    public static void jtableHdDEsign() {
        //TableDesign.tableHead();
    }

    public void tableHead() {
        JTableHeader stResulttbl = jTableGuest.getTableHeader();
        stResulttbl.setBackground(Color.red);
        stResulttbl.setForeground(Color.BLACK);

    }

    //==============================^^^^^^^^^^^^^^^^^^^^^^^^^^^^^==============================
    //TO GENERATING GUEST ID AUTOMATICALLY...
    //==============================^^^^^^^^^^^^^^^^^^^^^^^^^^^^^==============================
    private void autoId() {
        try {
            String sql = "select * from `guest` order by guest_id desc limit 1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String rnno = rs.getString("guest_id");
                int co = rnno.length();
                String txt = rnno.substring(0, 3);
                String num = rnno.substring(3, co);
                int m = Integer.parseInt(num);
                m++;
                String snum = Integer.toString(m);
                String ftext = txt + snum;
                guest_id.setText(ftext);
            } else {
                guest_id.setText("GNR10000");
            }

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //======================================================================================
    //AUTO ID FOR ROOOMS
    //======================================================================================
    private void roomo_autoId() {
        try {
            String sql = "select * from `room` order by id desc limit 1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String rnno = rs.getString("id");
                int co = rnno.length();
                String txt = rnno.substring(0, 3);
                String num = rnno.substring(3, co);
                int m = Integer.parseInt(num);
                m++;
                String snum = Integer.toString(m);
                String ftext = txt + snum;
                roomIdin.setText(ftext);
            } else {
                roomIdin.setText("RID10000");
            }

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //===========================================================================================
    //AUTO ID FOR RESERVATIONS ROOMS
    //==========================================================================================
    private void reservation_autoID() {
        try {
            String sql = "SELECT * FROM `reservation` order by reservation_id desc limit 1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String rnno = rs.getString("reservation_id");
                int co = rnno.length();
                String txt = rnno.substring(0, 3);
                String num = rnno.substring(3, co);
                int m = Integer.parseInt(num);
                m++;
                String snum = Integer.toString(m);
                String ftext = txt + snum;
                Reservation_Id_in.setText(ftext);
            } else {
                Reservation_Id_in.setText("RES10000");
            }

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void reservationBill_autoID() {
        try {
            String sql = "SELECT * FROM `reservationbill` order by res_bill_id desc limit 1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String rnno = rs.getString("res_bill_id");
                int co = rnno.length();
                String txt = rnno.substring(0, 3);
                String num = rnno.substring(3, co);
                int m = Integer.parseInt(num);
                m++;
                String snum = Integer.toString(m);
                String ftext = txt + snum;
                res_bill_id.setText(ftext);
            } else {
                res_bill_id.setText("GBR10000");
            }

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //======FOOD ORDER AUTO ID
    private void FOOD_ORDERautoID() {
        try {
            String sql = "SELECT * FROM `food_oder` order by oder_id desc limit 1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String rnno = rs.getString("oder_id");
                int co = rnno.length();
                String txt = rnno.substring(0, 3);
                String num = rnno.substring(3, co);
                int m = Integer.parseInt(num);
                m++;
                String snum = Integer.toString(m);
                String ftext = txt + snum;
                food_order_id.setText(ftext);
            } else {
                food_order_id.setText("ODR10000");
            }

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //FINAL BILLING AUTO ID
    private void final_bill_autoID() {
        try {
            String sql = "SELECT * FROM `bill` order by fbill_id desc limit 1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String rnno = rs.getString("fbill_id");
                int co = rnno.length();
                String txt = rnno.substring(0, 3);
                String num = rnno.substring(3, co);
                int m = Integer.parseInt(num);
                m++;
                String snum = Integer.toString(m);
                String ftext = txt + snum;
                checkout_bill_id.setText(ftext);
            } else {
                checkout_bill_id.setText("FCB10000");
            }

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    //===========================================================================================
    //DESIGN LOOK PERFECT STATIC PANEL DESIGN
    //==========================================================================================
    static BtnClickEvents btnColors = new BtnClickEvents();

    public void BtnPanelClickevent(java.awt.event.MouseEvent evt) {
        btnColors.btn_color_change(evt);
    }
    //==========================================================================================
    //SETTING TIME AND DATE
    //==========================================================================================
    Date_Time dt = new Date_Time();

    public void setDateandTime() {
        dt.showDate();
        dt.showTime();
    }
    //===========================================================================================
    //ALL METHODS ABOUT USER CREATION,DELETE,UPDATE,PASSWORD CHANGEING.
    //==========================================================================================
    static User newUser = new User();

    public void createNewUserAc() {
        newUser.input_new_user(); //INSERTING 
    }

    public static void newUserimgChooice() {
        User.chooiceUserImg(); //WHEN CREATING NEW USER
    }

    public void clearUserFeild() {
        newUser.clearNewUserForm();
    }

    public void deletingUserAc() {
        newUser.delete_User_Accounts();
    }

    public void makepuserProfileEditEnable() {
        newUser.setUserProfile_editable();
    }

    public static void newUserimgChooiceToUpdate() {
        User.chooiceUserImgUpdate(); //WHEN CREATING NEW USER
    }

    public void userProfileUpdate() {
        newUser.userDataUpdate();
    }

    //==========================================================================================
    //CHANGE USER PASSWORD
    static Password pw = new Password();

    public void changeUserNewPwd() {
        pw.changeUserPassword();
    }
    //==========================================================================================
    static Guest guest = new Guest();

    public void guestDataInsert2db() {
        guest.insertGuest_Data();
    }

    public void clearGuestFeilds() {
        guest.clearGuestForm();
    }

    public void showGuestDataToJTable1() {
        guest.showGuest_data();
    }

    public void searchingGuestByID() {
        guest.searchGuset_byID();
    }

    public void guestJTableClick() {
        guest.guestJTable_RowClicked();
    }

    public void GuestUpdatedata() {
        guest.UpdateGuest_information();
    }

    //==========================================================================================
    //FOOD AND CATERING PART
    //==========================================================================================
    static Food food = new Food();

    void insertFoodData() {
        food.add_new_food();
    }

    public void chooiceFoodImage() {
        Food.chooiceFoodImg();
    }

    public void foodTableClickedSet() {
        food.food_table_row_clicked();
    }

    public void tableautoRetrivewFood() {
        food.show_data_inJtablefood();
    }

    public void foodUpdate() {
        food.update_food_items();
    }

    public void clearingFoodFormAdd() {
        food.clearFoodForm();
    }

    public void deleteFood_Items() {
        food.delete_food_items();
    }

    public void seeAvailableFoodTableClicked() {
        food.showAvailable_foods_table_clicked();
    }

    public void seefoodItemVieewTable() {
        food.seeAvailabaleFoodJtble();
    }
    //==========================================================================================
    //ROOM MANAGEMENT AND RESERVATION
    //==========================================================================================
    static Room room = new Room();

    public void newRoomInsert() {
        room.add_new_rooms();
    }

    public void clearRoom_form() {
        room.clearRoomForm();
        roomo_autoId();
    }

    public void showRoomDataon_jtable() {
        room.showRooms_data_ontable();
    }

    public void updateRooms() {
        room.update_Rooms();
    }

    public void deletingRooms() {
        room.delete_rooms();
    }
    //==========================================================================================
    //RESERVATIONS
    //-----------------------------------------------------------------------------------------
    static Reservation reserVa = new Reservation();

    public void confirm_Reservations() {
        reserVa.confirming_reservation();
    }

    public void clearing_Reservation_feilds() {
        reserVa.clear_reservation_form();
    }

    public void deleteReservationing() {
        reserVa.delete_the_reservation();
    }
    //==========================================================================================
    //Bill genetation
    //==========================================================================================
    static Bill bill = new Bill();

    public void getReservationTotal() {
        bill.reservation_calculate_total();
    }

    public void setResBillDisplay() {
        bill.create_the_reservation_bill();
    }

    public void insert_ResBill() {
        bill.insert_reservation_bill_data();
        reservationBill_autoID();
    }

    public void clearResrFormFeilds() {
        bill.clearing_reservation_form_feilds();
        reservationBill_autoID();
    }

    public void display_foodOderBilling() {
        bill.displying_food_order_bill();
    }
    //==========================================================================================
    //FOOD ORDERING SECTION
    static FoodOder foodOder = new FoodOder();

//    private void radioBTNgrp() {
//        ButtonGroup rb = new ButtonGroup();
//        rb.add(jRadioButton1);
//        rb.add(jRadioButton2);
//        rb.add(jRadioButton3);
//        rb.add(jRadioButton4);
//        rb.add(jRadioButton5);
//    }
    public void set_oder_Sub_total() {
        foodOder.setSub_total_price();
    }

    private void fillFood_order_guest_combo() {
        try {
            String sql = "select * from guest order by guest_id desc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String od_guest = rs.getString("guest_id");
                Foodorder_guest_id_combo.addItem(od_guest);
                check_out_guest_id.addItem(od_guest);
            }

        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
        }
    }

    //======================
    private void fillFood_Resrvation_id_combo() {
        try {
            String sql = "select * from reservation order by reservation_id desc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String od_res = rs.getString("reservation_id");
                Foodorder_reservation_id_com.addItem(od_res);
                check_out_reservatiion_id.addItem(od_res);
            }

        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
        }
    }

    //===================
    //FOOD ORDER INSERTINFG ...
    public void insert_card_to_db() {
        foodOder.insert_food_order_items();
    }

    public void delete_food_oder_temp() {
        foodOder.canceling_oder();
    }

    //==========================================================================================
    //TODAY CHECK OUT...
    //SELECT `reservation_id`, `room_id`, `check_in`, `guest_id`, `amount` FROM `reservation` WHERE `check_out`="12-05-2020"
    //==========================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        food_price_out1 = new javax.swing.JLabel();
        buttonGroup1Food_order = new javax.swing.ButtonGroup();
        bg = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        DateLbl = new javax.swing.JLabel();
        time_lbl = new javax.swing.JLabel();
        side_Panel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btn_bill_and_report = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_inventory = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn_front_desk = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btn_room_and_hall = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_catering = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btn_housekeeping = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btn_development = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btn_account_setting = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Dynamic_title_bar = new javax.swing.JPanel();
        userNamelbl = new javax.swing.JLabel();
        txt_dynamic_title_bar = new javax.swing.JLabel();
        lbl_userType = new javax.swing.JLabel();
        Dynamic_Panel = new javax.swing.JPanel();
        Billing_And_Report = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jSeparator36 = new javax.swing.JSeparator();
        bill_base = new javax.swing.JPanel();
        Food_order_bill = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        bill_display1 = new javax.swing.JTextArea();
        jSeparator58 = new javax.swing.JSeparator();
        food_bill_id = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        foodbill_Cash_on_hand = new javax.swing.JTextField();
        jButton39 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jLabel90 = new javax.swing.JLabel();
        jSeparator59 = new javax.swing.JSeparator();
        jLabel91 = new javax.swing.JLabel();
        jSeparator60 = new javax.swing.JSeparator();
        food_bill_guest_id = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jSeparator61 = new javax.swing.JSeparator();
        foodbill_guest_name = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jSeparator62 = new javax.swing.JSeparator();
        foodbill_guest_address = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        jSeparator63 = new javax.swing.JSeparator();
        foodbill_amount = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        jSeparator64 = new javax.swing.JSeparator();
        foodbill_balance = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        jSeparator65 = new javax.swing.JSeparator();
        jLabel97 = new javax.swing.JLabel();
        foodbill_discount = new javax.swing.JTextField();
        jSeparator66 = new javax.swing.JSeparator();
        jLabel98 = new javax.swing.JLabel();
        foodbill_taxes = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        food_oder_bill_warning = new javax.swing.JTextField();
        Reservation_bill = new javax.swing.JPanel();
        jSeparator37 = new javax.swing.JSeparator();
        res_guest_id = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jSeparator38 = new javax.swing.JSeparator();
        res_reservation_id = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jSeparator39 = new javax.swing.JSeparator();
        res_staying_days = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jSeparator40 = new javax.swing.JSeparator();
        res_basic_amount = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jSeparator41 = new javax.swing.JSeparator();
        res_guest_name = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jSeparator42 = new javax.swing.JSeparator();
        res_guest_address = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jSeparator43 = new javax.swing.JSeparator();
        res_discount = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jSeparator44 = new javax.swing.JSeparator();
        res_taxes = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jSeparator45 = new javax.swing.JSeparator();
        res_advance_amount = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jSeparator46 = new javax.swing.JSeparator();
        res_total_amount = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jSeparator47 = new javax.swing.JSeparator();
        res_guest_email = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        bill_display = new javax.swing.JTextArea();
        reservation_bill_warning = new javax.swing.JTextField();
        jSeparator49 = new javax.swing.JSeparator();
        res_Balance_resAmount = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jSeparator50 = new javax.swing.JSeparator();
        jLabel85 = new javax.swing.JLabel();
        res_Cash_on_hand = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        jSeparator51 = new javax.swing.JSeparator();
        res_bill_id = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        bill_statistic = new javax.swing.JPanel();
        check_out_bill = new javax.swing.JPanel();
        check_out_guest_id = new javax.swing.JComboBox<>();
        check_out_reservatiion_id = new javax.swing.JComboBox<>();
        jSeparator71 = new javax.swing.JSeparator();
        checkout_guest_idin = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jSeparator72 = new javax.swing.JSeparator();
        checkout_reservation_idin = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        jPanelFinalBillingpart = new javax.swing.JPanel();
        jSeparator73 = new javax.swing.JSeparator();
        checkout_guest_name = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        jSeparator76 = new javax.swing.JSeparator();
        checkout_guest_email = new javax.swing.JTextField();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jSeparator77 = new javax.swing.JSeparator();
        checkout_res_amount = new javax.swing.JTextField();
        jSeparator78 = new javax.swing.JSeparator();
        checkout_order_amount = new javax.swing.JTextField();
        jLabel113 = new javax.swing.JLabel();
        jSeparator79 = new javax.swing.JSeparator();
        checkout_total_amount = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        jSeparator80 = new javax.swing.JSeparator();
        checkout_total_amount1 = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        jSeparator74 = new javax.swing.JSeparator();
        jLabel109 = new javax.swing.JLabel();
        res_Cash_on_hand1 = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        bill_display2 = new javax.swing.JTextArea();
        jSeparator75 = new javax.swing.JSeparator();
        checkout_bill_id = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        jButton53 = new javax.swing.JButton();
        final_billError = new javax.swing.JTextField();
        Frontdest = new javax.swing.JPanel();
        guestRegistrationBTN = new javax.swing.JButton();
        reservationBTN = new javax.swing.JButton();
        reservationUpdateBTN = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        frontDeskBase = new javax.swing.JPanel();
        GuestRegistartion = new javax.swing.JPanel();
        guest_email = new javax.swing.JTextField();
        Address3 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        guest_purpose_visit = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        guest_address = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        guest_in_image = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        guest_warning_text = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        Address4 = new javax.swing.JLabel();
        guest_id = new javax.swing.JTextField();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        guest_fullname = new javax.swing.JTextField();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel44 = new javax.swing.JLabel();
        guest_identitity = new javax.swing.JTextField();
        jSeparator22 = new javax.swing.JSeparator();
        jLabel45 = new javax.swing.JLabel();
        jSeparator23 = new javax.swing.JSeparator();
        guest_tel = new javax.swing.JTextField();
        Camera_panel1 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jCheckBoxCamereCheck = new javax.swing.JCheckBox();
        jButton13 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGuest = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        reservation = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        GuestIdTable_reservation = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        Reservation_Guest_Id_in = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        ReservationRoomAvble = new javax.swing.JTable();
        jLabel65 = new javax.swing.JLabel();
        Reservation_Room_id = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        Reservation_Price = new javax.swing.JTextField();
        ReservationWarning = new javax.swing.JLabel();
        jSeparator24 = new javax.swing.JSeparator();
        jSeparator33 = new javax.swing.JSeparator();
        jSeparator34 = new javax.swing.JSeparator();
        resrevation_staying_days = new javax.swing.JTextField();
        reserCountBtn = new javax.swing.JButton();
        DateIn = new com.toedter.calendar.JDateChooser();
        DateOut = new com.toedter.calendar.JDateChooser();
        jSeparator35 = new javax.swing.JSeparator();
        jButton28 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jSeparator48 = new javax.swing.JSeparator();
        Reservation_Id_in = new javax.swing.JTextField();
        Guest_Statictics = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        ViewBookedRooms = new javax.swing.JTable();
        jLabel50 = new javax.swing.JLabel();
        todayCheckOuts = new javax.swing.JPanel();
        jButton44 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        ViewCheckouts = new javax.swing.JTable();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jSeparator67 = new javax.swing.JSeparator();
        guest_fullname1 = new javax.swing.JTextField();
        jSeparator68 = new javax.swing.JSeparator();
        guest_id_chout = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jButton45 = new javax.swing.JButton();
        jSeparator69 = new javax.swing.JSeparator();
        guest_email_chout = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        today_ch_warning = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        jSeparator81 = new javax.swing.JSeparator();
        res_id_chout = new javax.swing.JTextField();
        todayReservationBTN = new javax.swing.JButton();
        Catering_and_food = new javax.swing.JPanel();
        FoodManagementBTN = new javax.swing.JButton();
        FoodPackageBTN = new javax.swing.JButton();
        jSeparator30 = new javax.swing.JSeparator();
        FoodOderBTN = new javax.swing.JButton();
        Catering_Base = new javax.swing.JPanel();
        foodPackage = new javax.swing.JPanel();
        jComboBoxfoodmenu = new javax.swing.JComboBox<>();
        Foodmenulableout = new javax.swing.JLabel();
        jComboBoxPackage = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        GuestIdTable = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        orderFood = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        ViewFooditem_oder = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        Foodorder_guest_id_combo = new javax.swing.JComboBox<>();
        Foodorder_reservation_id_com = new javax.swing.JComboBox<>();
        jSeparator52 = new javax.swing.JSeparator();
        food_order_id = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        ViewFooditem_card = new javax.swing.JTable();
        oder_food_name = new javax.swing.JTextField();
        oder_food_unit_price = new javax.swing.JTextField();
        oder_food_qty = new javax.swing.JTextField();
        oder_food_total_price = new javax.swing.JTextField();
        jButton36 = new javax.swing.JButton();
        oder_food_warning = new javax.swing.JTextField();
        jSeparator53 = new javax.swing.JSeparator();
        jSeparator54 = new javax.swing.JSeparator();
        jSeparator55 = new javax.swing.JSeparator();
        jPanelFood_order_confirm = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton37 = new javax.swing.JButton();
        jSeparator56 = new javax.swing.JToolBar.Separator();
        jButton38 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        jSeparator57 = new javax.swing.JSeparator();
        food_order_FinalAmount = new javax.swing.JTextField();
        Food_management = new javax.swing.JPanel();
        jSeparator25 = new javax.swing.JSeparator();
        addfoodbase = new javax.swing.JPanel();
        viewfood = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ViewFoodTable = new javax.swing.JTable();
        food_image_lable1 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        food_name_out = new javax.swing.JLabel();
        food_price_out = new javax.swing.JLabel();
        food_description_out = new javax.swing.JLabel();
        food_type_out = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        addfood1 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jSeparator26 = new javax.swing.JSeparator();
        Add_food_name = new javax.swing.JTextField();
        jSeparator27 = new javax.swing.JSeparator();
        Add_food_price = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jComboBoxFoodType = new javax.swing.JComboBox<>();
        food_image_lable = new javax.swing.JLabel();
        jSeparator28 = new javax.swing.JSeparator();
        Add_food_description = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        FoodWarningText = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        Add_food_id = new javax.swing.JTextField();
        jSeparator29 = new javax.swing.JSeparator();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        AddFoodTable = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        AddnewFoodBTN = new javax.swing.JButton();
        jSeparator70 = new javax.swing.JToolBar.Separator();
        Available_foodBTN = new javax.swing.JButton();
        Inventory = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        House_keeping = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        RoomandHall = new javax.swing.JPanel();
        jButtonAddHalls1 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jSeparator16 = new javax.swing.JSeparator();
        RoomandHallBase = new javax.swing.JPanel();
        addNewroom = new javax.swing.JPanel();
        jSeparator31 = new javax.swing.JSeparator();
        jLabel52 = new javax.swing.JLabel();
        jComboBoxroomBedtype = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jComboBoxRoomtype = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        AddRoomTableIn = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        roomWarning = new javax.swing.JLabel();
        roomIdin = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        roomInprice = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jSeparator32 = new javax.swing.JSeparator();
        Account_settings = new javax.swing.JPanel();
        profileBTN = new javax.swing.JButton();
        createuserBTN = new javax.swing.JButton();
        changepwdBTN = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        account_base = new javax.swing.JPanel();
        user_profile = new javax.swing.JPanel();
        jSeparator10 = new javax.swing.JSeparator();
        user_profile_addres2 = new javax.swing.JTextField();
        user_profile_image = new javax.swing.JLabel();
        jButton_user_profile_save = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        profile_warning_text1 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        Address2 = new javax.swing.JLabel();
        user_profile_username = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        user_profile_fullname = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        user_profile_nic = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        user_profile_addres1 = new javax.swing.JTextField();
        user_profile_type = new javax.swing.JTextField();
        comEditCh = new javax.swing.JCheckBox();
        create_newuser_form = new javax.swing.JPanel();
        User_image_lbl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        Address = new javax.swing.JLabel();
        new_username_in = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        new_user_fullname_in = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        new_user_nic_in = new javax.swing.JTextField();
        jComboAc_type = new javax.swing.JComboBox<>();
        jPassword_newUser = new javax.swing.JPasswordField();
        jSeparator7 = new javax.swing.JSeparator();
        Address1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        new_user_addres1_in = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        new_user_addres2_in = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        new_user_warning_text = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        change_password = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        user_password_username = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        jPassword_change_new = new javax.swing.JPasswordField();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jPassword_change_confirm = new javax.swing.JPasswordField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabelpwdswarnning = new javax.swing.JLabel();
        Developers = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jSeparator82 = new javax.swing.JSeparator();

        food_price_out1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        bg.setMaximumSize(new java.awt.Dimension(1332, 721));
        bg.setPreferredSize(new java.awt.Dimension(1332, 721));
        bg.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                bgMouseDragged(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/close.png"))); // NOI18N
        jButton1.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton1FocusGained(evt);
            }
        });
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/minimize.png"))); // NOI18N
        jButton2.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton2.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton2.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });

        DateLbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DateLbl.setForeground(new java.awt.Color(255, 255, 255));
        DateLbl.setText("Hotel Management System");

        time_lbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        time_lbl.setForeground(new java.awt.Color(255, 255, 255));
        time_lbl.setText("00:00:00");

        side_Panel.setBackground(new java.awt.Color(0, 0, 0));
        side_Panel.setMaximumSize(new java.awt.Dimension(277, 618));

        jLabel6.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Gamage Nest & Rest");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn_bill_and_report.setBackground(new java.awt.Color(102, 102, 102));
        btn_bill_and_report.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_bill_and_reportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_bill_and_reportMouseEntered(evt);
            }
        });
        btn_bill_and_report.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/bill.png"))); // NOI18N
        btn_bill_and_report.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Billing & Report");
        btn_bill_and_report.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 150, 30));

        btn_inventory.setBackground(new java.awt.Color(51, 51, 51));
        btn_inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_inventoryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_inventoryMouseEntered(evt);
            }
        });
        btn_inventory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/inventory.png"))); // NOI18N
        btn_inventory.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Inventory ");
        btn_inventory.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 140, 30));

        btn_front_desk.setBackground(new java.awt.Color(51, 51, 51));
        btn_front_desk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_front_deskMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_front_deskMouseEntered(evt);
            }
        });
        btn_front_desk.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/frontDesk.png"))); // NOI18N
        btn_front_desk.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Front Desk & Reservationt ");
        btn_front_desk.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, 30));

        btn_room_and_hall.setBackground(new java.awt.Color(51, 51, 51));
        btn_room_and_hall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_room_and_hallMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_room_and_hallMouseEntered(evt);
            }
        });
        btn_room_and_hall.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/room.png"))); // NOI18N
        btn_room_and_hall.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Room & Hall Booking");
        btn_room_and_hall.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 150, 30));

        btn_catering.setBackground(new java.awt.Color(51, 51, 51));
        btn_catering.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cateringMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cateringMouseEntered(evt);
            }
        });
        btn_catering.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/catering.png"))); // NOI18N
        btn_catering.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Catering & Food ");
        btn_catering.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 150, 30));

        btn_housekeeping.setBackground(new java.awt.Color(51, 51, 51));
        btn_housekeeping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_housekeepingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_housekeepingMouseEntered(evt);
            }
        });
        btn_housekeeping.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/housekeeping.png"))); // NOI18N
        btn_housekeeping.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("House keeping & laundry");
        btn_housekeeping.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 180, 30));

        btn_development.setBackground(new java.awt.Color(51, 51, 51));
        btn_development.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_developmentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_developmentMouseEntered(evt);
            }
        });
        btn_development.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/development.png"))); // NOI18N
        btn_development.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("About Developement");
        btn_development.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 150, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/logo100.png"))); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn_account_setting.setBackground(new java.awt.Color(51, 51, 51));
        btn_account_setting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_account_settingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_account_settingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_account_settingMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_account_settingMouseReleased(evt);
            }
        });
        btn_account_setting.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/acSett.png"))); // NOI18N
        btn_account_setting.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Account Settings");
        btn_account_setting.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 150, 30));

        javax.swing.GroupLayout side_PanelLayout = new javax.swing.GroupLayout(side_Panel);
        side_Panel.setLayout(side_PanelLayout);
        side_PanelLayout.setHorizontalGroup(
            side_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(side_PanelLayout.createSequentialGroup()
                .addGroup(side_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, side_PanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_account_setting, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(side_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btn_development, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_housekeeping, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                        .addComponent(btn_catering, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_room_and_hall, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_front_desk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_inventory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_bill_and_report, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(side_PanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(side_PanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        side_PanelLayout.setVerticalGroup(
            side_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(side_PanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btn_bill_and_report, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_front_desk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_room_and_hall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_catering, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_housekeeping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_development, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_account_setting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Dynamic_title_bar.setBackground(new java.awt.Color(51, 51, 51));

        userNamelbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        userNamelbl.setForeground(new java.awt.Color(255, 255, 255));
        userNamelbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        userNamelbl.setText("user name");
        userNamelbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userNamelblMouseClicked(evt);
            }
        });

        txt_dynamic_title_bar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_dynamic_title_bar.setForeground(new java.awt.Color(255, 255, 255));

        lbl_userType.setBackground(new java.awt.Color(0, 0, 0));
        lbl_userType.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_userType.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout Dynamic_title_barLayout = new javax.swing.GroupLayout(Dynamic_title_bar);
        Dynamic_title_bar.setLayout(Dynamic_title_barLayout);
        Dynamic_title_barLayout.setHorizontalGroup(
            Dynamic_title_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dynamic_title_barLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_dynamic_title_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_userType, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userNamelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Dynamic_title_barLayout.setVerticalGroup(
            Dynamic_title_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userNamelbl, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(txt_dynamic_title_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_userType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Dynamic_Panel.setBackground(new java.awt.Color(153, 153, 153));
        Dynamic_Panel.setLayout(new java.awt.CardLayout());

        Billing_And_Report.setBackground(new java.awt.Color(255, 255, 255));
        Billing_And_Report.setMaximumSize(new java.awt.Dimension(1007, 613));
        Billing_And_Report.setMinimumSize(new java.awt.Dimension(1007, 613));

        jButton27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton27.setText("Reservation Billing");
        jButton27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton27MouseClicked(evt);
            }
        });

        jButton29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton29.setText("Food order Billing");
        jButton29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton29MouseClicked(evt);
            }
        });
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton30.setText("Check out billing");
        jButton30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton30MouseClicked(evt);
            }
        });

        jButton31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton31.setText("Statistics");
        jButton31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton31MouseClicked(evt);
            }
        });

        bill_base.setBackground(new java.awt.Color(255, 255, 255));
        bill_base.setMaximumSize(new java.awt.Dimension(1106, 554));
        bill_base.setMinimumSize(new java.awt.Dimension(1106, 554));
        bill_base.setLayout(new java.awt.CardLayout());

        Food_order_bill.setBackground(new java.awt.Color(255, 255, 255));
        Food_order_bill.setMaximumSize(new java.awt.Dimension(1106, 554));
        Food_order_bill.setMinimumSize(new java.awt.Dimension(1106, 554));

        bill_display1.setColumns(20);
        bill_display1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        bill_display1.setRows(5);
        bill_display1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 2, 1, new java.awt.Color(1, 0, 1)));
        jScrollPane12.setViewportView(bill_display1);

        jSeparator58.setBackground(new java.awt.Color(0, 0, 0));

        food_bill_id.setBackground(new java.awt.Color(242, 248, 249));
        food_bill_id.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        food_bill_id.setBorder(null);
        food_bill_id.setEnabled(false);
        food_bill_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                food_bill_idKeyPressed(evt);
            }
        });

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel89.setText("Bill No :");

        foodbill_Cash_on_hand.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        foodbill_Cash_on_hand.setBorder(null);
        foodbill_Cash_on_hand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                foodbill_Cash_on_handKeyPressed(evt);
            }
        });

        jButton39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/gmail.png"))); // NOI18N
        jButton39.setText("Send Invoice");
        jButton39.setIconTextGap(10);
        jButton39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton39MouseClicked(evt);
            }
        });

        jButton43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/bill_print.png"))); // NOI18N
        jButton43.setText("Print Invoice");
        jButton43.setIconTextGap(10);
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel90.setText("Cash ");

        jSeparator59.setBackground(new java.awt.Color(0, 0, 0));

        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/Cash_inHand.png"))); // NOI18N
        jLabel91.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel91MouseClicked(evt);
            }
        });

        jSeparator60.setBackground(new java.awt.Color(0, 0, 0));

        food_bill_guest_id.setBackground(new java.awt.Color(242, 248, 249));
        food_bill_guest_id.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        food_bill_guest_id.setBorder(null);
        food_bill_guest_id.setEnabled(false);
        food_bill_guest_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                food_bill_guest_idKeyPressed(evt);
            }
        });

        jLabel92.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel92.setText("Guest ID");

        jSeparator61.setBackground(new java.awt.Color(0, 0, 0));

        foodbill_guest_name.setBackground(new java.awt.Color(242, 248, 249));
        foodbill_guest_name.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        foodbill_guest_name.setBorder(null);
        foodbill_guest_name.setEnabled(false);
        foodbill_guest_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                foodbill_guest_nameKeyPressed(evt);
            }
        });

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel93.setText("Guest Name");

        jSeparator62.setBackground(new java.awt.Color(0, 0, 0));

        foodbill_guest_address.setBackground(new java.awt.Color(242, 248, 249));
        foodbill_guest_address.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        foodbill_guest_address.setBorder(null);
        foodbill_guest_address.setEnabled(false);
        foodbill_guest_address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                foodbill_guest_addressKeyPressed(evt);
            }
        });

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel94.setText("Guest email");

        jSeparator63.setBackground(new java.awt.Color(0, 0, 0));

        foodbill_amount.setBackground(new java.awt.Color(242, 248, 249));
        foodbill_amount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        foodbill_amount.setBorder(null);
        foodbill_amount.setEnabled(false);
        foodbill_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                foodbill_amountKeyPressed(evt);
            }
        });

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel95.setText("Amount");

        jSeparator64.setBackground(new java.awt.Color(0, 0, 0));

        foodbill_balance.setBackground(new java.awt.Color(242, 248, 249));
        foodbill_balance.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        foodbill_balance.setBorder(null);
        foodbill_balance.setEnabled(false);
        foodbill_balance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                foodbill_balanceKeyPressed(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel96.setText("Balance");

        jSeparator65.setBackground(new java.awt.Color(0, 0, 0));

        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/percentage_20.png"))); // NOI18N

        foodbill_discount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        foodbill_discount.setBorder(null);
        foodbill_discount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                foodbill_discountKeyPressed(evt);
            }
        });

        jSeparator66.setBackground(new java.awt.Color(0, 0, 0));

        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/percentage_20.png"))); // NOI18N

        foodbill_taxes.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        foodbill_taxes.setBorder(null);
        foodbill_taxes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                foodbill_taxesKeyPressed(evt);
            }
        });

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel99.setText("Discounts");

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel100.setText("Taxes");

        food_oder_bill_warning.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        food_oder_bill_warning.setForeground(new java.awt.Color(255, 0, 0));
        food_oder_bill_warning.setBorder(null);

        javax.swing.GroupLayout Food_order_billLayout = new javax.swing.GroupLayout(Food_order_bill);
        Food_order_bill.setLayout(Food_order_billLayout);
        Food_order_billLayout.setHorizontalGroup(
            Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Food_order_billLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(food_oder_bill_warning, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Food_order_billLayout.createSequentialGroup()
                                .addComponent(jLabel90)
                                .addGap(61, 61, 61)
                                .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Food_order_billLayout.createSequentialGroup()
                                        .addComponent(foodbill_Cash_on_hand)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator59, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36))
                            .addGroup(Food_order_billLayout.createSequentialGroup()
                                .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Food_order_billLayout.createSequentialGroup()
                                        .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jSeparator64)
                                            .addComponent(foodbill_balance, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(Food_order_billLayout.createSequentialGroup()
                                        .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel100)
                                            .addComponent(jLabel99))
                                        .addGap(35, 35, 35)
                                        .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator65, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Food_order_billLayout.createSequentialGroup()
                                                    .addComponent(foodbill_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(Food_order_billLayout.createSequentialGroup()
                                                    .addComponent(foodbill_taxes, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jSeparator66, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(Food_order_billLayout.createSequentialGroup()
                            .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel93)
                                .addComponent(jLabel94)
                                .addComponent(jLabel92)
                                .addComponent(jLabel95)
                                .addComponent(jLabel89))
                            .addGap(21, 21, 21)
                            .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator58)
                                    .addComponent(food_bill_id, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator62)
                                    .addComponent(jSeparator60)
                                    .addComponent(foodbill_guest_address)
                                    .addComponent(jSeparator61)
                                    .addComponent(foodbill_guest_name)
                                    .addComponent(food_bill_guest_id)
                                    .addComponent(jSeparator63, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(foodbill_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Food_order_billLayout.createSequentialGroup()
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );
        Food_order_billLayout.setVerticalGroup(
            Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Food_order_billLayout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Food_order_billLayout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Food_order_billLayout.createSequentialGroup()
                        .addComponent(food_oder_bill_warning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(food_bill_id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator58, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(food_bill_guest_id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel93)
                            .addGroup(Food_order_billLayout.createSequentialGroup()
                                .addComponent(foodbill_guest_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(foodbill_guest_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(foodbill_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Food_order_billLayout.createSequentialGroup()
                                .addComponent(foodbill_balance, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Food_order_billLayout.createSequentialGroup()
                                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Food_order_billLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(foodbill_taxes)
                                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator66, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(foodbill_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator65, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Food_order_billLayout.createSequentialGroup()
                                .addGroup(Food_order_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(foodbill_Cash_on_hand, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator59, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(60, 60, 60))
        );

        bill_base.add(Food_order_bill, "card3");

        Reservation_bill.setBackground(new java.awt.Color(255, 255, 255));
        Reservation_bill.setMaximumSize(new java.awt.Dimension(1106, 554));
        Reservation_bill.setMinimumSize(new java.awt.Dimension(1106, 554));

        jSeparator37.setBackground(new java.awt.Color(0, 0, 0));

        res_guest_id.setBackground(new java.awt.Color(242, 248, 249));
        res_guest_id.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_guest_id.setBorder(null);
        res_guest_id.setEnabled(false);
        res_guest_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_guest_idKeyPressed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Guest ID");

        jSeparator38.setBackground(new java.awt.Color(0, 0, 0));

        res_reservation_id.setBackground(new java.awt.Color(242, 248, 249));
        res_reservation_id.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_reservation_id.setBorder(null);
        res_reservation_id.setEnabled(false);
        res_reservation_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_reservation_idKeyPressed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel69.setText("Reservation ID");

        jSeparator39.setBackground(new java.awt.Color(0, 0, 0));

        res_staying_days.setBackground(new java.awt.Color(242, 248, 249));
        res_staying_days.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_staying_days.setBorder(null);
        res_staying_days.setEnabled(false);
        res_staying_days.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_staying_daysKeyPressed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel70.setText("Day's in stay");

        jSeparator40.setBackground(new java.awt.Color(0, 0, 0));

        res_basic_amount.setBackground(new java.awt.Color(242, 248, 249));
        res_basic_amount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_basic_amount.setBorder(null);
        res_basic_amount.setEnabled(false);
        res_basic_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_basic_amountKeyPressed(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel71.setText("Reservation Amount");

        jSeparator41.setBackground(new java.awt.Color(0, 0, 0));

        res_guest_name.setBackground(new java.awt.Color(242, 248, 249));
        res_guest_name.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_guest_name.setBorder(null);
        res_guest_name.setEnabled(false);
        res_guest_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_guest_nameKeyPressed(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel72.setText("Guest Name");

        jSeparator42.setBackground(new java.awt.Color(0, 0, 0));

        res_guest_address.setBackground(new java.awt.Color(242, 248, 249));
        res_guest_address.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_guest_address.setBorder(null);
        res_guest_address.setEnabled(false);
        res_guest_address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_guest_addressKeyPressed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel73.setText("Guest Address");

        jSeparator43.setBackground(new java.awt.Color(0, 0, 0));

        res_discount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_discount.setBorder(null);
        res_discount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_discountKeyPressed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel74.setText("Discounts");

        jSeparator44.setBackground(new java.awt.Color(0, 0, 0));

        res_taxes.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_taxes.setBorder(null);
        res_taxes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_taxesKeyPressed(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel75.setText("Taxes");

        jSeparator45.setBackground(new java.awt.Color(0, 0, 0));

        res_advance_amount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_advance_amount.setBorder(null);
        res_advance_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_advance_amountKeyPressed(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel76.setText("Advance Amount");

        jSeparator46.setBackground(new java.awt.Color(0, 0, 0));

        res_total_amount.setBackground(new java.awt.Color(242, 248, 249));
        res_total_amount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_total_amount.setBorder(null);
        res_total_amount.setEnabled(false);
        res_total_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_total_amountKeyPressed(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel77.setText("Total Amount");

        jSeparator47.setBackground(new java.awt.Color(0, 0, 0));

        res_guest_email.setBackground(new java.awt.Color(242, 248, 249));
        res_guest_email.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_guest_email.setBorder(null);
        res_guest_email.setEnabled(false);
        res_guest_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_guest_emailKeyPressed(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel78.setText("Guest Email");

        jButton32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/estimate_amount.png"))); // NOI18N
        jButton32.setText("Calculate the sub total");
        jButton32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton32MouseClicked(evt);
            }
        });

        jButton33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/bill_print.png"))); // NOI18N
        jButton33.setText("Print Invoice");
        jButton33.setIconTextGap(10);
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        bill_display.setColumns(20);
        bill_display.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        bill_display.setRows(5);
        bill_display.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 2, 1, new java.awt.Color(1, 0, 1)));
        jScrollPane2.setViewportView(bill_display);

        reservation_bill_warning.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reservation_bill_warning.setForeground(new java.awt.Color(255, 0, 0));
        reservation_bill_warning.setBorder(null);

        jSeparator49.setBackground(new java.awt.Color(0, 0, 0));

        res_Balance_resAmount.setBackground(new java.awt.Color(242, 248, 249));
        res_Balance_resAmount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_Balance_resAmount.setBorder(null);
        res_Balance_resAmount.setEnabled(false);
        res_Balance_resAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_Balance_resAmountKeyPressed(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel80.setText("Payable Amount");

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/percentage_20.png"))); // NOI18N

        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/percentage_20.png"))); // NOI18N

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/Cash_inHand.png"))); // NOI18N

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel84.setText("Cash on Hand");

        jSeparator50.setBackground(new java.awt.Color(0, 0, 0));

        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/Cash_inHand.png"))); // NOI18N
        jLabel85.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel85MouseClicked(evt);
            }
        });

        res_Cash_on_hand.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_Cash_on_hand.setBorder(null);
        res_Cash_on_hand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_Cash_on_handKeyPressed(evt);
            }
        });

        jButton34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/gmail.png"))); // NOI18N
        jButton34.setText("Send Invoice");
        jButton34.setIconTextGap(10);
        jButton34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton34MouseClicked(evt);
            }
        });

        jSeparator51.setBackground(new java.awt.Color(0, 0, 0));

        res_bill_id.setBackground(new java.awt.Color(242, 248, 249));
        res_bill_id.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_bill_id.setBorder(null);
        res_bill_id.setEnabled(false);
        res_bill_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_bill_idKeyPressed(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel86.setText("Bill No :");

        jButton35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/Adelete.png"))); // NOI18N
        jButton35.setText("Cancel ");
        jButton35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton35MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Reservation_billLayout = new javax.swing.GroupLayout(Reservation_bill);
        Reservation_bill.setLayout(Reservation_billLayout);
        Reservation_billLayout.setHorizontalGroup(
            Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Reservation_billLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Reservation_billLayout.createSequentialGroup()
                            .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel75)
                                .addComponent(jLabel74)
                                .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton35, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(35, 35, 35)
                            .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator45)
                                .addGroup(Reservation_billLayout.createSequentialGroup()
                                    .addComponent(res_advance_amount)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator43)
                                .addGroup(Reservation_billLayout.createSequentialGroup()
                                    .addComponent(res_discount)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator44, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Reservation_billLayout.createSequentialGroup()
                                    .addComponent(res_taxes)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reservation_bill_warning)
                            .addGroup(Reservation_billLayout.createSequentialGroup()
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel72))
                                .addGap(73, 73, 73)
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSeparator42, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(res_guest_address, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator41, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(res_guest_name, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator37, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(res_guest_id, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Reservation_billLayout.createSequentialGroup()
                                .addComponent(jLabel69)
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Reservation_billLayout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(jSeparator38))
                                    .addGroup(Reservation_billLayout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(res_reservation_id))))
                            .addComponent(jLabel73)
                            .addGroup(Reservation_billLayout.createSequentialGroup()
                                .addComponent(jLabel78)
                                .addGap(76, 76, 76)
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator47)
                                    .addComponent(res_guest_email, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Reservation_billLayout.createSequentialGroup()
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel71)
                                    .addComponent(jLabel70))
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Reservation_billLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(res_staying_days))
                                    .addGroup(Reservation_billLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(res_basic_amount))
                                    .addGroup(Reservation_billLayout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(jSeparator40))))
                            .addComponent(jLabel80)
                            .addGroup(Reservation_billLayout.createSequentialGroup()
                                .addComponent(jLabel77)
                                .addGap(61, 61, 61)
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator49)
                                    .addComponent(res_total_amount, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(res_Balance_resAmount, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jSeparator46, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator39, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(141, 141, 141)
                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Reservation_billLayout.createSequentialGroup()
                                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(Reservation_billLayout.createSequentialGroup()
                            .addComponent(jLabel86)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(res_bill_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(21, 21, 21)))
                    .addGroup(Reservation_billLayout.createSequentialGroup()
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(Reservation_billLayout.createSequentialGroup()
                                .addComponent(res_Cash_on_hand, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator50, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        Reservation_billLayout.setVerticalGroup(
            Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Reservation_billLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(Reservation_billLayout.createSequentialGroup()
                        .addComponent(res_bill_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(reservation_bill_warning, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86))
                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Reservation_billLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Reservation_billLayout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(res_guest_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(res_reservation_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(res_staying_days, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Reservation_billLayout.createSequentialGroup()
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(Reservation_billLayout.createSequentialGroup()
                                        .addComponent(res_guest_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jSeparator37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(Reservation_billLayout.createSequentialGroup()
                                        .addComponent(res_guest_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jSeparator41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel72))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Reservation_billLayout.createSequentialGroup()
                                        .addComponent(res_guest_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jSeparator42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator39, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(Reservation_billLayout.createSequentialGroup()
                                .addComponent(res_basic_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(res_total_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator46, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(res_Balance_resAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Reservation_billLayout.createSequentialGroup()
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(res_taxes, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Reservation_billLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(res_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Reservation_billLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(res_advance_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator45, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Reservation_billLayout.createSequentialGroup()
                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton32)
                            .addComponent(jButton35)))
                    .addGroup(Reservation_billLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(res_Cash_on_hand, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Reservation_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        bill_base.add(Reservation_bill, "card2");

        bill_statistic.setBackground(new java.awt.Color(255, 255, 255));
        bill_statistic.setMaximumSize(new java.awt.Dimension(1106, 554));
        bill_statistic.setMinimumSize(new java.awt.Dimension(1106, 554));

        javax.swing.GroupLayout bill_statisticLayout = new javax.swing.GroupLayout(bill_statistic);
        bill_statistic.setLayout(bill_statisticLayout);
        bill_statisticLayout.setHorizontalGroup(
            bill_statisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1106, Short.MAX_VALUE)
        );
        bill_statisticLayout.setVerticalGroup(
            bill_statisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 557, Short.MAX_VALUE)
        );

        bill_base.add(bill_statistic, "card5");

        check_out_bill.setBackground(new java.awt.Color(255, 255, 255));
        check_out_bill.setMaximumSize(new java.awt.Dimension(1106, 554));
        check_out_bill.setMinimumSize(new java.awt.Dimension(1106, 554));

        check_out_guest_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        check_out_guest_id.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guest id" }));

        check_out_reservatiion_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        check_out_reservatiion_id.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reservatioin id", " " }));

        jSeparator71.setBackground(new java.awt.Color(0, 0, 0));

        checkout_guest_idin.setBackground(new java.awt.Color(242, 248, 249));
        checkout_guest_idin.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        checkout_guest_idin.setBorder(null);
        checkout_guest_idin.setEnabled(false);
        checkout_guest_idin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkout_guest_idinKeyPressed(evt);
            }
        });

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel105.setText("Guest ID");

        jSeparator72.setBackground(new java.awt.Color(0, 0, 0));

        checkout_reservation_idin.setBackground(new java.awt.Color(242, 248, 249));
        checkout_reservation_idin.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        checkout_reservation_idin.setBorder(null);
        checkout_reservation_idin.setEnabled(false);
        checkout_reservation_idin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkout_reservation_idinKeyPressed(evt);
            }
        });

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel106.setText("Reservation ID");

        jPanelFinalBillingpart.setBackground(new java.awt.Color(241, 252, 248));
        jPanelFinalBillingpart.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jSeparator73.setBackground(new java.awt.Color(0, 0, 0));

        checkout_guest_name.setBackground(new java.awt.Color(242, 248, 249));
        checkout_guest_name.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        checkout_guest_name.setBorder(null);
        checkout_guest_name.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        checkout_guest_name.setEnabled(false);
        checkout_guest_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkout_guest_nameKeyPressed(evt);
            }
        });

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel107.setText("Guest Name");

        jSeparator76.setBackground(new java.awt.Color(0, 0, 0));

        checkout_guest_email.setBackground(new java.awt.Color(242, 248, 249));
        checkout_guest_email.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        checkout_guest_email.setBorder(null);
        checkout_guest_email.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        checkout_guest_email.setEnabled(false);
        checkout_guest_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkout_guest_emailKeyPressed(evt);
            }
        });

        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel111.setText("Guest Email");

        jLabel112.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel112.setText("Res Amount");

        jSeparator77.setBackground(new java.awt.Color(0, 0, 0));

        checkout_res_amount.setBackground(new java.awt.Color(242, 248, 249));
        checkout_res_amount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        checkout_res_amount.setBorder(null);
        checkout_res_amount.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        checkout_res_amount.setEnabled(false);
        checkout_res_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkout_res_amountKeyPressed(evt);
            }
        });

        jSeparator78.setBackground(new java.awt.Color(0, 0, 0));

        checkout_order_amount.setBackground(new java.awt.Color(242, 248, 249));
        checkout_order_amount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        checkout_order_amount.setBorder(null);
        checkout_order_amount.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        checkout_order_amount.setEnabled(false);
        checkout_order_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkout_order_amountKeyPressed(evt);
            }
        });

        jLabel113.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel113.setText("Order Amount");

        jSeparator79.setBackground(new java.awt.Color(0, 0, 0));

        checkout_total_amount.setBackground(new java.awt.Color(242, 248, 249));
        checkout_total_amount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        checkout_total_amount.setBorder(null);
        checkout_total_amount.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        checkout_total_amount.setEnabled(false);
        checkout_total_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkout_total_amountKeyPressed(evt);
            }
        });

        jLabel114.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel114.setText("Total Amount");

        jSeparator80.setBackground(new java.awt.Color(0, 0, 0));

        checkout_total_amount1.setBackground(new java.awt.Color(242, 248, 249));
        checkout_total_amount1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        checkout_total_amount1.setBorder(null);
        checkout_total_amount1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        checkout_total_amount1.setEnabled(false);
        checkout_total_amount1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkout_total_amount1KeyPressed(evt);
            }
        });

        jLabel115.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel115.setText("Balance Amount");

        jSeparator74.setBackground(new java.awt.Color(0, 0, 0));

        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/Cash_inHand.png"))); // NOI18N
        jLabel109.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel109MouseClicked(evt);
            }
        });

        res_Cash_on_hand1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_Cash_on_hand1.setBorder(null);
        res_Cash_on_hand1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_Cash_on_hand1KeyPressed(evt);
            }
        });

        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel108.setText("Cash on Hand");

        jButton51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton51.setText("Balance");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jButton52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton52.setText("Set Bill");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFinalBillingpartLayout = new javax.swing.GroupLayout(jPanelFinalBillingpart);
        jPanelFinalBillingpart.setLayout(jPanelFinalBillingpartLayout);
        jPanelFinalBillingpartLayout.setHorizontalGroup(
            jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFinalBillingpartLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelFinalBillingpartLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator74, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelFinalBillingpartLayout.createSequentialGroup()
                        .addComponent(jLabel114)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator79, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkout_total_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelFinalBillingpartLayout.createSequentialGroup()
                        .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel107)
                            .addComponent(jLabel111))
                        .addGap(36, 36, 36)
                        .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator76, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkout_guest_email, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator73, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkout_guest_name, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelFinalBillingpartLayout.createSequentialGroup()
                            .addComponent(jLabel113)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator78, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkout_order_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelFinalBillingpartLayout.createSequentialGroup()
                            .addComponent(jLabel112)
                            .addGap(36, 36, 36)
                            .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator77, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkout_res_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelFinalBillingpartLayout.createSequentialGroup()
                        .addComponent(jLabel115)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator80, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkout_total_amount1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(res_Cash_on_hand1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelFinalBillingpartLayout.createSequentialGroup()
                        .addComponent(jLabel108)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel109, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFinalBillingpartLayout.createSequentialGroup()
                                .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanelFinalBillingpartLayout.setVerticalGroup(
            jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFinalBillingpartLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkout_guest_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkout_guest_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkout_res_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkout_order_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkout_total_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkout_total_amount1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(res_Cash_on_hand1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFinalBillingpartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton51, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jButton52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/bill_print.png"))); // NOI18N
        jButton49.setText("Print Invoice");
        jButton49.setIconTextGap(10);
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jButton50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/gmail.png"))); // NOI18N
        jButton50.setText("Send Invoice");
        jButton50.setIconTextGap(10);
        jButton50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton50MouseClicked(evt);
            }
        });

        bill_display2.setColumns(20);
        bill_display2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        bill_display2.setRows(5);
        bill_display2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 2, 1, new java.awt.Color(1, 0, 1)));
        jScrollPane14.setViewportView(bill_display2);

        jSeparator75.setBackground(new java.awt.Color(0, 0, 0));

        checkout_bill_id.setBackground(new java.awt.Color(242, 248, 249));
        checkout_bill_id.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        checkout_bill_id.setBorder(null);
        checkout_bill_id.setEnabled(false);
        checkout_bill_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkout_bill_idKeyPressed(evt);
            }
        });

        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel110.setText("Bill No :");

        jButton53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/checked.png"))); // NOI18N
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        final_billError.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        final_billError.setForeground(new java.awt.Color(255, 51, 51));
        final_billError.setBorder(null);

        javax.swing.GroupLayout check_out_billLayout = new javax.swing.GroupLayout(check_out_bill);
        check_out_bill.setLayout(check_out_billLayout);
        check_out_billLayout.setHorizontalGroup(
            check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(check_out_billLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(check_out_billLayout.createSequentialGroup()
                        .addComponent(check_out_guest_id, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(check_out_reservatiion_id, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(check_out_billLayout.createSequentialGroup()
                        .addGroup(check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel105)
                            .addComponent(jLabel106))
                        .addGap(36, 36, 36)
                        .addGroup(check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator72, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkout_reservation_idin, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator71, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkout_guest_idin, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanelFinalBillingpart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(final_billError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator75, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(check_out_billLayout.createSequentialGroup()
                            .addComponent(jLabel110)
                            .addGap(33, 33, 33)
                            .addComponent(checkout_bill_id, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(check_out_billLayout.createSequentialGroup()
                            .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        check_out_billLayout.setVerticalGroup(
            check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, check_out_billLayout.createSequentialGroup()
                .addGroup(check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(check_out_billLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkout_bill_id, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(check_out_billLayout.createSequentialGroup()
                                .addComponent(jLabel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(5, 5, 5)))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(check_out_billLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(check_out_guest_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(check_out_reservatiion_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(final_billError, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkout_guest_idin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(check_out_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkout_reservation_idin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton53))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelFinalBillingpart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(29, 29, 29))
        );

        bill_base.add(check_out_bill, "card4");

        javax.swing.GroupLayout Billing_And_ReportLayout = new javax.swing.GroupLayout(Billing_And_Report);
        Billing_And_Report.setLayout(Billing_And_ReportLayout);
        Billing_And_ReportLayout.setHorizontalGroup(
            Billing_And_ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Billing_And_ReportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Billing_And_ReportLayout.createSequentialGroup()
                .addGroup(Billing_And_ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator36)
                    .addComponent(bill_base, javax.swing.GroupLayout.PREFERRED_SIZE, 1007, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 41, Short.MAX_VALUE))
        );
        Billing_And_ReportLayout.setVerticalGroup(
            Billing_And_ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Billing_And_ReportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Billing_And_ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator36, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bill_base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Dynamic_Panel.add(Billing_And_Report, "card2");

        Frontdest.setBackground(new java.awt.Color(255, 255, 255));

        guestRegistrationBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        guestRegistrationBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/adduser.png"))); // NOI18N
        guestRegistrationBTN.setText("Guest Registration");
        guestRegistrationBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guestRegistrationBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guestRegistrationBTNMouseClicked(evt);
            }
        });
        guestRegistrationBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestRegistrationBTNActionPerformed(evt);
            }
        });

        reservationBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reservationBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/reservation_1.png"))); // NOI18N
        reservationBTN.setText("Reservation");
        reservationBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reservationBTN.setIconTextGap(30);
        reservationBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reservationBTNMouseClicked(evt);
            }
        });

        reservationUpdateBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reservationUpdateBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/updateRes.png"))); // NOI18N
        reservationUpdateBTN.setText("Reservation Update");
        reservationUpdateBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reservationUpdateBTN.setIconTextGap(30);
        reservationUpdateBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reservationUpdateBTNMouseClicked(evt);
            }
        });

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));

        frontDeskBase.setBackground(new java.awt.Color(255, 255, 255));
        frontDeskBase.setLayout(new java.awt.CardLayout());

        GuestRegistartion.setBackground(new java.awt.Color(255, 255, 255));
        GuestRegistartion.setMaximumSize(new java.awt.Dimension(994, 554));

        guest_email.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        guest_email.setBorder(null);
        guest_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guest_emailActionPerformed(evt);
            }
        });
        guest_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guest_emailKeyPressed(evt);
            }
        });

        Address3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Address3.setText("Address ");

        jSeparator17.setBackground(new java.awt.Color(0, 0, 0));

        guest_purpose_visit.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        guest_purpose_visit.setBorder(null);
        guest_purpose_visit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guest_purpose_visitKeyPressed(evt);
            }
        });

        jSeparator18.setBackground(new java.awt.Color(0, 0, 0));

        guest_address.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        guest_address.setBorder(null);
        guest_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guest_addressActionPerformed(evt);
            }
        });
        guest_address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guest_addressKeyPressed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/addguest.png"))); // NOI18N
        jButton8.setText("Add Guest");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        guest_in_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        guest_in_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/addimg.png"))); // NOI18N
        guest_in_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        guest_in_image.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guest_in_image.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guest_in_image.setPreferredSize(null);
        guest_in_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guest_in_imageMouseClicked(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setText("Guest ID");

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/updatemen.png"))); // NOI18N
        jButton9.setText("Update");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/icons8_erase_20px.png"))); // NOI18N
        jButton10.setText("Clear");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setText("Guest Name       ");

        guest_warning_text.setBackground(new java.awt.Color(255, 255, 255));
        guest_warning_text.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        guest_warning_text.setForeground(new java.awt.Color(255, 0, 0));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel47.setText("Identitity  ");

        Address4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Address4.setText("Purpose of visit");

        guest_id.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        guest_id.setBorder(null);
        guest_id.setEnabled(false);
        guest_id.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                guest_idMouseWheelMoved(evt);
            }
        });
        guest_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guest_idActionPerformed(evt);
            }
        });
        guest_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guest_idKeyPressed(evt);
            }
        });

        jSeparator19.setBackground(new java.awt.Color(0, 0, 0));

        jSeparator20.setBackground(new java.awt.Color(0, 0, 0));

        guest_fullname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        guest_fullname.setBorder(null);
        guest_fullname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guest_fullnameKeyPressed(evt);
            }
        });

        jSeparator21.setBackground(new java.awt.Color(0, 0, 0));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel44.setText("Telephone");

        guest_identitity.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        guest_identitity.setBorder(null);
        guest_identitity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guest_identitityKeyPressed(evt);
            }
        });

        jSeparator22.setBackground(new java.awt.Color(0, 0, 0));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText("Email");

        jSeparator23.setBackground(new java.awt.Color(0, 0, 0));

        guest_tel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        guest_tel.setBorder(null);
        guest_tel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guest_telKeyPressed(evt);
            }
        });

        Camera_panel1.setMaximumSize(new java.awt.Dimension(364, 490));

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/camera.png"))); // NOI18N
        jButton11.setText("Open Camera");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/capture.png"))); // NOI18N
        jButton12.setText("Tack Capture");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/camOff.png"))); // NOI18N
        jButton14.setText("Exit Camera  ");
        jButton14.setMaximumSize(new java.awt.Dimension(149, 29));
        jButton14.setMinimumSize(new java.awt.Dimension(149, 29));
        jButton14.setPreferredSize(new java.awt.Dimension(149, 29));
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Camera_panel1Layout = new javax.swing.GroupLayout(Camera_panel1);
        Camera_panel1.setLayout(Camera_panel1Layout);
        Camera_panel1Layout.setHorizontalGroup(
            Camera_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Camera_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Camera_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        Camera_panel1Layout.setVerticalGroup(
            Camera_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Camera_panel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jCheckBoxCamereCheck.setForeground(new java.awt.Color(0, 255, 0));
        jCheckBoxCamereCheck.setEnabled(false);
        jCheckBoxCamereCheck.setOpaque(false);

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/find guest.png"))); // NOI18N
        jButton13.setText("Find Guest");
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jTableGuest.setBackground(new java.awt.Color(231, 240, 240));
        jTableGuest.setBorder(new javax.swing.border.MatteBorder(null));
        jTableGuest.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTableGuest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "PHONE"
            }
        ));
        jTableGuest.setEditingColumn(1);
        jTableGuest.setEditingRow(1);
        jTableGuest.setFillsViewportHeight(true);
        jTableGuest.setGridColor(new java.awt.Color(51, 51, 51));
        jTableGuest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableGuestMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableGuestMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTableGuest);

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Guest Information Table");
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel26.setOpaque(true);

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/Search guest.png"))); // NOI18N
        jLabel46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel46MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout GuestRegistartionLayout = new javax.swing.GroupLayout(GuestRegistartion);
        GuestRegistartion.setLayout(GuestRegistartionLayout);
        GuestRegistartionLayout.setHorizontalGroup(
            GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GuestRegistartionLayout.createSequentialGroup()
                .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GuestRegistartionLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBoxCamereCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Address4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Address3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GuestRegistartionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(GuestRegistartionLayout.createSequentialGroup()
                            .addComponent(guest_id, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel46))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GuestRegistartionLayout.createSequentialGroup()
                            .addComponent(jButton8)
                            .addGap(10, 10, 10)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(22, 22, 22)))
                    .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator23, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(guest_warning_text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GuestRegistartionLayout.createSequentialGroup()
                            .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(GuestRegistartionLayout.createSequentialGroup()
                                    .addGap(87, 87, 87)
                                    .addComponent(guest_in_image, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Camera_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(guest_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(guest_identitity, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(guest_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(guest_email, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(guest_purpose_visit, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(guest_address, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(8, 8, 8)))
                    .addComponent(jSeparator22, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GuestRegistartionLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(165, 165, 165))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GuestRegistartionLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))))
        );
        GuestRegistartionLayout.setVerticalGroup(
            GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GuestRegistartionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GuestRegistartionLayout.createSequentialGroup()
                        .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(guest_in_image, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Camera_panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jCheckBoxCamereCheck))
                        .addGap(15, 15, 15)
                        .addComponent(guest_warning_text, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(guest_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel42))
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guest_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guest_identitity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guest_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guest_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guest_purpose_visit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Address4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guest_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Address3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(GuestRegistartionLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        frontDeskBase.add(GuestRegistartion, "card2");

        reservation.setBackground(new java.awt.Color(255, 255, 255));
        reservation.setMaximumSize(new java.awt.Dimension(1004, 548));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("Guest ID");

        GuestIdTable_reservation.setAutoCreateRowSorter(true);
        GuestIdTable_reservation.setBackground(new java.awt.Color(231, 240, 240));
        GuestIdTable_reservation.setBorder(new javax.swing.border.MatteBorder(null));
        GuestIdTable_reservation.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        GuestIdTable_reservation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAme"
            }
        ));
        GuestIdTable_reservation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GuestIdTable_reservation.setEditingColumn(1);
        GuestIdTable_reservation.setEditingRow(1);
        GuestIdTable_reservation.setFillsViewportHeight(true);
        GuestIdTable_reservation.setGridColor(new java.awt.Color(51, 51, 51));
        GuestIdTable_reservation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GuestIdTable_reservationMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GuestIdTable_reservationMouseEntered(evt);
            }
        });
        jScrollPane7.setViewportView(GuestIdTable_reservation);

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Check In Date ");

        Reservation_Guest_Id_in.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Reservation_Guest_Id_in.setBorder(null);
        Reservation_Guest_Id_in.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Reservation_Guest_Id_in.setEnabled(false);

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel49.setText("Check Out Date");

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/confirmpackage.png"))); // NOI18N
        jButton15.setText("Confiriming Reservation ");
        jButton15.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton15.setIconTextGap(1);
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
        });

        ReservationRoomAvble.setBackground(new java.awt.Color(231, 240, 240));
        ReservationRoomAvble.setBorder(new javax.swing.border.MatteBorder(null));
        ReservationRoomAvble.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ReservationRoomAvble.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TYPE", "BedType", "PRICE"
            }
        ));
        ReservationRoomAvble.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ReservationRoomAvble.setFillsViewportHeight(true);
        ReservationRoomAvble.setGridColor(new java.awt.Color(51, 51, 51));
        ReservationRoomAvble.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReservationRoomAvbleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReservationRoomAvbleMouseEntered(evt);
            }
        });
        jScrollPane9.setViewportView(ReservationRoomAvble);

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel65.setText("AVbl Room");

        Reservation_Room_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Reservation_Room_id.setBorder(null);
        Reservation_Room_id.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Reservation_Room_id.setEnabled(false);

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel66.setText("Amount per day");

        Reservation_Price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Reservation_Price.setBorder(null);
        Reservation_Price.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Reservation_Price.setEnabled(false);

        ReservationWarning.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ReservationWarning.setForeground(new java.awt.Color(255, 0, 0));

        resrevation_staying_days.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        resrevation_staying_days.setBorder(null);
        resrevation_staying_days.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        resrevation_staying_days.setEnabled(false);

        reserCountBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reserCountBtn.setText("Staying Days");
        reserCountBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reserCountBtnMouseClicked(evt);
            }
        });

        DateIn.setEnabled(false);

        jButton28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/icons8_erase_20px.png"))); // NOI18N
        jButton28.setText("Cancel ");
        jButton28.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton28.setIconTextGap(20);
        jButton28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton28MouseClicked(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel67.setText("Guest");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel68.setText("Available Rooms");

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel79.setText("Reservation ID");

        Reservation_Id_in.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Reservation_Id_in.setBorder(null);
        Reservation_Id_in.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Reservation_Id_in.setEnabled(false);

        javax.swing.GroupLayout reservationLayout = new javax.swing.GroupLayout(reservation);
        reservation.setLayout(reservationLayout);
        reservationLayout.setHorizontalGroup(
            reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reservationLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ReservationWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(reservationLayout.createSequentialGroup()
                            .addComponent(jLabel79)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator48, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                .addComponent(Reservation_Id_in)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, reservationLayout.createSequentialGroup()
                            .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(reserCountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(16, 16, 16)
                            .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(DateIn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator35)
                                .addComponent(resrevation_staying_days, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator34, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(DateOut, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                .addComponent(Reservation_Price))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, reservationLayout.createSequentialGroup()
                            .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator33, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                .addComponent(Reservation_Guest_Id_in)
                                .addComponent(jSeparator24)
                                .addComponent(Reservation_Room_id, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addGroup(reservationLayout.createSequentialGroup()
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton28)))
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reservationLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(249, 249, 249)
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        reservationLayout.setVerticalGroup(
            reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ReservationWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reservation_Id_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reservationLayout.createSequentialGroup()
                        .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(reservationLayout.createSequentialGroup()
                                .addComponent(Reservation_Guest_Id_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Reservation_Room_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(reservationLayout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Reservation_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DateIn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DateOut, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reserCountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resrevation_staying_days, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                    .addComponent(jScrollPane9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        frontDeskBase.add(reservation, "card2");

        Guest_Statictics.setBackground(new java.awt.Color(255, 255, 255));
        Guest_Statictics.setMaximumSize(new java.awt.Dimension(1004, 548));
        Guest_Statictics.setMinimumSize(new java.awt.Dimension(1004, 548));

        jButton17.setText("jButton17");

        ViewBookedRooms.setBackground(new java.awt.Color(231, 240, 240));
        ViewBookedRooms.setBorder(new javax.swing.border.MatteBorder(null));
        ViewBookedRooms.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ViewBookedRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TYPE", "Name", "PRICE", "Description"
            }
        ));
        ViewBookedRooms.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ViewBookedRooms.setFillsViewportHeight(true);
        ViewBookedRooms.setGridColor(new java.awt.Color(51, 51, 51));
        ViewBookedRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewBookedRoomsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ViewBookedRoomsMouseEntered(evt);
            }
        });
        jScrollPane8.setViewportView(ViewBookedRooms);

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setText("Booked Rooms Details");

        javax.swing.GroupLayout Guest_StaticticsLayout = new javax.swing.GroupLayout(Guest_Statictics);
        Guest_Statictics.setLayout(Guest_StaticticsLayout);
        Guest_StaticticsLayout.setHorizontalGroup(
            Guest_StaticticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Guest_StaticticsLayout.createSequentialGroup()
                .addGroup(Guest_StaticticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Guest_StaticticsLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Guest_StaticticsLayout.createSequentialGroup()
                        .addGap(403, 403, 403)
                        .addComponent(jLabel50)))
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Guest_StaticticsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton17)
                .addGap(115, 115, 115))
        );
        Guest_StaticticsLayout.setVerticalGroup(
            Guest_StaticticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Guest_StaticticsLayout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jButton17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        frontDeskBase.add(Guest_Statictics, "card2");

        todayCheckOuts.setBackground(new java.awt.Color(255, 255, 255));
        todayCheckOuts.setMaximumSize(new java.awt.Dimension(1004, 548));
        todayCheckOuts.setMinimumSize(new java.awt.Dimension(1004, 548));

        jButton44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/remainder.png"))); // NOI18N
        jButton44.setText("Send Remember");
        jButton44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton44MouseClicked(evt);
            }
        });

        ViewCheckouts.setBackground(new java.awt.Color(231, 240, 240));
        ViewCheckouts.setBorder(new javax.swing.border.MatteBorder(null));
        ViewCheckouts.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ViewCheckouts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "reservation_id", "room_id", "check_in", "guest_id", "amount"
            }
        ));
        ViewCheckouts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ViewCheckouts.setFillsViewportHeight(true);
        ViewCheckouts.setGridColor(new java.awt.Color(51, 51, 51));
        ViewCheckouts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewCheckoutsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ViewCheckoutsMouseEntered(evt);
            }
        });
        jScrollPane13.setViewportView(ViewCheckouts);

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel101.setText("Today Check out List");

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel102.setText("Guest Name       ");

        jSeparator67.setBackground(new java.awt.Color(0, 0, 0));

        guest_fullname1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        guest_fullname1.setBorder(null);
        guest_fullname1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guest_fullname1KeyPressed(evt);
            }
        });

        jSeparator68.setBackground(new java.awt.Color(0, 0, 0));

        guest_id_chout.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        guest_id_chout.setBorder(null);
        guest_id_chout.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guest_id_choutKeyPressed(evt);
            }
        });

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel103.setText("Guest ID");

        jButton45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/check out.png"))); // NOI18N
        jButton45.setText("Check Out");
        jButton45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton45MouseClicked(evt);
            }
        });

        jSeparator69.setBackground(new java.awt.Color(0, 0, 0));

        guest_email_chout.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        guest_email_chout.setBorder(null);
        guest_email_chout.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guest_email_choutKeyPressed(evt);
            }
        });

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel104.setText("Email");

        today_ch_warning.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        today_ch_warning.setForeground(new java.awt.Color(255, 51, 51));
        today_ch_warning.setBorder(null);

        jLabel116.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel116.setText("Reservation ID");

        jSeparator81.setBackground(new java.awt.Color(0, 0, 0));

        res_id_chout.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        res_id_chout.setBorder(null);
        res_id_chout.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                res_id_choutKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout todayCheckOutsLayout = new javax.swing.GroupLayout(todayCheckOuts);
        todayCheckOuts.setLayout(todayCheckOutsLayout);
        todayCheckOutsLayout.setHorizontalGroup(
            todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(todayCheckOutsLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(todayCheckOutsLayout.createSequentialGroup()
                        .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(todayCheckOutsLayout.createSequentialGroup()
                        .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel103, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel102, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator67, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                .addComponent(guest_fullname1))
                            .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator68)
                                .addComponent(guest_id_chout, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator69)
                                .addComponent(guest_email_chout, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(todayCheckOutsLayout.createSequentialGroup()
                        .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator81)
                            .addComponent(res_id_chout, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40))
            .addGroup(todayCheckOutsLayout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(jLabel101)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(today_ch_warning, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        todayCheckOutsLayout.setVerticalGroup(
            todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(todayCheckOutsLayout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, todayCheckOutsLayout.createSequentialGroup()
                        .addComponent(jLabel101)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, todayCheckOutsLayout.createSequentialGroup()
                        .addComponent(today_ch_warning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(todayCheckOutsLayout.createSequentialGroup()
                        .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guest_fullname1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator67, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guest_id_chout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator68, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(todayCheckOutsLayout.createSequentialGroup()
                                .addComponent(res_id_chout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator81, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guest_email_chout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator69, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(todayCheckOutsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        frontDeskBase.add(todayCheckOuts, "card2");

        todayReservationBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        todayReservationBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/check out.png"))); // NOI18N
        todayReservationBTN.setText("Today Checkout");
        todayReservationBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        todayReservationBTN.setIconTextGap(30);
        todayReservationBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                todayReservationBTNMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout FrontdestLayout = new javax.swing.GroupLayout(Frontdest);
        Frontdest.setLayout(FrontdestLayout);
        FrontdestLayout.setHorizontalGroup(
            FrontdestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrontdestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guestRegistrationBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reservationBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reservationUpdateBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(todayReservationBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator6)
            .addComponent(frontDeskBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FrontdestLayout.setVerticalGroup(
            FrontdestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrontdestLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FrontdestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guestRegistrationBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reservationBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reservationUpdateBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todayReservationBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frontDeskBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Dynamic_Panel.add(Frontdest, "card2");

        Catering_and_food.setBackground(new java.awt.Color(255, 255, 255));

        FoodManagementBTN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        FoodManagementBTN.setText("Food Management");
        FoodManagementBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FoodManagementBTNMouseClicked(evt);
            }
        });

        FoodPackageBTN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        FoodPackageBTN.setText("Packages");
        FoodPackageBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FoodPackageBTNMouseClicked(evt);
            }
        });
        FoodPackageBTN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FoodPackageBTNKeyPressed(evt);
            }
        });

        FoodOderBTN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        FoodOderBTN.setText("Food Order");
        FoodOderBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FoodOderBTNMouseClicked(evt);
            }
        });

        Catering_Base.setBackground(new java.awt.Color(255, 255, 255));
        Catering_Base.setMaximumSize(new java.awt.Dimension(1004, 657));
        Catering_Base.setMinimumSize(new java.awt.Dimension(1004, 657));
        Catering_Base.setPreferredSize(new java.awt.Dimension(1004, 657));
        Catering_Base.setLayout(new java.awt.CardLayout());

        foodPackage.setBackground(new java.awt.Color(255, 255, 255));

        jComboBoxfoodmenu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxfoodmenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select The Food Menu", "Menu - 01", "Menu - 02", "Menu - 03", "Menu - 04" }));
        jComboBoxfoodmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxfoodmenuMouseClicked(evt);
            }
        });

        jComboBoxPackage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxPackage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select The Package", "Menu - 01", "Menu - 02", "Menu - 03", "Menu - 04" }));
        jComboBoxPackage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxPackageMouseClicked(evt);
            }
        });

        GuestIdTable.setAutoCreateRowSorter(true);
        GuestIdTable.setBackground(new java.awt.Color(231, 240, 240));
        GuestIdTable.setBorder(new javax.swing.border.MatteBorder(null));
        GuestIdTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        GuestIdTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAme"
            }
        ));
        GuestIdTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GuestIdTable.setEditingColumn(1);
        GuestIdTable.setEditingRow(1);
        GuestIdTable.setFillsViewportHeight(true);
        GuestIdTable.setGridColor(new java.awt.Color(51, 51, 51));
        GuestIdTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GuestIdTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GuestIdTableMouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(GuestIdTable);

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/confirmpackage.png"))); // NOI18N
        jButton18.setText("Confirm Package");
        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton18MouseClicked(evt);
            }
        });
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout foodPackageLayout = new javax.swing.GroupLayout(foodPackage);
        foodPackage.setLayout(foodPackageLayout);
        foodPackageLayout.setHorizontalGroup(
            foodPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(foodPackageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(foodPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(foodPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBoxfoodmenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxPackage, 0, 270, Short.MAX_VALUE)
                        .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(Foodmenulableout, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        foodPackageLayout.setVerticalGroup(
            foodPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, foodPackageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(foodPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Foodmenulableout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(foodPackageLayout.createSequentialGroup()
                        .addComponent(jComboBoxfoodmenu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxPackage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        Catering_Base.add(foodPackage, "card2");

        orderFood.setBackground(new java.awt.Color(255, 255, 255));

        ViewFooditem_oder.setBackground(new java.awt.Color(231, 240, 240));
        ViewFooditem_oder.setBorder(new javax.swing.border.MatteBorder(null));
        ViewFooditem_oder.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ViewFooditem_oder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "PRICE"
            }
        ));
        ViewFooditem_oder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ViewFooditem_oder.setFillsViewportHeight(true);
        ViewFooditem_oder.setGridColor(new java.awt.Color(51, 51, 51));
        ViewFooditem_oder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewFooditem_oderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ViewFooditem_oderMouseEntered(evt);
            }
        });
        jScrollPane10.setViewportView(ViewFooditem_oder);

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1Food_order.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton1.setText("Breakfast");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1Food_order.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton2.setText("Lunch");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1Food_order.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton3.setText("Dinner");
        jRadioButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton3MouseClicked(evt);
            }
        });

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1Food_order.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton4.setText("Drinks & Bevarage");
        jRadioButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton4MouseClicked(evt);
            }
        });

        jRadioButton5.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1Food_order.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton5.setText("All foods");
        jRadioButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton5MouseClicked(evt);
            }
        });

        Foodorder_guest_id_combo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Foodorder_guest_id_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guest ID" }));

        Foodorder_reservation_id_com.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Foodorder_reservation_id_com.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reservation ID" }));

        food_order_id.setBackground(new java.awt.Color(238, 245, 249));
        food_order_id.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        food_order_id.setBorder(null);
        food_order_id.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        food_order_id.setEnabled(false);

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel87.setText("Order ID");

        jPanel1.setBackground(new java.awt.Color(246, 249, 252));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Food Card"));

        ViewFooditem_card.setBackground(new java.awt.Color(231, 240, 240));
        ViewFooditem_card.setBorder(new javax.swing.border.MatteBorder(null));
        ViewFooditem_card.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ViewFooditem_card.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Items", "Quantity", "unit price", "Total"
            }
        ));
        ViewFooditem_card.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ViewFooditem_card.setFillsViewportHeight(true);
        ViewFooditem_card.setGridColor(new java.awt.Color(51, 51, 51));
        ViewFooditem_card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewFooditem_cardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ViewFooditem_cardMouseEntered(evt);
            }
        });
        jScrollPane11.setViewportView(ViewFooditem_card);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );

        oder_food_name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        oder_food_name.setText("Food name");
        oder_food_name.setBorder(null);
        oder_food_name.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        oder_food_name.setEnabled(false);

        oder_food_unit_price.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        oder_food_unit_price.setText("Price");
        oder_food_unit_price.setBorder(null);
        oder_food_unit_price.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        oder_food_unit_price.setEnabled(false);

        oder_food_qty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        oder_food_qty.setText("Qty");
        oder_food_qty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                oder_food_qtyFocusGained(evt);
            }
        });
        oder_food_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                oder_food_qtyKeyPressed(evt);
            }
        });

        oder_food_total_price.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        oder_food_total_price.setText("SubTotal");
        oder_food_total_price.setBorder(null);
        oder_food_total_price.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        oder_food_total_price.setEnabled(false);

        jButton36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/add_food_card.png"))); // NOI18N
        jButton36.setText("Add to the Card");
        jButton36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton36MouseClicked(evt);
            }
        });

        oder_food_warning.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        oder_food_warning.setForeground(new java.awt.Color(255, 0, 0));
        oder_food_warning.setBorder(null);

        jPanelFood_order_confirm.setBackground(new java.awt.Color(246, 249, 252));
        jPanelFood_order_confirm.setBorder(javax.swing.BorderFactory.createTitledBorder("Oder Conformation"));

        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jToolBar1.setRollover(true);

        jButton37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/AUpdate.png"))); // NOI18N
        jButton37.setText("Update Selected Item");
        jButton37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton37.setFocusable(false);
        jButton37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton37.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton37MouseClicked(evt);
            }
        });
        jToolBar1.add(jButton37);
        jToolBar1.add(jSeparator56);

        jButton38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/Adelete.png"))); // NOI18N
        jButton38.setText("Remove Selected Item");
        jButton38.setFocusable(false);
        jButton38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton38.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton38MouseClicked(evt);
            }
        });
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton38);

        jButton40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/confirmpackage.png"))); // NOI18N
        jButton40.setText("Confirm Order");
        jButton40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton40MouseClicked(evt);
            }
        });
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/Paynow.png"))); // NOI18N
        jButton41.setText("Pay Now");
        jButton41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton41MouseClicked(evt);
            }
        });

        jButton42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/canceled.png"))); // NOI18N
        jButton42.setText("Cancel");
        jButton42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton42MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelFood_order_confirmLayout = new javax.swing.GroupLayout(jPanelFood_order_confirm);
        jPanelFood_order_confirm.setLayout(jPanelFood_order_confirmLayout);
        jPanelFood_order_confirmLayout.setHorizontalGroup(
            jPanelFood_order_confirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFood_order_confirmLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFood_order_confirmLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );
        jPanelFood_order_confirmLayout.setVerticalGroup(
            jPanelFood_order_confirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFood_order_confirmLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelFood_order_confirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 52, Short.MAX_VALUE))
        );

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel88.setText("Total Amount");

        food_order_FinalAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        food_order_FinalAmount.setBorder(null);
        food_order_FinalAmount.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        food_order_FinalAmount.setEnabled(false);

        javax.swing.GroupLayout orderFoodLayout = new javax.swing.GroupLayout(orderFood);
        orderFood.setLayout(orderFoodLayout);
        orderFoodLayout.setHorizontalGroup(
            orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderFoodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderFoodLayout.createSequentialGroup()
                        .addComponent(oder_food_warning, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderFoodLayout.createSequentialGroup()
                        .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(orderFoodLayout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Foodorder_guest_id_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(Foodorder_reservation_id_com, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator52)
                                    .addComponent(food_order_id, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(orderFoodLayout.createSequentialGroup()
                                .addGap(0, 9, Short.MAX_VALUE)
                                .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(orderFoodLayout.createSequentialGroup()
                                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jSeparator57)
                                            .addComponent(food_order_FinalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(orderFoodLayout.createSequentialGroup()
                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(orderFoodLayout.createSequentialGroup()
                                                .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(orderFoodLayout.createSequentialGroup()
                                                        .addComponent(jSeparator53, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jSeparator54, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(orderFoodLayout.createSequentialGroup()
                                                        .addComponent(oder_food_name, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(oder_food_unit_price, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(oder_food_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jSeparator55, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(orderFoodLayout.createSequentialGroup()
                                                        .addComponent(oder_food_total_price, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(10, 10, 10)
                                                        .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanelFood_order_confirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(20, 20, 20))))
        );
        orderFoodLayout.setVerticalGroup(
            orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderFoodLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderFoodLayout.createSequentialGroup()
                        .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton4)
                            .addComponent(jRadioButton5)
                            .addComponent(Foodorder_guest_id_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(oder_food_warning, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(orderFoodLayout.createSequentialGroup()
                        .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(food_order_id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel87)
                            .addComponent(Foodorder_reservation_id_com, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(food_order_FinalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel88))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderFoodLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(oder_food_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(oder_food_unit_price, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(oder_food_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton36)
                                .addComponent(oder_food_total_price, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addGroup(orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator53, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelFood_order_confirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Catering_Base.add(orderFood, "card2");

        Food_management.setBackground(new java.awt.Color(255, 255, 255));
        Food_management.setMaximumSize(new java.awt.Dimension(902, 546));
        Food_management.setMinimumSize(new java.awt.Dimension(902, 546));
        Food_management.setPreferredSize(new java.awt.Dimension(902, 546));

        addfoodbase.setBackground(new java.awt.Color(255, 255, 255));
        addfoodbase.setMaximumSize(new java.awt.Dimension(902, 546));
        addfoodbase.setMinimumSize(new java.awt.Dimension(902, 546));
        addfoodbase.setPreferredSize(new java.awt.Dimension(902, 546));
        addfoodbase.setLayout(new java.awt.CardLayout());

        viewfood.setBackground(new java.awt.Color(255, 255, 255));
        viewfood.setMaximumSize(new java.awt.Dimension(902, 546));
        viewfood.setMinimumSize(new java.awt.Dimension(902, 546));
        viewfood.setPreferredSize(new java.awt.Dimension(902, 546));

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel59.setText("Available Foods   ");

        ViewFoodTable.setBackground(new java.awt.Color(231, 240, 240));
        ViewFoodTable.setBorder(new javax.swing.border.MatteBorder(null));
        ViewFoodTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ViewFoodTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TYPE", "Name", "PRICE", "Description"
            }
        ));
        ViewFoodTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ViewFoodTable.setFillsViewportHeight(true);
        ViewFoodTable.setGridColor(new java.awt.Color(51, 51, 51));
        ViewFoodTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewFoodTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ViewFoodTableMouseEntered(evt);
            }
        });
        jScrollPane4.setViewportView(ViewFoodTable);
        if (ViewFoodTable.getColumnModel().getColumnCount() > 0) {
            ViewFoodTable.getColumnModel().getColumn(4).setHeaderValue("Description");
        }

        food_image_lable1.setBackground(new java.awt.Color(255, 255, 255));
        food_image_lable1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        food_image_lable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/addimg.png"))); // NOI18N
        food_image_lable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        food_image_lable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        food_image_lable1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        food_image_lable1.setOpaque(true);
        food_image_lable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                food_image_lable1MouseClicked(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel60.setText("Name       ");

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel61.setText("Price Rs.   ");

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel62.setText("Description     ");

        food_name_out.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        food_price_out.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        food_description_out.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        food_type_out.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel63.setText("Type");

        javax.swing.GroupLayout viewfoodLayout = new javax.swing.GroupLayout(viewfood);
        viewfood.setLayout(viewfoodLayout);
        viewfoodLayout.setHorizontalGroup(
            viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewfoodLayout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(jLabel59)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(viewfoodLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewfoodLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewfoodLayout.createSequentialGroup()
                                .addGroup(viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(food_type_out, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(food_description_out, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewfoodLayout.createSequentialGroup()
                                .addGroup(viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(food_price_out, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(food_name_out, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(viewfoodLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(food_image_lable1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        viewfoodLayout.setVerticalGroup(
            viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewfoodLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewfoodLayout.createSequentialGroup()
                        .addComponent(food_image_lable1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(food_name_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(food_price_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(food_description_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(food_type_out, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        addfoodbase.add(viewfood, "card2");

        addfood1.setBackground(new java.awt.Color(255, 255, 255));
        addfood1.setMaximumSize(new java.awt.Dimension(1017, 546));
        addfood1.setMinimumSize(new java.awt.Dimension(1017, 546));
        addfood1.setPreferredSize(new java.awt.Dimension(1017, 546));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel53.setText("Food Name       ");

        jSeparator26.setBackground(new java.awt.Color(0, 0, 0));

        Add_food_name.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Add_food_name.setBorder(null);
        Add_food_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Add_food_nameKeyPressed(evt);
            }
        });

        jSeparator27.setBackground(new java.awt.Color(0, 0, 0));

        Add_food_price.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Add_food_price.setBorder(null);
        Add_food_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Add_food_priceKeyPressed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setText("Food Price  in Rs.   ");

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel55.setText("Food Type    ");

        jComboBoxFoodType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBoxFoodType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Food Menu Type", "Breakfast", "Lunch", "Dinner", "Drinks and Bevarage", " " }));
        jComboBoxFoodType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFoodTypeActionPerformed(evt);
            }
        });

        food_image_lable.setBackground(new java.awt.Color(255, 255, 255));
        food_image_lable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        food_image_lable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/addimg.png"))); // NOI18N
        food_image_lable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        food_image_lable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        food_image_lable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        food_image_lable.setOpaque(true);
        food_image_lable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                food_image_lableMouseClicked(evt);
            }
        });

        jSeparator28.setBackground(new java.awt.Color(0, 0, 0));

        Add_food_description.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Add_food_description.setBorder(null);
        Add_food_description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Add_food_descriptionKeyPressed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel56.setText("Food Description     ");

        FoodWarningText.setForeground(new java.awt.Color(255, 51, 51));

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel57.setText("Food ID    ");

        Add_food_id.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Add_food_id.setBorder(null);
        Add_food_id.setEnabled(false);
        Add_food_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Add_food_idKeyPressed(evt);
            }
        });

        jSeparator29.setBackground(new java.awt.Color(0, 0, 0));

        jButton21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/AUpdate.png"))); // NOI18N
        jButton21.setText("Update");
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton21MouseClicked(evt);
            }
        });

        jButton22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/Adelete.png"))); // NOI18N
        jButton22.setText("Delete");
        jButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton22MouseClicked(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/Aclear.png"))); // NOI18N
        jButton23.setText("Clear");
        jButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton23MouseClicked(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel58.setText("Food Details    ");

        jButton24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/Add.png"))); // NOI18N
        jButton24.setText("Add");
        jButton24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton24MouseClicked(evt);
            }
        });

        AddFoodTable.setAutoCreateRowSorter(true);
        AddFoodTable.setBackground(new java.awt.Color(231, 240, 240));
        AddFoodTable.setBorder(new javax.swing.border.MatteBorder(null));
        AddFoodTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        AddFoodTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TYPE", "Name", "PRICE", "Description"
            }
        ));
        AddFoodTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddFoodTable.setEditingColumn(1);
        AddFoodTable.setEditingRow(1);
        AddFoodTable.setFillsViewportHeight(true);
        AddFoodTable.setGridColor(new java.awt.Color(51, 51, 51));
        AddFoodTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddFoodTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AddFoodTableMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(AddFoodTable);
        if (AddFoodTable.getColumnModel().getColumnCount() > 0) {
            AddFoodTable.getColumnModel().getColumn(4).setHeaderValue("Description");
        }

        javax.swing.GroupLayout addfood1Layout = new javax.swing.GroupLayout(addfood1);
        addfood1.setLayout(addfood1Layout);
        addfood1Layout.setHorizontalGroup(
            addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addfood1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addfood1Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FoodWarningText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(421, 421, 421))
                    .addGroup(addfood1Layout.createSequentialGroup()
                        .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addfood1Layout.createSequentialGroup()
                                .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(17, 17, 17)
                                .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Add_food_id, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator29, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(Add_food_name, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBoxFoodType, javax.swing.GroupLayout.Alignment.LEADING, 0, 352, Short.MAX_VALUE))
                                    .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(Add_food_price, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                        .addComponent(jSeparator26, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(Add_food_description, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                        .addComponent(jSeparator27, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jSeparator28, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(addfood1Layout.createSequentialGroup()
                                        .addGap(93, 93, 93)
                                        .addComponent(food_image_lable, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(addfood1Layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jButton22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addfood1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(18, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addfood1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel58)
                                .addGap(188, 188, 188))))))
        );
        addfood1Layout.setVerticalGroup(
            addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addfood1Layout.createSequentialGroup()
                .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addfood1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(food_image_lable, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FoodWarningText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Add_food_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator29, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxFoodType))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Add_food_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator26, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Add_food_price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator27, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Add_food_description, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addfood1Layout.createSequentialGroup()
                                .addComponent(jSeparator28, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addfood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(addfood1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );

        addfoodbase.add(addfood1, "card2");

        jToolBar2.setRollover(true);

        AddnewFoodBTN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AddnewFoodBTN.setText("Add New Food");
        AddnewFoodBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddnewFoodBTNMouseClicked(evt);
            }
        });
        jToolBar2.add(AddnewFoodBTN);
        jToolBar2.add(jSeparator70);

        Available_foodBTN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Available_foodBTN.setText("Available Food");
        Available_foodBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Available_foodBTNMouseClicked(evt);
            }
        });
        jToolBar2.add(Available_foodBTN);

        javax.swing.GroupLayout Food_managementLayout = new javax.swing.GroupLayout(Food_management);
        Food_management.setLayout(Food_managementLayout);
        Food_managementLayout.setHorizontalGroup(
            Food_managementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Food_managementLayout.createSequentialGroup()
                .addComponent(addfoodbase, javax.swing.GroupLayout.PREFERRED_SIZE, 1026, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator25, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(Food_managementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Food_managementLayout.setVerticalGroup(
            Food_managementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Food_managementLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator25, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addfoodbase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Catering_Base.add(Food_management, "card2");

        javax.swing.GroupLayout Catering_and_foodLayout = new javax.swing.GroupLayout(Catering_and_food);
        Catering_and_food.setLayout(Catering_and_foodLayout);
        Catering_and_foodLayout.setHorizontalGroup(
            Catering_and_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Catering_and_foodLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(FoodManagementBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(FoodOderBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(FoodPackageBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Catering_and_foodLayout.createSequentialGroup()
                .addGroup(Catering_and_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Catering_Base, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator30, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 44, Short.MAX_VALUE))
        );
        Catering_and_foodLayout.setVerticalGroup(
            Catering_and_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Catering_and_foodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Catering_and_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FoodManagementBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FoodPackageBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FoodOderBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Catering_Base, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Dynamic_Panel.add(Catering_and_food, "card2");

        Inventory.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setText("Inventory");

        javax.swing.GroupLayout InventoryLayout = new javax.swing.GroupLayout(Inventory);
        Inventory.setLayout(InventoryLayout);
        InventoryLayout.setHorizontalGroup(
            InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryLayout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(494, Short.MAX_VALUE))
        );
        InventoryLayout.setVerticalGroup(
            InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryLayout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(366, Short.MAX_VALUE))
        );

        Dynamic_Panel.add(Inventory, "card2");

        House_keeping.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel29.setText("housekeeping");

        javax.swing.GroupLayout House_keepingLayout = new javax.swing.GroupLayout(House_keeping);
        House_keeping.setLayout(House_keepingLayout);
        House_keepingLayout.setHorizontalGroup(
            House_keepingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(House_keepingLayout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(353, Short.MAX_VALUE))
        );
        House_keepingLayout.setVerticalGroup(
            House_keepingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(House_keepingLayout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(366, Short.MAX_VALUE))
        );

        Dynamic_Panel.add(House_keeping, "card2");

        RoomandHall.setBackground(new java.awt.Color(255, 255, 255));

        jButtonAddHalls1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonAddHalls1.setText("Show Halls");
        jButtonAddHalls1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAddHalls1MouseClicked(evt);
            }
        });
        jButtonAddHalls1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddHalls1ActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton16.setText("Rooms And Hall Manage");
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton16MouseClicked(evt);
            }
        });

        RoomandHallBase.setBackground(new java.awt.Color(255, 255, 255));
        RoomandHallBase.setLayout(new java.awt.CardLayout());

        addNewroom.setBackground(new java.awt.Color(255, 255, 255));

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel52.setText("Room ID");

        jComboBoxroomBedtype.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBoxroomBedtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Bed Type", "Single", "Duble", "Trible", " " }));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Room Details");

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel51.setText("Bed Type");

        jComboBoxRoomtype.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBoxRoomtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Room Type", "Normal", "Standard", "Deluxe", "Luxury" }));

        AddRoomTableIn.setAutoCreateRowSorter(true);
        AddRoomTableIn.setBackground(new java.awt.Color(231, 240, 240));
        AddRoomTableIn.setBorder(new javax.swing.border.MatteBorder(null));
        AddRoomTableIn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        AddRoomTableIn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TYPE", "BED TYPE", "PRICE"
            }
        ));
        AddRoomTableIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddRoomTableIn.setEditingColumn(1);
        AddRoomTableIn.setEditingRow(1);
        AddRoomTableIn.setFillsViewportHeight(true);
        AddRoomTableIn.setGridColor(new java.awt.Color(51, 51, 51));
        AddRoomTableIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddRoomTableInMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AddRoomTableInMouseEntered(evt);
            }
        });
        jScrollPane6.setViewportView(AddRoomTableIn);

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel48.setText("Room Type");

        roomWarning.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        roomWarning.setForeground(new java.awt.Color(255, 0, 0));

        roomIdin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        roomIdin.setBorder(null);
        roomIdin.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        roomIdin.setEnabled(false);

        jButton26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton26.setText("Clear");
        jButton26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton26MouseClicked(evt);
            }
        });

        roomInprice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        roomInprice.setBorder(null);
        roomInprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                roomInpriceKeyPressed(evt);
            }
        });

        jButton25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton25.setText("Delete");
        jButton25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton25MouseClicked(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel64.setText("Price");

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton20.setText("Update");
        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton20MouseClicked(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton19.setText("Add");
        jButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton19MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout addNewroomLayout = new javax.swing.GroupLayout(addNewroom);
        addNewroom.setLayout(addNewroomLayout);
        addNewroomLayout.setHorizontalGroup(
            addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewroomLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addNewroomLayout.createSequentialGroup()
                        .addGroup(addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(addNewroomLayout.createSequentialGroup()
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(roomInprice, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator32, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(addNewroomLayout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxRoomtype, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addNewroomLayout.createSequentialGroup()
                                .addGroup(addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addNewroomLayout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(jComboBoxroomBedtype, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(addNewroomLayout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jSeparator31)
                                            .addComponent(roomIdin, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(addNewroomLayout.createSequentialGroup()
                                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(roomWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addNewroomLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27)
                        .addGap(278, 278, 278)))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        addNewroomLayout.setVerticalGroup(
            addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewroomLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addNewroomLayout.createSequentialGroup()
                        .addComponent(roomWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roomIdin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator31, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxRoomtype, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxroomBedtype, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(addNewroomLayout.createSequentialGroup()
                                .addComponent(roomInprice, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jSeparator32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addGroup(addNewroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        RoomandHallBase.add(addNewroom, "card2");

        javax.swing.GroupLayout RoomandHallLayout = new javax.swing.GroupLayout(RoomandHall);
        RoomandHall.setLayout(RoomandHallLayout);
        RoomandHallLayout.setHorizontalGroup(
            RoomandHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoomandHallLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jButtonAddHalls1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jSeparator16)
            .addComponent(RoomandHallBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        RoomandHallLayout.setVerticalGroup(
            RoomandHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoomandHallLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RoomandHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddHalls1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RoomandHallBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Dynamic_Panel.add(RoomandHall, "card2");

        Account_settings.setBackground(new java.awt.Color(255, 255, 255));

        profileBTN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        profileBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/profile.png"))); // NOI18N
        profileBTN.setText("User  Profile");
        profileBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profileBTN.setIconTextGap(40);
        profileBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileBTNMouseClicked(evt);
            }
        });

        createuserBTN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        createuserBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/adduser.png"))); // NOI18N
        createuserBTN.setText("Create new user");
        createuserBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createuserBTN.setIconTextGap(30);
        createuserBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createuserBTNMouseClicked(evt);
            }
        });

        changepwdBTN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        changepwdBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/pwd.png"))); // NOI18N
        changepwdBTN.setText("Change Password");
        changepwdBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changepwdBTN.setIconTextGap(30);
        changepwdBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changepwdBTNMouseClicked(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));

        account_base.setBackground(new java.awt.Color(255, 255, 255));
        account_base.setLayout(new java.awt.CardLayout());

        user_profile.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator10.setBackground(new java.awt.Color(0, 0, 0));

        user_profile_addres2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        user_profile_addres2.setBorder(null);
        user_profile_addres2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                user_profile_addres2KeyPressed(evt);
            }
        });

        user_profile_image.setBackground(new java.awt.Color(255, 255, 255));
        user_profile_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        user_profile_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/addimg.png"))); // NOI18N
        user_profile_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        user_profile_image.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        user_profile_image.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        user_profile_image.setOpaque(true);
        user_profile_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_profile_imageMouseClicked(evt);
            }
        });

        jButton_user_profile_save.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_user_profile_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/icons8_save_20px_1.png"))); // NOI18N
        jButton_user_profile_save.setText("Save The Changes");
        jButton_user_profile_save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_user_profile_save.setIconTextGap(10);
        jButton_user_profile_save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_user_profile_saveMouseClicked(evt);
            }
        });
        jButton_user_profile_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_user_profile_saveActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("User Name     ");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("Full Name       ");

        profile_warning_text1.setBackground(new java.awt.Color(255, 255, 255));
        profile_warning_text1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        profile_warning_text1.setForeground(new java.awt.Color(255, 0, 0));
        profile_warning_text1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile_warning_text1.setText("<html>\n<a href=#> Edit User Profile</a>\n<html>");
        profile_warning_text1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profile_warning_text1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profile_warning_text1MouseClicked(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Account Type  ");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("N.I.C number  ");

        Address2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Address2.setText("Address ");

        user_profile_username.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        user_profile_username.setBorder(null);
        user_profile_username.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        user_profile_username.setEnabled(false);
        user_profile_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                user_profile_usernameKeyPressed(evt);
            }
        });

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));

        user_profile_fullname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        user_profile_fullname.setBorder(null);
        user_profile_fullname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                user_profile_fullnameKeyPressed(evt);
            }
        });

        jSeparator12.setBackground(new java.awt.Color(0, 0, 0));

        user_profile_nic.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        user_profile_nic.setBorder(null);
        user_profile_nic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                user_profile_nicKeyPressed(evt);
            }
        });

        jSeparator14.setBackground(new java.awt.Color(0, 0, 0));

        user_profile_addres1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        user_profile_addres1.setBorder(null);
        user_profile_addres1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                user_profile_addres1KeyPressed(evt);
            }
        });

        user_profile_type.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        user_profile_type.setBorder(null);
        user_profile_type.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        user_profile_type.setEnabled(false);
        user_profile_type.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                user_profile_typeKeyPressed(evt);
            }
        });

        comEditCh.setBackground(new java.awt.Color(255, 255, 255));
        comEditCh.setForeground(new java.awt.Color(255, 255, 255));
        comEditCh.setBorder(null);
        comEditCh.setEnabled(false);
        comEditCh.setOpaque(false);

        javax.swing.GroupLayout user_profileLayout = new javax.swing.GroupLayout(user_profile);
        user_profile.setLayout(user_profileLayout);
        user_profileLayout.setHorizontalGroup(
            user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_profileLayout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(user_profileLayout.createSequentialGroup()
                        .addComponent(Address2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(user_profile_addres1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(user_profile_addres2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(user_profileLayout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(user_profile_nic, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)))
                    .addGroup(user_profileLayout.createSequentialGroup()
                        .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(profile_warning_text1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                                .addComponent(user_profile_username, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(user_profile_type, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(user_profileLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(user_profile_fullname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(242, 242, 242))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_profileLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_user_profile_save, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(325, 325, 325))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_profileLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(comEditCh, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(user_profile_image, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(377, 377, 377))
        );
        user_profileLayout.setVerticalGroup(
            user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_profileLayout.createSequentialGroup()
                .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(user_profileLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(comEditCh, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(user_profile_image, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profile_warning_text1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(user_profileLayout.createSequentialGroup()
                        .addComponent(user_profile_username, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34)
                    .addGroup(user_profileLayout.createSequentialGroup()
                        .addComponent(user_profile_type, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33)
                    .addGroup(user_profileLayout.createSequentialGroup()
                        .addComponent(user_profile_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(user_profileLayout.createSequentialGroup()
                        .addComponent(user_profile_nic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel37))
                .addGap(18, 18, 18)
                .addGroup(user_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Address2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_profileLayout.createSequentialGroup()
                        .addComponent(user_profile_addres1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(user_profile_addres2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_user_profile_save, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        account_base.add(user_profile, "card2");

        create_newuser_form.setBackground(new java.awt.Color(255, 255, 255));
        create_newuser_form.setMaximumSize(new java.awt.Dimension(902, 572));
        create_newuser_form.setMinimumSize(new java.awt.Dimension(902, 572));

        User_image_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        User_image_lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/addimg.png"))); // NOI18N
        User_image_lbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        User_image_lbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        User_image_lbl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        User_image_lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                User_image_lblMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("User Name     ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Full Name       ");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Account Type  ");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Password        ");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("N.I.C number  ");

        Address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Address.setText("Address line1");

        new_username_in.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        new_username_in.setBorder(null);
        new_username_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                new_username_inKeyPressed(evt);
            }
        });

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));

        new_user_fullname_in.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        new_user_fullname_in.setBorder(null);
        new_user_fullname_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                new_user_fullname_inKeyPressed(evt);
            }
        });

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));

        new_user_nic_in.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        new_user_nic_in.setBorder(null);
        new_user_nic_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                new_user_nic_inKeyPressed(evt);
            }
        });

        jComboAc_type.setBackground(new java.awt.Color(204, 204, 255));
        jComboAc_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Staff Account", "Admin Account" }));
        jComboAc_type.setBorder(null);

        jPassword_newUser.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPassword_newUser.setBorder(null);
        jPassword_newUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPassword_newUserKeyPressed(evt);
            }
        });

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));

        Address1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Address1.setText("Address line2");

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));

        new_user_addres1_in.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        new_user_addres1_in.setBorder(null);
        new_user_addres1_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                new_user_addres1_inKeyPressed(evt);
            }
        });

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));

        new_user_addres2_in.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        new_user_addres2_in.setBorder(null);
        new_user_addres2_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                new_user_addres2_inKeyPressed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/icons8_add_user_20px.png"))); // NOI18N
        jButton3.setText("Add User");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/icons8_remove_user_male_20px.png"))); // NOI18N
        jButton4.setText("Delete User");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/icons8_erase_20px.png"))); // NOI18N
        jButton5.setText("Clear");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        new_user_warning_text.setBackground(new java.awt.Color(255, 255, 255));
        new_user_warning_text.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        new_user_warning_text.setForeground(new java.awt.Color(255, 0, 0));

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/showPwd.png"))); // NOI18N
        jLabel31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel31MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel31MouseReleased(evt);
            }
        });

        jButton46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/camera.png"))); // NOI18N
        jButton46.setText("Turn on Camera");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        jButton47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/capture.png"))); // NOI18N
        jButton47.setText("Tack  Capture    ");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton47, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(jButton46, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton46, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setForeground(new java.awt.Color(51, 255, 51));
        jCheckBox1.setEnabled(false);
        jCheckBox1.setOpaque(false);

        javax.swing.GroupLayout create_newuser_formLayout = new javax.swing.GroupLayout(create_newuser_form);
        create_newuser_form.setLayout(create_newuser_formLayout);
        create_newuser_formLayout.setHorizontalGroup(
            create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(create_newuser_formLayout.createSequentialGroup()
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Address1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(new_user_addres1_in, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(new_user_addres2_in, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, create_newuser_formLayout.createSequentialGroup()
                                    .addComponent(jButton3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton4)
                                    .addGap(8, 8, 8)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(new_user_warning_text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(new_username_in, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(new_user_fullname_in, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(new_user_nic_in, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboAc_type, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, create_newuser_formLayout.createSequentialGroup()
                                    .addComponent(jPassword_newUser, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(User_image_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        create_newuser_formLayout.setVerticalGroup(
            create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(create_newuser_formLayout.createSequentialGroup()
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(User_image_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(new_user_warning_text, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addComponent(new_username_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addComponent(new_user_fullname_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jComboAc_type, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel22))
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, create_newuser_formLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPassword_newUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, create_newuser_formLayout.createSequentialGroup()
                        .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(create_newuser_formLayout.createSequentialGroup()
                                .addComponent(new_user_nic_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addComponent(Address))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, create_newuser_formLayout.createSequentialGroup()
                        .addComponent(new_user_addres1_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addComponent(new_user_addres2_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Address1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(25, 25, 25))
        );

        account_base.add(create_newuser_form, "card3");

        change_password.setBackground(new java.awt.Color(255, 255, 255));
        change_password.setMaximumSize(new java.awt.Dimension(902, 546));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("User Name     ");

        user_password_username.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        user_password_username.setBorder(null);
        user_password_username.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        user_password_username.setEnabled(false);
        user_password_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                user_password_usernameKeyPressed(evt);
            }
        });

        jSeparator13.setBackground(new java.awt.Color(0, 0, 0));

        jPassword_change_new.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPassword_change_new.setBorder(null);
        jPassword_change_new.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPassword_change_newKeyPressed(evt);
            }
        });

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/showPwd.png"))); // NOI18N
        jLabel36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel36MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel36MouseReleased(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText("New Password        ");

        jSeparator15.setBackground(new java.awt.Color(0, 0, 0));

        jPassword_change_confirm.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPassword_change_confirm.setBorder(null);
        jPassword_change_confirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPassword_change_confirmKeyPressed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("Confirm Password");

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/showPwd.png"))); // NOI18N
        jLabel40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel40MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel40MouseReleased(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/icons8_add_user_20px.png"))); // NOI18N
        jButton6.setText("Update the Password");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/Aclear.png"))); // NOI18N
        jButton7.setText("Clear");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabelpwdswarnning.setBackground(new java.awt.Color(255, 255, 255));
        jLabelpwdswarnning.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabelpwdswarnning.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout change_passwordLayout = new javax.swing.GroupLayout(change_password);
        change_password.setLayout(change_passwordLayout);
        change_passwordLayout.setHorizontalGroup(
            change_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(change_passwordLayout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addGroup(change_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(change_passwordLayout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addGroup(change_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator15)
                            .addComponent(jPassword_change_confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(change_passwordLayout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel40))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, change_passwordLayout.createSequentialGroup()
                        .addGroup(change_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(change_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator13)
                            .addComponent(user_password_username)
                            .addComponent(jPassword_change_new)
                            .addComponent(jLabelpwdswarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel36)))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        change_passwordLayout.setVerticalGroup(
            change_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(change_passwordLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabelpwdswarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(change_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user_password_username, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addGroup(change_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(change_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPassword_change_new, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(change_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(change_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPassword_change_confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(change_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(137, 137, 137))
        );

        account_base.add(change_password, "card3");

        javax.swing.GroupLayout Account_settingsLayout = new javax.swing.GroupLayout(Account_settings);
        Account_settings.setLayout(Account_settingsLayout);
        Account_settingsLayout.setHorizontalGroup(
            Account_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Account_settingsLayout.createSequentialGroup()
                .addComponent(profileBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(createuserBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(changepwdBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1)
            .addGroup(Account_settingsLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(account_base, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Account_settingsLayout.setVerticalGroup(
            Account_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Account_settingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Account_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profileBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createuserBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changepwdBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(account_base, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Dynamic_Panel.add(Account_settings, "card2");

        Developers.setBackground(new java.awt.Color(255, 255, 255));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel30.setText("developing Team");

        javax.swing.GroupLayout DevelopersLayout = new javax.swing.GroupLayout(Developers);
        Developers.setLayout(DevelopersLayout);
        DevelopersLayout.setHorizontalGroup(
            DevelopersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DevelopersLayout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(353, Short.MAX_VALUE))
        );
        DevelopersLayout.setVerticalGroup(
            DevelopersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DevelopersLayout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(366, Short.MAX_VALUE))
        );

        Dynamic_Panel.add(Developers, "card2");

        jLabel117.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(255, 255, 255));
        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel117.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/icons8_user_male_circle_26px_1.png"))); // NOI18N
        jLabel117.setText("        Logout");
        jLabel117.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel117.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel117MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel117, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(side_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator82, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Dynamic_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(time_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(DateLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Dynamic_title_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(side_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(time_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(DateLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(Dynamic_title_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Dynamic_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, 1336, 1336, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_account_settingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_account_settingMouseClicked

        BtnPanelClickevent(evt);
    }//GEN-LAST:event_btn_account_settingMouseClicked

    private void btn_bill_and_reportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_bill_and_reportMouseClicked

        BtnPanelClickevent(evt);
    }//GEN-LAST:event_btn_bill_and_reportMouseClicked

    private void btn_inventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_inventoryMouseClicked

        BtnPanelClickevent(evt);
    }//GEN-LAST:event_btn_inventoryMouseClicked

    private void btn_front_deskMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_front_deskMouseClicked

        BtnPanelClickevent(evt);
    }//GEN-LAST:event_btn_front_deskMouseClicked

    private void btn_room_and_hallMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_room_and_hallMouseClicked

        BtnPanelClickevent(evt);
    }//GEN-LAST:event_btn_room_and_hallMouseClicked

    private void btn_cateringMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cateringMouseClicked

        BtnPanelClickevent(evt);
    }//GEN-LAST:event_btn_cateringMouseClicked

    private void btn_housekeepingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_housekeepingMouseClicked

        BtnPanelClickevent(evt);
    }//GEN-LAST:event_btn_housekeepingMouseClicked

    private void btn_developmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_developmentMouseClicked

        BtnPanelClickevent(evt);
    }//GEN-LAST:event_btn_developmentMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        this.setExtendedState(Main_Window.ICONIFIED);
    }//GEN-LAST:event_jButton2MouseClicked

    private void btn_account_settingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_account_settingMouseEntered
        btn_account_setting.setBackground(new Color(102, 102, 102));
        //btn_home.setBackground(new Color(51,51,51));
        btn_bill_and_report.setBackground(new Color(51, 51, 51));
        btn_inventory.setBackground(new Color(51, 51, 51));
        btn_front_desk.setBackground(new Color(51, 51, 51));
        btn_room_and_hall.setBackground(new Color(51, 51, 51));
        btn_catering.setBackground(new Color(51, 51, 51));
        btn_housekeeping.setBackground(new Color(51, 51, 51));
        btn_development.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btn_account_settingMouseEntered

    private void btn_account_settingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_account_settingMouseExited

    }//GEN-LAST:event_btn_account_settingMouseExited

    private void btn_account_settingMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_account_settingMouseReleased

    }//GEN-LAST:event_btn_account_settingMouseReleased

    private void jButton1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton1FocusGained

    }//GEN-LAST:event_jButton1FocusGained

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBackground(Color.red);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setBackground(Color.BLACK);
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setBackground(Color.BLACK);
    }//GEN-LAST:event_jButton2MouseExited

    private void btn_bill_and_reportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_bill_and_reportMouseEntered
        btn_bill_and_report.setBackground(new Color(102, 102, 102));
        btn_account_setting.setBackground(new Color(51, 51, 51));
        //btn_home.setBackground(new Color(51,51,51));
        btn_inventory.setBackground(new Color(51, 51, 51));
        btn_front_desk.setBackground(new Color(51, 51, 51));
        btn_room_and_hall.setBackground(new Color(51, 51, 51));
        btn_catering.setBackground(new Color(51, 51, 51));
        btn_housekeeping.setBackground(new Color(51, 51, 51));
        btn_development.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btn_bill_and_reportMouseEntered

    private void btn_inventoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_inventoryMouseEntered
        btn_inventory.setBackground(new Color(102, 102, 102));
        btn_bill_and_report.setBackground(new Color(51, 51, 51));
        btn_account_setting.setBackground(new Color(51, 51, 51));
        //btn_home.setBackground(new Color(51,51,51));
        btn_front_desk.setBackground(new Color(51, 51, 51));
        btn_room_and_hall.setBackground(new Color(51, 51, 51));
        btn_catering.setBackground(new Color(51, 51, 51));
        btn_housekeeping.setBackground(new Color(51, 51, 51));
        btn_development.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btn_inventoryMouseEntered

    private void btn_front_deskMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_front_deskMouseEntered
        btn_front_desk.setBackground(new Color(102, 102, 102));
        btn_inventory.setBackground(new Color(51, 51, 51));
        btn_bill_and_report.setBackground(new Color(51, 51, 51));
        btn_account_setting.setBackground(new Color(51, 51, 51));
        //btn_home.setBackground(new Color(51,51,51));
        btn_room_and_hall.setBackground(new Color(51, 51, 51));
        btn_catering.setBackground(new Color(51, 51, 51));
        btn_housekeeping.setBackground(new Color(51, 51, 51));
        btn_development.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btn_front_deskMouseEntered

    private void btn_room_and_hallMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_room_and_hallMouseEntered
        btn_room_and_hall.setBackground(new Color(102, 102, 102));
        btn_front_desk.setBackground(new Color(51, 51, 51));
        btn_inventory.setBackground(new Color(51, 51, 51));
        btn_bill_and_report.setBackground(new Color(51, 51, 51));
        btn_account_setting.setBackground(new Color(51, 51, 51));
        //btn_home.setBackground(new Color(51,51,51));
        btn_catering.setBackground(new Color(51, 51, 51));
        btn_housekeeping.setBackground(new Color(51, 51, 51));
        btn_development.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btn_room_and_hallMouseEntered

    private void btn_cateringMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cateringMouseEntered
        btn_catering.setBackground(new Color(102, 102, 102));
        btn_room_and_hall.setBackground(new Color(51, 51, 51));
        btn_front_desk.setBackground(new Color(51, 51, 51));
        btn_inventory.setBackground(new Color(51, 51, 51));
        btn_bill_and_report.setBackground(new Color(51, 51, 51));
        btn_account_setting.setBackground(new Color(51, 51, 51));
        //btn_home.setBackground(new Color(51,51,51));
        btn_housekeeping.setBackground(new Color(51, 51, 51));
        btn_development.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btn_cateringMouseEntered

    private void btn_housekeepingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_housekeepingMouseEntered
        btn_housekeeping.setBackground(new Color(102, 102, 102));
        btn_catering.setBackground(new Color(51, 51, 51));
        btn_room_and_hall.setBackground(new Color(51, 51, 51));
        btn_front_desk.setBackground(new Color(51, 51, 51));
        btn_inventory.setBackground(new Color(51, 51, 51));
        btn_bill_and_report.setBackground(new Color(51, 51, 51));
        btn_account_setting.setBackground(new Color(51, 51, 51));
        // btn_home.setBackground(new Color(51,51,51));
        btn_development.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btn_housekeepingMouseEntered

    private void btn_developmentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_developmentMouseEntered
        btn_development.setBackground(new Color(102, 102, 102));
        btn_housekeeping.setBackground(new Color(51, 51, 51));
        btn_catering.setBackground(new Color(51, 51, 51));
        btn_room_and_hall.setBackground(new Color(51, 51, 51));
        btn_front_desk.setBackground(new Color(51, 51, 51));
        btn_inventory.setBackground(new Color(51, 51, 51));
        btn_bill_and_report.setBackground(new Color(51, 51, 51));
        btn_account_setting.setBackground(new Color(51, 51, 51));
        // btn_home.setBackground(new Color(51,51,51));

    }//GEN-LAST:event_btn_developmentMouseEntered

    private void createuserBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createuserBTNMouseClicked
        profileBTN.setForeground(Color.BLACK);
        changepwdBTN.setForeground(Color.BLACK);
        createuserBTN.setForeground(Color.RED);


        if (!"Admin :".equals(lbl_userType.getText())) {
            txt_dynamic_title_bar.setText("Sorry! you do not have permisstion");
        } else {
            createuserBTN.setForeground(Color.RED);
            profileBTN.setForeground(new Color(240, 240, 240));
            changepwdBTN.setForeground(new Color(240, 240, 240));
            txt_dynamic_title_bar.setText("New User Creation");
            account_base.removeAll();
            account_base.removeAll();
            account_base.repaint();
            account_base.revalidate();
            account_base.add(create_newuser_form);
            account_base.repaint();
            account_base.revalidate();
        }

    }//GEN-LAST:event_createuserBTNMouseClicked

    private void User_image_lblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_User_image_lblMouseClicked
        //chooice new use image
        newUserimgChooice();
    }//GEN-LAST:event_User_image_lblMouseClicked

    private void new_username_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_new_username_inKeyPressed
        char c = evt.getKeyChar(); //to validate the user input only allowed alphebets letters.
        if (Character.isLetter(c) || Character.isISOControl(c)) {
            new_username_in.setEditable(true);
        } else {
            new_username_in.setEditable(false);
            new_user_warning_text.setText("Valid only letters..");

        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (new_username_in.getText().isEmpty()) {
                new_user_warning_text.setText("Please Fillout the Username");
            } else {
                User a = new User();
                if (a.chUname()) {
                    JOptionPane.showMessageDialog(null, "Sorry! username already created..", "Not Allowed", HEIGHT);
                } else {
                    new_username_in.setBackground(new Color(255, 255, 255));
                    new_user_fullname_in.setBackground(new Color(197, 232, 240));
                    new_user_fullname_in.requestFocus();
                    new_user_fullname_in.setText("");
                    new_user_warning_text.setText("");
                }
            }
        }
    }//GEN-LAST:event_new_username_inKeyPressed

    private void new_user_fullname_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_new_user_fullname_inKeyPressed
        // validate the feild have only letters and whitespace
        char c = evt.getKeyChar(); //to validate the user input only allowed alphebets letters.
        if (Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
            new_user_fullname_in.setEditable(true);
        } else {
            new_user_fullname_in.setEditable(false);
            new_user_warning_text.setText("Invalid character...");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (new_user_fullname_in.getText().isEmpty()) {
                new_user_warning_text.setText("Please Fillout the Fullname");
            } else {
                new_user_fullname_in.setBackground(new Color(255, 255, 255));
                jPassword_newUser.setBackground(new Color(197, 232, 240));
                jPassword_newUser.requestFocus();
                jPassword_newUser.setText("");

                new_user_warning_text.setText("");
            }

        }
    }//GEN-LAST:event_new_user_fullname_inKeyPressed

    private void jLabel31MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MousePressed
        jPassword_newUser.setEchoChar((char) 0);
    }//GEN-LAST:event_jLabel31MousePressed

    private void jLabel31MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseReleased
        jPassword_newUser.setEchoChar('\u25cf');
    }//GEN-LAST:event_jLabel31MouseReleased

    private void jPassword_newUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPassword_newUserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (jPassword_newUser.getText().isEmpty()) {
                new_user_warning_text.setText("Please Fillout the Password");
            } else {
                jPassword_newUser.setBackground(new Color(255, 255, 255));
                new_user_nic_in.setBackground(new Color(197, 232, 240));
                new_user_nic_in.requestFocus();
                new_user_nic_in.setText("");

                new_user_warning_text.setText("");
            }

        }
    }//GEN-LAST:event_jPassword_newUserKeyPressed

    private void new_user_nic_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_new_user_nic_inKeyPressed
        char c = evt.getKeyChar(); //to validate the user input only allowed alphebets letters.
        if (Character.isLetter(c) || Character.isISOControl(c) || Character.isDigit(c)) {
            new_user_nic_in.setEditable(true);
        } else {
            new_user_nic_in.setEditable(false);
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (new_user_nic_in.getText().isEmpty()) {
                new_user_warning_text.setText("Please Fillout the NIC number");
            } else {
                new_user_nic_in.setBackground(new Color(255, 255, 255));
                new_user_addres1_in.setBackground(new Color(197, 232, 240));
                new_user_addres1_in.requestFocus();
                new_user_addres1_in.setText("");

                new_user_warning_text.setText("");
            }

        }
    }//GEN-LAST:event_new_user_nic_inKeyPressed

    private void new_user_addres1_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_new_user_addres1_inKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (new_user_addres1_in.getText().isEmpty()) {
                new_user_warning_text.setText("Please Fillout the Address line 1");
            } else {
                new_user_addres1_in.setBackground(new Color(255, 255, 255));
                new_user_addres2_in.setBackground(new Color(197, 232, 240));
                new_user_addres2_in.requestFocus();
                new_user_addres2_in.setText("");

                new_user_warning_text.setText("");
            }

        }
    }//GEN-LAST:event_new_user_addres1_inKeyPressed

    private void new_user_addres2_inKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_new_user_addres2_inKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (new_user_addres2_in.getText().isEmpty()) {
                new_user_warning_text.setText("Please Fillout the Address line 2");
            } else {
                new_user_addres2_in.setBackground(new Color(255, 255, 255));
                new_user_warning_text.setText("");
                //code or method for inserting data into the database....
                JOptionPane.showMessageDialog(null, "Inserting Function called");
            }

        }
    }//GEN-LAST:event_new_user_addres2_inKeyPressed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        createNewUserAc();
        //CALL THE INSERT METHOD FROM USER
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        //CLEAING NEW USER TEXT FELDS
        clearUserFeild();
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        //coe for delete the users
        deletingUserAc();
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void user_profile_addres2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_profile_addres2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_profile_addres2KeyPressed

    private void user_profile_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_profile_imageMouseClicked
        if (!(comEditCh.isSelected())) {
            // JOptionPane.showMessageDialog(null,"not allowed to action");
        } else {
            newUserimgChooiceToUpdate();
        }

    }//GEN-LAST:event_user_profile_imageMouseClicked

    private void jButton_user_profile_saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_user_profile_saveMouseClicked
        //TO UPLOAD THE USER INFORMATION...
        userProfileUpdate();
    }//GEN-LAST:event_jButton_user_profile_saveMouseClicked

    private void jButton_user_profile_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_user_profile_saveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_user_profile_saveActionPerformed

    private void user_profile_usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_profile_usernameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_profile_usernameKeyPressed

    private void user_profile_fullnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_profile_fullnameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_profile_fullnameKeyPressed

    private void user_profile_nicKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_profile_nicKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_profile_nicKeyPressed

    private void user_profile_addres1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_profile_addres1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_profile_addres1KeyPressed

    private void user_profile_typeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_profile_typeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_profile_typeKeyPressed

    private void profileBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileBTNMouseClicked
        profileBTN.setForeground(Color.RED);
        changepwdBTN.setForeground(Color.BLACK);
        createuserBTN.setForeground(Color.BLACK);

        txt_dynamic_title_bar.setText("User Profile...");
        account_base.removeAll();
        account_base.removeAll();
        account_base.repaint();
        account_base.revalidate();
        account_base.add(user_profile);
        account_base.repaint();
        account_base.revalidate();
    }//GEN-LAST:event_profileBTNMouseClicked

    private void changepwdBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changepwdBTNMouseClicked
        profileBTN.setForeground(Color.BLACK);
        changepwdBTN.setForeground(Color.RED);
        createuserBTN.setForeground(Color.BLACK);
        
        txt_dynamic_title_bar.setText("Change User Password");
        account_base.removeAll();
        account_base.removeAll();
        account_base.repaint();
        account_base.revalidate();
        account_base.add(change_password);
        account_base.repaint();
        account_base.revalidate();
    }//GEN-LAST:event_changepwdBTNMouseClicked

    private void profile_warning_text1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profile_warning_text1MouseClicked
        //make it the feilds editable
        makepuserProfileEditEnable();
    }//GEN-LAST:event_profile_warning_text1MouseClicked

    private void userNamelblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userNamelblMouseClicked

        txt_dynamic_title_bar.setText("User Account and Security");
        Dynamic_Panel.removeAll();
        Dynamic_Panel.removeAll();
        Dynamic_Panel.repaint();
        Dynamic_Panel.revalidate();
        Dynamic_Panel.add(Account_settings);
        Dynamic_Panel.repaint();
        Dynamic_Panel.revalidate();
    }//GEN-LAST:event_userNamelblMouseClicked

    private void user_password_usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_password_usernameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_password_usernameKeyPressed

    private void jPassword_change_newKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPassword_change_newKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (jPassword_change_new.getText().isEmpty()) {
                jPassword_change_new.setBackground(new Color(197, 232, 240));
                jPassword_change_new.requestFocus();
                jLabelpwdswarnning.setText("Please type new password");
            } else {

                jPassword_change_new.setBackground(new Color(255, 255, 255));
                jPassword_change_confirm.setBackground(new Color(197, 232, 240));
                jPassword_change_confirm.requestFocus();
                jPassword_change_confirm.setText("");
                jLabelpwdswarnning.setText("");

            }
        }
    }//GEN-LAST:event_jPassword_change_newKeyPressed

    private void jLabel36MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MousePressed
        jPassword_change_new.setEchoChar((char) 0);
    }//GEN-LAST:event_jLabel36MousePressed

    private void jLabel36MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseReleased
        jPassword_change_new.setEchoChar('\u25cf');
    }//GEN-LAST:event_jLabel36MouseReleased

    private void jPassword_change_confirmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPassword_change_confirmKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (jPassword_change_confirm.getText().isEmpty()) {
                jPassword_change_confirm.setBackground(new Color(197, 232, 240));
                jPassword_change_confirm.requestFocus();
                jLabelpwdswarnning.setText("Please type confirm password");
            } else {
                changeUserNewPwd();
                jLabelpwdswarnning.setText("");

            }
        }
    }//GEN-LAST:event_jPassword_change_confirmKeyPressed

    private void jLabel40MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MousePressed
        jPassword_change_confirm.setEchoChar((char) 0);
    }//GEN-LAST:event_jLabel40MousePressed

    private void jLabel40MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseReleased
        jPassword_change_confirm.setEchoChar('\u25cf');
    }//GEN-LAST:event_jLabel40MouseReleased

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        changeUserNewPwd();
        //reference class is Password
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        changeUserNewPwd();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jPassword_change_confirm.setText("");
        jPassword_change_new.setText("");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void guestRegistrationBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guestRegistrationBTNMouseClicked
        reservationBTN.setForeground(Color.BLACK);
        reservationUpdateBTN.setForeground(Color.BLACK);
        todayReservationBTN.setForeground(Color.BLACK);
        guestRegistrationBTN.setForeground(Color.RED);
 
        frontDeskBase.removeAll();
        frontDeskBase.removeAll();
        frontDeskBase.repaint();
        frontDeskBase.revalidate();
        frontDeskBase.add(GuestRegistartion);
        frontDeskBase.repaint();
        frontDeskBase.revalidate();
        txt_dynamic_title_bar.setText("Front Desk - Guest Registration.");
    }//GEN-LAST:event_guestRegistrationBTNMouseClicked

    private void reservationBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reservationBTNMouseClicked
        reservationBTN.setForeground(Color.RED);
        reservationUpdateBTN.setForeground(Color.BLACK);
        guestRegistrationBTN.setForeground(Color.BLACK);
        todayReservationBTN.setForeground(Color.BLACK);
        
        frontDeskBase.removeAll();
        frontDeskBase.removeAll();
        frontDeskBase.repaint();
        frontDeskBase.revalidate();
        frontDeskBase.add(reservation);
        frontDeskBase.repaint();
        frontDeskBase.revalidate();
        txt_dynamic_title_bar.setText("Front Desk - Guest Reservation.");
    }//GEN-LAST:event_reservationBTNMouseClicked

    private void reservationUpdateBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reservationUpdateBTNMouseClicked
        reservationBTN.setForeground(Color.BLACK);
        reservationUpdateBTN.setForeground(Color.RED);
        guestRegistrationBTN.setForeground(Color.BLACK);
        todayReservationBTN.setForeground(Color.BLACK);

        frontDeskBase.removeAll();
        frontDeskBase.removeAll();
        frontDeskBase.repaint();
        frontDeskBase.revalidate();
        frontDeskBase.add(Guest_Statictics);
        frontDeskBase.repaint();
        frontDeskBase.revalidate();
        txt_dynamic_title_bar.setText("Front Desk - Guest Statistics.");
    }//GEN-LAST:event_reservationUpdateBTNMouseClicked

    private void guest_emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guest_emailKeyPressed

        char c = evt.getKeyChar(); //to validate the user input only allowed alphebets letters.
        if ((Character.isLetter(c)) || Character.isDigit(c) || evt.getKeyChar() == '.' || evt.getKeyChar() == '@' || evt.getKeyChar() == '_' || Character.isISOControl(c)) {
            guest_email.setEditable(true);
            guest_warning_text.setText("");
        } else {
            guest_email.setEditable(false);
            guest_warning_text.setText("Allowed only [@ . _],[a-z],[0-9] ");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (guest_email.getText().isEmpty()) {
                //guest_fullname.setBackground(new Color(197, 232, 240));
                guest_warning_text.setText("Please Fillout the guest email");
            } else {
                guest_email.setBackground(new Color(255, 255, 255));
                guest_purpose_visit.setBackground(new Color(197, 232, 240));
                guest_purpose_visit.requestFocus();
                guest_purpose_visit.setText("");
                guest_warning_text.setText("");
            }
        }
    }//GEN-LAST:event_guest_emailKeyPressed

    private void guest_purpose_visitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guest_purpose_visitKeyPressed
        // TODO add your handling code here:guest_purpose_visit
        char c = evt.getKeyChar(); //to validate the user input only allowed alphebets letters.
        if (Character.isLetter(c) || Character.isISOControl(c) || Character.isWhitespace(c)) {
            guest_purpose_visit.setEditable(true);
            guest_warning_text.setText("");
        } else {
            guest_purpose_visit.setEditable(false);
            guest_warning_text.setText("Allowed only alphabets letters");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (guest_purpose_visit.getText().isEmpty()) {
                //guest_fullname.setBackground(new Color(197, 232, 240));
                guest_warning_text.setText("Please Fillout guest visit purpose");
            } else {
                guest_purpose_visit.setBackground(new Color(255, 255, 255));
                guest_address.setBackground(new Color(197, 232, 240));
                guest_address.requestFocus();
                guest_address.setText("");
                guest_warning_text.setText("");
            }
        }
    }//GEN-LAST:event_guest_purpose_visitKeyPressed

    private void guest_addressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guest_addressKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (guest_address.getText().isEmpty()) {
                guest_warning_text.setText("Please Fillout address");
            } else {
                guest_address.setBackground(new Color(255, 255, 255));
                guestDataInsert2db();
                showGuestDataToJTable1();
                autoId();
            }
        }

    }//GEN-LAST:event_guest_addressKeyPressed

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        guestDataInsert2db();
        autoId();
        showGuestDataToJTable1();
        guest_address.setBackground(new Color(255, 255, 255));

    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //guestDataInsert2db();

    }//GEN-LAST:event_jButton8ActionPerformed

    private void guest_in_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guest_in_imageMouseClicked
        Camera_panel1.setVisible(true);
    }//GEN-LAST:event_guest_in_imageMouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // 
        GuestUpdatedata();
        showGuestDataToJTable1();
        autoId();
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        clearGuestFeilds();
        showGuestDataToJTable1();
        autoId();
    }//GEN-LAST:event_jButton10MouseClicked

    private void guest_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guest_idKeyPressed
        char c = evt.getKeyChar();
        if (Character.isDigit(c) || Character.isLetter(c) || Character.isISOControl(c)) {
            guest_id.setEditable(true);
        } else {
            guest_id.setEditable(false);
            guest_warning_text.setText("GuestID is auto generated GNR10000");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (guest_id.getText().isEmpty()) {
                guest_id.setBackground(new Color(197, 232, 240));
                guest_warning_text.setText("Please Clear button to reset the ID");
            } else if (guest_id.getText().length() != 8) {
                guest_warning_text.setText("Invalid Guest ID");
            } else {
                searchingGuestByID();
                guest_id.setBackground(new Color(255, 255, 255));
                guest_fullname.setBackground(new Color(197, 232, 240));
                guest_fullname.requestFocus();
                guest_fullname.setText("");
                guest_warning_text.setText("");
            }
        }
    }//GEN-LAST:event_guest_idKeyPressed

    private void guest_fullnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guest_fullnameKeyPressed
        char c = evt.getKeyChar(); //to validate the user input only allowed alphebets letters.
        if (Character.isLetter(c) || Character.isISOControl(c) || Character.isWhitespace(c)) {
            guest_fullname.setEditable(true);
            guest_warning_text.setText("");
        } else {
            guest_fullname.setEditable(false);
            guest_warning_text.setText("Allowed only alphabets letters ");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (guest_fullname.getText().isEmpty()) {
                //guest_fullname.setBackground(new Color(197, 232, 240));
                guest_warning_text.setText("Please Fillout the guest name");
            } else {
                guest_fullname.setBackground(new Color(255, 255, 255));
                guest_identitity.setBackground(new Color(197, 232, 240));
                guest_identitity.requestFocus();
                guest_identitity.setText("");
                guest_warning_text.setText("");
            }
        }
    }//GEN-LAST:event_guest_fullnameKeyPressed

    private void guest_identitityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guest_identitityKeyPressed
        //guest_identitity
        char c = evt.getKeyChar(); //to validate the user input only allowed alphebets letters.
        if (Character.isLetter(c) || Character.isDigit(c) || Character.isISOControl(c)) {
            guest_identitity.setEditable(true);
            guest_warning_text.setText("");
        } else {
            guest_identitity.setEditable(false);
            guest_warning_text.setText("Allowed only numbers & alphabets letters");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (guest_identitity.getText().isEmpty()) {
                //guest_fullname.setBackground(new Color(197, 232, 240));
                guest_warning_text.setText("Please Fillout the guest identity");
            } else {
                guest_identitity.setBackground(new Color(255, 255, 255));
                guest_tel.setBackground(new Color(197, 232, 240));
                guest_tel.requestFocus();
                guest_tel.setText("");
                guest_warning_text.setText("");
            }
        }
    }//GEN-LAST:event_guest_identitityKeyPressed

    private void guest_telKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guest_telKeyPressed
        char c = evt.getKeyChar(); //to validate the user input only allowed alphebets letters.
        int count = guest_tel.getText().length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' && count <= 10 || Character.isISOControl(c)) {
            guest_tel.setEditable(true);
            guest_warning_text.setText("");
        } else {
            guest_tel.setEditable(false);
            guest_warning_text.setText("Allowed only numbers(0-9)");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (guest_tel.getText().isEmpty()) {
                //guest_fullname.setBackground(new Color(197, 232, 240));
                guest_warning_text.setText("Please Fillout the guest telephone");
            } else if ((guest_tel.getText().length() != 10)) {
                guest_warning_text.setText("phone number should 10 characters");
            } else {
                guest_tel.setBackground(new Color(255, 255, 255));
                guest_email.setBackground(new Color(197, 232, 240));
                guest_email.requestFocus();
                guest_email.setText("");
                guest_warning_text.setText("");
            }
        }
    }//GEN-LAST:event_guest_telKeyPressed

    private void guest_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guest_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guest_idActionPerformed

    private void guest_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guest_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guest_emailActionPerformed

    Camera open = new Camera(); //here this object i put globle because when tack capture then want close frame.
    boolean camisOn = false; //this is used to check if camera on or off (state of webcamera)...
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (jCheckBoxCamereCheck.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "cam already opened!");
        } else {
            try {
                open.openCamera();
                jCheckBoxCamereCheck.setSelected(true);
                jCheckBoxCamereCheck.setText("Camera is On");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TO TACK WEBCAM PHOTO
        if (jCheckBoxCamereCheck.isSelected() == true) {
            try {
                Camera capture = new Camera();
                capture.tackCapture();
                open.frame.hide();
                jCheckBoxCamereCheck.setSelected(false);
                jCheckBoxCamereCheck.setText("Camera is Off");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            try {
                guest.setGuestImage();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please turn on the camera..");
        }

    }//GEN-LAST:event_jButton12ActionPerformed

    private void guest_idMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_guest_idMouseWheelMoved

    }//GEN-LAST:event_guest_idMouseWheelMoved

    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
        guest_id.setEnabled(true);
        guest_id.setEditable(true);
        showGuestDataToJTable1();
    }//GEN-LAST:event_jButton13MouseClicked

    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseClicked
        if (jCheckBoxCamereCheck.isSelected() == true) {
            open.frame.hide();
            jCheckBoxCamereCheck.setSelected(false);
            jCheckBoxCamereCheck.setText("Camera is Off");
            Camera_panel1.setVisible(false);
        } else {
            jCheckBoxCamereCheck.setSelected(false);
            jCheckBoxCamereCheck.setText("Camera is Off");
            Camera_panel1.setVisible(false);
        }
    }//GEN-LAST:event_jButton14MouseClicked

    private void jLabel46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseClicked
        searchingGuestByID();
    }//GEN-LAST:event_jLabel46MouseClicked

    private void guest_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guest_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guest_addressActionPerformed

    private void jTableGuestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableGuestMouseClicked
        //guestJTable_RowClicked();
        guestJTableClick();
    }//GEN-LAST:event_jTableGuestMouseClicked

    private void jTableGuestMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableGuestMouseEntered
        showGuestDataToJTable1();
    }//GEN-LAST:event_jTableGuestMouseEntered

    private void Add_food_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Add_food_nameKeyPressed
        //foodname
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Add_food_name.getText().isEmpty()) {
                Add_food_name.requestFocus();
                Add_food_name.setBackground(new Color(197, 232, 240));
                FoodWarningText.setText("Please fillout the food name");
            } else {
                Add_food_name.setBackground(new Color(255, 255, 255));
                Add_food_price.setBackground(new Color(197, 232, 240));
                Add_food_price.requestFocus();
                Add_food_price.setText("");
                FoodWarningText.setText("");
            }
        }
    }//GEN-LAST:event_Add_food_nameKeyPressed

    private void Add_food_priceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Add_food_priceKeyPressed
        char c = evt.getKeyChar();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' || Character.isISOControl(c)) {
            Add_food_price.setEditable(true);
            FoodWarningText.setText("");
        } else {
            Add_food_price.setEditable(false);
            FoodWarningText.setText(" Digits Only Allowed like 100 or 100.00");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Add_food_price.getText().isEmpty()) {
                Add_food_price.requestFocus();
                Add_food_price.setBackground(new Color(197, 232, 240));
                FoodWarningText.setText("Please fillout the food Price");
            } else {
                Add_food_description.setBackground(new Color(255, 255, 255));
                insertFoodData();
                FoodWarningText.setText("");
            }
        }
    }//GEN-LAST:event_Add_food_priceKeyPressed

    private void food_image_lableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_food_image_lableMouseClicked
        chooiceFoodImage();
    }//GEN-LAST:event_food_image_lableMouseClicked

    private void Add_food_descriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Add_food_descriptionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Add_food_descriptionKeyPressed

    private void Add_food_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Add_food_idKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Add_food_idKeyPressed

    private void FoodManagementBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FoodManagementBTNMouseClicked
        FoodManagementBTN.setForeground(Color.RED);
        FoodOderBTN.setForeground(Color.BLACK);
        FoodPackageBTN.setForeground(Color.BLACK);
        
        Catering_Base.removeAll();
        Catering_Base.removeAll();
        Catering_Base.repaint();
        Catering_Base.revalidate();
        Catering_Base.add(Food_management);
        Catering_Base.repaint();
        Catering_Base.revalidate();
        txt_dynamic_title_bar.setText("Food & Catering - Food Management");
    }//GEN-LAST:event_FoodManagementBTNMouseClicked

    private void jButton24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton24MouseClicked
        insertFoodData();
    }//GEN-LAST:event_jButton24MouseClicked

    private void AddFoodTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddFoodTableMouseClicked
        foodTableClickedSet();
    }//GEN-LAST:event_AddFoodTableMouseClicked

    private void AddFoodTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddFoodTableMouseEntered
        tableautoRetrivewFood();
    }//GEN-LAST:event_AddFoodTableMouseEntered

    private void jButton21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseClicked
        foodUpdate();
    }//GEN-LAST:event_jButton21MouseClicked

    private void jButton23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MouseClicked
        clearingFoodFormAdd();
    }//GEN-LAST:event_jButton23MouseClicked

    private void jButton22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton22MouseClicked
        deleteFood_Items();
    }//GEN-LAST:event_jButton22MouseClicked

    private void jComboBoxFoodTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFoodTypeActionPerformed
        if (jComboBoxFoodType.getSelectedIndex() != 0) {
            Add_food_name.requestFocus();
            Add_food_name.setBackground(new Color(197, 232, 240));
            FoodWarningText.setText("");
        } else {
            FoodWarningText.setText("Please Select food menu");
            jComboBoxFoodType.setBackground(new Color(197, 232, 240));
        }
    }//GEN-LAST:event_jComboBoxFoodTypeActionPerformed

    private void ViewFoodTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewFoodTableMouseClicked
        seeAvailableFoodTableClicked();
    }//GEN-LAST:event_ViewFoodTableMouseClicked

    private void ViewFoodTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewFoodTableMouseEntered
        seefoodItemVieewTable();
    }//GEN-LAST:event_ViewFoodTableMouseEntered

    private void food_image_lable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_food_image_lable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_food_image_lable1MouseClicked

    private void Available_foodBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Available_foodBTNMouseClicked
        Available_foodBTN.setForeground(Color.RED);
        AddnewFoodBTN.setForeground(Color.BLACK);
        //reservationBTN.setForeground(Color.RED);
        addfoodbase.removeAll();
        addfoodbase.removeAll();
        addfoodbase.repaint();
        addfoodbase.revalidate();
        addfoodbase.add(viewfood);
        addfoodbase.repaint();
        addfoodbase.revalidate();
        txt_dynamic_title_bar.setText("Food & Catering - Available Food");
    }//GEN-LAST:event_Available_foodBTNMouseClicked

    private void AddnewFoodBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddnewFoodBTNMouseClicked
        //AddnewFoodBTN
        AddnewFoodBTN.setForeground(Color.RED);
        Available_foodBTN.setForeground(Color.BLACK);
        //guestRegistrationBTN.setForeground(new Color(240, 240, 240));
        //reservationBTN.setForeground(Color.RED);
        addfoodbase.removeAll();
        addfoodbase.removeAll();
        addfoodbase.repaint();
        addfoodbase.revalidate();
        addfoodbase.add(addfood1);
        addfoodbase.repaint();
        addfoodbase.revalidate();
        txt_dynamic_title_bar.setText("Food & Catering - Adding New Foods");
    }//GEN-LAST:event_AddnewFoodBTNMouseClicked

    private void FoodOderBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FoodOderBTNMouseClicked
        // TODO add your handling code here:orderFood
        FoodManagementBTN.setForeground(Color.BLACK);
        FoodOderBTN.setForeground(Color.RED);
        FoodPackageBTN.setForeground(Color.BLACK);
        
        Catering_Base.removeAll();
        Catering_Base.removeAll();
        Catering_Base.repaint();
        Catering_Base.revalidate();
        Catering_Base.add(orderFood);
        Catering_Base.repaint();
        Catering_Base.revalidate();
        txt_dynamic_title_bar.setText("Food & Catering - Oder foods");
    }//GEN-LAST:event_FoodOderBTNMouseClicked

    private void jComboBoxfoodmenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxfoodmenuMouseClicked
        if (jComboBoxfoodmenu.getSelectedIndex() == 0) {
            jComboBoxfoodmenu.requestFocus();
            JOptionPane.showMessageDialog(null, "Plese Select a Food menu");
        } else {
            int id = jComboBoxfoodmenu.getSelectedIndex();
            try {
                String qry = "select * from `menu` where `id` = '" + id + "' ";
                ps = conn.prepareStatement(qry);
                rs = ps.executeQuery();
                if (rs.next()) {
                    //menuimg
                    byte[] img = rs.getBytes("menuimg");
                    ImageIcon image = new ImageIcon(img);
                    Image im = image.getImage();
                    Image myImg = im.getScaledInstance(Foodmenulableout.getWidth(), Foodmenulableout.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon newImage = new ImageIcon(myImg);
                    Foodmenulableout.setIcon(newImage);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jComboBoxfoodmenuMouseClicked

    private void jComboBoxPackageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxPackageMouseClicked
        if (jComboBoxPackage.getSelectedIndex() == 0) {
            jComboBoxPackage.requestFocus();
            JOptionPane.showMessageDialog(null, "Plese Select a Food menu");
        } else {
            int id = jComboBoxPackage.getSelectedIndex();
            try {
                String qry = "select * from `package` where `id` = '" + id + "' ";
                ps = conn.prepareStatement(qry);
                rs = ps.executeQuery();
                if (rs.next()) {
                    //menuimg
                    byte[] img = rs.getBytes("packageimg");
                    ImageIcon image = new ImageIcon(img);
                    Image im = image.getImage();
                    Image myImg = im.getScaledInstance(Foodmenulableout.getWidth(), Foodmenulableout.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon newImage = new ImageIcon(myImg);
                    Foodmenulableout.setIcon(newImage);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jComboBoxPackageMouseClicked

    private void FoodPackageBTNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FoodPackageBTNKeyPressed

    }//GEN-LAST:event_FoodPackageBTNKeyPressed

    private void FoodPackageBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FoodPackageBTNMouseClicked
        FoodManagementBTN.setForeground(Color.BLACK);
        FoodOderBTN.setForeground(Color.BLACK);
        FoodPackageBTN.setForeground(Color.RED);
        
        Catering_Base.removeAll();
        Catering_Base.removeAll();
        Catering_Base.repaint();
        Catering_Base.revalidate();
        Catering_Base.add(foodPackage);
        Catering_Base.repaint();
        Catering_Base.revalidate();
        txt_dynamic_title_bar.setText("Food & Catering - Packages");
    }//GEN-LAST:event_FoodPackageBTNMouseClicked

    private void GuestIdTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuestIdTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_GuestIdTableMouseClicked

    private void GuestIdTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuestIdTableMouseEntered
        try {
            String qry = "select `guest_id`,`guest_name` from guest order by guest_id desc";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            GuestIdTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_GuestIdTableMouseEntered

    private void jButton18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseClicked
        if (jComboBoxPackage.getSelectedIndex() == 0) {
            jComboBoxPackage.requestFocus();
            JOptionPane.showMessageDialog(null, "Please select the Package");
        } else if (ViewFoodTable.getSelectedRowCount() >= 0) {
            try {
                int index = GuestIdTable.getSelectedRow();  //i changed the editing colume in jetable
                String Food_ID = (GuestIdTable.getModel().getValueAt(index, 0).toString());
                //  BillGuestID.setText(Food_ID);

                String paId = null;
                String pakageId = jComboBoxPackage.getSelectedItem().toString();
                switch (pakageId) {
                    case "Menu - 01":
                        paId = "1";
                        break;
                    case "Menu - 02":
                        paId = "2";
                        break;
                    case "Menu - 03":
                        paId = "3";
                        break;
                    case "Menu - 04":
                        paId = "4";
                        break;
                    default:
                        break;
                }
                String qry = "select `price` from `package` where `id` = '" + paId + "' ";
                ps = conn.prepareStatement(qry);
                rs = ps.executeQuery();
                if (rs.next()) {
                    double pPrice = rs.getDouble("price");
                    String outPrice = String.valueOf(pPrice);
                    ///      BillPriceInput.setText(outPrice);
                    Dynamic_Panel.removeAll();
                    Dynamic_Panel.removeAll();
                    Dynamic_Panel.repaint();
                    Dynamic_Panel.revalidate();
                    Dynamic_Panel.add(Billing_And_Report);
                    Dynamic_Panel.repaint();
                    Dynamic_Panel.revalidate();
                    txt_dynamic_title_bar.setText("Billing and Report Generating...");

                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "please select guest id from table " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select the guest Id");
        }

    }//GEN-LAST:event_jButton18MouseClicked

    private void roomInpriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roomInpriceKeyPressed
        char c = evt.getKeyChar(); //to validate the user input only allowed alphebets letters.
        if (Character.isDigit(c) || Character.isISOControl(c)) {
            roomInprice.setEditable(true);
            roomWarning.setText("");
        } else {
            roomInprice.setEditable(false);
            roomWarning.setText("Allowed only numbers ");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (roomInprice.getText().isEmpty()) {
                roomInprice.requestFocus();
                roomInprice.setBackground(new Color(197, 232, 240));
                roomWarning.setText("Please fillout the room Price");
            } else {
                roomInprice.setBackground(new Color(255, 255, 255));
                insertFoodData();
                roomWarning.setText("");
            }
        }

    }//GEN-LAST:event_roomInpriceKeyPressed

    private void AddRoomTableInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddRoomTableInMouseClicked
        //set data on feilds,
        jButton19.setEnabled(false);
        try {
            int index = AddRoomTableIn.getSelectedRow();  //i changed the editing colume in jetable
            String Room_ID = (AddRoomTableIn.getModel().getValueAt(index, 0).toString());

            String qry = "select * from `room` where `id` = '" + Room_ID + "' ";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            if (rs.next()) {
                roomIdin.setText(Room_ID);

                String type = rs.getString("type");
                jComboBoxRoomtype.setSelectedItem(type);

                String bedtype = rs.getString("bedtype");
                jComboBoxroomBedtype.setSelectedItem(bedtype);

                double price = rs.getDouble("price");
                String p = String.valueOf(price);
                roomInprice.setText(p);

                //close............................ 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_AddRoomTableInMouseClicked

    private void AddRoomTableInMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddRoomTableInMouseEntered
        showRoomDataon_jtable();
    }//GEN-LAST:event_AddRoomTableInMouseEntered

    private void jButton19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseClicked
        newRoomInsert();
        showRoomDataon_jtable();
        clearRoom_form();
    }//GEN-LAST:event_jButton19MouseClicked

    private void jButton26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton26MouseClicked
        jButton19.setEnabled(true);
        clearRoom_form();
        showRoomDataon_jtable();
    }//GEN-LAST:event_jButton26MouseClicked

    private void jButton20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseClicked
        updateRooms();
        showRoomDataon_jtable();
        clearRoom_form();
    }//GEN-LAST:event_jButton20MouseClicked

    private void jButton25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton25MouseClicked
        deletingRooms();
    }//GEN-LAST:event_jButton25MouseClicked

    private void jButtonAddHalls1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddHalls1MouseClicked
        //to show hall info to the table
        jButtonAddHalls1.setForeground(Color.RED);
        jButton16.setForeground(Color.BLACK);
        
        try {
            String qry = "select * from `hall` order by id desc";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            AddRoomTableIn.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButtonAddHalls1MouseClicked

    private void jButtonAddHalls1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddHalls1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAddHalls1ActionPerformed

    private void jButton16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseClicked
        jButtonAddHalls1.setForeground(Color.BLACK);
        jButton16.setForeground(Color.RED);
        
        
        txt_dynamic_title_bar.setText("Room and Hall management");
        RoomandHallBase.removeAll();
        RoomandHallBase.removeAll();
        RoomandHallBase.repaint();
        RoomandHallBase.revalidate();
        RoomandHallBase.add(addNewroom);
        RoomandHallBase.repaint();
        RoomandHallBase.revalidate();
    }//GEN-LAST:event_jButton16MouseClicked

    private void GuestIdTable_reservationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuestIdTable_reservationMouseClicked
        try {
            int index = GuestIdTable_reservation.getSelectedRow();  //i changed the editing colume in jetable
            String Rguest_id = (GuestIdTable_reservation.getModel().getValueAt(index, 0).toString());
            Reservation_Guest_Id_in.setText(Rguest_id);
            ReservationWarning.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_GuestIdTable_reservationMouseClicked

    private void GuestIdTable_reservationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuestIdTable_reservationMouseEntered
        try {
            String qry = "select `guest_id`,`guest_name` from guest order by guest_id desc";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            GuestIdTable_reservation.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_GuestIdTable_reservationMouseEntered

    private void ViewBookedRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewBookedRoomsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewBookedRoomsMouseClicked

    private void ViewBookedRoomsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewBookedRoomsMouseEntered
        //select * from room inner join reservation on room.id = reservation.room_id;
        try {
            String qry = "select * from room inner join reservation on room.id = reservation.room_id";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            ViewBookedRooms.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_ViewBookedRoomsMouseEntered

    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
        //for reservatiion
        confirm_Reservations();
        reservation_autoID();
    }//GEN-LAST:event_jButton15MouseClicked

    private void ReservationRoomAvbleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReservationRoomAvbleMouseClicked
        try {
            int index = ReservationRoomAvble.getSelectedRow();  //i changed the editing colume in jetable
            String Reroom_id = (ReservationRoomAvble.getModel().getValueAt(index, 0).toString());
            String price = (ReservationRoomAvble.getModel().getValueAt(index, 3).toString());
            Reservation_Room_id.setText(Reroom_id);
            Reservation_Price.setText(price);
            ReservationWarning.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_ReservationRoomAvbleMouseClicked

    private void ReservationRoomAvbleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReservationRoomAvbleMouseEntered
        try {
            String sql = "select id,type,bedtype,price from room left join reservation on id=room_id where room_id is null";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ReservationRoomAvble.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_ReservationRoomAvbleMouseEntered

    private void reserCountBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reserCountBtnMouseClicked

        java.util.Date d1 = DateIn.getDate();
        java.util.Date d2 = DateOut.getDate();

        long days1 = (d1.getTime() / (1000 * 60 * 60 * 24));
        long days2 = (d2.getTime() / (1000 * 60 * 60 * 24));
        long days = days1 - days2;

        if (days < 0) {
            days *= -1;
        }
        resrevation_staying_days.setText(Long.toString(days));

    }//GEN-LAST:event_reserCountBtnMouseClicked

    private void jButton28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton28MouseClicked
        clearing_Reservation_feilds();
        reservation_autoID();
    }//GEN-LAST:event_jButton28MouseClicked

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MouseClicked
        bill_base.removeAll();
        bill_base.removeAll();
        bill_base.repaint();
        bill_base.revalidate();
        bill_base.add(Reservation_bill);
        bill_base.repaint();
        bill_base.revalidate();
        txt_dynamic_title_bar.setText("Billing  - Reservation billing");
    }//GEN-LAST:event_jButton27MouseClicked

    private void jButton29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton29MouseClicked
        bill_base.removeAll();
        bill_base.removeAll();
        bill_base.repaint();
        bill_base.revalidate();
        bill_base.add(Food_order_bill);
        bill_base.repaint();
        bill_base.revalidate();
        txt_dynamic_title_bar.setText("Billing  - Food order billing");
    }//GEN-LAST:event_jButton29MouseClicked

    private void jButton30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton30MouseClicked
        bill_base.removeAll();
        bill_base.removeAll();
        bill_base.repaint();
        bill_base.revalidate();
        bill_base.add(check_out_bill);
        bill_base.repaint();
        bill_base.revalidate();
        txt_dynamic_title_bar.setText("Billing  - Fiannce reports");
    }//GEN-LAST:event_jButton30MouseClicked

    private void jButton31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton31MouseClicked
        bill_base.removeAll();
        bill_base.removeAll();
        bill_base.repaint();
        bill_base.revalidate();
        bill_base.add(bill_statistic);
        bill_base.repaint();
        bill_base.revalidate();
        txt_dynamic_title_bar.setText("Billing  - Fiannce & statistic");
    }//GEN-LAST:event_jButton31MouseClicked

    private void res_guest_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_guest_idKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_res_guest_idKeyPressed

    private void res_reservation_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_reservation_idKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_res_reservation_idKeyPressed

    private void res_staying_daysKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_staying_daysKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_res_staying_daysKeyPressed

    private void res_basic_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_basic_amountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_res_basic_amountKeyPressed

    private void res_guest_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_guest_nameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_res_guest_nameKeyPressed

    private void res_guest_addressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_guest_addressKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_res_guest_addressKeyPressed

    private void res_discountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_discountKeyPressed
        char c = evt.getKeyChar(); //to validate the user input only allowed digits letters.
        int count = res_discount.getText().length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' && count < 2 || Character.isISOControl(c)) {
            res_discount.setEditable(true);
            reservation_bill_warning.setText("");
        } else {
            res_discount.setEditable(false);
            reservation_bill_warning.setText("Allowed only numbers Discount percentage less than 100% ");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (res_discount.getText().isEmpty()) {
                res_discount.requestFocus();
                res_discount.setBackground(new Color(197, 232, 240));
                reservation_bill_warning.setText("Please fillout the Discount");
            } else {
                res_discount.setBackground(new Color(255, 255, 255));
                res_advance_amount.requestFocus();
                res_advance_amount.setBackground(new Color(197, 232, 240));
                reservation_bill_warning.setText("");
            }
        }
    }//GEN-LAST:event_res_discountKeyPressed

    private void res_taxesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_taxesKeyPressed
        char c = evt.getKeyChar(); //to validate the user input only allowed alphebets letters.
        int count = res_taxes.getText().length();

        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' && count < 2 || Character.isISOControl(c)) {
            res_taxes.setEditable(true);
            reservation_bill_warning.setText("");
        } else {
            res_taxes.setEditable(false);
            reservation_bill_warning.setText("Allowed only numbers and Taxes less than 100%");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (res_taxes.getText().isEmpty()) {
                res_taxes.requestFocus();
                res_taxes.setBackground(new Color(197, 232, 240));
                reservation_bill_warning.setText("Please fillout the TAX");
            } else {
                res_taxes.setBackground(new Color(255, 255, 255));
                res_discount.requestFocus();
                res_discount.setBackground(new Color(197, 232, 240));
                reservation_bill_warning.setText("");
            }
        }


    }//GEN-LAST:event_res_taxesKeyPressed

    private void res_advance_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_advance_amountKeyPressed
        char c = evt.getKeyChar(); //to validate the user input only allowed digits letters.

        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' || Character.isISOControl(c)) {
            res_advance_amount.setEditable(true);
            reservation_bill_warning.setText("");
        } else {
            res_advance_amount.setEditable(false);
            reservation_bill_warning.setText("Allowed only digits ..(0-9) ");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (res_advance_amount.getText().isEmpty()) {
                res_advance_amount.setBackground(new Color(255, 255, 255));
                res_Cash_on_hand.requestFocus();
                res_Cash_on_hand.setBackground(new Color(197, 232, 240));
                reservation_bill_warning.setText("With out paid Advance amount");
                getReservationTotal();
            } else {
                res_advance_amount.setBackground(new Color(255, 255, 255));
                res_Cash_on_hand.requestFocus();
                res_Cash_on_hand.setBackground(new Color(197, 232, 240));
                reservation_bill_warning.setText("");
                getReservationTotal();
            }
        }
    }//GEN-LAST:event_res_advance_amountKeyPressed

    private void res_total_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_total_amountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_res_total_amountKeyPressed

    private void res_guest_emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_guest_emailKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_res_guest_emailKeyPressed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton32MouseClicked
        getReservationTotal();
    }//GEN-LAST:event_jButton32MouseClicked

    private void res_Balance_resAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_Balance_resAmountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_res_Balance_resAmountKeyPressed

    private void res_Cash_on_handKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_Cash_on_handKeyPressed
        char c = evt.getKeyChar(); //to validate the user input only allowed digits letters.

        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' || Character.isISOControl(c)) {
            res_Cash_on_hand.setEditable(true);
            reservation_bill_warning.setText("");
        } else {
            res_Cash_on_hand.setEditable(false);
            reservation_bill_warning.setText("Allowed only digits ..(0-9) ");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (res_Cash_on_hand.getText().isEmpty()) {
                res_Cash_on_hand.requestFocus();
                res_Cash_on_hand.setBackground(new Color(197, 232, 240));
                reservation_bill_warning.setText("Please fillout the cash feild...!");

            } else {
                res_Cash_on_hand.setBackground(new Color(255, 255, 255));
                res_Cash_on_hand.requestFocus();
                reservation_bill_warning.setText("");
                setResBillDisplay();
            }
        }
    }//GEN-LAST:event_res_Cash_on_handKeyPressed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        if (!bill_display.getText().equals("")) {
            try {
                boolean complete = bill_display.print();
                if (complete) {
                    reservation_bill_warning.setText("Done Printing");
                    insert_ResBill();
                } else {
                    reservation_bill_warning.setText("Printing.....");
                }
            } catch (PrinterException | HeadlessException e) {
                reservation_bill_warning.setText("Print Error: " + e.getMessage());
            }
        } else {
            reservation_bill_warning.setText("Please give the cash amount and press enter!");
        }

    }//GEN-LAST:event_jButton33ActionPerformed

    private void res_bill_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_bill_idKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_res_bill_idKeyPressed

    private void jLabel85MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel85MouseClicked
        setResBillDisplay();
    }//GEN-LAST:event_jLabel85MouseClicked

    private void jButton34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MouseClicked
        //SEND RESERVATION BILL TO GUEST EMAIL ADDRESS.
        SendEmail se = new SendEmail();

        se.setCompany_Email("gamagehotel7@gmail.com");
        se.setC_password("GAMAGE@nest");
        se.setGuest_email(res_guest_email.getText());
        se.setE_subject("Gamage Nest & Rest Reservation Bill");
        se.setE_message(bill_display.getText());

        se.start();
    }//GEN-LAST:event_jButton34MouseClicked

    private void jButton35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton35MouseClicked
        deleteReservationing();
        reservation_autoID();
    }//GEN-LAST:event_jButton35MouseClicked

    private void ViewFooditem_oderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewFooditem_oderMouseClicked
        // TO set the name and price into the text feilds
        try {
            int index = ViewFooditem_oder.getSelectedRow();  //i changed the editing colume in jetable
            String foodnm = (ViewFooditem_oder.getModel().getValueAt(index, 1).toString());
            String unitprice = (ViewFooditem_oder.getModel().getValueAt(index, 2).toString());
            oder_food_name.setText(foodnm);
            oder_food_unit_price.setText(unitprice);
            oder_food_qty.requestFocus();
            oder_food_qty.setBackground(new Color(197, 232, 240));
            oder_food_warning.setText("please select type the quantity");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_ViewFooditem_oderMouseClicked

    private void ViewFooditem_oderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewFooditem_oderMouseEntered

    }//GEN-LAST:event_ViewFooditem_oderMouseEntered

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        try {
            String type = "Breakfast"; //
            String qry = "select `id`,`name`,`price` from food where `type` ='" + type + "'";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            ViewFooditem_oder.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
        try {
            String type = "Lunch"; //
            String qry = "select `id`,`name`,`price` from food where `type` ='" + type + "'";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            ViewFooditem_oder.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jRadioButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton3MouseClicked
        try {
            String type = "Dinner"; //
            String qry = "select `id`,`name`,`price` from food where `type` ='" + type + "'";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            ViewFooditem_oder.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jRadioButton3MouseClicked

    private void jRadioButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton4MouseClicked
        try {
            String type = "Drinks and Bevarage"; //
            String qry = "select `id`,`name`,`price` from food where `type` ='" + type + "'";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            ViewFooditem_oder.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jRadioButton4MouseClicked

    private void jRadioButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton5MouseClicked
        try {
            String qry = "select `id`,`name`,`price` from food";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            ViewFooditem_oder.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jRadioButton5MouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void ViewFooditem_cardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewFooditem_cardMouseClicked
        jToolBar1.setVisible(true);
        jPanelFood_order_confirm.setVisible(true);
        DefaultTableModel mode1 = (DefaultTableModel) ViewFooditem_card.getModel();
        int i = ViewFooditem_card.getSelectedRow();
        oder_food_name.setText(mode1.getValueAt(i, 0).toString());
        oder_food_qty.setText(mode1.getValueAt(i, 1).toString());
        oder_food_unit_price.setText(mode1.getValueAt(i, 2).toString());
        oder_food_total_price.setText(mode1.getValueAt(i, 3).toString());
    }//GEN-LAST:event_ViewFooditem_cardMouseClicked

    private void ViewFooditem_cardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewFooditem_cardMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewFooditem_cardMouseEntered

    private void oder_food_qtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_oder_food_qtyKeyPressed
        char c = evt.getKeyChar();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' || Character.isISOControl(c)) {
            oder_food_qty.setEditable(true);
            oder_food_warning.setText("");
        } else {
            oder_food_qty.setEditable(false);
            oder_food_warning.setText("digits only allowed (1 - 9)");
            oder_food_qty.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            set_oder_Sub_total();
            oder_food_qty.setBackground(Color.white);
        } else if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            oder_food_total_price.setText("");
        }
    }//GEN-LAST:event_oder_food_qtyKeyPressed

    private void oder_food_qtyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_oder_food_qtyFocusGained
        oder_food_qty.setText("");
    }//GEN-LAST:event_oder_food_qtyFocusGained

    public void insertTempOder() {
        try {                                                                                                         //,`validitiy`
            ps = conn.prepareStatement("INSERT INTO food_oder(`oder_id`,`guest_id`,`resrvation_id`,`items`,`qty`,`price`,`tatal`,`order_date`,`time`)values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, food_order_id.getText());
            ps.setString(2, Foodorder_guest_id_combo.getSelectedItem().toString());
            ps.setString(3, Foodorder_reservation_id_com.getSelectedItem().toString());
            ps.setString(4, oder_food_name.getText());
            ps.setString(5, oder_food_qty.getText());
            ps.setString(6, oder_food_unit_price.getText());
            ps.setString(7, oder_food_total_price.getText());
            ps.setString(8, DateLbl.getText());
            ps.setString(9, time_lbl.getText());
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private void jButton36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton36MouseClicked
        if (Foodorder_guest_id_combo.getSelectedIndex() == 0) {
            oder_food_warning.setText("Plese select the the guest id!");
            Foodorder_guest_id_combo.requestFocus();
        } else if ("".equals(oder_food_total_price.getText())) {
            oder_food_warning.setText("Plese set the quantity and press enter!");
        } else {
            if (!"".equals(oder_food_qty.getText())) {
                insertTempOder();

                foododeritemName.add(oder_food_name.getText());
                foododerquantity.add(oder_food_qty.getText());
                foododerItemPrice.add(oder_food_unit_price.getText());
                DefaultTableModel mode1 = (DefaultTableModel) ViewFooditem_card.getModel();
                mode1.addRow(new Object[]{
                    oder_food_name.getText(),
                    oder_food_qty.getText(),
                    oder_food_unit_price.getText(),
                    oder_food_total_price.getText()}
                );

                //food_order_FinalAmount
                int numrow = ViewFooditem_card.getRowCount();
                double tot = 0;
                for (int i = 0; i < numrow; i++) {
                    double val = Double.valueOf(ViewFooditem_card.getValueAt(i, 3).toString());
                    tot += val;
                }

                food_order_FinalAmount.setText(Double.toString(tot));

                oder_food_name.setText("");
                oder_food_qty.setText("");
                oder_food_total_price.setText("");
                oder_food_unit_price.setText("");
                oder_food_warning.setText("");
                jPanelFood_order_confirm.setVisible(true);
            } else {
                oder_food_warning.setText("Plese select the food item on table!");
            }
        }

    }//GEN-LAST:event_jButton36MouseClicked

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton38ActionPerformed
    static FOder fOder = new FOder();
    Double bHeight = 0.0;

    /**
     *
     * @param pj
     * @return
     */
    public PageFormat getPageFormat(PrinterJob pj) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = bHeight;
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    public class BillPrintable implements Printable {

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {

            int r = foododeritemName.size();
            ImageIcon icon = new ImageIcon("src/hotel_management_system/sjnLogo.png");
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    // int headerRectHeighta=40;

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    g2d.drawImage(icon.getImage(), 70, 70, 70, 30, rootPane);
                    y += yShift + 30;
                    g2d.drawString("-------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("         Food Order       ", 12, y);
                    y += yShift;
                    g2d.drawString("   Gamage Nest And Rest ", 12, y);
                    y += yShift;
                    g2d.drawString("   Gadduwa road, miriswatta ", 12, y);
                    y += yShift;
                    g2d.drawString("   Kamburupitiya ", 12, y);
                    y += yShift;
                    g2d.drawString("         0713288376      ", 12, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 12, y);
                    y += headerRectHeight;

                    g2d.drawString(" Item Name                  Quantity   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += headerRectHeight;

                    for (int s = 0; s < r; s++) {
                        g2d.drawString(" " + foododeritemName.get(s) + "               " + foododerquantity.get(s) + "", 10, y);
                        y += yShift;

                    }

                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                    g2d.drawString("       Please make it fast            ", 10, y);
                    y += yShift;
                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                    g2d.drawString("       SOFTWARE BY:SUJAN          ", 10, y);
                    y += yShift;

                } catch (Exception e) {
                    e.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }


    private void jButton40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton40MouseClicked
        try {//
            String inAmt = food_order_FinalAmount.getText();
            double inAmtis = Double.parseDouble(inAmt);
            ps = conn.prepareStatement("INSERT INTO `foodsaleincome`(`guest_id`,`reservation_id`,`date`,`amount`)values(?,?,?,?)");
            ps.setString(1, Foodorder_guest_id_combo.getSelectedItem().toString());
            ps.setString(2, Foodorder_reservation_id_com.getSelectedItem().toString());
            ps.setString(3, DateLbl.getText());
            ps.setDouble(4, inAmtis);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        try {
            UpdateQuery = "UPDATE `food_oder` SET `validitiy`=? WHERE `guest_id`='"
                    + Foodorder_guest_id_combo.getSelectedItem().toString()
                    + "' and `oder_id`='" + food_order_id.getText() + "'";
            ps = conn.prepareStatement(UpdateQuery);
            ps.setString(1, "true");
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(), getPageFormat(pj));
        try {
            pj.print();

        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton40MouseClicked

    private void jButton41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton41MouseClicked
        int numrow = ViewFooditem_card.getRowCount();
        if (numrow > 0) {
            display_foodOderBilling();
        } else {
            oder_food_warning.setText("Please check the card ");
        }
    }//GEN-LAST:event_jButton41MouseClicked

    private void jButton42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton42MouseClicked

        delete_food_oder_temp();
        Foodorder_guest_id_combo.setSelectedIndex(0);
        Foodorder_reservation_id_com.setSelectedIndex(0);
        food_order_FinalAmount.setText("");

        DefaultTableModel mode1 = (DefaultTableModel) ViewFooditem_oder.getModel();
        while (mode1.getRowCount() > 0) {
            for (int i = 0; i < mode1.getRowCount(); i++) {
                mode1.removeRow(i);
            }
        }

        DefaultTableModel mode2 = (DefaultTableModel) ViewFooditem_card.getModel();
        while (mode2.getRowCount() > 0) {
            for (int i = 0; i < mode2.getRowCount(); i++) {
                mode2.removeRow(i);
            }
        }
        oder_food_warning.setText("");
        oder_food_name.setText("");
        oder_food_qty.setText("");
        oder_food_total_price.setText("");
        oder_food_unit_price.setText("");
        buttonGroup1Food_order.clearSelection();
        FOOD_ORDERautoID();

    }//GEN-LAST:event_jButton42MouseClicked

    private void jButton37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton37MouseClicked
        DefaultTableModel mode1 = (DefaultTableModel) ViewFooditem_card.getModel();
        int i = ViewFooditem_card.getSelectedRow();
        if (i >= 0) {
            mode1.setValueAt(oder_food_name.getText(), i, 0);
            mode1.setValueAt(oder_food_qty.getText(), i, 1);
            mode1.setValueAt(oder_food_unit_price.getText(), i, 2);
            mode1.setValueAt(oder_food_total_price.getText(), i, 3);
            oder_food_warning.setText("");
            oder_food_name.setText("");
            oder_food_qty.setText("");
            oder_food_total_price.setText("");
            oder_food_unit_price.setText("");
        } else {
            oder_food_warning.setText("Something went wrrong please check the card!");
        }

    }//GEN-LAST:event_jButton37MouseClicked

    private void jButton38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton38MouseClicked
        int i = ViewFooditem_card.getSelectedRow();
        DefaultTableModel mode1 = (DefaultTableModel) ViewFooditem_card.getModel();
        if (i >= 0) {
            mode1.removeRow(i);
            oder_food_warning.setText("");
            oder_food_name.setText("");
            oder_food_qty.setText("");
            oder_food_total_price.setText("");
            oder_food_unit_price.setText("");
            jToolBar1.setVisible(false);
        } else {
            oder_food_warning.setText("Somthing went wrrong please check the card");
        }
    }//GEN-LAST:event_jButton38MouseClicked

    private void bgMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMouseDragged
//        int x = evt.getXOnScreen();
//        int y = evt.getYOnScreen();
//        this.setLocation(x - this.xMouse, y - this.yMouse);
    }//GEN-LAST:event_bgMouseDragged

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40ActionPerformed

    private void food_bill_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_food_bill_idKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_food_bill_idKeyPressed

    private void foodbill_Cash_on_handKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_foodbill_Cash_on_handKeyPressed
        char c = evt.getKeyChar(); //to validate the user input only allowed digits letters.
        int count = foodbill_Cash_on_hand.getText().length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' && count < 2 || Character.isISOControl(c)) {
            foodbill_Cash_on_hand.setEditable(true);
            food_oder_bill_warning.setText("");
        } else {
            foodbill_Cash_on_hand.setEditable(false);
            food_oder_bill_warning.setText("Allowed only digits ");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (foodbill_Cash_on_hand.getText().isEmpty()) {
                foodbill_Cash_on_hand.requestFocus();
                foodbill_Cash_on_hand.setBackground(new Color(197, 232, 240));
                food_oder_bill_warning.setText("Please fillout the amount feild...");
            } else {
                double cash = Double.parseDouble(foodbill_Cash_on_hand.getText());
                double amd = Double.parseDouble(foodbill_amount.getText());
                double balance = cash - amd;
                foodbill_balance.setText(String.valueOf(balance));
                foodbill_Cash_on_hand.setBackground(new Color(255, 255, 255));
                food_oder_bill_warning.setText("");
                //want to call food order display method
            }
        }
    }//GEN-LAST:event_foodbill_Cash_on_handKeyPressed

    private void jButton39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton39MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton39MouseClicked

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jLabel91MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel91MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel91MouseClicked

    private void food_bill_guest_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_food_bill_guest_idKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_food_bill_guest_idKeyPressed

    private void foodbill_guest_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_foodbill_guest_nameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_foodbill_guest_nameKeyPressed

    private void foodbill_guest_addressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_foodbill_guest_addressKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_foodbill_guest_addressKeyPressed

    private void foodbill_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_foodbill_amountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_foodbill_amountKeyPressed

    private void foodbill_balanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_foodbill_balanceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_foodbill_balanceKeyPressed

    private void foodbill_discountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_foodbill_discountKeyPressed
        char c = evt.getKeyChar(); //to validate the user input only allowed digits letters.
        int count = foodbill_discount.getText().length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' && count < 2 || Character.isISOControl(c)) {
            foodbill_discount.setEditable(true);
            food_oder_bill_warning.setText("");
        } else {
            foodbill_discount.setEditable(false);
            food_oder_bill_warning.setText("Allowed only numbers Discount percentage less than 100% ");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (foodbill_discount.getText().isEmpty()) {
                foodbill_discount.requestFocus();
                foodbill_discount.setBackground(new Color(197, 232, 240));
                food_oder_bill_warning.setText("Please fillout the Discount");
            } else {
                double fooddis = Double.parseDouble(foodbill_discount.getText());
                double amd = Double.parseDouble(foodbill_amount.getText());
                double totals = amd - (amd * fooddis / 100);
                foodbill_amount.setText(String.valueOf(totals));

                foodbill_discount.setBackground(new Color(255, 255, 255));
                foodbill_Cash_on_hand.requestFocus();
                foodbill_Cash_on_hand.setBackground(new Color(197, 232, 240));
                food_oder_bill_warning.setText("");
            }
        }
    }//GEN-LAST:event_foodbill_discountKeyPressed

    private void foodbill_taxesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_foodbill_taxesKeyPressed
        char c = evt.getKeyChar(); //to validate the user input only allowed alphebets letters.
        int count = foodbill_taxes.getText().length();

        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' && count < 2 || Character.isISOControl(c)) {
            foodbill_taxes.setEditable(true);
            food_oder_bill_warning.setText("");
        } else {
            foodbill_taxes.setEditable(false);
            food_oder_bill_warning.setText("Allowed only numbers and Taxes less than 100%");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (foodbill_taxes.getText().isEmpty()) {
                foodbill_taxes.requestFocus();
                foodbill_taxes.setBackground(new Color(197, 232, 240));
                food_oder_bill_warning.setText("Please fillout the TAX");
            } else {
                double foodbltax = Double.parseDouble(foodbill_taxes.getText());
                double amd = Double.parseDouble(foodbill_amount.getText());
                double totals = amd + (amd * foodbltax / 100);
                foodbill_amount.setText(String.valueOf(totals));

                foodbill_taxes.setBackground(new Color(255, 255, 255));
                foodbill_discount.requestFocus();
                foodbill_discount.setBackground(new Color(197, 232, 240));
                food_oder_bill_warning.setText("");
            }
        }
    }//GEN-LAST:event_foodbill_taxesKeyPressed

    private void ViewCheckoutsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewCheckoutsMouseClicked
        //to set check out data into text feilds
        try {
            int index = ViewCheckouts.getSelectedRow();  //i changed the editing colume in jetable
            String res1 = (ViewCheckouts.getModel().getValueAt(index, 0).toString());
            String guest1 = (ViewCheckouts.getModel().getValueAt(index, 3).toString());

            try {
                String qry = "select * from `guest` where `guest_id` = '" + guest1 + "' ";
                ps = conn.prepareStatement(qry);
                rs = ps.executeQuery(qry);
                if (rs.next()) {
                    guest_fullname1.setText(rs.getString("guest_name"));
                    guest_email_chout.setText(rs.getString("gemail"));

                }
                guest_id_chout.setText(guest1);
                res_id_chout.setText(res1);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_ViewCheckoutsMouseClicked

    private void ViewCheckoutsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewCheckoutsMouseEntered
        try {
            String today = DateLbl.getText();
            String qry = "SELECT `reservation_id`, `room_id`, `check_in`, `guest_id`, `amount` FROM `reservation` WHERE `check_out`='" + today + "'";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            ViewCheckouts.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_ViewCheckoutsMouseEntered

    private void guest_fullname1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guest_fullname1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_guest_fullname1KeyPressed

    private void guest_id_choutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guest_id_choutKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_guest_id_choutKeyPressed

    private void guest_email_choutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guest_email_choutKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_guest_email_choutKeyPressed

    private void todayReservationBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_todayReservationBTNMouseClicked
        reservationBTN.setForeground(Color.BLACK);
        reservationUpdateBTN.setForeground(Color.BLACK);
        guestRegistrationBTN.setForeground(Color.BLACK);
        todayReservationBTN.setForeground(Color.RED);

        frontDeskBase.removeAll();
        frontDeskBase.removeAll();
        frontDeskBase.repaint();
        frontDeskBase.revalidate();
        frontDeskBase.add(todayCheckOuts);
        frontDeskBase.repaint();
        frontDeskBase.revalidate();
        txt_dynamic_title_bar.setText("Front Desk - Guest Reservation.");
    }//GEN-LAST:event_todayReservationBTNMouseClicked

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        if (jCheckBox1.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "cam already opened!");
        } else {
            try {
                userTCPic.openCamera();
                jCheckBox1.setSelected(true);
                jCheckBox1.setText("Camera is On");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton46ActionPerformed
    Camera userTCPic = new Camera();
    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        if (jCheckBox1.isSelected() == true) {
            try {
                Camera capture2 = new Camera();
                capture2.tackCaptureUser();
                userTCPic.frame.hide();
                jCheckBox1.setSelected(false);
                jCheckBox1.setText("Camera is Off");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            try {
                userTCPic.setNewUserImage();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please turn on the camera..");
        }
    }//GEN-LAST:event_jButton47ActionPerformed

    private void checkout_guest_idinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkout_guest_idinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkout_guest_idinKeyPressed

    private void checkout_reservation_idinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkout_reservation_idinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkout_reservation_idinKeyPressed

    private void checkout_guest_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkout_guest_nameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkout_guest_nameKeyPressed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
       if (!bill_display2.getText().equals("")) {
            try {
                boolean complete = bill_display2.print();
                if (complete) {
                    final_billError.setText("Done Printing");
                    insert_ResBill();
                    //to reservation delete function
                    try {
                        String gsID= checkout_guest_idin.getText();
                        String RsId =checkout_reservation_idin.getText();
                        String qry ="DELETE FROM `reservation` WHERE `reservation_id` = '"+RsId+"' AND `guest_id` = '"+gsID+"'";
                        stmt = conn.prepareStatement(qry);
                        stmt.executeUpdate(qry);
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                } else {
                    final_billError.setText("Printing.....");
                }
            } catch (PrinterException | HeadlessException e) {
                final_billError.setText("Print Error: " + e.getMessage());
            }
        } else {
            final_billError.setText("Please give the cash amount and press enter!");
        }
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton50MouseClicked
      //SEND CHECKOUT BILL TO GUEST EMAIL ADDRESS.
        SendEmail se = new SendEmail();

        se.setCompany_Email("gamagehotel7@gmail.com");
        se.setC_password("GAMAGE@nest");
        se.setGuest_email(checkout_guest_email.getText());
        se.setE_subject("Gamage Nest & Rest Checkout bill");
        se.setE_message(bill_display2.getText());
        se.start();
    }//GEN-LAST:event_jButton50MouseClicked

    private void res_Cash_on_hand1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_Cash_on_hand1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_res_Cash_on_hand1KeyPressed

    private void jLabel109MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel109MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel109MouseClicked

    private void checkout_bill_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkout_bill_idKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkout_bill_idKeyPressed

    private void checkout_guest_emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkout_guest_emailKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkout_guest_emailKeyPressed

    private void checkout_res_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkout_res_amountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkout_res_amountKeyPressed

    private void checkout_order_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkout_order_amountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkout_order_amountKeyPressed

    private void checkout_total_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkout_total_amountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkout_total_amountKeyPressed

    private void checkout_total_amount1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkout_total_amount1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkout_total_amount1KeyPressed

    private void jButton44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton44MouseClicked
        if (guest_fullname1.getText().equals("") || guest_id_chout.getText().equals("") || guest_email_chout.getText().equals("")) {
            today_ch_warning.setText("Please select the checkout list from the table!");
        } else {
            //send email remember to guest email
            String msg = "Dear " + guest_fullname1.getText() + "!\n"
                    + DateLbl.getText() + "  is your checkout day..! \n"
                    + "If you wish to extend the checkout days please meet our receptinist.\n"
                    + "Thank you for your comming!\n"
                    + "Gamage Nest and Rest\n"
                    + "email : gamagehotel7@gmail.com\n"
                    + "Tel : +94 71 32 88 376";

            SendEmail se = new SendEmail();

            se.setCompany_Email("gamagehotel7@gmail.com");
            se.setC_password("GAMAGE@nest");
            se.setGuest_email(guest_email_chout.getText());
            se.setE_subject("Gamage Nest & Checkout Remember...");
            se.setE_message(msg);

            se.start();
        }
    }//GEN-LAST:event_jButton44MouseClicked

    private void jButton45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton45MouseClicked
        // TODAY CHECK OUTS AND SET FINAL BILLING....
        if (guest_fullname1.getText().equals("") || guest_id_chout.getText().equals("") || guest_email_chout.getText().equals("")) {
            today_ch_warning.setText("Please select the checkout list from the table!");
        } else {
            String guest_checkous = guest_id_chout.getText();
            String guest_res_id_checkout = res_id_chout.getText();
            //SELECT * FROM `reservationbill`
            checkout_guest_idin.setText(guest_checkous);
            checkout_reservation_idin.setText(guest_res_id_checkout);

            Dynamic_Panel.removeAll();
            Dynamic_Panel.removeAll();
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            Dynamic_Panel.add(Billing_And_Report.add(check_out_bill));
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            txt_dynamic_title_bar.setText("Checkout and billing...");
        }

    }//GEN-LAST:event_jButton45MouseClicked

    private void res_id_choutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_res_id_choutKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_res_id_choutKeyPressed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        //jPanelFinalBillingpart.setVisible(true);
        if (checkout_guest_idin.getText().equals("") || checkout_reservation_idin.getText().equals("")) {
            final_billError.setText("Please select the guest id and reservation id");
        } else {
            String guest_final = checkout_guest_idin.getText();
            String reservatiion_fl_id = checkout_reservation_idin.getText();
            try {
                //geting food oder total amounts...
                String qry = "SELECT  sum(amount) FROM `foodsaleincome` WHERE `guest_id` = " + guest_final + " AND `reservation_id` =" + reservatiion_fl_id;
                ps = conn.prepareStatement(qry);
                rs = ps.executeQuery();
                if (rs.next()) {
                    checkout_order_amount.setText(rs.getString("sum(amount)"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            try {
                //getting reservation payable amounts...
                String qry = "SELECT `payable_amount` FROM `reservationbill` WHERE `reservation_id` =" + reservatiion_fl_id + " AND `guest_id` =" + guest_final;
                ps = conn.prepareStatement(qry);
                rs = ps.executeQuery();
                if (rs.next()) {
                    checkout_res_amount.setText(rs.getString("sum(amount)"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            try {
                String chAm = checkout_res_amount.getText();
                String OdrAm = checkout_order_amount.getText();
                double cha = Double.parseDouble(chAm);
                double odm = Double.parseDouble(OdrAm);
                double tl = cha + odm;
                String tlAm = String.valueOf(tl);
                checkout_total_amount.setText(tlAm);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            try {
                String qry = "SELECT  `guest_name`, `gemail` FROM `guest` WHERE `guest_id`=" + guest_final;
                ps = conn.prepareStatement(qry);
                rs = ps.executeQuery();
                if (rs.next()) {
                    checkout_guest_name.setText(rs.getString("guest_name"));
                    checkout_guest_email.setText(rs.getString("gemail"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        //res_Cash_on_hand1
        if (res_Cash_on_hand1.getText().equals("")) {
            final_billError.setText("Please fillout the important feilds...!");
        } else {
            String choh = res_Cash_on_hand1.getText();
            String tla = checkout_total_amount.getText();
            double cashOnH = Double.parseDouble(choh);
            double tlam = Double.parseDouble(tla);
            double blce = cashOnH - tlam;
            String FlBlce = String.valueOf(blce);
            checkout_total_amount1.setText(FlBlce);
        }
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        //to display bill on screen
        if (res_Cash_on_hand1.getText().equals("") || checkout_guest_idin.getText().equals("")
                || checkout_reservation_idin.getText().equals("")) {
            final_billError.setText("Please fillout the important feilds...!");
        } else {
            String billDate = DateLbl.getText();
            String billTime = time_lbl.getText();
            String fbill_id = checkout_bill_id.getText();
            String fl_guest_name = checkout_guest_name.getText();
            String fl_res_amount = checkout_res_amount.getText();
            String fl_food_oder_amt = checkout_order_amount.getText();
            String fl_total = checkout_total_amount.getText();
            String fl_cash_on = res_Cash_on_hand1.getText();
            String fl_blce = checkout_total_amount1.getText();

            bill_display2.append(""
                    + "=================================\n"
                    + "        GAMAGE NEST & REST       \n"
                    + "      Gadhuwa road Mirriswatta,  \n"
                    + "           Kamburupitiya.        \n"
                    + "Bill no:" + fbill_id + " Tel:+94 71 810 5051\n"
                    + "=================================\n"
                    + "Date:" + billDate + "\tTime:" + billTime + "\n"
                    + "=================================\n"
                    + "Guest Name:" + fl_guest_name + "      \n"
                    + "=================================\n"
                    + "Reservatiion Amount...." + fl_res_amount + "\n"
                    + "Food & Other Charge...." + fl_food_oder_amt + "\n"
                    + "Total Amount .........." + fl_total + "\n"
                    + "=================================\n"
                    + "Cash on hand..........." + fl_cash_on + "\n"
                    + "Balance Amount........." + fl_blce + "\n"
                    + "=================================\n"
                    + "\tTHANK YOU FOR YOUR VISITING!...\n"
                    + "     gamagehotel7@gmail.com      \n"
                    + "=================================");

            try {
                //inserting final billing
                String fl_gid = checkout_guest_idin.getText();
                double Fl_amd = Double.parseDouble(fl_total);
                ps = conn.prepareStatement("INSERT INTO `bill`(`fbill_id`, `guest_id`, `description`, `date`, `amount`) VALUES (?,?,?,?,?)");
                ps.setString(1, fbill_id);
                ps.setString(2, fl_gid);
                ps.setString(3, "reseration and services");
                ps.setString(4, billDate);
                ps.setDouble(5, Fl_amd);
                ps.executeUpdate();
            } catch (NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jLabel117MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel117MouseClicked
      this.dispose();
      new loginform().setVisible(true);
    }//GEN-LAST:event_jLabel117MouseClicked

    private void guestRegistrationBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestRegistrationBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guestRegistrationBTNActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main_Window().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel Account_settings;
    public static javax.swing.JTable AddFoodTable;
    public static javax.swing.JTable AddRoomTableIn;
    public static javax.swing.JTextField Add_food_description;
    public static javax.swing.JTextField Add_food_id;
    public static javax.swing.JTextField Add_food_name;
    public static javax.swing.JTextField Add_food_price;
    private javax.swing.JButton AddnewFoodBTN;
    private javax.swing.JLabel Address;
    private javax.swing.JLabel Address1;
    private javax.swing.JLabel Address2;
    private javax.swing.JLabel Address3;
    private javax.swing.JLabel Address4;
    private javax.swing.JButton Available_foodBTN;
    public static javax.swing.JPanel Billing_And_Report;
    public static javax.swing.JPanel Camera_panel1;
    public static javax.swing.JPanel Catering_Base;
    public static javax.swing.JPanel Catering_and_food;
    public static com.toedter.calendar.JDateChooser DateIn;
    public static javax.swing.JLabel DateLbl;
    public static com.toedter.calendar.JDateChooser DateOut;
    public static javax.swing.JPanel Developers;
    public static javax.swing.JPanel Dynamic_Panel;
    private javax.swing.JPanel Dynamic_title_bar;
    private javax.swing.JButton FoodManagementBTN;
    private javax.swing.JButton FoodOderBTN;
    private javax.swing.JButton FoodPackageBTN;
    public static javax.swing.JLabel FoodWarningText;
    public static javax.swing.JPanel Food_management;
    public static javax.swing.JPanel Food_order_bill;
    public static javax.swing.JLabel Foodmenulableout;
    public static javax.swing.JComboBox<String> Foodorder_guest_id_combo;
    public static javax.swing.JComboBox<String> Foodorder_reservation_id_com;
    public static javax.swing.JPanel Frontdest;
    public static javax.swing.JTable GuestIdTable;
    public static javax.swing.JTable GuestIdTable_reservation;
    private javax.swing.JPanel GuestRegistartion;
    private javax.swing.JPanel Guest_Statictics;
    public static javax.swing.JPanel House_keeping;
    public static javax.swing.JPanel Inventory;
    public static javax.swing.JTable ReservationRoomAvble;
    public static javax.swing.JLabel ReservationWarning;
    public static javax.swing.JTextField Reservation_Guest_Id_in;
    public static javax.swing.JTextField Reservation_Id_in;
    public static javax.swing.JTextField Reservation_Price;
    public static javax.swing.JTextField Reservation_Room_id;
    public static javax.swing.JPanel Reservation_bill;
    public static javax.swing.JPanel RoomandHall;
    private javax.swing.JPanel RoomandHallBase;
    public static javax.swing.JLabel User_image_lbl;
    public static javax.swing.JTable ViewBookedRooms;
    public static javax.swing.JTable ViewCheckouts;
    public static javax.swing.JTable ViewFoodTable;
    public static javax.swing.JTable ViewFooditem_card;
    public static javax.swing.JTable ViewFooditem_oder;
    public javax.swing.JPanel account_base;
    private javax.swing.JPanel addNewroom;
    public static javax.swing.JPanel addfood1;
    public static javax.swing.JPanel addfoodbase;
    private javax.swing.JPanel bg;
    public static javax.swing.JPanel bill_base;
    public static javax.swing.JTextArea bill_display;
    public static javax.swing.JTextArea bill_display1;
    public static javax.swing.JTextArea bill_display2;
    public static javax.swing.JPanel bill_statistic;
    public static javax.swing.JPanel btn_account_setting;
    public static javax.swing.JPanel btn_bill_and_report;
    public static javax.swing.JPanel btn_catering;
    public static javax.swing.JPanel btn_development;
    public static javax.swing.JPanel btn_front_desk;
    public static javax.swing.JPanel btn_housekeeping;
    public static javax.swing.JPanel btn_inventory;
    public static javax.swing.JPanel btn_room_and_hall;
    private javax.swing.ButtonGroup buttonGroup1Food_order;
    public javax.swing.JPanel change_password;
    private javax.swing.JButton changepwdBTN;
    public static javax.swing.JPanel check_out_bill;
    private javax.swing.JComboBox<String> check_out_guest_id;
    private javax.swing.JComboBox<String> check_out_reservatiion_id;
    public static javax.swing.JTextField checkout_bill_id;
    public static javax.swing.JTextField checkout_guest_email;
    public static javax.swing.JTextField checkout_guest_idin;
    public static javax.swing.JTextField checkout_guest_name;
    public static javax.swing.JTextField checkout_order_amount;
    public static javax.swing.JTextField checkout_res_amount;
    public static javax.swing.JTextField checkout_reservation_idin;
    public static javax.swing.JTextField checkout_total_amount;
    public static javax.swing.JTextField checkout_total_amount1;
    public static javax.swing.JCheckBox comEditCh;
    public javax.swing.JPanel create_newuser_form;
    private javax.swing.JButton createuserBTN;
    private javax.swing.JTextField final_billError;
    public static javax.swing.JPanel foodPackage;
    public static javax.swing.JTextField food_bill_guest_id;
    public static javax.swing.JTextField food_bill_id;
    public static javax.swing.JLabel food_description_out;
    public static javax.swing.JLabel food_image_lable;
    public static javax.swing.JLabel food_image_lable1;
    public static javax.swing.JLabel food_name_out;
    public static javax.swing.JTextField food_oder_bill_warning;
    public static javax.swing.JTextField food_order_FinalAmount;
    public static javax.swing.JTextField food_order_id;
    public static javax.swing.JLabel food_price_out;
    public static javax.swing.JLabel food_price_out1;
    public static javax.swing.JLabel food_type_out;
    public static javax.swing.JTextField foodbill_Cash_on_hand;
    public static javax.swing.JTextField foodbill_amount;
    public static javax.swing.JTextField foodbill_balance;
    public static javax.swing.JTextField foodbill_discount;
    public static javax.swing.JTextField foodbill_guest_address;
    public static javax.swing.JTextField foodbill_guest_name;
    public static javax.swing.JTextField foodbill_taxes;
    private javax.swing.JPanel frontDeskBase;
    private javax.swing.JButton guestRegistrationBTN;
    public static javax.swing.JTextField guest_address;
    public static javax.swing.JTextField guest_email;
    public static javax.swing.JTextField guest_email_chout;
    public static javax.swing.JTextField guest_fullname;
    public static javax.swing.JTextField guest_fullname1;
    public static javax.swing.JTextField guest_id;
    public static javax.swing.JTextField guest_id_chout;
    public static javax.swing.JTextField guest_identitity;
    public static javax.swing.JLabel guest_in_image;
    public static javax.swing.JTextField guest_purpose_visit;
    public static javax.swing.JTextField guest_tel;
    public static javax.swing.JLabel guest_warning_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    public static javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    public static javax.swing.JButton jButton6;
    public static javax.swing.JButton jButton7;
    public static javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonAddHalls1;
    public static javax.swing.JButton jButton_user_profile_save;
    private javax.swing.JCheckBox jCheckBox1;
    public static javax.swing.JCheckBox jCheckBoxCamereCheck;
    public static javax.swing.JComboBox<String> jComboAc_type;
    public static javax.swing.JComboBox<String> jComboBoxFoodType;
    public static javax.swing.JComboBox<String> jComboBoxPackage;
    public static javax.swing.JComboBox<String> jComboBoxRoomtype;
    public static javax.swing.JComboBox<String> jComboBoxfoodmenu;
    public static javax.swing.JComboBox<String> jComboBoxroomBedtype;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    public javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    public javax.swing.JLabel jLabel81;
    public javax.swing.JLabel jLabel82;
    public javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    public javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    public javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    public javax.swing.JLabel jLabel97;
    public javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLabelpwdswarnning;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelFinalBillingpart;
    public static javax.swing.JPanel jPanelFood_order_confirm;
    public static javax.swing.JPasswordField jPassword_change_confirm;
    public static javax.swing.JPasswordField jPassword_change_new;
    public static javax.swing.JPasswordField jPassword_newUser;
    public static javax.swing.JRadioButton jRadioButton1;
    public static javax.swing.JRadioButton jRadioButton2;
    public static javax.swing.JRadioButton jRadioButton3;
    public static javax.swing.JRadioButton jRadioButton4;
    public static javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator10;
    public javax.swing.JSeparator jSeparator11;
    public javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    public javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    public javax.swing.JSeparator jSeparator26;
    public javax.swing.JSeparator jSeparator27;
    public javax.swing.JSeparator jSeparator28;
    public javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JSeparator jSeparator32;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator35;
    private javax.swing.JSeparator jSeparator36;
    private javax.swing.JSeparator jSeparator37;
    private javax.swing.JSeparator jSeparator38;
    private javax.swing.JSeparator jSeparator39;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator40;
    private javax.swing.JSeparator jSeparator41;
    private javax.swing.JSeparator jSeparator42;
    private javax.swing.JSeparator jSeparator43;
    private javax.swing.JSeparator jSeparator44;
    private javax.swing.JSeparator jSeparator45;
    private javax.swing.JSeparator jSeparator46;
    private javax.swing.JSeparator jSeparator47;
    private javax.swing.JSeparator jSeparator48;
    private javax.swing.JSeparator jSeparator49;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator50;
    private javax.swing.JSeparator jSeparator51;
    private javax.swing.JSeparator jSeparator52;
    private javax.swing.JSeparator jSeparator53;
    private javax.swing.JSeparator jSeparator54;
    private javax.swing.JSeparator jSeparator55;
    private javax.swing.JToolBar.Separator jSeparator56;
    private javax.swing.JSeparator jSeparator57;
    private javax.swing.JSeparator jSeparator58;
    private javax.swing.JSeparator jSeparator59;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator60;
    private javax.swing.JSeparator jSeparator61;
    private javax.swing.JSeparator jSeparator62;
    private javax.swing.JSeparator jSeparator63;
    private javax.swing.JSeparator jSeparator64;
    private javax.swing.JSeparator jSeparator65;
    private javax.swing.JSeparator jSeparator66;
    private javax.swing.JSeparator jSeparator67;
    private javax.swing.JSeparator jSeparator68;
    private javax.swing.JSeparator jSeparator69;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator70;
    private javax.swing.JSeparator jSeparator71;
    private javax.swing.JSeparator jSeparator72;
    private javax.swing.JSeparator jSeparator73;
    private javax.swing.JSeparator jSeparator74;
    private javax.swing.JSeparator jSeparator75;
    private javax.swing.JSeparator jSeparator76;
    private javax.swing.JSeparator jSeparator77;
    private javax.swing.JSeparator jSeparator78;
    private javax.swing.JSeparator jSeparator79;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator80;
    private javax.swing.JSeparator jSeparator81;
    private javax.swing.JSeparator jSeparator82;
    private javax.swing.JSeparator jSeparator9;
    public static javax.swing.JTable jTableGuest;
    public static javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    public static javax.swing.JLabel lbl_userType;
    public static javax.swing.JTextField new_user_addres1_in;
    public static javax.swing.JTextField new_user_addres2_in;
    public static javax.swing.JTextField new_user_fullname_in;
    public static javax.swing.JTextField new_user_nic_in;
    public static javax.swing.JLabel new_user_warning_text;
    public static javax.swing.JTextField new_username_in;
    public static javax.swing.JTextField oder_food_name;
    public static javax.swing.JTextField oder_food_qty;
    public static javax.swing.JTextField oder_food_total_price;
    public static javax.swing.JTextField oder_food_unit_price;
    public static javax.swing.JTextField oder_food_warning;
    public static javax.swing.JPanel orderFood;
    private javax.swing.JButton profileBTN;
    public static javax.swing.JLabel profile_warning_text1;
    public static javax.swing.JTextField res_Balance_resAmount;
    public static javax.swing.JTextField res_Cash_on_hand;
    public static javax.swing.JTextField res_Cash_on_hand1;
    public static javax.swing.JTextField res_advance_amount;
    public static javax.swing.JTextField res_basic_amount;
    public static javax.swing.JTextField res_bill_id;
    public static javax.swing.JTextField res_discount;
    public static javax.swing.JTextField res_guest_address;
    public static javax.swing.JTextField res_guest_email;
    public static javax.swing.JTextField res_guest_id;
    public static javax.swing.JTextField res_guest_name;
    public static javax.swing.JTextField res_id_chout;
    public static javax.swing.JTextField res_reservation_id;
    public static javax.swing.JTextField res_staying_days;
    public static javax.swing.JTextField res_taxes;
    public static javax.swing.JTextField res_total_amount;
    public static javax.swing.JButton reserCountBtn;
    public static javax.swing.JPanel reservation;
    private javax.swing.JButton reservationBTN;
    private javax.swing.JButton reservationUpdateBTN;
    public static javax.swing.JTextField reservation_bill_warning;
    public static javax.swing.JTextField resrevation_staying_days;
    public static javax.swing.JTextField roomIdin;
    public static javax.swing.JTextField roomInprice;
    public static javax.swing.JLabel roomWarning;
    private javax.swing.JPanel side_Panel;
    public static javax.swing.JLabel time_lbl;
    private javax.swing.JPanel todayCheckOuts;
    private javax.swing.JButton todayReservationBTN;
    private javax.swing.JTextField today_ch_warning;
    public static javax.swing.JLabel txt_dynamic_title_bar;
    public static javax.swing.JLabel userNamelbl;
    public static javax.swing.JTextField user_password_username;
    public javax.swing.JPanel user_profile;
    public static javax.swing.JTextField user_profile_addres1;
    public static javax.swing.JTextField user_profile_addres2;
    public static javax.swing.JTextField user_profile_fullname;
    public static javax.swing.JLabel user_profile_image;
    public static javax.swing.JTextField user_profile_nic;
    public static javax.swing.JTextField user_profile_type;
    public static javax.swing.JTextField user_profile_username;
    public static javax.swing.JPanel viewfood;
    // End of variables declaration//GEN-END:variables
}
