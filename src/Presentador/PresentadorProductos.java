package Presentador;

import Modelo.BD.BD;
import Modelo.producto.ColorP;
import Modelo.producto.Marca;
import Modelo.producto.Producto;
import Modelo.producto.Rubro;
import Modelo.producto.Stock;
import Modelo.producto.Talle;
import Modelo.producto.TipoDeTalle;
import Vista.pListarProductos;
import Vista.pModProductos;
import Vista.pProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luciano Acosta
 */
public class PresentadorProductos implements ActionListener, java.awt.event.ItemListener{

    pListarProductos listaProd = new pListarProductos();
    pProductos productos = new pProductos();
    Producto producto = new Producto();
    pModProductos modProductos = new pModProductos();
    BD bd = new BD();
    int suma = 0;
            
    public PresentadorProductos(pListarProductos lista) {
        this.listaProd = lista;
        listaProd.btnBuscarP.addActionListener(this);
        listaProd.jtfBuscar.addActionListener(this);
        listaProd.btnEliminar.addActionListener(this);
    }    
    
    public PresentadorProductos(pProductos prod){
        this.productos = prod; 
        productos.cbTipo.addItemListener(this);
        productos.btnAgregarProd.addActionListener(this);
        productos.btnConfirmar.addActionListener(this);
        productos.btnEliminar.addActionListener(this);
    }

