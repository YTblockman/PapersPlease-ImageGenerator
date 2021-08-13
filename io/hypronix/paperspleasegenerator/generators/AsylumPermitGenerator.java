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


public class AsylumPermitGenerator extends Generator {

	private String name;
	private String surname;
	private String id;
	private String nation;
	private String dob;
	private String ht;
	private String wt;
	private String exp;
	
	private String[] internalData;
	
	public AsylumPermitGenerator() {
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

			BufferedImage image = ImgNator.loadRes("AsylumGen.png");
			Graphics g = image.getGraphics();
			
			InputStream is = Main.class.getResourceAsStream("vaxfont.ttf");
		    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		    font = font.deriveFont(font.getSize() * 15f);
		    g.setFont(font);
		    g.setColor(Color.gray);
		    g.drawString(surname.toUpperCase(), image.getWidth()/2, 120 + 15);
		    
		    g.drawString(name.toUpperCase(), image.getWidth()/2, 120);
		    
		    int indent = 0;
		    
		    this.internalData = new String[] {
		    		nation,
		    		id,
		    		dob,
		    		ht,
		    		wt
		    };
		    
		    for (String s : this.internalData) {
		    	g.drawString(s.toUpperCase(), image.getWidth()/2 + 30, indent*18+165);
		    	indent ++;
		    }
		    
		    g.drawString(exp.toUpperCase(), image.getWidth()/2, image.getHeight() - 35);
		    
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
			surname = (String)data[1];
			exp	 = (String)data[2];
			nation = (String)data[3];
			id = (String)data[4];
			dob = (String)data[5];
			ht = (String)data[6];
			wt = (String)data[7];
		}catch (Exception e) {
			
			name = "name";
			surname = "surname";
			exp = "exp";
			nation = "nation";
			id = "id";
			dob = "dob";
			ht = "ht";
			wt = "wt";
		}
		
	}

	@Override
	public void generateToFile(File path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitl() {
		// TODO Auto-generated method stub
		return "name\nsurname\nexp\nnation\nid\ndob\nht\nwt";
	}
}
