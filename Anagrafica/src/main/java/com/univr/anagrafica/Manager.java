package com.univr.anagrafica;

/**
 * Classe contenente i campi e i metodi inerenti i responsabili dell'azienda
 */
public class Manager extends Person {
    private int password;

    /**
     * Costruttore della classe, prende in input solo i dati anagrafici,
     * l'email, il nome utente e password per il login,
     * l'eventuale recapito telefonico va impostato separatamente
     * @param name       nome
     * @param surName    cognome
     * @param birthPlace luogo di nascita
     * @param birthDay   data di nascita
     * @param nation     nazionalità
     * @param email      email
     * @param password   password
     */
    public Manager(String name, String surName, String birthPlace, DateCustom birthDay,
                   String nation, String email, String phone, String password){

        super(name, surName, birthPlace, birthDay, nation, email);

        // Telefono obbligatorio
        if (phone == null || phone.isBlank() || phone.isEmpty())
            throw new IllegalArgumentException("Telefono non inserito");

        super.setPhone(phone);

        // Password obbligatoria
        if (password == null || password.isBlank() || password.isEmpty())
            throw new IllegalArgumentException("Password non inserita");

        this.password = password.hashCode();
    }

    /**
     * Metodo utilizzato per impostare la password del responsabile
     * @param password password
     */
    public void setPassword(String password) { this.password = password.hashCode(); }

    /**
     * Metodo utilizzato per verificare le credenziali di accesso
     * @param password password
     * @return true se le credenziali di accesso sono corrette
     */
    public boolean access(String email, String password){ return email.equals(getEmail()) && password.hashCode() == this.password; }

    /**
     * Metodo per formattare i campi in XML
     * @return i campi della classe separati da virgola
     */
    public String toString(){
        return super.toString() + "," + password;
    }
}