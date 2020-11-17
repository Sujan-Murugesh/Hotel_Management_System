package hotel_management_system;

import static hotel_management_system.Main_Window.jTableGuest;
import java.awt.Color;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Sujan
 */
public class TableDesign extends Main_Window{
    public  void tableHead1(){
        JTableHeader stResulttbl=jTableGuest.getTableHeader();
        //stResulttbl.setBackground(Color.BLACK);
        stResulttbl.setBackground(Color.red);
        stResulttbl.setForeground(Color.white);
        
    }
}
