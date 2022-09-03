package com.univr.anagrafica;

import java.util.HashSet;

public class Main2 {
    public static void main(String[] args){
        Agenzia agenzia = Agenzia.getInstance();

        agenzia.addLavoratore(new Lavoratore("ciccio", "bastardo", "Verona", new Data(25, 7, 1989), "Italiano",
               "Via le grazie 15", "puffopallo@test.com"));
        agenzia.addLavoratore(new Lavoratore("ciccio", "bastardo", "Verona", new Data(25, 7, 1989), "Italiano",
               "Via le grazie 15", "puffopallo@test.com"));
        agenzia.addLavoratore(new Lavoratore("ciccio", "bastardo", "Verona", new Data(25, 7, 1989), "Italiano",
                "Via le grazie 15", "puffopallo@test.com"));

        System.out.println("peperoni01.".hashCode());

        for ( Responsabile responsabile : agenzia.getResponsabili()) {
            System.out.println(responsabile.accesso("gianmaria.forte@gmail.com", "peperoni01."));
        }

        System.out.println(agenzia.getLavoratori());

        System.out.println(agenzia.getResponsabili());

        agenzia.toFile();
    }
}
