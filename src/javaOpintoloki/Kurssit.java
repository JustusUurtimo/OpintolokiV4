package javaOpintoloki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Opintolokin kurssit joka osaa mm. lis�t� uuden kurssin
 * @author Olli Mehtonen ja Justus Uurtimo
 * @version 3.5.2019
 */
public class Kurssit {
	
	private static final int MaxKurssit = 15;
	@SuppressWarnings("unused")
    private Kurssi[] kurssit = new Kurssi[MaxKurssit];
	private HashMap<Integer, Kurssi> kunnonKurssit = new HashMap<>();
	private String tiedostonPerusNimi = "kurssit";
	private String kokoNimi = "";
	private Boolean muutettu = false;
	@SuppressWarnings("unused")
    private int lkm = 0;
	private int opintoPisteet;
	
	
	/**
	 * Alustaa kurssit
	 */
	public Kurssit() {
		
	}
	
	
	/**
	 * Getataan kunnonKurssit hashmap
	 * @return kunnonKurssit
	 */
	public HashMap<Integer, Kurssi> getKunnonKurssit() {
		return kunnonKurssit;
	}
	
	
   /**
	* Lisää uuden jäsenen tietorakenteeseen.  Ottaa jäsenen omistukseensa.
	* @param kurssi lisätäävän jäsenen viite.  Huom tietorakenne muuttuu omistajaksi
	* @throws SailoException jos tietorakenne on jo täynnä
	* @example
	* <pre name="test">
	* #THROWS SailoException 
    * Kurssit kurssit2 = new Kurssit();
    * kurssit2.getLkm() === 0;
    * Kurssi k3 = new Kurssi();
    * k3.annaVakio();
    * kurssit2.lisaa(k3);
    * kurssit2.getLkm() === 1;
	* </pre>
	*/
	public void lisaa(Kurssi kurssi) throws SailoException {
		kunnonKurssit.put(kurssi.getId(), kurssi);
		
		lkm++;
		muutettu = true;		
	}
	
	
	/**
	 * Haetaan kurssi
	 * @param id haettavan kurssin id
	 * @return kurssi jota haettiin
	 */
	public Kurssi hae(int id) {
		return kunnonKurssit.get(id);
	}
	
	
	/**
	 * Asetetaan tiedostolle nimi
	 * @param nimi nimi, joka asetetaan
	 */
	public void setTiedostonPerusNimi(String nimi) {
		this.tiedostonPerusNimi = nimi;
	}
	
	
	/**
	 * Getataan tiedoston nimi
	 * @return tiedoston nimi
	 */
	public String getTiedostonNimi() {
		return this.tiedostonPerusNimi + ".dat";
	}
	
	
	/**
     * Getataan tiedoston perus nimi
     * @return tiedoston perus nimi
     */
	public String getTiedostonPerusNimi() {
		return this.tiedostonPerusNimi;
	}
	

    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
	public String getBakNimi() {
		return this.tiedostonPerusNimi + ".bak";
	}
	
	
	/**
     * Palauttaa Opintolokin koko nimen
     * @return Opintolokin koko nimi merkkijononna
     */
	public String getKokoNimi() {
		return this.kokoNimi;
	}
	
	
    /**
     * Luokka jäsenten iteroimiseksi.
     */
	public class JasenetIterator implements Iterator<Kurssi> {
		private int kohdalla = 0;

		
		/**
		 * Onko olemassa viel� seuraavaa j�sent�
		 * @see java.util.Iterator#hasNext()
		 * @return true jos on viel� j�seni�
		 */
		@Override
		public boolean hasNext() {
			return kohdalla < getLkm();
		}

	        
		/**
		 * Annetaan seuraava j�sen
		 * @return seuraava j�sen
		 * @throws NoSuchElementException jos seuraava kurssita ei en�� ole
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Kurssi next() throws NoSuchElementException {
			if ( !hasNext() ) throw new NoSuchElementException("Ei oo");
			return hae(kohdalla++);
		}
	}		


	/**
	 * Palautetaan iteraattori j�senist��n.
	 * @return j�sen iteraattori
	 */
	public Iterator<Kurssi> iterator() {
	    return new JasenetIterator();
	}
	
	
	/**
     * Lukee kurssit tiedostosta.  Kesken.
     * @param tiedosto tiedoston hakemisto
     * @throws SailoException jos lukeminen ep�onnistuu
     */
	public void lueTiedostosta(String tiedosto) throws SailoException{
		setTiedostonPerusNimi(tiedosto);
		try( BufferedReader fi = new BufferedReader(new FileReader (getTiedostonNimi()))) {
			kokoNimi = fi.readLine();
			
			if(kokoNimi == null) {
				throw new SailoException("Kurssin nimi puuttuu, tai muu virhe");				
			}
			
			String rivi = fi.readLine();
			if(rivi == null) {
				throw new SailoException("Rivi� ei ole");
			}
			
			while((rivi = fi.readLine()) != null) {
				rivi = rivi.trim();
				if("".equals(rivi) || rivi.charAt(0) == ';') {
					continue;
				}
				Kurssi kurssi = new Kurssi();
				kurssi.parse(rivi);
				lisaa(kurssi);
			}
			muutettu = false;
		} catch (FileNotFoundException e) {
			throw new SailoException(tiedosto + " ei aukea!");
		} catch (IOException e) {
			throw new SailoException("Onglemia" + tiedosto + e.getMessage());
		}
	}
	
	
    /**
     * Lukee kurssin tiedostosta. 
     * @throws SailoException jos lukeminen epäonnistuu
     */ 
	public void lueTiedostosta() throws SailoException  {
		lueTiedostosta(getTiedostonPerusNimi());
	}

	
    /**
     * Korvaa jäsenen tietorakenteessa.  Ottaa jäsenen omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva jäsen.  Jos ei löydy,
     * niin lisätään uutena jäsenenä.
     * @param kurssi kurssi jota lisätään/muokataan
     * @throws SailoException jos täynnä
     */ 
	public void korvaaTaiLisaa(Kurssi kurssi) throws SailoException {
		int id = kurssi.getId();
		Kurssi kala = kunnonKurssit.get(id);
		if(kala == null) {
			lisaa(kurssi);
			return;
		}
		kunnonKurssit.put(id, kurssi);
		muutettu = true;
	}
	
	
	/**
     * Tallentaa kurssit tiedostoon. 
     * @throws SailoException jos talletus epäonnistuu
     */
	public void tallenna() throws SailoException {
		if(!muutettu) {
			return;
		}
		File fbak = new File(getBakNimi());
		File ftied = new File(getTiedostonNimi());
		fbak.delete();
		ftied.renameTo(fbak);
		
		try(PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath()))) {
			fo.println(getKokoNimi());
			fo.println(kunnonKurssit.size());
			for(Kurssi kurssi: this.kunnonKurssit.values()) {
				System.out.println(kurssi.toString());
				fo.println(kurssi.toString());
			}
			
		} catch (FileNotFoundException e) {
			throw new SailoException(ftied.getName() + " ei aukea!");
		} catch (IOException e) {
			throw new SailoException("Onglemia" + ftied.getName() + e.getMessage());
		}
		muutettu = false;
	}
	
	
    /**
     * Getataan kurssien lukumäärä
     * @return Kurssien lukumäärä
     */
	public int getLkm() {
		return kunnonKurssit.size();
	}
	
	
	/**
     * Getataan opintopisteet
     * @return Kurssien opintopisteet
     */
	public int getOpintopisteet() {
		int pisteet = 0;
		for(Kurssi kurssi : this.kunnonKurssit.values()) {
			pisteet += kurssi.getOpintoPisteet();
		}
		return this.opintoPisteet;
	}
	
	
	/**
     * Setataan kurssien lukumäärä
	 * @param pisteet pisteet, jotka asetetaan
     */
	public void setOpintopisteet(int pisteet) {
		this.opintoPisteet += pisteet;
	}
	
	
	/**
     * Poistetaan kurssi ja vähennetään kurssien lukumäärää
     * @param id jonka kurssi poistetaan
     * @return 1
     */
	public int poista(int id) { 
        kunnonKurssit.remove(id);
        lkm--;
        muutettu = true;
        return 1;
    } 
	
	
	/**
     * Testiohjelma kursseille
     * @param args ei käytössä
     */
	public static void main(String args[]) {
		Kurssi k1 = new Kurssi();
		k1.setOid();
		k1.annaVakio();
		Kurssi k2 = new Kurssi();
		k2.setOid();
		k2.annaVakio();
		
		Kurssit kurssit = new Kurssit();
		try {
			kurssit.lisaa(k1);
			kurssit.lisaa(k2);
			
			for(int i = 0; i < kurssit.getLkm(); i++) {
				int x = i+1;
				Kurssi kurssi = kurssit.hae(i);
				System.out.println("Kurssin nro: " + x);
				kurssi.tulosta(System.out);
			}
		} catch (SailoException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
