package Modelo.venta;

public class FormaDePago {
    
    private int idForma;
    private String descripcion;

    public FormaDePago() {
        
    }

    public int getIdForma() {
        return idForma;
    }

    public void setIdForma(int idForma) {
        this.idForma = idForma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public FormaDePago(int idForma, String descripcion) {
        this.idForma = idForma;
        this.descripcion = descripcion;
    }

    
}
