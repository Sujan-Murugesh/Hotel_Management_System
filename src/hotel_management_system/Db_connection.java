package hotel_management_system;

//import static loginsystem.Login_form.conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Sujan
 */
public  class Db_connection extends javax.swing.JFrame{
    
    
    public static Connection connect() {
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem?useSSL=false","root","");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return conn;
    }
}
