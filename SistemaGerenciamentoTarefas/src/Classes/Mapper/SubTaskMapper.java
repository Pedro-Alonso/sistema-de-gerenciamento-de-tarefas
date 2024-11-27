
package Classes.Mapper;

import Classes.DTO.SubTaskDto;
import Classes.Model.SubTask;

public class SubTaskMapper {

    /**
     * Maps a SubTask to a SubTaskDto.
     * @param subTask The subtask to be mapped -> {@link SubTask}
     * @return The mapped SubTaskDto -> {@link SubTaskDto}
     */
    public static SubTaskDto toDto(SubTask subTask) {
        if (subTask == null) {
            return null;
        }
        SubTaskDto subTaskDto = new SubTaskDto();
        subTaskDto.setId(subTask.getId());
        subTaskDto.setName(subTask.getName());
        subTaskDto.setDescription(subTask.getDescription());
        subTaskDto.setDeadline(subTask.getDeadline());
        subTaskDto.setGravity(subTask.getGravity());
        subTaskDto.setUrgency(subTask.getUrgency());
        subTaskDto.setTrend(subTask.getTrend());
        subTaskDto.setStatus(subTask.getStatus());
        return subTaskDto;
    }

    /**
     * Maps a SubTaskDto to a SubTask.
     * @param subTaskDto The SubTaskDto to be mapped -> {@link SubTaskDto}
     * @return The mapped SubTask -> {@link SubTask}
     */
    public static SubTask fromDto(SubTaskDto subTaskDto) {
        if (subTaskDto == null) {
            return null;
        }
        SubTask subTask = new SubTask(
            subTaskDto.getId(),
            subTaskDto.getName(),
            subTaskDto.getDescription(),
            subTaskDto.getDeadline(),
            subTaskDto.getGravity(),
            subTaskDto.getUrgency(),
            subTaskDto.getTrend(),
            subTaskDto.getStatus(),
            subTaskDto.getSteps(),
            subTaskDto.getStepsStatus()
        );
        return subTask;
    }
}