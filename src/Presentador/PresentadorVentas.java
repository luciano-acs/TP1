/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentador;

import Modelo.BD.BD;
import Modelo.Organizacion.Empleado;
import Modelo.cliente.Cliente;
import Modelo.cliente.CondTributaria;
import Modelo.producto.Producto;
import Modelo.venta.FormaDePago;
import Modelo.venta.LineaDeVenta;
import Modelo.venta.Pago;
import Modelo.venta.Venta;
import Vista.VistaVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luciano Acosta
 */
public class PresentadorVentas implements ActionListener{

    VistaVentas vistaVentas = new VistaVentas();
    Venta v = new Venta();
    BD bd = new BD();

    public PresentadorVentas(VistaVentas vista) {
        this.vistaVentas = vista;
        this.vistaVentas.jbAgregar.addActionListener(this);
        this.vistaVentas.jbBuscar.addActionListener(this);
        this.vistaVentas.jbImprimir.addActionListener(this);
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vistaVentas.jbAgregar){
            agregarLinea();
        }
        if(e.getSource()==vistaVentas.jbBuscar){
            buscarProductos();
        }
        if(e.getSource()==vistaVentas.jbImprimir){
            cargarVenta();
        }
    }  
    

    private void buscarProductos() {
        ArrayList<Producto> productos = bd.getProductos();
        int codigo = Integer.parseInt(vistaVentas.jtfCodigo.getText());
        
        if(productos.size()==0){
            JOptionPane.showMessageDialog(null,"No hay productos cargados");
        }else{
            for(int i =0;i<productos.size();i++){
            if(productos.get(i).getCodigo()==codigo){
                vistaVentas.jtfDescrip.setText(productos.get(i).getDescripcion());
                vistaVentas.jtfPrecio.setText(String.valueOf(productos.get(i).getPrecioVenta()));
                vistaVentas.jtfMarca.setText(productos.get(i).getMarca().getMarcas());   
            }else{
                    System.out.println("HOLA");
                    JOptionPane.showMessageDialog(null,"Producto Inexistente");
                }
            }
        }
        
    }
    
    private void agregarLinea() {
        DefaultTableModel datos = (DefaultTableModel) vistaVentas.jtDetalles.getModel();
        datos.setNumRows(0);        
        
        ArrayList<Producto> lista = bd.getProductos();
        int codigo = Integer.parseInt(vistaVentas.jtfCodigo.getText());
        
        if(lista.size()==0){
            JOptionPane.showMessageDialog(null,"No hay productos cargados");
        }else{
            for(int i =0;i<lista.size();i++){
            if(lista.get(i).getCodigo()==codigo){
                Producto p = new Producto(lista.get(i).getCodigo(),
                             lista.get(i).getDescripcion(),
                             lista.get(i).getPrecioVenta(),
                             lista.get(i).getPorcIVA(),                             
                             lista.get(i).getCosto(),                             
                             lista.get(i).getMargenGanancia(),
                             lista.get(i).getMarca());
                
                LineaDeVenta lv = new LineaDeVenta(p,Integer.parseInt(vistaVentas.jtfCantidad.getText()));
                v.agregarLinea(lv);
                double subtotal = (Double.parseDouble(vistaVentas.jtfPrecio.getText()))*(Double.parseDouble(vistaVentas.jtfCantidad.getText()));
                
                Object[] fila = {lista.get(i).getCodigo(),
                             lista.get(i).getDescripcion(),
                             lista.get(i).getMarca(),
                             lista.get(i).getPrecioVenta(),
                             subtotal
                };
            
                datos.addRow(fila);
                total();
                borrarjtf();
            }
            }
        }
    }
    
    private void total() {
        if(vistaVentas.jtDetalles.getRowCount()>0){
            for(int i=0;i<vistaVentas.jtDetalles.getRowCount();i++){
                double st = Double.parseDouble(vistaVentas.jtDetalles.getValueAt(i, 3).toString());
                double total =+ st;
                vistaVentas.jtfTotal.setText(String.valueOf(total));
            }
            
        }
    }
    
    private void cargarVenta() {
        String fecha = vistaVentas.lFecha.getText();
        int comprobante = Integer.parseInt(vistaVentas.jtfVenta.getText());
        double total = Double.parseDouble(vistaVentas.jtfTotal.getText());
        Cliente cl = new Cliente("Luciano","2038243415","Rivadavia 1050",CondTributaria.Consumidor_Final);
        String tipo = (String) vistaVentas.cbTipo.getSelectedItem();
        Pago p = new Pago(Double.parseDouble(vistaVentas.jtfPago.getText()),FormaDePago.valueOf(tipo));
        Empleado e = new Empleado(44444,"Nicolas","Acosta","lucianoacosta28@gmail.com","Usuario","123456");
        
        Venta ve = new Venta(fecha,comprobante,total,cl,v.getLista(),p,e);
        bd.agregarVenta(ve);
        borrarjtf();
    }   
    
    private void borrarjtf() {
        vistaVentas.jtfCliente.setText(null);
        vistaVentas.jtfCodigo.setText(null);
        vistaVentas.jtfDescrip.setText(null);
        vistaVentas.jtfTotal.setText(null);
        vistaVentas.jtfVenta.setText(null);
        vistaVentas.jtfPrecio.setText(null);
        vistaVentas.jtfCantidad.setText(null);
    }
}
