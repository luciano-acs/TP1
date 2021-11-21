package Modelo.venta;
import Modelo.producto.Producto;
import Modelo.cliente.Cliente;
import Modelo.Organizacion.Empleado;

import java.util.ArrayList;

public class Venta {

    private String fecha;
    private int nroComprobante;
    private double total;
    private Cliente cliente;
    private ArrayList<LineaDeVenta> lista = new ArrayList<>();
    private Pago pago;
//    private Factura factura;
    private Empleado empleado;

    public Venta(String fecha, int nroComprobante, double total, Cliente cliente, ArrayList<LineaDeVenta> lista, Pago pago, Empleado empleado) {
        this.fecha = fecha;
        this.nroComprobante = nroComprobante;
        this.total = total;
        this.cliente = cliente;
        this.lista = lista;
        this.pago = pago;
//        this.factura = factura;
        this.empleado = empleado;
    }

    public Venta() {
        
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(int nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<LineaDeVenta> getLista() {
        return lista;
    }

    public void setLista(ArrayList<LineaDeVenta> lista) {
        this.lista = lista;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

//    public Factura getFactura() {
//        return factura;
//    }
//
//    public void setFactura(Factura factura) {
//        this.factura = factura;
//    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "fecha='" + fecha + '\'' +
                ", nroComprobante=" + nroComprobante +
                ", total=" + total +
                ", cliente=" + cliente +
                ", lista=" + lista +
                ", pago=" + pago +
                ", empleado=" + empleado +
                '}';
    }
    
    public void agregarLinea(LineaDeVenta linea) {        
        lista.add(linea);
    }

    public void calcularTotal() {
        
        double total = 0;
        for (int i = 0; i < lista.size();i++) {
            total = total + lista.get(i).getProducto().getPrecioVenta();  
        }
        this.total = total;
        
    }
    
    public void nroComprobante(){
        
    }
   
    
}
