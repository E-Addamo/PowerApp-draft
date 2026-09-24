package com.powerapp;

import java.util.ArrayList;

public class App{

    //percorsi provvisori: da gestire in modo più robusto. Il metodo getProperty restituisce il percorso in cui sta venendo eseguito lo script,
    // quindi il programma al momento funziona solo se viene lanciato da C:\Users\eliaa\Desktop\Progetti\PowerApp\PowerApp-draft\powerapp
    private static final String DIR = System.getProperty("user.dir") + "/powerapp/src/main/java/com/powerapp/";
    private static final String TARGET_DIR = "C:/Users/eliaa/Desktop/";

    public static void main( String[] args ){
        try{
            Parser parser = new Parser();
            ArrayList<Heir> heirList = parser.parseHeirs(DIR + "Eredi.txt");
            ArrayList<SubFund> fundList = parser.parseSubFunds(DIR + "Comparti.txt");
            ExcelWriter.createFundsRipartition(heirList, fundList, TARGET_DIR + "Suddivisione fondi.xlsx");
            System.out.println("Suddivisione fondi prodotta con successo.");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
