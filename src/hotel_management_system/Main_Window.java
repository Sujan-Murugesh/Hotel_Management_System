package hotel_management_system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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

    public Main_Window() {
        initComponents();
        setDateandTime();
        conn = Db_connection.connect();
        autoId();
        Camera_panel1.setVisible(false);
        tableHead();

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        food_price_out1 = new javax.swing.JLabel();
        bg = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        DateLbl = new javax.swing.JLabel();
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
        Inventory = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        Frontdest = new javax.swing.JPanel();
        guestRegistrationBTN = new javax.swing.JButton();
        reservationBTN = new javax.swing.JButton();
        GuestStaticsBTN = new javax.swing.JButton();
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
        Guest_Statictics = new javax.swing.JPanel();
        Room_and_hallbooking = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jSeparator16 = new javax.swing.JSeparator();
        Room_Base = new javax.swing.JPanel();
        Room_manage = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        Room_manage1 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        Catering_and_food = new javax.swing.JPanel();
        FoodManagementBTN = new javax.swing.JButton();
        FoodPackageBTN = new javax.swing.JButton();
        jSeparator30 = new javax.swing.JSeparator();
        FoodManagementBTN1 = new javax.swing.JButton();
        Catering_Base = new javax.swing.JPanel();
        foodPackage = new javax.swing.JPanel();
        jComboBoxfoodmenu = new javax.swing.JComboBox<>();
        Foodmenulableout = new javax.swing.JLabel();
        jComboBoxPackage = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        GuestIdTable = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        orderFood = new javax.swing.JPanel();
        Food_management = new javax.swing.JPanel();
        AddnewFoodBTN = new javax.swing.JButton();
        Available_foodBTN = new javax.swing.JButton();
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
        House_keeping = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        Developers = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
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
        Billing_And_Report = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        BillGuestID = new javax.swing.JLabel();
        BillPriceInput = new javax.swing.JLabel();
        time_lbl = new javax.swing.JLabel();

        food_price_out1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        bg.setMaximumSize(new java.awt.Dimension(1332, 721));

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

        side_Panel.setBackground(new java.awt.Color(0, 0, 0));

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
        jLabel12.setText("Front Desk & Transport ");
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

        userNamelbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        userNamelbl.setForeground(new java.awt.Color(255, 255, 255));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(lbl_userType, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(userNamelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Dynamic_title_barLayout.setVerticalGroup(
            Dynamic_title_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userNamelbl, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(txt_dynamic_title_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_userType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Dynamic_Panel.setBackground(new java.awt.Color(153, 153, 153));
        Dynamic_Panel.setLayout(new java.awt.CardLayout());

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
                .addContainerGap(450, Short.MAX_VALUE))
        );
        InventoryLayout.setVerticalGroup(
            InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryLayout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(385, Short.MAX_VALUE))
        );

        Dynamic_Panel.add(Inventory, "card2");

        Frontdest.setBackground(new java.awt.Color(255, 255, 255));

        guestRegistrationBTN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        guestRegistrationBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/adduser.png"))); // NOI18N
        guestRegistrationBTN.setText("Guest Registration");
        guestRegistrationBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guestRegistrationBTN.setIconTextGap(40);
        guestRegistrationBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guestRegistrationBTNMouseClicked(evt);
            }
        });

        reservationBTN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        reservationBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/reservatining.png"))); // NOI18N
        reservationBTN.setText("Recervation");
        reservationBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reservationBTN.setIconTextGap(30);
        reservationBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reservationBTNMouseClicked(evt);
            }
        });

        GuestStaticsBTN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        GuestStaticsBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/staticstics.png"))); // NOI18N
        GuestStaticsBTN.setText("Statistics");
        GuestStaticsBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GuestStaticsBTN.setIconTextGap(30);
        GuestStaticsBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GuestStaticsBTNMouseClicked(evt);
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

        Address3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
        guest_in_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guest_in_imageMouseClicked(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel43.setText("Guest Name       ");

        guest_warning_text.setBackground(new java.awt.Color(255, 255, 255));
        guest_warning_text.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        guest_warning_text.setForeground(new java.awt.Color(255, 0, 0));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setText("Identitity  ");

        Address4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel44.setText("Telephone");

        guest_identitity.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        guest_identitity.setBorder(null);
        guest_identitity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guest_identitityKeyPressed(evt);
            }
        });

        jSeparator22.setBackground(new java.awt.Color(0, 0, 0));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GuestRegistartionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GuestRegistartionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                        .addComponent(jLabel26)
                        .addGap(129, 129, 129))))
        );
        GuestRegistartionLayout.setVerticalGroup(
            GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GuestRegistartionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, GuestRegistartionLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(GuestRegistartionLayout.createSequentialGroup()
                        .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(guest_in_image, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                .addComponent(Camera_panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(GuestRegistartionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        frontDeskBase.add(GuestRegistartion, "card2");

        reservation.setBackground(new java.awt.Color(255, 255, 255));
        reservation.setMaximumSize(new java.awt.Dimension(1004, 548));

        jLabel41.setText("guest registration");

        javax.swing.GroupLayout reservationLayout = new javax.swing.GroupLayout(reservation);
        reservation.setLayout(reservationLayout);
        reservationLayout.setHorizontalGroup(
            reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationLayout.createSequentialGroup()
                .addGap(439, 439, 439)
                .addComponent(jLabel41)
                .addContainerGap(480, Short.MAX_VALUE))
        );
        reservationLayout.setVerticalGroup(
            reservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationLayout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jLabel41)
                .addContainerGap(348, Short.MAX_VALUE))
        );

        frontDeskBase.add(reservation, "card2");

        Guest_Statictics.setBackground(new java.awt.Color(255, 255, 255));
        Guest_Statictics.setMaximumSize(new java.awt.Dimension(1004, 548));
        Guest_Statictics.setMinimumSize(new java.awt.Dimension(1004, 548));

        javax.swing.GroupLayout Guest_StaticticsLayout = new javax.swing.GroupLayout(Guest_Statictics);
        Guest_Statictics.setLayout(Guest_StaticticsLayout);
        Guest_StaticticsLayout.setHorizontalGroup(
            Guest_StaticticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Guest_StaticticsLayout.setVerticalGroup(
            Guest_StaticticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        frontDeskBase.add(Guest_Statictics, "card2");

        javax.swing.GroupLayout FrontdestLayout = new javax.swing.GroupLayout(Frontdest);
        Frontdest.setLayout(FrontdestLayout);
        FrontdestLayout.setHorizontalGroup(
            FrontdestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrontdestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guestRegistrationBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(reservationBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(GuestStaticsBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
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
                    .addComponent(GuestStaticsBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frontDeskBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Dynamic_Panel.add(Frontdest, "card2");

        Room_and_hallbooking.setBackground(new java.awt.Color(255, 255, 255));
        Room_and_hallbooking.setMaximumSize(new java.awt.Dimension(1004, 610));

        jButton15.setText("jButton15");

        jButton16.setText("jButton15");

        jButton17.setText("jButton15");

        Room_Base.setBackground(new java.awt.Color(255, 255, 255));
        Room_Base.setMaximumSize(new java.awt.Dimension(1004, 610));
        Room_Base.setLayout(new java.awt.CardLayout());

        Room_manage.setBackground(new java.awt.Color(255, 255, 255));
        Room_manage.setMaximumSize(new java.awt.Dimension(1004, 610));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel49.setText("Add new rooms");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setText("Room Type");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Room Type", "Normal", "Standard", "Deluxe", "Luxury" }));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel51.setText("Bed Type");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Bed Type", "Single", "Duble", "Trible", " " }));

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel52.setText("Room ID");

        javax.swing.GroupLayout Room_manageLayout = new javax.swing.GroupLayout(Room_manage);
        Room_manage.setLayout(Room_manageLayout);
        Room_manageLayout.setHorizontalGroup(
            Room_manageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Room_manageLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Room_manageLayout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(Room_manageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(Room_manageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Room_manageLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Room_manageLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(464, 464, 464))
        );
        Room_manageLayout.setVerticalGroup(
            Room_manageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Room_manageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Room_manageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(Room_manageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox2)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(418, Short.MAX_VALUE))
        );

        Room_Base.add(Room_manage, "card3");

        Room_manage1.setBackground(new java.awt.Color(255, 255, 255));
        Room_manage1.setMaximumSize(new java.awt.Dimension(1004, 610));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel50.setText("Room manage");

        javax.swing.GroupLayout Room_manage1Layout = new javax.swing.GroupLayout(Room_manage1);
        Room_manage1.setLayout(Room_manage1Layout);
        Room_manage1Layout.setHorizontalGroup(
            Room_manage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Room_manage1Layout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(349, Short.MAX_VALUE))
        );
        Room_manage1Layout.setVerticalGroup(
            Room_manage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Room_manage1Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(304, Short.MAX_VALUE))
        );

        Room_Base.add(Room_manage1, "card3");

        javax.swing.GroupLayout Room_and_hallbookingLayout = new javax.swing.GroupLayout(Room_and_hallbooking);
        Room_and_hallbooking.setLayout(Room_and_hallbookingLayout);
        Room_and_hallbookingLayout.setHorizontalGroup(
            Room_and_hallbookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator16)
            .addGroup(Room_and_hallbookingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
            .addGroup(Room_and_hallbookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Room_Base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Room_and_hallbookingLayout.setVerticalGroup(
            Room_and_hallbookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Room_and_hallbookingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Room_and_hallbookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(596, Short.MAX_VALUE))
            .addGroup(Room_and_hallbookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Room_and_hallbookingLayout.createSequentialGroup()
                    .addContainerGap(57, Short.MAX_VALUE)
                    .addComponent(Room_Base, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Dynamic_Panel.add(Room_and_hallbooking, "card2");

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

        FoodManagementBTN1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        FoodManagementBTN1.setText("Food Order");
        FoodManagementBTN1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FoodManagementBTN1MouseClicked(evt);
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

        javax.swing.GroupLayout foodPackageLayout = new javax.swing.GroupLayout(foodPackage);
        foodPackage.setLayout(foodPackageLayout);
        foodPackageLayout.setHorizontalGroup(
            foodPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(foodPackageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(foodPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxfoodmenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxPackage, 0, 270, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53)
                .addComponent(Foodmenulableout, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
        );
        foodPackageLayout.setVerticalGroup(
            foodPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(foodPackageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(foodPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(foodPackageLayout.createSequentialGroup()
                        .addComponent(jComboBoxfoodmenu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxPackage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Foodmenulableout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Catering_Base.add(foodPackage, "card2");

        orderFood.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout orderFoodLayout = new javax.swing.GroupLayout(orderFood);
        orderFood.setLayout(orderFoodLayout);
        orderFoodLayout.setHorizontalGroup(
            orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1004, Short.MAX_VALUE)
        );
        orderFoodLayout.setVerticalGroup(
            orderFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 657, Short.MAX_VALUE)
        );

        Catering_Base.add(orderFood, "card2");

        Food_management.setBackground(new java.awt.Color(255, 255, 255));
        Food_management.setMaximumSize(new java.awt.Dimension(902, 546));
        Food_management.setMinimumSize(new java.awt.Dimension(902, 546));
        Food_management.setPreferredSize(new java.awt.Dimension(902, 546));

        AddnewFoodBTN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        AddnewFoodBTN.setText("Add New Food");
        AddnewFoodBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddnewFoodBTNMouseClicked(evt);
            }
        });

        Available_foodBTN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Available_foodBTN.setText("Available Food");
        Available_foodBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Available_foodBTNMouseClicked(evt);
            }
        });

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

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel60.setText("Name       ");

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel61.setText("Price Rs.   ");

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel62.setText("Description     ");

        food_name_out.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        food_price_out.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        food_description_out.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        food_type_out.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewfoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(food_image_lable1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(food_name_out, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                            .addComponent(food_type_out, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                .addContainerGap())
        );

        addfoodbase.add(viewfood, "card2");

        addfood1.setBackground(new java.awt.Color(255, 255, 255));
        addfood1.setMaximumSize(new java.awt.Dimension(1017, 546));
        addfood1.setMinimumSize(new java.awt.Dimension(1017, 546));
        addfood1.setPreferredSize(new java.awt.Dimension(1017, 546));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel54.setText("Food Price  in Rs.   ");

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel56.setText("Food Description     ");

        FoodWarningText.setForeground(new java.awt.Color(255, 51, 51));

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
            AddFoodTable.getColumnModel().getColumn(2).setHeaderValue("Name");
            AddFoodTable.getColumnModel().getColumn(3).setHeaderValue("PRICE");
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
                                .addContainerGap(27, Short.MAX_VALUE))
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
                .addGap(0, 6, Short.MAX_VALUE))
        );

        addfoodbase.add(addfood1, "card2");

        javax.swing.GroupLayout Food_managementLayout = new javax.swing.GroupLayout(Food_management);
        Food_management.setLayout(Food_managementLayout);
        Food_managementLayout.setHorizontalGroup(
            Food_managementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Food_managementLayout.createSequentialGroup()
                .addGroup(Food_managementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Food_managementLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AddnewFoodBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(Available_foodBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addfoodbase, javax.swing.GroupLayout.PREFERRED_SIZE, 1026, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator25, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        Food_managementLayout.setVerticalGroup(
            Food_managementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Food_managementLayout.createSequentialGroup()
                .addGroup(Food_managementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Available_foodBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddnewFoodBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
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
                .addComponent(FoodManagementBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(FoodPackageBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Catering_and_foodLayout.createSequentialGroup()
                .addGroup(Catering_and_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Catering_Base, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator30, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Catering_and_foodLayout.setVerticalGroup(
            Catering_and_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Catering_and_foodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Catering_and_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FoodManagementBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FoodPackageBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FoodManagementBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Catering_Base, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Dynamic_Panel.add(Catering_and_food, "card2");

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
                .addContainerGap(309, Short.MAX_VALUE))
        );
        House_keepingLayout.setVerticalGroup(
            House_keepingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(House_keepingLayout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(385, Short.MAX_VALUE))
        );

        Dynamic_Panel.add(House_keeping, "card2");

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
                .addContainerGap(309, Short.MAX_VALUE))
        );
        DevelopersLayout.setVerticalGroup(
            DevelopersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DevelopersLayout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(385, Short.MAX_VALUE))
        );

        Dynamic_Panel.add(Developers, "card2");

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

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setText("User Name     ");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setText("Account Type  ");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setText("N.I.C number  ");

        Address2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
        create_newuser_form.setMaximumSize(new java.awt.Dimension(902, 546));
        create_newuser_form.setMinimumSize(new java.awt.Dimension(902, 546));

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("User Name     ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Full Name       ");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Account Type  ");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Password        ");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("N.I.C number  ");

        Address.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        Address1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/showPwd.png"))); // NOI18N
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel31MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel31MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout create_newuser_formLayout = new javax.swing.GroupLayout(create_newuser_form);
        create_newuser_form.setLayout(create_newuser_formLayout);
        create_newuser_formLayout.setHorizontalGroup(
            create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(create_newuser_formLayout.createSequentialGroup()
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Address1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addGroup(create_newuser_formLayout.createSequentialGroup()
                                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(new_user_warning_text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(new_username_in, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(new_user_fullname_in, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(new_user_nic_in, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                                    .addComponent(jComboAc_type, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPassword_newUser, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel31))))
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(User_image_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(212, Short.MAX_VALUE))
        );
        create_newuser_formLayout.setVerticalGroup(
            create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(create_newuser_formLayout.createSequentialGroup()
                .addComponent(User_image_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(new_user_warning_text, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addComponent(new_username_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addComponent(new_user_fullname_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jComboAc_type, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(jPassword_newUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(create_newuser_formLayout.createSequentialGroup()
                        .addComponent(new_user_addres2_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Address1))
                .addGap(15, 15, 15)
                .addGroup(create_newuser_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        account_base.add(create_newuser_form, "card3");

        change_password.setBackground(new java.awt.Color(255, 255, 255));
        change_password.setMaximumSize(new java.awt.Dimension(902, 546));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setText("New Password        ");

        jSeparator15.setBackground(new java.awt.Color(0, 0, 0));

        jPassword_change_confirm.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPassword_change_confirm.setBorder(null);
        jPassword_change_confirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPassword_change_confirmKeyPressed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/icons8_add_user_20px.png"))); // NOI18N
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
                .addComponent(profileBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
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
                .addContainerGap(69, Short.MAX_VALUE))
        );

        Dynamic_Panel.add(Account_settings, "card2");

        Billing_And_Report.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText("Guest ID :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        BillGuestID.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        BillPriceInput.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        javax.swing.GroupLayout Billing_And_ReportLayout = new javax.swing.GroupLayout(Billing_And_Report);
        Billing_And_Report.setLayout(Billing_And_ReportLayout);
        Billing_And_ReportLayout.setHorizontalGroup(
            Billing_And_ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Billing_And_ReportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Billing_And_ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Billing_And_ReportLayout.createSequentialGroup()
                        .addComponent(BillPriceInput, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Billing_And_ReportLayout.createSequentialGroup()
                        .addComponent(BillGuestID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(296, 296, 296))))
        );
        Billing_And_ReportLayout.setVerticalGroup(
            Billing_And_ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Billing_And_ReportLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(Billing_And_ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Billing_And_ReportLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(Billing_And_ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BillGuestID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addComponent(BillPriceInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(504, Short.MAX_VALUE))
        );

        Dynamic_Panel.add(Billing_And_Report, "card2");

        time_lbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        time_lbl.setForeground(new java.awt.Color(255, 255, 255));
        time_lbl.setText("00:00:00");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(side_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Dynamic_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Dynamic_title_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(bgLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(time_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DateLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(508, 508, 508)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(side_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DateLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(time_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(Dynamic_title_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Dynamic_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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
        changepwdBTN.setForeground(new Color(240, 240, 240));
        createuserBTN.setForeground(new Color(240, 240, 240));

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
        changepwdBTN.setForeground(Color.RED);
        profileBTN.setForeground(new Color(240, 240, 240));
        createuserBTN.setForeground(new Color(240, 240, 240));
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
        guestRegistrationBTN.setForeground(Color.RED);
        reservationBTN.setForeground(new Color(240, 240, 240));
        GuestStaticsBTN.setForeground(new Color(240, 240, 240));
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
        GuestStaticsBTN.setForeground(new Color(240, 240, 240));
        guestRegistrationBTN.setForeground(new Color(240, 240, 240));

        frontDeskBase.removeAll();
        frontDeskBase.removeAll();
        frontDeskBase.repaint();
        frontDeskBase.revalidate();
        frontDeskBase.add(reservation);
        frontDeskBase.repaint();
        frontDeskBase.revalidate();
        txt_dynamic_title_bar.setText("Front Desk - Guest Reservation.");
    }//GEN-LAST:event_reservationBTNMouseClicked

    private void GuestStaticsBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuestStaticsBTNMouseClicked
        GuestStaticsBTN.setForeground(Color.RED);
        guestRegistrationBTN.setForeground(new Color(240, 240, 240));
        reservationBTN.setForeground(Color.RED);

        frontDeskBase.removeAll();
        frontDeskBase.removeAll();
        frontDeskBase.repaint();
        frontDeskBase.revalidate();
        frontDeskBase.add(Guest_Statictics);
        frontDeskBase.repaint();
        frontDeskBase.revalidate();
        txt_dynamic_title_bar.setText("Front Desk - Guest Statistics.");
    }//GEN-LAST:event_GuestStaticsBTNMouseClicked

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
        //guestRegistrationBTN.setForeground(new Color(240, 240, 240));
        //reservationBTN.setForeground(Color.RED);

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
        AddnewFoodBTN.setForeground(Color.white);
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
        Available_foodBTN.setForeground(Color.white);
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

    private void FoodManagementBTN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FoodManagementBTN1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FoodManagementBTN1MouseClicked

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
        FoodPackageBTN.setForeground(Color.RED);
        Available_foodBTN.setForeground(Color.white);
        //guestRegistrationBTN.setForeground(new Color(240, 240, 240));
        //reservationBTN.setForeground(Color.RED);
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
        } else if (ViewFoodTable.getSelectedRowCount()>=0){
            try {
                int index = GuestIdTable.getSelectedRow();  //i changed the editing colume in jetable
                String Food_ID = (GuestIdTable.getModel().getValueAt(index, 0).toString());
                BillGuestID.setText(Food_ID);

                String paId = null ;
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
                    BillPriceInput.setText(outPrice);
                    Dynamic_Panel.removeAll();
                    Dynamic_Panel.removeAll();
                    Dynamic_Panel.repaint();
                    Dynamic_Panel.revalidate();
                    Dynamic_Panel.add(Billing_And_Report);
                    Dynamic_Panel.repaint();
                    Dynamic_Panel.revalidate();
                    txt_dynamic_title_bar.setText("Billing and Report Generating...");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"please select guest id from table "+e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select the guest Id");
        }

    }//GEN-LAST:event_jButton18MouseClicked

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main_Window().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel Account_settings;
    public static javax.swing.JTable AddFoodTable;
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
    private javax.swing.JLabel BillGuestID;
    private javax.swing.JLabel BillPriceInput;
    public static javax.swing.JPanel Billing_And_Report;
    public static javax.swing.JPanel Camera_panel1;
    public static javax.swing.JPanel Catering_Base;
    public static javax.swing.JPanel Catering_and_food;
    public static javax.swing.JLabel DateLbl;
    public static javax.swing.JPanel Developers;
    public static javax.swing.JPanel Dynamic_Panel;
    private javax.swing.JPanel Dynamic_title_bar;
    private javax.swing.JButton FoodManagementBTN;
    private javax.swing.JButton FoodManagementBTN1;
    private javax.swing.JButton FoodPackageBTN;
    public static javax.swing.JLabel FoodWarningText;
    public static javax.swing.JPanel Food_management;
    public static javax.swing.JLabel Foodmenulableout;
    public static javax.swing.JPanel Frontdest;
    public static javax.swing.JTable GuestIdTable;
    private javax.swing.JPanel GuestRegistartion;
    private javax.swing.JButton GuestStaticsBTN;
    private javax.swing.JPanel Guest_Statictics;
    public static javax.swing.JPanel House_keeping;
    public static javax.swing.JPanel Inventory;
    public static javax.swing.JPanel Room_Base;
    public static javax.swing.JPanel Room_and_hallbooking;
    public static javax.swing.JPanel Room_manage;
    public static javax.swing.JPanel Room_manage1;
    public static javax.swing.JLabel User_image_lbl;
    public static javax.swing.JTable ViewFoodTable;
    public javax.swing.JPanel account_base;
    public static javax.swing.JPanel addfood1;
    public static javax.swing.JPanel addfoodbase;
    private javax.swing.JPanel bg;
    public static javax.swing.JPanel btn_account_setting;
    public static javax.swing.JPanel btn_bill_and_report;
    public static javax.swing.JPanel btn_catering;
    public static javax.swing.JPanel btn_development;
    public static javax.swing.JPanel btn_front_desk;
    public static javax.swing.JPanel btn_housekeeping;
    public static javax.swing.JPanel btn_inventory;
    public static javax.swing.JPanel btn_room_and_hall;
    public javax.swing.JPanel change_password;
    private javax.swing.JButton changepwdBTN;
    public static javax.swing.JCheckBox comEditCh;
    public javax.swing.JPanel create_newuser_form;
    private javax.swing.JButton createuserBTN;
    public static javax.swing.JPanel foodPackage;
    public static javax.swing.JLabel food_description_out;
    public static javax.swing.JLabel food_image_lable;
    public static javax.swing.JLabel food_image_lable1;
    public static javax.swing.JLabel food_name_out;
    public static javax.swing.JLabel food_price_out;
    public static javax.swing.JLabel food_price_out1;
    public static javax.swing.JLabel food_type_out;
    private javax.swing.JPanel frontDeskBase;
    private javax.swing.JButton guestRegistrationBTN;
    public static javax.swing.JTextField guest_address;
    public static javax.swing.JTextField guest_email;
    public static javax.swing.JTextField guest_fullname;
    public static javax.swing.JTextField guest_id;
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    public static javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    public static javax.swing.JButton jButton6;
    public static javax.swing.JButton jButton7;
    public static javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    public static javax.swing.JButton jButton_user_profile_save;
    public static javax.swing.JCheckBox jCheckBoxCamereCheck;
    public static javax.swing.JComboBox<String> jComboAc_type;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    public static javax.swing.JComboBox<String> jComboBoxFoodType;
    public static javax.swing.JComboBox<String> jComboBoxPackage;
    public static javax.swing.JComboBox<String> jComboBoxfoodmenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelpwdswarnning;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPasswordField jPassword_change_confirm;
    public static javax.swing.JPasswordField jPassword_change_new;
    public static javax.swing.JPasswordField jPassword_newUser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
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
    private javax.swing.JSeparator jSeparator25;
    public javax.swing.JSeparator jSeparator26;
    public javax.swing.JSeparator jSeparator27;
    public javax.swing.JSeparator jSeparator28;
    public javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    public static javax.swing.JTable jTableGuest;
    public static javax.swing.JLabel lbl_userType;
    public static javax.swing.JTextField new_user_addres1_in;
    public static javax.swing.JTextField new_user_addres2_in;
    public static javax.swing.JTextField new_user_fullname_in;
    public static javax.swing.JTextField new_user_nic_in;
    public static javax.swing.JLabel new_user_warning_text;
    public static javax.swing.JTextField new_username_in;
    public static javax.swing.JPanel orderFood;
    private javax.swing.JButton profileBTN;
    public static javax.swing.JLabel profile_warning_text1;
    private javax.swing.JPanel reservation;
    private javax.swing.JButton reservationBTN;
    private javax.swing.JPanel side_Panel;
    public static javax.swing.JLabel time_lbl;
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
