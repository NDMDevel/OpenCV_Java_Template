/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv_java_template;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author Damian
 */
public class TemplateTestWindow extends javax.swing.JFrame
{
    private Mat image;
    private Mat frame;
    private VideoCapture cam;
    private final ArrayList<String> imagesName;
    private TemplateTestWindowVideoThread videoThread;
    //constructor por defecto
    public TemplateTestWindow() throws IOException
    {
        //Inicia la interface grafica. Debe llamarse primero
        initComponents();

        //Cambia Look&Feel
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(TemplateTestWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(this);
        pack();
        
        /*
            IconName contiene el nombre del icono de la aplicacion. 
            Los iconos disponibles se encuentran en el directorio IconsPNG
        */
        String IconName = "Smart2.png";
        Image img = ImageIO.read(getClass().getResource("IconsPNG/"+IconName));
        setIconImage(img);  //carga el icono en la aplicacion
        
        File dir = (new File("./"));
        String[] aux = dir.list();
        int i = 0; 
        int j = 0;
        imagesName = new ArrayList();
        while( i<aux.length  )
        {
            if( aux[i].length()>5 )
                if( aux[i].substring(aux[i].length()-4).equals(".jpg") )
                    imagesName.add(aux[i]);
            i++;
        }
        System.out.println("Images [*.jpg] found:");
        for( i = 0 ; i<imagesName.size() ; i++ )
        {
            ImageList.addItem(imagesName.get(i));
            System.out.println(((int)(i+1))+") "+imagesName.get(i));
        }
        ShowImageLabel.setText(null);
        
        /*
            Busca las cameras conectadas a la PC y las lista en el combobox
        */
        cam = new VideoCapture();
        for( int cam_id=0 ; cam_id<10 ; cam_id++ )
        {
            if( !cam.open(cam_id) )
                break;
            try
            {
                frame = new Mat(Highgui.CV_CAP_PROP_FRAME_WIDTH,Highgui.CV_CAP_PROP_FRAME_HEIGHT,CvType.CV_8UC3);
                cam.read(frame);
                CameraList.addItem("Device ID: "+cam_id);
            }
            catch(Exception e)
            {
                cam.release();
                break;
            }
        }
        if( cam.isOpened() )
            cam.release();
        videoThread = null;
    }
    
    private BufferedImage toBufferedImage(Mat m)
    {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if( m.channels() > 1)
            type = BufferedImage.TYPE_3BYTE_BGR;
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b); // get all the pixels
        BufferedImage aux_image = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) aux_image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return aux_image;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        ShowImageBtn = new javax.swing.JButton();
        ImageList = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowImageLabel = new javax.swing.JLabel();
        CameraList = new javax.swing.JComboBox();
        InitVideoBtn = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OpenCV_Java_Template");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        ShowImageBtn.setText("Show Image");
        ShowImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowImageBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(ShowImageBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 224;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(ImageList, gridBagConstraints);

        jScrollPane1.setBorder(null);

        ShowImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ShowImageLabel.setText("jLabel1");
        jScrollPane1.setViewportView(ShowImageLabel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 359;
        gridBagConstraints.ipady = 227;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 12, 12);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(CameraList, gridBagConstraints);

        InitVideoBtn.setText("Start Video");
        InitVideoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InitVideoBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(InitVideoBtn, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ShowImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowImageBtnActionPerformed
        // TODO add your handling code here:
        image = Highgui.imread((String) ImageList.getItemAt(ImageList.getSelectedIndex()));
        ShowImageLabel.setIcon(new ImageIcon(toBufferedImage(image)));    
    }//GEN-LAST:event_ShowImageBtnActionPerformed

    private void InitVideoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InitVideoBtnActionPerformed
        // TODO add your handling code here:
        if( InitVideoBtn.isSelected() )
        {
            InitVideoBtn.setText("Stop Video");
            CameraList.setEnabled(false);
            ShowImageBtn.setEnabled(false);
            ImageList.setEnabled(false);
            cam.open(CameraList.getSelectedIndex());
            videoThread = new TemplateTestWindowVideoThread(cam,ShowImageLabel,1);
            videoThread.start();
        }
        else
        {
            videoThread.TerminateThread();
            InitVideoBtn.setText("Start Video");
            CameraList.setEnabled(true);
            ShowImageBtn.setEnabled(true);
            ImageList.setEnabled(true);
        }
    }//GEN-LAST:event_InitVideoBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TemplateTestWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TemplateTestWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TemplateTestWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TemplateTestWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TemplateTestWindow().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(TemplateTestWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CameraList;
    private javax.swing.JComboBox ImageList;
    private javax.swing.JToggleButton InitVideoBtn;
    private javax.swing.JButton ShowImageBtn;
    private javax.swing.JLabel ShowImageLabel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
