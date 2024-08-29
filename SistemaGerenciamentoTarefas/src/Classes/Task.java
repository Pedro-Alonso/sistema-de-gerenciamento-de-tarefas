package SistemaGerenciamentoTarefas.src.Classes;

import java.time.LocalDate;
import java.util.UUID;

public class Task {

  protected UUID taskId;
  protected String name;
  protected String description;
  protected LocalDate deadline;
  protected int priority;
  protected boolean isDone;
  protected Status status;

  enum Status {
    TODO,
    DOING,
    DONE,
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

  public boolean isDone() {
    return isDone;
  }

  public void setDone(boolean isDone) {
    this.isDone = isDone;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
