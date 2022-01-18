/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentador;

import Modelo.BD.BD;
import Modelo.Organizacion.Empleado;
import Modelo.cliente.Cliente;
import Modelo.producto.ColorP;
import Modelo.producto.Producto;
import Modelo.producto.Talle;
import Modelo.producto.TipoDeTalle;
import Modelo.venta.FormaDePago;
import Modelo.venta.LineaDeVenta;
import Modelo.venta.Venta;
import Vista.pVentas;
import Vista.vistaMenu;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luciano Acosta
 */
public class PresentadorVentas implements ActionListener, java.awt.event.ItemListener{

    vistaMenu menu = new vistaMenu();
    pVentas ventas = new pVentas();
    Venta v = new Venta();
    BD bd = new BD();

    public PresentadorVentas(pVentas vista, vistaMenu menu) {
        this.ventas = vista;
        this.menu = menu;
        
        ventas.btnBuscarCliente.addActionListener(this);
        ventas.btnBuscarP.addActionListener(this);
        ventas.btnConfirmar.addActionListener(this);
        ventas.btnEliminar.addActionListener(this);
        ventas.cbTipo.addItemListener(this);
        ventas.cbPago.addItemListener(this);
        ventas.btnCancelarVenta.addActionListener(this);
        ventas.btnRegistarVenta.addActionListener(this);
        ventas.btnFinalizarVenta.addActionListener(this);
        
        ventas.jtfMonto.setEnabled(false);
        ventas.cbPago.setEnabled(true);
        ventas.jtfCVV.setEnabled(false);
        ventas.jtfTarjeta.setEnabled(false);
        ventas.jtfVto.setEnabled(false);
    }  
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(ventas.btnBuscarCliente)){
            buscarCliente();
        }
        if(e.getSource().equals(ventas.btnBuscarP)){
            if(bd.existe(ventas.jtfNombre.getText())){
                if(bd.consultarStock(ventas.jtfNombre.getText()) > 0){
                    buscarProducto();
                }else{
                    JOptionPane.showMessageDialog(null, "Producto sin stock");
                }
            }else{
                JOptionPane.showMessageDialog(null, "El producto no exite");
                ventas.jtfNombre.setText("");
            }
        }
        if(e.getSource().equals(ventas.btnConfirmar)){
            int codColor = bd.buscarCodColor((String) ventas.cbColor.getSelectedItem());
            int codTalle = bd.buscarCodTalle((String) ventas.cbTalle.getSelectedItem());
            if(Integer.parseInt(ventas.jtfCantidad.getText()) <= bd.consultarStock(ventas.jtfNombre.getText())){
                if(bd.cantColor(ventas.jtfNombre.getText(),codColor) >= Integer.parseInt(ventas.jtfCantidad.getText()) &&
                   bd.cantTalle(ventas.jtfNombre.getText(),codTalle) >= Integer.parseInt(ventas.jtfCantidad.getText())){
                    
                    String codigo = ventas.jtfNombre.getText();
                    String descripcion = ventas.jtfDescripcion.getText();
                    int cant = Integer.parseInt(ventas.jtfCantidad.getText());
            
                    Producto p = bd.buscarProducto(codigo);    
                    Talle t = new Talle(bd.buscarCodTalle((String) ventas.cbTalle.getSelectedItem()),(String) ventas.cbTalle.getSelectedItem());
                    ColorP co = new ColorP(bd.buscarCodColor((String) ventas.cbColor.getSelectedItem()),(String) ventas.cbColor.getSelectedItem());
            
                    double precio = p.calcularPrecio(p.getCosto(), p.getPorcIVA(), p.getMargenGanancia());;
                    double subtotal = cant * precio;            
            
                    DefaultTableModel datos = (DefaultTableModel) ventas.jtLinea.getModel();        
                    Object[] fila = {codigo,descripcion,cant,precio,subtotal,t.getIdTalle(),co.getIdColor()};                
                    datos.addRow(fila); 
            
                    total();
                    limpiar();
                    bd.actualizarStock(Integer.parseInt(codigo),cant,Integer.parseInt(ventas.jtfID.getText()),t,co);                    
                }else{
                    JOptionPane.showMessageDialog(null, "Stock no disponible. Las "+ventas.jtfDescripcion.getText()+" en stock para dicho talle y color son: "+bd.cantColor(ventas.jtfNombre.getText(),codColor));
                }                
            }else{
                JOptionPane.showMessageDialog(null, "Stock no disponible. Las "+ventas.jtfDescripcion.getText()+" en stock son: "+bd.consultarStock(ventas.jtfNombre.getText()));
            }
        }
        if(e.getSource().equals(ventas.btnEliminar)){
            DefaultTableModel datos = (DefaultTableModel) ventas.jtLinea.getModel();
            int fila = ventas.jtLinea.getSelectedRow();
            int linea = Integer.parseInt(ventas.jtfID.getText());
            
            String codigo = (String) ventas.jtLinea.getValueAt(fila, 0);
            int cant = (int) ventas.jtLinea.getValueAt(fila, 2);
            Talle t = new Talle((int) ventas.jtLinea.getValueAt(fila, 5),bd.buscarDTalle((int) ventas.jtLinea.getValueAt(fila, 5)));
            ColorP co = new ColorP((int) ventas.jtLinea.getValueAt(fila, 6),bd.buscarDColor((int) ventas.jtLinea.getValueAt(fila, 6)));
            
            bd.restaurarStock(codigo,cant,t,co,linea);            
            
            datos.removeRow(fila);
            total();
        }
        if(e.getSource().equals(ventas.btnRegistarVenta)){
            cargarVenta();
            
            
            ventas.jtfMonto.setText(ventas.jlTotal.getText());
            limpiar();
            DefaultTableModel datos = (DefaultTableModel) ventas.jtLinea.getModel();
            datos.setRowCount(0);
            for(int i=0;i<datos.getRowCount();i++){
                datos.removeRow(i);
            }
            
            JOptionPane.showMessageDialog(null,"Venta registrada");
            ventas.jtfMonto.setEnabled(true);
            ventas.cbPago.setEnabled(true);
            
            ArrayList<FormaDePago> formas = bd.listarFormas();
            for(int i = 0;i<formas.size();i++){
                ventas.cbPago.addItem(formas.get(i).getDescripcion());
            }
        }
        if(e.getSource().equals(ventas.btnCancelarVenta)){
            DefaultTableModel datos = (DefaultTableModel) ventas.jtLinea.getModel();
            int linea = Integer.parseInt(ventas.jtfID.getText());
            
            for(int i=0;i<datos.getRowCount();i++){
                String codigo = (String) ventas.jtLinea.getValueAt(i, 0);
                int cant = (int) ventas.jtLinea.getValueAt(i, 2);
                Talle t = new Talle((int) ventas.jtLinea.getValueAt(i, 5),bd.buscarDTalle((int) ventas.jtLinea.getValueAt(i, 5)));
                ColorP co = new ColorP((int) ventas.jtLinea.getValueAt(i, 6),bd.buscarDColor((int) ventas.jtLinea.getValueAt(i, 6)));
            
                bd.restaurarStock(codigo,cant,t,co,linea);
            }
            datos.setRowCount(0);
            for(int i=0;i<datos.getRowCount();i++){
                datos.removeRow(i);
            }
            
            limpiar();
            total();
            JOptionPane.showMessageDialog(null, "Venta cancelada");
        }
        if(e.getSource().equals(ventas.btnFinalizarVenta)){
            limpiar();
            cardarId(1);       
        }
    }
    
    private void total() {
        if(ventas.jtLinea.getRowCount()>0){
            double sum = 0;
            for(int i=0;i<ventas.jtLinea.getRowCount();i++){
                sum += (double) ventas.jtLinea.getValueAt(i, 4);
                ventas.jlTotal.setText(""+sum);
            }
        }else{
            ventas.jlTotal.setText(""+0);
        }
    }
