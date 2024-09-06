package SistemaGerenciamentoTarefas.src.Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Function;

public class Task {

  private UUID id;
  private UUID userId;
  private String name;
  private String description;
  private LocalDate deadline;
  private int priority;
  private TaskStatus status;
  private ArrayList<Tag> tags;
  private ArrayList<SubTask> subTasks;

  public enum TaskStatus {
    TODO,
    DOING,
    DONE,
  }

  /**
   * Constructor for the Task class
   * @param userId The id of the user that owns the Task -> {@link UUID}
   * @param name The name of the Task -> {@link String}
   * @param description The description of the Task -> {@link String}
   * @param deadline The deadline of the Task -> {@link LocalDate}
   * @param priority The priority of the Task -> {@code int}
   */
  public Task(
    UUID userId,
    String name,
    String description,
    LocalDate deadline,
    int priority
  ) {
    this.id = UUID.randomUUID();
    this.userId = userId;
    this.name = name;
    this.description = description;
    this.deadline = deadline;
    this.priority = priority;
    this.status = TaskStatus.TODO;
    this.tags = new ArrayList<Tag>();
    this.subTasks = new ArrayList<SubTask>();
  }

  /**
   * {@return the id of the Task as a {@link UUID}}
   */
  public UUID getId() {
    return id;
  }

  /**
   * {@return the id of the user that owns the Task as a {@link UUID}}
   */
  public UUID getUserId() {
    return userId;
  }

