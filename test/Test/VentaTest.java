package Test;

import Modelo.BD.BD;
import Modelo.Organizacion.Empleado;
import Modelo.cliente.Cliente;
import Modelo.cliente.CondTributaria;
import Modelo.producto.ColorP;
import Modelo.producto.Marca;
import Modelo.producto.Producto;
import Modelo.producto.Rubro;
import Modelo.producto.Stock;
import Modelo.producto.Talle;
import Modelo.venta.FormaDePago;
import Modelo.venta.LineaDeVenta;
import Modelo.venta.Pago;
import Modelo.venta.Venta;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Luciano Acosta
 */
public class VentaTest {
    
    public VentaTest() {
    }
    
    @Test
    public void vincularClienteAVenta(){
        //CONFIGURACION O DATOS
        ArrayList<LineaDeVenta> lista = new ArrayList<>();      
        
        Cliente cliente = new Cliente("Belgrano","2014267423","Ejercito del norte 1500",CondTributaria.Consumidor_Final);
        Empleado empleado = new Empleado();
        Pago pago = new Pago(1500,FormaDePago.EFECTIVO);
        Producto producto = new Producto(001,"Remera",600,0.21,500,0.2,Marca.Adidas);
        LineaDeVenta linea = new LineaDeVenta(producto,1);
        lista.add(linea);

        //EJECUCION
        Venta venta = new Venta("17/11/2021",001,600,cliente,lista,pago,empleado);

        //COMPROBACION
        Assert.assertEquals(cliente,venta.getCliente());
    }

    @Test
    public void actualizarStockConVenta(){
        //CONFIGURACION O DATOS
        Producto producto = new Producto();

        Producto producto1 = new Producto(1,"Remera",producto.calcularPrecio(500,0.21,0.2),0.21,500,0.2,Marca.Adidas);
        Stock stock = new Stock(producto1,150,Talle.S,ColorP.AZUL,Rubro.MUJER);
        BD bd = new BD();
        bd.agregarStock(stock);
        int codigo = 1;
        int vendido = 5;
        
        //EJECUCION
        stock.actualizarStock(codigo,vendido);

        //COMPROBACION
        Assert.assertEquals(145,bd.getStocks().get(0).getCantidad());
    }
//
    @Test
    public void calcularTotalVenta(){

        //CONFIGURACION
        
        Venta venta = new Venta();
        Producto producto = new Producto();        
        ArrayList<LineaDeVenta> lista = new ArrayList();
        
        Producto producto1 = new Producto(001,"Remera",producto.calcularPrecio(500,0.21,0.2),0.21,500,0.2,Marca.Adidas);
        Producto producto2 = new Producto(002,"Pantalon",producto.calcularPrecio(200,0.21,0.2),0.21,200,0.2,Marca.Nike);
        
        LineaDeVenta linea1 = new LineaDeVenta(producto1,1);
        LineaDeVenta linea2 = new LineaDeVenta(producto2,1);
        
        lista.add(linea1);
        lista.add(linea2);

        for(int i =0; i<lista.size();i++){
            venta.agregarLinea(lista.get(i));
        }
        
        double total = producto.calcularPrecio(500,0.21,0.2) + producto.calcularPrecio(200,0.21,0.2);
        //EJECUCION
        venta.calcularTotal();

        //COMPROBACION
        Assert.assertEquals(total,venta.getTotal(),0);

    }    
}
