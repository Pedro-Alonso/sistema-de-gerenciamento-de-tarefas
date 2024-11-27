package Classes.DTO;

import java.time.LocalDate;
import java.util.UUID;

import Classes.Model.Task.TaskStatus;

/**
 * Data Transfer Object for Task.
 */
public class TaskDto {
    private UUID id;
    private String name;
    private String description;
    private LocalDate deadline;
    private int gravity;
    private int urgency;
    private int trend;
    private int priority;
    private TaskStatus status;

    /**
     * {@return the ID of the task as a {@link UUID}}
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setter for the ID of the task.
     * @param id The new ID for the task -> {@link UUID}
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * {@return the name of the task as a {@link String}}
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the task.
     * @param name The new name for the task -> {@link String}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@return the description of the task as a {@link String}}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the task.
     * @param description The new description for the task -> {@link String}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * {@return the deadline of the task as a {@link LocalDate}}
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Setter for the deadline of the task.
     * @param deadline The new deadline for the task -> {@link LocalDate}
     */
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    /**
     * {@return the gravity of the task as an {@code int}}
     */
    public int getGravity() {
        return gravity;
    }

    /**
     * Setter for the gravity of the task.
     * @param gravity The new gravity for the task -> {@code int}
     */
    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    /**
     * {@return the urgency of the task as an {@code int}}
     */
    public int getUrgency() {
        return urgency;
    }

    /**
     * Setter for the urgency of the task.
     * @param urgency The new urgency for the task -> {@code int}
     */
    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

    /**
     * {@return the trend of the task as an {@code int}}
     */
    public int getTrend() {
        return trend;
    }

    /**
     * Setter for the trend of the task.
     * @param trend The new trend for the task -> {@code int}
     */
    public void setTrend(int trend) {
        this.trend = trend;
    }

    /**
     * {@return the priority of the task as an {@code int}}
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Setter for the priority of the task.
     * @param priority The new priority for the task -> {@code int}
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * {@return the status of the task as a {@link String}}
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Setter for the status of the task.
     * @param status The new status for the task -> {@link String}
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}