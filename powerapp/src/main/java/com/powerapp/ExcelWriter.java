package com.powerapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

    private final static String[] COLUMN_NAMES = {
        "Nome erede",
        "Cognome erede",
        "Codice fiscale erede",
        "IBAN erede",
        "ISIN",
        "Quote ereditate"
    };

    // output directory deve contenere anche il nome del file, non solo la posizione.
    public static void createFundsRipartition(ArrayList<Heir> heirList, ArrayList<SubFund> fundList, String outputDirectory)
        throws InvalidHeirException, InvalidSubFundException, IOException{
        
        // initial safety checks
        if(heirList == null || heirList.isEmpty()){
            throw new InvalidHeirException("Heir list is null or empty.");
        }
        if(fundList == null || fundList.isEmpty()){
            throw new InvalidSubFundException("Subfund list is null or empty.");
        }

        float totalShare = 0.0f;
        for(Heir heir : heirList){
            if(heir == null){
                throw new InvalidHeirException("Null value in heir list.");
            }
            totalShare += heir.getShare();
        }
        if(totalShare != 1.0f){
            throw new InvalidHeirException("Heir's shares must add up to 1.0");
        }

        for(SubFund fund : fundList){
            if(fund == null){
                throw new InvalidSubFundException("Null value in subfund list.");
            }
        }

        // creating and writing the output file.
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Suddivisione fondi");

        //writing header row
        Row headerRow = sheet.createRow(0);
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);

        for(int i = 0; i < COLUMN_NAMES.length; i++){
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellStyle(headerStyle);
            headerCell.setCellValue(COLUMN_NAMES[i]);
        }

        int rowNumber = 1;
        for(SubFund fund : fundList){
            for(Heir heir : heirList){
                Row row = sheet.createRow(rowNumber);
                Cell nameCell = row.createCell(0);
                Cell surnameCell = row.createCell(1);
                Cell cfCell = row.createCell(2);
                Cell ibanCell = row.createCell(3);
                Cell isinCell = row.createCell(4);
                Cell quotesCell = row.createCell(5);

                nameCell.setCellValue(heir.getName());
                surnameCell.setCellValue(heir.getSurname());
                cfCell.setCellValue(heir.getCf());
                ibanCell.setCellValue(heir.getIban());
                isinCell.setCellValue(fund.getIsin());
                quotesCell.setCellValue(fund.getShares() * heir.getShare());

                rowNumber++;
            }
        }

        // regola la larghezza delle colonne per favorire la leggibilità
        for (int i = 0; i < COLUMN_NAMES.length; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 256);
        }

        FileOutputStream outputStream = new FileOutputStream(outputDirectory);
        workbook.write(outputStream);
        workbook.close();
        
    }
}
