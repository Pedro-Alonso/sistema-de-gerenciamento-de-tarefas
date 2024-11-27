package Classes.Controller;

import java.util.Observable;
import java.time.LocalDate;

import Classes.DTO.LoggerRecordDto;
import Classes.Model.Project;
import Classes.Model.Task;
import Classes.Model.User;
import Classes.Model.UserSession;
import Classes.Model.UserTask;

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
     * @param userSession The session of the user creating the project -> {@link UserSession}
     * @return The created project -> {@link Project}
     */
    public Project createProject(LocalDate beginDate, LocalDate limitDate, String name, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        Project project = new Project(beginDate, limitDate, name);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), project, "Project created.");
        setChanged();
        notifyObservers(log);
        return project;
    }

    /**
     * Method to add a task to a project.
     * @param project The project to add the task to -> {@link Project}
     * @param task The task to be added -> {@link Task}
     * @param userSession The session of the user adding the task -> {@link UserSession}
     */
    public void addTaskToProject(Project project, Task task, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        project.addTask(task);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), task, "Task added to project.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a task from a project.
     * @param project The project to remove the task from -> {@link Project}
     * @param task The task to be removed -> {@link Task}
     * @param userSession The session of the user removing the task -> {@link UserSession}
     */
    public void removeTaskFromProject(Project project, Task task, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        project.removeTask(task.getId());
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), task, "Task removed from project.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to add a user to a project.
     * @param project The project to add the user to -> {@link Project}
     * @param user The user to be added -> {@link User}
     * @param editorSession The session of the user adding the user -> {@link UserSession}
     */
    public void addUserToProject(Project project, User user, UserSession editorSession) {
        if (!editorSession.getStatus()) {
            throw new IllegalStateException("Editor session is not active.");
        }
        if (user == null || editorSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        UserTask userTask = (UserTask) user;
        project.addUser(userTask);
        LoggerRecordDto log = new LoggerRecordDto(editorSession.getUser(), project, "User added to project.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a user from a project.
     * @param project The project to remove the user from -> {@link Project}
     * @param user The user to be removed -> {@link User}
     * @param editorSession The session of the user removing the user -> {@link UserSession}
     */
    public void removeUserFromProject(Project project, User user, UserSession editorSession) {
        if (!editorSession.getStatus()) {
            throw new IllegalStateException("Editor session is not active.");
        }
        if (user == null || editorSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        project.removeUser(user.getId());
        LoggerRecordDto log = new LoggerRecordDto(editorSession.getUser(), project, "User removed from project.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the name of a project.
     * @param project The project to be updated -> {@link Project}
     * @param name The new name for the project -> {@link String}
     * @param userSession The session of the user updating the project name -> {@link UserSession}
     */
    public void updateProjectName(Project project, String name, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        String oldName = project.getName();
        project.setName(name);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), project, "Project name updated from '" + oldName + "' to '" + name + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the start date of a project.
     * @param project The project to be updated -> {@link Project}
     * @param beginDate The new start date for the project -> {@link LocalDate}
     * @param userSession The session of the user updating the project start date -> {@link UserSession}
     */
    public void updateProjectBeginDate(Project project, LocalDate beginDate, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        LocalDate oldBeginDate = project.getBeginDate();
        project.setBeginDate(beginDate);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), project, "Project begin date updated from '" + oldBeginDate + "' to '" + beginDate + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the end date of a project.
     * @param project The project to be updated -> {@link Project}
     * @param limitDate The new end date for the project -> {@link LocalDate}
     * @param userSession The session of the user updating the project end date -> {@link UserSession}
     */
    public void updateProjectLimitDate(Project project, LocalDate limitDate, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        LocalDate oldLimitDate = project.getLimitDate();
        project.setLimitDate(limitDate);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), project, "Project limit date updated from '" + oldLimitDate + "' to '" + limitDate + "'.");
        setChanged();
        notifyObservers(log);
    }
}