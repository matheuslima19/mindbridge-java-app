package Classes;
public class RegistrosPC {
    public Double usoProcessador;
    public Long memoriaUso;
    public Long discoUso;
    public static Integer fkMaquinas;
    public Double getUsoProcessador() {
        return usoProcessador;
    }
    public void setUsoProcessador (Double usoProcessador){
        this.usoProcessador = usoProcessador;
    }
    public Long getMemoriaUso() {
        return memoriaUso;
    }
    public void setMemoriaUso(Long memoriaUso) {
        this.memoriaUso = memoriaUso;
    }
    public Long getDiscoUso() {
        return discoUso;
    }
    public void setDiscoUso(Long discoUso) {
        this.discoUso = discoUso;
    }
    public Integer getFkMaquinas() {return fkMaquinas;}
    public void setFkMaquinas(Integer fkMaquinas) {this.fkMaquinas = fkMaquinas;}

}
