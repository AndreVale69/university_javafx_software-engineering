package com.univr.anagrafica;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Classe per il DTO del Lavoratore
 */
public class LavoratoreDTO {
    public String nome, cognome, luogoDiNascita, nazionalita, email, indirizzo, telefono;
    public Data nascita;
    public ArrayList<String> tipoPatente;
    public ArrayList<String> lingue;
    public boolean automunito;
    public Data[][] disponibilita;
    public ArrayList<String> zone;
    public HashSet<ContattoDiEmergenza> contattiEmergenza;
    public ArrayList<String> esperienzeLavorative;

    /**
     * Costruttore del Data Transfer Object di Lavoratore
     * @param nome
     * @param cognome
     * @param luogoDiNascita
     * @param nazionalita
     * @param email
     * @param indirizzo
     * @param telefono
     * @param nascita
     * @param tipoPatente
     * @param lingue
     * @param automunito
     * @param disponibilita
     * @param zone
     * @param contattiEmergenza
     * @param esperienzeLavorative
     */
    public LavoratoreDTO (String nome, String cognome, String luogoDiNascita, String nazionalita, String email,
                          String indirizzo, String telefono, Data nascita, ArrayList<String> tipoPatente,
                          ArrayList<String> lingue, boolean automunito, Data[][] disponibilita,
                          ArrayList<String> zone, HashSet<ContattoDiEmergenza> contattiEmergenza,
                          ArrayList<String> esperienzeLavorative) {
        this.nome = nome;
        this.cognome = cognome;
        this.luogoDiNascita = luogoDiNascita;
        this.nazionalita = nazionalita;
        this.email = email;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.nascita = nascita;
        this.tipoPatente = tipoPatente;
        this.lingue = lingue;
        this.automunito = automunito;
        this.disponibilita = disponibilita;
        this.zone = zone;
        this.contattiEmergenza = contattiEmergenza;
        this.esperienzeLavorative = esperienzeLavorative;
    }
}
