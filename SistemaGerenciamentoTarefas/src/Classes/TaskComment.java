package Classes;

import java.util.UUID;

public class TaskComment extends Comment {
    private UUID taskId;

    /**
     * Constructor for the TaskComment class
     * Initializes the attributes of the Comment superclass and the taskId specific to TaskComment
     *
     * @param author The author of the comment -> {@link UserTask}
     * @param text The text of the comment -> {@link String}
     * @param taskId The ID of the task associated with this comment -> {@link UUID}
     */
    public TaskComment(UserTask author, String text, UUID taskId) {
        super(author, text); // Calls the correct constructor from Comment
        this.taskId = taskId;
    }

    /**
     * {@return the ID of the task associated with this comment as a {@link UUID}}
     */
    public UUID getTaskId() {
        return taskId;
    }

    /**
     * Setter for the task ID associated with this comment
     * @param taskId The new task ID -> {@link UUID}
     * @throws IllegalArgumentException if the task ID is null
     */
    public void setTaskId(UUID taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID must exist.");
        }
        this.taskId = taskId;
    }

    /**
     * Method to display the comment details including the task ID
     * @return {@link String} with the comment details
     */
    @Override
    public String displayComment() {
        String baseDisplay = super.displayComment();
        return baseDisplay + "\nTask ID: " + taskId;
    }
}
