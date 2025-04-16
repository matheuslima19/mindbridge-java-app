/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Classes.AlertaLogAcesso;
import Classes.AlertaRegMaquina;
import Classes.Login;
import Classes.UsuarioLogin;
import ConexaoBanco.Conexao;
import Models.CapturaJanelas;
import Models.CapturaAcesso;
import Models.CapturaMaquina;
import Models.Captura;
import Views.popUpView;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Matheus
 */
public class LoginDAO extends javax.swing.JFrame{
    public boolean validaLogin(String email, String senha) throws SQLException{
        String sql = "SELECT email, senha FROM UsuarioAluno WHERE email = '"+email+"' AND senha = '"+senha+"'";
        System.out.println("sql");
        Connection conn = null;
        Statement stmt = null;
        conn = Conexao.getConexaoMSSQL();
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            UsuarioLogin usuarioLogin = new UsuarioLogin();
            String emailLogin = rs.getString(1);
            String senhaLogin = rs.getString(2);
            usuarioLogin.setEmail(emailLogin);
            usuarioLogin.setSenha(senhaLogin);
            System.out.println(usuarioLogin.getEmail());
            System.out.println(usuarioLogin.getSenha());
            JOptionPane.showMessageDialog(rootPane, "Entrando !!!");
            CapturaMaquina capturaMaquina = new CapturaMaquina();
            capturaMaquina.capturaMaquina();
            CapturaAcesso capturaAcesso = new CapturaAcesso();
            capturaAcesso.capturaAcesso();
            Captura captura = new Captura();
            captura.capturaDados();
            CapturaJanelas capturaJanelas = new CapturaJanelas();
            capturaJanelas.capturaJanelas();
            LimitesDAO limites = new LimitesDAO();
            limites.listarLimites();

            AlertaLogAcessoDAO alertaLogAcesso = new AlertaLogAcessoDAO();
            alertaLogAcesso.registrarAlertaMaquina();


            popUpView popUp = new popUpView();
            popUp.iniciarPopUp();
        }else{
            System.out.println("Não Possui");
            JOptionPane.showMessageDialog(rootPane, "Email e/ou senha inválidos !!!");
        }
        return true;
    }
    
}
