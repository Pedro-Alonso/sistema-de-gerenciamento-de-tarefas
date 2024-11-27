package Classes.Repository;

import java.util.UUID;
import java.util.Vector;

import Classes.Model.Task;

public class TaskDatabase {

    private static TaskDatabase instance = null;

    private Vector<Task> tasks;

    private TaskDatabase() {
        tasks = new Vector<>();
    }

    /**
     * Returns the singleton instance of TaskDatabase.
     * @return the singleton instance of TaskDatabase
     */
    public static synchronized TaskDatabase getInstance() {
        if (instance == null) {
            instance = new TaskDatabase();
        }
        return instance;
    }

    /**
     * Adds a task to the database.
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the list of tasks in the database.
     * @return the list of tasks
     */
    public Vector<Task> getTasks() {
        return tasks;
    }

    /**
     * Updates a task in the database by replacing the existing task with the new task.
     * @param newTask the new task to replace the existing task
     * @return true if the task was updated, false otherwise
     */
    public boolean updateTask(Task newTask) {
        UUID id = newTask.getId();
        if (removeTask(id)) {
            addTask(newTask);
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the tasks in the database.
     * @return a string representation of the tasks
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "Nenhuma tarefa cadastrada.";
        }

        StringBuilder builder = new StringBuilder("Lista de Tarefas:\n");
        for (Task task : tasks) {
            builder.append(task.printTask()).append("\n");
        }
        return builder.toString();
    }

    /**
     * Removes a task from the database.
     * @param id the ID of the task to be removed
     * @return true if the task was removed, false otherwise
     */
    public boolean removeTask(UUID id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }

    /**
     * Clears all tasks from the database.
     */
    public void clearTasks() {
        tasks.clear();
    }
}