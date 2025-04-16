package ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String urlSQLServer = "jdbc:sqlserver://ec2-52-6-22-247.compute-1.amazonaws.com:1433;database=Mindbridge;encrypt=true;trustServerCertificate=true;";
    private static final String userSQLServer = "sa";
    private static final String passwordSQLServer = "gemini78";
    private static final String urlDocker = "jdbc:mysql://localhost:3306/Mindbridge_maquina";
    private static final String userDocker = "admin";
    private static final String passwordDocker = "admin";
    private static Connection conn;
    private static Connection connDocker;

    public static Connection getConexaoMSSQL(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if(conn == null){
                conn = DriverManager.getConnection(urlSQLServer, userSQLServer, passwordSQLServer);
                return conn;
            }else {
                return conn;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConexaoDocker(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if(connDocker == null){
                connDocker = DriverManager.getConnection(urlDocker, userDocker, passwordDocker);
                return connDocker;
            }else {
                return connDocker;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}