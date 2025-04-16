package Classes;

public class CadastroMaquina {
    public static Integer idMaquina;
    public static String nomeComputador;
    public static String sistemaOperacional;
    public static String nomeProcessador;
    public static Long quantidadeRAM;
    public static Long armazenamentoHD;

    public Integer getIdMaquina() {return idMaquina;}
    public void setIdMaquina(Integer idMaquina) {this.idMaquina = idMaquina;}
    public String getNomeComputador() {return nomeComputador;}
    public void setNomeComputador(String nomeComputador) {this.nomeComputador = nomeComputador;}
    public String getSistemaOperacional() {
        return sistemaOperacional;
    }
    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }
    public String getNomeProcessador() {
        return nomeProcessador;
    }
    public void setNomeProcessador(String nomeProcessador) {
        this.nomeProcessador = nomeProcessador;
    }
    public Long getQuantidadeRAM() {
        return quantidadeRAM;
    }
    public void setQuantidadeRAM(Long quantidadeRAM) {
        this.quantidadeRAM = quantidadeRAM;
    }
    public Long getArmazenamentoHD() {
        return armazenamentoHD;
    }
    public void setArmazenamentoHD(Long armazenamentoHD) {this.armazenamentoHD = armazenamentoHD;}

    @Override
    public String toString() {
        return "CadastroMaquina{" +
                "idMaquina=" + idMaquina +
                ", nomeComputador='" + nomeComputador + '\'' +
                ", sistemaOperacional='" + sistemaOperacional + '\'' +
                ", nomeProcessador='" + nomeProcessador + '\'' +
                ", quantidadeRAM=" + quantidadeRAM +
                ", armazenamentoHD=" + armazenamentoHD +
                '}';
    }
}
