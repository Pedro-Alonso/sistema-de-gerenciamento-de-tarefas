package SistemaGerenciamentoTarefas.src.Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

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

  public void addTag(Tag tag) {
    tags.add(tag);
  }

  public ArrayList<SubTask> getSubTasks() {
    return subTasks;
  }

  public void addSubTask(SubTask subTask) {
    subTasks.add(subTask);
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
