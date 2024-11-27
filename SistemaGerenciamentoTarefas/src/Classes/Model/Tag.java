package Classes.Model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Class representing a Tag in the task management system.
 */
public class Tag {
    
    private UUID id;
    private String description;
    private UUID projectId;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    /**
     * Constructor for the Tag class
     * Initializes the description and projectId attributes, and generates a unique ID for the tag
     *
     * @param description The description of the tag -> {@link String}
     * @param projectId The ID of the project associated with this tag -> {@link UUID}
     */
    public Tag(String description, UUID projectId) {
        this.description = description;
        this.projectId = projectId;
        this.id = UUID.randomUUID();
    }

    /**
     * Constructor for the Tag class for DTO purposes
     * Initializes the description, projectId and id attributes
     *
     * @param id The ID of the tag -> {@link UUID}
     * @param description The description of the tag -> {@link String}
     * @param projectId The ID of the project associated with this tag -> {@link UUID}
     */
    public Tag(UUID id, String description, UUID projectId) {
        this.id = id;
        this.description = description;
        this.projectId = projectId;
    }
    
    /**
     * {@return the project ID associated with this tag as a {@link UUID}}
     */
    public UUID getProjectID() {
        return projectId;
    }

    /**
     * {@return the ID of the tag as a {@link UUID}}
     */
    public UUID getId() {
        return id;
    }

    /**
     * {@return the description of the tag as a {@link String}}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the tag
     * @param description The new description for the tag -> {@link String}
     */
    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * {@return the creation date of the tag as a {@link LocalDateTime}}
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * {@return the last update date of the tag as a {@link LocalDateTime}}
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
