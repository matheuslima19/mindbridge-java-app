package DAO;

import Classes.CadastroMaquina;
import Classes.Limites;
import Classes.LogAcesso;
import Classes.Telegram;
import ConexaoBanco.Conexao;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertaRegMaquinaDAO {
    Limites limites;
    CadastroMaquina maquina;
    public boolean registrarAlertaMaquina() throws SQLException {

        limites = new Limites();
        maquina = new CadastroMaquina();

        LogAcesso logAcessoClass = new LogAcesso();
        Looca looca = new Looca();
        Rede rede = looca.getRede();
        String nomeComputador = rede.getParametros().getHostName();

        Double ramMaquina = Double.valueOf(maquina.getQuantidadeRAM())/Math.pow(1024,3);
        Double ramLimite = limites.getRamPorcent();
        Double cpuLimite = limites.getCpuPorcent();
        Double porcentRamUsada;
        List<Double> ramAtual = new ArrayList<>();
        List<Double> cpuAtual = new ArrayList<>();
        List<Integer> idReg = new ArrayList<>();
        List<Integer> idRegDocker = new ArrayList<>();

        Connection conn = null;
        Connection connDocker = null;
        PreparedStatement ps = null;
        PreparedStatement psDocker = null;
        Statement stmtRegistro = null;
        Statement stmtRegistroDocker = null;
        Statement stmtIdMaquina = null;
        Statement stmtIdMaquinaDocker = null;

        conn = Conexao.getConexaoMSSQL();
        connDocker = Conexao.getConexaoDocker();
        stmtRegistro = conn.createStatement();
        stmtRegistroDocker = connDocker.createStatement();
        stmtIdMaquina = conn.createStatement();
        stmtIdMaquinaDocker = connDocker.createStatement();

        String insertAlerta = "insert into Alertas(componente, tipo, fkRegistro) values (?,?,?)";

        String selectIdMaquina = "SELECT id FROM Maquinas WHERE hostname = '%s'".formatted(maquina.getNomeComputador());
        String selectIdMaquinaDocker = "SELECT id FROM Maquina WHERE hostname = '%s'".formatted(maquina.getNomeComputador());

        try{
            ResultSet rsMaquinaDocker = stmtIdMaquinaDocker.executeQuery(selectIdMaquinaDocker);
            if (rsMaquinaDocker.next()) {
                Integer idMaquinaDocker = rsMaquinaDocker.getInt("id");
                String selectRegDocker = "SELECT * FROM RegistroMaquina WHERE fkMaquina = %d ORDER BY dtRegistro DESC LIMIT 3;".formatted(idMaquinaDocker);

                ResultSet rsRegistroDocker = stmtRegistroDocker.executeQuery(selectRegDocker);

                while (rsRegistroDocker.next()) {
                    idRegDocker.add(rsRegistroDocker.getInt("id"));
                }
            }

            ResultSet rsMaquina = stmtIdMaquina.executeQuery(selectIdMaquina);
            if (rsMaquina.next()) {
                Integer idMaquina = rsMaquina.getInt("id");
                String selectReg = "SELECT TOP 3 * FROM RegistroMaquina WHERE fkMaquinas = %d ORDER BY dtRegistro DESC;".formatted(idMaquina);
                ResultSet rsRegistro = stmtRegistro.executeQuery(selectReg);

                while (rsRegistro.next()) {
                    porcentRamUsada = (100*rsRegistro.getDouble("usoRam"))/ramMaquina;
                    ramAtual.add(porcentRamUsada);
                    cpuAtual.add((rsRegistro.getDouble("usoProcessador")));
                    idReg.add(rsRegistro.getInt("id"));
                }
            }
            System.out.println(ramAtual.toString());

            if (ramAtual.size() >= 3) {
                if (ramAtual.get(2) >= (ramLimite - (ramLimite*0.1)) && ramAtual.get(1) >= (ramLimite - (ramLimite*0.1))) {
                    if (ramAtual.get(0) >= (ramLimite - (ramLimite * 0.1))) {
                        if (ramAtual.get(0) >= (ramLimite - (ramLimite * 0.1)) && ramAtual.get(0) < ramLimite) {
                            /*inserir com atenção*/
                            try {
                                ps = Conexao.getConexaoMSSQL().prepareStatement(insertAlerta);
                                ps.setString(1, "RAM");
                                ps.setString(2, "Atenção");
                                ps.setInt(3, idReg.get(0));
                                ps.execute();

                                psDocker = Conexao.getConexaoDocker().prepareStatement(insertAlerta);
                                psDocker.setString(1,"RAM");
                                psDocker.setString(2,"Atenção");
                                psDocker.setInt(3,idRegDocker.get(0));
                                psDocker.execute();

                                Telegram telegram = new Telegram();
                                telegram.enviarAlerta(String.format("""
                                    \u26A0\uFE0F Atenção!
                                    Alerta: Atenção
                                    Componente: RAM
                                    Máquina %s
                                    """,nomeComputador));

                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }
                        } else {
                            /*inserir com critico*/
                            try {
                                ps = Conexao.getConexaoMSSQL().prepareStatement(insertAlerta);
                                ps.setString(1, "RAM");
                                ps.setString(2, "Crítico");
                                ps.setInt(3, idReg.get(0));
                                ps.execute();

                                psDocker = Conexao.getConexaoDocker().prepareStatement(insertAlerta);
                                psDocker.setString(1,"RAM");
                                psDocker.setString(2,"Crítico");
                                psDocker.setInt(3,idRegDocker.get(0));
                                psDocker.execute();

                                Telegram telegram = new Telegram();
                                telegram.enviarAlerta(String.format("""
                                    \uD83D\uDEA8 Atenção!
                                    Alerta:CRÍTICO
                                    Componente: RAM
                                    Máquina %s
                                    """,nomeComputador));
                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }
                        }
                    }
                }
            }

            if (cpuAtual.size() >= 3) {
                if (cpuAtual.get(2) >= (cpuLimite - (cpuLimite*0.1)) && cpuAtual.get(1) >= (cpuLimite - (cpuLimite*0.1))) {
                    if (cpuAtual.get(0) >= (cpuLimite - (cpuLimite*0.1))) {
                        if (cpuAtual.get(0) >= (cpuLimite - (cpuLimite*0.1)) && cpuAtual.get(0) < cpuLimite) {
                            /*inserir com atenção*/
                            try{
                                ps = Conexao.getConexaoMSSQL().prepareStatement(insertAlerta);
                                ps.setString(1,"CPU");
                                ps.setString(2,"Atenção");
                                ps.setInt(3,idReg.get(0));
                                ps.execute();

                                psDocker = Conexao.getConexaoDocker().prepareStatement(insertAlerta);
                                psDocker.setString(1,"CPU");
                                psDocker.setString(2,"Atenção");
                                psDocker.setInt(3,idRegDocker.get(0));
                                psDocker.execute();

                                Telegram telegram = new Telegram();
                                telegram.enviarAlerta(String.format("""
                                    \u26A0\uFE0F Atenção!
                                    Alerta: Atenção
                                    Componente: CPU
                                    Máquina %s
                                    """,nomeComputador));
                            } catch ( SQLException ex ) {
                                System.out.println(ex);
                            }
                        } else {
                            /*inserir com critico*/
                            try {
                                ps = Conexao.getConexaoMSSQL().prepareStatement(insertAlerta);
                                ps.setString(1,"CPU");
                                ps.setString(2,"Crítico");
                                ps.setInt(3,idReg.get(0));
                                ps.execute();

                                psDocker = Conexao.getConexaoDocker().prepareStatement(insertAlerta);
                                psDocker.setString(1,"CPU");
                                psDocker.setString(2,"Crítico");
                                psDocker.setInt(3,idRegDocker.get(0));
                                psDocker.execute();
                                Telegram telegram = new Telegram();
                                telegram.enviarAlerta(String.format("""
                                    \uD83D\uDEA8 Atenção!
                                    Alerta:CRÍTICO
                                    Componente: CPU
                                    Máquina %s
                                    """,nomeComputador));
                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }
                        }
                    }
                }
            }
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }

        return false;
    }
}
