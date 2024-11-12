package SistemaGerenciamentoTarefas.src.Classes;

import java.util.ArrayList;

/**
 * Singleton class representing a Logger in the task management system.
 */
public class Logger {
    private static Logger singleInstance;
    private ArrayList<LoggerRecord> logs;

    /**
     * Private constructor to prevent instantiation.
     */
    private Logger() {
        logs = new ArrayList<>();
    }

    /**
     * Method to get the single instance of the Logger class.
     * @return {@link Logger} The single instance of the Logger class.
     */
    public static synchronized Logger getInstance() {
        if (singleInstance == null) {
            singleInstance = new Logger();
        }
        return singleInstance;
    }

    /**
     * Method to publish a log record.
     * @param log The log record to be published -> {@link LoggerRecord}
     */
    public void publish(LoggerRecord log) {
        logs.add(log);
    }

    /**
     * Method to clear all log records.
     */
    public void flush() {
        logs.clear();
    }

    /**
     * {@return the list of log records as an {@link ArrayList} of {@link LoggerRecord}}
     */
    public ArrayList<LoggerRecord> getLogs() {
        return logs;
    }

    /**
     * Setter for the list of log records.
     * @param logs The new list of log records -> {@link ArrayList} of {@link LoggerRecord}
     */
    public void setLogs(ArrayList<LoggerRecord> logs) {
        this.logs = logs;
    }
}