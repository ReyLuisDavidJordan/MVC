/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import mvc.modelo.Persona;
import mvc.modelo.Factura;
import mvc.modelo.ModeloFactura;
import mvc.modelo.ModeloProducto;
import mvc.modelo.ModeloPersona;
import mvc.modelo.producto;
import mvc.vista.VistaFactura;
import java.util.*;
/**
 *
 * @author Patricio
 */
public class ControladorFactura12 {
   
    private ModeloFactura modelo;
    private VistaFactura vista;
    private List<Factura> factura = new ArrayList<>();

    public ControladorFactura12(ModeloFactura modelo, VistaFactura vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        ClickTabla(vista.getjTable1(), vista.getjTable2());
        cargarfactura();
        cargarpersona();
        cargarproducto();
    }

    public class ControladorMenuPrincipal {

    }

    public void iniciaControl() {
        vista.getBtnConsultar().addActionListener(buscar -> visualizar(vista.getTxtBuscar().getText()));
        vista.getBtnConsultar().addActionListener(o -> cargarfactura());
        vista.getBtnCrear().addActionListener(l -> abrirDialogo("Crear"));
        vista.getBtnEditar().addActionListener(l -> abrirDialogo("Editar"));
        vista.getElegir1().addActionListener(l ->{
            vista.getjDialog1().setVisible(true) ;
//            vista.getjDialog1().setLocationRelativeTo(null);
            vista.getjDialog1().setSize(vista.getSize());
            
                });
       vista.getElegir2().addActionListener(l ->{
            vista.getjDialog2().setVisible(true) ;
//            vista.getjDialog1().setLocationRelativeTo(null);
            vista.getjDialog2().setSize(vista.getSize());
            
                });
        vista.getBntAceptar().addActionListener(l -> crearFactura());
        vista.getBtnRemover().addActionListener(remover -> {
            if (modelo.eliminarfactura(vista.getTxtBuscar().getText())) {
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error de eliminacion");
            }
            cargarfactura();
        });
        
    }

    private void crearFactura() {
        if (vista.getDlgCrearEditar().getTitle().contentEquals("Crear")) {
           
            
            if (vista.getIdcliente().getText().isEmpty() || vista.getTotal().getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Existen campos vacios");
            } else {
                 setearDatos();

                if (modelo.grabarfactura()) {
                    JOptionPane.showMessageDialog(vista, "Registro guardado");
                    vista.getDlgCrearEditar().setVisible(false);
                    cargarfactura();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al grabar datos");
                }
            }
        }
    }

    private void visualizar(String id) {
        DefaultTableModel dtm;
        String[] columnas = {"ID", "Fecha", "Cedula Cliente", "ID Producto", "Cantidad", "Precio Unidad", "Total"};
        dtm = new DefaultTableModel(null, columnas);
        factura = modelo.listarFacturas(id);
        factura.stream().forEach(p -> dtm.addRow(new Object[]{p.getId(), p.getFecha(), p.getCedula_cliente(),
            p.getId_producto(), p.getCantidad(), p.getPrecio(), p.getTotal()}));
        vista.getTblPersonas().setModel(dtm);
        vista.getTblPersonas().setRowHeight(30);
    }

    private void abrirDialogo(String ce) {
        vista.getDlgCrearEditar().setLocationRelativeTo(vista);
        vista.getDlgCrearEditar().setSize(500, 500);
        vista.getDlgCrearEditar().setTitle(ce);
        vista.getDlgCrearEditar().setVisible(true);
    }

    private void mensaje() {
        vista.getLblPersonas().setText("Mensaje del Click");
    }

    public void cargarfactura() {
        DefaultTableModel mJTable;
        mJTable = (DefaultTableModel) vista.getTblPersonas().getModel();
        mJTable.setNumRows(0);
        List<Factura> listaP = ModeloFactura.listarfactura2();
        listaP.stream().forEach(p -> {
            String[] rowData = {String.valueOf(p.getId()), p.getFecha().toString(),
                String.valueOf(p.getCedula_cliente()), String.valueOf(p.getId_producto())};
            mJTable.addRow(rowData);
        });
    }
    public void setearDatos() {
        try {
            Date hoy = new Date();
            Long d = hoy.getTime();
            java.sql.Date date = new java.sql.Date(d);
            modelo.setFecha(date);

            modelo.setCedula_cliente(vista.getIdcliente().getText());
            modelo.setId_producto(Integer.parseInt(vista.getIdproducto().getText()));
            modelo.setPrecio(Integer.parseInt(vista.getPrecioUnidad().getText()));
            modelo.setTotal(Integer.parseInt(vista.getTotal().getText()));
            modelo.setCantidad(Integer.parseInt(vista.getCantidad().getText()));
            modelo.setEstado("Activo");
            System.out.println(modelo.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datos invalidos");
        }

    }
    public void ClickTabla(JTable t, JTable t2) {
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    String idcli = t.getValueAt(t.getSelectedRow(), 0).toString();
                    vista.getIdcliente().setText(idcli);
                    vista.getjDialog1().setVisible(false);
                }
            }
        });
        t2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    String idproduc = t2.getValueAt(t2.getSelectedRow(), 0).toString();
                    int precio = Integer.parseInt(t2.getValueAt(t2.getSelectedRow(), 2).toString());
                    try {
                        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad", 1));
                        vista.getIdproducto().setText(idproduc);
                        vista.getPrecioUnidad().setText(precio + "");
                        vista.getCantidad().setText(cantidad+"");
                        vista.getTotal().setText(""+(precio*cantidad));
                        vista.getjDialog2().setVisible(false);
                       
                    } catch (Exception e) {
                    }
            
                }
            }
        });
    }
    public void cargarpersona(){
     DefaultTableModel mJTable;
     mJTable=(DefaultTableModel)vista.getjTable1().getModel();
     mJTable.setNumRows(0);
     List<Persona> listaP= ModeloPersona.listarPersonas();
     listaP.stream().forEach(p->{
         String[] rowData={p.getIdPersona(),p.getNombrePersona(),
         p.getApellidoPersona(),String.valueOf(p.getEdad())};
         mJTable.addRow(rowData);
     });
    }
     public void cargarproducto(){
     DefaultTableModel mJTable;
     mJTable=(DefaultTableModel)vista.getjTable2().getModel();
     mJTable.setNumRows(0);
     List<producto> listaP= ModeloProducto.listarproductos();
     listaP.stream().forEach(p->{
         String[] rowData={String.valueOf(p.getId()),p.getNombre(),String.valueOf(p.getPrecio()),String.valueOf(p.getCantidad()),p.getDescripcion()};
         mJTable.addRow(rowData);
     });
    }
    
}
