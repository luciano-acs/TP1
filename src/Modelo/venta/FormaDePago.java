package Modelo.venta;

public enum FormaDePago {

    EFECTIVO("EFECTIVO"),
    TARJERA("TARJETA");
    
    private String fdp;

    private FormaDePago(String fdp) {
        this.fdp = fdp;
    }
    
    public String getFdp() {
        return fdp;
    }

    public void setFdp(String fdp) {
        this.fdp = fdp;
    }
}
