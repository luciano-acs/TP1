package Modelo.venta;

public class Pago {

    private double pago;
    private FormaDePago fdp;

    public Pago(double pago, FormaDePago fdp) {
        this.pago = pago;
        this.fdp = fdp;
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
