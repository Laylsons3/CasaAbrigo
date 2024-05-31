package com.example.converter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvToExcelConverter {

    public static void convertCsvToExcel(String csvFilePath, String excelFilePath) throws IOException {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            Workbook workbook = new XSSFWorkbook();
            FileOutputStream fileOut = new FileOutputStream(excelFilePath)
        ) {
            Sheet sheet = workbook.createSheet("Dados CSV");

            int rowIndex = 0;
            for (CSVRecord csvRecord : csvParser) {
                Row row = sheet.createRow(rowIndex++);
                for (int colIndex = 0; colIndex < csvRecord.size(); colIndex++) {
                    row.createCell(colIndex).setCellValue(csvRecord.get(colIndex));
                }
            }

            workbook.write(fileOut);
        }
    }
}
