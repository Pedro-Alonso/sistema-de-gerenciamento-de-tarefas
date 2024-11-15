import SistemaGerenciamentoTarefas.src.Classes.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class App {

  public static void main(String[] args) throws Exception {
    System.out.println("Sistema de Gerenciamento de tarefas");

    System.out.println("===== Criando usuário =====");
    UserTask user = new UserTask(
      "Testador da Silva",
      "testador@email.com",
      "testando123"
    );
    System.out.println(user.printUser() + "\n");

    System.out.println("===== Criando projeto =====");
    ArrayList<Project> projects = new ArrayList<>();
    Project project = new Project(
      LocalDate.now(),
      LocalDate.now().plusDays(7),
      "Projeto de teste"
    );
    projects.add(project);
    user.setProjects(projects);
    System.out.println(
      String.format(
        """
        Projeto: %s
        Início: %s
        Limite: %s
            """,
        user.getProjects().get(0).getName(),
        user.getProjects().get(0).getBeginDate(),
        user.getProjects().get(0).getLimitDate()
      )
    );

    System.out.println(
      "===== Adicionando tarefas, etiquetas e subtarefas ====="
    );
    Task task1 = new Task(
      user,
      "Tarefa 1",
      "Descrição da tarefa 1",
      LocalDate.now().plusDays(3),
      1
    );
    Task task2 = new Task(
      user,
      "Tarefa 2",
      "Descrição da tarefa 2",
      LocalDate.now().plusDays(5),
      2
    );
    Task task3 = new Task(
      user,
      "Tarefa 3",
      "Descrição da tarefa 3",
      LocalDate.now().plusDays(7),
      3
    );

    Tag tag1 = new Tag("Tag 1", user.getId());
    Tag tag2 = new Tag("Tag 2", user.getId());
    Tag tag3 = new Tag("Tag 3", user.getId());

    task1.addTag(tag1);
    task2.addTag(tag1);
    task2.addTag(tag2);
    task3.addTag(tag1);
    task3.addTag(tag2);
    task3.addTag(tag3);

    SubTask subTask1 = new SubTask(
      user,
      "Subtarefa 1",
      "Descrição da subtarefa 1",
      LocalDate.now().plusDays(3),
      1
    );

    ArrayList<String> steps = new ArrayList<>();
    steps.add("Passo 1");
    steps.add("Passo 2");
    steps.add("Passo 3");

    ArrayList<Boolean> stepsStatus = new ArrayList<>();
    stepsStatus.add(false);
    stepsStatus.add(false);
    stepsStatus.add(false);

    subTask1.setSteps(steps);
    subTask1.setStepsStatus(stepsStatus);
    subTask1.changeStepStatus(0);
    subTask1.changeStepStatus(2);

    task1.addSubTask(subTask1);

    project.addTask(task1);
    project.addTask(task2);
    project.addTask(task3);

    System.out.println(task1.printTask());
    System.out.println(
      String.format(
        """
        %s: %s
        %s: %s
        %s: %s
    """,
        steps.get(0),
        stepsStatus.get(0),
        steps.get(1),
        stepsStatus.get(1),
        steps.get(2),
        stepsStatus.get(2)
      )
    );
    System.out.println(task2.printTask());
    System.out.println(task3.printTask());

    System.out.println("===== Comentando tarefas =====");
    TaskComment comment1 = new TaskComment(
      user.getUserName(),
      "Comentário 1",
      "2024-01-01",
      task1.getId()
    );
    comment1.addTopic("#TesteZika");

    task1.addComment(comment1);
    System.out.println(String.format("Id da Tarefa 1: %s", task1.getId()));
    System.out.println(task1.printTask());
    comment1.displayComment();

    System.out.println("===== Removendo tarefas =====");
    project.removeTask(task1.getId());

    System.out.println("Tarefas no Projeto:");
    for (Task task : project.getTaskArray()) {
      System.out.println(task.getName());
    }
  }
}
