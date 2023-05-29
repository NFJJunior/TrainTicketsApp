package service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    static String filePath = "logs.csv";
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static void addLogEntry(String operation) {

        try (FileWriter csvWriter = new FileWriter(filePath, true)){
            csvWriter.append(operation);
            csvWriter.append(",");
            csvWriter.append(LocalDateTime.now().format(formatter));
            csvWriter.append("\n");

            csvWriter.flush();
        }
        catch (IOException e) {
            System.out.println("Error : the entry couldn't register");
        }
    }
}
