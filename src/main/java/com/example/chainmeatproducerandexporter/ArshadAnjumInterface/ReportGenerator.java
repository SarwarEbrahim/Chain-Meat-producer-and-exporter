package com.example.chainmeatproducerandexporter.ArshadAnjumInterface;

public interface ReportGenerator {
    String generateReport();
    void saveToFile(String filePath, String content);
}
