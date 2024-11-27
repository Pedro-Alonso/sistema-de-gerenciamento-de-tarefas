package Classes.Controller;

import java.util.Observable;
import java.time.LocalDate;

import Classes.DTO.LoggerRecordDto;
import Classes.Model.SubTask;
import Classes.Model.Task;
import Classes.Model.UserSession;
import Classes.Model.UserTask;

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
    * @param userSession The session of the user creating the subtask -> {@link UserSession}
    * @return The created subtask -> {@link SubTask}
    */
    public SubTask createSubTask(Task task, String name, String description, LocalDate deadline, int gravity, int urgency, int trend, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask user = (UserTask) userSession.getUser();
        if (user == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        SubTask subTask = new SubTask(user, name, description, deadline, gravity, urgency, trend);
        task.addSubTask(subTask);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), subTask, "SubTask created.");
        setChanged();
        notifyObservers(log);
        return subTask;
    }
        
    /**
     * Method to update the name of a subtask.
     * @param subTask The subtask to be updated -> {@link SubTask}
     * @param name The new name for the subtask -> {@link String}
     * @param userSession The session of the user updating the subtask -> {@link UserSession}
     */
    public void updateSubTaskName(SubTask subTask, String name, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        String oldName = subTask.getName();
        subTask.setName(name);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), subTask, "SubTask name updated from '" + oldName + "' to '" + name + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the description of a subtask.
     * @param subTask The subtask to be updated -> {@link SubTask}
     * @param description The new description for the subtask -> {@link String}
     * @param userSession The session of the user updating the subtask -> {@link UserSession}
     */
    public void updateSubTaskDescription(SubTask subTask, String description, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        String oldDescription = subTask.getDescription();
        subTask.setDescription(description);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), subTask, "SubTask description updated from '" + oldDescription + "' to '" + description + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the deadline of a subtask.
     * @param subTask The subtask to be updated -> {@link SubTask}
     * @param deadline The new deadline for the subtask -> {@link LocalDate}
     * @param userSession The session of the user updating the subtask -> {@link UserSession}
     */
    public void updateSubTaskDeadline(SubTask subTask, LocalDate deadline, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        LocalDate oldDeadline = subTask.getDeadline();
        subTask.setDeadline(deadline);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), subTask, "SubTask deadline updated from '" + oldDeadline + "' to '" + deadline + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a subtask.
     * @param task The parent task of the subtask -> {@link Task}
     * @param subTask The subtask to be removed -> {@link SubTask}
     * @param userSession The session of the user removing the subtask -> {@link UserSession}
     */
    public void removeSubTask(Task task, SubTask subTask, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        task.removeSubTask(subTask.getId());
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), subTask, "SubTask removed.");
        setChanged();
        notifyObservers(log);
    }
}