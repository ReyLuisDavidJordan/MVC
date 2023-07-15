/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mvc.modelo.ModeloProducto;
import mvc.modelo.producto;

import mvc.vista.VistaProducto;

/**
 *
 * @author Usuario
 */
public class ControladorProducto {
  private ModeloProducto modelo;
    private VistaProducto vista;
    String idproduc;
private List<producto> Productosz= new ArrayList<>();
    public ControladorProducto(ModeloProducto modelo, VistaProducto vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        cargaPersonas();
        ClickTabla(vista.getTabl2());
        
    }
    
    public class ControladorMenuPrincipal{
        
    }
    public void iniciaControl(){
            vista.getBtnConsultar().addActionListener(buscar -> visualizar(vista.getTxtBuscar().getText()));
    vista.getBtnConsultar().addActionListener(o->cargaPersonas());
    vista.getBtnCrear().addActionListener(l->abrirDialogo("Crear"));
        vista.getBtnEditar().addActionListener(l->abrirDialogo("Editar"));
    vista.getjButton1().addActionListener(o->metodo1());
    
    
    vista.getBntAceptar().addActionListener(l->CrearEditarProducto());
     vista.getBtnRemover().addActionListener(remover -> {
            if (modelo.eliminarproducto(vista.getTxtBuscar().getText())) {
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error de eliminacion");
            }
          cargaPersonas();
        });
      vista.getBtnEditar().addActionListener(actualizar -> {
        
            if (modelo.Actualizar()) {
               
            } else {
              
            }
            visualizar("");
        });
    }
    public void metodo1()
    {
        mvc.vista.NewJFrame a=new mvc.vista.NewJFrame();
        a.setVisible(false);
    }
    private void visualizar(String id) {
          DefaultTableModel dtm;
           String[] columnas = {"ID", "Fecha", "Cliente", "ID PRODUCTO", "cantidad"};
        dtm = new DefaultTableModel(null, columnas);
        Productosz = modelo.listarproducs(id);
        Productosz.stream().forEach(p -> dtm.addRow(new Object[]{p.getId(), p.getNombre(), p.getPrecio(),
            p.getCantidad(), p.getDescripcion()}));
        vista.getTblPersonas().setModel(dtm);
        vista.getTblPersonas().setRowHeight(30);
    }
     private void abrirDialogo(String ce){
        vista.getDlgCrearEditar().setLocationRelativeTo(vista);
        vista.getDlgCrearEditar().setSize(500,500);
        vista.getDlgCrearEditar().setTitle(ce);     
        vista.getDlgCrearEditar().setVisible(true);
    }
    private void CrearEditarProducto(){
    if(vista.getDlgCrearEditar().getTitle().contentEquals("Crear")){
     
        ModeloProducto p=new ModeloProducto();
        if(vista.getIdproducto().getText().isEmpty()){
                JOptionPane.showMessageDialog(vista, "Existen campos vacios");
        }else{
          
        p.setNombre(vista.getNombreproducto().getText());
        String ok=vista.getPrecioproducto().getText();
        int z=Integer.parseInt(ok);
        p.setPrecio(z);
       String ok2=vista.getCantidadproducto().getText();
        int porque=Integer.parseInt(ok2);
        p.setCantidad(porque);
        p.setDescripcion(null);
        
        
       if(p.granbarProducto()){
           JOptionPane.showMessageDialog(vista, "Registro guardado");
           vista.getDlgCrearEditar().setVisible(false);
           cargaPersonas();
       }else{
           JOptionPane.showMessageDialog(vista, "Error al grabar datos");
       }
        }
    }else {
        setearDatos();
        if (modelo.Actualizar())
         JOptionPane.showMessageDialog(vista, "Funciono");
 
    
    }
    }
    public void setearDatos() {
        try {
            if (vista.getIdproducto().getText().isEmpty()||vista.getNombreproducto().getText().isEmpty()|| vista.getPrecioproducto().getText().isEmpty()
                  ) {
               
            } else {
                System.out.println("estoy vivo");
                modelo.setId(Integer.parseInt(idproduc));
                modelo.setNombre(vista.getNombreproducto().getText());
                modelo.setPrecio(Integer.parseInt(vista.getPrecioproducto().getText()));
              
                modelo.setCantidad(Integer.parseInt(vista.getCantidadproducto().getText()));
                modelo.setDescripcion(null);
                System.out.println(modelo.toString());
                //modelo.setFoto((byte)00111001);
                
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datos invalidos");
        }
        
    }
    
    
    
    
    
     public void cargaPersonas(){
     DefaultTableModel mJTable;
     mJTable=(DefaultTableModel)vista.getTabl2().getModel();
     mJTable.setNumRows(0);
     List<producto> listaP= ModeloProducto.listarproductos();
     listaP.stream().forEach(p->{
         String[] rowData={String.valueOf(p.getId()),p.getNombre(),String.valueOf(p.getPrecio()),String.valueOf(p.getCantidad()),p.getDescripcion()};
         mJTable.addRow(rowData);
     });
    }
     public void ClickTabla(JTable t) {
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 1) {
                     idproduc = t.getValueAt(t.getSelectedRow(), 0).toString();
                     Llenar();
                }
            }
        });
       
    }
     public void Llenar(){
      Productosz= modelo.listarproducs(idproduc);
      vista.getIdproducto().setText(Productosz.get(0).getId()+"");
      vista.getNombreproducto().setText(Productosz.get(0).getNombre()+"");
      vista.getPrecioproducto().setText(Productosz.get(0).getPrecio()+"");
      vista.getCantidadproducto().setText(Productosz.get(0).getCantidad()+"");
     
     }
}
