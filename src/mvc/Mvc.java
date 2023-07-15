/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import mvc.controlador.ControladorMenu;
import mvc.controlador.ControladorPersonas;
import mvc.modelo.ModeloPersona;
import mvc.vista.NewJFrame;
import mvc.vista.VistaPersonas;

/**
 *
 * @author Patricio
 */
public class Mvc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         NewJFrame vista = new NewJFrame();
        ControladorMenu control = new ControladorMenu(vista);
        control.iniciaControl();
    }
    
}
