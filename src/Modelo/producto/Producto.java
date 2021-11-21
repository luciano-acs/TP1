package Modelo.producto;

public class Producto {

    private int codigo;
    private String descripcion;
    private double precioVenta;
    private double porcIVA;
    private double costo;
    private double margenGanancia;
    private Marca marca;

    public Producto(int codigo, String descripcion, double precioVenta, double porcIVA, double costo, double margenGanancia, Marca marca) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.porcIVA = porcIVA;
        this.costo = costo;
        this.margenGanancia = margenGanancia;
        this.marca = marca;
    }
    
    public Producto(){
        
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPorcIVA() {
        return porcIVA;
    }

    public void setPorcIVA(double porcIVA) {
        this.porcIVA = porcIVA;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getMargenGanancia() {
        return margenGanancia;
    }

    public void setMargenGanancia(double margenGanancia) {
        this.margenGanancia = margenGanancia;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", descripcion=" + descripcion + ", precioVenta=" + precioVenta + ", porcIVA=" + porcIVA + ", costo=" + costo + ", margenGanancia=" + margenGanancia + ", marca=" + marca + '}';
    }   
    
    public double calcularPrecio(double costo, double porcIVA, double margen) {
        
        double neto = costo + (costo*margen);
        double iva = neto * 0.21;
        double precio = neto + iva;
        return precio;        
    }     
}
