package atm.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class WebCam implements Runnable {
	
	private Webcam webcam;
	
	public void TakePicture(String account_number, DatabaseController db) {
		if (webcam != null) {
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		long startTime, endTime;
		int i = 30;
		// get default webcam and open it
		startTime = System.currentTimeMillis();
		webcam = Webcam.getDefault();
		if (webcam != null) {
			webcam.open();
			BufferedImage image = webcam.getImage();

			while (i > 0){
				// get image
				image = webcam.getImage();
				i--;
			}

			// save image to PNG file
			try {
				ImageIO.write(image, "JPG", new File("test.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			webcam.close();
			
			System.out.println("Camera initialized ");
			
		}
		else {
			System.out.println("No camera detected");
		}
		endTime = System.currentTimeMillis();
		System.out.println("Run Time: " + (endTime - startTime));
		
	}

}
