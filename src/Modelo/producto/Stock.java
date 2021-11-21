package Modelo.producto;
import Modelo.BD.BD;
import java.util.ArrayList;
import Modelo.venta.LineaDeVenta;

public class Stock {

    private Producto producto;
    private int cantidad;
    private Talle talle;
    private Color color;
    private Rubro rubro;
    

    public Stock(Producto producto, int cantidad, Talle talle, Color color, Rubro rubro) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.talle = talle;
        this.color = color;
        this.rubro = rubro;
    }

    public Stock() {
        
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

    public Talle getTalle() {
        return talle;
    }

    public void setTalle(Talle talle) {
        this.talle = talle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                ", talle=" + talle +
                ", color=" + color +
                ", rubro=" + rubro +
                '}';
    }

    public void actualizarStock(int codigo, int vendido) {
        
        BD bd = new BD();
               
        for(int i=0; i<bd.getStocks().size();i++){ 
            if(bd.getStocks().get(i).getProducto().getCodigo() == codigo){
                int nvaCantidad = bd.getStocks().get(i).getCantidad() - vendido;
                bd.getStocks().get(i).setCantidad(nvaCantidad);                
            }
        }
    }   
}
