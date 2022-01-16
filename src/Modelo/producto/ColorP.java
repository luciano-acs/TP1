package Modelo.producto;

public class ColorP {

    private int idColor;
    private String descripcion;

    public ColorP(int idColor, String descripcion) {
        this.idColor = idColor;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    @Override
    public String toString() {
        return "Color{" + "idColor=" + idColor + ", descripcion=" + descripcion + '}';
    }
    
    
    
    
}
