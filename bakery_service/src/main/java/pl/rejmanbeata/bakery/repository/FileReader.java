package pl.rejmanbeata.bakery.repository;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileReader {

    public static List<List<String>> readCsv(String filePath) {
        List<List<String>> fileData = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new java.io.FileReader(filePath));) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                fileData.add(List.of(values));
            }
        } catch (FileNotFoundException | NullPointerException e) {
            log.info("File not found: " + e.getMessage());
        } catch (IOException | CsvValidationException e) {
            log.info("CSV file exception: " + e.getMessage());
        }
        return fileData;
    }
}
