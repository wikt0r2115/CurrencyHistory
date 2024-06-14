package com.przelicznikwalut;

public abstract class BaseCsvWriter {
    public abstract void writeDataToCsv(ProcessedData data, String fileName);
}
