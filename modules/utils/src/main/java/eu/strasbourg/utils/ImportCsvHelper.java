package eu.strasbourg.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ImportCsvHelper {

    public static List<Map<String, String>> loadingDataFromCsv(File csvFile, String[] headerMapping) throws IOException {
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(headerMapping).withDelimiter(';');

        CSVParser csvFileParser = CSVParser.parse(csvFile, StandardCharsets.UTF_8, csvFileFormat);

        List<Map<String, String>> recordsListMap = new ArrayList<>();
        List<CSVRecord> csvRecords = csvFileParser.getRecords();

        for (int i = 1; i < csvRecords.size(); i++) {
            CSVRecord record = csvRecords.get(i);
            recordsListMap.add(record.toMap());
        }

        return recordsListMap;
    }

    /**
     * Vérification du Header du CSV => Nombre de colonne, ordre des headers, libellé des headers
     *
     * @param csvFile
     * @param headerMapping
     * @throws Exception
     */
    public static String csvCheckHeader(File csvFile, String[] headerMapping) throws IOException {
        String error = "";
        CSVParser csvFileParser = CSVParser.parse(csvFile, StandardCharsets.UTF_8, CSVFormat.DEFAULT);
        List<CSVRecord> csvRecords = csvFileParser.getRecords();

        CSVRecord headerRecord = csvRecords.get(0);

        if (headerRecord != null && headerRecord.get(0) != null) {
            String[] headerCsv = headerRecord.get(0).split(";");

            // Vérification taille du header
            if (headerCsv.length != headerMapping.length) {
                // error nombre de champs header
                error = "Erreur concernant le nombre de headers. </br>" +
                        "Nombre de colonnes pr\u00e9sentes dans le CSV : " + headerCsv.length + "</br>" +
                        "Nombre de colonnes attendu : " + headerMapping.length + "</br>" +
                        "</br>" +
                        "Headers pr\u00e9sents dans le CSV : " + "</br>" +
                        Arrays.toString(headerCsv) + "</br>" +
                        "</br>" +
                        "Headers attendus dans le CSV : " + "</br>" +
                        Arrays.toString(headerMapping);
                return error;
            }

            // Vérification Order et Libellé des Header
            for (int i = 0; i < headerCsv.length; i++) {
                if (!headerCsv[i].equals(headerMapping[i])) {
                    error = "Erreur de header \u00e0 la colonne : " + String.valueOf(i + 1) + " </br>" +
                            "Header pr\u00e9sent dans le CSV pour cette colonne : " + headerCsv[i] + "</br>" +
                            "Header attendu pour cette colonne : " + headerMapping[i] + "</br>" +
                            "</br>" +
                            "Headers pr\u00e9sents dans le CSV : " + "</br>" +
                            Arrays.toString(headerCsv) + "</br>" +
                            "</br>" +
                            "Headers attendus dans le CSV : " + "</br>" +
                            Arrays.toString(headerMapping);
                    return error;
                }
            }

            // Vérification d'erreur par lignes du CSV
            for (int i = 1; i < csvRecords.size(); i++) {
                String value = "";
                for(int j = 0; j < csvRecords.get(i).size(); j++){
                    value += csvRecords.get(i).get(j);
                }

                int count = 1;
                for (int k = 0; k < value.length(); k++) {
                    if (value.charAt(k) == ';') {
                        count++;
                    }
                }

                if (count != headerMapping.length) {
                    // error nombre de champs dans les lignes
                    error += "Erreur concernant le nombre de colonnes dans une ligne. </br>" +
                            "Nombre de colonnes pr\u00e9sentes \u00e0 la ligne " + (i+1) + " : " + count + "</br>" +
                            "Nombre de colonnes attendu : " + headerMapping.length + "</br>" +
                            "</br>";
                }
            }
        } else {
            error = "Erreur lors de la r\u00e9cup\u00e9ration du header du fichier CSV";
        }

        return error;
    }
}

