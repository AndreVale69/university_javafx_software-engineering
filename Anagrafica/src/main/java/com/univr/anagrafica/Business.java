package com.univr.anagrafica;

import com.univr.graphics.components.popup.PopupFactory;

import java.io.*;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Singleton per la gestione dell'agenzia
 */
public class Business implements Serializable {
    private final HashSet<Worker> workers = new HashSet<>();
    private final HashSet<Manager> managers = new HashSet<>();
    private static HashSet<Worker> workersBackup = new HashSet<>();
    private static final HashSet<Work> worksBackup = new HashSet<>();
    private static Business instance = null;
    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * Costruttore
     */
    private Business() {}

    /**
     * Utilizzato per ottenere o creare la singola istanza di Agenzia
     * @return Agenzia
     */
    public static Business getInstance(){
        if(instance == null){
            instance = new Business().load();
        }
        return instance;
    }

    /**
     * Utilizzato per impostare la base per una ricerca
     * @param base base
     */
    public static void setWorkersBackup(HashSet<Worker> base) {
        workersBackup = base;
    }

    /**
     * Metodo utilizzato per ottenere il backup della ricerca di lavoratori
     * @return Hashset<Lavoratore>
     */
    public static HashSet<Worker> getWorkersBackup() {
        return workersBackup;
    }

    /**
     * Utilizzato per ottenere il backup dei lavori
     * @return Hashset<Lavoratore>
     */
    public static HashSet<Work> getWorksBackup() {
        return worksBackup;
    }

    /**
     * Utilizzato per aggiungere un lavoratore
     * @param worker lavoratore
     */
    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    /**
     * Utilizzato per eliminare un lavoratore
     * @param worker lavoratore
     */
    public void removeWorker(Worker worker) {
        workers.remove(worker);
    }

    /**
     * Utilizzato per aggiungere un responsabile
     * @param manager responsabile
     */
    public void addManager(Manager manager) {
        managers.add(manager);
    }

    /**
     * Utilizzato per eliminare un responsabile
     * @param manager responsabile
     */
    public void removeManager(Manager manager) {
        managers.remove(manager);
    }

    /**
     * Utilizzato per ottenere l'insieme dei lavoratori registrati
     * @return insieme di lavoratori
     */
    public HashSet<Worker> getWorkers(){
        return workers;
    }

    /**
     * Utilizzato per ottenere l'insieme dei responsabili registrati
     * @return insieme dei responsabili
     */
    public HashSet<Manager> getManagers(){
        return managers;
    }

