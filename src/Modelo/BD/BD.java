package Modelo.BD;

import Modelo.Organizacion.Empleado;
import Modelo.cliente.Cliente;
import Modelo.producto.ColorP;
import Modelo.producto.Marca;
import Modelo.producto.Producto;
import Modelo.producto.Rubro;
import Modelo.producto.Stock;
import Modelo.producto.Talle;
import Modelo.producto.TipoDeTalle;
import Modelo.venta.LineaDeVenta;
import Modelo.venta.Venta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Luciano Acosta
 */
public class BD {
    
    Connection getConnection(){
        Connection c = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp1","root","2531");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return c;
    }

    public ArrayList<Producto> listarProductos() {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select a.codProducto, a.descripcion, a.costo, a.margenGanancia, a.porcIVA, a.visible, b.cantidad, c.idMarca, c.descripcion as Marca, d.idRubro, d.descripcion as Rubro from Producto as a, Stock as b, Marca as c, Rubro as d \n" +
                       "where a.Stock_idStock = b.idStock and\n" +
                       "a.Marca_idMarca = c.idMarca and\n" +
                       "a.Rubro_idRubro = d.idRubro and\n" +
                       "a.visible = 1";
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              Marca marca = new Marca(r.getInt("idMarca"),r.getString("Marca")); 
              Rubro rubro = new Rubro(r.getInt("idRubro"),r.getString("Rubro"));
              Producto p = new Producto();
              p.setCodigo(r.getInt("codProducto"));
              p.setDescripcion(r.getString("descripcion"));
              p.setPorcIVA(r.getDouble("porcIva"));
              p.setCosto(r.getDouble("costo"));
              p.setMargenGanancia(r.getDouble("margenGanancia"));              
              p.setMarca(marca);
              p.setRubro(rubro);
              p.setVisible(1);
              productos.add(p);
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return productos;
    }
    
    public Producto buscarProducto(String codigo){
        Producto p = new Producto();
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select a.codProducto, a.descripcion, a.costo, a.margenGanancia, a.porcIVA, b.cantidad, c.idMarca, c.descripcion as Marca, d.idRubro, d.descripcion as Rubro from Producto as a, Stock as b, Marca as c, Rubro as d \n" +
                       "where a.Stock_idStock = b.idStock and\n" +
                       "a.Marca_idMarca = c.idMarca and\n" +
                       "a.Rubro_idRubro = d.idRubro and a.codProducto = %1".replace("%1", codigo);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              Marca marca = new Marca(r.getInt("idMarca"),r.getString("Marca")); 
              Rubro rubro = new Rubro(r.getInt("idRubro"),r.getString("Rubro"));              
              p.setCodigo(r.getInt("codProducto"));
              p.setDescripcion(r.getString("descripcion"));
              p.setPorcIVA(r.getDouble("porcIva"));
              p.setCosto(r.getDouble("costo"));
              p.setMargenGanancia(r.getDouble("margenGanancia"));              
              p.setMarca(marca);
              p.setRubro(rubro);
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return p;
    }

    public ArrayList<Marca> listarMarcas() {
        ArrayList<Marca> marcas = new ArrayList<Marca>();
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select * from Marca";
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              Marca m = new Marca(r.getInt("idMarca"),r.getString("descripcion")); 
              
              marcas.add(m);
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return marcas;
    }

    public ArrayList<TipoDeTalle> listarTipoTalle() {
        ArrayList<TipoDeTalle> tipos = new ArrayList<TipoDeTalle>();
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select * from tipodetalle";
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              TipoDeTalle t = new TipoDeTalle(r.getInt("idTipoTalle"),r.getString("descripcion")); 
              
              tipos.add(t);
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return tipos;
    }

    public int buscarCodTipo(String buscar) {
        int cod = 0;
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select idTipoTalle from tipodetalle where descripcion='%1'".replace("%1", buscar);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              cod = r.getInt("idTipoTalle");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
       return cod;
    }
    
