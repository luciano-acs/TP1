package Presentador;

import Modelo.BD.BD;
import Vista.Sesion;
import Vista.pClientes;
import Vista.pListarProductos;
import Vista.pLogo;
import Vista.pModProductos;
import Vista.pProductos;
import Vista.pVentas;
import Vista.vistaMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PresentadorMenu implements ActionListener{
    
    vistaMenu principal = new vistaMenu();
    Sesion iniciar = new Sesion();
    pProductos productos = new pProductos();
    pVentas ventas = new pVentas();
    pClientes clientes = new pClientes();
    pListarProductos listaProd = new pListarProductos();
    pLogo logo = new pLogo();
    pModProductos modProductos = new pModProductos();
    
    BD bd = new BD();
    
    public PresentadorMenu(vistaMenu vista, Sesion sesion) {
        this.principal = vista;
        this.iniciar = sesion;
        this.principal.btnNvoProducto.addActionListener(this);
        this.principal.btnVentas.addActionListener(this);
        this.principal.btnClientes.addActionListener(this);
        this.principal.btnListarProd.addActionListener(this);
        this.principal.btnModProducto.addActionListener(this);
        this.principal.btnNvoCliente.addActionListener(this);
        this.principal.btnSalir.addActionListener(this);
        
        principal.jlLogo.setVisible(false);
        principal.jlLogo1.setVisible(true);
        
        principal.add(productos); 
        principal.add(ventas); 
        principal.add(clientes); 
        principal.add(logo); 
        principal.add(listaProd); 
        principal.add(modProductos);
        
        logo.setVisible(true);
        productos.setVisible(false);
        ventas.setVisible(false);
        clientes.setVisible(false); 
        listaProd.setVisible(false);
        modProductos.setVisible(false);
        
        logo.setBounds(250, 100, 700, 700);
        productos.setBounds(250, 100, 700, 700);
        ventas.setBounds(250, 100, 700, 700);
        clientes.setBounds(250, 100, 700, 700);
        listaProd.setBounds(250, 100, 700, 700);
        modProductos.setBounds(250, 100, 700, 700);
        
        Calendar fecha = new GregorianCalendar();
        int mes = fecha.get(Calendar.MONTH);
        principal.jlFecha.setText(fecha.get(Calendar.DAY_OF_MONTH)+"/"+(mes+1)+"/"+fecha.get(Calendar.YEAR));
        principal.jlEmpleado.setText(iniciar.jtfLegajo.getText());
    }

    PresentadorMenu(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==principal.btnNvoProducto){            
            productos.setVisible(true);
            principal.jlLogo.setVisible(true);
            principal.jlLogo1.setVisible(false);
            
            logo.setVisible(false);
            ventas.setVisible(false);
            clientes.setVisible(false);
            listaProd.setVisible(false);            
            modProductos.setVisible(false);
            
            PresentadorProductos pp = new PresentadorProductos(productos);
            pp.cargarCombos();
            pp.borrarjtf();
        }
        if(e.getSource()==principal.btnVentas){
            productos.setVisible(false);
            principal.jlLogo.setVisible(true);
            principal.jlLogo1.setVisible(false);
            
            logo.setVisible(false);
            ventas.setVisible(true);     
            clientes.setVisible(false);            
            listaProd.setVisible(false);
            modProductos.setVisible(false);
            
            PresentadorVentas pv = new PresentadorVentas(ventas,principal);
            pv.cardarId(0);
        }
        if(e.getSource()==principal.btnClientes){
            productos.setVisible(false);
            principal.jlLogo.setVisible(true);
            principal.jlLogo1.setVisible(false);
            
            logo.setVisible(false);
            ventas.setVisible(false);     
            clientes.setVisible(true);            
            listaProd.setVisible(false);
            modProductos.setVisible(false);
        }
        if(e.getSource()==principal.btnListarProd){
            productos.setVisible(false);
            principal.jlLogo.setVisible(true);
            principal.jlLogo1.setVisible(false);
            
            listaProd.setVisible(true);
            logo.setVisible(false);
            ventas.setVisible(false);     
            clientes.setVisible(false);
            modProductos.setVisible(false);
            
            PresentadorProductos pp = new PresentadorProductos(listaProd);
            pp.listarTabla();
        } 
        if(e.getSource().equals(principal.btnModProducto)){
            productos.setVisible(false);
            principal.jlLogo.setVisible(true);
            principal.jlLogo1.setVisible(false);
            
            listaProd.setVisible(false);
            logo.setVisible(false);
            ventas.setVisible(false);     
            clientes.setVisible(false);
            modProductos.setVisible(true);
            
            PresentadorProductos pp = new PresentadorProductos(modProductos);
            pp.cargarCombosM();
        }
        if(e.getSource()==principal.btnSalir){
            principal.dispose(); 
            Sesion s = new Sesion();
            PresentadorInicio inicio = new PresentadorInicio(s);           
        }
    }   
}
