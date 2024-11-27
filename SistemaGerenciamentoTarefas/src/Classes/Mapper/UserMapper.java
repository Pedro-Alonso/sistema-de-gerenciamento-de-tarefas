
package Classes.Mapper;

import java.util.UUID;

import Classes.DTO.UserDto;
import Classes.Model.User;
import Classes.Model.UserTask;

public class UserMapper {

    /**
     * Maps a User to a UserDto.
     * @param user The user to be mapped -> {@link User}
     * @return The mapped UserDto -> {@link UserDto}
     */
    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getUserEmail());
        return userDto;
    }

    /**
     * Maps a UserDto to a User.
     * @param userDto The UserDto to be mapped -> {@link UserDto}
     * @return The mapped User -> {@link User}
     */
    public static User fromDto(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        UUID id = userDto.getId();
        String username = userDto.getUsername();
        String email = userDto.getEmail();
        UserTask user = new UserTask(id, username, email);
        return user;
    }
}