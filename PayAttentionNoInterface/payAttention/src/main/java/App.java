import Views.LoginNoInterface;
import java.sql.SQLException;

public class App extends javax.swing.JFrame {
    public static void main(String[] args) throws SQLException {
        LoginNoInterface loginNoInterface = new LoginNoInterface();
        loginNoInterface.validaLogin();
    }
}