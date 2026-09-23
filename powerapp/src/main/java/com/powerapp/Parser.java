package com.powerapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 

public class Parser {

    // scorre lo scanner fornito in input finché non trova una linea non vuota.
    public String getNextNotEmptyLine(Scanner scanner){
        String line = "";
        if(scanner.hasNextLine()){
            do {
                line = scanner.nextLine().strip();
            } while (scanner.hasNextLine() && line == "");
        }
        return line;
        
    }

    // scorre lo scanner fornito in input fino a trovare una riga fatta in questo modo: key:value.
    // Il metodo restituisce key e value in un oggetto apposito. Il metodo ignora le righe vuote.
    public KeyValue getNextKeyValue(Scanner scanner) throws FileFormatException{
        String line = getNextNotEmptyLine(scanner);
        String[] words = line.split(":");

        if(line.equals("")){
            return new KeyValue("", "");
        }

        if(words.length != 2){
            throw new FileFormatException("line " + line + " does not contain the character ':'");
        }

        if(words[0].strip() == ""){
            throw new FileFormatException("Key is missing in line " + line);
        }

        if(words[1].strip() == ""){
            throw new FileFormatException("Value is missing in line " + line);
        }

        return new KeyValue(words[0].strip(), words[1].strip());
    }

    public ArrayList<Heir> parseHeirs(String path) throws InvalidHeirException, FileNotFoundException, FileFormatException{
        File txtFile = new File(path);
        ArrayList<Heir> heirList = new ArrayList<>();

        Scanner scanner = new Scanner(txtFile);
        KeyValue name;
        do{
            name = getNextKeyValue(scanner);
            KeyValue surname = getNextKeyValue(scanner);
            KeyValue cf = getNextKeyValue(scanner);
            KeyValue iban = getNextKeyValue(scanner);
            KeyValue share = getNextKeyValue(scanner);

            if(name.getKey().equalsIgnoreCase("Nome") &&
                surname.getKey().equalsIgnoreCase("Cognome") && 
                cf.getKey().equalsIgnoreCase("Codice fiscale") &&
                iban.getKey().equalsIgnoreCase("IBAN") &&
                share.getKey().equalsIgnoreCase("Quota")){
                float shareFloat = Float.parseFloat(share.getValue());
                Heir newHeir = new Heir(name.getValue(), surname.getValue(), cf.getValue(), iban.getValue(), shareFloat);
                heirList.add(newHeir);
            }

            //se le keys non corrispondono, significa necessariamente che il file è finito, dunque le keys devono essere tutte vuote.
            else if(!(name.isEmpty() && surname.isEmpty() && cf.isEmpty() && iban.isEmpty() && share.isEmpty())){
                throw new FileFormatException("Invalid heirs file format.");
            }
        }while(!name.isEmpty());

        scanner.close();

        return heirList;
    }

    public ArrayList<SubFund> parseSubFunds(String path) throws FileNotFoundException, FileFormatException, InvalidSubFundException{
        File txtFile = new File(path);
        ArrayList<SubFund> subFundList = new ArrayList<>();

        Scanner scanner = new Scanner(txtFile);
        KeyValue isin;
        do{
            isin = getNextKeyValue(scanner);
            KeyValue shares = getNextKeyValue(scanner);

            if(isin.getKey().equalsIgnoreCase("ISIN") &&
                shares.getKey().equalsIgnoreCase("Quote")){
                float sharesFloat = Float.parseFloat(shares.getValue());
                SubFund subfund = new SubFund(isin.getValue(), sharesFloat);
                subFundList.add(subfund);
            }
        }while(!isin.getKey().equals("") && !isin.getValue().equals(""));

        return subFundList;

    }

}
