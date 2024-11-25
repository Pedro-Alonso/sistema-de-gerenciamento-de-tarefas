package Classes.Controller;

import java.util.Observable;
import java.time.LocalDate;

import Classes.Project;
import Classes.Task;
import Classes.UserTask;
import Classes.DTO.LoggerRecordDto;

@SuppressWarnings("deprecation")
public class ProjectController extends Observable {
    /**
     * Constructor for the ProjectController class.
     */
    public ProjectController() {
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
     * Method to create a project.
     * @param beginDate The start date of the project -> {@link LocalDate}
     * @param limitDate The end date of the project -> {@link LocalDate}
     * @param name The name of the project -> {@link String}
     * @param userTask The user creating the project -> {@link UserTask}
     * @return The created project -> {@link Project}
     */
    public Project createProject(LocalDate beginDate, LocalDate limitDate, String name, UserTask userTask) {
        Project project = new Project(beginDate, limitDate, name);
        LoggerRecordDto log = new LoggerRecordDto(userTask, project, "Project created.");
        setChanged();
        notifyObservers(log);
        return project;
    }

    /**
     * Method to add a task to a project.
     * @param project The project to add the task to -> {@link Project}
     * @param task The task to be added -> {@link Task}
     * @param userTask The user adding the task -> {@link UserTask}
     */
    public void addTaskToProject(Project project, Task task, UserTask userTask) {
        project.addTask(task);
        LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task added to project.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a task from a project.
     * @param project The project to remove the task from -> {@link Project}
     * @param task The task to be removed -> {@link Task}
     * @param userTask The user removing the task -> {@link UserTask}
     */
    public void removeTaskFromProject(Project project, Task task, UserTask userTask) {
        project.removeTask(task.getId());
        LoggerRecordDto log = new LoggerRecordDto(userTask, task, "Task removed from project.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to add a user to a project.
     * @param project The project to add the user to -> {@link Project}
     * @param userTask The user to be added -> {@link UserTask}
     * @param editor The user adding the user -> {@link UserTask}
     */
    public void addUserToProject(Project project, UserTask userTask, UserTask editor) {
        project.addUser(userTask);
        LoggerRecordDto log = new LoggerRecordDto(editor, project, "User added to project.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a user from a project.
     * @param project The project to remove the user from -> {@link Project}
     * @param userTask The user to be removed -> {@link UserTask}
     * @param editor The user removing the user -> {@link UserTask}
     */
    public void removeUserFromProject(Project project, UserTask userTask, UserTask editor) {
        project.removeUser(userTask.getId());
        LoggerRecordDto log = new LoggerRecordDto(editor, project, "User removed from project.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the name of a project.
     * @param project The project to be updated -> {@link Project}
     * @param name The new name for the project -> {@link String}
     * @param userTask The user updating the project name -> {@link UserTask}
     */
    public void updateProjectName(Project project, String name, UserTask userTask) {
        String oldName = project.getName();
        project.setName(name);
        LoggerRecordDto log = new LoggerRecordDto(userTask, project, "Project name updated from '" + oldName + "' to '" + name + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the start date of a project.
     * @param project The project to be updated -> {@link Project}
     * @param beginDate The new start date for the project -> {@link LocalDate}
     * @param userTask The user updating the project start date -> {@link UserTask}
     */
    public void updateProjectBeginDate(Project project, LocalDate beginDate, UserTask userTask) {
        LocalDate oldBeginDate = project.getBeginDate();
        project.setBeginDate(beginDate);
        LoggerRecordDto log = new LoggerRecordDto(userTask, project, "Project begin date updated from '" + oldBeginDate + "' to '" + beginDate + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the end date of a project.
     * @param project The project to be updated -> {@link Project}
     * @param limitDate The new end date for the project -> {@link LocalDate}
     * @param userTask The user updating the project end date -> {@link UserTask}
     */
    public void updateProjectLimitDate(Project project, LocalDate limitDate, UserTask userTask) {
        LocalDate oldLimitDate = project.getLimitDate();
        project.setLimitDate(limitDate);
        LoggerRecordDto log = new LoggerRecordDto(userTask, project, "Project limit date updated from '" + oldLimitDate + "' to '" + limitDate + "'.");
        setChanged();
        notifyObservers(log);
    }
}