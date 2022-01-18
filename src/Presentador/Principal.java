/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentador;

import Modelo.BD.BD;
import Modelo.venta.Venta;
import Vista.Sesion;
import Vista.pProductos;
import Vista.pVentas;
import Vista.vistaMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Luciano Acosta
 */
public class Principal{
    
    public static void main(String[] args) {        
        Sesion inicio = new Sesion();
        PresentadorInicio iniciar = new PresentadorInicio(inicio);
//        BD bd = new BD();
//        
//        System.out.println(bd.ultimoCod());
        //http://istp1service.azurewebsites.net/LoginService.svc?wsdl
        //https://wswhomo.afip.gov.ar/wsfev1/service.asmx?WSDL
        
    }
}
