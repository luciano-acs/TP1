package Presentador;

import Modelo.BD.BD;
import Modelo.producto.Marca;
import Modelo.producto.Producto;
import Vista.VistaProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luciano Acosta
 */
public class PresentadorProductos implements ActionListener{

    VistaProductos vistaProductos = new VistaProductos();
    Producto producto = new Producto();
    BD bd = new BD();
    
    public PresentadorProductos(VistaProductos vista) {
        this.vistaProductos = vista;
        this.vistaProductos.btnAgregar.addActionListener(this);
        this.vistaProductos.btnCalcular.addActionListener(this);
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vistaProductos.btnAgregar){
            agregarProducto();
        }
        if(e.getSource()==vistaProductos.btnCalcular){
            calcularPrecio();
        }
    }

    private void calcularPrecio() {
        double costo = Double.parseDouble(vistaProductos.jtfCosto.getText());
        double iva = Double.parseDouble(vistaProductos.jtfIVA.getText());
        double margen = Double.parseDouble(vistaProductos.jtfMargen.getText());
        
        vistaProductos.jtfPrecio.setText(String.valueOf(producto.calcularPrecio(costo, iva, margen)));
    }

    private void agregarProducto() {
        int codigo = Integer.parseInt(vistaProductos.jtfNombre.getText());
        String descripcion = vistaProductos.jtfDescripcion.getText();
        double precio = Double.parseDouble(vistaProductos.jtfPrecio.getText());        
        double iva = Double.parseDouble(vistaProductos.jtfIVA.getText());
        double costo = Double.parseDouble(vistaProductos.jtfCosto.getText());
        double margen = Double.parseDouble(vistaProductos.jtfMargen.getText());
        String marca = (String) vistaProductos.cbMarca.getSelectedItem();       
        
        Producto productoEnvio = new Producto(codigo,descripcion,precio,iva,costo,margen,Marca.valueOf(marca));
        bd.agregarProducto(productoEnvio);
        listarTabla();
        borrarjtf();        
    }

    private void listarTabla() {
        DefaultTableModel datos = (DefaultTableModel) vistaProductos.jTable2.getModel();
        datos.setNumRows(0);
        
        ArrayList<Producto> lista = bd.getProductos();
        
        for(int i = 0;i<lista.size();i++){
            Object[] fila = {lista.get(i).getCodigo(),
                             lista.get(i).getDescripcion(),
                             lista.get(i).getMarca(),
                             lista.get(i).getCosto(),
                             lista.get(i).getPrecioVenta(),
                             lista.get(i).getPorcIVA(),
                             lista.get(i).getMargenGanancia()
                             };
            
            datos.addRow(fila);
        }
    }

    private void borrarjtf() {
        vistaProductos.jtfCosto.setText(null);
        vistaProductos.jtfDescripcion.setText(null);
        vistaProductos.jtfNombre.setText(null);
        vistaProductos.jtfIVA.setText(null);
        vistaProductos.jtfMargen.setText(null);
        vistaProductos.jtfPrecio.setText(null);
    }
    
    
}
