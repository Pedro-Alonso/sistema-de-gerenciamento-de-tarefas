package Classes.Model;

import java.time.LocalDateTime;

/**
 * Class representing a log record in the task management system.
 */
public class LoggerRecord {
    private Object editor;    
    private LocalDateTime createdAt;
    private Object subject;
    private String message;

    /**
     * Constructor for the LoggerRecord class with an editor.
     * @param editor The editor of the log record -> {@link User}
     * @param subject The subject of the log record -> {@link Object}
     * @param message The message of the log record -> {@link String}
     */
    public LoggerRecord(User editor, Object subject, String message) {
        setEditor(editor);
        setCreatedAt();
        this.subject = subject;
        this.message = message;
    }

    /**
     * Constructor for the LoggerRecord class without an editor.
     * @param subject The subject of the log record -> {@link String}
     * @param message The message of the log record -> {@link String}
     */
    public LoggerRecord(String subject, String message) {
        setEditor("SISTEMA");
        setCreatedAt();
        this.subject = subject;
        this.message = message;
    }

    /**
     * {@return the editor of the log record as an {@link Object}}
     */
    public Object getEditor() {
        return editor;
    }

    /**
     * Setter for the editor of the log record.
     * @param editor The new editor for the log record -> {@link Object}
     * @throws IllegalArgumentException if the editor is not of type User or String
     */
    private void setEditor(Object editor) {
        if (editor instanceof User || editor instanceof String) {
            this.editor = editor;
        } else {
            throw new IllegalArgumentException("Editor deve ser do tipo User ou String.");
        }
    }

    /**
     * {@return the creation date of the log record as a {@link LocalDateTime}}
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Setter for the creation date of the log record.
     */
    private void setCreatedAt() {
        createdAt = LocalDateTime.now();
    }

    /**
     * {@return the subject of the log record as a {@link String}}
     */
    public Object getSubject() {
        return subject;
    }

    /**
     * Setter for the subject of the log record.
     * @param subject The new subject for the log record -> {@link String}
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * {@return the message of the log record as a {@link String}}
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for the message of the log record.
     * @param message The new message for the log record -> {@link String}
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * {@return the name of the editor of the log record as a {@link String}}
     */
    private String getEditorName() {
        if (editor instanceof User) {
            return ((User) editor).getUsername();
        } else {
            return (String) editor;
        }
    }

    /**
     * {@return the name of the subject of the log record as a {@link String}}
     */
    private String getSubjectName() {
        if (subject instanceof User) {
            return ((User) subject).getUsername();
        } else if (subject instanceof Task) {
            return ((Task) subject).getName();
        } else if (subject instanceof Project) {
            return ((Project) subject).getName();
        } else {
            return (String) subject;
        }
    }

    /**
     * {@return the type of the subject of the log record as a {@link String}}
     */
    private String getSubjectType() {
        if (subject instanceof User) {
            return "User";
        } else if (subject instanceof Task) {
            return "Task";
        } else if (subject instanceof Project) {
            return "Project";
        } else {
            return "String";
        }
    }

    /**
     * {@return a string representation of the log record}
     */
    @Override
    public String toString() {
        return "Editor: " + getEditorName() + "\n" +
                "Subject: " + getSubjectName() + " (" + getSubjectType() + ")\n" +
                "Message: " + message + "\n" +
                "Created at: " + createdAt;
        
    }
}
