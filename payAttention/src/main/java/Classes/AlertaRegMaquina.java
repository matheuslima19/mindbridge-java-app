package Classes;

public class AlertaRegMaquina {
    public Integer id;
    public String componente;
    public String tipo;
    public Integer fkRegistro;

    public AlertaRegMaquina() {
        this.id = null;
        this.componente = null;
        this.tipo = null;
        this.fkRegistro = null;
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

    public Integer getfkRegistro() {
        return fkRegistro;
    }

    public void setfkRegistro(Integer fkRegistro) {
        this.fkRegistro = fkRegistro;
    }
}
