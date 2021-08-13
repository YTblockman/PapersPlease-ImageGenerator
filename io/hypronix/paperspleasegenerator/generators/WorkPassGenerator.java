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


public class WorkPassGenerator extends Generator {

	
	private String[] internalData;
	
	private String line1;
	private String line2;
	private String line3;
	
	public WorkPassGenerator() {
		internalData = null;
	}
	
	public BufferedImage generate(boolean nogui) {
		
		line1 = line1.toUpperCase();
		line2 = line2.toUpperCase();
		line3 = line3.toUpperCase();
		
		try {
			BufferedImage image = ImgNator.loadRes("WorkPassGen.png");
			Graphics g = image.getGraphics();
			
			InputStream is = Main.class.getResourceAsStream("vaxfont.ttf");
		    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		    font = font.deriveFont(font.getSize() * 15f);
		    g.setFont(font);
		    g.setColor(Color.gray);
	
		    int w;
		    
		    internalData = new String[] {
					this.line1,
					this.line2,
					this.line3
			};
		 
		    int indent = 0;
		    
		    for (String s : this.internalData) {
		    	w = font.getSize() * s.length();
		    	g.drawString(s, image.getWidth() / 2 - w/4 - s.length() + 30, indent*30+146);
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
			line3 = (String)data[2];
		}catch (Exception e) {
			line1 = "line1";
			line2 = "line2";
			line3 = "line3";
		}
		
	}

	@Override
	public String getTitl() {
		return "line1\nline2\nline3";
	}
	
	@Override
	public void generateToFile(File path) {
		// TODO Auto-generated method stub
		
	}
}
