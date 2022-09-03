package com.univr.anagrafica;

/**
 * Classe contenente costruttore e metodi inerenti i contatti di emergenza dei lavoratori
 */
public class ContattoDiEmergenza extends Persona{
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore di contatto d'emergenza
     * @param nome
     * @param cognome
     * @param email
     * @param telefono
     */
    public ContattoDiEmergenza(String nome, String cognome, String email, String telefono){
        super(nome, cognome, email, telefono);
    }

    /**
     * Metodo utilizzato per visualizzare in stringa la classe
     * @return i campi della classe separati da virgola
     */
    public String toString(){
        return getNome() + "," + getCognome() + "," + getTelefono() + "," + getEmail();
    }

    @Override
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

        return (this.getNome().equals(ogg.getNome())
                && this.getCognome().equals(ogg.getCognome())
                && this.getEmail().equals(ogg.getEmail()));
    }
}
