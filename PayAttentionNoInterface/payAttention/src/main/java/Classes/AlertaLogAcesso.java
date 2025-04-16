package Classes;

public class AlertaLogAcesso {

    public Integer id;
    public String componente;
    public String tipo;
    public Integer fkLogAcesso;

    public AlertaLogAcesso() {
        this.id = null;
        this.componente = null;
        this.tipo = null;
        this.fkLogAcesso = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getFkLogAcesso() {
        return fkLogAcesso;
    }

    public void setFkLogAcesso(Integer fkLogAcesso) {
        this.fkLogAcesso = fkLogAcesso;
    }

    @Override
    public String toString() {
        return "AlertaLogAcesso{" +
                "id=" + id +
                ", componente='" + componente + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fkLogAcesso=" + fkLogAcesso +
                '}';
    }
}
