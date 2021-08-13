package io.hypronix.paperspleasegenerator.passport.generators;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import io.hypronix.paperspleasegenerator.ImageManager;
import io.hypronix.paperspleasegenerator.Main;
import io.hypronix.paperspleasegenerator.img.ImgNator;
import io.hypronix.paperspleasegenerator.passport.PassportGenerator;

public class AntegriaPassportGenerator extends PassportGenerator {

	@Override
	public String getRequiredFields() {
		return "DateOfBirth\nM/F\nSt. Marmero\nExpiration\nName, Surname\nPASS-ID";
	}

	@Override
	public BufferedImage generateImage(boolean nogui, String[] data) throws FontFormatException, IOException {

		BufferedImage image = ImgNator.loadRes("passports/Antegria.png");
		Graphics graphics = image.getGraphics();
		
		InputStream is = Main.class.getResourceAsStream("vaxfont.ttf");
	    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
	    font = font.deriveFont(font.getSize() * 15f);
	    graphics.setFont(font);
	    graphics.setColor(Color.DARK_GRAY);
		
	    String nameLong;
	    String dateOfBirth;
	    String maleFemaleId;
	    String issuingCity;
	    String expiration;
	    String passportId;
	    
	    try {
	    	nameLong = data[4];
		    dateOfBirth = data[0];
		    maleFemaleId = data[1];
		    issuingCity = data[2];
		    expiration = data[3];
		    passportId = data[5];
	    }catch (Exception e) {
	    	nameLong = "Name, Surname";
		    dateOfBirth = "DateOfBirth";
		    maleFemaleId = "M/F";
		    issuingCity = "St. Marmero";
		    expiration = "ExpirationDate";
		    passportId = "PASS-ID";
	    }
	    
	    graphics.drawString(nameLong, 20, image.getHeight() - 35);
	    graphics.drawString(dateOfBirth, 50, 213);
	    graphics.drawString(maleFemaleId, 50, 233);
	    graphics.drawString(issuingCity, 50, 250);
	    graphics.drawString(expiration, 50, 268);
	    graphics.drawString(passportId, image.getWidth() - (passportId.length() * font.getSize() -
	    		passportId.length()*4), image.getHeight() - 15);
	  
	    
	    if (!nogui) {
	    	JFrame frame = new JFrame(this.getClass().getSimpleName());
	    	
	    	frame.setVisible(true);
		    JMenuBar bar = new JMenuBar();
		    JMenu file = new JMenu("File");
		    
		    JMenuItem savetofile = new JMenuItem("Save");
		    	
		    savetofile.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					File outputfile = new File(new Random().nextLong() + ".png");
					try {
						System.out.println(outputfile.getPath());
						ImageIO.write(image, "png", outputfile);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			});
		    file.add(savetofile);
		    
		    bar.add(file);
		    
		    frame.setJMenuBar(bar);
		    frame.getContentPane().add(new JLabel(new ImageIcon(image)));
		    frame.pack();
		    frame.setResizable(false);
	    	
	    }
	    
		return image;
	}

}
