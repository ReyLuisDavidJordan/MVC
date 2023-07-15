/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import mvc.modelo.ConexionPG;
import mvc.modelo.ModeloPersona;
import mvc.modelo.Persona;
import mvc.vista.VistaPersonas;
import mvc.vista.reportes.Reporte;

/**
 *
 * @author Patricio
 */
public class ControladorPersonas {
    
    private ModeloPersona modelo;
    private VistaPersonas vista;
private List<Persona> personas= new ArrayList<>();
    public ControladorPersonas(ModeloPersona modelo, VistaPersonas vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        cargaPersonas();
    }
    public class ControladorMenuPrincipal{
        
    }
            
    
    public void iniciaControl(){
            vista.getBtnConsultar().addActionListener(buscar -> visualizar(vista.getTxtBuscar().getText()));
    vista.getBtnConsultar().addActionListener(o->cargaPersonas());
    vista.getBtnCrear().addActionListener(l->abrirDialogo("Crear"));
        vista.getBtnEditar().addActionListener(l->abrirDialogo("Editar"));
     vista.getFoto2().addActionListener(l -> Foto());
    //Acciones del Dialogo
     vista.getBtnImprimir().addActionListener(l-> { Reporte r = new Reporte();
                r.imprimir_personas();});
    vista.getBntAceptar().addActionListener(l->crearEditarPersona());
     vista.getBtnRemover().addActionListener(remover -> {
            if (modelo.eliminarPersona(vista.getTxtBuscar().getText())) {
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error de eliminacion");
            }
          cargaPersonas();
        });
      vista.getBtnEditar().addActionListener(actualizar -> {
            setearDatos();
            if (modelo.Actualizar()) {
               
            } else {
              
            }
            visualizar("");
        });
    }
    
    
    private void crearEditarPersona(){
    if(vista.getDlgCrearEditar().getTitle().contentEquals("Crear")){
        //Acciones de Validacion
        ModeloPersona p=new ModeloPersona();
        if(vista.getTxtCedula().getText().isEmpty()){
                JOptionPane.showMessageDialog(vista, "Existen campos vacios");
        }else{
        p.setIdPersona(vista.getTxtCedula().getText());
        p.setNombrePersona(vista.getTxtNombres().getText());
        p.setApellidoPersona(vista.getTxtApellidos().getText());
       
        Date fechan= Date.valueOf(LocalDate.of(
                vista.getCalFechaNac().getDate().getYear()+1900,
                vista.getCalFechaNac().getDate().getMonth()+1,
                vista.getCalFechaNac().getDate().getDate()));
        p.setFechanacimineto(fechan);
        p.setTelefono(vista.getTelefono().getText());
        p.setSexo(vista.getSexo22().getText());
        int z=Integer.parseInt(vista.getSueldo1().getText());
        p.setSueldo(z);
        int k=Integer.parseInt(vista.getCupo1().getText());
        p.setCupo(k);
        p.setFoto(vista.getTxtruta().getText());
        p.setCorreo(vista.getCorreo22().getText());
        vista.getTxtruta().setText(p.getFoto());

                ImageIcon miImagen = new ImageIcon(vista.getTxtruta().getText());
                Image foto = miImagen.getImage();
                foto = foto.getScaledInstance(145, 145, Image.SCALE_SMOOTH);
                vista.getLblFoto().setIcon(new ImageIcon(foto));
        
       if(p.grabarPersona()){
           JOptionPane.showMessageDialog(vista, "Registro guardado");
           vista.getDlgCrearEditar().setVisible(false);
           cargaPersonas();
       }else{
           JOptionPane.showMessageDialog(vista, "Error al grabar datos");
       }
        }
    }else {
             String cedula = vista.getTxtCedula().getText();
        String nombres = vista.getTxtNombres().getText();
        String apellidos = vista.getTxtApellidos().getText();
        String sexo = vista.getSexo22().getText();
        String telefono = vista.getTelefono().getText();
        java.util.Date fecha = vista.getCalFechaNac().getDate();
            double sueldo = Double.parseDouble(vista.getSueldo1().getText());
            int cupo = Integer.parseInt(vista.getCupo1().getText());

            ModeloPersona persona = new ModeloPersona();
            persona.setIdPersona(cedula);
            persona.setNombrePersona(nombres);
            persona.setApellidoPersona(apellidos);
            persona.setSexo(sexo);
            persona.setTelefono(telefono);

            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());//Paso de util.Date a sql.Date
            persona.setFechanacimineto(fechaSQL);
            persona.setSueldo(sueldo);
            persona.setCupo(cupo);

        if (persona.modificarPersona()==null) {
            vista.getDlgCrearEditar().setVisible(false);
            JOptionPane.showMessageDialog(vista, "Persona Modificada Satisfactoriamente");
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo modificar la persona");
        }
    
    }
    }
     private void visualizar(String id) {
          DefaultTableModel dtm;
           String[] columnas = {"idpersona", "nombres", "apellidos", "edad", "telefono", "sexo", "sueldo", "cupo","email"};
        dtm = new DefaultTableModel(null, columnas);
        personas = modelo.listarPersonas(id);
        personas.stream().forEach(p -> dtm.addRow(new Object[]{p.getIdPersona(), p.getNombrePersona(), p.getApellidoPersona(),
            p.getEdad(), p.getTelefono(), p.getSexo(), p.getSueldo(), p.getCupo(), p.getCorreo()}));
        vista.getTblPersonas().setModel(dtm);
        vista.getTblPersonas().setRowHeight(30);
    }







    private void abrirDialogo(String ce){
        vista.getDlgCrearEditar().setLocationRelativeTo(vista);
        vista.getDlgCrearEditar().setSize(500,500);
        vista.getDlgCrearEditar().setTitle(ce);     
        vista.getDlgCrearEditar().setVisible(true);
    }
    
    private void mensaje(){
       vista.getLblPersonas().setText("Mensaje del Click"); 
    }
   
    public void cargaPersonas(){
     DefaultTableModel mJTable;
     mJTable=(DefaultTableModel)vista.getTblPersonas().getModel();
     mJTable.setNumRows(0);
     List<Persona> listaP= ModeloPersona.listarPersonas();
     listaP.stream().forEach(p->{
         String[] rowData={p.getIdPersona(),p.getNombrePersona(),
         p.getApellidoPersona(),String.valueOf(p.getEdad())};
         mJTable.addRow(rowData);
     });
    }
    public void setearDatos() {
        try {
            if (vista.getTxtCedula().getText().isEmpty()||vista.getTxtNombres().getText().isEmpty()|| vista.getTxtApellidos().getText().isEmpty()
                  ) {
               
            } else {
                modelo.setIdPersona(vista.getTxtCedula().getText());
                modelo.setNombrePersona(vista.getTxtNombres().getText());
                modelo.setApellidoPersona(vista.getTxtApellidos().getText());
                java.util.Date fecha = vista.getCalFechaNac().getDate();
                Long d = fecha.getTime();
                java.sql.Date nac = new java.sql.Date(d);
                modelo.setFechanacimineto(nac);
                modelo.setFoto(vista.getFoto2().getText());
               
                //modelo.setFoto((byte)00111001);
                
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datos invalidos");
        }
        
    }
     public void Foto() {
        
//        
//         este método permite al usuario seleccionar una imagen de un archivo y mostrarla en un componente de la interfaz gráfica.
//         La ruta del archivo seleccionado se guarda en un campo de texto y la imagen se muestra en un componente de etiqueta (JLabel).

        JFileChooser file = new JFileChooser();
        file.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JGP, PNG, & GIF", "jpg", "png", "gif");
        file.setFileFilter(filtrado);
        file.setDialogTitle("Abrir Imagen");

        File Ruta = new File("C:\\Users\\blink\\Pictures");
        file.setCurrentDirectory(Ruta);

        int res = file.showOpenDialog(vista);
        if (res == JFileChooser.APPROVE_OPTION) {

            File archivo = file.getSelectedFile();
            vista.getTxtruta().setText(String.valueOf(archivo));
            ImageIcon miImagen = new ImageIcon(vista.getTxtruta().getText());
            Image foto = miImagen.getImage();
            foto = foto.getScaledInstance(vista.getLblFoto().getWidth(), vista.getLblFoto().getHeight(), Image.SCALE_SMOOTH);
            vista.getLblFoto().setIcon(new ImageIcon(foto));
        }

    }
    
            
    
}
