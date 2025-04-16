/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import DAO.AlertaRegMaquinaDAO;
import DAO.RegistrosDAO;
import Classes.RegistrosPC;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
/**
 *
 * @author Matheus
 */
public class Captura extends javax.swing.JFrame{
    public static final long TEMPO = (5000);
    static Timer timer = null;
    public void capturaDados() throws SQLException {
        JOptionPane.showMessageDialog(rootPane, "Iniciando a captura de dados em segundo plano...");
         RegistrosPC registros = new RegistrosPC();
         /*--------------------------------------------------------------*/
        /*Inicio Looca para captura de dados registro de maquinas*/
        Looca looca = new Looca();
        Memoria memoria = looca.getMemoria();
        DiscoGrupo disco = looca.getGrupoDeDiscos();
        Processador processador = looca.getProcessador();
        if (timer == null) {
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {
                    try {
                            Long memoriaUso = memoria.getEmUso();
                            Long usoDisco = disco.getTamanhoTotal();
                            registros.setMemoriaUso(memoriaUso);
                            registros.setUsoProcessador(processador.getUso());
                            registros.setDiscoUso(usoDisco);
                            System.out.println(registros);
                            RegistrosDAO.inserirRegistros(registros);

                        AlertaRegMaquinaDAO alertaMaq = new AlertaRegMaquinaDAO();
                        alertaMaq.registrarAlertaMaquina();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }
    }
}
