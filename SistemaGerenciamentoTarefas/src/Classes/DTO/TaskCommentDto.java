package Classes.DTO;

import java.util.UUID;

/**
 * Data Transfer Object for TaskComment.
 */
public class TaskCommentDto {
    private UUID id;
    private UUID taskId;
    private String text;
    private UUID authorId;

    /**
     * {@return the ID of the comment as a {@link UUID}}
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setter for the ID of the comment.
     * @param id The new ID for the comment -> {@link UUID}
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * {@return the ID of the task associated with the comment as a {@link UUID}}
     */
    public UUID getTaskId() {
        return taskId;
    }

    /**
     * Setter for the ID of the task associated with the comment.
     * @param taskId The new task ID -> {@link UUID}
     */
    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    /**
     * {@return the text of the comment as a {@link String}}
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for the text of the comment.
     * @param text The new text for the comment -> {@link String}
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * {@return the ID of the author of the comment as a {@link UUID}}
     */
    public UUID getAuthorId() {
        return authorId;
    }

    /**
     * Setter for the ID of the author of the comment.
     * @param authorId The new author ID -> {@link UUID}
     */
    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }
}