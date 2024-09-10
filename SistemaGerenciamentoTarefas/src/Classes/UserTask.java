package SistemaGerenciamentoTarefas.src.Classes;

import java.util.ArrayList;
import java.util.UUID;

public class UserTask extends User{
    private ArrayList<Task> tasks;
    private ArrayList<Project> projects;

    public UserTask(String userName, String email, String password, ArrayList<Task> tasks, ArrayList<Project> projects) {
        super(userName, email, password);
        this.tasks = tasks;
        this.projects = projects;
    }

    public ArrayList<Task> getTasks() { return tasks; }
    public void setTasks(ArrayList<Task> tasks) { this.tasks = tasks; }
    public ArrayList<Project> getProjects() { return projects; }
    public void setProjects(ArrayList<Project> projects) { this.projects = projects; }

    public void addTask(Task newTask) {
        Task checker = getTaskById(newTask.getId());
        if (checker == null) tasks.add(newTask);
    }
    private boolean removeTaskById(UUID id) {
        int tagIndex = getTaskIndex(id);
        if (tagIndex == -1) return false;
        tasks.remove(tagIndex);
        return true;
    }

    private Task getTaskById(UUID id) {
        for (Task task : tasks) {
            if (task.getId() == id) return task;
        }
        return null;
    }

    private int getTaskIndex(UUID id) {
        int index = -1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void printTasks() {
        for (Task task : tasks) {
            System.out.println(task.printTask());
        }
    }
}
