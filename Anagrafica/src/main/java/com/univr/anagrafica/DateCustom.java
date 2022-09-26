package com.univr.anagrafica;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe per la gestione delle date
 */
public class DateCustom implements Serializable {

    private final int day, month, year;
    private static final int[] DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private static final LocalDate localdate = LocalDate.now();
    private static final int currentYear = localdate.getYear(); //Calendar.getInstance().get(Calendar.YEAR);
    private static final int currentMonth = localdate.getMonthValue();
    private static final int currentDay = localdate.getDayOfMonth();

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * Overload del costruttore, senza parametri ritorna il giorno corrente
     */
    public DateCustom(){
        this(currentDay, currentMonth, currentYear);
    }

    /**
     * Costruttore della classe
     * @param day   giorno
     * @param month mese
     * @param year  anno
     * @throws IllegalArgumentException sse la data inserita non è valida
     */
    public DateCustom(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
        if(!isValid()){
            throw new IllegalArgumentException("La data inserita non è valida");
        }
    }

    /**
     * Ritorno il giorno settato
     * @return int giorno
     */
    public int getDay(){
        return day;
    }

    /**
     * Ritorna il mese settato
     * @return int mese
     */
    public int getMonth(){
        return month;
    }

    /**
     * Ritorna l'anno settato
     * @return int anno
     */
    public int getYear(){
        return year;
    }

    /**
     * Ritorna la data come stringa per la visualizzazione
     * @return Stringa data inserita
     */
    public String toString(){
        String tmpGiorno = day < 10 ? ("0" + day) : Integer.toString(day);
        String tmpMese = month < 10 ? ("0" + month) : Integer.toString(month);

        return tmpGiorno + "/" + tmpMese + "/" + year;
    }

    /**
     * Verifica della correttezza della data inserita
     * @return boolean
     */
    private boolean isValid(){
        if(month <= 0 || month > 12)
            return false;

        if((year < 1950 || year > 9999))
            return false;

        if(day <= 0 || day > daysInMonth())
            return false;

        return true;
    }

    /**
     * Utilità: ritorna il numero di giorni nel mese dato un anno
     * @return int numero di giorni nel mese richiesto
     */
    private int daysInMonth() {
        if (month == 2 && isLeapYear(year))
            return 29;

        return DAYS[month - 1];
    }

    /**
     * Verifica se l'anno è considerato è bisestile o meno
     * @param y anno
     * @return boolean
     */
    private static boolean isLeapYear(int y) {
        return y % 4 == 0 && (y % 100 != 0 || y % 400 == 0);
    }

    /**
     * Verifica se il lavoratore inserito è maggiorenne o meno
     * @param dateCustom la data inserita
     * @param eta eta minima
     * @return boolean
     */
    public static boolean isOlderThan(DateCustom dateCustom, int eta){
        if(currentYear - dateCustom.getYear() != eta){
            return currentYear - dateCustom.getYear() > eta;
        }

        if(currentMonth - dateCustom.getMonth() != 0){
            return currentMonth - dateCustom.getMonth() > 0;
        }

        return currentDay - dateCustom.getDay() >= 0;
    }

    /**
     * Tipico compareTo
     * @param dateCustom1 data1
     * @param dateCustom2 data2
     * @return int >0 sse data1 > data2, <0 sse data1 < data2, 0 sse uguali
     */
    public static int compareTo(DateCustom dateCustom1, DateCustom dateCustom2){
        if(dateCustom1.getYear() - dateCustom2.getYear() != 0){
            return dateCustom1.getYear() - dateCustom2.getYear();
        }
        if(dateCustom1.getMonth() - dateCustom2.getMonth() != 0){
            return dateCustom1.getMonth() - dateCustom2.getMonth();
        }
        if(dateCustom1.getDay() - dateCustom2.getDay() != 0){
            return dateCustom1.getDay() - dateCustom2.getDay();
        }
        return 0;
    }

}
