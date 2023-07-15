/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Usuario
 */
public class ModeloFactura extends Factura{
    ConexionPG cpg=new ConexionPG();

    public ModeloFactura() {
    }
     public List<Factura> listarFacturas(String id) {
        ResultSet rs = null;
        String sql;
        List<Factura> listafactura = new ArrayList<>();
        try {
            if ("".equals(id)) {
                sql = "SELECT id,Fecha,cedula_cli,id_producto,cantidad,precio_unidad,total FROM factura";
            } else {
                sql = "SELECT id,Fecha,cedula_cli,id_producto,cantidad,precio_unidad,total FROM factura WHERE id='"+id+"'";
            }
            rs = cpg.consultaBD(sql);
            if (rs != null) {
                while (rs.next()) {
                    Factura fac = new Factura();
                    fac.setId(rs.getInt(1));
                    fac.setFecha(rs.getDate(2));
                    fac.setCedula_cliente(rs.getString(3));
                    fac.setId_producto(rs.getInt(4));
                    fac.setCantidad(rs.getInt(5));
                    fac.setPrecio(rs.getInt(6));
                    fac.setTotal(rs.getInt(7));
                    
                    listafactura.add(fac);
                    
                }
            }
            return listafactura;
        } catch (SQLException ex) {
            return null;
        }
    }
    public static List<Factura> listarfactura2(){
         ConexionPG cpg=new ConexionPG();
          List<Factura> listaPersonas=new ArrayList<Factura>();
        try {
            String sql="SELECT id,fecha,cedula_cli,id_producto,cantidad,precio_unidad,total FROM Factura";
            ResultSet rs= cpg.consultaBD(sql);
            while(rs.next()){
                Factura fac=new Factura();
               fac.setId(rs.getInt(1));
                    fac.setFecha(rs.getDate(2));
                    fac.setCedula_cliente(rs.getString(3));
                    fac.setId_producto(rs.getInt(4));
                   fac.setCantidad(rs.getInt(5));
                    fac.setPrecio(rs.getInt(6));
                    fac.setTotal(rs.getInt(7));
                    
                    listaPersonas.add(fac);
            }
            rs.close();//CIERRO CONEXION
        return listaPersonas;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
        return null;    
        }
    }
     public boolean grabarfactura(){
   String sql;
   sql="INSERT INTO factura(Fecha,cedula_cli,id_producto,cantidad,precio_unidad,total) ";
   sql+=" VALUES('"+getFecha()+"','"+getCedula_cliente()+"','"+getId_producto()+"','"+getCantidad()+"','"+getPrecio()+"','"+getTotal()+"')";
         System.out.println(sql);
   return cpg.accionBD(sql);
   }
    
    public boolean eliminarfactura(String id){
        String sql;
        sql = "DELETE FROM Factura WHERE id='"+id+"'";
        return cpg.accionBD(sql);
    }
     public boolean Actualizar() {
         String sql;
        sql = "UPDATE Factura SET cantidad='"
                +getCantidad()+"', Fecha='"
                +getFecha()+"', fechanacimiento='"
               +"' WHERE cedula_cli='"+getId()+"'";
        return cpg.accionBD(sql);
    }
    public SQLException modificarPersona() { 
    ConexionPG conec=new ConexionPG();
        String sql = "UPDATE factura SET id='" + getId()+ "', Fecha='" + getFecha()+ "', cedula_cli='" + getCedula_cliente()+"', id_producto='" + getId_producto()+ "', cantidad='" + getCantidad()+ "', precio_unidad=" + getPrecio()+ ", total=" + getTotal()+ "WHERE id = '" + getId()+ "';";

      

        SQLException ex = conec.accion(sql);
        return ex;
    }
    
    
    
    
    
    
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}
