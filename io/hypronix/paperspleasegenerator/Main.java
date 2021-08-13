package io.hypronix.paperspleasegenerator;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import io.hypronix.paperspleasegenerator.generators.Generator;

public class Main {

	public static void main(String[] args) {
		
		final boolean lookAndFeel = false;
		
		try {
			
			if (lookAndFeel) 
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());	
			}else 
			{
				//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");	
				for (LookAndFeelInfo infs : UIManager.getInstalledLookAndFeels()) {
					if (infs.getClassName().toUpperCase().contains("WindowsClassic".toUpperCase())) {
						UIManager.setLookAndFeel(infs.getClassName());	
					}
				}
				//UIManager.setLookAndFeel("javax.swing.plaf.windows.WindowsClassicLookAndFeel");	
			}
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e1) { e1.printStackTrace(); }
			e.printStackTrace();
		}	
		
		if (args.length == 0) {
			System.out.println("NO ARGUMENTS PASSED IN! TRY: (CTRL + C) IS UNIVERSAL TERMINATION SHORTCUT");
			System.out.println("On windows is <jar init> 'java -jar'");
			System.out.println("<jar init> \"print\" to see all generators");
			System.out.println("<jar init> \"vax\" \"name\" \"id\" \"vax1;vax2;etc\" \"date1;date2;etc\" for vax gen!");
			System.out.println("initializing gui version /--/");
			
			GUI.INIT();
			
			return;
		}
		
		if (args[0].equals("print")) {
			for (Generators f : Generators.values()) {
				System.out.println("\"" + f.id + "\" =  " + f.name());
			}
			
			return;
		}
		
		String id = args[0];
		Generator generator = Generators.byID(id);
		
		List<String> list = new ArrayList<String>();
	
		int indent = 0;
		
		for (String s : args) {
			indent++;
			if (!(indent > 1)) continue;
			
			list.add(s);
		}
		
		String[] stringArray = list.toArray(new String[0]);
		generator.setData(stringArray);
		generator.generate(false);
		
	
	}

}
