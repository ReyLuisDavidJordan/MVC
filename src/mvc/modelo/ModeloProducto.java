/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author PC01
 */
public class ModeloProducto extends producto {
    ConexionPG cpg=new ConexionPG();
    public ModeloProducto(){}
    public List<producto> listarproducs(String id) {
        ResultSet rs = null;
        String sql;
        List<producto> listaproducto = new ArrayList<>();
        try {
            if ("".equals(id)) {
                sql = "SELECT id, nombre, precio, cantidad,  descripcion FROM producto";
            } else {
                sql = "SELECT id, nombre, precio, cantidad,  descripcion FROM producto WHERE id='"+id+"'";
            }
            rs = cpg.consultaBD(sql);
            if (rs != null) {
                while (rs.next()) {
                    producto produc = new producto();
                    produc.setId(rs.getInt(1));
                    produc.setNombre(rs.getString(2));
                    produc.setPrecio(rs.getInt(3));
                    produc.setCantidad(rs.getInt(4));
                    produc.setDescripcion(rs.getString(5));
                 
                    
                 
                   
                    listaproducto.add(produc);
                    
                }
            }
            return listaproducto;
        } catch (SQLException ex) {
            return null;
        }
    }
    public static List<producto> listarproductos(){
         ConexionPG cpg=new ConexionPG();
          List<producto> listaproducs=new ArrayList<producto>();
        try {
            String sql="SELECT id, nombre, precio, cantidad, descripcion from producto";
            ResultSet rs= cpg.consultaBD(sql);
            while(rs.next()){
                producto produc=new producto();
                produc.setId(rs.getInt("id"));
                produc.setNombre(rs.getString("nombre"));
                produc.setPrecio(rs.getInt("precio"));
                produc.setCantidad(rs.getInt("cantidad"));
                 produc.setDescripcion(rs.getString("descripcion"));
                
                listaproducs.add(produc);
            }
            rs.close();
        return listaproducs;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProducto.class.getName()).log(Level.SEVERE, null, ex);
        return null;    
        }
    }
    public boolean granbarProducto(){
   String sql;
   sql="INSERT INTO producto( nombre, precio, cantidad, descripcion) ";
   sql+=" VALUES('"+getNombre()+"','"+getPrecio()+"','"+getCantidad()+"','"+getDescripcion()+"')";
   
   return cpg.accionBD(sql);
   }
     public boolean eliminarproducto(String id){
        String sql;
        sql = "DELETE FROM producto WHERE id='"+id+"'";
        return cpg.accionBD(sql);
    }
      public boolean Actualizar() {
         String sql;
        sql = "UPDATE producto SET nombre='"
                +getNombre()+"', precio="
                +getPrecio()+", cantidad="+getCantidad()
               +" WHERE id='"+getId()+"'";
          System.out.println(sql);
        return cpg.accionBD(sql);
        
    }
       public SQLException modificarproducto() { 
    ConexionPG conec=new ConexionPG();
        String sql = "UPDATE producto SET nombre='" + getNombre()+ "', precio='" + getPrecio()+ "', cantidad='" + getCantidad()+"', descripcion='" + getDescripcion()+  "';";

      

        SQLException ex = conec.accion(sql);
        return ex;
    }
    
    
    
    
    
    
    
    
}
