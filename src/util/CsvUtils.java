package util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
    public static void writeCSV(String filePath, String[] data, boolean append) {
        try {
            boolean shouldWriteHeader = !append || !new java.io.File(filePath).exists();
            try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, append))) {
                if (shouldWriteHeader && data.length > 0) {
                    writer.writeNext(SystemConstants.CSV_USER_HEADER);
                }
                if (data.length > 0) {
                    writer.writeNext(data);
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public static List<String[]> readCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                if (nextRecord.length == 0 || nextRecord[0].trim().isEmpty()) {
                    continue;
                }
                records.add(nextRecord);
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return records;
    }

    public static boolean isItemInCsv(String path, String input, int columnIndex) {
        ArrayList<String[]> data = (ArrayList<String[]>) CsvUtils.readCSV(path);
        for (String[] line : data) {
            if (line[columnIndex].equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getColumnItemsCsv(String path, int columnIndex, boolean header) {
        List<String> result = new ArrayList<>();
        ArrayList<String[]> data = (ArrayList<String[]>) CsvUtils.readCSV(path);
        if (header) {
            columnIndex++;
        }
        for (String[] line : data) {
            result.add(line[columnIndex]);
        }
        return result;
    }

    public static String getItemByKeyCsv(String path, String key, int keyIndex, int itemIndex) {
        ArrayList<String[]> data = (ArrayList<String[]>) CsvUtils.readCSV(path);
        for (String[] line : data) {
            if (line.length <= Math.max(keyIndex, itemIndex)) continue;
            if (line[keyIndex].equals(key)) {
                return line[itemIndex];
            }
        }
        return "";
    }
}
