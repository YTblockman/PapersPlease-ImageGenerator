package io.hypronix.paperspleasegenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import io.hypronix.paperspleasegenerator.passport.PassportCountry;
import io.hypronix.paperspleasegenerator.passport.PassportGenerator;
import io.hypronix.paperspleasegenerator.passport.PassportStamp;

public class PASSPORT {

	private JFrame frmPaperspleaseDocumentGenerator;

	/**
	 * Create the application.
	 */
	public PASSPORT() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private final Color booth = new Color(44, 33, 32);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frmPaperspleaseDocumentGenerator = new JFrame();
		frmPaperspleaseDocumentGenerator.setResizable(false);
		frmPaperspleaseDocumentGenerator.setVisible(true);
		frmPaperspleaseDocumentGenerator.setTitle("Passport");
		frmPaperspleaseDocumentGenerator.setBounds(100, 100, 675, 443);
		frmPaperspleaseDocumentGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPaperspleaseDocumentGenerator.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmPaperspleaseDocumentGenerator.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Passport Generator");
		lblNewLabel.setBounds(10, 11, 360, 14);
		panel.add(lblNewLabel);
		

		JLabel lblNewLabel_1 = new JLabel("Passport Type:");
		lblNewLabel_1.setBounds(10, 51, 173, 14);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(232, 51, 417, 344);
		JScrollPane pane = new JScrollPane(panel_1);
		pane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pane.setBounds(232, 67, 417, 328);
		pane.setBackground(booth);
		panel_1.setBackground(booth);
		panel.add(pane);
		
		JLabel lblNewLabel_3 = new JLabel(new ImageIcon(ImageManager.ARSTOTZKA));
		lblNewLabel_3.setText("");
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Document Texture Preview:");
		lblNewLabel_2.setBounds(232, 51, 191, 14);
		panel.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(PassportCountry.values()));
		comboBox.setBounds(10, 67, 212, 22);
		
		panel.add(comboBox);
		
		JLabel lblNewLabel_1_1 = new JLabel("Arguments use '\\n' to separate");
		lblNewLabel_1_1.setBounds(10, 182, 173, 14);
		panel.add(lblNewLabel_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 198, 212, 163);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JEditorPane editorPane = new JEditorPane();
		panel_2.add(editorPane, BorderLayout.CENTER);
		
		editorPane.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String[] split = editorPane.getText().split("\n");
				PassportCountry s = (PassportCountry)comboBox.getSelectedItem();
				
				BufferedImage image;
				try {
					image = s.generator.generateImage(true, split);
					lblNewLabel_3.setIcon(new ImageIcon(image));
				} catch (FontFormatException | IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				String[] split = editorPane.getText().split("\n");
				PassportCountry s = (PassportCountry)comboBox.getSelectedItem();
				
				BufferedImage image;
				try {
					image = s.generator.generateImage(true, split);
					lblNewLabel_3.setIcon(new ImageIcon(image));
				} catch (FontFormatException | IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] split = editorPane.getText().split("\n");
				PassportCountry s = (PassportCountry)comboBox.getSelectedItem();
				
				BufferedImage image;
				try {
					image = s.generator.generateImage(true, split);
					lblNewLabel_3.setIcon(new ImageIcon(image));
					editorPane.setText(s.generator.getRequiredFields());
				} catch (FontFormatException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("By HypronixDev");
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setBounds(10, 26, 110, 14);
		lblNewLabel_4.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("https://github.com/YTblockman"));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Link to game website ->");
		lblNewLabel_4_1.setForeground(Color.BLUE);
		lblNewLabel_4_1.setBounds(521, 0, 157, 14);
		lblNewLabel_4_1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("https:/papersplea.se/"));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		panel.add(lblNewLabel_4_1);
		
		JButton btnNewButton = new JButton("New");
		btnNewButton.setBounds(10, 372, 71, 23);
		panel.add(btnNewButton);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(91, 372, 71, 23);
		panel.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String[] split = editorPane.getText().split("\n");
				PassportCountry s = (PassportCountry)comboBox.getSelectedItem();
				
				
				try {
					BufferedImage image = s.generator.generateImage(true, split);
					File outputfile = new File(new Random().nextLong() + ".png");
					System.out.println(outputfile.getPath());
					ImageIO.write(image, "png", outputfile);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] split = editorPane.getText().split("\n");
				PassportCountry s = (PassportCountry)comboBox.getSelectedItem();
				
				try {
					BufferedImage image = s.generator.generateImage(false, split);
					File outputfile = new File(new Random().nextLong() + ".png");
					System.out.println(outputfile.getPath());
					ImageIO.write(image, "png", outputfile);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		InputStream is = Main.class.getResourceAsStream("titlfont.ttf");
	    Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    font = font.deriveFont(font.getSize() * 20f);
	    String[] split = editorPane.getText().split("\n");
		PassportCountry s = (PassportCountry)comboBox.getSelectedItem();
		
		BufferedImage image;
		try {
			image = s.generator.generateImage(true, split);
			lblNewLabel_3.setIcon(new ImageIcon(image));
			editorPane.setText(s.generator.getRequiredFields());
			
			JLabel lblNewLabel_5 = new JLabel("Passport images will be added\r\n");
			lblNewLabel_5.setBounds(10, 106, 202, 14);
			panel.add(lblNewLabel_5);
			
			JLabel lblNewLabel_5_1 = new JLabel("next update...");
			lblNewLabel_5_1.setBounds(10, 120, 202, 14);
			panel.add(lblNewLabel_5_1);
			
			JButton btnNewButton_1_1 = new JButton("Documents");
			btnNewButton_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frmPaperspleaseDocumentGenerator.setVisible(false);
					GUI.INIT();
				}
			});
			btnNewButton_1_1.setBounds(558, 42, 89, 23);
			panel.add(btnNewButton_1_1);
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
		}
	}
}
