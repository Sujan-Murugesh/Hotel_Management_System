package hotel_management_system;

import static hotel_management_system.Main_Window.Add_food_description;
import static hotel_management_system.Main_Window.Add_food_name;
import static hotel_management_system.Main_Window.Add_food_price;
import static hotel_management_system.Main_Window.FoodWarningText;
import static hotel_management_system.Main_Window.food_image_lable;
import static hotel_management_system.Main_Window.jComboBoxFoodType;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Sujan
 */
public class Food extends Main_Window {

    public static String FoodImgPath = null;
    //Resize image

    public static ImageIcon ResizeImage(String FoodImgPath, byte[] pic) {
        ImageIcon myImage = null;
        if (FoodImgPath != null) {
            myImage = new ImageIcon(FoodImgPath);
        } else {
            myImage = new ImageIcon(pic);
        }

        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(food_image_lable.getWidth(), food_image_lable.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }

    //==========================********************************************====================
    public static void chooiceFoodImg() {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
        int result = file.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            food_image_lable.setIcon(ResizeImage(path, null));
            FoodImgPath = path;
            FoodWarningText.setText("File :" + FoodImgPath);
        } else {
            FoodWarningText.setText("No File selected..");
        }
        //ChoiceNewUserImg(); // to chooice the user profile picture
        jComboBoxFoodType.requestFocus();
        jComboBoxFoodType.setBackground(new Color(197, 232, 240));
    }
    //==========================********************************************====================

