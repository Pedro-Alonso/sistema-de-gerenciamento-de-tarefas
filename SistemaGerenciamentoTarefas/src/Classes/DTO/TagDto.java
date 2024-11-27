package Classes.DTO;

import java.util.UUID;

/**
 * Data Transfer Object for Tag.
 */
public class TagDto {
    private UUID id;
    private String description;
    private UUID projectId;

    /**
     * {@return the ID of the tag as a {@link UUID}}
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setter for the ID of the tag.
     * @param id The new ID for the tag -> {@link UUID}
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * {@return the description of the tag as a {@link String}}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the tag.
     * @param description The new description for the tag -> {@link String}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * {@return the ID of the project associated with the tag as a {@link UUID}}
     */
    public UUID getProjectId() {
        return projectId;
    }

    /**
     * Setter for the ID of the project associated with the tag.
     * @param projectId The new project ID -> {@link UUID}
     */
    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }
}