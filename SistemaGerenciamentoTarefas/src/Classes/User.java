package SistemaGerenciamentoTarefas.src.Classes;

import java.util.UUID;

public class User {

    private String name;
    private final UUID id = UUID.randomUUID();
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String userName) {
        this.name = userName;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String printUser() {
        return String.format("""
                Nome: %s
                Email: %s
                Password: %s
                """, name, email, password);
    }

    public boolean changeEmail(String oldEmail, String newEmail) {
        if (email.equals(oldEmail)) {
            email = newEmail;
            return true;
        } else {
            return false;
        }
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (password.equals(oldPassword)) {
            password = newPassword;
            return true;
        } else {
            return false;
        }
    }


}
