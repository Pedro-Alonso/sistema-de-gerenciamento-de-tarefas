package Classes.Controller;

import java.util.Observable;
import java.time.LocalDate;

import Classes.DTO.LoggerRecordDto;
import Classes.DTO.SubTaskDto;
import Classes.Mapper.SubTaskMapper;
import Classes.Model.SubTask;
import Classes.Model.Task;
import Classes.Model.UserSession;
import Classes.Model.UserTask;
import Classes.Repository.SubTaskDatabase;

@SuppressWarnings("deprecation")
public class SubTaskController extends Observable {
    private final SubTaskDatabase subTaskDatabase;

    /**
     * Constructor for the SubTaskController class.
     * Initializes the SubTaskDatabase instance.
     */
    public SubTaskController() {
        super();
        this.subTaskDatabase = SubTaskDatabase.getInstance();
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
    * @param subTaskDto The subtask DTO to be created -> {@link SubTaskDto}
    * @param userSession The session of the user creating the subtask -> {@link UserSession}
    * @return The created subtask DTO -> {@link SubTaskDto}
    */
    public SubTaskDto createSubTask(Task task, SubTaskDto subTaskDto, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        UserTask user = (UserTask) userSession.getUser();
        if (user == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        SubTask subTask = SubTaskMapper.fromDto(subTaskDto);
        task.addSubTask(subTask);
        subTaskDatabase.addSubTask(subTask);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), subTask, "SubTask created.");
        setChanged();
        notifyObservers(log);
        return SubTaskMapper.toDto(subTask);
    }

    /**
     * Method to update the name of a subtask.
     * @param subTaskDto The subtask DTO to be updated -> {@link SubTaskDto}
     * @param name The new name for the subtask -> {@link String}
     * @param userSession The session of the user updating the subtask -> {@link UserSession}
     */
    public void updateSubTaskName(SubTaskDto subTaskDto, String name, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        SubTask subTask = SubTaskMapper.fromDto(subTaskDto);
        String oldName = subTask.getName();
        subTask.setName(name);
        subTaskDatabase.updateSubTask(subTask);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), subTask, "SubTask name updated from '" + oldName + "' to '" + name + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the description of a subtask.
     * @param subTaskDto The subtask DTO to be updated -> {@link SubTaskDto}
     * @param description The new description for the subtask -> {@link String}
     * @param userSession The session of the user updating the subtask -> {@link UserSession}
     */
    public void updateSubTaskDescription(SubTaskDto subTaskDto, String description, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        SubTask subTask = SubTaskMapper.fromDto(subTaskDto);
        String oldDescription = subTask.getDescription();
        subTask.setDescription(description);
        subTaskDatabase.updateSubTask(subTask);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), subTask, "SubTask description updated from '" + oldDescription + "' to '" + description + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to update the deadline of a subtask.
     * @param subTaskDto The subtask DTO to be updated -> {@link SubTaskDto}
     * @param deadline The new deadline for the subtask -> {@link LocalDate}
     * @param userSession The session of the user updating the subtask -> {@link UserSession}
     */
    public void updateSubTaskDeadline(SubTaskDto subTaskDto, LocalDate deadline, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        SubTask subTask = SubTaskMapper.fromDto(subTaskDto);
        LocalDate oldDeadline = subTask.getDeadline();
        subTask.setDeadline(deadline);
        subTaskDatabase.updateSubTask(subTask);
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), subTask, "SubTask deadline updated from '" + oldDeadline + "' to '" + deadline + "'.");
        setChanged();
        notifyObservers(log);
    }

    /**
     * Method to remove a subtask.
     * @param task The parent task of the subtask -> {@link Task}
     * @param subTaskDto The subtask DTO to be removed -> {@link SubTaskDto}
     * @param userSession The session of the user removing the subtask -> {@link UserSession}
     */
    public void removeSubTask(Task task, SubTaskDto subTaskDto, UserSession userSession) {
        if (!userSession.getStatus()) {
            throw new IllegalStateException("User session is not active.");
        }
        if (userSession.getUser() == null) {
            throw new IllegalStateException("User session is invalid.");
        }
        SubTask subTask = SubTaskMapper.fromDto(subTaskDto);
        task.removeSubTask(subTask.getId());
        subTaskDatabase.removeSubTask(subTask.getId());
        LoggerRecordDto log = new LoggerRecordDto(userSession.getUser(), subTask, "SubTask removed.");
        setChanged();
        notifyObservers(log);
    }
}