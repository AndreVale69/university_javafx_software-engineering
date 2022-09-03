package com.univr.anagrafica;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Classe contenente i campi e i metodi inerenti i lavoratori stagionali
 */
public class Lavoratore extends Persona{

    private String indirizzo;
    private ArrayList<String> tipoPatente = new ArrayList<>();
    private ArrayList<String> lingue = new ArrayList<>();

    // Stringa contenente le patenti canoniche, solo per verifica
    private static final String[] patenti = {"AM", "A1", "A2", "A", "B1", "B", "BE", "C1", "C1E", "C", "CE",
                                             "D1", "D1E", "D", "DE", "KA", "KB", "CQC", "CFP ADR"};
    private boolean automunito;
    private Data[][] disponibilita = new Data[4][2];
    private ArrayList<String> zone = new ArrayList<>();
    private HashSet<ContattoDiEmergenza> contattiEmergenza = new HashSet<>();
    private ArrayList<String> esperienzeLavorative = new ArrayList<>();
    private HashSet<Lavoro> lavori = new HashSet<>();
    private int nCont = 0;
    private int rows = 0;

    /**
     * Costruttore della classe lavoratore, il campo telefono va impostato separatamente in quanto opzionale
     * @param nome
     * @param cognome
     * @param luogoDiNascita
     * @param nascita
     * @param nazionalita
     * @param indirizzo
     * @param email
     */
    public Lavoratore(String nome, String cognome, String luogoDiNascita, Data nascita, String nazionalita, String indirizzo, String email){

        super(nome, cognome, luogoDiNascita, nascita, nazionalita, email);
        if (indirizzo.isBlank() || indirizzo.isEmpty())
            throw new IllegalArgumentException("Indirizzo non inserito");
        this.indirizzo = indirizzo;
    }

    /**
     * Metodo utilizzato per aggiungere un contatto di emergenza
     * @param contatto
     */
    public void addContattoEmergenza(ContattoDiEmergenza contatto){
        if (nCont >= 3)
            throw new IndexOutOfBoundsException("è stato raggiunto il limite massimo di contatti, attualmente: " + nCont);
        if (contattiEmergenza.contains(contatto))
            throw new IndexOutOfBoundsException("Due contatti sono identici");

        contattiEmergenza.add(contatto);
        nCont ++;
    }

    /**
     * Metodo utilizzato per verificare se esista almeno un contatto di emergenza
     */
    public void existContattoEmergenza(){
        if (nCont == 0)
            throw new InvalidParameterException("Inserire almeno un contatto d'emergenza");
    }

    /**
     * Metodo utilizzato per rimuovere tutti i contatti di emergenza
     */
    public void clearContattoEmergenza() {
        contattiEmergenza.clear();
        nCont = 0;
    }

    /**
     * Metodo utilizzato per sovrascrivere i comuni di disponibilità
     * @param zona
     * @throws IllegalArgumentException
     */
    public void resetZone(String ... zona){

        if(zona == null || zona[0] == null){
            throw new IllegalArgumentException("Il campo zone non può essere vuoto");
        }

        this.zone = new ArrayList<>();

        for(String s : zona)
            if (s != null)
                this.zone.add(s);
    }

    /**
     * Metodo utilizzato per sovrascrivere le esperienze lavorative
     * @param esperienze
     * @throws IllegalArgumentException
     */
    public void resetEsperienzeLavorative(String ... esperienze){
        if(esperienze == null || esperienze.length == 0){
            throw new IllegalArgumentException("Il campo esperienze non può essere vuoto");
        }

        this.esperienzeLavorative = new ArrayList<>();

        for (String esperienza : esperienze)
            if (esperienza != null)
                this.esperienzeLavorative.add(esperienza);
    }

    /**
     * Metodo utilizzato per sovrascrivere le patenti possedute dal lavoratore
     * @param tipoPatente
     * @throws IllegalArgumentException
     */
    public void resetTipoPatente(String ... tipoPatente){
        if(tipoPatente == null || tipoPatente[0] == null){
            throw new IllegalArgumentException("Il campo patente non può essere vuoto");
        }

        this.tipoPatente = new ArrayList<>();

        for (String patente : tipoPatente)
            if (patente != null)
                this.tipoPatente.add(patente);
    }

    /**
     * Metodo utilizzato per sovrascrivere le lingue parlate dal lavoratore
     * @param lingue
     * @throws IllegalArgumentException
     */
    public void resetLingue(String ... lingue){
        if(lingue == null || lingue[0] == null)
            throw new IllegalArgumentException("Il campo lingue non può essere vuoto");

        this.lingue = new ArrayList<>();

        for (String lingua : lingue)
            if (lingua != null)
                this.lingue.add(lingua);
    }

    /**
     * Metodo utilizzato per aggiungere un periodo di disponibilità dell'utente
     * @param dataInizio
     * @param dataFine
     * @throws IllegalArgumentException
     */
    public void addDisponibilita (Data dataInizio, Data dataFine) throws IllegalArgumentException{
        if(dataInizio == null || dataFine == null)
            throw new IllegalArgumentException("La data inserita non è valida");
        if(rows >= 4)
            throw new IllegalArgumentException("Impossibile aggiungere ulteriori date: " + rows);
        if (Data.compareTo(dataInizio, dataFine) > 0)
            throw new IllegalArgumentException("La data di inizio del periodo è maggiore della data in cui termina");
        if (Data.compareTo(dataFine, new Data()) < 0)
            throw new IllegalArgumentException("La fine del periodo è nel passato");

        disponibilita[rows][0] = dataInizio;
        disponibilita[rows][1] = dataFine;
        rows++;
    }

