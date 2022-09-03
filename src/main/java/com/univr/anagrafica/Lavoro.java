package com.univr.anagrafica;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe per
 */
public class Lavoro implements Serializable {
    private Data[] periodo = new Data[2];
    private String azienda, mansione, luogo, retribuzione;
    private static HashSet<Lavoro> lavoriBackup = new HashSet<Lavoro>();
    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * Costruttore della classe lavoro
     * @param dataInizio
     * @param dataFine
     * @param azienda
     * @param mansione
     * @param luogo
     * @param retribuzione
     */
    public Lavoro (Data dataInizio, Data dataFine, String azienda, String mansione, String luogo, String retribuzione) {
        setPeriodo(dataInizio, dataFine);
        setAzienda(azienda);
        setMansione(mansione);
        setLuogo(luogo);
        setRetribuzione(retribuzione);
    }

    /**
     * Metodo utilizzato per ottenere la base per una ricerca
     * @return
     */
    public static HashSet<Lavoro> getLavoriBackup() {
        return lavoriBackup;
    }

    /**
     * Metodo per impostare il periodo di lavoro
     * @param dataInizio
     * @param dataFine
     */
    public void setPeriodo(Data dataInizio, Data dataFine) {
        if (dataInizio == null)
            throw new IllegalArgumentException("La data di inizio non è stata inserita");

        if (dataFine == null)
            throw new IllegalArgumentException("La data di fine non è stata inserita");

        if (Data.compareTo(dataInizio, dataFine) > 0)
            throw new IllegalArgumentException("La data di inizio del periodo è maggiore della data in cui termina");

        periodo[0] = dataInizio;
        periodo[1] = dataFine;
    }

    /**
     * Metodo per impostare l'azienda di lavoro
     * @param azienda
     */
    public void setAzienda (String azienda) {
        if (azienda == null || azienda.isEmpty() || azienda.isBlank())
            throw new IllegalArgumentException("L'azienda non può essere vuota");

        this.azienda = azienda;
    }

    /**
     * Metodo per impostare la mansione di lavoro
     * @param mansione
     */
    public void setMansione (String mansione) {
        if (mansione == null || mansione.isEmpty() || mansione.isBlank())
            throw new IllegalArgumentException("La mansione non può essere vuota");

        this.mansione = mansione;
    }

    /**
     * Metodo per impostare il luogo di lavoro
     * @param luogo
     */
    public void setLuogo (String luogo) {
        if (luogo == null || luogo.isEmpty() || luogo.isBlank())
            throw new IllegalArgumentException("Il luogo non può essere vuoto");

        this.luogo = luogo;
    }

    /**
     * Metodo per impostare la retribuzione di lavoro
     * @param retribuzione
     */
    public void setRetribuzione (String retribuzione) {
        isValidRetribuzione(retribuzione);
        this.retribuzione = retribuzione;
    }

    /**
     * Metodo per ottenere il periodo di lavoro
     * @return
     */
    public Data[] getPeriodo() {
        return periodo;
    }

    /**
     * Metodo per ottenere l'azienda di lavoro
     * @return
     */
    public String getAzienda () {
        return azienda;
    }

    /**
     * Metodo per ottenere la mansione di lavoro
     * @return
     */
    public String getMansione () {
        return mansione;
    }

    /**
     * Metodo per ottenere il luogo di lavoro
     * @return
     */
    public String getLuogo () {
        return luogo;
    }

    /**
     * Metodo per ottenere la retribuzione di lavoro
     * @return
     */
    public String getRetribuzione () {
        return retribuzione;
    }

    /**
     * Metodo utilizzato per visualizzare in stringa la classe
     * @return i campi della classe separati da virgola
     */
    public String toString () {
        return "" + periodo[0] + "-" + periodo[1] + "," + azienda + "," + mansione + "," + luogo + "," + retribuzione;
    }

    /**
     * Metodo per verificare se la retribuzione è valida
     * @param retribuzione
     */
    private void isValidRetribuzione(String retribuzione) throws IllegalArgumentException{
        String regex = "^\\d+$";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(retribuzione);

        if(!m.matches())
            throw new IllegalArgumentException("Retribuzione inserita non valida");
    }

    /**
     * Verifica se due istanze di lavoro hanno campi uguali
     * @param other
     * @return
     */
    public boolean equals(Object other) {
        Lavoro ogg;

        if (!(other instanceof Lavoro)) {
            throw new IllegalArgumentException("Il parametro passato non è di classe Persona");
        }

        ogg = ((Lavoro)other);

        return (azienda.equals(ogg.getAzienda())
                && luogo.equals(ogg.getLuogo())
                && mansione.equals(ogg.getMansione())
                && retribuzione.equals(ogg.getRetribuzione())
                && Data.compareTo(periodo[0], ogg.getPeriodo()[0]) == 0
                && Data.compareTo(periodo[1], ogg.getPeriodo()[1]) == 0);
    }

    /**
     * Utilizzato per l'hashing di un oggetto della classe Lavoro
     * @return
     */
    public int hashCode() {
        return azienda.charAt(0) + luogo.charAt(0);
    }
}
