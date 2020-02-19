package javaOpintoloki;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

import fi.jyu.mit.ohj2.Mjonot;
import fxopintoloki.Tietue;

/**
 * Kurssin suoritus joka osaa mm. itse huolehtia Id:eest��n
 * @author Olli Mehtonen ja Justus Uurtimo
 * @version 3.5.2019
 */
public class Suoritus implements Cloneable, Tietue{

	private int oid;
	private int arvosana = 0;
	private String pvm = "";
	
	
	/**
     * Alustetaan suoritus
     */
	public Suoritus() {
		
	}
	
	
	/**
     * Alustetaan suoritus
     * @param id suorituksen id
     */
	public Suoritus(int id) {
		oid = id;
	}
	
	
	/**
	 * Asetetaan id
	 * @param oid id joka asetetaan
	 */
	public void setOid(int oid) {
		this.oid = oid;
	}
	
	
	/**
     * Getataan kenttien määrä
     * @return käyttäjän syötettävien kenttien lukumäärä
     */
	@Override
    public int getKenttia() {
		return 3;
	}
	
	
	/**
     * Monesko on ensimmäinen kenttä
     * @return ensimmäinen käyttäjän syötettävän kentän indeksi
     */
	@Override
    public int ekaKentta() {
		return 1;
	}
	

    /**
     * @param k minkä kentän kysymys halutaan
     * @return valitun kentän kysymysteksti
     */
	@Override
    public String getKysymys(int k) {
		switch (k) {
		case 0:
			return "id";
		case 1:
			return "Arvosana";
		case 2:
			return "P�iv�m��r�";
		default:
			return "???";
		}
    }
	
	
    /**
     * @param k Minkä kentän sisältö halutaan
     * @return valitun kentän sisältö
     * * @example
     * <pre name="test">
     *   Suoritus har = new Suoritus();
     *   har.parse("1,2,3");
     *   har.anna(0) === "1";
     *   har.anna(1) === "2";
     *   har.anna(2) === "3";
     * </pre>
     */  
	@Override
    public String anna(int k) {
		switch (k) {
		case 0:
			return "" + this.oid;
		case 1:
			return "" + this.arvosana;
		case 2:
			return this.pvm;
		default:
			return "???";
		}
	}
	
	
    /**
     * Asetetaan valitun kentän sisältö.  Mikäli asettaminen onnistuu,
     * palautetaan null, muutoin virheteksti.
     * @param k minkä kentän sisältö asetetaan
     * @param s asetettava sisältö merkkijonona  
	 */
	@Override
    public String aseta(int k, String s) {
		String st = s.trim();
		StringBuffer sb = new StringBuffer(st);
		switch (k) {
		case 0:
			setOid(Mjonot.erota(sb, '$', getId()));
			return null;
		case 1:
			this.arvosana = Mjonot.erota(sb, '$', this.arvosana);
			return null;
		case 2:
			this.pvm = st;
			return null;
			
		default:
			return "V��r�n kent�n indeksi";
		}
	}
	
    /**
     * Tehdään identtinen klooni 
     * @return Object kloonattu objekti
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Suoritus har = new Suoritus();
     *   har.parse("1,2,3");
     *   Suoritus kopio = har.clone();
     *   kopio.toString() === har.toString();
     *   har.parse("1,3,2");
     *   kopio.toString().equals(har.toString()) === false;
     * </pre>
     */
	@Override
    public Suoritus clone() throws CloneNotSupportedException { 
	        return (Suoritus)super.clone();
	}
	  
	
	/**
     * Luodaan random suoritus
     * @param id suorituksen id
     */
	public void annaVakio(int id) {
		Random randomi = new Random();
		
		this.oid = id;
		this.arvosana = randomi.nextInt(5);
		this.pvm = "1.1.2019";
	}
	
	
	
	/**
     * Palautetaan suorituksen oma oid
     * @return suorituksen oid
     */
	public int getId() {
		return this.oid;
	}
	

    /**
     * Tulostetaan suorituksen tiedot
     * @param out tietovirta johon tulostetaan
     */	
	public void tulosta(PrintStream out) {
		out.println("Arvosana: " + arvosana);
		out.println("Suoritus p�iv�m��r�: " + pvm + "\n");
	}
	

    /**
     * Tulostetaan suorituksen tiedot
     * @param os tietovirta johon tulostetaan
     */
	public void tulosta(OutputStream os) {
		tulosta(new PrintStream(os));
	}
	
	
    /**
     * Palauttaa suorituksen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return suoritus pilkulla eroteltuna merkkijonona 
     *  <pre name="test">
     *   Suoritus harrastus = new Suoritus();
     *   harrastus.parse("   1   ,  2  ,   3");
     *   harrastus.toString() === "1,2,3";
     * </pre>
     */ 
	@Override
    public String toString() {
	    return "" + oid + "," + 
	            arvosana + "," +
	            pvm;
	}
	
	
    /**
     * Selvitää suorituksen tiedot "," erotellusta merkkijonosta.
     * @param rivi josta suorituksen tiedot otetaan
     */
	public void parse(String rivi) {
		StringBuffer sb = new StringBuffer(rivi);
		setOid(Mjonot.erota(sb, ',', getId()));
		arvosana = Mjonot.erota(sb, ',', arvosana);
		pvm = Mjonot.erota(sb, ',', pvm);	
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if ( obj == null ) return false;
		return this.toString().equals(obj.toString());
	}
	    

	@Override
	public int hashCode() {
		return this.oid;
	}
	

	/**
     * Testiohjelma Harrastukselle.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Suoritus suoritus = new Suoritus(1);
        suoritus.annaVakio(1);
        suoritus.tulosta(System.out);
    }
}
