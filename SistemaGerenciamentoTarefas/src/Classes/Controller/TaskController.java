package Classes.Controller;

import java.util.Observable;

import Classes.Project;
import Classes.Task;
import Classes.UserTask;
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
}
