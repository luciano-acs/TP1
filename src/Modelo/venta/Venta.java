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

    public Venta(String fecha, int nroComprobante, double total, Cliente cliente, ArrayList<LineaDeVenta> lista) {
        this.fecha = fecha;
        this.nroComprobante = nroComprobante;
        this.total = total;
        this.cliente = cliente;
        this.lista = lista;
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

    @Override
    public String toString() {
        return "Venta{" +
                "fecha='" + fecha + '\'' +
                ", nroComprobante=" + nroComprobante +
                ", total=" + total +
                ", cliente=" + cliente +
                ", lista=" + lista +
                '}';
    }
    
    public void agregarLinea(LineaDeVenta linea) {        
        lista.add(linea);
    }   
}
