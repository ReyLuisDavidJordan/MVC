/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author Patricio
 */
public class ModeloPersona  extends Persona{
    
    ConexionPG cpg=new ConexionPG();

    public ModeloPersona() {
    }
    public List<Persona> listarPersonas(String id) {
        ResultSet rs = null;
        String sql;
        List<Persona> listaPersonas = new ArrayList<>();
        try {
            if ("".equals(id)) {
                sql = "SELECT cedula, nombre, apellidos, fechanacimiento, telefono, sexo, sueldo, cupo, foto, correo FROM personas";
            } else {
                sql = "SELECT cedula, nombre, apellidos, fechanacimiento, telefono, sexo, sueldo, cupo, foto, correo FROM personas WHERE cedula='"+id+"'";
            }
            rs = cpg.consultaBD(sql);
            if (rs != null) {
                while (rs.next()) {
                    Persona persona = new Persona();
                     byte[] bytea;
                    persona.setIdPersona(rs.getString(1));
                    persona.setNombrePersona(rs.getString(2));
                    persona.setApellidoPersona(rs.getString(3));
                    persona.setFechanacimineto(rs.getDate(4));
                    persona.setTelefono(rs.getString(5));
                    persona.setSexo(rs.getString(6));
                    persona.setSueldo(rs.getInt(7));
                    persona.setCupo(rs.getInt(8));
                
                    persona.setCorreo(rs.getString(10));
                    listaPersonas.add(persona);
                
                }
            }
            return listaPersonas;
        } catch (SQLException ex) {
            return null;
        }
    }
   public static List<Persona> listarPersonas(){
         ConexionPG cpg=new ConexionPG();
          List<Persona> listaPersonas=new ArrayList<Persona>();
        try {
            String sql="select cedula, nombre,apellidos,fechanacimiento,telefono,sexo,sueldo,cupo,correo from personas";
            ResultSet rs= cpg.consultaBD(sql);
            while(rs.next()){
                Persona persona=new Persona();
                persona.setIdPersona(rs.getString("cedula"));
                persona.setNombrePersona(rs.getString("nombre"));
                persona.setApellidoPersona(rs.getString("apellidos"));
                persona.setFechanacimineto(rs.getDate("fechanacimiento"));
                 persona.setTelefono(rs.getString("telefono"));
                persona.setSexo(rs.getString("sexo"));
                persona.setSueldo(rs.getInt("sueldo"));
                persona.setCupo(rs.getInt("cupo"));
                persona.setCorreo(rs.getString("correo"));
                listaPersonas.add(persona);
            }
            rs.close();//CIERRO CONEXION
        return listaPersonas;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
        return null;    
        }
    }
   
   public boolean grabarPersona(){
   String sql;
   sql="INSERT INTO personas(cedula,nombre,apellidos,fechanacimiento,telefono,sexo,sueldo,cupo,correo) ";
   sql+=" VALUES('"+getIdPersona()+"','"+getNombrePersona()+"','"+getApellidoPersona()+"','"+getFechanacimineto().toString()+"','"+getTelefono()+"','"+getSexo()+"','"+getSueldo()+"','"+getCupo()+"','"+getCorreo()+"')";
   
   return cpg.accionBD(sql);
   }
    public boolean eliminarPersona(String id){
        String sql;
        sql = "DELETE FROM personas WHERE cedula='"+id+"'";
        return cpg.accionBD(sql);
    }
     public boolean Actualizar() {
         String sql;
        sql = "UPDATE personas SET nombre='"
                +getNombrePersona()+"', apellidos='"
                +getApellidoPersona()+"', fechanacimiento='"
               +"' WHERE cedula='"+getIdPersona()+"'";
        return cpg.accionBD(sql);
    }
     public SQLException modificarPersona() { 
    ConexionPG conec=new ConexionPG();
        String sql = "UPDATE personas SET nombre='" + getNombrePersona()+ "', apellidos='" + getApellidoPersona()+ "', fechanacimiento='" + getFechanacimineto()+"', telefono='" + getTelefono()+ "', sexo='" + getSexo()+ "', sueldo=" + getSueldo() + ", cupo=" + getCupo() + ", foto=null WHERE cedula = '" + getIdPersona()+ "';";

      

        SQLException ex = conec.accion(sql);
        return ex;
    }
      private Image getImage(byte[] bytes) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Iterator it = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader imageReader = (ImageReader) it.next();
        Object source = bais;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        imageReader.setInput(iis, true);
        ImageReadParam param = imageReader.getDefaultReadParam();
        param.setSourceSubsampling(1, 1, 0, 0);

        return imageReader.read(0, param);

    }
}
