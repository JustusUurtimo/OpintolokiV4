package javaOpintoloki;


/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille
 * @author Olli Mehtonen ja Justus Uurtimo
 * @version 3.5.2019
 */
public class SailoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	
    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa käytettävä viesti
     * käytettävä viesti
     * @param viesti Poikkeuksen viesti
     */
	public SailoException(String viesti) {
		super(viesti);
	}
}
