package DAO;
import Classes.UsuarioLogin;
import ConexaoBanco.Conexao;
import Classes.Limites;

import java.sql.*;

public class LimitesDAO {

    UsuarioLogin usuarioLogin;

    public boolean listarLimites() throws SQLException {

        usuarioLogin = new UsuarioLogin();

        /* Recuperar id da instituicao*/
        String selectInstituicao = String.format("select t.fkInstituicao from UsuarioAluno ua join Turma t on ua.fkTurma = t.id join InstituicaoEnsino ie on t.fkInstituicao = ie.id where ua.email = '%s'", usuarioLogin.getEmail());

        Connection conn = null;
        Statement stmtLimite = null;
        Statement stmtInst = null;

        conn = Conexao.getConexaoMSSQL();
        stmtLimite = conn.createStatement();
        stmtInst = conn.createStatement();

        try {
            ResultSet rsInst = stmtInst.executeQuery(selectInstituicao);

            if (rsInst.next()) {
                Integer idInst = rsInst.getInt("fkInstituicao");
                String selectLimites = "SELECT * FROM Limites WHERE fkInstituicao = %d".formatted(idInst);
                ResultSet rsLimites = stmtLimite.executeQuery(selectLimites);

                if (rsLimites.next()) {
                    Limites limites = new Limites();
                    limites.setId(rsLimites.getInt("id"));
                    limites.setCpuPorcent(rsLimites.getDouble("cpuPorcent"));
                    limites.setRamPorcent(rsLimites.getDouble("ramPorcent"));
                    limites.setDiscoPorcent(rsLimites.getDouble("discoPorcent"));

                    System.out.println("CPU:" + limites.getCpuPorcent());
                    System.out.println("RAM:" + limites.getRamPorcent());
                }
            } else {
                System.out.println("Usuário não encontrado");
            }
        } catch (
                SQLException ex ) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return true;
    }

}
