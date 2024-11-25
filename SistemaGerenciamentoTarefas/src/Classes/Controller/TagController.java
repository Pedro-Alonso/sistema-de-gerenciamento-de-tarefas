
package Classes.Controller;

import java.util.Observable;
import java.util.UUID;

import Classes.Tag;
import Classes.UserTask;
import Classes.DTO.LoggerRecordDto;

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
     * @param userTask The user creating the tag -> {@link UserTask}
     * @return The created tag -> {@link Tag}
     */
    public Tag createTag(String description, UUID projectId, UserTask userTask) {
        Tag tag = new Tag(description, projectId);
        LoggerRecordDto log = new LoggerRecordDto(userTask, tag, "Tag created.");
        setChanged();
        notifyObservers(log);
        return tag;
    }

    /**
     * Method to update the description of a tag.
     * @param tag The tag to be updated -> {@link Tag}
     * @param description The new description for the tag -> {@link String}
     * @param userTask The user updating the tag -> {@link UserTask}
     */
    public void updateTagDescription(Tag tag, String description, UserTask userTask) {
        String oldDescription = tag.getDescription();
        tag.setDescription(description);
        LoggerRecordDto log = new LoggerRecordDto(userTask, tag, "Tag description updated from '" + oldDescription + "' to '" + description + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a tag.
     * @param tag The tag to be removed -> {@link Tag}
     * @param userTask The user removing the tag -> {@link UserTask}
     */
    public void removeTag(Tag tag, UserTask userTask) {
        LoggerRecordDto log = new LoggerRecordDto(userTask, tag, "Tag removed.");
        setChanged();
        notifyObservers(log);
    }
}