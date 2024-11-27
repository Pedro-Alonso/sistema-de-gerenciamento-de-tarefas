
package Classes.Mapper;

import Classes.DTO.TaskDto;
import Classes.Model.Task;

public class TaskMapper {

    /**
     * Maps a Task to a TaskDto.
     * @param task The task to be mapped -> {@link Task}
     * @return The mapped TaskDto -> {@link TaskDto}
     */
    public static TaskDto toDto(Task task) {
        if (task == null) {
            return null;
        }
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setDeadline(task.getDeadline());
        taskDto.setGravity(task.getGravity());
        taskDto.setUrgency(task.getUrgency());
        taskDto.setTrend(task.getTrend());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }

    /**
     * Maps a TaskDto to a Task.
     * @param taskDto The TaskDto to be mapped -> {@link TaskDto}
     * @return The mapped Task -> {@link Task}
     */
    public static Task fromDto(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }
        Task task = new Task(
        taskDto.getId(),
        taskDto.getName(),
        taskDto.getDescription(),
        taskDto.getDeadline(),
        taskDto.getGravity(),
        taskDto.getUrgency(),
        taskDto.getTrend(),
        taskDto.getStatus()
        );
        return task;
    }
}