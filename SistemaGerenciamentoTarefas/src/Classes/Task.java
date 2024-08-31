package SistemaGerenciamentoTarefas.src.Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Function;

public class Task {

  private UUID taskId;
  private String name;
  private String description;
  private LocalDate deadline;
  private int priority;
  private Status status;
  private ArrayList<Tag> tags;
  private ArrayList<SubTask> subTasks;

  enum Status {
    TODO,
    DOING,
    DONE,
  }

  public Task(
    String name,
    String description,
    LocalDate deadline,
    int priority
  ) {
    this.name = name;
    this.description = description;
    this.deadline = deadline;
    this.priority = priority;

    this.taskId = UUID.randomUUID();
    this.status = Status.TODO;

    this.tags = new ArrayList<Tag>();
    this.subTasks = new ArrayList<SubTask>();
  }

  public UUID getTaskId() {
    return taskId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) {
    this.deadline = deadline;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public ArrayList<Tag> getTags() {
    return tags;
  }

  public ArrayList<SubTask> getSubTasks() {
    return subTasks;
  }

  /**
   * Method to add a Tag to the current Task object
   * @category Method
   * @return void
   */
  public void addTag(Tag tag) {
    Tag t = getTagById(tag.getId());
    if (t == null) tags.add(tag);
    return;
  }

  /**
   * Method to get the Tag object on this Task tags, if exists
   * @param id
   * @return Tag -> Tag if exists, null if not
   */
  public Tag getTagById(UUID id) {
    for (Tag tag : tags) {
      if (tag.getId() == id) return tag;
    }
    return null;
  }

  /**
   * Method to get the index of a specific Tag on this Task tags
   * @param id
   * @return int -> index if exists, -1 if not
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
   * @param id
   * @return boolean -> true if removed, false if not exists
   */
  public boolean removeTag(UUID id) {
    int tagIndex = getTagIndex(id);
    if (tagIndex == -1) return false;
    tags.remove(tagIndex);
    return true;
  }

  /**
   * Method to add a SubTask to the current Task object
   * @category Method
   * @return void
   */
  public void addSubTask(SubTask subTask) {
    SubTask st = getSubTaskById(subTask.getTaskId());
    if (st == null) subTasks.add(subTask);
    return;
  }

  /**
   * Method to get the SubTask object on this Task subTasks, if exists
   * @param id
   * @return SubTask -> SubTask if exists, null if not
   */
  public SubTask getSubTaskById(UUID id) {
    for (SubTask subTask : subTasks) {
      if (subTask.getTaskId() == id) return subTask;
    }
    return null;
  }

  /**
   * Method to get the index of a specific SubTask on this Task subTasks
   * @param id
   * @return int -> index if exists, -1 if not
   */
  private int getSubTaskIndex(UUID id) {
    int index = -1;
    for (int i = 0; i < subTasks.size(); i++) {
      if (subTasks.get(i).getTaskId() == id) {
        index = i;
        break;
      }
    }
    return index;
  }

  /**
   * Method to remove a SubTask from this Task subTasks of a given ig
   * @param id
   * @return boolean -> true if removed, false if not exists
   */
  public boolean removeSubTask(UUID id) {
    int subTaskIndex = getSubTaskIndex(id);
    if (subTaskIndex == -1) return false;
    subTasks.remove(subTaskIndex);
    return true;
  }

  /**
   * Method to get all elements of a given ArrayList<T> and return a String with their names, using the equivalent getName() method of the T class
   * @category Method
   * @param array ArrayList if T class
   * @param getNameFunc Equivalent function of getName() on T class
   * @return void
   *
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
   * Method that returns a String with all the information about the current Task object, except for the taskId
   * @category Method
   * @return String
   */
  public String printTask() {
    String tagNames = getListString(tags, Tag::getName);
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
      Status: %s
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
   * @category Method
   * @return void
   */
  public void incrementPriority() {
    this.priority += 1;
  }

  /**
   * Method to decrement (by one) the priority of the current Task object
   * @category Method
   * @return void
   */
  public void decrementPriority() {
    this.priority -= 1;
  }
}