    /**
     * Utilizzato per effettuare una ricerca sul "database" di lavoratori,
     * riceve come parametro la condizione di ricerca in forma lambda
     * @param condizione condizione lambda
     * @return Collection contenente i lavoratori che soddisfano la condizione data
     */
    public HashSet<Worker> searchCondition(Predicate<Worker> condizione) {
        return workers.stream().filter(condizione).collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Utilizzato per eseguire una ricerca in AND tra l'insieme srcd e la condizione specificata
     * @param srcd       insieme di lavoratori
     * @param condizione condizione
     * @return insieme di lavoratori filtrati
     */
    public HashSet<Worker> andSearch(HashSet<Worker> srcd, Predicate<Worker> condizione) {
        srcd.retainAll(searchCondition(condizione));
        return srcd;
    }

    /**
     * Utilizzato per eseguire una ricerca in OR tra l'insieme srcd e la condizione specificata
     * @param srcd       insieme di lavoratori
     * @param condizione condizione
     * @return insieme di lavoratori filtrati
     */
    public HashSet<Worker> orSearch(HashSet<Worker> srcd, Predicate<Worker> condizione) {
        srcd.addAll(searchCondition(condizione));
        return srcd;
    }

    /**
     * Utilizzato per ottenere i responsabili e i lavoratori come stringa
     * @return stringa
     */
    public String toString(){
        StringBuilder str = new StringBuilder("Lavoratori:\n");

        for(Worker worker : workers)
            str.append(worker).append("\n");

        str.append("Responsabili:\n");

        for(Manager manager : managers)
            str.append(manager).append("\n");

        return str.toString();
    }

    /**
     * Utilizzato per verificare le credenziali d'accesso al sistema
     * @param email email
     * @param psw   password
     * @return true se le credenziali sono giuste, altrimenti false
     */
    public static boolean access(String email, String psw){
        for(Manager resp : instance.getManagers())
            if(resp.access(email, psw))
                return true;
        return false;
    }

    /**
     * Utilizzato per serializzare l'oggetto Agenzia
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
            // System.out.println("Salvataggio avvenuto con successo");
        } catch (Exception e){
            PopupFactory.getPopup("ERROR").createPopup("Errore durante il salvataggio:" + e);
        }
    }

    /**
     * Utilizzato per caricare la serializzazione (<3 Max) di Agenzia
     * @return Agenzia
     */
    private Business load(){

        try{
            File file = new File("src/main/resources/com/univr/anagrafica/obj.ser");
            if(!file.exists()){
                toFile();
            }
            FileInputStream inFile = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(inFile);
            Business loaded = (Business) in.readObject();
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
     * Utilizzato per resettare gli insiemi di agenzia con campi default
     * TODO: SOLO PER TEST IN FASE DI SVILUPPO
     */
    public void reset() {
        // Resp: gimmy
        Manager gimmy = new Manager("Gimmy", "Forte", "Trincea",
                new DateCustom(1, 1, 1990), "Botswana",
                "t@t.t", "123456789", "1");

        // Lav: max
        Worker max = new Worker("Max", "Renso", "Verona",
                new DateCustom(27, 5, 1995), "Italia", "Viale Pivetti 48", "massimiliano.renso@gmail.com");

        max.resetLanguages("Italiana", "Britannica");
        max.setCarOwner("Sì");
        max.resetDriveLicense("A1");
        max.setPhone("6666666666");
        max.addAvailability(new DateCustom(10,7,2023), new DateCustom(10, 8, 2023));
        max.addAvailability(new DateCustom(10,8,2023), new DateCustom(10, 9, 2023));
        max.addAvailability(new DateCustom(10,10,2023), new DateCustom(10, 11, 2023));
        max.resetZones("Affi", "Bardolino", "Villafranca di Verona", "Garda");

        max.addEmergencyContact(new EmergencyContact("Andrea", "Valentini", "andre.vale@lol.it", ""));
        max.addEmergencyContact(new EmergencyContact("Andreaa", "Valentini", "andre.vale@lol.it", ""));
        max.addEmergencyContact(new EmergencyContact("AndreaaAaAaAa", "Valentini", "andre.vale@lol.it", ""));

        max.addWork(new Work(new DateCustom(10,7,2022), new DateCustom(10, 8, 2023), "NVidia", "Ricercatore ;studente; stagista ; Giardiniere", "Varona", "123"));

        // Lav: marcy
        Worker marcy = new Worker("Marcello", "Lucati", "Altamura",
                new DateCustom(24, 6, 1998), "Kuwait", "Piazza Alberto 28", "marcel.lucat@gmail.com");

        marcy.resetLanguages("Cambogiana", "Britannica");
        marcy.setCarOwner("No");
        marcy.resetDriveLicense("D1E");
        marcy.setPhone("6666666666");
        marcy.addAvailability(new DateCustom(20,7,2023), new DateCustom(30, 7, 2023));
        marcy.addAvailability(new DateCustom(20,8,2023), new DateCustom(30, 8, 2023));
        marcy.addAvailability(new DateCustom(20,9,2023), new DateCustom(30, 9, 2023));
        marcy.resetZones("Erbezzo", "illasi", "San Bonifacio", "Terrazzo");

        marcy.addEmergencyContact(new EmergencyContact("Chiama", "Il", "c.l@lol.it", "118"));

        marcy.addWork(new Work(new DateCustom(13,7,2022), new DateCustom(17, 8, 2022), "Monsters.inc", "Spaventatore", "Universo parallelo", "2"));
        marcy.addWork(new Work(new DateCustom(24,9,2023), new DateCustom(10, 10, 2023), "Ribbon Army", "Razziatore ; stagista", "Namek", "5"));
        marcy.addWork(new Work(new DateCustom(10,6,2022), new DateCustom(2, 7, 2022), "Mia madre", "Giardiniere ; lavapiatti ; addetto pulizie ; servizio mensa", "Campagna", "0"));

        // Lav: matteo
        Worker matteo = new Worker("Matteo", "Cugini", "Altamura",
                new DateCustom(1, 8, 2002), "Sudafrica", "Via Medioevo 17", "matty.c97@gmail.com");

        matteo.resetLanguages("Islandese", "Italiana");
        matteo.setCarOwner("No");
        matteo.resetDriveLicense("B1");
        matteo.setPhone("6666666666");
        matteo.addAvailability(new DateCustom(11,7,2023), new DateCustom(18, 7, 2023));
        matteo.addAvailability(new DateCustom(14,6,2023), new DateCustom(30, 6, 2023));
        matteo.addAvailability(new DateCustom(15,8,2023), new DateCustom(15, 9, 2023));
        matteo.resetZones("Erbezzo", "Affi", "Sanguinetto", "Terrazzo");

        matteo.addEmergencyContact(new EmergencyContact("Lamico", "DiBari", "focaccia.barese@buona.it", "7777777777"));

        matteo.addWork(new Work(new DateCustom(13,7,2022), new DateCustom(17, 8, 2022), "Alfredo", "Pizzaiolo ; Fattorino ; schiavo", "Luogo dimenticato", "3"));
        matteo.addWork(new Work(new DateCustom(24,9,2023), new DateCustom(10, 10, 2023), "Università degli studi del Sannio", "ricercatore ; depresso", "Avalon", "0"));
        matteo.addWork(new Work(new DateCustom(10,6,2022), new DateCustom(2, 7, 2022), "Mia madre", "lavapiatti ; uomo delle pulizie ; cuoco ;sperimentatore di vite umane; disorganizzatore si stanze", "campo di battaglia", "0"));

        // Lav: gian
        Worker gian = new Worker("Gian", "Destrorso", "Taranto",
                new DateCustom(1, 10, 2002), "Palestina", "Via Pescivendolo 22", "pokemon.fire22@water.com");

        gian.resetLanguages("Islandese", "maltese", "Britannica");
        gian.setCarOwner("No");
        gian.resetDriveLicense("B1");
        gian.setPhone("6666666666");
        gian.addAvailability(new DateCustom(27,9,2023), new DateCustom(12, 10, 2023));
        gian.addAvailability(new DateCustom(23,8,2023), new DateCustom(15, 9, 2023));
        gian.addAvailability(new DateCustom(18,9,2023), new DateCustom(23, 9, 2023));
        gian.resetZones("Selva di Progno", "Affi", "San Bonifacio", "Garda");

        gian.addEmergencyContact(new EmergencyContact("Vtucc", "Gang", "ctrl.alt@canc.it", "7777777777"));

        gian.addWork(new Work(new DateCustom(13,7,2022), new DateCustom(17, 8, 2022), "Politecnico di Bari", "depresso ; istigatore di masse ; ricercatore", "BR", "3"));
        gian.addWork(new Work(new DateCustom(24,9,2023), new DateCustom(10, 10, 2023), "Fruttivendolo da Monica", "commercialista ; contabile ; testimonial", "Somewhere in Puglia", "0"));
        gian.addWork(new Work(new DateCustom(10,6,2022), new DateCustom(2, 7, 2022), "BN vacanze", "fornitore di deodoranti ; coccolatore di cani ; terrore dei gatti; curatore di braci", "Narnia", "0"));

        // Lav: vito
        Worker vito = new Worker("Vito", "Appendiabiti", "Gallia",
                new DateCustom(3, 11, 2002), "RD del Congo", "Via Disegno 23", "mnghcc.marang@gmail.com");

        vito.resetLanguages("Italiana", "Messicana", "Brasiliana");
        vito.setCarOwner("No");
        vito.resetDriveLicense("B1");
        vito.setPhone("6666666666");
        vito.addAvailability(new DateCustom(11,7,2023), new DateCustom(18, 7, 2023));
        vito.addAvailability(new DateCustom(14,6,2023), new DateCustom(30, 6, 2023));
        vito.addAvailability(new DateCustom(15,8,2023), new DateCustom(15, 9, 2023));
        vito.resetZones("Selva di Progno", "Sanguinetto", "illasi", "Garda");

        vito.addEmergencyContact(new EmergencyContact("Ciccio", "Lina", "netti.not@funny.it", "7777777777"));

        vito.addWork(new Work(new DateCustom(13,7,2022), new DateCustom(17, 8, 2022), "Politecnico di Bari", "arraffatore di voti ; abbassatore di aspettative ; disegnatore", "BR", "3"));
        vito.addWork(new Work(new DateCustom(24,9,2023), new DateCustom(10, 10, 2023), "XXX.inc", "commercialista ; contabile ; rubacuori", "Su internet (voce di Timmy Turner)", "10000000"));
        vito.addWork(new Work(new DateCustom(10,6,2022), new DateCustom(2, 7, 2022), "BN vacanze", "russatore di notti ; coccolatore di cani ; terrore dei gatti; criticatore di braciole", "Moria", "0"));

        // Lav: valeria
        Worker valeria = new Worker("Valeria", "Stecchino", "Mesopotamia",
                new DateCustom(13, 9, 2002), "Birmania", "Via Novigrad 23", "vale.unadelledue@gmail.com");

        valeria.resetLanguages("Italiana", "Statunitense", "Islandese");
        valeria.setCarOwner("No");
        valeria.resetDriveLicense("B1");
        valeria.setPhone("6666666666");
        valeria.addAvailability(new DateCustom(11,6,2023), new DateCustom(18, 6, 2023));
        valeria.addAvailability(new DateCustom(14,7,2023), new DateCustom(30, 7, 2023));
        valeria.addAvailability(new DateCustom(15,8,2023), new DateCustom(15, 9, 2023));
        valeria.resetZones("Erbezzo", "Castagnaro", "Villafranca di Verona", "Terrazzo");

        valeria.addEmergencyContact(new EmergencyContact("Matto", "Cute", "non.intendo@carino.it", "7777777777"));

        valeria.addWork(new Work(new DateCustom(13,7,2022), new DateCustom(17, 8, 2022), "Università degli studi del Sannio", "studiatrice seriale ; accalappiatrice di altamuresi ; stagista", "BN", "3"));
        valeria.addWork(new Work(new DateCustom(24,9,2023), new DateCustom(10, 10, 2023), "Nuova Yorka", "visitatrice di parchi ; cacciatrice di topi ; assaggiatrice di pizze", "La Merica", "1"));
        valeria.addWork(new Work(new DateCustom(10,6,2022), new DateCustom(2, 7, 2022), "BN vacanze", "Organizzatrice di serate ; Ammaestratrice di cani ; terrore dei gatti; Spalmatrice di dentifrici", "Corea del Nord", "0"));

        workers.clear();
        managers.clear();

        this.addWorker(max);
        this.addWorker(marcy);
        this.addWorker(matteo);
        this.addWorker(gian);
        this.addWorker(vito);
        this.addWorker(valeria);
        this.addManager(gimmy);

        this.toFile();
    }
}
