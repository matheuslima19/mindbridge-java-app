package Models;
import DAO.JanelasDAO;
import Classes.Janelas;
import com.github.britooo.looca.api.core.Looca;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CapturaJanelas {
    public static final long TEMPO = (30000);
    static Timer timer = null;
    public void capturaJanelas() throws SQLException {
        Looca looca = new Looca();
        Janelas janelas = new Janelas();


        if (timer == null) {
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < looca.getGrupoDeJanelas().getJanelasVisiveis().size(); i++) {
                            Integer PID = Math.toIntExact(looca.getGrupoDeJanelas().getJanelasVisiveis().get(i).getPid());
                            Integer idJanela = Math.toIntExact(looca.getGrupoDeJanelas().getJanelasVisiveis().get(i).getJanelaId());
                            String tituloJanela = looca.getGrupoDeJanelas().getJanelasVisiveis().get(i).getTitulo();
                            String comandoJanela = looca.getGrupoDeJanelas().getJanelasVisiveis().get(i).getComando();
                            String dividir[] = {};
                            if (comandoJanela.contains("\\")) {
                                dividir = comandoJanela.split("\\\\");

                            } else if (comandoJanela.contains("/")) {
                                dividir = comandoJanela.split("/");
                            }

                            List<String> listarPartes = Arrays.stream(dividir).toList();
                            String ultimaParte = listarPartes.get(listarPartes.size()-1); // equivale ao nome do programa
                            String localizacaoJanela = String.valueOf(looca.getGrupoDeJanelas().getJanelasVisiveis().get(i).getLocalizacaoETamanho());
                            janelas.setPID(PID);
                            janelas.setIdJanela(idJanela);
                            janelas.setTitulo(ultimaParte);
                            janelas.setComando(comandoJanela);
                            janelas.setLocalizacao(localizacaoJanela);
                            if(tituloJanela != ""){
                                JanelasDAO.cadastrarJanelas(janelas);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }

    }
}
