
package Classes.Mapper;

import Classes.DTO.ProjectDto;
import Classes.Model.Project;

public class ProjectMapper {

    /**
     * Converts a Project entity to a ProjectDto.
     * @param project The project entity to be converted -> {@link Project}
     * @return The converted ProjectDto -> {@link ProjectDto}
     */
    public static ProjectDto toDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setBeginDate(project.getBeginDate());
        projectDto.setLimitDate(project.getLimitDate());
        return projectDto;
    }

    /**
     * Converts a ProjectDto to a Project entity.
     * @param projectDto The ProjectDto to be converted -> {@link ProjectDto}
     * @return The converted Project entity -> {@link Project}
     */
    public static Project fromDto(ProjectDto projectDto) {
        Project project = new Project(
            projectDto.getId(),
            projectDto.getName(),
            projectDto.getBeginDate(),
            projectDto.getLimitDate()
        );
        return project;
    }
}