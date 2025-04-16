package Views;

import Classes.Login;
import DAO.LoginDAO;
import DAO.popUpDAO;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class LoginNoInterface {
    public static final long TEMPO_OPEN = (5000);
    static Timer timerOpen = null;
    Login login = new Login();

    public void validaLogin() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("""
                                
                ______            ___  _   _             _   _            \s
                | ___ \\          / _ \\| | | |           | | (_)           \s
                | |_/ /_ _ _   _/ /_\\ \\ |_| |_ ___ _ __ | |_ _  ___  _ __ \s
                |  __/ _` | | | |  _  | __| __/ _ \\ '_ \\| __| |/ _ \\| '_ \\\s
                | | | (_| | |_| | | | | |_| ||  __/ | | | |_| | (_) | | | |
                \\_|  \\__,_|\\__, \\_| |_/\\__|\\__\\___|_| |_|\\__|_|\\___/|_| |_|
                            __/ |                                         \s
                           |___/                                          \s
                                
                """));
        System.out.println(String.format("""
                +========================================+
                |    Bem vindo(a) ao PayAttention !!!    |
                +========================================+
                """));
        System.out.print("Digite o seu email: ");
        String email = scanner.nextLine();
        login.setEmail(email);
        System.out.print("Digite a sua senha: ");
        String password = scanner.nextLine();
        login.setSenha(password);
        System.out.println(String.format("""
                +========================================+
                |                                        |
                +========================================+
                """));
        LoginDAO loginDAO = new LoginDAO();
        loginDAO.validaLogin();
        scanner.close();
    }

    public void popUpNoFrame()throws SQLException{
        popUpDAO popUp = new popUpDAO();
        if (timerOpen == null) {
            timerOpen = new Timer();
            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Ainda está acompanhando essa aula ? (Sim)");
                    String resposta = "Sim";
                    if (resposta == "Sim"){
                        try {
                            popUp.popUpTrue();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };
            timerOpen.scheduleAtFixedRate(tarefa, TEMPO_OPEN, TEMPO_OPEN);
        }
    }
}