    public int buscarCodMarca(String marca) {
        int cod = 0;
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select idMarca from marca where descripcion='%1'".replace("%1", marca);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              cod = r.getInt("idMarca");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
       return cod;
    }

    public int buscarCodRubro(String rubro) {
        int cod = 0;
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select idRubro from rubro where descripcion='%1'".replace("%1", rubro);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              cod = r.getInt("idRubro");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
       return cod;
    }
    
    public int buscarCodTalle(String talle) {
        int cod = 0;
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select idTalle from talle where descripcion='%1'".replace("%1", talle);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              cod = r.getInt("idTalle");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
       return cod;
    }

    public int buscarCodColor(String color) {
        int cod = 0;
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select idColor from color where descripcion='%1'".replace("%1", color);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              cod = r.getInt("idColor");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
       return cod;
    }

    public ArrayList<Talle> buscarTalle(int buscarCodTipo) {
        ArrayList<Talle> talles = new ArrayList<Talle>();
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select distinct a.idTalle, a.descripcion from talle as a INNER jOIN tipodetalle as b where a.Tipodetalle_idTipoTalle = %1;".replace("%1",""+buscarCodTipo);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              Talle ta = new Talle(r.getInt("idTalle"),r.getString("descripcion")); 
              
              talles.add(ta);
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return talles;
    }

    public ArrayList<ColorP> listarColor() {
        ArrayList<ColorP> colores = new ArrayList<ColorP>();
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select * from color";
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              ColorP co = new ColorP(r.getInt("idColor"),r.getString("descripcion")); 
              
              colores.add(co);
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return colores;
    }

    public ArrayList<Rubro> listarRubros() {
        ArrayList<Rubro> rubros = new ArrayList<Rubro>();
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select * from rubro";
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              Rubro ru = new Rubro(r.getInt("idRubro"),r.getString("descripcion")); 
              
              rubros.add(ru);
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return rubros;
    } 

    public void agregarProducto(Producto p, Stock s, Marca m, Rubro r) {
        try{
            String cadena1 = "insert into stock values (%1,%2)"
            .replace("%1",""+s.getId())
            .replace("%2",""+s.getCantidad());            
            
            String cadena2 = "insert into producto values (%1,'%2',%3,%4,%5,%6,%7,%8,%9)"
           .replace("%1",""+p.getCodigo()) 
           .replace("%2",p.getDescripcion())
           .replace("%3",""+p.getCosto())  
           .replace("%4",""+p.getMargenGanancia())
           .replace("%5",""+p.getPorcIVA())
           .replace("%6",""+s.getId())
           .replace("%7",""+m.getCodMarca())
           .replace("%8",""+r.getCodRubro())
           .replace("%9",""+1);
            
            Connection c = getConnection();
            c.createStatement().executeUpdate(cadena1);    
            c.createStatement().executeUpdate(cadena2);
        }
        catch(Exception e){
            e.printStackTrace();
        } 
    }

//    public int ultimoCod() {
//        int cod = 0;
//        try {
//          Connection c = getConnection();
//          Statement s = c.createStatement();
//          String sql = "select max(idStock) from stock";
//          ResultSet r = s.executeQuery(sql);
//                  
//          while(r.next()){
//              cod = r.getInt("max(idStock)");
//          }          
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//        return cod+1;
//    }

    public Producto listarProducto(int codigo) {
        Producto p = new Producto();
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select a.codProducto, a.descripcion, a.costo, a.margenGanancia, a.porcIVA, a.Stock_idStock, a.Marca_idMarca, a.Rubro_idRubro, a.visible from producto as a where a.codProducto=%1"
                        .replace("%1",""+codigo);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              Marca m = new Marca(r.getInt("Marca_idMarca"),buscarDMarca(r.getInt("Marca_idMarca")));
              Rubro ru = new Rubro(r.getInt("Rubro_idRubro"),buscarDRubro(r.getInt("Rubro_idRubro")));
              Stock stock = new Stock(r.getInt("Stock_idStock"),buscarCStock(r.getInt("Stock_idStock")));
              p.setCodigo(r.getInt("codProducto"));
              p.setDescripcion(r.getString("descripcion"));
              p.setCosto(r.getDouble("costo"));
              p.setMargenGanancia(r.getDouble("margenGanancia"));
              p.setPorcIVA(r.getDouble("porcIVA"));
              p.setStock(stock);
              p.setMarca(m);
              p.setRubro(ru);
              p.setVisible(r.getInt("visible"));
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return p;
    }
    
