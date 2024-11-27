
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
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setBeginDate(project.getBeginDate());
        dto.setLimitDate(project.getLimitDate());
        dto.setName(project.getName());
        return dto;
    }

    /**
     * Converts a ProjectDto to a Project entity.
     * @param projectDto The ProjectDto to be converted -> {@link ProjectDto}
     * @return The converted Project entity -> {@link Project}
     */
    public static Project fromDto(ProjectDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ProjectDto cannot be null");
        }
        Project project = new Project(dto.getBeginDate(), dto.getLimitDate(), dto.getName());
        project.setId(dto.getId());
        return project;
    }
}