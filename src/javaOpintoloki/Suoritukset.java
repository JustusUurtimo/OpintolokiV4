package javaOpintoloki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * Kurssin suoritukset jotka osaavat lisätä uuden suorituksen
 * @author Olli Mehtonen ja Justus Uurtimo
 * @version 3.5.2019
 */
public class Suoritukset implements Iterable<Suoritus> {

	private final Collection<Suoritus> suoritukset = new ArrayList<Suoritus>();
	private boolean muutettu = false;
	private String tiedostonPerusNimi = "suoritukset";
	private int hylatyt = 0;
	private int hyvaksytyt = 0;
	
	
	/**
	 * Suoritusten alustus
	 */
	public Suoritukset() {
		
	}
	
	
	/**
	 * Suorituksen lisääminen
	 * @param suor lisättävä suoritus
	 */
	public void lisaaSuoritus(Suoritus suor) {
		suoritukset.add(suor);
		if(Integer.parseInt(suor.anna(1)) > 0) {
			this.hyvaksytyt++;
		} else {
			this.hylatyt++;
		}
		muutettu = true;
	}
	
	
	/**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param nimi tallennustiedoston perusnimi
     */
	public void setTiedostonPerusNimi(String nimi) {
		this.tiedostonPerusNimi = nimi;
	}
	
	
	/**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
	public String getTiedostonNimi() {
		return this.tiedostonPerusNimi + ".dat";
	}
	
	
	/**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
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
	 * Haetaan hyväksytyt suoritukset
	 * @return hyväksytyt suoritukset
	 */
	public int getHyvaksytyt() {
		return this.hyvaksytyt;
	}
	
	
	/**
     * Haetaan hylätyt suoritukset
     * @return hylätyt suoritukset
	 */
	public int getHylatyt() {
		return this.hylatyt;
	}
	
	
	/**
     * Lukee tiedostosta.  
     * @param tiedosto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
	public void lueTiedostosta(String tiedosto) throws SailoException{
		setTiedostonPerusNimi(tiedosto);
		try( BufferedReader fi = new BufferedReader(new FileReader (getTiedostonNimi()))) {
			
			String rivi;
			while((rivi = fi.readLine()) != null) {
				rivi = rivi.trim();
				if("".equals(rivi) || rivi.charAt(0) == ';') {
					continue;
				}
				Suoritus suoritus = new Suoritus();
				suoritus.parse(rivi);
				lisaaSuoritus(suoritus);
			}
			muutettu = false;
			
		} catch (FileNotFoundException e) {
			throw new SailoException(tiedosto + " ei aukea!");
		} catch (IOException e) {
			throw new SailoException("Onglemia" + tiedosto + e.getMessage());
		}
	}
	
	
	/**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @throws SailoException jos tulee poikkeus
     */
	public void lueTiedostosta() throws SailoException  {
		lueTiedostosta(getTiedostonPerusNimi());
	}
	
	
	/**
     * Tallentaa suoritust tiedostoon.
     * @throws SailoException jos talletus ep�onnistuu
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
			for(Suoritus suoritus: this.suoritukset) {
				fo.println(suoritus.toString());
			}
		} catch (FileNotFoundException e) {
			throw new SailoException(ftied.getName() + " ei aukea!");
		} catch (IOException e) {
			throw new SailoException("Onglemia" + ftied.getName() + e.getMessage());
		}
		muutettu = false;
	}
	
				
	/**
     * Tallentaa j�senist�n tiedostoon.  
     * @return suoritusten lukumäärä
     */
	public int getLkm() {
		return suoritukset.size();
	}
	
	
	/**
     * Iteraattori kaikkien suoritusten l�pik�ymiseen
     * @return suoritukset iteroituna 
	 */
	@Override
	public Iterator<Suoritus> iterator() {
		return suoritukset.iterator();
	}
	
	
	/**
     * Haetaan kaikki suoritusn suoritukset
	 * @param oid suoritusn id jolle harrastuksia haetaan
     * @return tietorakenne jossa viiteet l�ydetteyihin suorituksiin
     * </pre>
	 */
	public List<Suoritus> getSuoritukset(int oid) {
		List<Suoritus> loydetyt = new ArrayList<Suoritus>();
		for(Suoritus suoritus : suoritukset) {
			if(suoritus.getId() == oid) {
				loydetyt.add(suoritus);
			}
		}
		return loydetyt;
	}
	
	
	/**
	 * Poistaa tietyn kurssin suoritukset
	 * @param id kurssin id jonka suoritukset poistetaan
	 * ottaa id:n ja poistaa sit� vastaavat suoritukset.
	 * tarkastaa oliko suoritus hyl�tty vai hyväksytty ja muuttaa niiden arvon oikeaksi
	 * @return palauttaa tiedon jos muutettiin eli n
	 */
	public int poistaKurssinSuoritukset(int id) {
        int n = 0;
        for (Iterator<Suoritus> it = suoritukset.iterator(); it.hasNext();) {
            Suoritus har = it.next();
            if ( har.getId() == id ) {
                it.remove();
                n++;
                //kysyy poistettavan kurssin arvosanan ja miinustaa joko hylättyjen määrää tai hyv�ksyttyjen määrää
                if(Integer.parseInt(har.anna(1)) > 0) {
        			this.hyvaksytyt--;
        		} else {
        			this.hylatyt--;
        		}
            }
        }
        if (n > 0) muutettu = true;
        return n;
    }

	
	/**
     * Testiohjelma suorituksille
     * @param args ei käytössä 
     */
    public static void main(String[] args) {
        Suoritukset suoritukset = new Suoritukset();
        Suoritus s1 = new Suoritus(1);
        s1.annaVakio(1);
        Suoritus s2 = new Suoritus(2);
        s2.annaVakio(2);
        Suoritus s3 = new Suoritus(3);
        s3.annaVakio(3);
        
        suoritukset.lisaaSuoritus(s1);
        suoritukset.lisaaSuoritus(s2);
        suoritukset.lisaaSuoritus(s3);
        
        List<Suoritus> suorituksetLista = suoritukset.getSuoritukset(1);
        
        for(Suoritus suor : suorituksetLista) {
        	System.out.println(suor.getId() + " ");
        	suor.tulosta(System.out);
        }
    }
}