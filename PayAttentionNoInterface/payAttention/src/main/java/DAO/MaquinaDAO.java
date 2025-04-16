package DAO;
import Classes.UsuarioLogin;
import ConexaoBanco.Conexao;
import Classes.CadastroMaquina;
import LogErro.Log;

import java.sql.*;
public class MaquinaDAO {
    UsuarioLogin usuarioLogin;
    public boolean cadastrarMaquina(CadastroMaquina cadastroMaquina) throws SQLException {
        usuarioLogin = new UsuarioLogin();
        String sql = "INSERT INTO Maquinas (hostname, SistemaOperacional, Processador, RAM, armazenamento, statSist, fkInstituicao) VALUES (?,?,?,?,?,?,?)";
        String sqlDocker = "INSERT INTO Maquina (hostname, SistemaOperacional, Processador, RAM, armazenamento) VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;
        PreparedStatement psDocker = null;
        String selectNome = String.format("select hostname from Maquinas WHERE hostname = '%s'", cadastroMaquina.getNomeComputador());
        String selectNomeDocker = String.format("select hostname from Maquina WHERE hostname = '%s'", cadastroMaquina.getNomeComputador());
        String selectInstituicao = String.format("select t.fkInstituicao from UsuarioAluno ua join Turma t on ua.fkTurma = t.id join InstituicaoEnsino ie on t.fkInstituicao = ie.id where ua.email = '%s'", usuarioLogin.getEmail());
        Connection conn = null;
        Connection connDocker = null;
        Statement stmt = null;
        Statement stmt2 = null;
        Statement stmtDocker = null;
        conn = Conexao.getConexaoMSSQL();
        connDocker = Conexao.getConexaoMSSQL();
        stmt = conn.createStatement();
        stmt2 = conn.createStatement();
        stmtDocker = connDocker.createStatement();

        try {
            ResultSet rsDocker = stmtDocker.executeQuery(selectNomeDocker);
            if (rsDocker.next()) {
                System.out.println("Essa máquina já está no sistema !");
            } else {
                psDocker = Conexao.getConexaoMSSQL().prepareStatement(sqlDocker);
                psDocker.setString(1, cadastroMaquina.getNomeComputador());
                psDocker.setString(2, cadastroMaquina.getSistemaOperacional());
                psDocker.setString(3, cadastroMaquina.getNomeProcessador());
                psDocker.setDouble(4, cadastroMaquina.getQuantidadeRAM() / (Math.pow(1024, 3)));
                psDocker.setDouble(5, cadastroMaquina.getArmazenamentoHD() / (Math.pow(1024, 3)));
                psDocker.execute();
            }

        } catch (
                SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
            // Capturando informações relevantes para o log
            String mensagemErro = ex.getMessage();
            String estadoSQL = ex.getSQLState();
            Integer codigoErro = ex.getErrorCode();

            // Agora você pode incluir essas informações no log
            Log log = new Log();
            log.exibirLog("""
             Registro das Maquinas Docker
             Erro: %s
             Estado SQL: %s
             Código de Erro: %d
                """.formatted(mensagemErro, estadoSQL, codigoErro));
        }

        try {
            ResultSet rs2 = stmt.executeQuery(selectNome);
            ResultSet rs3 = stmt2.executeQuery(selectInstituicao);
            if (rs2.next()){
                System.out.println("Essa máquina já está no sistema !");
            } else{
                while (rs3.next()) {
                    Integer fkInstituicao = rs3.getInt(1);
                    ps = Conexao.getConexaoMSSQL().prepareStatement(sql);
                    ps.setString(1, cadastroMaquina.getNomeComputador());
                    ps.setString(2, cadastroMaquina.getSistemaOperacional());
                    ps.setString(3, cadastroMaquina.getNomeProcessador());
                    ps.setDouble(4, cadastroMaquina.getQuantidadeRAM() / (Math.pow(1024, 3)));
                    ps.setDouble(5, cadastroMaquina.getArmazenamentoHD() / (Math.pow(1024, 3)));
                    ps.setInt(6, 1);
                    ps.setInt(7, fkInstituicao);
                    ps.execute();
                }
            }
        } catch (
                SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
            // Capturando informações relevantes para o log
            String mensagemErro = ex.getMessage();
            String estadoSQL = ex.getSQLState();
            Integer codigoErro = ex.getErrorCode();

            // Agora você pode incluir essas informações no log
            Log log = new Log();
            log.exibirLog("""
             Registro das Maquinas
             Erro: %s
             Estado SQL: %s
             Código de Erro: %d
                """.formatted(mensagemErro, estadoSQL, codigoErro));
        }
        return true;
    }
}
