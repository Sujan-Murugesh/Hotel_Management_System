package hotel_management_system;

import static hotel_management_system.Main_Window.DateLbl;
import static hotel_management_system.Main_Window.time_lbl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author Sujan
 */
public class Date_Time{

    /**
     *
     */
    public void showDate(){
        SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        DateLbl.setText(d1.format(d));
    }
    
    public void showTime(){
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat d1 = new SimpleDateFormat("hh:mm:ss");
                Date d = new Date();
                time_lbl.setText(d1.format(d));
            }
        }).start();
    }
    
}
