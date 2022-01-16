package Modelo.producto;

public class Producto {

    private int codigo;
    private String descripcion;
    private double porcIVA;
    private double costo;
    private double margenGanancia;
    private Marca marca;
    private Rubro rubro;
    private Stock stock;
    private int visible;
    
    public Producto(int codigo, String descripcion, double porcIVA, double costo, double margenGanancia, Marca marca, Rubro rubro, Stock stock, int visible) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.porcIVA = porcIVA;
        this.costo = costo;
        this.margenGanancia = margenGanancia;
        this.marca = marca;
        this.rubro = rubro;
        this.stock = stock;
        this.visible = visible;
    }
    
    public Producto(){
        
    }

    public Producto(int codigo, String descripcion, double iva, double costo, double margen, Marca m, Rubro r, int visible) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.porcIVA = iva;
        this.costo = costo;
        this.margenGanancia = margen;
        this.marca = m;
        this.rubro = r;
        this.visible = visible;
    }

    public int isVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
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

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", descripcion=" + descripcion + ", porcIVA=" + porcIVA + ", costo=" + costo + ", margenGanancia=" + margenGanancia + ", marca=" + marca + ", rubro=" + rubro + '}';
    }
      
    
    public double calcularPrecio(double costo, double porcIVA, double margen) {
        
        double neto = costo + (costo*margen);
        double iva = neto * 0.21;
        double precio = neto + iva;
        return precio;        
    }  

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
