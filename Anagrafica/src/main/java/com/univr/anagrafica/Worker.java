package com.univr.anagrafica;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Classe contenente i campi e i metodi inerenti i lavoratori stagionali
 */
public class Worker extends Person {

    private final String address;
    private ArrayList<String> driveLicense = new ArrayList<>();
    private ArrayList<String> languages = new ArrayList<>();
    private boolean carOwner;
    private final DateCustom[][] availability = new DateCustom[4][2];
    private ArrayList<String> zones = new ArrayList<>();
    private final HashSet<EmergencyContact> emergencyContacts = new HashSet<>();
    private ArrayList<String> experiences = new ArrayList<>();
    private final HashSet<Work> works = new HashSet<>();
    private int nCont = 0;
    private int rows = 0;

    /**
     * Costruttore della classe lavoratore, il campo telefono va impostato separatamente in quanto opzionale
     * @param name       nome
     * @param surName    cognome
     * @param birthPlace luogo di nascita
     * @param birthDay   data di nascita
     * @param nation     nazionalità
     * @param address    indirizzo
     * @param email      email
     */
    public Worker(String name, String surName, String birthPlace, DateCustom birthDay, String nation, String address, String email){

        super(name, surName, birthPlace, birthDay, nation, email);
        if (address.isBlank() || address.isEmpty())
            throw new IllegalArgumentException("Indirizzo non inserito");
        this.address = address;
    }

    /**
     * Metodo utilizzato per aggiungere un contatto di emergenza
     * @param contact contatto
     */
    public void addEmergencyContact(EmergencyContact contact){
        if (nCont >= 3)
            throw new IndexOutOfBoundsException("è stato raggiunto il limite massimo di contatti, attualmente: " + nCont);
        if (emergencyContacts.contains(contact))
            throw new IndexOutOfBoundsException("Due contatti sono identici");

        emergencyContacts.add(contact);
        nCont ++;
    }

    /**
     * Metodo utilizzato per verificare se esista almeno un contatto di emergenza
     */
    public void existEmergencyContact(){
        if (nCont == 0)
            throw new InvalidParameterException("Inserire almeno un contatto d'emergenza");
    }

    /**
     * Metodo utilizzato per sovrascrivere i comuni di disponibilità
     * @param zones zone
     * @throws IllegalArgumentException il campo zone non può essere vuoto
     */
    public void resetZones(String ... zones){

        if(zones == null || zones[0] == null){
            throw new IllegalArgumentException("Il campo zone non può essere vuoto");
        }

        this.zones = new ArrayList<>();

        for(String s : zones)
            if (s != null)
                this.zones.add(s);
    }

    /**
     * Metodo utilizzato per sovrascrivere le esperienze lavorative
     * @param experiences esperienze
     * @throws IllegalArgumentException Il campo esperienze non può essere vuoto
     */
    public void resetExperiences(String ... experiences){
        if(experiences == null || experiences.length == 0){
            throw new IllegalArgumentException("Il campo esperienze non può essere vuoto");
        }

        this.experiences = new ArrayList<>();

        for (String esperienza : experiences)
            if (esperienza != null)
                this.experiences.add(esperienza);
    }

    /**
     * Metodo utilizzato per sovrascrivere le patenti possedute dal lavoratore
     * @param driveLicense patente
     * @throws IllegalArgumentException Il campo patente non può essere vuoto
     */
    public void resetDriveLicense(String ... driveLicense){
        if(driveLicense == null || driveLicense[0] == null){
            throw new IllegalArgumentException("Il campo patente non può essere vuoto");
        }

        this.driveLicense = new ArrayList<>();

        for (String license : driveLicense)
            if (license != null)
                this.driveLicense.add(license);
    }

    /**
     * Metodo utilizzato per sovrascrivere le lingue parlate dal lavoratore
     * @param languages lingue
     * @throws IllegalArgumentException Il campo lingue non può essere vuoto
     */
    public void resetLanguages(String ... languages){
        if(languages == null || languages[0] == null)
            throw new IllegalArgumentException("Il campo lingue non può essere vuoto");

        this.languages = new ArrayList<>();

        for (String language : languages)
            if (language != null)
                this.languages.add(language);
    }

