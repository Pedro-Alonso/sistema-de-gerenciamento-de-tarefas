package Classes.Controller;

import java.util.Observable;
import java.util.UUID;

import Classes.DTO.LoggerRecordDto;
import Classes.Model.TaskComment;
import Classes.Model.UserTask;
import Classes.Model.UserSession;

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
     * @param authorSession The session of the author of the comment -> {@link UserSession}
     * @param text The text of the comment -> {@link String}
     * @param taskId The ID of the task associated with this comment -> {@link UUID}
     * @return The created comment -> {@link TaskComment}
     */
    public TaskComment createTaskComment(UserSession authorSession, String text, UUID taskId) {
        if (!authorSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask user = (UserTask) authorSession.getUser();
        if (user == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        TaskComment comment = new TaskComment(user, text, taskId);
        LoggerRecordDto log = new LoggerRecordDto(authorSession.getUser(), comment, "Task comment created.");
        setChanged();
        notifyObservers(log);
        return comment;
    }

    /**
     * Method to update the text of a task comment.
     * @param comment The comment to be updated -> {@link TaskComment}
     * @param text The new text for the comment -> {@link String}
     * @param userSession The session of the user updating the comment -> {@link UserSession}
     */
    public void updateTaskCommentText(TaskComment comment, String text, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        String oldText = comment.getText();
        comment.setText(text);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), comment, "Task comment text updated from '" + oldText + "' to '" + text + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to add a topic to a task comment.
     * @param comment The comment to add the topic to -> {@link TaskComment}
     * @param topic The topic to be added -> {@link String}
     * @param userSession The session of the user adding the topic -> {@link UserSession}
     */
    public void addTopicToTaskComment(TaskComment comment, String topic, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        comment.addTopic(topic);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), comment, "Topic added to task comment.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update a topic in a task comment.
     * @param comment The comment to update the topic in -> {@link TaskComment}
     * @param index The index of the topic to be updated -> {@code int}
     * @param newTopic The new topic -> {@link String}
     * @param userSession The session of the user updating the topic -> {@link UserSession}
     */
    public void updateTaskCommentTopic(TaskComment comment, int index, String newTopic, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        String oldTopic = comment.getTopics().get(index);
        comment.updateTopic(index, newTopic);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), comment, "Topic updated from '" + oldTopic + "' to '" + newTopic + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a topic from a task comment.
     * @param comment The comment to remove the topic from -> {@link TaskComment}
     * @param index The index of the topic to be removed -> {@code int}
     * @param userSession The session of the user removing the topic -> {@link UserSession}
     */
    public void removeTaskCommentTopic(TaskComment comment, int index, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        comment.removeTopic(index);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), comment, "Topic removed from task comment.");
        setChanged();
        notifyObservers(log);
    }
}