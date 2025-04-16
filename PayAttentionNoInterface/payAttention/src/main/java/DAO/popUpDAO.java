package DAO;
import Classes.Login;
import Classes.UsuarioLogin;
import ConexaoBanco.Conexao;
import LogErro.Log;

import java.sql.*;
import java.time.LocalDateTime;
public class popUpDAO{
    Login login;
    public boolean popUpTrue() throws SQLException {
        login = new Login();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt = null;
        System.out.println(login.getEmail());
        String selectIdAluno = String.format("select id from UsuarioAluno where email = 'joaosilva@gmail.com'");
        String insertPontos = "insert into Pontuacao (pontos, dtRegistro, fkAluno) VALUES(?,?,?)";
        conn = Conexao.getConexaoMSSQL();
        stmt = conn.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(selectIdAluno);
            if (rs.next()){
                System.out.println("Inserindo pontuação.");
                Integer fkAluno = rs.getInt(1);
                ps = Conexao.getConexaoMSSQL().prepareStatement(insertPontos);
                ps.setInt(1,10);
                ps.setObject(2, dataHoraAtual);
                ps.setInt(3, fkAluno);
                ps.execute();
            }
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return true;
    }
    public boolean popUpFalse() throws SQLException {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt = null;
        System.out.println(login.getEmail());
        String selectIdAluno = String.format("select id from UsuarioAluno where email = 'joaosilva@gmail.com'");
        String insertPontos = "insert into Pontuacao (pontos, dtRegistro, fkAluno) VALUES(?,?,?)";

        conn = Conexao.getConexaoMSSQL();
        stmt = conn.createStatement();

        try {
            ResultSet rs = stmt.executeQuery(selectIdAluno);
            if (rs.next()){
                System.out.println("Inserindo pontuação.");
                Integer fkAluno = rs.getInt(1);
                ps = Conexao.getConexaoMSSQL().prepareStatement(insertPontos);
                ps.setInt(1,0);
                ps.setObject(2, dataHoraAtual);
                ps.setInt(3, fkAluno);
                ps.execute();
            }
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
            // Capturando informações relevantes para o log
            String mensagemErro = ex.getMessage();
            String estadoSQL = ex.getSQLState();
            Integer codigoErro = ex.getErrorCode();

            // Agora você pode incluir essas informações no log
            Log log = new Log();
            log.exibirLog("""
             Registro dos PopUp's
             Erro: %s
             Estado SQL: %s
             Código de Erro: %d
                """.formatted(mensagemErro, estadoSQL, codigoErro));
        }
        return false;
    }
}
