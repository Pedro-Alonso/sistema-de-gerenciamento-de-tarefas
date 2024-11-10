package SistemaGerenciamentoTarefas.src.Classes.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {
    private static final String FILE_PATH = "users.json";
    private final Gson gson = new Gson();

    public List<User> loadUsers() {
        try (FileReader reader = new FileReader((FILE_PATH))) {
            Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
            return gson.fromJson(reader, userListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveUsers(List<User> users) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean updateUser(UUID id, String newUsername, String newEmail, String newPassword) {
        List<User> users = loadUsers();

        for (User user : users) {
            if (user.getUserId().equals(id)) {
                if (newUsername != null) user.setUsername(newUsername);
                if (newEmail != null) user.setUserEmail(newEmail);
                if (newPassword != null) user.setUserPassword(newPassword);

                saveUsers(users);
                return true;
            }
        }
        return false;
    }
}