    public void add_new_food() {
        //String foodtype = jComboBoxFoodType.getSelectedItem().toString();
        //String TableName = "food".concat(jComboBoxFoodType.getSelectedItem().toString());
        String TableName = "food";
        if (FoodImgPath == null) {
            FoodWarningText.setText("Please Select the Food image");
            food_image_lable.requestFocus();
            food_image_lable.setBackground(new Color(197, 232, 240));
        } else if (jComboBoxFoodType.getSelectedItem().toString().equals("Food Menu Type")) {
            jComboBoxFoodType.requestFocus();
            FoodWarningText.setText("Please select Food manu type");
        } else if (Add_food_name.getText().equals("")) {
            Add_food_name.requestFocus();
            Add_food_name.setBackground(new Color(197, 232, 240));
            FoodWarningText.setText("Please Fill out the food name");
        } else if (Add_food_price.getText().equals("")) {
            Add_food_price.requestFocus();
            Add_food_price.setBackground(new Color(197, 232, 240));
            FoodWarningText.setText("Please Fill out the food Price");
        } else if (Add_food_description.getText().equals("")) {
            Add_food_description.requestFocus();
            Add_food_description.setBackground(new Color(197, 232, 240));
            FoodWarningText.setText("Please Fill out the Description");
        } else {
//            if (jComboBoxFoodType.getSelectedItem().toString().equals("Drinks and Bevarage")) {
//                TableName = "foodDrinks";
//            }
            try {
                DatabaseMetaData db1 = conn.getMetaData();
                ResultSet rs1 = db1.getTables(null, null, TableName, null);
                if (rs1.next()) {
                    try {
                        //================================================================
                        //TO INSERT FOOD DATA TO DATABASE...
                        ps = conn.prepareStatement("INSERT INTO " + TableName + "(`type`,`name`,`price`,`description`,`foodImg`)values(?,?,?,?,?)");
                        ps.setString(1, jComboBoxFoodType.getSelectedItem().toString());
                        ps.setString(2, Add_food_name.getText());

                        double foodPrice = Double.parseDouble(Add_food_price.getText());
                        ps.setDouble(3, foodPrice);
                        ps.setString(4, Add_food_description.getText());

                        InputStream img = new FileInputStream(new File(FoodImgPath));
                        ps.setBlob(5, img);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "New Food Added Successfully...", "Success!", HEIGHT);
                        foodId();
                        clearFoodForm();
                        show_data_inJtablefood();
                        //================================================================
                    } catch (SQLException | NumberFormatException | FileNotFoundException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                } else {
                    //table create code....
                    try {
                        stmt = conn.createStatement();
                        String sql = "CREATE TABLE IF NOT EXISTS " + TableName + "(id int(11) NOT NULL AUTO_INCREMENT,"
                                + "type varchar(20) NOT NULL," + "name varchar(100) NOT NULL," + "price double(6,2) NOT NULL," + "description varchar(200) NOT NULL," + "foodImg longblob NOT NULL," + "PRIMARY KEY (id))";
                        stmt.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "" + TableName + " Created Successfully...", "Success!", HEIGHT);
                    } catch (SQLException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    //================================================================
                    //TO INSERT FOOD DATA TO DATABASE...
                    ps = conn.prepareStatement("INSERT INTO " + TableName + "(`type`,`name`,`price`,`description`,`foodImg`)values(?,?,?,?,?)");
                    ps.setString(1, jComboBoxFoodType.getSelectedItem().toString());
                    ps.setString(2, Add_food_name.getText());

                    double foodPrice = Double.parseDouble(Add_food_price.getText());
                    ps.setDouble(3, foodPrice);
                    ps.setString(4, Add_food_description.getText());

                    InputStream img = new FileInputStream(new File(FoodImgPath));
                    ps.setBlob(5, img);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "New Food Added Successfully...", "Success!", HEIGHT);
                    foodId();
                    clearFoodForm();
                    show_data_inJtablefood();
                    //================================================================
                }
            } catch (SQLException | HeadlessException | FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

    }

    //==========================********************************************====================
    //show table
    public void show_data_inJtablefood() {
        try {
            String qry = "select `id`,`type`,`name`,`price`,`description` from food";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            AddFoodTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    //==========================********************************************====================
    //JTABLE CLICK EVENT 
    public void food_table_row_clicked() {
        try {
            int index = AddFoodTable.getSelectedRow();  //i changed the editing colume in jetable
            String Food_ID = (AddFoodTable.getModel().getValueAt(index, 0).toString());

            String qry = "select * from `food` where `id` = '" + Food_ID + "' ";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            if (rs.next()) {
                Add_food_id.setText(Food_ID);
                String Name = rs.getString("name");
                Add_food_name.setText(Name);

                String type = rs.getString("type");
                switch (type) {
                    case "Breakfast":
                        jComboBoxFoodType.setSelectedIndex(1);
                        break;
                    case "Lunch":
                        jComboBoxFoodType.setSelectedIndex(2);
                        break;
                    case "Dinner":
                        jComboBoxFoodType.setSelectedIndex(3);
                        break;
                    case "Drinks and Bevarage":
                        jComboBoxFoodType.setSelectedIndex(4);
                        break;
                    default:
                        break;
                }

                double price = rs.getDouble("price");
                String p = String.valueOf(price);
                Add_food_price.setText(p);

                String description = rs.getString("description");
                Add_food_description.setText(description);

                byte[] img = rs.getBytes("foodImg");
                ImageIcon image = new ImageIcon(img);
                Image im = image.getImage();
                Image myImg = im.getScaledInstance(food_image_lable.getWidth(), food_image_lable.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImg);
                food_image_lable.setIcon(newImage);

                //close............................ 
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //==========================********************************************====================
    //UPDATE FOOD
    public void update_food_items() {
        if (jComboBoxFoodType.getSelectedItem().toString().equals("Food Menu Type")) {
            jComboBoxFoodType.requestFocus();
            FoodWarningText.setText("Please select Food manu type");
        } else if (Add_food_name.getText().equals("")) {
            Add_food_name.requestFocus();
            Add_food_name.setBackground(new Color(197, 232, 240));
            FoodWarningText.setText("Please Fill out the food name");
        } else if (Add_food_price.getText().equals("")) {
            Add_food_price.requestFocus();
            Add_food_price.setBackground(new Color(197, 232, 240));
            FoodWarningText.setText("Please Fill out the food Price");
        } else if (Add_food_description.getText().equals("")) {
            Add_food_description.requestFocus();
            Add_food_description.setBackground(new Color(197, 232, 240));
            FoodWarningText.setText("Please Fill out the Description");
        } else //no image
        {
            if (FoodImgPath == null) {
                try {
                    UpdateQuery = "UPDATE `food` SET `type`=?,`name`=?,`price`=?,`description`=? WHERE `id`='" + Add_food_id.getText() + "'";
                    ps = conn.prepareStatement(UpdateQuery);

                    ps.setString(1, jComboBoxFoodType.getSelectedItem().toString());
                    ps.setString(2, Add_food_name.getText());

                    double price = Double.parseDouble(Add_food_price.getText());
                    ps.setDouble(3, price);
                    ps.setString(4, Add_food_description.getText());

                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Food Data Updated...");
                    foodId();
                    clearFoodForm();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } else {
                //if have new image..
                try {
                    UpdateQuery = "UPDATE `food` SET `type`=?,`name`=?,`price`=?,`description`=?,`foodImg`=? WHERE `id`='" + Add_food_id.getText() + "'";
                    ps = conn.prepareStatement(UpdateQuery);

                    ps.setString(1, jComboBoxFoodType.getSelectedItem().toString());
                    ps.setString(2, Add_food_name.getText());

                    double price = Double.parseDouble(Add_food_price.getText());
                    ps.setDouble(3, price);
                    ps.setString(4, Add_food_description.getText());

                    InputStream img = new FileInputStream(new File(FoodImgPath));
                    ps.setBlob(5, img);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Food  Data Updated...");
                    foodId();
                    clearFoodForm();
                } catch (SQLException | FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }

    //==========================********************************************====================    
    //DELETE FROM `food` WHERE 0
    public void delete_food_items() {
        if (Add_food_id.getText().equals("")) {
            FoodWarningText.setText("Please select the food from table");
            AddFoodTable.requestFocus();
        } else {
            try {
                String sql = "select * from `food` order by id desc limit 1";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int idno = rs.getInt("id") + 1;
                    String id = String.valueOf(idno);
                    if (Add_food_id.getText().equals(id)) {
                        FoodWarningText.setText("This foodID not inserted please select from table");
                        AddFoodTable.requestFocus();
                    } else {
                        try {
                            String fid = Add_food_id.getText();
                            String sql2 = "DELETE FROM `food` WHERE `id`=" + fid;
                            stmt = conn.prepareStatement(sql2);
                            stmt.executeUpdate(sql2);

                            JOptionPane.showMessageDialog(null, fid + "Food deleted ");
                            foodId();
                            show_data_inJtablefood();
                            clearFoodForm();

                        } catch (SQLException | HeadlessException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }

    }

    //==========================********************************************====================
    public void clearFoodForm() {
        jComboBoxFoodType.setSelectedIndex(0);
        Add_food_name.setText("");
        Add_food_name.setBackground(Color.WHITE);

        Add_food_price.setText("");
        Add_food_price.setBackground(Color.WHITE);

        Add_food_description.setText("");
        Add_food_description.setBackground(Color.WHITE);

        food_image_lable.setBackground(Color.WHITE);
        food_image_lable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_management_system/img/icons/addimg.png")));
        food_image_lable.revalidate();
        FoodImgPath = null;
        foodId();
        FoodWarningText.setText("");

    }

    public void foodId() {
        try {
            String sql = "select * from `food` order by id desc limit 1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int idno = rs.getInt("id") + 1;
                String id = String.valueOf(idno);
                Add_food_id.setText(id);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    //==============================*********************************=======================
        //SHOW AVAILABLE FOOD
    //======================================================================================
        public void showAvailable_foods_table_clicked(){
            try {
            int index = AddFoodTable.getSelectedRow();  //i changed the editing colume in jetable
            String Food_ID = (AddFoodTable.getModel().getValueAt(index, 0).toString());

            String qry = "select * from `food` where `id` = '" + Food_ID + "' ";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            if (rs.next()) {
                
                String Name = rs.getString("name");
                food_name_out.setText(Name);

                String type = rs.getString("type");
                food_type_out.setText(type);

                double price = rs.getDouble("price");
                String p = String.valueOf(price);
                food_price_out.setText(p);

                String description = rs.getString("description");
                food_description_out.setText(description);

                byte[] img = rs.getBytes("foodImg");
                ImageIcon image = new ImageIcon(img);
                Image im = image.getImage();
                Image myImg = im.getScaledInstance(food_image_lable1.getWidth(), food_image_lable1.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImg);
                food_image_lable1.setIcon(newImage);

                //close............................ 
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
    //======================================================================================
        public void seeAvailabaleFoodJtble(){
            try {
            String qry = "select `id`,`type`,`name`,`price`,`description` from food";
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            AddFoodTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        }
}
