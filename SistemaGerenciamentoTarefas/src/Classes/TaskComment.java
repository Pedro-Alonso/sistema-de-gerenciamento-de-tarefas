package SistemaGerenciamentoTarefas.src.Classes;

import java.util.UUID;

public class TaskComment extends Comment {
    private UUID taskId;

    /**
     * Constructor for the TaskComment class
     * Initializes the attributes of the Comment superclass and the taskId specific to TaskComment
     *
     * @param author The author of the comment
     * @param text The text of the comment
     * @param date The date of the comment
     * @param taskId The ID of the task associated with this comment
     */
    public TaskComment(String author, String text, String date, UUID taskId) {
        super(author, text, date); // Calls the correct constructor from Comment
        this.taskId = taskId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID must exist.");
        }
        this.taskId = taskId;
    }

    @Override
    protected void displayComment() {
        System.out.println("Task ID: " + taskId);
        super.displayComment();
    }
}
