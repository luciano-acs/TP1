package Presentador;

import Modelo.BD.BD;
import Modelo.producto.Color;
import Modelo.producto.Marca;
import Modelo.producto.Producto;
import Modelo.producto.Talle;
import Modelo.venta.FormaDePago;
import Vista.VistaMenu;
import Vista.VistaProductos;
import Vista.VistaVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PresentadorMenu implements ActionListener{
    
    VistaMenu vistaMenu = new VistaMenu();
    BD bd = new BD();
    
    public static void main(String[] args) {
        
        VistaMenu vista = new VistaMenu();
        PresentadorMenu presentador = new PresentadorMenu(vista);
        vista.setVisible(true);
        vista.setLocationRelativeTo(vista);
    }
    
    public PresentadorMenu(VistaMenu vista){
        this.vistaMenu = vista;
        this.vistaMenu.btnInicio.addActionListener(this);
        this.vistaMenu.btnProductos.addActionListener(this);
        this.vistaMenu.btnVenta.addActionListener(this);
//        this.vistaMenu.addWindowListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vistaMenu.btnInicio){
            System.out.println("EN CONSTRUCCION");
        }
        if(e.getSource()==vistaMenu.btnProductos){
            VistaProductos vistaProductos = new VistaProductos();
            PresentadorProductos presentador = new PresentadorProductos(vistaProductos);
//            cargarProductos(vistaProductos);
            cargarComboP(vistaProductos);
            vistaProductos.setVisible(true);
            vistaProductos.setLocationRelativeTo(vistaProductos);
        }
        int c = 0;
        if(e.getSource()==vistaMenu.btnVenta){
            VistaVentas vistaVentas = new VistaVentas();
            PresentadorVentas presentador = new PresentadorVentas(vistaVentas);
            cargarComboV(vistaVentas);
            secuencia(vistaVentas,c);
            vistaVentas.setVisible(true);
            vistaVentas.setLocationRelativeTo(vistaVentas);
            c++;
        }
    }
    
//    @Override
//    public void windowClosing(WindowEvent e){
//        System.out.println("123");
//        cerrar();
//    }
//    
//    public void cerrar() {
//        String botones[]={"cerrar","cancelar"};
//        int eleccion = JOptionPane.showOptionDialog(vistaMenu, "¿Desea cerrar la aplicacion?", "TITULO", 0, 0, null, botones, vistaMenu);
//        if(eleccion == JOptionPane.YES_OPTION){
//            System.exit(0);
//        }else if(eleccion == JOptionPane.NO_OPTION){
//            System.exit(1);
//        }
//    }


//    private void cargarProductos(VistaProductos vistaProductos) {
//        DefaultTableModel datos = (DefaultTableModel) vistaProductos.jTable2.getModel();
//        datos.setNumRows(0);
//        
//        ArrayList<Producto> lista = bd.getProductos();
//        
//        for(int i = 0;i<lista.size();i++){
//            Object[] fila = {lista.get(i).getCodigo(),
//                             lista.get(i).getDescripcion(),
//                             lista.get(i).getMarca(),
//                             lista.get(i).getCosto(),
//                             lista.get(i).getPrecioVenta(),
//                             lista.get(i).getPorcIVA(),
//                             lista.get(i).getMargenGanancia()
//                             };
//            datos.addRow(fila);
//        }
//    }
    
    private void secuencia(VistaVentas vistaVentas, int clic) {
        int c = 1+clic;
        String contador = String.valueOf(c);
        vistaVentas.jtfVenta.setText(contador);
        
    }
    private void cargarComboP(VistaProductos vistaProductos) {
        
        for(Marca marca: Marca.values()){
            vistaProductos.cbMarca.addItem(marca.getMarcas());
        }       
    }

    private void cargarComboV(VistaVentas vistaVentas) {
        for(Talle talle: Talle.values()){
            vistaVentas.cbTalle.addItem(talle.getTalles());
        }
        for(Color color: Color.values()){
            vistaVentas.cbColor.addItem(color.getColores());
        }
        for(FormaDePago fdp: FormaDePago.values()){
            vistaVentas.cbTipo.addItem(fdp.getFdp());
        }  
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        vistaVentas.lFecha.setText(String.valueOf(dtf.format(LocalDateTime.now())));
        
    }
}
