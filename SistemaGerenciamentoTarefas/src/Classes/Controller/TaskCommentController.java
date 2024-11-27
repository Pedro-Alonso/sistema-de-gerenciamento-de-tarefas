package Classes.Controller;

import java.util.Observable;

import Classes.DTO.LoggerRecordDto;
import Classes.DTO.TaskCommentDto;
import Classes.Mapper.TaskCommentMapper;
import Classes.Model.Task;
import Classes.Model.TaskComment;
import Classes.Model.UserSession;
import Classes.Repository.CommentDatabase;
import Classes.Repository.TaskDatabase;

@SuppressWarnings("deprecation")
public class TaskCommentController extends Observable {
    private final CommentDatabase commentDatabase;
    private final TaskDatabase taskDatabase;

    /**
     * Constructor for the TaskCommentController class.
     * Initializes the CommentDatabase instance.
     */
    public TaskCommentController() {
        super();
        this.commentDatabase = CommentDatabase.getInstance();
        this.taskDatabase = TaskDatabase.getInstance();
    }

    /**
     * Method to notify observers of a change.
     */
    @Override
    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }

    /**
     * Method to add a comment to a task.
     * @param userSession The user session adding the comment -> {@link UserSession}
     * @param task The task to add the comment to -> {@link Task}
     * @param comment The comment to be added -> {@link TaskComment}
     */
    public void addComment(UserSession userSession, Task task, TaskComment comment) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (taskDatabase.getTaskById(task.getId()) == null) {
            throw new IllegalArgumentException("Task does not exist.");
        }
        task.addComment(comment);
        commentDatabase.addComment(comment);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), comment, "Comment added.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to add a comment to a task using TaskCommentDto.
     * @param userSession The user session adding the comment -> {@link UserSession}
     * @param task The task to add the comment to -> {@link Task}
     * @param commentDto The comment DTO to be added -> {@link TaskCommentDto}
     * @return The added comment as a TaskCommentDto -> {@link TaskCommentDto}
     */
    public TaskCommentDto addComment(UserSession userSession, Task task, TaskCommentDto commentDto) {
        TaskComment comment = TaskCommentMapper.fromDto(commentDto);
        addComment(userSession, task, comment);
        return TaskCommentMapper.toDto(comment);
    }

    /**
     * Method to update a comment.
     * @param userSession The user session updating the comment -> {@link UserSession}
     * @param comment The comment to be updated -> {@link TaskComment}
     * @param newContent The new content for the comment -> {@link String}
     */
    public void updateComment(UserSession userSession, TaskComment comment, String newContent) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        comment.setText(newContent);
        commentDatabase.updateComment(comment);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), comment, "Comment updated.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a comment from a task.
     * @param userSession The user session removing the comment -> {@link UserSession}
     * @param task The task to remove the comment from -> {@link Task}
     * @param comment The comment to be removed -> {@link TaskComment}
     */
    public void removeComment(UserSession userSession, Task task, TaskComment comment) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        task.removeComment(comment.getId());
        commentDatabase.removeComment(comment.getId());
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), comment, "Comment removed.");
        setChanged();
        notifyObservers(log);
    }
}