package fxopintoloki;


/**
 * Rajapinta tietueelle johon voidaan taulukon avulla rakentaa 
 * "attribuutit".
 * @author Olli Mehtonen ja Justus Uurtimo
 * @version 3.5.2019
 */
public interface Tietue {

    
    /**
     * @return tietueen kenttien lukumäärä
     */
    public abstract int getKenttia();


    /**
     * @return ensimm�inen käyttäjän syötettävän kentän indeksi
     */
    public abstract int ekaKentta();


    /**
     * @param k mink� kent�n kysymys halutaan
     * @return valitun kent�n kysymysteksti
     */
    public abstract String getKysymys(int k);


    /**
     * @param k minkä kentän sisältö halutaan
     * @return valitun kentän sisältö
     * @example
     * <pre name="test">
     *   Suoritus har = new Suoritus();
     *   har.parse("   1   ,   2   ,   3");
     *   har.anna(0) === "1";   
     *   har.anna(1) === "2";   
     *   har.anna(2) === "3";   
     * </pre>
     */
    public abstract String anna(int k);

    
    /**
     * Asetetaan valitun kentän sisältö.  Mikäli asettaminen onnistuu,
     * palautetaan null, muutoin virheteksti.
     * @param k minkä kentän sisältö asetetaan
     * @param s asetettava sisältö merkkijonona
     * @return string
     */
    public abstract String aseta(int k, String s);


    /**
     * Tehdään identtinen klooni tietueesta
     * @return kloonattu tietue
     * @throws CloneNotSupportedException jos kloonausta ei tueta
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Suoritus har = new Suoritus();
     *   har.parse("   1   ,  2   ,   3");
     *   Object kopio = har.clone();
     *   kopio.toString() === har.toString();
     *   har.parse("   1   ,  3   ,   2");
     *   kopio.toString().equals(har.toString()) === false;
     *   kopio instanceof Suoritus === true;
     * </pre>
     */
    public abstract Tietue clone() throws CloneNotSupportedException;


    /**
     * Palauttaa tietueen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return tietue tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Suoritus harrastus = new Suoritus();
     *   harrastus.parse("   1   ,   2   ,   3");
     *   harrastus.toString() =R= "1\\,2\\,3";
     * </pre>
     */
    @Override
    public abstract String toString();

}