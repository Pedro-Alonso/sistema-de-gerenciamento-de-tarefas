package Classes.Persistency;

import java.util.UUID;
import java.util.Vector;

import Classes.Model.SubTask;

public class SubTaskDatabase {

    private static SubTaskDatabase instance = null;

    private Vector<SubTask> subTasks;

    private SubTaskDatabase() {
        subTasks = new Vector<>();
    }

    /**
     * Returns the singleton instance of SubTaskDatabase.
     * @return the singleton instance of SubTaskDatabase
     */
    public static synchronized SubTaskDatabase getInstance() {
        if (instance == null) {
            instance = new SubTaskDatabase();
        }
        return instance;
    }

    /**
     * Adds a subtask to the database.
     * @param subTask the subtask to be added
     */
    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
    }

    /**
     * Returns the list of subtasks in the database.
     * @return the list of subtasks
     */
    public Vector<SubTask> getSubTasks() {
        return subTasks;
    }

    /**
     * Updates a subtask in the database by replacing the existing subtask with the new subtask.
     * @param newSubTask the new subtask to replace the existing subtask
     * @return true if the subtask was updated, false otherwise
     */
    public boolean updateSubTask(SubTask newSubTask) {
        UUID id = newSubTask.getId();
        if (removeSubTask(id)) {
            addSubTask(newSubTask);
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the subtasks in the database.
     * @return a string representation of the subtasks
     */
    @Override
    public String toString() {
        if (subTasks.isEmpty()) {
            return "Nenhuma sub-tarefa cadastrada.";
        }

        StringBuilder builder = new StringBuilder("Lista de Sub-Tarefas:\n");
        for (SubTask subTask : subTasks) {
            builder.append(subTask).append("\n");
        }
        return builder.toString();
    }

    /**
     * Removes a subtask from the database.
     * @param id the ID of the subtask to be removed
     * @return true if the subtask was removed, false otherwise
     */
    public boolean removeSubTask(UUID id) {
        return subTasks.removeIf(subTask -> subTask.getId().equals(id));
    }

    /**
     * Clears all subtasks from the database.
     */
    public void clearSubTasks() {
        subTasks.clear();
    }
}