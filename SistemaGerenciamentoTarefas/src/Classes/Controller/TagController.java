package Classes.Controller;

import java.util.Observable;
import java.util.UUID;

import Classes.DTO.LoggerRecordDto;
import Classes.Model.Tag;
import Classes.Model.UserSession;

@SuppressWarnings("deprecation")
public class TagController extends Observable {
    /**
     * Constructor for the TagController class.
     */
    public TagController() {
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
     * Method to create a tag.
     * @param description The description of the tag -> {@link String}
     * @param projectId The ID of the project associated with this tag -> {@link UUID}
     * @param userSession The session of the user creating the tag -> {@link UserSession}
     * @return The created tag -> {@link Tag}
     */
    public Tag createTag(String description, UUID projectId, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        Tag tag = new Tag(description, projectId);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), tag, "Tag created.");
        setChanged();
        notifyObservers(log);
        return tag;
    }

    /**
     * Method to update the description of a tag.
     * @param tag The tag to be updated -> {@link Tag}
     * @param description The new description for the tag -> {@link String}
     * @param userSession The session of the user updating the tag -> {@link UserSession}
     */
    public void updateTagDescription(Tag tag, String description, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        String oldDescription = tag.getDescription();
        tag.setDescription(description);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), tag, "Tag description updated from '" + oldDescription + "' to '" + description + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a tag.
     * @param tag The tag to be removed -> {@link Tag}
     * @param userSession The session of the user removing the tag -> {@link UserSession}
     */
    public void removeTag(Tag tag, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), tag, "Tag removed.");
        setChanged();
        notifyObservers(log);
    }
}