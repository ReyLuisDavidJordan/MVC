package mvc.vista.reportes;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import mvc.modelo.ConexionPG;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Reporte {

    Map<String, Object> map;
    String url;

    public void imprimir_factura(int id) {
        map = new HashMap<>();
        map.put("parametro_id", id);
        try {
            String min = JOptionPane.showInputDialog(null, "Ingrese un mínimo para el subtotal:");
            String max = JOptionPane.showInputDialog(null, "Ingrese un máximo para el subtotal:");
            
            map.put("min", Double.valueOf(min));
            map.put("max", Double.valueOf(max));
            
            System.out.println("minimo: "+min);
            System.out.println("maximo: "+max);
            url = "/reporte/Factura.jasper";
            print(map, url);
        } catch (Exception e) {
        }
    }
    
    public void imprimir_personas() {
        map = new HashMap<>();
        url = "/reporte/Persona.jasper";
        print(map, url);
    }

    public void print(Map<String, Object> map, String url) {

        try {
           
            ConexionPG con = new ConexionPG();
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource(url));
            JasperPrint print = JasperFillManager.fillReport(jr, map, con.getConection());
            JasperViewer pv = new JasperViewer(print, false);
            pv.setVisible(true);
            pv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Archivo de reporte no encontrado", null, JOptionPane.ERROR_MESSAGE);
        }
    }
}
