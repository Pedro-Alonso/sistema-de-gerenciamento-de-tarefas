package SistemaGerenciamentoTarefas.src.Classes;

import java.util.ArrayList;

public class Logger {
    private static Logger singleInstance;
    private ArrayList<LoggerRecord> logs;

    private Logger() {
        logs = new ArrayList<>();
    }

    public static synchronized Logger getInstance() {
        if (singleInstance == null) {
            singleInstance = new Logger();
        }
        return singleInstance;
    }

    public void publish (LoggerRecord log) {
        logs.add(log);
    }

    public void flush () {
        logs.clear();
    }

    public ArrayList<LoggerRecord> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<LoggerRecord> logs) {
        this.logs = logs;
    }

}