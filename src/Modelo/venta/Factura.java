package Modelo.venta;

public class Factura {

   private int numFact;
   private String fecha;
   private double total;
   private Venta venta;

    public Factura(int numFact, String fecha, double total, Venta venta) {
        this.numFact = numFact;
        this.fecha = fecha;
        this.total = total;
        this.venta = venta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public int getNumFact() {
        return numFact;
    }

    public void setNumFact(int numFact) {
        this.numFact = numFact;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Factura{" + "numFact=" + numFact + ", fecha=" + fecha + ", total=" + total + ", venta=" + venta + '}';
    }
}