//    
    private void cargarVenta() {
        String fecha = menu.jlFecha.getText();
        int comprobante = Integer.parseInt(ventas.jtfID.getText());
        double total = Double.parseDouble(ventas.jlTotal.getText());
        Cliente cl = bd.buscarCliente(ventas.jtfCUIT.getText());
        
        ArrayList<LineaDeVenta> lista = new ArrayList<LineaDeVenta>();
        DefaultTableModel datos = (DefaultTableModel) ventas.jtLinea.getModel();  
        for(int i = 0;i<ventas.jtLinea.getRowCount();i++){
            LineaDeVenta li = new LineaDeVenta();
            li.setCantidad((int) datos.getValueAt(i, 2));
            
            Producto p = bd.buscarProducto(ventas.jtLinea.getValueAt(i, 0).toString());
            li.setProducto(p);
            lista.add(li);
        }        
        
        Venta ve = new Venta(fecha,comprobante,total,cl,lista);
        bd.registrarVenta(ve,Integer.parseInt(menu.jlPunto.getText()));
    }   

    public void cardarId(int sum) {
        int cod = 1+sum;
        
        Calendar fecha = new GregorianCalendar();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH);
        int anio = fecha.get(Calendar.YEAR);
        
        String cadena = dia+""+(mes+1)+""+anio+""+cod+"";
        
        System.out.println(cadena);
        String idVenta = cadena;
        ventas.jtfID.setHorizontalAlignment(SwingConstants.CENTER);
        ventas.jtfID.setText(idVenta);
        ventas.jtfID.setEnabled(false);
    }

    private void buscarCliente() {
        String cuit = ventas.jtfCUIT.getText();
        Cliente c = bd.buscarCliente(cuit);
        
        ventas.jtfRazonSocial.setText(c.getRazonSocial());
    }

    private void buscarProducto() {
        int cod = Integer.parseInt(ventas.jtfNombre.getText());
        Producto p = bd.listarProducto(cod);

        ventas.jtfDescripcion.setText(p.getDescripcion());
        ventas.jtfMarca.setText(p.getMarca().getDescripcionM());
        ventas.cbColor.removeAllItems();;
        ventas.cbTipo.removeAllItems();
        ventas.cbTalle.removeAllItems();
        
        ArrayList<TipoDeTalle> tipo = bd.listarStockTipoTalle(cod);
        ArrayList<Modelo.producto.ColorP> color = bd.listarStockColor(cod);
        
        for(int i =0;i<tipo.size();i++){
            ventas.cbTipo.addItem(tipo.get(i).getDescripcion());
        }
        for(int i =0;i<color.size();i++){
            ventas.cbColor.addItem(color.get(i).getDescripcion());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            ventas.cbTalle.removeAllItems();
            if(ventas.cbTipo.getSelectedIndex()>-1){
                int cod = bd.buscarCodTipo(ventas.cbTipo.getSelectedItem().toString());        
                ArrayList<Talle> talle =  bd.buscarStockTalle(cod,ventas.cbTipo.getSelectedItem().toString());
                for(int i =0;i<talle.size();i++){
                    ventas.cbTalle.addItem(talle.get(i).getDescripcion());            
                }
            }
        }
        if(e.getStateChange()==ItemEvent.SELECTED){
            if(ventas.cbPago.getSelectedIndex()>-1){
                if(ventas.cbPago.getSelectedItem().equals("tarjeta")){
                    ventas.jtfTarjeta.setEnabled(true);
                    ventas.jtfVto.setEnabled(true);
                    ventas.jtfCVV.setEnabled(true);
                }else{
                    ventas.jtfTarjeta.setEnabled(false);
                    ventas.jtfVto.setEnabled(false);
                    ventas.jtfCVV.setEnabled(false);
                }
            }
        }
    }

    //limpiar jtf venta
    private void limpiar() {
        ventas.jtfCantidad.setText("");
        ventas.jtfMarca.setText("");
        ventas.jtfDescripcion.setText("");
        ventas.jtfNombre.setText("");
        ventas.cbColor.removeAllItems();
        ventas.cbTipo.removeAllItems();
        ventas.cbTalle.removeAllItems();
        ventas.cbPago.removeAllItems();
    }
}
