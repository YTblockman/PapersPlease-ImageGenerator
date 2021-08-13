package io.hypronix.paperspleasegenerator.generators;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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

import io.hypronix.paperspleasegenerator.Main;
import io.hypronix.paperspleasegenerator.img.ImgNator;


public class TheTruthOfArstotzkaGenerator extends Generator {

	
	private String[] internalData;
	
	private String line1;
	private String line2;
	private String line3;
	
	public TheTruthOfArstotzkaGenerator() {
		
	}
	
	public BufferedImage generate(boolean nogui) {
		try {
			
			
			
			BufferedImage image = ImgNator.loadRes("TruthOfArstotzkaGen.png");
			Graphics g = image.getGraphics();
			
			internalData = new String[] {
					line1,
					line2,
					line3
			};
			
			InputStream is = Main.class.getResourceAsStream("theTrueFont.ttf");
		    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		    font = font.deriveFont(font.getSize() * 30f);
		    
		    g.setFont(font);
		    g.setColor(Color.black);
		    int indent = 0;
		    
		    String title = internalData[0];
		    
		    int stringSize = title.length();
		    int w = font.getSize() * stringSize;
		    
		    
		    g.drawString(title, image.getWidth()/2 - w/4 + stringSize*2, 150);
		    
		    
		    is = Main.class.getResourceAsStream("vaxfont.ttf");
		    font = Font.createFont(Font.TRUETYPE_FONT, is);
		    font = font.deriveFont(font.getSize() * 15f);
		    g.setFont(font);
		    
		    
		    int i = 0;
		    

		    
		    for (String subtitle : this.internalData) {
		    	i++;
		    	if (i < 2) continue;
		    	stringSize = subtitle.length();
			    w = font.getSize() * stringSize;
			    
			    g.drawString(subtitle ,image.getWidth()/2 - w/4 - stringSize/2, 150+40 + 20*indent);
		    	indent ++;
		    }
		    
		    if (!nogui) {
		    	JFrame frame = new JFrame("Output");
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void setData(Object[] data) {
		try {
			line1 = (String)data[0];
			line2 = (String)data[1];
			try {
				line3 = (String)data[2];
			}catch(Exception e) {
				line3 = "";
			}
		}catch (Exception e) {
			
			line1 = "new title";
			line2 = "subtitle1";
			line3 = "subtitle2";
			
		}
	}

	@Override
	public String getTitl() {
		return "title\nsub\nsub2";
	}
	
	@Override
	public void generateToFile(File path) {
		// TODO Auto-generated method stub
		
	}
}
