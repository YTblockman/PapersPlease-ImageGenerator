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


public class AccessPermitGenerator extends Generator {

	private String name;


	private String[] lines;
	private String enter;
	private String physical_appearence;
	
	public AccessPermitGenerator() {
		name = "name";
		this.physical_appearence = "phap";
		this.lines = new String[] {
				"l1;l2"
		};
	}
	
	public BufferedImage generate(boolean nogui) {
		try {
			name = name.toUpperCase();
			enter = enter.toUpperCase();
			physical_appearence = physical_appearence.toUpperCase();
			
			BufferedImage image = ImgNator.loadRes("AccessPermitGen.png");
			Graphics g = image.getGraphics();
			
			InputStream is = Main.class.getResourceAsStream("vaxfont.ttf");
		    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		    font = font.deriveFont(font.getSize() * 15f);
		    g.setFont(font);
		    g.setColor(Color.gray);
		    
		    int stringSize = name.length();
		    int w = font.getSize() * stringSize;
		    g.drawString(name, image.getWidth() / 2 - w/4 - stringSize, 120);
		    
		    int indent = 0;
		    
		    
		    for (String s : this.lines) {
		    	
		    	
		    	String a = s.split(";")[0];
		    	String b = s.split(";")[1];
		    	int ind = 43;
		    	int out = 165;
		    	w = font.getSize() * a.length();
		    	g.drawString(a.toUpperCase(), image.getWidth() / 2 - w/4 + a.length() - 75, indent*ind+out);
		    	w = font.getSize() * b.length();
		    	g.drawString(b.toUpperCase(), image.getWidth() / 2 - w/4 - a.length() + 60, indent*ind+out);
		    	indent ++;
		    }
		    
		    w = font.getSize() * physical_appearence.length();
		    g.drawString(physical_appearence.toUpperCase(), image.getWidth() / 2 - w/4 - physical_appearence.length(),
		    		image.getHeight() - 95);
		    
		    w = font.getSize() * enter.length();
		    g.drawString(enter.toUpperCase(), image.getWidth() / 2 - w/4 - enter.length() + 60,
		    		image.getHeight() - 50);
		    
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
			name = (String)data[0];
			this.lines = new String[3];
			lines[0] = (String)data[1];
			lines[1] = (String)data[2];
			lines[2] = (String)data[3];
			physical_appearence = (String)data[4];
			enter = (String) data[5];
			
		}catch (Exception e) {
			
			name = "name";
			this.physical_appearence = "phap";
			this.lines = new String[] {
					"l1;l2",
					"l3;l4",
					"l5;l6",
			};
		}
		
	}

	@Override
	public void generateToFile(File path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitl() {
		// TODO Auto-generated method stub
		return "name\nnationality;idnum\npurpose;duration\nheight;weight\nphysical appearence\nenterby";
	}
}
