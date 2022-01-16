package Modelo.venta;

public class Pago {

    private double pago;
    private FormaDePago fdp;
    private Venta venta;

    public Pago(double pago, FormaDePago fdp, Venta venta) {
        this.pago = pago;
        this.fdp = fdp;
        this.venta = venta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public FormaDePago getFdp() {
        return fdp;
    }

    public void setFdp(FormaDePago fdp) {
        this.fdp = fdp;
    }
}
