package Modelo.venta;

public class PuntoDeVenta {

    private int idPDV;
    private String descripcion;

    public PuntoDeVenta(int idPDV, String descripcion) {
        this.idPDV = idPDV;
        this.descripcion = descripcion;
    }

    public int getIdPDV() {
        return idPDV;
    }

    public void setIdPDV(int idPDV) {
        this.idPDV = idPDV;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
