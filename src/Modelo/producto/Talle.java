package Modelo.producto;

public class Talle {
    private int idTalle;
    private String descripcion;

    public Talle(int idTalle, String descripcion) {
        this.idTalle = idTalle;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTalle() {
        return idTalle;
    }

    public void setIdTalle(int idTalle) {
        this.idTalle = idTalle;
    }

    @Override
    public String toString() {
        return "Talle{" + "idTalle=" + idTalle + ", descripcion=" + descripcion + '}';
    }
    
}
