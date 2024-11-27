package Classes.DTO;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object for Project.
 */
public class ProjectDto {
    private UUID id;
    private String name;
    private LocalDate beginDate;
    private LocalDate limitDate;

    /**
     * {@return the ID of the project as a {@link UUID}}
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setter for the ID of the project.
     * @param id The new ID for the project -> {@link UUID}
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * {@return the name of the project as a {@link String}}
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the project.
     * @param name The new name for the project -> {@link String}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@return the start date of the project as a {@link LocalDate}}
     */
    public LocalDate getBeginDate() {
        return beginDate;
    }

    /**
     * Setter for the start date of the project.
     * @param beginDate The new start date for the project -> {@link LocalDate}
     */
    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * {@return the end date of the project as a {@link LocalDate}}
     */
    public LocalDate getLimitDate() {
        return limitDate;
    }

    /**
     * Setter for the end date of the project.
     * @param limitDate The new end date for the project -> {@link LocalDate}
     */
    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }
}