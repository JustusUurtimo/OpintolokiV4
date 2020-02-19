package javaOpintoloki;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;
import fxopintoloki.Tietue;

/**
 * Opintolokin kurssi joka osaa mm. itse huolehtia Id:eest��n
 * @author Olli Mehtonen ja Justus Uurtimo
 * @version 3.5.2019
 */
public class Kurssi implements Cloneable, Tietue{

	private int oid;
	private int opintoPisteet;
	private String sivuPaaAine;
	private String lisaTiedot = "";
	private String opintotaso;
	private String tunnus = "";
	private static int seuraava = 0;
	
	
	/**
     * Alustetaan tyhjä kurssi
     */
	public Kurssi() {
		
	}
	
	
	/**
	 * Palauttaa kurssin kenttien lukumäärän
	 * @return kenttien lukumäärä
	 */
	@Override
    public int getKenttia() {
		return 6;
	}
	
	
	/**
	 * Eka kenttä joka on mielekäs kysyttäväksi
	 * @return eknn kentän indeksi
	 */
	@Override
    public int ekaKentta() {
		return 1;
	}
	
	
	/**
	 * Antaa k:n kentän sisällön merkkijonona
	 * @param k monenenko kentän sisältö palautetaan
	 * @return kentän sisältö merkkijonona
	 */
	@Override
    public String anna(int k) {
	       switch ( k ) {
	       case 0: return "" + oid;
	       case 1: return "" + tunnus;
	       case 2: return "" + opintoPisteet;
	       case 3: return "" + opintotaso;
	       case 4: return "" + sivuPaaAine;
	       case 5: return "" + lisaTiedot;
	       
	       default: return "Ääliö";
	       }
	}
	
	
	/**
     * Asetetaan jotkin arvot kurssille
     */
	public void annaVakio() {
		this.opintoPisteet = 2;
		
		this.sivuPaaAine = "sivu";
		this.lisaTiedot = "oli kivaa";
				
		this.tunnus = "TIEP111";
		
		this.opintotaso = ";D";
	}
	
	
	/**
	 * Asettaa k:n kentän arvoksi parametrina tuodun merkkijonon arvon
	 * @param k kuinka monennen kentän arvo asetetaan
	 * @param jono jonoa joka asetetaan kentän arvoksi
	 * @return null jos asettaminen onnistuu, muuten vastaava virheilmoitus.
	 */
	@Override
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        StringBuffer sb = new StringBuffer(tjono);
        switch ( k ) {
        case 0:
            setOid(Mjonot.erota(sb, ' ', getId())); //� mik� t�m�
            return null;
        case 1:
            tunnus = tjono;
            return null;
        case 2:
            this.opintoPisteet = Integer.parseInt(tjono);
            return null;
        case 3:
            this.opintotaso = tjono;
            return null;
        case 4:
            this.sivuPaaAine = tjono;
            return null;
        case 5:
            this.lisaTiedot = tjono;
            return null;
        default:
        	return "heipahei";
        }
	}
	
	
	/**
	 * Palauttaa k:tta kurssia kenttää vastaavan kysymyksen
     * @param k kuinka monennen kentän kysymys palautetaan (0-alkuinen)
	 * @return k:netta kenttää vastaava kysymys
	 */
	@Override
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "id";
        case 1: return "Tunnus";
        case 2: return "Opintopisteet";
        case 3: return "Opintotaso";
        case 4: return "Sivu-/P��aine";
        case 5: return "Lis�tiedot";
        default: return "Ääliö :D";
        }
    }
	
	
	/**
     * Kopioidaan kurssi muokkausta varten
     */
	@Override
    public Kurssi clone() throws CloneNotSupportedException {
		Kurssi uusi;
		uusi = (Kurssi) super.clone();
		return uusi;
	}
	
	
	/**
     * Asetetaan id kurssille ja siirryt��n id eteenp�in
     * @return kurssin id
     */
	public int setOid() {
		this.oid = seuraava;
		seuraava++;
		return this.oid;
	}
	
	
	/**
     * Asetetaan id kurssille ja siirryt��n id eteenp�in
	 * @param oid id joka asetetaan
     */
	public void setOid(int oid) {
		this.oid = oid;
		if(this.oid >= seuraava) {
			seuraava = this.oid + 1;
		}
	}
	

	/**
	 * Palauttaa kurssin id:een
	 * @return kurssin id
	 */
	public int getId() {
		return this.oid;
	}
	
	
	/**
     * Asetetaan tunnus (nimi) kurssille
     * @param tunnus kurssin tunnus (esim tiep111) 
     */
	public void setTunnus(String tunnus) {
		this.tunnus = tunnus;
	}
	

    /**
     * Getataan kurssin tunnus (nimi)
     * @return Kurssin tunnus (nimi)
     */
	public String getTunnus() {
		return this.tunnus;
	}
	
	
	/**
     * Asetetaan opintopisteet kursille
     * @param pisteet opintopisteet jotka kurssille annetaan 
     */
	public void setOpintoPisteet(int pisteet) {
		this.opintoPisteet = pisteet;
	}
	
	
	 /**
     * Palauttaa kurssin opintopisteiden määrän
     * @return opintopisteiden määrä
     */
	public int getOpintoPisteet() {
		return this.opintoPisteet;
	}
	
	
	/**
     * Asetetaan onko kurssi sivu- vai pääaine
     * @param aine kurssin aine syvyys 
     */
	public void setSivuPaaAine(String aine) {
		this.sivuPaaAine = aine;
	}
	
	
	/**
     * Asetetaan kurssin lis�tiedot
     * @param tiedot mahdolliset lis�tiedot kurssille 
     */
	public void setLisaTiedot(String tiedot) {
		this.lisaTiedot += tiedot + "\n";
	}
	
	
	/**
     * Asetetaan opintotaso kursille
     * @param taso kurssin opintotaso 
     */
	public void setOpintotaso(String taso) {
		this.opintotaso = taso;
	}
	
	
	 /**
     * Tulostetaan kurssin tiedot
     * @param out tietovirta johon tulostetaan
     */
	public void tulosta(PrintStream out) {
		out.println("id: " + oid);
		out.println("Kurssin nimi: " + tunnus);
		out.println("Opintopisteet: " + opintoPisteet);
		out.println("Opintotaso: " + opintotaso);
		out.println("P��/Sivuaine: " + sivuPaaAine);
		out.println("Lis�tiedot: " + lisaTiedot + "\n");
	}
	
	
	 /**
     * Tulostetaan kurssin tiedot
     * @param os tietovirta johon tulostetaan
     */
	public void tulosta(OutputStream os) {
		tulosta(new PrintStream(os));
	}
	
	
	/**
	 * Palauttaa kurssin tiedot merkkijonona jonka voi tallentaa tiedostoon.
	 * @return Kurssi pilkulla eroteltuna merkkijonona 
	 * @example
     * <pre name="test">
     *   Kurssi kurssi = new Kurssi();
     *   kurssi.parse("   1   ,   2   ,  3   ,  4   ,   5");
     *   kurssi.toString().startsWith("1,2,3,4,5") === true;
     * </pre>  
	 */ 
	@Override
	public String toString() {
	    return "" + oid + "," + 
	            tunnus + "," +
	            opintoPisteet + "," +
	            opintotaso + "," + 
	            sivuPaaAine + "," +
	            lisaTiedot;
	}
	
	
	/**
     * Selvittää kurssin tiedot "," eroteltuna
	 * @param rivi Rivi, jota parsetaan
     */
	public void parse(String rivi) {
		StringBuffer sb = new StringBuffer(rivi);
		setOid(Mjonot.erota(sb, ',', getId()));
		tunnus = Mjonot.erota(sb, ',', tunnus);
		opintoPisteet = Mjonot.erota(sb, ',', opintoPisteet);
		opintotaso = Mjonot.erota(sb, ',', opintotaso);
		sivuPaaAine = Mjonot.erota(sb, ',', sivuPaaAine);
		lisaTiedot = Mjonot.erota(sb, ',', lisaTiedot);
	}
	
	
	/**
	 * Testiohjelma kurssille
	 * @param args
	 * 
	 * <pre name="test">
	 *  Kurssi k1 = new Kurssi();
     *  k1.annaVakio();
     *  k1.getTunnus() === "TIEP111";
	 * </pre>
	 */
	public static void main(String args[]) {
	    Kurssi k1 = new Kurssi();
	    k1.annaVakio();
	    k1.tulosta(System.out);
	}
}