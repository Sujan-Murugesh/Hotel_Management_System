package hotel_management_system;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;
import com.googlecode.javacv.cpp.opencv_highgui;
import static hotel_management_system.Main_Window.guest_id;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static hotel_management_system.Main_Window.guest_in_image;
import static hotel_management_system.User.ImgPath;
import static hotel_management_system.User.ResizeImage;
import java.io.File;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static hotel_management_system.Main_Window.User_image_lbl;
import static hotel_management_system.Main_Window.time_lbl;

/**
 *
 * @author Sujan
 */
public class Camera{
    //user cam path
    public static String uImg;
    Thread webcam;
    CanvasFrame frame;

    //THIS RUN METHOD I CREATED TO OPEN CAMERA 
    public void run() {
        try {
            opencv_highgui.CvCapture capture = opencv_highgui.cvCreateCameraCapture(0); //[1332, 721]
            opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 700);//set height 
            opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 700);//set  width

            opencv_core.IplImage grappedImage = opencv_highgui.cvQueryFrame(capture);
            frame = new CanvasFrame("  Webcam");
            URL iconUrl = getClass().getResource("sujan.png");
            ImageIcon imageicon = new ImageIcon(iconUrl);
            frame.setIconImage(imageicon.getImage());
            frame.setLocation(100, 60);
            while (frame.isVisible() && (grappedImage = opencv_highgui.cvQueryFrame(capture)) != null) {
                frame.showImage(grappedImage);
            }         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }
    }

    //=============================================================================
    // TACK WEB CAM PICTURE
    //=============================================================================
    public void tackCapture() {
        String guestImgName = guest_id.getText();

        try {
            OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
            grabber.start();
            opencv_core.IplImage img = grabber.grab();
            if (img != null) {
                cvSaveImage("src/GuestPicture/" + guestImgName+".jpg", img);
                grabber.stop();
                //i got this picture using class gust constructor in main window 
                //setGuestImage();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    //===========================================================================
    //TACK USER CAMERA PICTURE
    public void tackCaptureUser() {
        
        String UserImgName = "UserImage";
      
        try {
            OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
            grabber.start();
            opencv_core.IplImage img = grabber.grab();
            if (img != null) {
                cvSaveImage("src/UserPicture/"+UserImgName+".jpg", img);
                grabber.stop();
                uImg = "src/UserPicture/" + UserImgName+".jpg";
                //i got this picture using class gust constructor in main window 
                //setGuestImage();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    //===========================================================================
     public void setGuestImage() {
        String guestImgName = guest_id.getText();
        ImgPath = "src/GuestPicture/" + guestImgName + ".jpg";
        File file = new File(ImgPath);

        File selectedFile = file.getAbsoluteFile();
        String path = selectedFile.getAbsolutePath();
        guest_in_image.setIcon(ResizeImage(path, null));
        ImgPath = path;
    }
     
    //set user camera image
     public void setNewUserImage() {
        //String guestImgName = guest_id.getText();
        ImgPath =uImg;
        File file = new File(ImgPath);

        File selectedFile = file.getAbsoluteFile();
        String path = selectedFile.getAbsolutePath();
        User_image_lbl.setIcon(ResizeImage(path, null));
        ImgPath = path;
    }
//======================================================================================

    public void openCamera() {
        webcam = new Thread() {
            private Object grabber;
            @Override
            public void run() {
                opencv_highgui.CvCapture capture = opencv_highgui.cvCreateCameraCapture(0);
                opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 750);//set height 
                opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 800);//set  width

                opencv_core.IplImage grappedImage = opencv_highgui.cvQueryFrame(capture);
                
                frame = new CanvasFrame("  Web Camera ");
                frame.setCanvasSize(410,460);
                URL iconUrl = getClass().getResource("camera.png");
                ImageIcon imageicon = new ImageIcon(iconUrl);
                frame.setIconImage(imageicon.getImage());
                frame.setLocation(877, 180);
                frame.setResizable(false);
                
                while (frame.isVisible() && (grappedImage = opencv_highgui.cvQueryFrame(capture)) != null) {
                    frame.showImage(grappedImage);
                }
            }
        };
        webcam.start();
    }
//======================================================================================

}
