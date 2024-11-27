package Classes.Controller;

import java.util.Observable;

import Classes.DTO.LoggerRecordDto;
import Classes.DTO.TagDto;
import Classes.Mapper.TagMapper;
import Classes.Model.Tag;
import Classes.Model.UserSession;
import Classes.Repository.TagDatabase;

@SuppressWarnings("deprecation")
public class TagController extends Observable {
    private final TagDatabase tagDatabase;

    /**
     * Constructor for the TagController class.
     * Initializes the TagDatabase instance.
     */
    public TagController() {
        super();
        this.tagDatabase = TagDatabase.getInstance();
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
     * @param userSession The user session creating the tag -> {@link UserSession}
     * @param tag The tag to be created -> {@link Tag}
     */
    public void createTag(UserSession userSession, Tag tag) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        tagDatabase.addTag(tag);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), tag, "Tag created.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to create a tag using TagDto.
     * @param userSession The user session creating the tag -> {@link UserSession}
     * @param tagDto The tag DTO to be created -> {@link TagDto}
     * @return The created tag DTO -> {@link TagDto}
     */
    public TagDto create(UserSession userSession, TagDto tagDto) {
        Tag tag = TagMapper.fromDto(tagDto);
        createTag(userSession, tag);
        return TagMapper.toDto(tag);
    }

    /**
     * Method to update a tag description.
     * @param userSession The user session updating the tag -> {@link UserSession}
     * @param tag The tag to be updated -> {@link Tag}
     * @param description The new description for the tag -> {@link String}
     */
    public void updateTagDescription(UserSession userSession, Tag tag, String description) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        tag.setDescription(description);
        tagDatabase.updateTag(tag);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), tag, "Tag description updated.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a tag.
     * @param userSession The user session removing the tag -> {@link UserSession}
     * @param tag The tag to be removed -> {@link Tag}
     */
    public void removeTag(UserSession userSession, Tag tag) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        tagDatabase.removeTag(tag.getId());
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), tag, "Tag removed.");
        setChanged();
        notifyObservers(log);
    }
}