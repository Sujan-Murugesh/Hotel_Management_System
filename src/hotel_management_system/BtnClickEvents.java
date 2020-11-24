/*
    THIS IS FOR CHANGING COLOR WHEN CLICK THE BUTTON PANELS...
    //SUJAN
 */
package hotel_management_system;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Sujan
 */
class BtnClickEvents extends Main_Window{
    //Button color change method
    void setColor(JPanel panel){
        //[102,102,102]
        panel.setBackground(new Color(102,102,102));
    }
    
    void resetColor(JPanel panel){
        //[51,51,51]
        panel.setBackground(new Color(51,51,51));
    }
    
    //==========================================================
    //chooice the color
    void btn_color_change(java.awt.event.MouseEvent evt){
//        if(evt.getComponent()==btn_home){ //when click the home button
//            Dynamic_Panel.removeAll();
//            Dynamic_Panel.removeAll();
//            Dynamic_Panel.repaint();
//            Dynamic_Panel.revalidate();
//            Dynamic_Panel.add(home);
//            Dynamic_Panel.repaint();
//            Dynamic_Panel.revalidate();
//            txt_dynamic_title_bar.setText("Home");
//        
//            setColor(btn_home);
//            resetColor(btn_security);
//            resetColor(btn_bill_and_report);
//            resetColor(btn_inventory);
//            resetColor(btn_front_desk);
//            resetColor(btn_room_and_hall);
//            resetColor(btn_catering);
//            resetColor(btn_housekeeping);
//            resetColor(btn_development);
//        }
        //===================================================
         if(evt.getComponent()==btn_account_setting){ //when click the security button
            Dynamic_Panel.removeAll();
            Dynamic_Panel.removeAll();
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            Dynamic_Panel.add(Account_settings);
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            txt_dynamic_title_bar.setText("User Account and Security");
        
            setColor(btn_account_setting);
            
            resetColor(btn_bill_and_report);
            resetColor(btn_inventory);
            resetColor(btn_front_desk);
            resetColor(btn_room_and_hall);
            resetColor(btn_catering);
            resetColor(btn_housekeeping);
            resetColor(btn_development);
        }
        //===================================================
        else if(evt.getComponent()==btn_bill_and_report){ //when click the btn_bill_and_report button
            Dynamic_Panel.removeAll();
            Dynamic_Panel.removeAll();
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            Dynamic_Panel.add(Billing_And_Report);
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            txt_dynamic_title_bar.setText("Billing and Report Generating...");
        
            setColor(btn_bill_and_report);
           
            resetColor(btn_account_setting);
            resetColor(btn_inventory);
            resetColor(btn_front_desk);
            resetColor(btn_room_and_hall);
            resetColor(btn_catering);
            resetColor(btn_housekeeping);
            resetColor(btn_development);
        }
        //===================================================
        else if(evt.getComponent()==btn_inventory){ //when click the btn_inventory button
            Dynamic_Panel.removeAll();
            Dynamic_Panel.removeAll();
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            Dynamic_Panel.add(Inventory);
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            txt_dynamic_title_bar.setText("Hotel Inventory Management...");
            
            setColor(btn_inventory);
           
            resetColor(btn_account_setting);
            resetColor(btn_bill_and_report);
            resetColor(btn_front_desk);
            resetColor(btn_room_and_hall);
            resetColor(btn_catering);
            resetColor(btn_housekeeping);
            resetColor(btn_development);
        }
        //===================================================
        else if(evt.getComponent()==btn_front_desk){ //when click the btn_front_desk button
             Dynamic_Panel.removeAll();
            Dynamic_Panel.removeAll();
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            Dynamic_Panel.add(Frontdest);
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            txt_dynamic_title_bar.setText("Front desk and transport management...");
        
            setColor(btn_front_desk);
          
            resetColor(btn_account_setting);
            resetColor(btn_bill_and_report);
            resetColor(btn_inventory);
            resetColor(btn_room_and_hall);
            resetColor(btn_catering);
            resetColor(btn_housekeeping);
            resetColor(btn_development);
        }
        //===================================================
        else if(evt.getComponent()==btn_room_and_hall){ //when click the btn_room_and_hall button
            Dynamic_Panel.removeAll();
            Dynamic_Panel.removeAll();
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            Dynamic_Panel.add(RoomandHall);
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            txt_dynamic_title_bar.setText("Room and Hall Booking Management...");
        
            setColor(btn_room_and_hall);
            resetColor(btn_account_setting);
            resetColor(btn_bill_and_report);
            resetColor(btn_inventory);
            resetColor(btn_front_desk);
            resetColor(btn_catering);
            resetColor(btn_housekeeping);
            resetColor(btn_development);
        }
        //===================================================
        else if(evt.getComponent()==btn_catering){ //when click the btn_catering button
            Dynamic_Panel.removeAll();
            Dynamic_Panel.removeAll();
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            Dynamic_Panel.add(Catering_and_food);
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            txt_dynamic_title_bar.setText("Catering and Food management...");
        
            setColor(btn_catering);
           
            resetColor(btn_account_setting);
            resetColor(btn_bill_and_report);
            resetColor(btn_inventory);
            resetColor(btn_front_desk);
            resetColor(btn_room_and_hall);
            resetColor(btn_housekeeping);
            resetColor(btn_development);
        }
        //===================================================
        else if(evt.getComponent()==btn_housekeeping){ //when click the btn_housekeeping button
            Dynamic_Panel.removeAll();
            Dynamic_Panel.removeAll();
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            Dynamic_Panel.add(House_keeping);
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            txt_dynamic_title_bar.setText("House keeping and Loundry Management...");
        
            setColor(btn_housekeeping);
       
            resetColor(btn_account_setting);
            resetColor(btn_bill_and_report);
            resetColor(btn_inventory);
            resetColor(btn_front_desk);
            resetColor(btn_room_and_hall);
            resetColor(btn_catering);
            resetColor(btn_development);
        }
        //===================================================
        else if(evt.getComponent()==btn_development){ //when click the btn_development button
            Dynamic_Panel.removeAll();
            Dynamic_Panel.removeAll();
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            Dynamic_Panel.add(Developers);
            Dynamic_Panel.repaint();
            Dynamic_Panel.revalidate();
            txt_dynamic_title_bar.setText("Developers Team Information...");
        
            setColor(btn_development);
           
            resetColor(btn_account_setting);
            resetColor(btn_bill_and_report);
            resetColor(btn_inventory);
            resetColor(btn_front_desk);
            resetColor(btn_room_and_hall);
            resetColor(btn_catering);
            resetColor(btn_housekeeping);
        }
        //===================================================
    }
}
