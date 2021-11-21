package Modelo.BD;

import Modelo.producto.Producto;
import Modelo.producto.Stock;
import Modelo.venta.Venta;
import java.util.ArrayList;

/**
 *
 * @author Luciano Acosta
 */
public class BD {
    
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static ArrayList<Venta> ventas = new ArrayList<>();
    private static ArrayList<Stock> stocks = new ArrayList<>();

    public static ArrayList<Producto> getProductos() {
        return productos;
    }

    public static void setProductos(ArrayList<Producto> productos) {
        BD.productos = productos;
    }

    public static ArrayList<Venta> getVentas() {
        return ventas;
    }

    public static void setVentas(ArrayList<Venta> ventas) {
        BD.ventas = ventas;
    }

    public static ArrayList<Stock> getStocks() {
        return stocks;
    }

    public static void setStocks(ArrayList<Stock> stocks) {
        BD.stocks = stocks;
    }    
    
    public void agregarProducto(Producto producto){
        productos.add(producto);
    }
    public ArrayList<Producto> listarProductos(){
        return productos;        
    }
    
    public void agregarVenta(Venta venta){
        ventas.add(venta);
    }
    
    public ArrayList<Venta> listarVentas(){
        return ventas;
    }
    
    public void agregarStock(Stock stock){
        stocks.add(stock);
    }
    public ArrayList<Stock> listarStock(){
        return stocks;
    }
    public void limpiarStock(){
        stocks.clear();
    }
}