    /**
     * Metodo utilizzato per rimuovere tutti i periodi di disponibilità del lavoratore
     */
    public void clearDisponibilita(){
        disponibilita = new Data[4][2];
    }

    /**
     * Metodo per aggiungere un lavoro
     * @param lavoro
     * @throws IllegalArgumentException
     */
    public void addLavoro (Lavoro lavoro) {
        if(lavoro == null)
            throw new IllegalArgumentException("Il campo lavoro non può essere vuoto");
        lavori.add(lavoro);
    }

    /**
     * Metodo per eliminare un lavoro
     * @param lavoro
     * @throws IllegalArgumentException
     */
    public void delLavoro (Lavoro lavoro) {
        if(lavoro == null)
            throw new IllegalArgumentException("Errore nella eliminazione: lavoro è vuoto");
        lavori.remove(lavoro);
    }

    /**
     * Metodo utilizzato per impostare l'indirizzo
     * @param indirizzo
     * @throws IllegalArgumentException
     */
    public void setIndirizzo(String indirizzo){
        if(indirizzo.isEmpty() || indirizzo.isBlank())
            throw new IllegalArgumentException("Il campo indirizzo non può essere vuoto");
        this.indirizzo = indirizzo;
    }

    /**
     * Metodo utilizzato per definire se il lavoratore è automunito o meno
     * @param automunito
     * @throws IllegalArgumentException
     */
    public void setAutomunito(String automunito){
        if(automunito == null)
            throw new IllegalArgumentException("Il campo automunito non può essere vuoto");
        this.automunito = automunito.equals("Sì");
    }

    /**
     * Metodo utilizzato per sapere se il lavoratore automunito
     * @return automunito
     */
    public Boolean getAutomunito(){
        return automunito;
    }

    /**
     * Metodo utilizzato per ottenere le lingue parlate dal lavoratore
     * @return lingue
     */
    public ArrayList<String> getLingue(){
        return lingue;
    }

    /**
     * Metodo utilizzato per ottenere i contatti di emergenza del lavoratore
     * @return contattiEmergenza
     */
    public HashSet<ContattoDiEmergenza> getContattiEmergenza(){
        return contattiEmergenza;
    }

    /**
     * Metodo utilizzato per ottenere le patenti possedute dal lavoratore
     * @return tipoPatente
     */
    public ArrayList<String> getTipoPatente() {
        return tipoPatente;
    }

    /**
     * Metodo utilizzato per ottenere l'indirizzo del lavoratore
     * @return indirizzo
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Metodo utilizzato per ottenere le esperienze lavorative del lavoratore
     * @return stringa delle esperienza lavorative, separate da ;
     */
    public ArrayList<String> getEsperienzeLavorative(){
        return esperienzeLavorative;
    }

    /**
     * Metodo utilizzato per ottenere i comuni di disponibilità del lavoratore
     * @return stringa dei comuni di disponibilità, separate da ;
     */
    public ArrayList<String> getZone(){
        return zone;
    }

    /**
     * Metodo utilizzato per ottenere i periodi di disponibilità del lavoratore
     * @return stringa dei periodi di disponibilità, separate da ;
     */
    public String getDisponibilita(){
        String res = "";
        for(int j = 0; j < rows; j++){
            if (j == 0)
                res += disponibilita[j][0] + "-" + disponibilita[j][1];
            else
                res += ";" + disponibilita[j][0] + "-" + disponibilita[j][1];
        }
        return res;
    }

    /**
     * Metodo utilizzato per ottenere i periodi di disponibilità del lavoratore
     * @return matrice di Data
     */
    public Data[][] getDates(){
        return disponibilita;
    }

    /**
     * Utilizzato per ottenere i lavori stagionali
     * @return HashSet di lavori
     */
    public HashSet<Lavoro> getLavori(){
        return lavori;
    }

    /**
     * Metodo utilizzato per visualizzare in stringa la classe
     * @return i campi della classe separati da virgola
     */
    public String toString(){
        return super.toString() + "," + indirizzo + "," + toXML(lingue) + "," + toXML(tipoPatente) + "," + automunito + "," +
                toXML(zone) + "," + getDisponibilita() + ","  + toXML(esperienzeLavorative) + lavoriToString();
    }

    /**
     * Metodo che restituisce il DTO dell'oggetto Lavoratore chiamante
     * @return
     */
    public LavoratoreDTO getDTO() {
        return new LavoratoreDTO(getNome(), getCognome(), getLuogoDiNascita(),
        getNazionalita(), getEmail(), this.indirizzo, getTelefono(), getNascita(),
        this.tipoPatente, this.lingue, this.automunito, this.disponibilita, this.zone,
        this.contattiEmergenza, this.esperienzeLavorative);
    }

    /**
     * Metodo per formattare i lavori stagionali in XML
     * @return
     */
    private String lavoriToString() {
        StringBuilder stringBuilder = new StringBuilder(",");

        if(lavori.isEmpty())
            return "";

        for (Lavoro lavoro : lavori)
            stringBuilder.append(lavoro.toString());

        return stringBuilder.toString();
    }

}
