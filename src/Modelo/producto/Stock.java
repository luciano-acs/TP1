package Modelo.producto;
import Modelo.BD.BD;
import java.util.ArrayList;
import Modelo.venta.LineaDeVenta;

public class Stock {

//    private Producto producto;
    private int id;
    private int cantidad;
//    private Talle talle;
//    private ColorP color;
    

    public Stock(int id, int cantidad) {
//        this.producto = producto;
        this.id = id;
        this.cantidad = cantidad;
//        this.talle = talle;
//        this.color = color;
    }

    public Stock() {
        
    }

//    public Producto getProducto() {
//        return producto;
//    }
//
//    public void setProducto(Producto producto) {
//        this.producto = producto;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

//    public Talle getTalle() {
//        return talle;
//    }
//
//    public void setTalle(Talle talle) {
//        this.talle = talle;
//    }
//
//    public ColorP getColor() {
//        return color;
//    }
//
//    public void setColor(ColorP color) {
//        this.color = color;
//    }

    @Override
    public String toString() {
        return "Stock{" +
                ", cantidad=" + cantidad +
                '}';
    }

    public void actualizarStock(int codigo, int vendido) {
        
        BD bd = new BD();
               
//        for(int i=0; i<bd.getStocks().size();i++){ 
//            if(bd.getStocks().get(i).getProducto().getCodigo() == codigo){
//                int nvaCantidad = bd.getStocks().get(i).getCantidad() - vendido;
//                bd.getStocks().get(i).setCantidad(nvaCantidad);                
//            }
//        }
    }   
}
