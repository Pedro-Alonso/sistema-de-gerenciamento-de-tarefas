package Classes.Controller;

import java.util.Observable;
import java.time.LocalDate;

import Classes.DTO.LoggerRecordDto;
import Classes.Model.Project;
import Classes.Model.UserSession;
import Classes.Model.UserTask;
import Classes.Repository.ProjectDatabase;

@SuppressWarnings("deprecation")
public class ProjectController extends Observable {
    private final ProjectDatabase projectDatabase;

    /**
     * Constructor for the ProjectController class.
     * Initializes the ProjectDatabase instance.
     */
    public ProjectController() {
        super();
        this.projectDatabase = ProjectDatabase.getInstance();
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
     * @param userSession The user session creating the project -> {@link UserSession}
     * @param project The project to be created -> {@link Project}
     */
    public void createProject(UserSession userSession, Project project) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        projectDatabase.addProject(project);
        LoggerRecordDto log = new LoggerRecordDto(userTask, project, "Project created.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update a project name.
     * @param userSession The user session updating the project -> {@link UserSession}
     * @param project The project to be updated -> {@link Project}
     * @param name The new name for the project -> {@link String}
     */
    public void updateProjectName(UserSession userSession, Project project, String name) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        project.setName(name);
        projectDatabase.updateProject(project);
        LoggerRecordDto log = new LoggerRecordDto(userTask, project, "Project name updated.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update a project deadline.
     * @param userSession The user session updating the project -> {@link UserSession}
     * @param project The project to be updated -> {@link Project}
     * @param deadline The new deadline for the project -> {@link LocalDate}
     */
    public void updateProjectDeadline(UserSession userSession, Project project, LocalDate deadline) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        project.setLimitDate(deadline);
        projectDatabase.updateProject(project);
        LoggerRecordDto log = new LoggerRecordDto(userTask, project, "Project deadline updated.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to add a user to a project.
     * @param userSession The user session adding the user -> {@link UserSession}
     * @param project The project to add the user to -> {@link Project}
     * @param userTask The user to be added -> {@link UserTask}
     */
    public void addUserToProject(UserSession userSession, Project project, UserTask userTask) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userTask == null) {
            throw new IllegalStateException("User task is invalid.");
        }
        project.addUser(userTask);
        projectDatabase.updateProject(project);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), project, "User added to project.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a user from a project.
     * @param userSession The user session removing the user -> {@link UserSession}
     * @param project The project to remove the user from -> {@link Project}
     * @param userTask The user to be removed -> {@link UserTask}
     */
    public void removeUserFromProject(UserSession userSession, Project project, UserTask userTask) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userTask == null) {
            throw new IllegalStateException("User task is invalid.");
        }
        project.removeUser(userTask.getId());
        projectDatabase.updateProject(project);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), project, "User removed from project.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a project.
     * @param userSession The user session removing the project -> {@link UserSession}
     * @param project The project to be removed -> {@link Project}
     */
    public void removeProject(UserSession userSession, Project project) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        projectDatabase.removeProject(project.getId());
        LoggerRecordDto log = new LoggerRecordDto(userTask, project, "Project removed.");
        setChanged();
        notifyObservers(log);
    }
}