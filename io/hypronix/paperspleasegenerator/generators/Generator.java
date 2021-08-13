package io.hypronix.paperspleasegenerator.generators;

import java.awt.image.BufferedImage;
import java.io.File;

public abstract class Generator {

	public abstract void setData(Object[] data);
	public abstract BufferedImage generate(boolean nogui);
	public abstract void generateToFile(File path);
	public abstract String getTitl();
	
}
