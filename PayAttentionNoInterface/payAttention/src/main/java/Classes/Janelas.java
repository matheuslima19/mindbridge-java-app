package Classes;

import java.sql.Array;
import java.util.List;

public class Janelas {
    private Integer PID;
    private Integer idJanela;
    private String titulo;
    private String comando;
    private String localizacao;

    public Integer getPID() {
        return PID;
    }

    public void setPID(Integer PID) {
        this.PID = PID;
    }

    public Integer getIdJanela() {
        return idJanela;
    }

    public void setIdJanela(Integer idJanela) {
        this.idJanela = idJanela;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Janelas{" +
                "PID=" + PID +
                ", idJanela=" + idJanela +
                ", titulo='" + titulo + '\'' +
                ", comando='" + comando + '\'' +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
}

