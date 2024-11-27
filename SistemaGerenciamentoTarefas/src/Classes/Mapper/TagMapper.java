
package Classes.Mapper;

import Classes.DTO.TagDto;
import Classes.Model.Tag;
import Classes.Repository.ProjectDatabase;

public class TagMapper {

    /**
     * Maps a Tag to a TagDto.
     * @param tag The tag to be mapped -> {@link Tag}
     * @return The mapped TagDto -> {@link TagDto}
     */
    public static TagDto toDto(Tag tag) {
        if (tag == null) {
            return null;
        }
        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setDescription(tag.getDescription());
        tagDto.setProjectId(tag.getProjectID());
        return tagDto;
    }

    /**
     * Maps a TagDto to a Tag.
     * @param tagDto The TagDto to be mapped -> {@link TagDto}
     * @return The mapped Tag -> {@link Tag}
     */
    public static Tag fromDto(TagDto tagDto) {
        if (tagDto == null) {
            return null;
        }
        Tag tag = new Tag(
            tagDto.getId(),
            tagDto.getDescription(),
            ProjectDatabase.getInstance().getProjectById(tagDto.getProjectId()).getId()
        );
        return tag;
    }
}