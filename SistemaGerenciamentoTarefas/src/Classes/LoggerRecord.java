package SistemaGerenciamentoTarefas.src.Classes;

import java.time.LocalDateTime;

public class LoggerRecord {
    private UserTask editor;    
    private LocalDateTime createdAt;
    
    private String subject;
    private String message;

    public LoggerRecord(UserTask editor, String subject, String message) {
        this.editor = editor;
        setCreatedAt();
        this.subject = subject;
        this.message = message;
    }

    public UserTask getEditor() {
        return editor;
    }

    public void setEditor(UserTask editor) {
        this.editor = editor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt() {
        createdAt = LocalDateTime.now();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
