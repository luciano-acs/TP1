package Modelo.cliente;

public class Cliente {

    private String razonSocial;
    private String cuit;
    private String domicilio;
    private CondTributaria condicion;

    public Cliente(String razonSocial, String cuit, String domicilio, CondTributaria condicion) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.condicion = condicion;
    }

    public CondTributaria getCondicion() {
        return condicion;
    }

    public void setCondicion(CondTributaria condicion) {
        this.condicion = condicion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Cliente{" + "razonSocial=" + razonSocial + ", cuit=" + cuit + ", domicilio=" + domicilio + ", condicion=" + condicion + '}';
    }

    
}
