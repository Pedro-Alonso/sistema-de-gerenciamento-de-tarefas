package Classes.Controller;

import java.util.Observable;
import java.time.LocalDate;

import Classes.DTO.LoggerRecordDto;
import Classes.DTO.TaskDto;
import Classes.Mapper.TaskMapper;
import Classes.Model.Project;
import Classes.Model.Task;
import Classes.Model.UserSession;
import Classes.Model.UserTask;
import Classes.Repository.TaskDatabase;

@SuppressWarnings("deprecation")
public class TaskController extends Observable {
    private final TaskDatabase taskDatabase;

    /**
     * Constructor for the TaskController class.
     * Initializes the TaskDatabase instance.
     */
    public TaskController() {
        super();
        this.taskDatabase = TaskDatabase.getInstance();
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
     * @param userSession The user session to add the task to -> {@link UserSession}
     * @param task The task to be created -> {@link Task}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void createTask(Project project, UserSession userSession, Task task) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        if (project.getUsers() != null && project.getUsers().contains(userTask)) {
            project.addTask(task);
            userTask.addTask(task);
            taskDatabase.addTask(task);
            LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task created.");
            setChanged();
            notifyObservers(log);
        } else {
            throw new IllegalArgumentException("Usuário não pertence ao projeto.");
        }
    }

    public TaskDto create(Project project, UserSession userSession, TaskDto taskDto) {
        Task task = TaskMapper.fromDto(taskDto);
        createTask(project, userSession, task);
        return TaskMapper.toDto(task);
    }

    /**
     * Method to remove a task from the project and user task.
     * @param project The project to remove the task from -> {@link Project}
     * @param userSession The user session to remove the task from -> {@link UserSession}
     * @param task The task to be removed -> {@link Task}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void removeTask(Project project, UserSession userSession, Task task) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        if (project.getUsers() != null && project.getUsers().contains(userTask)) {
            project.removeTask(task.getId());
            userTask.removeTaskFromProject(project.getId(), task.getId());
            taskDatabase.removeTask(task.getId());
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
     * @param userSession The user session to update the task in -> {@link UserSession}
     * @param task The task to be updated -> {@link Task}
     * @param name The new name for the task -> {@link String}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskName(Project project, UserSession userSession, Task task, String name) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        if (project.getUsers() != null && project.getUsers().contains(userTask)) {
            String oldName = task.getName();
            task.setName(name);
            taskDatabase.updateTask(task);
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
     * @param userSession The user session to update the task in -> {@link UserSession}
     * @param task The task to be updated -> {@link Task}
     * @param description The new description for the task -> {@link String}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskDescription(Project project, UserSession userSession, Task task, String description) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        if (project.getUsers() != null && project.getUsers().contains(userTask)) {
            String oldDescription = task.getDescription();
            task.setDescription(description);
            taskDatabase.updateTask(task);
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
     * @param userSession The user session to update the task in -> {@link UserSession}
     * @param task The task to be updated -> {@link Task}
     * @param deadline The new deadline for the task -> {@link LocalDate}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskDeadline(Project project, UserSession userSession, Task task, LocalDate deadline) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        if (project.getUsers() != null && project.getUsers().contains(userTask)) {
            LocalDate oldDeadline = task.getDeadline();
            task.setDeadline(deadline);
            taskDatabase.updateTask(task);
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
     * @param userSession The user session to update the task in -> {@link UserSession}
     * @param task The task to be updated -> {@link Task}
     * @param gravity The new gravity for the task -> {@code int}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskGravity(Project project, UserSession userSession, Task task, int gravity) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        if (project.getUsers() != null && project.getUsers().contains(userTask)) {
            int oldGravity = task.getGravity();
            task.setGravity(gravity);
            taskDatabase.updateTask(task);
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
     * @param userSession The user session to update the task in -> {@link UserSession}
     * @param task The task to be updated -> {@link Task}
     * @param urgency The new urgency for the task -> {@code int}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskUrgency(Project project, UserSession userSession, Task task, int urgency) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        if (project.getUsers() != null && project.getUsers().contains(userTask)) {
            int oldUrgency = task.getUrgency();
            task.setUrgency(urgency);
            taskDatabase.updateTask(task);
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
     * @param userSession The user session to update the task in -> {@link UserSession}
     * @param task The task to be updated -> {@link Task}
     * @param trend The new trend for the task -> {@code int}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskTrend(Project project, UserSession userSession, Task task, int trend) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        if (project.getUsers() != null && project.getUsers().contains(userTask)) {
            int oldTrend = task.getTrend();
            task.setTrend(trend);
            taskDatabase.updateTask(task);
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
     * @param userSession The user session to update the task in -> {@link UserSession}
     * @param task The task to be updated -> {@link Task}
     * @param status The new status for the task -> {@link Task.TaskStatus}
     * @throws IllegalArgumentException if the user task is not part of the project
     */
    public void updateTaskStatus(Project project, UserSession userSession, Task task, Task.TaskStatus status) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        if (project.getUsers() != null && project.getUsers().contains(userTask)) {
            Task.TaskStatus oldStatus = task.getStatus();
            task.setStatus(status);
            taskDatabase.updateTask(task);
            LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task status updated from '" + oldStatus + "' to '" + status + "'.");
            setChanged();
            notifyObservers(log);
        } else {
            throw new IllegalArgumentException("Usuário não pertence ao projeto.");
        }
    }
}
