package SistemaGerenciamentoTarefas.src.Classes.user;


import SistemaGerenciamentoTarefas.src.Classes.Project;
import SistemaGerenciamentoTarefas.src.Classes.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class UserTask extends User{
    private ArrayList<Task> tasks;
    private ArrayList<Project> projects;
    private HashMap<UUID, Task> taskMap;
    private HashMap<UUID, Project> projectMap;

    public UserTask(String userName, String email, String password) {
        super(userName, email, password);
        this.tasks = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.taskMap = new HashMap<>();
        this.projectMap = new HashMap<>();
    }

    /**
     * Method to add a Task to tasks
     * @param newTask The Task that will be added -> {@link Task}
     * @return {@code boolean} | {@code true} if it was added, {@code false} otherwise
     * @throws Exception if an error occurs while adding the Task
     */
    public boolean addTask(Task newTask) {
        try {
            // Checks if the newTask already exists
            if (taskMap.containsKey(newTask.getId())) return false;

            // Add the task to tasks
            tasks.add(newTask);
            taskMap.put(newTask.getId(), newTask);
            return true;
        } catch (Exception e) {
            //Handle any unexpected exceptions
            System.out.println("Erro ao adicionar tarefa: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method to remove a Task from a tasks by its ID
     * @param taskId The ID of the Task will be removed -> {@link UUID}
     * @return {@code boolean} | {@code true} if it was removed, {@code false} otherwise
     * @throws Exception if an error occurs while removing the Task
     */
    private boolean removeTaskById(UUID taskId) {
        try {
            //removes the task from taskMap and gets the task;
            Task task = taskMap.remove(taskId);
            if (task == null) return false;

            // Remove the task from tasks
            tasks.remove(task);
            return true;
        } catch (Exception e) {
            //Handle any unexpected exceptions
            System.out.println("Erro ao remover a tarefa do array: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method to edit a task from tasks, null param will be ignored
     * @param taskId The ID of the Task that will be edited -> {@link UUID}
     * @param newName The new name of the Task -> {@link String}
     * @param newDescription The new description of the Task -> {@link String}
     * @param newDeadLine The new deadline of the Task -> {@link LocalDate}
     * @param newPriority The new priority of the Task -> {@link Integer}
     * @param newStatus The new status of the Task -> {@link SistemaGerenciamentoTarefas.src.Classes.Task.TaskStatus}
     * @return {@code boolean} | {@code true} if it was edited, {@code false} otherwise
     */
    public boolean editTask(UUID taskId, String newName, String newDescription, LocalDate newDeadLine, Integer newPriority, Task.TaskStatus newStatus) {
        Task task = taskMap.get(taskId);
        if (task == null) return false;

        boolean updated = false;

        if (newName != null && !newName.trim().isEmpty()) {
            task.setName(newName);
            updated = true;
        }
        if (newDescription != null) {
            task.setDescription(newDescription);
            updated = true;
        }
        if (newDeadLine != null) {
            task.setDeadline(newDeadLine);
            updated = true;
        }
        if (newPriority != null) {
            task.setPriority(newPriority);
            updated = true;
        }
        if (newStatus != null) {
            task.setStatus(newStatus);
            updated = true;
        }

        return updated;
    }

    /**
     * Method to add a Project to projects
     * @param newProject The Project that will be added -> {@link Project}
     * @return {@code boolean} | {@code true} if it was added, {@code false} otherwise
     * @throws Exception if an error occurs while adding the Project
     */
    public boolean addProject(Project newProject) {
        try {
            // Checks if the Project already exists
            if (projectMap.containsKey(newProject.getId())) return false;

            // Add the Project to projects
            projects.add(newProject);
            projectMap.put(newProject.getId(), newProject);
            return true;
        } catch (Exception e) {
            //Handle any unexpected exceptions
            System.out.println("Erro ao remover o projeto do array: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method to remove a Project from projects by its ID
     * @param projectId The ID of the Project that will be removed -> {@link UUID}
     * @return {@code boolean} | {@code true} if it was removed, {@code false} otherwise
     * @throws Exception if an error occurs while removing the Project
     */
    public boolean removeProjectById(UUID projectId) {
        try {
            Project project = projectMap.remove(projectId);
            if (project == null) return false;

            // Remove the Project from projects
            projects.remove(project);
            return true;
        } catch (Exception e) {
            //Handle any unexpected exceptions
            System.out.println("Erro ao remover o projeto do array: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method to edit a Project from projects, null param will be ignored
     * @param projectId The ID of the Project that will be edited -> {@link UUID}
     * @param newName The new name of the Project -> {@link String}
     * @param newBeginDate The new start date of the Project -> {@link LocalDate}
     * @param newLimitDate The new limit date of the Project -> {@link LocalDate}
     * @return {@code boolean} | {@code true} if it was edited, {@code false} otherwise
     */
    public boolean editProject(UUID projectId, LocalDate newBeginDate, LocalDate newLimitDate, String newName) {
        Project project = projectMap.get(projectId);
        if (project == null) return false;

        boolean updated = false;

        if (newName != null && !newName.trim().isEmpty()) {
            project.setName(newName);
            updated = true;
        }
        if (newBeginDate != null) {
            project.setBeginDate(newBeginDate);
            updated = true;
        }
        if (newLimitDate != null) {
            project.setLimitDate(newLimitDate);
            updated = true;
        }
        return updated;
    }

    /**
     * Method to assign a Task to a Project
     * @param projectId The ID of the Project that the task will be added to -> {@link UUID}
     * @param newTask The Task that will be added -> {@link Task}
     * @return {@code boolean} | {@code true} if it was removed, {@code false} otherwise
     */
    public boolean addTaskToProject(UUID projectId, Task newTask) {
            // Get the project by its ID
            Project project = projectMap.get(projectId);
            if (project == null) return false; // Project not found

            // Check if the task already exists in the project
            if (project.searchTask(newTask.getId()) != -1) return false; // Task already exists
            // Add the task from the project
            project.addTask(newTask);
            return true;
    }

    /**
     * Method to remove a Task from a Project by its ID
     * @param projectId The ID of the Project that the task will be removed -> {@link UUID}
     * @param taskID The ID of the Task will be removed -> {@link UUID}
     * @private int getTaskIndex(UUID id) {
        return taskMap.get(id);
    }return {@code boolean} | {@code true} if it was removed, {@code false} otherwise
     * @throws Exception if an error occurs while removing the Task
     */
    public boolean removeTaskFromProject(UUID projectId, UUID taskID) {
            // Get the index of the project by its ID
            Project project = projectMap.get(projectId);
            if (project == null) return false; // Project not found

            // Check if the task exists in the project
            if (project.searchTask(taskID) == -1) return false; // Task not found

            // Remove the task from the project
            project.removeTask(taskID);
            return true;
    }

    /**
     * Method to edit a Task from a Project, null param will be ignored
     * @param taskId The ID of the Task that will be edited -> {@link UUID}
     * @param projectId The ID of the Project that the task is in -> {@link UUID}
     * @param newName The new name of the Task -> {@link String}
     * @param newDescription The new description of the Task -> {@link String}
     * @param newDeadLine The new deadline of the Task -> {@link LocalDate}
     * @param newPriority The new priority of the Task -> {@link Integer}
     * @param newStatus The new status of the Task -> {@link SistemaGerenciamentoTarefas.src.Classes.Task.TaskStatus}
     * @return {@code boolean} | {@code true} if it was edited, {@code false} otherwise
     */
    public boolean editTaskInProject(UUID projectId, UUID taskId, String newName, String newDescription, LocalDate newDeadLine, Integer newPriority, Task.TaskStatus newStatus) {
        Project project = projectMap.get(projectId);

        if (project == null) return false;

        int taskIndex = project.searchTask(taskId);

        if (taskIndex == -1) return false;

        Task task = project.getTaskArray().get(taskIndex);

        boolean updated = false;

        if (newName != null && !newName.trim().isEmpty()) {
            task.setName(newName);
            updated = true;
        }
        if (newDescription != null) {
            task.setDescription(newDescription);
            updated = true;
        }
        if (newDeadLine != null) {
            task.setDeadline(newDeadLine);
            updated = true;
        }
        if (newPriority != null) {
            task.setPriority(newPriority);
            updated = true;
        }
        if (newStatus != null) {
            task.setStatus(newStatus);
            updated = true;
        }

        return updated;
    }

    /**
     * Method that returns a String with all the information about all the tasks of the User
     * @return {@link String}
     */
    public String printTasks() {
        StringBuilder sb = new StringBuilder();
        tasks.forEach(task -> sb.append(task.printTask()).append("\n"));
        return sb.toString();
    }

    /**
     * Method that returns a String with all the information about all the projects of the User
     * @return {@link String}
     */
    public String printProjects() {
        StringBuilder sb = new StringBuilder();
        projects.forEach(project -> sb.append(project.toString()).append("\n"));
        return sb.toString();
    }

    //getters & setters

    /**
     * {@return the Task using its ID {@link Task}}
     */
    public Task getTaskById(UUID id) {
        return taskMap.get(id);
    }

    /**
     * {@return the Project using its ID {@link Project}}
     */
    public Project getProjectById(UUID id) {
        return projectMap.get(id);
    }

    /**
     * {@return the number of tasks of the UserTask as a {@link int}}
     */
    public int getTaskNumber() {return tasks.size(); }
    /**
     * {@return the number of projects of the UserTask as a {@link int}}
     */
    public int getProjectNumber() {return projects.size(); }

    /**
     * Method to get the index of a specific Task on this User tasks
     * @param id The id of the Task to be searched -> {@link UUID}
     * @return {@code int} | {@code -1} if not exists
     */
    private int getTaskIndex(UUID id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method to get the index of a specific Project on this User projects
     * @param id The id of the Project to be searched -> {@link UUID}
     * @return {@code int} | {@code -1} if not exists
     */
    public int getProjectIndex(UUID id) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getId().equals(id)) return i;
        }
        return -1;
    }

    /**
     * {@return the ArrayList of the tasks of the UserTask as a {@link ArrayList<Task>}}
     */
    public ArrayList<Task> getTasks() { return tasks; }
    /**
     * Setter for the tasks of the User
     * @param tasks The new array of tasks for the User -> {@link ArrayList<Task>}
     */
    public void setTasks(ArrayList<Task> tasks) { this.tasks = tasks; }
    /**
     * {@return the ArrayList of the projects of the UserTask as a {@link ArrayList<Project>}}
     */
    public ArrayList<Project> getProjects() { return projects; }

    /**
     * Setter for the projects of the User
     * @param projects The new array of projects for the User -> {@link ArrayList<Project>}
     */
    public void setProjects(ArrayList<Project> projects) { this.projects = projects; }

}
