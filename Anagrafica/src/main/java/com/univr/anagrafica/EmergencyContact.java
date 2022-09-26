package com.univr.anagrafica;

import java.io.Serial;

/**
 * Classe contenente costruttore e metodi inerenti i contatti di emergenza dei lavoratori
 */
public class EmergencyContact extends Person {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore di contatto d'emergenza
     * @param name    nome
     * @param surName cognome
     * @param email   email
     * @param phone   telefono
     */
    public EmergencyContact(String name, String surName, String email, String phone){
        super(name, surName, email, phone);
    }

    /**
     * Metodo utilizzato per visualizzare in stringa la classe
     * @return i campi in formato XML
     */
    public String toString(){
        return getName() + "," + getSurName() + "," + getPhone() + "," + getEmail();
    }

    /**
     * Metodo che verifica se due istanze di persona sono uguali (si applica ai campi, può essere mal funzionante)
     * @param other altro oggetto
     * @return true se sono uguali, altrimenti false
     */
    @Override
    public boolean equals(Object other) {
        Person obj;

        if (!(other instanceof Person)) {
            throw new IllegalArgumentException("Il parametro passato non è di classe Persona");
        }

        obj = ((Person)other);

        return (this.getName().equals(obj.getName())
                && this.getSurName().equals(obj.getSurName())
                && this.getEmail().equals(obj.getEmail()));
    }
}
