package com.univr.anagrafica;

/**
 * Classe contenente i campi e i metodi inerenti i responsabili dell'azienda
 */
public class Responsabile extends Persona {
    private int password;

    /**
     * Costruttore della classe, prende in input solo i dati anagrafici,
     * l'email, il nome utente e password per il login,
     * l'eventuale recapito telefonico va impostato separatamente
     * @param nome
     * @param cognome
     * @param luogoDiNascita
     * @param nascita
     * @param nazionalita
     * @param email
     * @param password
     */
    public Responsabile(String nome, String cognome, String luogoDiNascita, Data nascita,
                        String nazionalita, String email, String password){

        super(nome, cognome, luogoDiNascita, nascita, nazionalita, email);
        this.password = password.hashCode();
    }

    /**
     * Metodo utilizzato per ottenere la password del responsabile
     * @return password
     */
    public int getPassword(){
        return password;
    }

    /**
     * Metodo utilizzato per impostare la password del responsabile
     * @param password
     */
    public void setPassword(String password) { this.password = password.hashCode(); }

    /**
     * Metodo utilizzato per verificare le credenziali di accesso
     * @param password
     * @return true se le credenziali di accesso sono corrette
     */
    public boolean accesso(String email, String password){ return email.equals(getEmail()) && password.hashCode() == this.password; }

    /**
     * Metodo utilizzato per visualizzare in stringa la classe
     * @return i campi della classe separati da virgola
     */
    public String toString(){
        return super.toString() + "," + password;
    }
}