    /**
     * Metodo utilizzato per aggiungere un periodo di disponibilità del lavoratore
     * @param dateCustomStart data d'inizio
     * @param dateCustomEnd   data di fine
     */
    public void addAvailability(DateCustom dateCustomStart, DateCustom dateCustomEnd) {
        if(dateCustomStart == null || dateCustomEnd == null)
            throw new IllegalArgumentException("La data inserita non è valida");
        if(rows >= 4)
            throw new IllegalArgumentException("Impossibile aggiungere ulteriori date: " + rows);
        if (DateCustom.compareTo(dateCustomStart, dateCustomEnd) > 0)
            throw new IllegalArgumentException("La data di inizio del periodo è maggiore della data in cui termina");
        if (DateCustom.compareTo(dateCustomEnd, new DateCustom()) < 0)
            throw new IllegalArgumentException("La fine del periodo è nel passato");

        availability[rows][0] = dateCustomStart;
        availability[rows][1] = dateCustomEnd;
        rows++;
    }

    /**
     * Metodo per aggiungere un lavoro
     * @param work lavoro
     * @throws IllegalArgumentException Il campo lavoro non può essere vuoto
     */
    public void addWork(Work work) {
        if(work == null)
            throw new IllegalArgumentException("Il campo lavoro non può essere vuoto");
        works.add(work);
    }

    /**
     * Metodo per eliminare un lavoro
     * @param work lavoro
     * @throws IllegalArgumentException Errore durante l'eliminazione: lavoro è vuoto
     */
    public void delWork(Work work) {
        if(work == null)
            throw new IllegalArgumentException("Errore durante l'eliminazione: lavoro è vuoto");
        works.remove(work);
    }

    /**
     * Metodo utilizzato per definire se il lavoratore è automunito o meno
     * @param carOwner automunito
     * @throws IllegalArgumentException Il campo automunito non può essere vuoto
     */
    public void setCarOwner(String carOwner){
        if(carOwner == null)
            throw new IllegalArgumentException("Il campo automunito non può essere vuoto");
        this.carOwner = carOwner.equals("Sì");
    }

    /**
     * Metodo utilizzato per sapere se il lavoratore automunito
     * @return automunito
     */
    public Boolean getCarOwner(){
        return carOwner;
    }

    /**
     * Metodo utilizzato per ottenere le lingue parlate dal lavoratore
     * @return lingue
     */
    public ArrayList<String> getLanguages(){
        return languages;
    }

    /**
     * Metodo utilizzato per ottenere i contatti di emergenza del lavoratore
     * @return contattiEmergenza
     */
    public HashSet<EmergencyContact> getEmergencyContacts(){
        return emergencyContacts;
    }

    /**
     * Metodo utilizzato per ottenere le patenti possedute dal lavoratore
     * @return tipoPatente
     */
    public ArrayList<String> getDriveLicense() {
        return driveLicense;
    }

    /**
     * Metodo utilizzato per ottenere l'indirizzo del lavoratore
     * @return indirizzo
     */
    public String getAddress() {
        return address;
    }

    /**
     * Metodo utilizzato per ottenere le esperienze lavorative del lavoratore
     * @return stringa delle esperienze lavorative, separate da ;
     */
    public ArrayList<String> getExperiences(){
        return experiences;
    }

    /**
     * Metodo utilizzato per ottenere i comuni di disponibilità del lavoratore
     * @return stringa dei comuni di disponibilità, separate da ;
     */
    public ArrayList<String> getZones(){
        return zones;
    }

    /**
     * Metodo utilizzato per ottenere i periodi di disponibilità del lavoratore
     * @return stringa dei periodi di disponibilità, separate da ;
     */
    public String getAvailability(){
        StringBuilder res = new StringBuilder();
        for(int j = 0; j < rows; j++)
            if (j == 0)
                res.append(availability[j][0]).append("-").append(availability[j][1]);
            else
                res.append(";").append(availability[j][0]).append("-").append(availability[j][1]);

        return res.toString();
    }

    /**
     * Metodo utilizzato per ottenere i periodi di disponibilità del lavoratore
     * @return matrice di Data
     */
    public DateCustom[][] getDates(){
        return availability;
    }

    /**
     * Utilizzato per ottenere i lavori stagionali
     * @return HashSet di lavori
     */
    public HashSet<Work> getWorks(){
        return works;
    }

    /**
     * Metodo utilizzato per visualizzare in stringa la classe
     * @return i campi della classe separati da virgola
     */
    public String toString(){
        return super.toString() + "," + address + "," + toXML(languages) + "," + toXML(driveLicense) + "," + carOwner + "," +
                toXML(zones) + "," + getAvailability() + ","  + toXML(experiences) + worksToString();
    }

    /**
     * Metodo per formattare i lavori stagionali in XML
     * @return lavori stagionali in XML
     */
    private String worksToString() {
        StringBuilder stringBuilder = new StringBuilder(",");

        if(works.isEmpty())
            return "";

        for (Work work : works)
            stringBuilder.append(work.toString());

        return stringBuilder.toString();
    }

}
