package Classes.DTO;

import Classes.Model.User;

public class LoggerRecordDto {
    private User editor;
    private Object subject;
    private String message;

    public LoggerRecordDto(User editor, Object subject, String message) {
        this.editor = editor;
        this.subject = subject;
        this.message = message;
    }

    public User getEditor() {
        return editor;
    }

    public Object getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
