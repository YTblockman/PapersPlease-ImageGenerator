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

public class GUI {

	private JFrame frmPaperspleaseDocumentGenerator;

	/**
	 * Launch the application.
	 */
	public static void INIT() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmPaperspleaseDocumentGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
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
		frmPaperspleaseDocumentGenerator.setTitle("PapersPlease Document Generator");
		frmPaperspleaseDocumentGenerator.setBounds(100, 100, 675, 443);
		frmPaperspleaseDocumentGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPaperspleaseDocumentGenerator.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmPaperspleaseDocumentGenerator.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to PaperPlease - DocumentsGenerator");
		lblNewLabel.setBounds(10, 11, 360, 14);
		panel.add(lblNewLabel);
		

		JLabel lblNewLabel_1 = new JLabel("Select Generator Type");
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
		
		JLabel lblNewLabel_3 = new JLabel(new ImageIcon(ImageManager.ENTRY_PERMIT_IMAGE));
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Document Texture Preview:");
		lblNewLabel_2.setBounds(232, 51, 191, 14);
		panel.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Generators.values()));
		comboBox.setBounds(10, 67, 212, 22);
		

		
		panel.add(comboBox);
		
		JLabel lblNewLabel_1_1 = new JLabel("Arguments use '\\n' to separate");
		lblNewLabel_1_1.setBounds(10, 100, 173, 14);
		panel.add(lblNewLabel_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 114, 212, 247);
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
				Generators s = (Generators)comboBox.getSelectedItem();
				s.host.setData(split);
				BufferedImage image = s.host.generate(true);
				lblNewLabel_3.setIcon(new ImageIcon(image));
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				String[] split = editorPane.getText().split("\n");
				Generators s = (Generators)comboBox.getSelectedItem();
				s.host.setData(split);
				BufferedImage image = s.host.generate(true);
				lblNewLabel_3.setIcon(new ImageIcon(image));
				
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Generators s = (Generators)comboBox.getSelectedItem();
				editorPane.setText(s.host.getTitl());
				
				String[] split = editorPane.getText().split("\n");
				s.host.setData(split);
				BufferedImage image = s.host.generate(true);
				lblNewLabel_3.setIcon(new ImageIcon(image));
			}
		});
		
		Generators s = (Generators)comboBox.getSelectedItem();
		lblNewLabel_3.setIcon(new ImageIcon(s.img));
		editorPane.setText(s.host.getTitl());
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(Model.values()));
		
		comboBox_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Model s = (Model)comboBox_1.getSelectedItem();
				if (s.equals(Model.PRINT_MODEL)) {
					JOptionPane.showInternalMessageDialog(new JPanel(), "Selection doesn't have print type!", "Error!", JOptionPane.ERROR_MESSAGE);	
				}else {
					
				}
				
				comboBox_1.setSelectedItem(Model.CLASSIC_MODEL);
				
			}
		});
		
	
		panel_2.add(comboBox_1, BorderLayout.SOUTH);
		
		String[] split = editorPane.getText().split("\n");
		s.host.setData(split);
		BufferedImage image = s.host.generate(true);
		
		lblNewLabel_3.setIcon(new ImageIcon(image));
		
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
		
		JButton btnNewButton_1 = new JButton("Passport");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new PASSPORT();
				frmPaperspleaseDocumentGenerator.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(560, 36, 89, 23);
		panel.add(btnNewButton_1);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String[] split = editorPane.getText().split("\n");
				Generators s = (Generators)comboBox.getSelectedItem();
				s.host.setData(split);
				BufferedImage image = s.host.generate(true);
				File outputfile = new File(new Random().nextLong() + ".png");
				try {
					System.out.println(outputfile.getPath());
					ImageIO.write(image, "png", outputfile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] split = editorPane.getText().split("\n");
				Generators s = (Generators)comboBox.getSelectedItem();
				s.host.setData(split);
				s.host.generate(false);
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
		
	}
}
