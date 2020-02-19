package javaOpintoloki;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Opintoloki luokka, huolehtii kurssien ja suorituksien kommunikoinnista, yhdistää ne kokonaisuudeksi
 * @author Olli Mehtonen ja Justus Uurtimo
 * @version 3.5.2019
 * 
 * * Testien alustus
 * @example
 * <pre name="testJAVA">
 * #import javaOpintoloki.SailoException;
 *  private Opintoloki opintoloki;
 *  private Kurssi aku1;
 *  private Kurssi aku2;
 *  private int jid1;
 *  private int jid2;
 *  private Suoritus pitsi21;
 *  private Suoritus pitsi11;
 *  private Suoritus pitsi22; 
 *  private Suoritus pitsi12; 
 *  private Suoritus pitsi23;
 *  
 *  @SuppressWarnings("javadoc")
 *  public void alustaOpintoloki() {
 *    opintoloki = new Opintoloki();
 *    aku1 = new Kurssi(); aku1.annaVakio(); aku1.setOid();
 *    aku2 = new Kurssi(); aku2.annaVakio(); aku2.setOid();
 *    jid1 = aku1.getId();
 *    jid2 = aku2.getId();
 *    pitsi21 = new Suoritus(jid2); pitsi21.annaVakio(jid2);
 *    pitsi11 = new Suoritus(jid1); pitsi11.annaVakio(jid1);
 *    pitsi22 = new Suoritus(jid2); pitsi22.annaVakio(jid2); 
 *    pitsi12 = new Suoritus(jid1); pitsi12.annaVakio(jid1); 
 *    pitsi23 = new Suoritus(jid2); pitsi23.annaVakio(jid2);
 *    try {
 *    opintoloki.lisaa(aku1);
 *    opintoloki.lisaa(aku2);
 *    opintoloki.lisaa(pitsi21);
 *    opintoloki.lisaa(pitsi11);
 *    opintoloki.lisaa(pitsi22);
 *    opintoloki.lisaa(pitsi12);
 *    opintoloki.lisaa(pitsi23);
 *    } catch ( Exception e) {
 *       System.err.println(e.getMessage());
 *    }
 *  }
 * </pre>
 */

