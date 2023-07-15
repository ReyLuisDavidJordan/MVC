/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patricio
 */
public class ConexionPG {
    
    Connection con;
    
    String cadenaConexion="jdbc:postgresql://localhost:5433/MVC";
    String usuarioPG="postgres";
    String contraPG="1234";

    public ConexionPG() {
        
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver OK");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex );
        }
        
        try {
          con=DriverManager.getConnection(cadenaConexion, usuarioPG, contraPG);
           System.out.println("Coneccion OK");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error de conexion");
        }
        
        
    }
    
    public ResultSet consultaBD(String sql){
    
        try {
            Statement st= con.createStatement();
            return st.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public boolean accionBD(String sql){
     Statement st;
    
         try {
            st = con.createStatement();
            if (st.executeUpdate(sql) == 1) {
                st.close();
                return true;
            } else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     public SQLException accion(String sql){
        try {
            Statement st = con.createStatement();
            st.execute(sql);
            st.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
            return ex;
        }
        
    }
     
    public boolean CRUD(String sql) {
        
      
         Statement st = null;
           st = null;
        try {
            st = con.createStatement();
            st.execute(sql);
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public Connection getConection () {
        return con;
    }
    
}
