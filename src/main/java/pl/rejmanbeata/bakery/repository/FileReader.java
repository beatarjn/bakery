package pl.rejmanbeata.bakery.repository;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<List<String>> readCsv(String filePath) {
        List<List<String>> fileData = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new java.io.FileReader(filePath));) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                fileData.add(List.of(values));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        } catch (IOException | CsvValidationException e) {
            System.out.println("CSV file exception" + e.getMessage());
        }
        return fileData;
    }
}
