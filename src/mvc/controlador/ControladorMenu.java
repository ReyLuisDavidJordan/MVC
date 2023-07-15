/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controlador;

import mvc.modelo.ModeloPersona;
import mvc.vista.NewJFrame;
import mvc.vista.VistaPersonas;
import mvc.vista.VistaProducto;
import mvc.vista.VistaFactura;
import mvc.modelo.ModeloProducto;
import mvc.controlador.ControladorProducto;
import mvc.modelo.ModeloFactura;


/**
 * 
 *
 * @author Usuario
 */
public class ControladorMenu {
    NewJFrame a;
    public ControladorMenu(NewJFrame a) {
        this.a = a;
        a.setVisible(true);
    }
    public void iniciaControl() {
        a.getjMenu1().addActionListener(l -> crudPersonas());
        a.getBotonpersona().addActionListener(l -> crudPersonas());
        a.getBotonproducto().addActionListener(l -> crudProductos());
        a.getjButton1().addActionListener(l -> crudFavtura());
      
    }
      private void crudPersonas() {
        
        VistaPersonas vista = new VistaPersonas();
        ModeloPersona modelo = new ModeloPersona();

       
      
        vista.setVisible(true);
        ControladorPersonas control = new ControladorPersonas(modelo, vista);
        control.iniciaControl();
    }
     private void crudProductos (){
        
        VistaProducto vistaF = new VistaProducto();
        ModeloProducto modeloz = new ModeloProducto();
        
       
         vistaF.setVisible(true);
 ControladorProducto control4 = new ControladorProducto(modeloz, vistaF);
         control4.iniciaControl();
         
    }
     private void crudFavtura (){
        
         VistaFactura vistaF = new VistaFactura();
         ModeloFactura modeloz = new ModeloFactura();
        
       
         vistaF.setVisible(true);
 ControladorFactura12 control4 = new ControladorFactura12(modeloz, vistaF);
         control4.iniciaControl();
         
    }
}
