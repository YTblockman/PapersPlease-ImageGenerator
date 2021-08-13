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


public class VaxGenerator extends Generator {

	private String name;
	private String id;
	
	private String[] listVax;
	private String[] listVaxDates;
	
	public VaxGenerator() {
		name = "IDK";
		id = "ARSTTZK?";
		listVax = new String[] {
		};
		
		listVaxDates = new String[] {
		};
	}
	
	public BufferedImage generate(boolean nogui) {
		try {
			name = name.toUpperCase();
			id = id.toUpperCase();
			
			BufferedImage image = ImgNator.loadRes("VaxGen.png");
			Graphics g = image.getGraphics();
			
			InputStream is = Main.class.getResourceAsStream("vaxfont.ttf");
		    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		    font = font.deriveFont(font.getSize() * 15f);
		    g.setFont(font);
		    g.setColor(Color.blue);
		    int stringSize = id.length();
		    int w = font.getSize() * stringSize;
		    g.drawString(id, image.getWidth() / 2 - w/4, 140);
		    
		    stringSize = name.length();
		    w = font.getSize() * stringSize;
		    g.drawString(name, image.getWidth() / 2 - w/4, 115);
		    
		    int indent = 0;
		    
		    for (String s : this.listVaxDates) {
		    	g.drawString(s, 40, indent*22+205);
		    	indent ++;
		    }
		    
		    indent = 0;
		    
		    for (String s : this.listVax) {
		    	g.drawString(s, 130, indent*22+205);
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
			id = (String)data[1];
			
			listVax = ((String)data[2]).split(";");
			listVaxDates = ((String)data[3]).split(";");
		}catch (Exception e) {
			e.printStackTrace();
			name = "name";
			id = "id";
			listVax = new String[] {
				"covid19"
			};
			
			listVaxDates = new String[] {
				"date1"	
			};
			
		}
		
	}

	@Override
	public String getTitl() {
		return "name\nid\nvax1;vax2\ntime1;time2";
	}
	
	@Override
	public void generateToFile(File path) {
		
	}
}
