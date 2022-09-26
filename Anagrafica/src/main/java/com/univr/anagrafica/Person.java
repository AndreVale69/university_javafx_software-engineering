package com.univr.anagrafica;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe che contiene i campi condivisi da responsabili e lavoratori e i metodi per accedervi
 */
public abstract class Person implements Serializable {
    private String name;
    private String surName;
    private String phone = "", birthPlace, nation, email;
    private DateCustom birthDay;
    private int hash;
    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * Prende in ingresso solo i dati anagrafici e l'email,
     * l'eventuale recapito telefonico va impostato separatamente
     * @param name       nome
     * @param surName    cognome
     * @param birthPlace luogo di nascita
     * @param birthDay   data di nascita
     * @param nation     nazionalità
     * @param email      email
     */
    Person(String name, String surName, String birthPlace, DateCustom birthDay, String nation, String email){
        isValidName(name, surName);
        this.name = name;
        this.surName = surName;
        if (birthPlace.isBlank() || birthPlace.isEmpty())
            throw new IllegalArgumentException("Luogo di nascita non inserito");
        this.birthPlace = birthPlace;
        if (nation == null)
            throw new IllegalArgumentException("Nazionalità non inserita");
        this.nation = nation;

        if (!DateCustom.isOlderThan(birthDay, 18))
            throw new IllegalArgumentException("Età minore di 18 anni");
        this.birthDay = birthDay;

        isValidEmail(email);
        this.email = email;

        this.hash = setID();
    }

    /**
     * costruttore per il contatto di emergenza, prende meno parametri
     * @param name    nome
     * @param surName cognome
     * @param email   email
     */
    Person(String name, String surName, String email, String phone){
        isValidName(name, surName);
        this.name = name;
        this.surName = surName;
        isValidEmail(email);
        this.email = email;
        isValidPhone(phone);
        this.phone = phone;

        this.hash = setID();
    }

    /**
     * Genera un hashcode non banale
     * @return hashcode
     */
    private int setID() {
        return name.charAt(0) + surName.charAt(0);
    }

    /**
     * Utilizzato per ottenere il nome
     * @return nome
     */
    public String getName(){ return name; }

    /**
     * Utilizzato per modificare il nome
     * @param name nome
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Utilizzato per ottenere il cognome
     * @return cognome
     */
    public String getSurName(){ return surName; }

    /**
     * Utilizzato per modificare il cognome
     * @param surName cognome
     */
    public void setSurName(String surName){this.surName = surName;}

    /**
     * Utilizzato per ottenere il luogo di nascita
     * @return luogo di nascita
     */
    public String getBirthPlace(){ return birthPlace; }

    /**
     * Utilizzato per modificare il luogo di nascita
     * @param birthPlace luogo di nascita
     */
    public void setBirthPlace(String birthPlace){this.birthPlace = birthPlace;}

    /**
     * Utilizzato per ottenere la data di nascita
     * @return data di nascita
     */
    public DateCustom getBirthDay(){ return birthDay; }

    /**
     * Utilizzato per modificare la data di nascita
     * @param birthDay data di nascita
     */
    public void setBirthDay(DateCustom birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * Utilizzato per ottenere la nazionalità
     * @return nazionalità
     */
    public String getNation(){ return nation; }

    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * Utilizzato per ottenere l'email
     * @return email
     */
    public String getEmail(){ return email; }

    /**
     * Utilizzato per ottenere il numero telefonico
     * @return telefono
     */
    public String getPhone(){ return phone; }

    /**
     * Utilizzato per impostare l'email
     * @param email email
     */
    public void setEmail(String email){
        isValidEmail(email);
        this.email = email;
    }

    /**
     * Utilizzato per impostare il recapito telefonico
     * @param phone telefono
     */
    public void setPhone(String phone){
        isValidPhone(phone);
        this.phone = phone;
    }

    /**
     * Utilizzato per verificare la validità dell'email data come parametro
     * @param email email
     */
    private void isValidEmail(String email) throws IllegalArgumentException{
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(email);

        if(!m.matches() || email.isEmpty() || email.isBlank())
            throw new IllegalArgumentException("Email inserita non valida");
    }

    /**
     * Verificare se il numero di telefono è valido
     * @param phone telefono
     */
    private void isValidPhone(String phone) throws IllegalArgumentException{
        if(phone == null)
            phone = "";
        String regex = "^\\d+$";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(phone);

        if(!m.matches() && !(phone.isEmpty() || phone.isBlank()))
            throw new IllegalArgumentException("Telefono inserito non valido");
    }

    /**
     * Utilizzato per visualizzare in stringa la classe
     * @return i campi della classe separati da virgola
     */
    public String toString(){
        return name + "," + surName + "," + birthPlace + "," + birthDay + "," + nation + "," + email + "," + phone;
    }

    /**
     * Verifica che il nome e il cognome non contengano caratteri non permessi
     * @param name    nome
     * @param surName cognome
     */
    private void isValidName(String name, String surName) throws IllegalArgumentException{
        boolean check = true;
        String regex = "^[A-Za-z]+$";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        /*
         * CONTROLLO DEL NOME
         */
        // Regex per verificare il Nome.
        if (name.isEmpty() || name.isBlank())
            check = false;
        else {
            // Pattern class contains matcher() method
            // to find matching between given Nome
            // and regular expression.
            Matcher m = p.matcher(name);
            // Verifica del risultato del match
            if (! m.matches())
                check = false;

            m = p.matcher(surName);

            if(!m.matches()){
                check = false;
            }
        }
        if(!check)
            throw new IllegalArgumentException("Nome o cognome inseriti non validi");
    }

    /**
     * Utilizzato per ottenere tutti i valori della lista separati da punto e virgola
     * @param list lista
     * @return stringa con tutti i valori della lista separati da punto e virgola
     */
    public static String toXML(ArrayList<String> list){
        StringBuilder res = new StringBuilder();
        int i = 0;

        if(list.isEmpty() || list.size() == 1){
            return list.toString().substring(1, list.toString().length() - 1);
        }
        for(String string : list){
            if(i == 0)
                res = new StringBuilder(string);
            else
                res.append(";").append(string);
            i++;
        }
        return res.toString();
    }

    /**
     * Verifica se due istanze di persona hanno campi uguali
     * @param other oggetto
     * @return true se sono uguali, altrimenti false
     */
    public boolean equals(Object other) {
        Person ogg;

        if (!(other instanceof Person)) {
            throw new IllegalArgumentException("Il parametro passato non è di classe Persona");
        }

        ogg = ((Person)other);

        return (this.getName().equals(ogg.getName()) && this.getSurName().equals(ogg.getSurName())
                && this.getBirthPlace().equals(ogg.getBirthPlace())
                && this.getNation().equals(ogg.getNation())
                && this.getEmail().equals(ogg.getEmail())
                && DateCustom.compareTo(this.getBirthDay(), ogg.getBirthDay()) == 0);
    }

    /**
     * Utilizzato per hashing di un oggetto della classe Persona
     * @return hash di Persona
     */
    public int hashCode() {
        return hash;
    }
}
