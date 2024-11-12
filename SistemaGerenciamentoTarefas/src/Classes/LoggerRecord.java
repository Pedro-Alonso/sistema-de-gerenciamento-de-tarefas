package SistemaGerenciamentoTarefas.src.Classes;

import java.time.LocalDateTime;

public class LoggerRecord {
    private Object editor;    
    private LocalDateTime createdAt;
    
    private String subject;
    private String message;

    public LoggerRecord(UserTask editor, String subject, String message) {
        setEditor(editor);
        setCreatedAt();
        this.subject = subject;
        this.message = message;
    }

    public LoggerRecord(String subject, String message) {
        setEditor("SISTEMA");
        setCreatedAt();
        this.subject = subject;
        this.message = message;
    }

    public Object getEditor() {
        return editor;
    }

    private void setEditor(Object editor) {
        if (editor instanceof UserTask || editor instanceof String) {
            this.editor = editor;
        } else {
            throw new IllegalArgumentException("Editor deve ser do tipo UserTask ou String.");
        }
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
