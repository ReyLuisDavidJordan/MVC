/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.modelo;
import java.util.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
public class Factura {
    private int id;
    private Date fecha;
    private String cedula_cliente;
    private int id_producto;
    private int cantidad;
    private int precio;
    private int total;
    private String estado;

    public Factura() {
    }

    public Factura(int id, Date fecha, String cedula_cliente, int id_producto, int cantidad, int precio, int total, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.cedula_cliente = cedula_cliente;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(String cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Factura{" + "id=" + id + ", fecha=" + fecha + ", cedula_cliente=" + cedula_cliente + ", id_producto=" + id_producto + ", cantidad=" + cantidad + ", precio=" + precio + ", total=" + total + ", estado=" + estado + '}';
    }

   
    
    
}
