package Modelo.producto;

public class Rubro {
    private int codRubro;
    private String descripcionR;

    public Rubro(int codRubro, String descripcionR) {
        this.codRubro = codRubro;
        this.descripcionR = descripcionR;
    }

    public String getDescripcionR() {
        return descripcionR;
    }

    public void setDescripcionR(String descripcionR) {
        this.descripcionR = descripcionR;
    }

    public int getCodRubro() {
        return codRubro;
    }

    public void setCodRubro(int codRubro) {
        this.codRubro = codRubro;
    }

    @Override
    public String toString() {
        return "Rubro{" + "codRubro=" + codRubro + ", descripcionR=" + descripcionR + '}';
    }
    
    
}