public class Opintoloki {
	private  Kurssit kurssit = new Kurssit();
	private  Suoritukset suoritukset = new Suoritukset();
	
	
	/**
     * Palautaa kurssien lukumäärän
     * @return kurssien määrän
     */
	public int KurssienMaara() {
		return kurssit.getLkm();
	}
	
	
	/**
	 * Getataan kunnon kurssit
	 * @return kunnon kurssit
	 */
	public HashMap<Integer, Kurssi> getKunnonKurssit() {
		return kurssit.getKunnonKurssit();
	}
	
	
	/**
     * Korvaa jäsenen tietorakenteessa.  Ottaa jäsenen omistukseensa. 
     * Etsitään samalla tunnusnumerolla oleva jäsen.  Jos ei löydy, 
     * niin lisätään uutena jäsenenä. 	 
     * @param kurssi kurssi jota muokataan tai lisätään
	 * @throws SailoException jos ei tilaa
	 * 
	 * @example
     * <pre name="test">
     * #THROWS SailoException  
     *  alustaOpintoloki();
     *  opintoloki.getKunnonKurssit().size() === 2;
     *  opintoloki.korvaaTaiLisaa(aku1);
     *  opintoloki.getKunnonKurssit().size() === 2;
     * </pre>
	 */
	public void korvaaTaiLisaa(Kurssi kurssi) throws SailoException {
		kurssit.korvaaTaiLisaa(kurssi);
	}
	
	
	/**
     * Lisää uuden j�senen tietorakenteeseen. Ottaa jäsenen omistukseensa.
     * @param kurssi lisätäävän jäsenen viite. Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo täynnä
     */
	public void lisaa(Kurssi kurssi) throws SailoException {
		kurssit.lisaa(kurssi);
	}
	
	
	/**
     * Lisätään uusi suoritus kurssille
     * Lisätään kursseille opintopisteet jos kurssi on hyv�ksytty
     * @param suoritus lis�tt�v� suoritus 
     */
	public void lisaa(Suoritus suoritus) {
		suoritukset.lisaaSuoritus(suoritus);
		
		if(Integer.parseInt(suoritus.anna(1)) > 0) {
			int pisteet = this.haeKurssi(suoritus.getId()).getOpintoPisteet();
			kurssit.setOpintopisteet(pisteet);
		}
		
	}
	
	
	/**
     * Haetaan id:n kurssi
     * @param id monesko kurssi haetaan 
     * @return viite id:een kurssiin
     * @throws IndexOutOfBoundsException jos id v��rin
     */
	public Kurssi haeKurssi(int id) throws IndexOutOfBoundsException {
		return kurssit.hae(id);
	}
	
	
	/** 
	 * haetaan hylättyjen määrä
	 * @return hylätyt
	 */
	public int getHylatyt() {
		return suoritukset.getHylatyt();
	}
	
	
	/**
	 * haetaan hyväksytyt
	 * @return hyväksytyt suoritukset
	 */
	public int getHyvaksytyt() {
		return suoritukset.getHyvaksytyt();
	}
	
	
	/**
	 * Getataan suoritettujen kurssien opintopisteet
	 * @return suoritettujen kurssien opintopisteet
	 */
	public int getOpintopisteet() {
	    int pisteet = 0;
		for(Suoritus suoritus : this.suoritukset) {
		    if (Integer.parseInt(suoritus.anna(1)) > 0) {
		       pisteet += kurssit.hae(suoritus.getId()).getOpintoPisteet();
		    }
		}
        return pisteet;
	}
	
	
	/**
     * Haetaan kurssin suoritus/suoritukset
     * @param kurssi minkä suoritukset haetaan 
     * @return suoritus/suortitukset
     */
	public List<Suoritus> haeSuoritukset(Kurssi kurssi) {
		return suoritukset.getSuoritukset(kurssi.getId());
	}
	
	
	/**
	 * Asettaa tiedostojen peruskurssit
	 * @param nimi uusi nimi
	 */
	public void setTiedosto(String nimi) {
		File dir = new File(nimi);
		dir.mkdir();
		String hakemistonNimi = "";
		if(!nimi.isEmpty()) {
			hakemistonNimi = nimi + "/";
		}
		kurssit.setTiedostonPerusNimi(hakemistonNimi + "kurssit");
		suoritukset.setTiedostonPerusNimi(hakemistonNimi + "suoritukset");
		
	}
	
	
	/**
     * Lukee opintolokin tiedot tiedostosta
     * @param nimi jota k�yte��n lukemisessa
     * @throws SailoException jos lukeminen ep�onnistuu
     *    * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.*;
     * #import java.util.*;
     * 
     *   
     *  String hakemisto = "testikelmit";
     *  File dir = new File(hakemisto);
     *  File ftied  = new File(hakemisto+"/kurssit.dat");
     *  File fhtied = new File(hakemisto+"/suoritukset.dat");
     *  dir.mkdir();  
     *  ftied.delete();
     *  fhtied.delete();
     *  opintoloki = new Opintoloki(); // tiedostoja ei ole, tulee poikkeus 
     *  opintoloki.lueTiedostosta(hakemisto); #THROWS SailoException
     *  alustaOpintoloki();
     *  opintoloki.setTiedosto(hakemisto); // nimi annettava koska uusi poisti sen
     *  opintoloki.tallenna(); 
     *  opintoloki = new Opintoloki();
     *  opintoloki.lueTiedostosta(hakemisto);
     *  List<Suoritus> loytyneet = opintoloki.haeSuoritukset(aku1);
     *  Iterator<Suoritus> ih = loytyneet.iterator();
     *  ih.next() === pitsi11;
     *  ih.next() === pitsi12;
     *  ih.hasNext() === false;
     *  loytyneet = opintoloki.haeSuoritukset(aku2);
     *  ih = loytyneet.iterator();
     *  ih.next() === pitsi21;
     *  ih.next() === pitsi22;
     *  ih.next() === pitsi23;
     *  ih.hasNext() === false;
     *  opintoloki.lisaa(aku2);
     *  opintoloki.lisaa(pitsi23);
     *  opintoloki.tallenna(); // tekee molemmista .bak
     *  ftied.delete()  === true;
     *  fhtied.delete() === true;
     *  File fbak = new File(hakemisto+"/kurssit.bak");
     *  File fhbak = new File(hakemisto+"/suoritukset.bak");
     *  fbak.delete() === true;
     *  fhbak.delete() === true;
     *  dir.delete() === true;
     * </pre>
     */
	public void lueTiedostosta(String nimi) throws SailoException {
		kurssit = new Kurssit();
		suoritukset = new Suoritukset();
		
		setTiedosto(nimi);
		kurssit.lueTiedostosta();
		suoritukset.lueTiedostosta();
	}
	
	
	 /**
     * Tallentaa opintolokin tiedot tiedostoon
     * @throws SailoException jos tallentamisessa ongelmia
     */
	public void tallenna() throws SailoException {
		String virhe = "";
		try {
			kurssit.tallenna();
		} catch(SailoException e) {
			virhe = e.getMessage();
		}
		
		try {
			suoritukset.tallenna();
		} catch(SailoException e) {
			virhe += e.getMessage();
		}
		
		if(!"".equals(virhe)) {
			throw new SailoException(virhe);
		}
	}
	
	
	/**
	 * Poistaa kurssin/suorituksen 
	 * @param kurssi joka poistetaan
	 * @return montako kurssia poistettiin
	 * <pre name="test">
	 *   alustaOpintoloki();
     *   opintoloki.getKunnonKurssit().size() === 2;
     *   opintoloki.haeSuoritukset(aku1).size() === 2;
     *   opintoloki.poista(aku1) === 1;
     *   opintoloki.getKunnonKurssit().size() === 1;
     *   opintoloki.haeSuoritukset(aku1).size() === 0;
     *   opintoloki.haeSuoritukset(aku2).size() === 3;
     * </pre>
	 */
	public int poista(Kurssi kurssi) {
	    if ( kurssi == null ) return 0;
	    int ret = kurssit.poista(kurssi.getId()); 
	        
	    Suoritus poistettava = suoritukset.getSuoritukset(kurssi.getId()).get(0);
	    if(Integer.parseInt(poistettava.anna(1)) > 0) {
	    	int pisteet = kurssi.getOpintoPisteet();
	    	kurssit.setOpintopisteet(-pisteet);
	    }  
	    suoritukset.poistaKurssinSuoritukset(kurssi.getId());
	    return ret; 
	}
	        
	    	
	/**
     * Testiohjelma opintolokista
     * @param args ei k�yt�ss�
     */
	public static void main(String args[]) {
        Opintoloki opintoloki = new Opintoloki();

        try {
            // opintoloki.lueTiedostosta("opintoloki");

            Kurssi k1 = new Kurssi(), k2 = new Kurssi();
            k1.setOid();
            k1.annaVakio();
            k2.setOid();
            k2.annaVakio();
            

            opintoloki.lisaa(k1);
            opintoloki.lisaa(k2);
            int id1 = k1.getId();
            int id2 = k2.getId();
            Suoritus Tiep111 = new Suoritus(); 
            Tiep111.annaVakio(id1);
            opintoloki.lisaa(Tiep111);
            
            Suoritus Tiep112 = new Suoritus(); 
            Tiep112.annaVakio(id1);
            opintoloki.lisaa(Tiep112);
            
            Suoritus Tiep113 = new Suoritus(); 
            Tiep113.annaVakio(id2);
            opintoloki.lisaa(Tiep113);

            System.out.println("============= Opintolokin testi =================");

            for (int i = 0; i < opintoloki.KurssienMaara(); i++) {
                Kurssi kurssi = opintoloki.haeKurssi(i);
                System.out.println("Kurssi paikassa: " + i);
                kurssi.tulosta(System.out);
                List<Suoritus> loytyneet = opintoloki.haeSuoritukset(kurssi);
                for (Suoritus suoritus : loytyneet)
                    suoritus.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }
}