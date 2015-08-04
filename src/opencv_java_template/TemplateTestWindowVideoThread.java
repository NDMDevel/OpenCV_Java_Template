/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv_java_template;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author damian
 */
public class TemplateTestWindowVideoThread extends Thread
{
    private int fps;
    private boolean exit;
    private JLabel imshow;
    private VideoCapture cam;
    public TemplateTestWindowVideoThread(VideoCapture cam,JLabel lab,int fps)
    {
        this.cam = cam;
        imshow = lab;
        this.fps = fps;
        exit = false;
    }
    public TemplateTestWindowVideoThread(VideoCapture cam,JLabel lab)
    {
        this.cam = cam;
        imshow = lab;
        this.fps = 1000/25;    //40 ms por defecto (25 fps)
        exit = false;
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
    public void TerminateThread()
    {
        exit = true;
    }
    public synchronized void run()
    {
        Mat frame = new Mat(Highgui.CV_CAP_PROP_FRAME_WIDTH,Highgui.CV_CAP_PROP_FRAME_HEIGHT,CvType.CV_8UC3);
        while(!exit)
        {
            cam.read(frame);
            imshow.setIcon(new ImageIcon(toBufferedImage(frame)));
            try
            {
                sleep(fps);
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(TemplateTestWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
