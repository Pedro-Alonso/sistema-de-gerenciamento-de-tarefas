package SistemaGerenciamentoTarefas.src.Classes;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Logger {
    private static Logger uniqueInstance;
    private ArrayList<String> logEntries;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z");

    private Logger() {
        logEntries = new ArrayList<>();
    }

    public static synchronized Logger getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Logger();
        }
        return uniqueInstance;
    }

    public void createLogEntry(String message) {
        ZonedDateTime dateTime = ZonedDateTime.now();
        String formattedDate = dateTime.format(formatter);
        String logMessage = formattedDate + ": " + message;
        logEntries.add(logMessage);
    }

    public ArrayList<String> getLogs() {
        return logEntries;
    }

}
