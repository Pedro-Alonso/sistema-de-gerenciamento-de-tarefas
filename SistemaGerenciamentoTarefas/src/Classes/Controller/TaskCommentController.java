package Classes.Controller;

import java.util.Observable;
import java.util.UUID;

import Classes.DTO.LoggerRecordDto;
import Classes.Model.TaskComment;
import Classes.Model.UserTask;

@SuppressWarnings("deprecation")
public class TaskCommentController extends Observable {
    /**
     * Constructor for the TaskCommentController class.
     */
    public TaskCommentController() {
        super();
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
     * Method to create a task comment.
     * @param author The author of the comment -> {@link UserTask}
     * @param text The text of the comment -> {@link String}
     * @param taskId The ID of the task associated with this comment -> {@link UUID}
     * @return The created comment -> {@link TaskComment}
     */
    public TaskComment createTaskComment(UserTask author, String text, UUID taskId) {
        TaskComment comment = new TaskComment(author, text, taskId);
        LoggerRecordDto log = new LoggerRecordDto(author, comment, "Task comment created.");
        setChanged();
        notifyObservers(log);
        return comment;
    }

    /**
     * Method to update the text of a task comment.
     * @param comment The comment to be updated -> {@link TaskComment}
     * @param text The new text for the comment -> {@link String}
     * @param userTask The user updating the comment -> {@link UserTask}
     */
    public void updateTaskCommentText(TaskComment comment, String text, UserTask userTask) {
        String oldText = comment.getText();
        comment.setText(text);
        LoggerRecordDto log = new LoggerRecordDto(userTask, comment, "Task comment text updated from '" + oldText + "' to '" + text + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to add a topic to a task comment.
     * @param comment The comment to add the topic to -> {@link TaskComment}
     * @param topic The topic to be added -> {@link String}
     * @param userTask The user adding the topic -> {@link UserTask}
     */
    public void addTopicToTaskComment(TaskComment comment, String topic, UserTask userTask) {
        comment.addTopic(topic);
        LoggerRecordDto log = new LoggerRecordDto(userTask, comment, "Topic added to task comment.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update a topic in a task comment.
     * @param comment The comment to update the topic in -> {@link TaskComment}
     * @param index The index of the topic to be updated -> {@code int}
     * @param newTopic The new topic -> {@link String}
     * @param userTask The user updating the topic -> {@link UserTask}
     */
    public void updateTaskCommentTopic(TaskComment comment, int index, String newTopic, UserTask userTask) {
        String oldTopic = comment.getTopics().get(index);
        comment.updateTopic(index, newTopic);
        LoggerRecordDto log = new LoggerRecordDto(userTask, comment, "Topic updated from '" + oldTopic + "' to '" + newTopic + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a topic from a task comment.
     * @param comment The comment to remove the topic from -> {@link TaskComment}
     * @param index The index of the topic to be removed -> {@code int}
     * @param userTask The user removing the topic -> {@link UserTask}
     */
    public void removeTaskCommentTopic(TaskComment comment, int index, UserTask userTask) {
        comment.removeTopic(index);
        LoggerRecordDto log = new LoggerRecordDto(userTask, comment, "Topic removed from task comment.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the author of a task comment.
     * @param comment The comment to be updated -> {@link TaskComment}
     * @param author The new author for the comment -> {@link UserTask}
     * @param userTask The user updating the author -> {@link UserTask}
     */
    public void updateTaskCommentAuthor(TaskComment comment, UserTask author, UserTask userTask) {
        UserTask oldAuthor = comment.getAuthor();
        comment.setAuthor(author);
        LoggerRecordDto log = new LoggerRecordDto(userTask, comment, "Task comment author updated from '" + oldAuthor + "' to '" + author + "'.");
        setChanged();
        notifyObservers(log);
    }
}