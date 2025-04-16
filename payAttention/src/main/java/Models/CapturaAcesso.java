package Models;

import DAO.LogAcessoDAO;
import Classes.LogAcesso;
import com.github.britooo.looca.api.core.Looca;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class CapturaAcesso {
    public void capturaAcesso() throws SQLException {
        Looca looca = new Looca();
        LogAcesso logAcesso = new LogAcesso();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        logAcesso.setDtInicializacao(String.valueOf(dataHoraAtual));
        Integer armUsado = 0;
        Long armTotal = (long) (looca.getGrupoDeDiscos().getVolumes().get(0).getTotal()/ (Math.pow(1024, 3)));
        Long armDisp = (long) (looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel() / (Math.pow(1024, 3)));
        armUsado = Math.toIntExact(armTotal - armDisp);
        logAcesso.setArmUsado(Double.valueOf(armUsado));
        System.out.println(logAcesso.getArmUsado());
        System.out.println(logAcesso.getDtInicializacao());
        LogAcessoDAO logAcessoDAO = new LogAcessoDAO();
        logAcessoDAO.logAcesso(logAcesso);
    }
}
