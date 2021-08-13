package io.hypronix.paperspleasegenerator;

import java.awt.image.BufferedImage;

import io.hypronix.paperspleasegenerator.generators.*;

public enum Generators {

	ENTRY_PERMIT_GENERATOR("entry-permit", new EntryPermitGenerator(), ImageManager.ENTRY_PERMIT_IMAGE),
	ID_CARD_GENERATOR("id-card", new IdCardGenerator(), ImageManager.ID_CARD_IMAGE),
	ID_SUPPLEMENT_GENERATOR("id-suplement", new IdSupplementGenerator(), ImageManager.ID_SUPLEMENT_IMAGE),
	THE_TRUTH_OF_ARSTOTZKA_GENERATOR("truth-paper", new TheTruthOfArstotzkaGenerator(), ImageManager.THE_TRUTH_OF_ARSTOTZKA_IMAGE),
	VAX_GENERATOR("vax", new VaxGenerator(), ImageManager.VAX_IMAGE),
	WORK_PASS_GENERATOR("work-pass", new WorkPassGenerator(), ImageManager.WORK_PASS_IMAGE),
	PRESS_PASS_GENERATOR("press-pass", new PressPassGenerator(), ImageManager.PRESS_PASS_IMAGE),
	ENTRY_TICKET_GENERATOR("entry-ticket", new EntryTicketGenerator(), ImageManager.ENTRY_TICKET_IMAGE),
	ACCESS_PERMIT_GENERATOR("access-permit", new AccessPermitGenerator(), ImageManager.ACCESS_PERMIT_IMAGE),
	ASYLUM_PERMIT_GENERATOR("asylum-permit", new AsylumPermitGenerator(), ImageManager.ASYLUM_PERMIT_IMAGE);
	
	String id;
	Generator host;
	BufferedImage img;
	Generators(String id, Generator gen, BufferedImage img) {
		this.id = id;
		this.host = gen;
		this.img = img;
	}
	
	
	
	public static Generator byID(String id) {
		for (Generators gen : Generators.values()) {
			if (gen.id.equals(id)) {
				return gen.host;
			}
		}
		
		return null;
	}
	
}
