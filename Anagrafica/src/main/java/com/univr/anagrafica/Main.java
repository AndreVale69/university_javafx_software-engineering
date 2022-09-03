package com.univr.anagrafica;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main{
    public static void main(String[] args){

        String res = "";

        Responsabile gimmy = new Responsabile("Vercingetorige", "Daroma", "boh",
                new Data(1, 1, 1990), "Nigerianno",
                "t@t.t", "1");

        Lavoratore max = new Lavoratore("Max", "Renso", "Verona",
                new Data(27, 5, 1995), "Italiana", "Viale Mandalculo", "Massimiliano.renso@gmail.com");

        try{
            Lavoratore max2 = new Lavoratore("Max1", "Renso", "Verona",
                    new Data(27, 5, 1995), "Italiana", "Viale Mandalculo", "Massimiliano.renso@gmail.com");
        } catch (Exception e){
            System.out.println("Max1 va in errore");
        }

        max.resetLingue("Italiano", "Inglese");
        max.setAutomunito("Sì");
        max.resetTipoPatente("A1");
        max.setTelefono("6666666666");
        max.addDisponibilita(new Data(10,7,2022), new Data(10, 8, 2022));
        max.addDisponibilita(new Data(10,8,2022), new Data(10, 9, 2022));
        max.addDisponibilita(new Data(10,10,2022), new Data(10, 11, 2022));
        max.resetZone("Verona", "Bologna", "Modena", "Genova");

        max.addContattoEmergenza(new ContattoDiEmergenza("Andrea", "Valentini", "andre-vale@lol.it", null));
        max.addContattoEmergenza(new ContattoDiEmergenza("Andreaa", "Valentini", "andre-vale@lol.it", null));
        max.addContattoEmergenza(new ContattoDiEmergenza("AndreaaAaAaAa", "Valentini", "andre-vale@lol.it", null));

        for(Persona persona : max.getContattiEmergenza()){
            res += persona + " ";
        }

        System.out.println(gimmy);
        System.out.println(max);
        System.out.println(res);
        System.out.println(gimmy.accesso("gianmaria.forte@gmail.com", "peperoni01."));
        System.out.println(gimmy.accesso("gianmaria.forte@gmail.com", "LOLOLOLOLOL"));
        System.out.println(gimmy.accesso("gianmaria.forte@diocan.com", "peperoni01."));

        /*
            Scrittura e lettura su/da file, test
         */
        Path path = Paths.get(System.getProperty("user.dir") + "\\test.txt");
        System.out.println("Salva in: " + path);
        String str = "ciao come stai? questo è un test di scrittura";
        byte[] toSave = str.getBytes();

        try{
            Files.write(path, toSave);
        }
        catch(IOException ex){
            System.out.println("Path non valido: " + path);
        }

        String letto = "";
        try {
            letto = Files.readString(path);
        } catch(IOException ex){
            System.out.println("Errore nella lettura");
        }

        System.out.println("Lettura: " + letto);

        Agenzia agenzia = Agenzia.getInstance();

        agenzia.addLavoratore(max);
        agenzia.addResponsabile(gimmy);

        System.out.println(agenzia);

        agenzia.toFile();

        Agenzia loaded = Agenzia.getInstance();
        System.out.println(loaded);

        for(Lavoratore lavoratore : loaded.getLavoratori()){
            System.out.println(lavoratore.getContattiEmergenza());
        }

        agenzia.addLavoratore(new Lavoratore("ciccio", "bastardo", "Verona", new Data(25, 7, 1989), "Italiano",
                "Via le grazie 15", "puffopallo@test.com"));

        agenzia.toFile();
    }
}
