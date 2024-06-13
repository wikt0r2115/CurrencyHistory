package com.przelicznikwalut;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    public void writeDataToCsv(ProcessedData data, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Kod waluty,Licznik\n");
            writer.append(data.getCode())
                    .append(",")
                    .append(String.valueOf(data.getRate()))
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
