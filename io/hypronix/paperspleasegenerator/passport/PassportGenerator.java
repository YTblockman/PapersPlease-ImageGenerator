package io.hypronix.paperspleasegenerator.passport;

import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class PassportGenerator {

	public abstract String getRequiredFields();
	public abstract BufferedImage generateImage(boolean nogui, String[] data) throws FontFormatException, IOException;
	
}
