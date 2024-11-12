package Classes;

import java.util.ArrayList;
import java.util.Observer;

import Classes.Controller.TaskController;
import Classes.DTO.LoggerRecordDto;

import java.util.Observable;

/**
 * Singleton class representing a Logger in the task management system.
 */
@SuppressWarnings("deprecation")
public class Logger implements Observer {
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

    /**
     * This method is called whenever the observed object is changed. An application calls an 
     * Observable object's notifyObservers method to have all the object's observers notified 
     * of the change.
     *
     * @param o the observable object.
     * @param arg an argument passed to the notifyObservers method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof TaskController) {
            LoggerRecordDto log = (LoggerRecordDto) arg;
            publish(new LoggerRecord(log.getEditor(), log.getSubject(), log.getMessage()));
        }
    }

    /**
     * Method to add the Logger as an observer to the TaskController.
     * @param taskController The TaskController to observe -> {@link TaskController}
     */
    public void observe(TaskController taskController) {
        taskController.addObserver(this);
    }
}