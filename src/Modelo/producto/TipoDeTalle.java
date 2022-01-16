/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.producto;

/**
 *
 * @author Luciano Acosta
 */
public class TipoDeTalle {
    private int idTipoTalle;
    private String descripcion;

    public TipoDeTalle(int idTipoTalle, String descripcion) {
        this.idTipoTalle = idTipoTalle;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTalle() {
        return idTipoTalle;
    }

    public void setIdTalle(int idTalle) {
        this.idTipoTalle = idTalle;
    }

    @Override
    public String toString() {
        return "Talle{" + "idTipoTalle=" + idTipoTalle + ", descripcion=" + descripcion + '}';
    }
}
