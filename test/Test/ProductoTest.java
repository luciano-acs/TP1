package Test;

import Modelo.producto.Marca;
import Modelo.producto.Producto;
import Modelo.venta.LineaDeVenta;
import Modelo.venta.Venta;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Luciano Acosta
 */
public class ProductoTest {
    
    public ProductoTest(){
    
    }
    
    @Test
    public void agregarLineaDeProductosAVenta(){

        //Configuracion o datos
        Venta venta = new Venta();
        Producto producto = new Producto();        
        ArrayList<LineaDeVenta> lista = new ArrayList();
        
        Producto producto1 = new Producto(001,"Remera",producto.calcularPrecio(500,0.21,0.2),0.21,500,0.2,Marca.Adidas);
        Producto producto2 = new Producto(002,"Pantalon",producto.calcularPrecio(200,0.21,0.2),0.21,200,0.2,Marca.Nike);
        
        LineaDeVenta linea1 = new LineaDeVenta(producto1,1);
        LineaDeVenta linea2 = new LineaDeVenta(producto2,1);
        
        lista.add(linea1);
        lista.add(linea2);
        
        //Ejecucion
        for(int i =0; i<lista.size();i++){
            venta.agregarLinea(lista.get(i));
        }
        
        
        //comprobacion
        Assert.assertEquals(lista.size(),venta.getLista().size());
        for(int i =0; i<lista.size();i++){
            Assert.assertEquals(lista.get(i),venta.getLista().get(i));
        }
    }

    @Test
    public void calcularPrecioVentaProducto(){
        //CONFIGURACION O DATOS       
        
        double costo = 600;
        double porcIVA = 0.21;
        double margen = 0.2;
        double precio = (costo + (costo*margen))+((costo + (costo*margen))*porcIVA);
        
        //EJECUCION
        Producto producto = new Producto(001,"Remera",precio,porcIVA,costo,margen,Marca.Adidas);
        producto.calcularPrecio(costo,porcIVA,margen);

        //COMPROBACION
        Assert.assertEquals(precio,producto.getPrecioVenta(),1);
    }
}
