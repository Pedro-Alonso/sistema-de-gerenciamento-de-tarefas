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

  private <T> String getListString(ArrayList<T> array, Function<T, String> getNameFunc) {
    if (array == null || array.isEmpty()) return "N/A";

    StringBuilder returnString = new StringBuilder();
    array.forEach(t -> {
        returnString.append(getNameFunc.apply(t)).append(", ");
    });

    return returnString.substring(0, returnString.length() - 2);
  }

  /**
   * Method to add a Tag to the current Task object
   * @category Method
   * @return void
   */
  public void addTag(Tag tag) {
    tags.add(tag);
  }

  /**
   * Method to add a SubTask to the current Task object
   * @category Method
   * @return void
   */
  public void addSubTask(SubTask subTask) {
    subTasks.add(subTask);
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

    String returnData = String.format(
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
    return returnData;
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
