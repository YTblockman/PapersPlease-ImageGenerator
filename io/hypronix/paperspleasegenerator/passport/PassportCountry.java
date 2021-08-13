package io.hypronix.paperspleasegenerator.passport;

import java.awt.image.BufferedImage;

import io.hypronix.paperspleasegenerator.ImageManager;
import io.hypronix.paperspleasegenerator.passport.generators.AntegriaPassportGenerator;
import io.hypronix.paperspleasegenerator.passport.generators.ArstotzkaPassportGenerator;
import io.hypronix.paperspleasegenerator.passport.generators.CobrastanPassportGenerator;
import io.hypronix.paperspleasegenerator.passport.generators.ImporPassportGenerator;
import io.hypronix.paperspleasegenerator.passport.generators.KolechiaPassportGenerator;
import io.hypronix.paperspleasegenerator.passport.generators.ObristanPassportGenerator;
import io.hypronix.paperspleasegenerator.passport.generators.RepubliaPassportGenerator;
import io.hypronix.paperspleasegenerator.passport.generators.UnitedFederationPassportGenerator;

public enum PassportCountry {


	ANTEGRIA(ImageManager.ANTEGRIA, ImageManager.ANTEGRIA_TITLE, "Antegria", new AntegriaPassportGenerator()),
	ARSTOTZKA(ImageManager.ARSTOTZKA, ImageManager.ARSTOTZKA_TITLE, "Arstotzka", new ArstotzkaPassportGenerator()),
	IMPOR(ImageManager.IMPOR, ImageManager.IMPOR_TITLE, "Impor", new ImporPassportGenerator()),
	KOLECHIA(ImageManager.KOLECHIA, ImageManager.KOLECHIA_TITLE, "Kolechia", new KolechiaPassportGenerator()),
	OBRISTAN(ImageManager.OBRISTAN, ImageManager.OBRISTAN_TITLE, "Obristan", new ObristanPassportGenerator()),
	REPUBLIA(ImageManager.REPUBLIA, ImageManager.REPUBLIA_TITLE, "Republia", new RepubliaPassportGenerator()),
	UNITED_FED(ImageManager.UNITED_FED, ImageManager.UNITED_FED_TITLE, "United Federation", new UnitedFederationPassportGenerator()),
	COBRASTAN(ImageManager.COBRASTAN, ImageManager.COBRASTAN_TITLE, "Cobrastan", new CobrastanPassportGenerator());
	
	public BufferedImage image;
	public BufferedImage titleImage;
	public String title;
	public PassportGenerator generator;
	
	PassportCountry(BufferedImage image, BufferedImage titleImage, String title, PassportGenerator generator) {
		this.image = image;
		this.titleImage = titleImage;
		this.title = title;
		this.generator = generator;
	}
}
