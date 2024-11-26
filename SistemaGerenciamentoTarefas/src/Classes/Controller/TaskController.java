package Classes.Controller;

import java.util.Observable;
import java.time.LocalDate;

import Classes.Project;
import Classes.Task;
import Classes.user.UserTask;
import Classes.DTO.LoggerRecordDto;

@SuppressWarnings("deprecation")
public class TaskController extends Observable {
    /**
     * Constructor for the TaskController class.
     */
    public TaskController() {
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
     * Method to create a task and add it to the project and user task.
     * @param project The project to add the task to -> {@link Project}
     * @param userTask The user task to add the task to -> {@link UserTask}
     * @param task The task to be created -> {@link Task}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void createTask(Project project, UserTask userTask, Task task) {
        if (project.getUsers().contains(userTask)) {
            project.addTask(task);
            userTask.addTask(task);
            LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task created.");
            setChanged();
            notifyObservers(log);
        } else {
            throw new IllegalArgumentException("Usuário não pertence ao projeto.");
        }
    }

    /**
     * Method to remove a task from the project and user task.
     * @param project The project to remove the task from -> {@link Project}
     * @param userTask The user task to remove the task from -> {@link UserTask}
     * @param task The task to be removed -> {@link Task}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void removeTask(Project project, UserTask userTask, Task task) {
        if (project.getUsers().contains(userTask)) {
            project.removeTask(task.getId());
            userTask.removeTaskFromProject(project.getId(), task.getId());
            LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task removed.");
            setChanged();
            notifyObservers(log);
        } else {
            throw new IllegalArgumentException("Usuário não pertence ao projeto.");
        }
    }

    /**
     * Method to update a task name in the project and user task.
     * @param project The project to update the task in -> {@link Project}
     * @param userTask The user task to update the task in -> {@link UserTask}
     * @param task The task to be updated -> {@link Task}
     * @param name The new name for the task -> {@link String}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskName(Project project, UserTask userTask, Task task, String name) {
        if (project.getUsers().contains(userTask)) {
            String oldName = task.getName();
            task.setName(name);
            LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task name updated from '" + oldName + "' to '" + name + "'.");
            setChanged();
            notifyObservers(log);
        } else {
            throw new IllegalArgumentException("Usuário não pertence ao projeto.");
        }
    }

    /**
     * Method to update a task description in the project and user task.
     * @param project The project to update the task in -> {@link Project}
     * @param userTask The user task to update the task in -> {@link UserTask}
     * @param task The task to be updated -> {@link Task}
     * @param description The new description for the task -> {@link String}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskDescription(Project project, UserTask userTask, Task task, String description) {
        if (project.getUsers().contains(userTask)) {
            String oldDescription = task.getDescription();
            task.setDescription(description);
            LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task description updated from '" + oldDescription + "' to '" + description + "'.");
            setChanged();
            notifyObservers(log);
        } else {
            throw new IllegalArgumentException("Usuário não pertence ao projeto.");
        }
    }

    /**
     * Method to update a task deadline in the project and user task.
     * @param project The project to update the task in -> {@link Project}
     * @param userTask The user task to update the task in -> {@link UserTask}
     * @param task The task to be updated -> {@link Task}
     * @param deadline The new deadline for the task -> {@link LocalDate}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskDeadline(Project project, UserTask userTask, Task task, LocalDate deadline) {
        if (project.getUsers().contains(userTask)) {
            LocalDate oldDeadline = task.getDeadline();
            task.setDeadline(deadline);
            LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task deadline updated from '" + oldDeadline + "' to '" + deadline + "'.");
            setChanged();
            notifyObservers(log);
        } else {
            throw new IllegalArgumentException("Usuário não pertence ao projeto.");
        }
    }

    /**
     * Method to update a task gravity in the project and user task.
     * @param project The project to update the task in -> {@link Project}
     * @param userTask The user task to update the task in -> {@link UserTask}
     * @param task The task to be updated -> {@link Task}
     * @param gravity The new gravity for the task -> {@code int}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskGravity(Project project, UserTask userTask, Task task, int gravity) {
        if (project.getUsers().contains(userTask)) {
            int oldGravity = task.getGravity();
            task.setGravity(gravity);
            LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task gravity updated from '" + oldGravity + "' to '" + gravity + "'.");
            setChanged();
            notifyObservers(log);
        } else {
            throw new IllegalArgumentException("Usuário não pertence ao projeto.");
        }
    }

    /**
     * Method to update a task urgency in the project and user task.
     * @param project The project to update the task in -> {@link Project}
     * @param userTask The user task to update the task in -> {@link UserTask}
     * @param task The task to be updated -> {@link Task}
     * @param urgency The new urgency for the task -> {@code int}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskUrgency(Project project, UserTask userTask, Task task, int urgency) {
        if (project.getUsers().contains(userTask)) {
            int oldUrgency = task.getUrgency();
            task.setUrgency(urgency);
            LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task urgency updated from '" + oldUrgency + "' to '" + urgency + "'.");
            setChanged();
            notifyObservers(log);
        } else {
            throw new IllegalArgumentException("Usuário não pertence ao projeto.");
        }
    }

    /**
     * Method to update a task trend in the project and user task.
     * @param project The project to update the task in -> {@link Project}
     * @param userTask The user task to update the task in -> {@link UserTask}
     * @param task The task to be updated -> {@link Task}
     * @param trend The new trend for the task -> {@code int}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskTrend(Project project, UserTask userTask, Task task, int trend) {
        if (project.getUsers().contains(userTask)) {
            int oldTrend = task.getTrend();
            task.setTrend(trend);
            LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task trend updated from '" + oldTrend + "' to '" + trend + "'.");
            setChanged();
            notifyObservers(log);
        } else {
            throw new IllegalArgumentException("Usuário não pertence ao projeto.");
        }
    }

    /**
     * Method to update a task status in the project and user task.
     * @param project The project to update the task in -> {@link Project}
     * @param userTask The user task to update the task in -> {@link UserTask}
     * @param task The task to be updated -> {@link Task}
     * @param status The new status for the task -> {@link Task.TaskStatus}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskStatus(Project project, UserTask userTask, Task task, Task.TaskStatus status) {
        if (project.getUsers().contains(userTask)) {
            Task.TaskStatus oldStatus = task.getStatus();
            task.setStatus(status);
            LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task status updated from '" + oldStatus + "' to '" + status + "'.");
            setChanged();
            notifyObservers(log);
        } else {
            throw new IllegalArgumentException("Usuário não pertence ao projeto.");
        }
    }
}
