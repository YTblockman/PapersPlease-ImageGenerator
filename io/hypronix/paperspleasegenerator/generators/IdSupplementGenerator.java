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


public class IdSupplementGenerator extends Generator {

	
	private String[] internalData;
	
	private String line1;
	private String line2;
	private String line3;
	private String line4;
	private String line5;
	
	public IdSupplementGenerator() {
		internalData = new String[] {
		};
	}
	
	public BufferedImage generate(boolean nogui) {
		try {
			
			line1 = line1.toUpperCase();
			line2 = line2.toUpperCase();
			line3 = line3.toUpperCase();
			line4 = line4.toUpperCase();
			line5 = line5.toUpperCase();
			
			BufferedImage image = ImgNator.loadRes("IDSuplGen.png");
			Graphics g = image.getGraphics();
			
			InputStream is = Main.class.getResourceAsStream("vaxfont.ttf");
		    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		    font = font.deriveFont(font.getSize() * 15f);
		    
		    g.setFont(font);
		    g.setColor(Color.DARK_GRAY);
		    int indent = 0;
		    
		    internalData = new String[] {
					line1,
					line2,
					line3,
					line4,
					line5
			};
		    
		    String name = internalData[0];
		    String surname  = internalData[1];
		    
		    int stringSize = name.length();
		    int w = font.getSize() * stringSize;
		    
		    
		    g.drawString(name,image.getWidth() - w - stringSize, 75);
		    stringSize = surname.length();
		    w = font.getSize() * stringSize;
		    
		    g.drawString(surname,image.getWidth() - w - stringSize, 95);
		    
		    int i = 0;
		    
		    String expiry = internalData[2];
		    
		    for (String s : this.internalData) {
		    	i++;
		    	if (i < 4) continue;
		    	g.drawString(s, 22, indent*16+145);
		    	indent ++;
		    }
		
		    g.setColor(Color.RED);
		    g.setFont(font);  
		    g.drawString(expiry, 63, image.getHeight() - 10);
		    
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
			line4 = (String)data[3];
			line5 = (String)data[4];
		}catch (Exception e) {
			
			line1 = "line1";
			line2 = "line2";
			line3 = "line3";
			line4 = "line4";
			line5 = "line5";
			
			
		}
		
	}

	@Override
	public String getTitl() {
		return "line1\nline2\nline3\nline4\nline5";
	}

	@Override
	public void generateToFile(File path) {
		// TODO Auto-generated method stub
		
	}
}
