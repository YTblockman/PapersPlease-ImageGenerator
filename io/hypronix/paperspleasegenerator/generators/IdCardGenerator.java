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


public class IdCardGenerator extends Generator {

	
	private String[] internalData;
	
	private String name;
	private String surname;
	private String line1;
	private String line2;
	private String line3;
	
	public IdCardGenerator() {
		internalData = new String[] {
				
		};
	}
	
	public BufferedImage generate(boolean nogui) {
		try {
			
			name = name.toUpperCase();
			surname = surname.toUpperCase();
			line1 = line1.toUpperCase();
			line2 = line2.toUpperCase();
			line3 = line3.toUpperCase();
		
			
			BufferedImage image = ImgNator.loadRes("IDCardGen.png");
			Graphics g = image.getGraphics();
			
			InputStream is = Main.class.getResourceAsStream("vaxfont.ttf");
		    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		    font = font.deriveFont(font.getSize() * 13f);
		    g.setFont(font);
		    g.setColor(Color.DARK_GRAY);
		    int indent = 0;
		    
			internalData = new String[] {
					name,
					surname,
					line1,
					line2,
					line3
			};
		    
		    String name = internalData[0];
		    String surname = internalData[1];
		    
		    g.drawString(name, 105, 54);
		    g.drawString(surname, 105, 66);
		    
		    int i = 0;
		    
		    for (String s : this.internalData) {
		    	i++;
		    	if (i < 3) continue;
		    	g.drawString(s, 135, indent*20+90);
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
			
			return null;
		}
	}

	@Override
	public void setData(Object[] data) {
		try {
			name = (String)data[0];
			surname = (String)data[1];
			line1 = (String)data[2];
			line2 = (String)data[3];
			line3 = (String)data[4];
		}catch (Exception e) {
			
			name = "name";
			surname = "surname";
			line1 = "line1";
			line2 = "line2";
			line3 = "line3";
			
			
		}
		
	}

	@Override
	public void generateToFile(File path) {
		
		
	}
	
	@Override
	public String getTitl() {
		return "name\nsurname\nline1\nline2\nline3";
	}
}
