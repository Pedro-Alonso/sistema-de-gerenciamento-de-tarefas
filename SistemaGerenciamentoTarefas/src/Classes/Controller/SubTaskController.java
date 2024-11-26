
package Classes.Controller;

import java.util.Observable;
import java.time.LocalDate;

import Classes.SubTask;
import Classes.Task;
import Classes.user.UserTask;
import Classes.DTO.LoggerRecordDto;

@SuppressWarnings("deprecation")
public class SubTaskController extends Observable {
    /**
     * Constructor for the SubTaskController class.
     */
    public SubTaskController() {
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
    * Method to create a subtask.
    * @param task The parent task of the subtask -> {@link Task}
    * @param name The name of the subtask -> {@link String}
    * @param description The description of the subtask -> {@link String}
    * @param deadline The deadline of the subtask -> {@link LocalDate}
    * @param gravity The gravity of the subtask -> {@link int}
    * @param urgency The urgency of the subtask -> {@link int}
    * @param trend The trend of the subtask -> {@link int}
    * @param userTask The user creating the subtask -> {@link UserTask}
    * @return The created subtask -> {@link SubTask}
    */
    public SubTask createSubTask(Task task, String name, String description, LocalDate deadline, int gravity, int urgency, int trend, UserTask userTask) {
        SubTask subTask = new SubTask(userTask, name, description, deadline, gravity, urgency, trend);
        task.addSubTask(subTask);
        LoggerRecordDto log = new LoggerRecordDto(userTask, subTask, "SubTask created.");
        setChanged();
        notifyObservers(log);
        return subTask;
    }
        
    /**
     * Method to update the name of a subtask.
     * @param subTask The subtask to be updated -> {@link SubTask}
     * @param name The new name for the subtask -> {@link String}
     * @param userTask The user updating the subtask -> {@link UserTask}
     */
    public void updateSubTaskName(SubTask subTask, String name, UserTask userTask) {
        String oldName = subTask.getName();
        subTask.setName(name);
        LoggerRecordDto log = new LoggerRecordDto(userTask, subTask, "SubTask name updated from '" + oldName + "' to '" + name + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the description of a subtask.
     * @param subTask The subtask to be updated -> {@link SubTask}
     * @param description The new description for the subtask -> {@link String}
     * @param userTask The user updating the subtask -> {@link UserTask}
     */
    public void updateSubTaskDescription(SubTask subTask, String description, UserTask userTask) {
        String oldDescription = subTask.getDescription();
        subTask.setDescription(description);
        LoggerRecordDto log = new LoggerRecordDto(userTask, subTask, "SubTask description updated from '" + oldDescription + "' to '" + description + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the deadline of a subtask.
     * @param subTask The subtask to be updated -> {@link SubTask}
     * @param deadline The new deadline for the subtask -> {@link LocalDate}
     * @param userTask The user updating the subtask -> {@link UserTask}
     */
    public void updateSubTaskDeadline(SubTask subTask, LocalDate deadline, UserTask userTask) {
        LocalDate oldDeadline = subTask.getDeadline();
        subTask.setDeadline(deadline);
        LoggerRecordDto log = new LoggerRecordDto(userTask, subTask, "SubTask deadline updated from '" + oldDeadline + "' to '" + deadline + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a subtask.
     * @param task The parent task of the subtask -> {@link Task}
     * @param subTask The subtask to be removed -> {@link SubTask}
     * @param userTask The user removing the subtask -> {@link UserTask}
     */
    public void removeSubTask(Task task, SubTask subTask, UserTask userTask) {
        task.removeSubTask(subTask.getId());
        LoggerRecordDto log = new LoggerRecordDto(userTask, subTask, "SubTask removed.");
        setChanged();
        notifyObservers(log);
    }
}