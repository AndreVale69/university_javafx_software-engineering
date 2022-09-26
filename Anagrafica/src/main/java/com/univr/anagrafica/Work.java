package com.univr.anagrafica;

import java.io.Serial;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe per
 */
public class Work implements Serializable {
    private final DateCustom[] period = new DateCustom[2];
    private String company, tasks, place, pay;
    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * Costruttore della classe lavoro
     * @param dateCustomStart periodo d'inizio
     * @param dateCustomEnd   periodo di fine
     * @param company         azienda
     * @param tasks           mansioni
     * @param place           luogo
     * @param pay             retribuzione lorda
     */
    public Work(DateCustom dateCustomStart, DateCustom dateCustomEnd, String company, String tasks, String place, String pay) {
        setPeriod(dateCustomStart, dateCustomEnd);
        setCompany(company);
        setTasks(tasks);
        setPlace(place);
        setPay(pay);
    }

    /**
     * Metodo per impostare il periodo di lavoro
     * @param dateCustomStart periodo d'inizio
     * @param dateCustomEnd   periodo di fine
     */
    public void setPeriod(DateCustom dateCustomStart, DateCustom dateCustomEnd) {
        if (dateCustomStart == null)
            throw new IllegalArgumentException("La data di inizio non è stata inserita");

        if (dateCustomEnd == null)
            throw new IllegalArgumentException("La data di fine non è stata inserita");

        if (DateCustom.compareTo(dateCustomStart, dateCustomEnd) > 0)
            throw new IllegalArgumentException("La data di inizio del periodo è maggiore della data in cui termina");

        if (DateCustom.isOlderThan(dateCustomEnd, 5))
            throw new IllegalArgumentException("Il lavoro stagionale ha avuto luogo più di 5 anni fa");

        period[0] = dateCustomStart;
        period[1] = dateCustomEnd;
    }

    /**
     * Metodo per impostare l'azienda di lavoro
     * @param company azienda
     */
    public void setCompany(String company) {
        if (company == null || company.isEmpty() || company.isBlank())
            throw new IllegalArgumentException("L'azienda non può essere vuota");

        this.company = company;
    }

    /**
     * Metodo per impostare la mansione di lavoro
     * @param tasks mansioni
     */
    public void setTasks(String tasks) {
        if (tasks == null || tasks.isEmpty() || tasks.isBlank())
            throw new IllegalArgumentException("La mansione non può essere vuota");

        this.tasks = tasks;
    }

    /**
     * Metodo per impostare il luogo di lavoro
     * @param place luogo di lavoro
     */
    public void setPlace(String place) {
        if (place == null || place.isEmpty() || place.isBlank())
            throw new IllegalArgumentException("Il luogo non può essere vuoto");

        this.place = place;
    }

    /**
     * Metodo per impostare la retribuzione di lavoro
     * @param pay retribuzione lorda
     */
    public void setPay(String pay) {
        isValidPay(pay);
        this.pay = pay;
    }

    /**
     * Metodo per ottenere il periodo di lavoro
     * @return periodo di lavoro
     */
    public DateCustom[] getPeriod() {
        return period;
    }

    /**
     * Metodo per ottenere l'azienda di lavoro
     * @return azienda di lavoro
     */
    public String getCompany() {
        return company;
    }

    /**
     * Metodo per ottenere la mansione di lavoro
     * @return mansione di lavoro
     */
    public String getTasks() {
        return tasks;
    }

    /**
     * Metodo per ottenere il luogo di lavoro
     * @return luogo di lavoro
     */
    public String getPlace() {
        return place;
    }

    /**
     * Metodo per ottenere la retribuzione di lavoro
     * @return retribuzione di lavoro
     */
    public String getPay() {
        return pay;
    }

    /**
     * Metodo utilizzato per visualizzare in stringa la classe
     * @return i campi della classe separati da virgola
     */
    public String toString () {
        return "" + period[0] + "-" + period[1] + "," + company + "," + tasks + "," + place + "," + pay;
    }

    /**
     * Metodo per verificare se la retribuzione è valida
     * @param pay retribuzione lorda
     */
    private void isValidPay(String pay) throws IllegalArgumentException{
        String regex = "^\\d+$";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(pay);

        if(!m.matches())
            throw new IllegalArgumentException("Retribuzione inserita non valida");
    }

    /**
     * Verifica se due istanze di lavoro hanno campi uguali
     * @param other oggetto
     * @return true se sono uguali, altrimenti false
     */
    public boolean equals(Object other) {
        Work ogg;

        if (!(other instanceof Work)) {
            throw new IllegalArgumentException("Il parametro passato non è di classe Persona");
        }

        ogg = ((Work)other);

        return (company.equals(ogg.getCompany())
                && place.equals(ogg.getPlace())
                && tasks.equals(ogg.getTasks())
                && pay.equals(ogg.getPay())
                && DateCustom.compareTo(period[0], ogg.getPeriod()[0]) == 0
                && DateCustom.compareTo(period[1], ogg.getPeriod()[1]) == 0);
    }

    /**
     * Utilizzato per hashing di un oggetto della classe Lavoro
     * @return hash
     */
    public int hashCode() {
        return company.charAt(0) + place.charAt(0);
    }
}