    public String buscarDMarca(int marca) {
        String descripcion = "";
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select descripcion from marca where idMarca=%1".replace("%1",""+marca);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              descripcion = r.getString("descripcion");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
       return descripcion;
    }

    public String buscarDRubro(int rubro) {
        String descripcion = "";
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select descripcion from rubro where idRubro=%1".replace("%1",""+rubro);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              descripcion = r.getString("descripcion");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
       return descripcion;
    }
    
    public String buscarDColor(int color) {
        String descripcion = "";
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select descripcion from color where idcolor=%1".replace("%1",""+color);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              descripcion = r.getString("descripcion");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
       return descripcion;
    }
    public String buscarDTalle(int talle) {
        String descripcion = "";
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select descripcion from talle where idTalle=%1".replace("%1",""+talle);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              descripcion = r.getString("descripcion");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
       return descripcion;
    }
    public void modificarProducto(Producto p, Marca m, Rubro ru) {
        try{
            String cadena1 = "update producto set descripcion='%2', costo=%3, margenGanancia=%4, porcIVA=%5, Marca_idMarca=%6, Rubro_idRubro=%7 where codProducto = %1"
                            .replace("%1",""+p.getCodigo())
                            .replace("%2",p.getDescripcion())
                            .replace("%3",""+p.getCosto())
                            .replace("%4",""+p.getMargenGanancia())
                            .replace("%5",""+p.getPorcIVA())
                            .replace("%6",""+m.getCodMarca())
                            .replace("%7",""+ru.getCodRubro());
          
            Connection c = getConnection();
            c.createStatement().executeUpdate(cadena1); 
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public Cliente buscarCliente(String cuit) {
        Cliente cl = new Cliente();
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select a.cuit, a.razonSocial, a.domicilio, a.email, a.cond_tributaria from cliente as a where a.cuit=%1"
                        .replace("%1",""+cuit);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              cl.setCuit(r.getString("cuit"));
              cl.setRazonSocial(r.getString("razonSocial"));
              cl.setDomicilio(r.getString("domicilio"));
              cl.setCondicion(r.getString("cond_tributaria"));
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return cl;
    }

    private int buscarCStock(int idStock) {
        int cantidad = 0;
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select cantidad from stock where idStock='%1'".replace("%1",""+idStock);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              cantidad = r.getInt("cantidad");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
       return cantidad;
    }

    public void agregarStock(int cod, Talle t, ColorP co, int cantidadS) {
        try{
            String cadena1 = "insert into stock_has_talle values (%1,%2,%3)"
            .replace("%1",""+cod)
            .replace("%2",""+t.getIdTalle())
            .replace("%3",""+cantidadS);
            
            String cadena2 = "insert into color_has_stock values (%1,%2,%3)"
           .replace("%1",""+cod) 
           .replace("%2",""+co.getIdColor())
           .replace("%3",""+cantidadS);
            
            Connection c = getConnection();
            c.createStatement().executeUpdate(cadena1);    
            c.createStatement().executeUpdate(cadena2);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void eliminarProducto(int codProducto) {
        try{
            String cadena1 = "update producto set visible=0 where codProducto = %1"
                            .replace("%1",""+codProducto);
          
            Connection c = getConnection();
            c.createStatement().executeUpdate(cadena1); 
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<ColorP> listarStockColor(int cod) {
        ArrayList<ColorP> colores = new ArrayList<ColorP>();
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select DISTINCT a.idColor, a.descripcion, d.cantidad from color as a, producto as b, stock as c, color_has_stock as d \n" +
                        "where b.Stock_idStock = c.idStock and\n" +
                        "c.idStock = d.Stock_idStock and\n" +
                        "d.Color_idColor = a.idColor and\n" +
                        "b.codProducto = %1 and d.cantidad > 0".replace("%1",""+cod);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              ColorP co = new ColorP(r.getInt("idColor"),r.getString("descripcion"));
              colores.add(co);
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return colores;
    }

    public ArrayList<TipoDeTalle> listarStockTipoTalle(int cod) {
        ArrayList<TipoDeTalle> tipos = new ArrayList<TipoDeTalle>();
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select DISTINCT e.idTipoTalle, e.descripcion from talle as a, producto as b, stock as c, stock_has_talle as d, tipodetalle as e \n" +
                        "where b.Stock_idStock = c.idStock and\n" +
                        "c.idStock = d.Stock_idStock and\n" +
                        "d.Talle_idTalle = a.idTalle and\n" +
                        "a.Tipodetalle_idTipoTalle = e.idTipoTalle and\n" +
                        "b.codProducto = %1".replace("%1",""+cod);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              TipoDeTalle t = new TipoDeTalle(r.getInt("idTipoTalle"),r.getString("descripcion"));
              tipos.add(t);
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return tipos;
    }

    public ArrayList<Talle> buscarStockTalle(int cod, String tipo) {
        ArrayList<Talle> talles = new ArrayList<Talle>();
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select DISTINCT a.idTalle, a.descripcion, d.cantidad from talle as a, producto as b, stock as c, stock_has_talle as d, tipodetalle as e \n" +
                        "where b.Stock_idStock = c.idStock and\n" +
                        "c.idStock = d.Stock_idStock and\n" +
                        "d.Talle_idTalle = a.idTalle and\n" +
                        "a.Tipodetalle_idTipoTalle = e.idTipoTalle and\n" +
                        "b.codProducto = %1 and e.descripcion = '%2' and d.cantidad > 0"
                        .replace("%1",""+cod)
                        .replace("%2",tipo);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              Talle t = new Talle(r.getInt("idTalle"),r.getString("descripcion"));
              talles.add(t);
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return talles;
    }

    public void registrarVenta(Venta v, int pto) {
        try{
            String cadena1 = "insert into venta values (%1,'%2',%3,%4,%5)"
            .replace("%1",""+v.getNroComprobante())
            .replace("%2",v.getFecha())
            .replace("%3",""+v.getTotal())
            .replace("%4",""+pto)
            .replace("%5",""+v.getCliente().getCuit());
           
            Connection c = getConnection();
            c.createStatement().executeUpdate(cadena1);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void actualizarStock(int codigo, int cantidad, int id, Talle t, ColorP co) {
        try{
            String cadena1 = "update stock as a INNER JOIN producto as b set a.cantidad = a.cantidad - %1 where a.idStock = b.Stock_idStock and b.codProducto = %2"
            .replace("%1",""+cantidad)
            .replace("%2",""+codigo);             
                
            String cadena2 = "update stock_has_talle as a INNER JOIN talle as b, producto as c, stock as d set a.cantidad = a.cantidad - %1 where a.Stock_idStock = d.idStock and d.idStock = c.Stock_idStock and a.Talle_idTalle = b.idTalle and c.codProducto = %2 and b.idTalle = %3"
                              .replace("%1",""+cantidad)
                              .replace("%2",""+codigo)
                              .replace("%3",""+t.getIdTalle());
            
            String cadena3 = "update color_has_stock as a INNER JOIN color as b, producto as c, stock as d set a.cantidad = a.cantidad - %1 where a.Stock_idStock = d.idStock and d.idStock = c.Stock_idStock and a.Color_idColor = b.idColor and c.codProducto = %2 and b.idColor = %3"
                             .replace("%1",""+cantidad)
                             .replace("%2",""+codigo)
                             .replace("%3",""+co.getIdColor());
                
            String cadena4 = "insert into lineadeventa values (null,%2,%3,%4)"
            .replace("%2",""+cantidad)
            .replace("%3",""+id)
            .replace("%4",""+codigo);
                
            Connection c = getConnection();
            c.createStatement().executeUpdate(cadena1);   
            c.createStatement().executeUpdate(cadena2); 
            c.createStatement().executeUpdate(cadena3);   
            c.createStatement().executeUpdate(cadena4);                        
        }
        catch(Exception e){
            e.printStackTrace();
        } 
    }

    public int consultarStock(String codigo) {
        int cantidad = 0;
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select a.idStock, a.cantidad from stock as a where a.idStock=%1"
                        .replace("%1",""+codigo);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              cantidad = r.getInt("cantidad");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return cantidad;
    }

    public boolean existe(String codigo) {
        boolean existe = true;
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select count(codProducto) From producto where codProducto=%1 and visible=1"
                        .replace("%1",""+codigo);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              if(r.getInt("count(codProducto)")==0){
                  existe = false;
              }
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return existe;
    }
    
    public int cantTalle(String codigo, int talle){
        int cantidad = 0;
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select a.idTalle, a.descripcion, d.cantidad from talle as a, producto as b, stock as c, stock_has_talle as d \n" +
                        "where b.Stock_idStock = c.idStock and\n" +
                        "c.idStock = d.Stock_idStock and\n" +
                        "d.Talle_idTalle = a.idTalle and b.codProducto = %1 and a.idTalle = %2"
                        .replace("%1",""+codigo)
                        .replace("%2",""+talle);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              cantidad = r.getInt("cantidad");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return cantidad;
    }
    
    public int cantColor(String cod, int co){
        int cantidad = 0;
        try {
          Connection c = getConnection();
          Statement s = c.createStatement();
          String sql = "select a.idColor, a.descripcion, d.cantidad from color as a, producto as b, stock as c, color_has_stock as d \n" +
                        "where b.Stock_idStock = c.idStock and\n" +
                        "c.idStock = d.Stock_idStock and\n" +
                        "d.Color_idColor = a.idColor and\n" +
                        "b.codProducto = %1 and a.idColor=%2"
                        .replace("%1",""+cod)
                        .replace("%2",""+co);
          ResultSet r = s.executeQuery(sql);
                  
          while(r.next()){
              cantidad = r.getInt("cantidad");
          }          
        } catch(Exception e){
            e.printStackTrace();
        }
        return cantidad;
    }

    public void restaurarStock(String codigo, int cant, Talle t, ColorP co) {
        try{
            String cadena1 = "update stock as a INNER JOIN producto as b set a.cantidad = a.cantidad + %1 where a.idStock = b.Stock_idStock and b.codProducto = %2"
            .replace("%1",""+cant)
            .replace("%2",""+codigo);             
                
            String cadena2 = "update stock_has_talle as a INNER JOIN talle as b, producto as c, stock as d set a.cantidad = a.cantidad + %1 where a.Stock_idStock = d.idStock and d.idStock = c.Stock_idStock and a.Talle_idTalle = b.idTalle and c.codProducto = %2 and b.idTalle = %3"
                              .replace("%1",""+cant)
                              .replace("%2",""+codigo)
                              .replace("%3",""+t.getIdTalle());
            
            String cadena3 = "update color_has_stock as a INNER JOIN color as b, producto as c, stock as d set a.cantidad = a.cantidad + %1 where a.Stock_idStock = d.idStock and d.idStock = c.Stock_idStock and a.Color_idColor = b.idColor and c.codProducto = %2 and b.idColor = %3"
                             .replace("%1",""+cant)
                             .replace("%2",""+codigo)
                             .replace("%3",""+co.getIdColor());
                
            Connection c = getConnection();
            c.createStatement().executeUpdate(cadena1);   
            c.createStatement().executeUpdate(cadena2); 
            c.createStatement().executeUpdate(cadena3);                                    
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
