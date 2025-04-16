package DAO;

import Classes.CadastroMaquina;
import Classes.LogAcesso;
import Classes.Limites;
import Classes.Telegram;
import ConexaoBanco.Conexao;
import java.sql.*;

public class AlertaLogAcessoDAO {
    Limites limites;
    CadastroMaquina maquina;
    LogAcesso logAcessoClass;
    public boolean registrarAlertaMaquina() throws SQLException {
        limites = new Limites();
        maquina = new CadastroMaquina();
        logAcessoClass = new LogAcesso();


        Double discoLimite = limites.getDiscoPorcent();
        Double discoTotal = maquina.getArmazenamentoHD()/Math.pow(1024,3);
        Double discoUsado = logAcessoClass.getArmUsado();

        Connection conn = null;
        PreparedStatement ps = null;
        Statement stmtLog = null;

        conn = Conexao.getConexaoMSSQL();
        stmtLog = conn.createStatement();

        String selectLog = "SELECT TOP 1 * FROM LogAcesso WHERE fkUsuario = %d AND fkMaquina = %d ORDER BY dtInicializacao DESC;".formatted(logAcessoClass.getFkUsuario(), logAcessoClass.getFkMaquina());

        String insertAlertaLog = "INSERT INTO AlertasLog(componente,tipo,fkLogAcesso) VALUES (?,?,?)";

        try {
            ResultSet rsSelectLog = stmtLog.executeQuery(selectLog);
            if (rsSelectLog.next()) {
                Integer idRegistro = rsSelectLog.getInt("id");

                Double porcentUsado = (100*discoUsado)/discoTotal;

                if (porcentUsado >= (discoLimite - (discoLimite*0.1)) && porcentUsado <= discoLimite) {
                    // System.out.println("test ATENÇÃO");
                    ps = Conexao.getConexaoMSSQL().prepareStatement(insertAlertaLog);
                    ps.setString(1,"Armazenamento");
                    ps.setString(2,"Atenção");
                    ps.setDouble(3, idRegistro);
                    ps.execute();
                    Telegram telegram = new Telegram();
                    telegram.enviarAlerta(String.format("\uD83D\uDD21 "+"Atenção! Armazenamento próximo ao nivel critico para a máquina %s",logAcessoClass.getFkMaquina()));
                } else if (porcentUsado > discoLimite) {
                    // System.out.println("test CRITICO");
                    ps = Conexao.getConexaoMSSQL().prepareStatement(insertAlertaLog);
                    ps.setString(1,"Armazenamento");
                    ps.setString(2,"Crítico");
                    ps.setDouble(3, idRegistro);
                    ps.execute();
                    Telegram telegram = new Telegram();
                    telegram.enviarAlerta(String.format("\uD83D\uDEA8 "+"Atenção! Armazenamento à nivel **CRÍTICO** para a máquina %s",logAcessoClass.getFkMaquina()));

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }
}
