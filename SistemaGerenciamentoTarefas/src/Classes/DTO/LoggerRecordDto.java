package Classes.DTO;

import Classes.user.UserTask;

public class LoggerRecordDto {
    private UserTask editor;
    private Object subject;
    private String message;

    public LoggerRecordDto(UserTask editor, Object subject, String message) {
        this.editor = editor;
        this.subject = subject;
        this.message = message;
    }

    public UserTask getEditor() {
        return editor;
    }

    public Object getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
