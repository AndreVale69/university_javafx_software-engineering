package com.univr.anagrafica;

import javafx.scene.text.Text;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe per la gestione delle date
 */
public class Data implements Serializable {

    private final int giorno, mese, anno;
    private static final int[] DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private static LocalDate localdate = LocalDate.now();
    private static int annoCorrente = localdate.getYear(); //Calendar.getInstance().get(Calendar.YEAR);
    private static int meseCorrente = localdate.getMonthValue();
    private static int giornoCorrente = localdate.getDayOfMonth();

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * Overload del costruttore, senza parametri ritorna il giorno corrente
     */
    public Data(){
        this(giornoCorrente, meseCorrente, annoCorrente);
    }

    /**
     * Costruttore della classe
     * @param giorno
     * @param mese
     * @param anno
     * @throws IllegalArgumentException sse la data inserita non è valida
     */
    public Data(int giorno, int mese, int anno){
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
        if(!isValid()){
            throw new IllegalArgumentException("La data inserita non è valida");
        }
    }

    /**
     * Ritorno il giorno settato
     * @return int giorno
     */
    public int getGiorno(){
        return giorno;
    }

    /**
     * Ritorna il mese settato
     * @return int mese
     */
    public int getMese(){
        return mese;
    }

    /**
     * Ritorna l'anno settato
     * @return int anno
     */
    public int getAnno(){
        return anno;
    }

    /**
     * Ritorna la data del giorno corrente
     * @return Data giorno corrente
     */
    public static Data getToday(){
        return new Data(giornoCorrente, meseCorrente, annoCorrente);
    }

    /**
     * Ritorna la data come stringa per la visualizzazione
     * @return Stringa data inserita
     */
    public String toString(){
        String tmpGiorno = giorno < 10 ? ("0" + giorno) : Integer.toString(giorno);
        String tmpMese = mese < 10 ? ("0" + mese) : Integer.toString(mese);

        return tmpGiorno + "/" + tmpMese + "/" + anno;
    }

    /**
     * Ritorna true o false in base alla validità della data
     * @return boolean
     */
    private boolean isValid(){
        if(mese <= 0 || mese > 12){
            return false;
        }
        if((anno < 1950 || anno > 9999)){
            return false;
        }

        if(giorno <= 0 || giorno > daysInMonth()){
            return false;
        }
        return true;
    }

    /**
     * Utilità: ritorna il numero di giorni nel mese dato un anno
     * @return int numero di giorni nel mese richiesto
     */
    private int daysInMonth() {
        if (mese == 2 && isLeapYear(anno))
            return 29;

        return DAYS[mese - 1];
    }

    /**
     * Veritifica se l'anno considerato è bisestile o meno
     * @param y anno considerato
     * @return boolean
     */
    private static boolean isLeapYear(int y) {
        return y % 4 == 0 && (y % 100 != 0 || y % 400 == 0);
    }

    /**
     * Verifica se il lavoratore inserito è maggiorenne o meno
     * @param data la data inserita
     * @param eta eta minima
     * @return boolean
     */
    public static boolean isOlderThan(Data data, int eta){
        if(annoCorrente - data.getAnno() != eta){
            return annoCorrente - data.getAnno() > eta;
        }

        if(meseCorrente - data.getMese() != 0){
            return meseCorrente - data.getMese() > 0;
        }

        return giornoCorrente - data.getGiorno() >= 0;
    }

    /**
     * Tipico compareTo
     * @param data1
     * @param data2
     * @return int >0 sse data1 > data2, <0 sse data1 < data2, 0 sse uguali
     */
    public static int compareTo(Data data1, Data data2){
        if(data1.getAnno() - data2.getAnno() != 0){
            return data1.getAnno() - data2.getAnno();
        }
        if(data1.getMese() - data2.getMese() != 0){
            return data1.getMese() - data2.getMese();
        }
        if(data1.getGiorno() - data2.getGiorno() != 0){
            return data1.getGiorno() - data2.getGiorno();
        }
        return 0;
    }

}
