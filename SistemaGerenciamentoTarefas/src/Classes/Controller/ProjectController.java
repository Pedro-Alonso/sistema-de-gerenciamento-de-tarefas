package Classes.Controller;

import java.util.Observable;
import java.time.LocalDate;

import Classes.DTO.LoggerRecordDto;
import Classes.DTO.ProjectDto;
import Classes.Mapper.ProjectMapper;
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
     * @param projectDto The project DTO to be created -> {@link ProjectDto}
     * @return The created project DTO -> {@link ProjectDto}
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

    public ProjectDto createProjectDto(LocalDate beginDate, LocalDate limitDate, String name, UserSession userSession) {
        Project project = createProject(beginDate, limitDate, name, userSession);
        return ProjectMapper.toDto(project);
    }

    /**
     * Method to update a project name.
     * @param userSession The user session updating the project -> {@link UserSession}
     * @param projectDto The project DTO to be updated -> {@link ProjectDto}
     * @param name The new name for the project -> {@link String}
     */
    public void updateProjectName(UserSession userSession, ProjectDto projectDto, String name) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        Project project = ProjectMapper.fromDto(projectDto);
        project.setName(name);
        projectDatabase.updateProject(project);
        LoggerRecordDto log = new LoggerRecordDto(userTask, project, "Project name updated.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update a project deadline.
     * @param userSession The user session updating the project -> {@link UserSession}
     * @param projectDto The project DTO to be updated -> {@link ProjectDto}
     * @param deadline The new deadline for the project -> {@link LocalDate}
     */
    public void updateProjectDeadline(UserSession userSession, ProjectDto projectDto, LocalDate deadline) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        Project project = ProjectMapper.fromDto(projectDto);
        project.setLimitDate(deadline);
        projectDatabase.updateProject(project);
        LoggerRecordDto log = new LoggerRecordDto(userTask, project, "Project deadline updated.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to add a user to a project.
     * @param userSession The user session adding the user -> {@link UserSession}
     * @param projectDto The project DTO to add the user to -> {@link ProjectDto}
     * @param userTask The user to be added -> {@link UserTask}
     */
    public void addUserToProject(UserSession userSession, ProjectDto projectDto, UserTask userTask) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userTask == null) {
            throw new IllegalStateException("User task is invalid.");
        }
        Project project = ProjectMapper.fromDto(projectDto);
        if (project == null) {
            throw new IllegalStateException("Project is invalid.");
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
     * @param projectDto The project DTO to remove the user from -> {@link ProjectDto}
     * @param userTask The user to be removed -> {@link UserTask}
     */
    public void removeUserFromProject(UserSession userSession, ProjectDto projectDto, UserTask userTask) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userTask == null) {
            throw new IllegalStateException("User task is invalid.");
        }
        Project project = ProjectMapper.fromDto(projectDto);
        project.removeUser(userTask.getId());
        projectDatabase.updateProject(project);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), project, "User removed from project.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a project.
     * @param userSession The user session removing the project -> {@link UserSession}
     * @param projectDto The project DTO to be removed -> {@link ProjectDto}
     */
    public void removeProject(UserSession userSession, ProjectDto projectDto) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask userTask = (UserTask) userSession.getUser();
        if (userTask == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        Project project = ProjectMapper.fromDto(projectDto);
        projectDatabase.removeProject(project.getId());
        LoggerRecordDto log = new LoggerRecordDto(userTask, project, "Project removed.");
        setChanged();
        notifyObservers(log);
    }
}