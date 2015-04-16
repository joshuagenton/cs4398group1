package atm.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class WebCam implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub

		// get default webcam and open it
		Webcam webcam = Webcam.getDefault();
		webcam.open();

		// get image
		BufferedImage image = webcam.getImage();

		// save image to PNG file
		try {
			ImageIO.write(image, "JPG", new File("test.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		webcam.close();
		webcam.
		
		System.out.println(" this thread is running ... ");
	}

}
