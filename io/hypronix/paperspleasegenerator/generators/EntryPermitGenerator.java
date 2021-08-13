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


public class EntryPermitGenerator extends Generator {

	private String name;
	private String id;
	
	private String type;
	private String date;
	private String days;
	
	private String[] internalData;
	
	public EntryPermitGenerator() {
		name = "name";
		id = "id";
		internalData = new String[] {
				"VISIT",
				"31 DAYS",
				"8/6/21"
		};
	}
	
	public BufferedImage generate(boolean nogui) {
		try {
			name = name.toUpperCase();
			id = id.toUpperCase();
			type = type.toUpperCase();
			date = date.toUpperCase();
			days = days.toUpperCase();
			
			BufferedImage image = ImgNator.loadRes("EntryPermitGen.png");
			Graphics g = image.getGraphics();
			
			InputStream is = Main.class.getResourceAsStream("vaxfont.ttf");
		    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		    font = font.deriveFont(font.getSize() * 15f);
		    g.setFont(font);
		    g.setColor(Color.gray);
		    int stringSize = id.length();
		    int w = font.getSize() * stringSize;
		    g.drawString(id, image.getWidth() / 2 - w/4 - stringSize, 195+65);
		    
		    stringSize = name.length();
		    w = font.getSize() * stringSize;
		    g.drawString(name, image.getWidth() / 2 - w/4 - stringSize, 195);
		    
		    int indent = 0;
		    
		    this.internalData = new String[] {
		    		type,
		    		days,
		    		date
		    };
		    
		    for (String s : this.internalData) {
		    	w = font.getSize() * s.length();
		    	g.drawString(s, image.getWidth() / 2 - w/4 - s.length() + 30, indent*29+293);
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
			name = (String)data[0];
			id = (String)data[1];
			type = (String)data[2];
			days = (String)data[3];
			date = (String)data[4];
		}catch (Exception e) {
			
			name = "name";
			id = "id?";
			this.type = "VISIT";
			this.days = "31 DAYS";
			this.date = "1.1.21";
		}
		
	}

	@Override
	public void generateToFile(File path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitl() {
		// TODO Auto-generated method stub
		return "name\nid\ntype\ntime\ndate";
	}
}
