package Classes.Repository;

import java.util.UUID;
import java.util.Vector;
import Classes.Model.Project;

public class ProjectDatabase {

    private static ProjectDatabase instance = null;

    private Vector<Project> projects;

    private ProjectDatabase() {
        projects = new Vector<>();
    }

    /**
     * Returns the singleton instance of ProjectDatabase.
     * @return the singleton instance of ProjectDatabase
     */
    public static synchronized ProjectDatabase getInstance() {
        if (instance == null) {
            instance = new ProjectDatabase();
        }
        return instance;
    }

    /**
     * Adds a project to the database.
     * @param project the project to be added
     */
    public void addProject(Project project) {
        projects.add(project);
    }

    /**
     * Returns the list of projects in the database.
     * @return the list of projects
     */
    public Vector<Project> getProjects() {
        return projects;
    }

    /**
     * Updates a project in the database by replacing the existing project with the new project.
     * @param newProject the new project to replace the existing project
     * @return true if the project was updated, false otherwise
     */
    public boolean updateProject(Project newProject) {
        UUID id = newProject.getId();
        if (removeProject(id)) {
            addProject(newProject);
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the projects in the database.
     * @return a string representation of the projects
     */
    @Override
    public String toString() {
        if (projects.isEmpty()) {
            return "Nenhum projeto cadastrado.";
        }

        StringBuilder builder = new StringBuilder("Lista de Projetos:\n");
        for (Project project : projects) {
            builder.append(project).append("\n");
        }
        return builder.toString();
    }

    /**
     * Removes a project from the database.
     * @param id the ID of the project to be removed
     * @return true if the project was removed, false otherwise
     */
    public boolean removeProject(UUID id) {
        return projects.removeIf(project -> project.getId().equals(id));
    }

    /**
     * Clears all projects from the database.
     */
    public void clearProjects() {
        projects.clear();
    }
}