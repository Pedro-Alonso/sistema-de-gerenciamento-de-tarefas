
package Classes.Mapper;

import Classes.DTO.TaskCommentDto;
import Classes.Model.TaskComment;
import Classes.Repository.TaskDatabase;
import Classes.Repository.UserDatabase;

public class TaskCommentMapper {

    /**
     * Maps a TaskComment to a TaskCommentDto.
     * @param taskComment The task comment to be mapped -> {@link TaskComment}
     * @return The mapped TaskCommentDto -> {@link TaskCommentDto}
     */
    public static TaskCommentDto toDto(TaskComment taskComment) {
        if (taskComment == null) {
            return null;
        }
        TaskCommentDto taskCommentDto = new TaskCommentDto();
        taskCommentDto.setId(taskComment.getId());
        taskCommentDto.setText(taskComment.getText());
        taskCommentDto.setAuthorId(taskComment.getAuthor().getId());
        taskCommentDto.setTaskId(taskComment.getTaskId());
        return taskCommentDto;
    }

    /**
     * Maps a TaskCommentDto to a TaskComment.
     * @param taskCommentDto The TaskCommentDto to be mapped -> {@link TaskCommentDto}
     * @return The mapped TaskComment -> {@link TaskComment}
     */
    public static TaskComment fromDto(TaskCommentDto taskCommentDto) {
        if (taskCommentDto == null) {
            return null;
        }
        TaskComment taskComment = new TaskComment(
            taskCommentDto.getId(),
            taskCommentDto.getText(),
            UserDatabase.getInstance().getUserById(taskCommentDto.getAuthorId()).getId(),
            TaskDatabase.getInstance().getTaskById(taskCommentDto.getTaskId()).getId()
        );
        return taskComment;
    }
}