  /**
   * {@return the name of the Task as a {@link String}}
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for the name of the Task
   * @param name The new name for the Task -> {@link String}
   */
  public void setName(String name) {
    try {
      if (name.isBlank()) throw new IllegalArgumentException(
        "O nome da tarefa não pode ser vazio"
      );
      this.name = name;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * {@return the description of the Task as a {@link String}}
   */
  public String getDescription() {
    return description;
  }

  /**
   * Setter for the description of the Task
   * @param description The new description for the Task -> {@link String}
   */
  public void setDescription(String description) {
    try {
      if (description.isBlank()) throw new IllegalArgumentException(
        "A descrição da tarefa não pode ser vazia"
      );
      this.description = description;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * {@return the deadline of the Task as a {@link LocalDate}}
   */
  public LocalDate getDeadline() {
    return deadline;
  }

  /**
   * Setter for the deadline of the Task
   * @param deadline The new deadline for the Task -> {@link LocalDate}
   */
  public void setDeadline(LocalDate deadline) {
    try {
      if (
        deadline.isBefore(LocalDate.now())
      ) throw new IllegalArgumentException(
        "A data limite da tarefa não pode ser anterior a data atual"
      );
      this.deadline = deadline;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * {@return the priority of the Task as a {@code int}}
   */
  public int getPriority() {
    return priority;
  }

  /**
   * Setter for the priority of the Task
   * @param priority The new priority for the Task -> {@code int}
   */
  public void setPriority(int priority) {
    try {
      if (priority < 0) throw new IllegalArgumentException(
        "A prioridade da tarefa não pode ser negativa"
      );
      this.priority = priority;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * {@return the status of the Task as a {@link TaskStatus}}
   */
  public TaskStatus getStatus() {
    return status;
  }

  /**
   * Setter for the status of the Task
   * @param status The new status for the Task -> {@link TaskStatus}
   */
  public void setStatus(TaskStatus status) {
    try {
      if (status == null) throw new IllegalArgumentException(
        "O status da tarefa não pode ser nulo"
      );
      this.status = status;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * {@return the tags of the Task as a {@link ArrayList} of {@link Tag}}
   */
  public ArrayList<Tag> getTags() {
    return tags;
  }

  /**
   * {@return the subTasks of the Task as a {@link ArrayList} of {@link SubTask}}
   */
  public ArrayList<SubTask> getSubTasks() {
    return subTasks;
  }

  /**
   * Method to add a Tag to the current Task object. If the Tag already exists on the Task tags, it will not be added
   * @param tag The Tag object to be added to the Task tags -> {@link Tag}
   */
  public void addTag(Tag tag) {
    try {
      Tag t = getTagById(tag.getId());
      if (t == null) tags.add(tag);
      return;
    } catch (Exception e) {
      System.out.println("Erro ao adicionar a tag: " + e.getMessage());
    }
  }

  /**
   * Method to get the Tag object on this Task tags, if exists
   * @param id The id of the Tag to be searched -> {@link UUID}
   * @return {@link Tag} | {@code null} if not exists
   */
  public Tag getTagById(UUID id) {
    for (Tag tag : tags) {
      if (tag.getId() == id) return tag;
    }
    return null;
  }

  /**
   * Method to get the index of a specific Tag on this Task tags
   * @param id The id of the Tag to be searched -> {@link UUID}
   * @return {@code int} | {@code -1} if not exists
   */
  private int getTagIndex(UUID id) {
    int index = -1;
    for (int i = 0; i < tags.size(); i++) {
      if (tags.get(i).getId() == id) {
        index = i;
        break;
      }
    }
    return index;
  }

  /**
   * Method to remove a Tag from this Task tags of a given ig
   * @param id The id of the Tag to be removed -> {@link UUID}
   * @return {@code boolean} | {@code true} if removed, {@code false} if not exists
   */
  public boolean removeTag(UUID id) {
    try {
      int tagIndex = getTagIndex(id);
      if (tagIndex == -1) return false;
      tags.remove(tagIndex);
      return true;
    } catch (Exception e) {
      System.out.println("Erro ao remover a tag: " + e.getMessage());
      return false;
    }
  }

  /**
   * Method to add a SubTask to the current Task object
   * @param subTask The SubTask object to be added to the Task subTasks -> {@link SubTask}
   */
  public void addSubTask(SubTask subTask) {
    try {
      SubTask st = getSubTaskById(subTask.getId());
      if (st == null) subTasks.add(subTask);
      return;
    } catch (Exception e) {
      System.out.println("Erro ao adicionar a sub tarefa: " + e.getMessage());
    }
  }

  /**
   * Method to get the SubTask object on this Task subTasks, if exists
   * @param id The id of the SubTask to be searched -> {@link UUID}
   * @return {@link SubTask} | {@code null} if not exists
   */
  public SubTask getSubTaskById(UUID id) {
    for (SubTask subTask : subTasks) {
      if (subTask.getId() == id) return subTask;
    }
    return null;
  }

  /**
   * Method to get the index of a specific SubTask on this Task subTasks
   * @param id The id of the SubTask to be searched -> {@link UUID}
   * @return {@code int} | {@code -1} if not exists
   */
  private int getSubTaskIndex(UUID id) {
    int index = -1;
    for (int i = 0; i < subTasks.size(); i++) {
      if (subTasks.get(i).getId() == id) {
        index = i;
        break;
      }
    }
    return index;
  }

  /**
   * Method to remove a SubTask from this Task subTasks of a given ig
   * @param id The id of the SubTask to be removed -> {@link UUID}
   * @return {@code boolean} | {@code true} if removed, {@code false} if not exists
   */
  public boolean removeSubTask(UUID id) {
    try {
      int subTaskIndex = getSubTaskIndex(id);
      if (subTaskIndex == -1) return false;
      subTasks.remove(subTaskIndex);
      return true;
    } catch (Exception e) {
      System.out.println("Erro ao remover a sub tarefa: " + e.getMessage());
      return false;
    }
  }

  /**
   * Method to get all elements of a given ArrayList<T> and return a String with their names, using the equivalent getName() method of the T class
   * @param array The ArrayList<> of given class T to be used -> {@link ArrayList}
   * @param getNameFunc Equivalent function of getName() on T class -> {@link Function}
   *
   * @return {@link String}
   * Example: getListString(tasks, Task::getName);
   */
  private <T> String getListString(
    ArrayList<T> array,
    Function<T, String> getNameFunc
  ) {
    if (array == null || array.isEmpty()) return "N/A";

    StringBuilder returnString = new StringBuilder();
    array.forEach(t -> {
      returnString.append(getNameFunc.apply(t)).append(", ");
    });

    return returnString.substring(0, returnString.length() - 2);
  }

  /**
   * Method that returns a String with all the information about the current Task object, except for the id
   * @return {@link String}
   */
  public String printTask() {
    String tagNames = getListString(tags, Tag::getDescription);
    String subTaskNames = getListString(subTasks, SubTask::getName);

    String currentStatus = "";
    switch (status) {
      case TODO:
        currentStatus = "Pendente";
        break;
      case DOING:
        currentStatus = "Em andamento";
      case DONE:
        currentStatus = "Concluído";
    }

    return String.format(
      """
      Nome: %s
      Descrição: %s
      Prazo: %s
      Prioridade: %s
      TaskStatus: %s
      Etiquetas: %s
      Sub tarefas: %s
      """,
      name,
      description,
      deadline,
      priority,
      currentStatus,
      tagNames,
      subTaskNames
    );
  }

  /**
   * Method to increment (by one) the priority of the current Task object
   */
  public void incrementPriority() {
    this.priority += 1;
  }

  /**
   * Method to decrement (by one) the priority of the current Task object
   */
  public void decrementPriority() {
    this.priority -= 1;
  }
}
