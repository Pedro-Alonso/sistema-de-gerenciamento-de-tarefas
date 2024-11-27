package Classes.Model;

import java.util.ArrayList;
import java.util.Observer;

import Classes.Controller.LoginController;
import Classes.Controller.ProjectController;
import Classes.Controller.SubTaskController;
import Classes.Controller.TagController;
import Classes.Controller.TaskCommentController;
import Classes.Controller.TaskController;
import Classes.Controller.UserController;
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

        if (o instanceof ProjectController) {
            LoggerRecordDto log = (LoggerRecordDto) arg;
            publish(new LoggerRecord(log.getEditor(), log.getSubject(), log.getMessage()));
        }

        if (o instanceof UserController) {
            LoggerRecordDto log = (LoggerRecordDto) arg;
            publish(new LoggerRecord(log.getEditor(), log.getSubject(), log.getMessage()));
        }

        if (o instanceof TaskCommentController) {
            LoggerRecordDto log = (LoggerRecordDto) arg;
            publish(new LoggerRecord(log.getEditor(), log.getSubject(), log.getMessage()));
        }

        if (o instanceof SubTaskController) {
            LoggerRecordDto log = (LoggerRecordDto) arg;
            publish(new LoggerRecord(log.getEditor(), log.getSubject(), log.getMessage()));
        }

        if (o instanceof TagController) {
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

    /**
     * Method to add the Logger as an observer to the ProjectController.
     * @param projectController The ProjectController to observe -> {@link ProjectController}
     */
    public void observe(ProjectController projectController) {
        projectController.addObserver(this);
    }

    /**
     * Method to add the Logger as an observer to the UserController.
     * @param userController The UserController to observe -> {@link UserController}
     */
    public void observe(UserController userController) {
        userController.addObserver(this);
    }

    /**
     * Method to add the Logger as an observer to the TaskCommentController.
     * @param taskCommentController The TaskCommentController to observe -> {@link TaskCommentController}
     */
    public void observe(TaskCommentController taskCommentController) {
        taskCommentController.addObserver(this);
    }

    /**
     * Method to add the Logger as an observer to the SubTaskController.
     * @param subTaskController The SubTaskController to observe -> {@link SubTaskController}
     */
    public void observe(SubTaskController subTaskController) {
        subTaskController.addObserver(this);

    }

    /**
     * Method to add the Logger as an observer to the TagController.
     * @param tagController The TagController to observe -> {@link TagController}
     */
    public void observe(TagController tagController) {
        tagController.addObserver(this);
    }

    

    /**
     * Method to get the log records as a string.
     * @return The log records as a string.
     */
    public String getLogsAsString() {
        StringBuilder logsAsString = new StringBuilder();
        for (LoggerRecord log : logs) {
            logsAsString.append(log.toString()).append("\n");
        }
        return logsAsString.toString();
    }
}