    public PresentadorProductos(pModProductos mod) {
        this.modProductos = mod;
        modProductos.cbTipo.addItemListener(this);
        modProductos.btnBuscarP.addActionListener(this);
        modProductos.btnModProd.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(listaProd.btnBuscarP)){
            buscarProductoTabla();
        }
        if(e.getSource().equals(listaProd.btnEliminar)){
            eliminarProducto();
            listarTabla();
        }
        if(e.getSource().equals(productos.btnAgregarProdSolo)){
            agregarProductoSolo();
            borrarjtf();        
        }
        if(e.getSource().equals(productos.btnAgregarProd)){
            agregarProducto();
            borrarjtf();
        }
        if(e.getSource().equals(modProductos.btnBuscarP)){
            rellenarCampos();
        }
        if(e.getSource().equals(modProductos.btnModProd)){
            modProducto();            
        }
        if(e.getSource().equals(productos.btnConfirmar)){
            int cantidad = Integer.parseInt(productos.jtfCantidad.getText());
            
            String ttalle = (String) productos.cbTipo.getSelectedItem();
            String talle = (String) productos.cbTalle.getSelectedItem();
            String colorr = (String) productos.cbColor.getSelectedItem();
            int cantidadS = Integer.parseInt(productos.jtfCantidadS.getText());            
            
            suma = suma + cantidadS;
            if(suma > cantidad){
                System.out.println(suma);
                if((suma-cantidadS)==0){suma=0;}
                else{suma=suma-cantidadS;}
                JOptionPane.showMessageDialog(null, "La cantidad superó el total indicado");
                System.out.println(suma);
            }else{
                DefaultTableModel datos = (DefaultTableModel) productos.jtStock.getModel();        
                Object[] fila = {ttalle,talle,colorr,cantidadS};                
                datos.addRow(fila);                
            }          
        }
        if(e.getSource().equals(productos.btnEliminar)){
            DefaultTableModel datos = (DefaultTableModel) productos.jtStock.getModel();            
            int fila = productos.jtStock.getSelectedRow();
            int disminuir = (int) productos.jtStock.getValueAt(fila, 3);
            suma = suma - disminuir;
            datos.removeRow(productos.jtStock.getSelectedRow());
            System.out.println(suma);
        }
    }

    private void calcularPrecio() {
//        double costo = Double.parseDouble(productos.jtfCosto.getText());
//        double iva = Double.parseDouble(productos.jtfIVA.getText());
//        double margen = Double.parseDouble(productos.jtfMargen.getText());
//        
//        productos.jtfPrecio.setText(String.valueOf(producto.calcularPrecio(costo, iva, margen)));
    }

    private void agregarProductoSolo() {
        int codigo = Integer.parseInt(productos.jtfNombre.getText());
        String descripcion = productos.jtfDescripcion.getText();
        double iva = Double.parseDouble(productos.jtfIVA.getText());
        double costo = Double.parseDouble(productos.jtfCosto.getText());
        double margen = Double.parseDouble(productos.jtfMargen.getText());
        
        String marca = (String) productos.cbMarca.getSelectedItem();       
        String rubro = (String) productos.cbRubro.getSelectedItem();
        int stock = Integer.parseInt(productos.jtfCantidad.getText());
//        String talle = (String) productos.cbTalle.getSelectedItem();
//        String colorr = (String) productos.cbColor.getSelectedItem();
        
        Marca m = new Marca(bd.buscarCodMarca(marca),marca);
        Rubro r = new Rubro(bd.buscarCodRubro(rubro),rubro);
//        Talle t = new Talle(bd.buscarCodTalle(talle),talle);
//        ColorP c = new ColorP(bd.buscarCodColor(colorr),colorr);        
        
        Stock s = new Stock(codigo,stock);
        Producto productoEnvio = new Producto(codigo,descripcion,iva,costo,margen,m,r,s,1);
        bd.agregarProducto(productoEnvio,s,m,r);
        listarTabla();       
        JOptionPane.showMessageDialog(null, "Producto agregado con exito");
    }

    private void agregarProducto() {
        int codigo = Integer.parseInt(productos.jtfNombre.getText());
        String descripcion = productos.jtfDescripcion.getText();
        double iva = Double.parseDouble(productos.jtfIVA.getText());
        double costo = Double.parseDouble(productos.jtfCosto.getText());
        double margen = Double.parseDouble(productos.jtfMargen.getText());
        
        String marca = (String) productos.cbMarca.getSelectedItem();       
        String rubro = (String) productos.cbRubro.getSelectedItem();
        int stock = Integer.parseInt(productos.jtfCantidad.getText());
        
        Marca m = new Marca(bd.buscarCodMarca(marca),marca);
        Rubro r = new Rubro(bd.buscarCodRubro(rubro),rubro);       
        Stock s = new Stock(codigo,stock);
        
        Producto productoEnvio = new Producto(codigo,descripcion,iva,costo,margen,m,r,s,1);
        bd.agregarProducto(productoEnvio,s,m,r);
        
        for(int i=0;i<productos.jtStock.getRowCount();i++){            
            Talle t = new Talle(bd.buscarCodTalle(productos.jtStock.getValueAt(i,1).toString()),productos.jtStock.getValueAt(i,1).toString());
            ColorP c = new ColorP(bd.buscarCodColor(productos.jtStock.getValueAt(i,2).toString()),productos.jtStock.getValueAt(i,2).toString()); 
            int cantidad = Integer.parseInt(productos.jtStock.getValueAt(i, 3).toString());
            bd.agregarStock(codigo, t, c, cantidad);
        }        
        
        listarTabla();       
        JOptionPane.showMessageDialog(null, "Producto agregado con exito");
    }
    public void listarTabla() {
        DefaultTableModel datos = (DefaultTableModel) listaProd.jtProductos.getModel();
        datos.setNumRows(0);
        
        ArrayList<Producto> lista = bd.listarProductos();
        
        for(int i = 0;i<lista.size();i++){
            Object[] fila = {lista.get(i).getCodigo(),
                             lista.get(i).getDescripcion(),
                             lista.get(i).getMarca().getDescripcionM(),
                             lista.get(i).getRubro().getDescripcionR(),
                             lista.get(i).getCosto(),
                             lista.get(i).getPorcIVA(),
                             lista.get(i).getMargenGanancia()
                             };
            
            datos.addRow(fila);
        }
    }
    
    private void buscarProductoTabla() {
        
        String codigo = listaProd.jtfBuscar.getText();
        System.out.println(codigo);
        for(int i = 0;i<listaProd.jtProductos.getRowCount();i++){
            if(listaProd.jtProductos.getValueAt(i, 0).toString().equals(codigo)){
                listaProd.jLabel3.setText("");
                listaProd.jtProductos.changeSelection(i, 0, false, false);
                break;
            }
            else{
                listaProd.jLabel3.setText("El producto no se encuentra registrado");
            }
        }
    }

    public void borrarjtf() {
        productos.jtfCosto.setText(null);
        productos.jtfDescripcion.setText(null);
        productos.jtfNombre.setText(null);
        productos.jtfIVA.setText(null);
        productos.jtfMargen.setText(null);
        productos.jtfCantidadS.setText(null);
        
        modProductos.jtfCosto.setText(null);
        modProductos.jtfDescripcion.setText(null);
        modProductos.jtfNombre.setText(null);
        modProductos.jtfIVA.setText(null);
        modProductos.jtfMargen.setText(null);
        modProductos.jtfCantidad.setText(null);
    }
    
    public void cargarCombos(){
        productos.cbMarca.removeAllItems();
        productos.cbTalle.removeAllItems();
        productos.cbColor.removeAllItems();
        productos.cbTipo.removeAllItems();
        productos.cbRubro.removeAllItems();
        
        ArrayList<Marca> marca = bd.listarMarcas();
        ArrayList<TipoDeTalle> tipo = bd.listarTipoTalle();
        ArrayList<Modelo.producto.ColorP> color = bd.listarColor();
        ArrayList<Rubro> rubro = bd.listarRubros();
        
        for(int i =0;i<marca.size();i++){
            productos.cbMarca.addItem(marca.get(i).getDescripcionM());
        }
        for(int i =0;i<tipo.size();i++){
            productos.cbTipo.addItem(tipo.get(i).getDescripcion());
        }
        for(int i =0;i<color.size();i++){
            productos.cbColor.addItem(color.get(i).getDescripcion());
        }
        for(int i =0; i<rubro.size();i++){
            productos.cbRubro.addItem(rubro.get(i).getDescripcionR());
        }

    }

    public void buscarTalle(String item) {
        String buscar = item;     
        int cod = bd.buscarCodTipo(buscar);
        ArrayList<Talle> talle =  bd.buscarTalle(cod);
        
        for(int i =0;i<talle.size();i++){
            productos.cbTalle.addItem(talle.get(i).getDescripcion());
            System.out.println(talle.get(i).getDescripcion());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            productos.cbTalle.removeAllItems();
            if(productos.cbTipo.getSelectedIndex()>-1){
                BD bd = new BD();
                int cod = bd.buscarCodTipo(productos.cbTipo.getSelectedItem().toString());        
                ArrayList<Talle> talle =  bd.buscarTalle(cod);
                for(int i =0;i<talle.size();i++){
                    productos.cbTalle.addItem(talle.get(i).getDescripcion());            
                }
            }
        }
        if(e.getStateChange()==ItemEvent.SELECTED){
            modProductos.cbTalle.removeAllItems();
            if(modProductos.cbTipo.getSelectedIndex()>-1){
                BD bd = new BD();
                int cod = bd.buscarCodTipo(modProductos.cbTipo.getSelectedItem().toString());        
                ArrayList<Talle> talle =  bd.buscarTalle(cod);
                for(int i =0;i<talle.size();i++){
                    modProductos.cbTalle.addItem(talle.get(i).getDescripcion());            
                }
            }
        }
    }

    void cargarCombosM() {
        modProductos.cbMarca.removeAllItems();
        modProductos.cbTalle.removeAllItems();
        modProductos.cbColor.removeAllItems();
        modProductos.cbTipo.removeAllItems();
        modProductos.cbRubro.removeAllItems();
        modProductos.jtfCantidad.setEnabled(false);
        modProductos.cbTipo.setEnabled(false);
        modProductos.cbTalle.setEnabled(false);
        modProductos.cbColor.setEnabled(false);
        
        ArrayList<Marca> marca = bd.listarMarcas();
        ArrayList<TipoDeTalle> tipo = bd.listarTipoTalle();
        ArrayList<Modelo.producto.ColorP> color = bd.listarColor();
        ArrayList<Rubro> rubro = bd.listarRubros();
        
        for(int i =0;i<marca.size();i++){
            modProductos.cbMarca.addItem(marca.get(i).getDescripcionM());
        }
        for(int i =0;i<tipo.size();i++){
            modProductos.cbTipo.addItem(tipo.get(i).getDescripcion());
        }
        for(int i =0;i<color.size();i++){
            modProductos.cbColor.addItem(color.get(i).getDescripcion());
        }
        for(int i =0; i<rubro.size();i++){
            modProductos.cbRubro.addItem(rubro.get(i).getDescripcionR());
        }
    }

    private void rellenarCampos() {
        int cod = Integer.parseInt(modProductos.jtfNombre.getText());
        
        Producto p = bd.listarProducto(cod);
        
        modProductos.jtfDescripcion.setText(p.getDescripcion());
        modProductos.jtfCosto.setText(String.valueOf(p.getCosto()));
        modProductos.jtfIVA.setText(String.valueOf(p.getPorcIVA()));
        modProductos.jtfMargen.setText(String.valueOf(p.getMargenGanancia()));
        modProductos.jtfNombre.setEnabled(false);
    }

    private void modProducto() {
        int codigo = Integer.parseInt(modProductos.jtfNombre.getText());
        String descripcion = modProductos.jtfDescripcion.getText();
        double iva = Double.parseDouble(modProductos.jtfIVA.getText());
        double costo = Double.parseDouble(modProductos.jtfCosto.getText());
        double margen = Double.parseDouble(modProductos.jtfMargen.getText());
        
        String marca = (String) modProductos.cbMarca.getSelectedItem();       
        String rubro = (String) modProductos.cbRubro.getSelectedItem();
        
        Marca m = new Marca(bd.buscarCodMarca(marca),marca);
        Rubro r = new Rubro(bd.buscarCodRubro(rubro),rubro);        
        
        Producto productoEnvio = new Producto(codigo,descripcion,iva,costo,margen,m,r,1);
        bd.modificarProducto(productoEnvio,m,r);
        listarTabla();
        borrarjtf();        
        modProductos.jtfNombre.setEnabled(true);        
        JOptionPane.showMessageDialog(null, "Producto modificado con exito");
    }

    private void eliminarProducto() {
        DefaultTableModel datos = (DefaultTableModel) listaProd.jtProductos.getModel(); 
        int fila = listaProd.jtProductos.getSelectedRow();
        if(listaProd.jtProductos.getValueAt(fila, 0)!=null){
            int codProducto = (int) listaProd.jtProductos.getValueAt(fila, 0);
            bd.eliminarProducto(codProducto);
        } 
    }
    
}
