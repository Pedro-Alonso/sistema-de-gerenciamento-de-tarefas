package Classes.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Class representing a Project in the task management system.
 */
public class Project {

  private UUID id;
  private int taskCount;
  private LocalDate beginDate;
  private LocalDate limitDate;
  private int max;
  private String name;
  private ArrayList<Task> tasks;
  private ArrayList<UserTask> users;
  private LocalDateTime createdAt = LocalDateTime.now();
  private LocalDateTime updatedAt = LocalDateTime.now();

  /**
   * Constructor for the Project class
   * Initializes the beginDate, limitDate, and name attributes, and generates a unique ID for the project
   *
   * @param beginDate The start date of the project -> {@link LocalDate}
   * @param limitDate The end date of the project -> {@link LocalDate}
   * @param name The name of the project -> {@link String}
   */
  public Project(LocalDate beginDate, LocalDate limitDate, String name) {
    id = UUID.randomUUID();
    this.beginDate = beginDate;
    this.limitDate = limitDate;
    taskCount = 0;
    max = 50;
    this.name = name;
    tasks = new ArrayList<Task>();
    users = new ArrayList<UserTask>();
  }

  /**
   * Constructor for the Project class for DTO purposes
   * Initializes the ID, beginDate, limitDate, and name attributes
   * @param id The ID of the project -> {@link UUID}
   * @param name The name of the project -> {@link String}
   * @param beginDate The start date of the project -> {@link LocalDate}
   * @param limitDate The end date of the project -> {@link LocalDate}
   */
  public Project(UUID id, String name, LocalDate beginDate, LocalDate limitDate) {
    this.id = id;
    this.beginDate = beginDate;
    this.limitDate = limitDate;
    taskCount = 0;
    max = 50;
    this.name = name;
    tasks = new ArrayList<Task>();
    users = new ArrayList<UserTask>();
  }

  /**
   * Method to search for a task in the project by its ID
   * @param id The ID of the task to be searched -> {@link UUID}
   * @return {@code int} The index of the task in the list, or {@code -1} if not found
   */
  public int getTaskIndexById(UUID id) {
    for (int i = 0; i < tasks.size(); i++) {
      if (tasks.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

    /**
     * Method to search for a user in the project by its ID
     * @param id The ID of the user to be searched -> {@link UUID}
     * @return {@code int} The index of the user in the list, or {@code -1} if not found
     */
    public int getUserIndexById(UUID id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

  /**
   * Method to add a task to the project
   * @param t The task to be added -> {@link Task}
   */
  public void addTask(Task t) {
    try {
      if (taskCount >= max) {
        throw new IllegalArgumentException("Número máximo de tarefas atingido");
      }
      if (t == null) {
        throw new IllegalArgumentException("Tarefa não encontrada");
      }
      tasks.add(t);
      taskCount++;
      this.updatedAt = LocalDateTime.now();
    } catch (Exception e) {
      System.out.println("Erro ao adicionar a tarefa: " + e.getMessage());
    }
  }

  /**
   * Method to remove a task from the project by its ID
   * @param id The ID of the task to be removed -> {@link UUID}
   */
  public void removeTask(UUID id) {
    try {
      int i = getTaskIndexById(id);
      if (i == -1) {
        throw new IllegalArgumentException("Tarefa não encontrada");
      }
      tasks.remove(i);
      taskCount--;
      this.updatedAt = LocalDateTime.now();
    } catch (Exception e) {
      System.out.println("Erro ao remover a tarefa: " + e.getMessage());
    }
  }

  /**
   * Method to add a user to the project
   * @param userTask The user to be added -> {@link UserTask}
   */
  public void addUser(UserTask userTask) {
    try {
      if (userTask == null) {
        throw new IllegalArgumentException("Usuário não encontrado");
      }
      users.add(userTask);
      this.updatedAt = LocalDateTime.now();
    } catch (Exception e) {
      System.out.println("Erro ao adicionar o usuário: " + e.getMessage());
    }
  }

  /**
   * Method to remove a user from the project by its ID
   * @param id The ID of the user to be removed -> {@link UUID}
   */
  public void removeUser(UUID id) {
    try {
      int i = getUserIndexById(id);
      if (i == -1) {
        throw new IllegalArgumentException("Usuário não encontrado");
      }
      users.remove(i);
      this.updatedAt = LocalDateTime.now();
    } catch (Exception e) {
      System.out.println("Erro ao remover o usuário: " + e.getMessage());
    }
  }

  /**
   * {@return the ID of the project as a {@link UUID}}
   */
  public UUID getId() {
    return id;
  }

  /**
   * {@return the start date of the project as a {@link LocalDate}}
   */
  public LocalDate getBeginDate() {
    return beginDate;
  }

  /**
   * Setter for the start date of the project
   * @param beginDate The new start date for the project -> {@link LocalDate}
   */
  public void setBeginDate(LocalDate beginDate) {
    this.beginDate = beginDate;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * {@return the end date of the project as a {@link LocalDate}}
   */
  public LocalDate getLimitDate() {
    return limitDate;
  }

  /**
   * Setter for the end date of the project
   * @param limitDate The new end date for the project -> {@link LocalDate}
   */
  public void setLimitDate(LocalDate limitDate) {
    this.limitDate = limitDate;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * {@return the name of the project as a {@link String}}
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for the name of the project
   * @param name The new name for the project -> {@link String}
   */
  public void setName(String name) {
    this.name = name;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * {@return the list of tasks in the project as an {@link ArrayList} of {@link Task}}
   */
  public ArrayList<Task> getTasks() {
    return tasks;
  }

  /**
   * {@return the list of users in the project as an {@link ArrayList} of {@link UserTask}}
   */
  public ArrayList<UserTask> getUsers() {
    return users;
  }

  /**
   * {@return the creation date of the project as a {@link LocalDateTime}}
   */
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  /**
   * {@return the last update date of the project as a {@link LocalDateTime}}
   */
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
