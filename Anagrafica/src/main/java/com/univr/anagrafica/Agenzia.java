package com.univr.anagrafica;

import java.io.*;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Classe per la creazione gestione dell'oggetto azienda (è un Singleton)
 */
public class Agenzia implements Serializable {
    private HashSet<Lavoratore> lavoratori = new HashSet<>();
    private HashSet<Responsabile> responsabili = new HashSet<>();
    private static HashSet<Lavoratore> lavoratoriBackup = new HashSet<>();
    private static Agenzia instance = null;
    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * Metodo per creazione nuovo oggetto vuoto
     */
    private Agenzia() {}

    /**
     * Metodo che restituisce o crea la singola istanza di Agenzia
     * @return
     */
    public static Agenzia getInstance(){
        if(instance == null){
            instance = new Agenzia().load();
        }
        return instance;
    }

    /**
     * Metodo per impostare la base per una ricerca
     * @param base
     */
    public static void setLavoratoriBackup(HashSet<Lavoratore> base) {
        lavoratoriBackup = base;
    }

    /**
     * Metodo utilizzato per ottenere la base per una ricerca
     * @return
     */
    public static HashSet<Lavoratore> getLavoratoriBackup() {
        return lavoratoriBackup;
    }

    /**
     * Metodo per aggiungere un lavoratore
     * @param lavoratore
     */
    public void addLavoratore(Lavoratore lavoratore) {
        lavoratori.add(lavoratore);
    }

    /**
     * Metodo per eliminare un lavoratore
     * @param lavoratore
     */
    public void delLavoratore(Lavoratore lavoratore) {
        lavoratori.remove(lavoratore);
    }

    /**
     * Metodo per aggiungere un responsabile
     * @param responsabile
     */
    public void addResponsabile(Responsabile responsabile) {
        responsabili.add(responsabile);
    }

    /**
     *
     * @return
     */
    public HashSet<Lavoratore> getLavoratori(){
        return lavoratori;
    }

    /**
     *
     * @return
     */
    public HashSet<Responsabile> getResponsabili(){
        return responsabili;
    }

    /**
     * Metodo utilizzato per effettuare una ricerca sul "database" di lavoratori,
     * riceve come parametro la condizione di ricerca in forma lambda
     * @param condizione
     * @return Collection contenente i lavoratori che soddisfano la condizione data
     */
    public HashSet<Lavoratore> searchCondition(Predicate<Lavoratore> condizione) {
        return lavoratori.stream().filter(condizione).collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Metodo utilizzato per eseguire una ricerca in AND tra l'insieme srcd e la condizione specificata
     * @param srcd
     * @param condizione
     * @return
     */
    public HashSet<Lavoratore> andSearch(HashSet<Lavoratore> srcd, Predicate<Lavoratore> condizione) {
        srcd.retainAll(searchCondition(condizione));
        return srcd;
    }

    /**
     * Metodo utilizzato per eseguire una ricerca in OR tra l'insieme srcd e la condizione specificata
     * @param srcd
     * @param condizione
     * @return
     */
    public HashSet<Lavoratore> orSearch(HashSet<Lavoratore> srcd, Predicate<Lavoratore> condizione) {
        srcd.addAll(searchCondition(condizione));
        return srcd;
    }

    public String toString(){
        String str = "Lavoratori:\n";

        for(Lavoratore lavoratore : lavoratori){
            str += lavoratore + "\n";
        }

        str += "Responsabili:\n";

        for(Responsabile responsabile : responsabili){
            str += responsabile + "\n";
        }

        return str;
    }

    public static boolean accesso(String email, String psw){
        for(Responsabile resp : instance.getResponsabili()){
            if(resp.accesso(email, psw))
                return true;
        }
        return false;
    }

    /**
     * Metodo per serializzare l'oggetto Agenzia
     */
    public void toFile(){

        try {
            File file = new File("src/main/resources/com/univr/anagrafica/obj.ser");
            // Crea il percorso se non completo
            file.getParentFile().mkdirs();
            FileOutputStream outFile = new FileOutputStream(file, false);
            ObjectOutputStream out = new ObjectOutputStream(outFile);
            out.writeObject(this);
            out.close();
            outFile.close();
            System.out.println("Salvataggio avvenuto con successo");
        } catch (Exception e){
            System.out.println("Errore durante il salvataggio:" + e);
        }
    }

    /**
     * Metodo per caricare la serializzazione (<3 Max) di Agenzia
     * @return
     */
    private Agenzia load(){

        try{
            File file = new File("src/main/resources/com/univr/anagrafica/obj.ser");
            if(!file.exists()){
                toFile();
            }
            FileInputStream inFile = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(inFile);
            Agenzia loaded = (Agenzia) in.readObject();
            in.close();
            inFile.close();
            return loaded;
        } catch (Exception e){
            System.out.println("errore durante il caricamento: " + e);
            System.out.println("Creazione di un nuovo salvataggio...");
            this.reset();
            System.out.println("Caricamento nuovo salvataggio...");
            return getInstance();
        }
    }

    /**
     * Resetta agenzia con campi default
     */
    public void reset() {
        Responsabile gimmy = new Responsabile("Vercingetorige", "Daroma", "boh",
                new Data(1, 1, 1990), "Nigerianno",
                "t@t.t", "1");

        Lavoratore max = new Lavoratore("Max", "Renso", "Verona",
                new Data(27, 5, 1995), "Italiana", "Viale Mandalculo", "Massimiliano.renso@gmail.com");

        max.resetLingue("Italiano", "Inglese");
        max.setAutomunito("Sì");
        max.resetTipoPatente("A1");
        max.setTelefono("6666666666");
        max.addDisponibilita(new Data(10,7,2022), new Data(10, 8, 2023));
        max.addDisponibilita(new Data(10,8,2022), new Data(10, 9, 2023));
        max.addDisponibilita(new Data(10,10,2022), new Data(10, 11, 2023));
        max.resetZone("Verona", "Bologna", "Modena", "Genova");

        max.addContattoEmergenza(new ContattoDiEmergenza("Andrea", "Valentini", "andre-vale@lol.it", null));
        max.addContattoEmergenza(new ContattoDiEmergenza("Andreaa", "Valentini", "andre-vale@lol.it", null));
        max.addContattoEmergenza(new ContattoDiEmergenza("AndreaaAaAaAa", "Valentini", "andre-vale@lol.it", null));

        lavoratori.clear();
        responsabili.clear();

        this.addLavoratore(max);
        this.addResponsabile(gimmy);

        this.toFile();
    }
}
