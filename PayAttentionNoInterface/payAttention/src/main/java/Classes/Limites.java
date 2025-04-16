package Classes;

public class Limites {

    public static Integer id;
    public static Double cpuPorcent;
    public static Double ramPorcent;
    public static Double discoPorcent;
    public static Integer fkInstituicao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCpuPorcent() {
        return cpuPorcent;
    }

    public void setCpuPorcent(Double cpuPorcent) {
        this.cpuPorcent = cpuPorcent;
    }

    public Double getRamPorcent() {
        return ramPorcent;
    }

    public void setRamPorcent(Double ramPorcent) {
        this.ramPorcent = ramPorcent;
    }

    public Double getDiscoPorcent() {
        return discoPorcent;
    }

    public void setDiscoPorcent(Double discoPorcent) {
        this.discoPorcent = discoPorcent;
    }

    public Integer getFkInstituicao() {
        return fkInstituicao;
    }

    public void setFkInstituicao(Integer fkInstituicao) {
        this.fkInstituicao = fkInstituicao;
    }

    @Override
    public String toString() {
        return "Limites{" +
                "id=" + id +
                "cpuPorcent=" + cpuPorcent +
                ", ramPorcent=" + ramPorcent +
                ", discoPorcent=" + discoPorcent +
                ", fkInstituicao=" + fkInstituicao +
                '}';
    }
}
