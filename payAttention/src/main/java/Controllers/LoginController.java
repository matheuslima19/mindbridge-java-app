/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;
import java.io.IOException;
import java.sql.SQLException;

import DAO.LoginDAO;
import Views.LoginView;
import Classes.Login;

/**
 *
 * @author Matheus
 */
public class LoginController {
    public void loginUsuario(LoginView view) throws SQLException, IOException{
        LoginDAO chamarLogin = new LoginDAO();
        chamarLogin.validaLogin(view.getjTextFieldLogin().getText(), view.getjPasswordFieldSenha().getText());
    }
}
