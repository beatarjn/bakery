package pl.rejmanbeata.bakery.repository;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.rejmanbeata.bakery.repository.FileReader.readCsv;

class FileReaderTest {
    @Test
    void testReadCsv_correctFilePath() {
        List<List<String>> list = readCsv("src/test/java/pl/rejmanbeata/bakery/repository/testData.csv");

        assertThat(list)
                .isNotEmpty()
                .hasSize(2);
    }

    @Test
    void testReadCsv_IncorrectFilePath() {
        List<List<String>> list = readCsv("failData.csv");

        assertThat(list).isEmpty();
    }
    @Test
    void testReadCsv_NullFilePath() {
        List<List<String>> list = readCsv(null);

        assertThat(list).isEmpty();
    }

}