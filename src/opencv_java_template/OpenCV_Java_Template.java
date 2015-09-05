/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv_java_template;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.*;


/**
 *
 * @author Damian
 */
public class OpenCV_Java_Template
{
    /**
     * @param args the command line arguments
     * 
     * Este Template implementa un test que puede correrse desde la consola
     * con:
     * 
     * java -jar OpenCV_Java_Template.jar --run-test
     * 
     * Para detalles del test, ver metodo RunTemplateTest().
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException 
    {
        /**
         * Chequea los argumentos de la linea de comando, si esta presente
         * el argumento --run-test como primer parametro, corre el test.
         * 
         * **NOTA:  actualmente el argumento --run-test esta siempre presente,
         *          para removerlo/cambiarlo ir a File->Project Properties->Run->Arguments.
         */
        if( args.length == 1 )  //hay argumentos en la linea de comandos?
            if( args[0].equals("--run-test") )  //el primer arg es --run-test?
                RunTemplateTest();  //correr el test
        //TODO User code goes here:
        
    }
    
    private static void RunTemplateTest() throws IOException
    {
        /**
         * RunTemplateTest:
         * Este test carga la libreria dinamica opencv249 chequeando
         * automaticamente la arquitectura (x86/x64) y tipo (linux/windows) del
         * sistema. Una vez cargada la libreria, inicia una interface grafica
         * que lee y muestra todas las imagenes *.jpg disponibles en el
         * directorio de trabajo.
         * Ademas de lo anterior, este metodo muestra en consola las propiedades
         * del sistema (tipo y arquitectura del sistema, java.library.path,
         * java.class.path y otras variables interntas).
         * 
         * **Nota:  este metodo es de debug, y deberia removerse en versiones
         *          Release.
         */
        
        //Muestra en consola las propiedades del sistema:
        System.out.println("\n ### Running Template Test ###\n");
        Properties p = System.getProperties();
        Enumeration keys = p.keys();
        int i = 1;
        while( keys.hasMoreElements() )
        {
            String key = (String)keys.nextElement();
            String value = (String)p.get(key);
            System.out.println(i+")"+key + ": " + value);
            i++;
        }
        
        //Muestra en consola un resumen de las propiedades del sistema mas
        //importantes: OS, ARCH, y java.labrary.path
        System.out.println("\n# Sumary:");
        System.out.println("  OS    "+System.getProperty("os.name"));
        System.out.println("  ARCH  "+System.getProperty("os.arch"));

        System.out.println("  java.library.path:\n"+System.getProperty("java.library.path"));
        System.out.println("");
        
        //Segun la arquitectura del sistema (x86/x64) carga la libreria dinamica
        //opencv249 que corresponda
        if( System.getProperty("os.arch").contains("64")==true )
        {
            //Carga libreria de x64
            System.out.println("---> Loading dll/so: "+Core.NATIVE_LIBRARY_NAME+"_x64");
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME+"_x64");
        }
        else
        {
            //Carga libreria de x86
            System.out.println("---> Loading dll/so: "+Core.NATIVE_LIBRARY_NAME+"_x86");
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME+"_x86");
        }
        //Si llega hasta aca, es porque la libreria se cargo con exito, muestra
        //mensaje de confirmacion
        System.out.println("---> dll/so loaded.");

            //Inicia la interface grafica que lee y muestra todos las imagenes *.jpg
            //disponibles en el directorio de trabajo:
            TemplateTestWindow win = new TemplateTestWindow();
            win.setVisible(true);
            //win.dispose();
            //TemplateTestWindow.main(null);
    }
}
