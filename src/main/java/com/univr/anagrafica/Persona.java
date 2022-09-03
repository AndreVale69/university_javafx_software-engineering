package com.univr.anagrafica;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe che contiene i campi condivisi da responsabili e lavoratori e i metodi per accedervi
 */
public abstract class Persona implements Serializable {
    private final String nome, cognome;
    private String telefono = "", luogoDiNascita, nazionalita, email;
    private Data nascita;
    int hash;
    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * Costruttore della classe, prende in input solo i dati anagrafici e l'email,
     * l'eventuale recapito telefonico va impostato separatamente
     * @param nome
     * @param cognome
     * @param luogoDiNascita
     * @param nascita
     * @param nazionalita
     * @param email
     */
    Persona(String nome, String cognome, String luogoDiNascita, Data nascita, String nazionalita, String email){
        isValidNome(nome, cognome);
        this.nome = nome;
        this.cognome = cognome;
        if (luogoDiNascita.isBlank() || luogoDiNascita.isEmpty())
            throw new IllegalArgumentException("Luogo di nascita non inserito");
        this.luogoDiNascita = luogoDiNascita;
        if (nazionalita == null)
            throw new IllegalArgumentException("Nazionalità non inserita");
        this.nazionalita = nazionalita;

        if (!Data.isOlderThan(nascita, 18))
            throw new IllegalArgumentException("Età minore di 18 anni");
        this.nascita = nascita;

        isValidEmail(email);
        this.email = email;

        this.hash = setID();
    }

    /**
     * Costruttore della classe, prende in input solo i dati del contatto d'emergenza
     * @param nome
     * @param cognome
     * @param email
     */
    Persona(String nome, String cognome, String email, String telefono){
        isValidNome(nome, cognome);
        this.nome = nome;
        this.cognome = cognome;
        isValidEmail(email);
        this.email = email;
        isValidTelefono(telefono);
        this.telefono = telefono;

        this.hash = setID();
    }

    private int setID() {
        // Genera un hashcode non banale
        return nome.charAt(0) + cognome.charAt(0);
    }

    /**
     * Metodo utilizzato per ottenere il nome
     * @return nome
     */
    public String getNome(){ return nome; }

    /**
     * Metodo utilizzato per ottenere il cognome
     * @return cognome
     */
    public String getCognome(){ return cognome; }

    /**
     * Metodo utilizzato per ottenere il luogo di nascita
     * @return luogo di nascita
     */
    public String getLuogoDiNascita(){ return luogoDiNascita; }

    /**
     * Metodo utilizzato per ottenere la data di nascita
     * @return data di nascita
     */
    public Data getNascita(){ return nascita; }

    /**
     * Metodo utilizzato per ottenere la nazionalità
     * @return nazionalità
     */
    public String getNazionalita(){ return nazionalita; }

    /**
     * Metodo utilizzato per ottenere l'email
     * @return email
     */
    public String getEmail(){ return email; }

    /**
     * Metodo utilizzato per ottenere il numero telefonico
     * @return telefono
     */
    public String getTelefono(){ return telefono; }

    /**
     * Metodo utilizzato per impostare l'email
     * @param email
     */
    public void setEmail(String email){
        isValidEmail(email);
        this.email = email;
    }

    /**
     * Metodo utilizzato per impostare il recapito telefonico
     * @param telefono
     */
    public void setTelefono(String telefono){
        isValidTelefono(telefono);
        this.telefono = telefono;
    }

    /**
     * Metodo utilizzato per verificare la validità dell'email data come parametro
     * @param email
     * @return true se l'email rispetta la formattazione corretta, false altrimenti
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
     * Metodo per verificare se il numero di telefono è valido
     * @param telefono
     */
    private void isValidTelefono(String telefono) throws IllegalArgumentException{
        if(telefono == null)
            telefono = "";
        String regex = "^\\d+$";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(telefono);

        if(!m.matches() && !(telefono.isEmpty() || telefono.isBlank()))
            throw new IllegalArgumentException("Telefono inserito non valido");
    }

    /**
     * Metodo utilizzato per visualizzare in stringa la classe
     * @return i campi della classe separati da virgola
     */
    public String toString(){
        return nome + "," + cognome + "," + luogoDiNascita + "," + nascita + "," + nazionalita + "," + email + "," + telefono;
    }

    public void dumpToFile(){

    }

    /**
     * Metodo che verifica che il nome e il cognome non contengano caratteri non permessi
     * @param nome
     * @param cognome
     * @throws IllegalArgumentException
     */
    public void isValidNome(String nome, String cognome) throws IllegalArgumentException{
        boolean check = true;
        String regex = "^[A-Za-z]+$";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        /**
         * CONTROLLO DEL NOME
         **/
        // Regex per verificare il Nome.
        if (nome.isEmpty() || nome.isBlank())
            check = false;
        else {
            // Pattern class contains matcher() method
            // to find matching between given Nome
            // and regular expression.
            Matcher m = p.matcher(nome);
            // Verifica del risultato del match
            if (! m.matches())
                check = false;

            m = p.matcher(cognome);

            if(!m.matches()){
                check = false;
            }
        }
        if(!check){
            throw new IllegalArgumentException("Nome o cognome inseriti non validi");
        }
    }

    /**
     * Metodo utilizzato per ottenere tutti i valori della lista separati da punto e virgola
     * @param list
     * @return stringa con tutti i valori della lista separati da punto e virgola
     */
    public static String toXML(ArrayList<String> list){
        StringBuilder res = new StringBuilder();
        int i = 0;

        if(list.isEmpty() || list.size() == 1){
            return list.toString().substring(1, list.toString().length() - 1);
        }
        for(String string : list){
            if(i == 0){
                res = new StringBuilder(string);
                i++;
            } else {
                res.append(";").append(string);
                i++;
            }
        }
        return res.toString();
    }

    /**
     * Metodo che verifica se due istanze di persona hanno campi uguali
     * @param other
     * @return
     */
    public boolean equals(Object other) {
        Persona ogg;

        if (!(other instanceof Persona)) {
            throw new IllegalArgumentException("Il parametro passato non è di classe Persona");
        }

        ogg = ((Persona)other);

        return (this.getNome().equals(ogg.getNome()) && this.getCognome().equals(ogg.getCognome())
                && this.getLuogoDiNascita().equals(ogg.getLuogoDiNascita())
                && this.getNazionalita().equals(ogg.getNazionalita())
                && this.getEmail().equals(ogg.getEmail())
                && Data.compareTo(this.getNascita(), ogg.getNascita()) == 0);
    }

    /**
     * Metodo utilizzato per l'hashing di un oggetto della classe Persona
     * @return
     */
    public int hashCode() {
        return hash;
    }
}
