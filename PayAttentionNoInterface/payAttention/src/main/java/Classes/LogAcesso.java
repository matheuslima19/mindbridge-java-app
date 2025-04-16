package Classes;

public class LogAcesso {
    public static String dtRegistro;
    public static String dtInicializacao;
    public static Double armUsado;
    public static Integer fkUsuario;
    public static Integer fkMaquina;

    public String getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(String dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public String getDtInicializacao() {
        return dtInicializacao;
    }

    public void setDtInicializacao(String dtInicializacao) {
        this.dtInicializacao = dtInicializacao;
    }

    public Double getArmUsado() {
        return armUsado;
    }

    public void setArmUsado(Double armUsado) {
        this.armUsado = armUsado;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Integer getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    @Override
    public String toString() {
        return "LogAcesso{" +
                "dtRegistro='" + dtRegistro + '\'' +
                ", dtInicializacao='" + dtInicializacao + '\'' +
                ", armUsado=" + armUsado +
                ", fkUsuario=" + fkUsuario +
                ", fkMaquina=" + fkMaquina +
                '}';
    }
}
