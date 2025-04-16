package DAO;
import ConexaoBanco.Conexao;
import Classes.Janelas;
import LogErro.Log;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;

import java.sql.*;
import java.time.LocalDateTime;

public class JanelasDAO {
    public static boolean cadastrarJanelas(Janelas janelas) throws SQLException {
        Looca looca = new Looca();
        Rede rede = looca.getRede();
        String nomeComputador = rede.getParametros().getHostName();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        String sql = "insert into JanelasAbertas(dtRegistro, PID, idJanela, titulo, comando, localizacao, visivel, fkMaquina) values (?,?,?,?,?,?,?,?)";
        String selectIdMaquina = String.format("select id from Maquinas WHERE hostname = '%s'", nomeComputador);
        String selectIdMaquinaDocker = String.format("select id from Maquina WHERE hostname = '%s'", nomeComputador);

        PreparedStatement ps = null;
        PreparedStatement psDocker = null;
        Connection conn = null;
        Connection connDocker = null;
        Statement stmt = null;
        Statement stmt2 = null;
        Statement stmtDocker = null;
        conn = Conexao.getConexaoMSSQL();
        connDocker = Conexao.getConexaoDocker();
        stmt2 = conn.createStatement();
        stmtDocker = connDocker.createStatement();
        try{
            ResultSet rs = stmt2.executeQuery(selectIdMaquina);
            while(rs.next()) {
                Integer fkMaquina = rs.getInt(1);
                ps = Conexao.getConexaoMSSQL().prepareStatement(sql);
                ps.setObject(1, dataHoraAtual);
                ps.setInt(2, janelas.getPID());
                ps.setInt(3, janelas.getIdJanela());
                ps.setString(4, janelas.getTitulo());
                ps.setString(5, janelas.getComando());
                ps.setString(6, janelas.getLocalizacao());
                ps.setString(7, "true");
                ps.setInt(8, fkMaquina);
                ps.execute();
            }
        }
        catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
            // Capturando informações relevantes para o log
            String mensagemErro = e.getMessage();
            String estadoSQL = e.getSQLState();
            Integer codigoErro = e.getErrorCode();

            // Agora você pode incluir essas informações no log
            Log log = new Log();
            log.exibirLog("""
             Registro das Janelas
             Erro: %s
             Estado SQL: %s
             Código de Erro: %d
                """.formatted(mensagemErro, estadoSQL, codigoErro));
            throw new RuntimeException(e);
        }

        try{
            ResultSet rsDocker = stmtDocker.executeQuery(selectIdMaquinaDocker);
            while(rsDocker.next()) {
                Integer fkMaquinaDocker = rsDocker.getInt(1);
                psDocker = Conexao.getConexaoDocker().prepareStatement(sql);
                psDocker.setObject(1, dataHoraAtual);
                psDocker.setInt(2, janelas.getPID());
                psDocker.setInt(3, janelas.getIdJanela());
                psDocker.setString(4, janelas.getTitulo());
                psDocker.setString(5, janelas.getComando());
                psDocker.setString(6, janelas.getLocalizacao());
                psDocker.setString(7, "true");
                psDocker.setInt(8, fkMaquinaDocker);
                psDocker.execute();
            }
        }
        catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
            // Capturando informações relevantes para o log
            String mensagemErro = e.getMessage();
            String estadoSQL = e.getSQLState();
            Integer codigoErro = e.getErrorCode();

            // Agora você pode incluir essas informações no log
            Log log = new Log();
            log.exibirLog("""
             Registro das Janelas
             Erro: %s
             Estado SQL: %s
             Código de Erro: %d
                """.formatted(mensagemErro, estadoSQL, codigoErro));
            throw new RuntimeException(e);
        }
        return false;
    }
}
