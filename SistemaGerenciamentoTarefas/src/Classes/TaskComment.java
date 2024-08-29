package SistemaGerenciamentoTarefas.src.Classes;

public class TaskComment extends Comment {
    private int taskId;

    /**
     * Constructor for the TaskComment class
     * Initializes the attributes of the Comment superclass and the taskId specific to TaskComment
     *
     * @param author The author of the comment
     * @param text The text of the comment
     * @param date The date of the comment
     * @param taskId The ID of the task associated with this comment
     */
    public TaskComment(String author, String text, String date, int taskId) {
        super(author, text, date); // Calls the correct constructor from Comment
        setTaskId(taskId);
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        if (taskId <= 0) {
            throw new IllegalArgumentException("Task ID must be a positive integer.");
        }
        this.taskId = taskId;
    }

    @Override
    protected void displayComment() {
        System.out.println("Task ID: " + taskId);
        super.displayComment();
    }
}
