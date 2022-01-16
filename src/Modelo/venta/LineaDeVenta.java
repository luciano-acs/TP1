package Modelo.venta;
import Modelo.producto.Producto;
public class LineaDeVenta {

    private Producto producto;
    private int cantidad;

    public LineaDeVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public LineaDeVenta() {
        
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
