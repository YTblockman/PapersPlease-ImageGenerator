package io.hypronix.paperspleasegenerator.img;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class ImgNator {
	
	private static ArrayList<String> cannotLoadRes = new ArrayList<String>();
	
	public static BufferedImage loadRes(String res) {
		try {
			return ImageIO.read(ImgNator.class.getResource(res));
		}catch (Exception e) {
				cannotLoadRes.add(res);
			 e.printStackTrace();
		
		}
		return null;
	}
}
