/*
package SistemaGerenciamentoTarefas.src.Classes.user;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {
    private static final String FILE_PATH = "users.json";
    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();

    public List<User> loadUsers() {
       File file = new File(FILE_PATH);

       if (!file.exists() || file.length() == 0) {
           System.out.println("No data found or file is empty. Returning an empty list.");
           return new ArrayList<>();
       }

        try (FileReader reader = new FileReader(file)) {
            Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
            return gson.fromJson(reader, userListType);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Returning an empty list.");
            return new ArrayList<>();
        } catch (EOFException | JsonSyntaxException e) {
            System.out.println("File is empty or improperly formatted. Returning an empty list.");
            return new ArrayList<>();
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

    public static void main(String[] args) {
        UserRepository  userRepository = new UserRepository();

        List<User> users = userRepository.loadUsers();

        System.out.println("Loaded users: " + users);

        User newUser = new User("name", "email", "senha");
        users.add(newUser);
        userRepository.saveUsers(users);
        System.out.println("Added new user: " + newUser);

        if (userRepository.updateUser(newUser.getUserId(), "newName", "newEmail", "newSenha")) {
            System.out.println("Updated user: " + newUser.getUserId());
        } else {
            System.out.println("User not found.");
        }

        users = userRepository.loadUsers();
        System.out.println("Users after update: " + users);
    }
}
*/