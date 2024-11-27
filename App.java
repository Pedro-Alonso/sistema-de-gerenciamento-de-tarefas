import Classes.Controller.*;
import Classes.DTO.*;
import Classes.Model.*;
import Classes.Service.*;
import Classes.Mapper.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class App {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        // Initialize services and controllers
        LoginService loginService = new LoginService();
        LoginController loginController = new LoginController(loginService);
        UserController userController = new UserController();
        ProjectController projectController = new ProjectController();
        TaskController taskController = new TaskController();
        SubTaskController subTaskController = new SubTaskController();
        TagController tagController = new TagController();
        TaskCommentController taskCommentController = new TaskCommentController();

        // Add logger as an observer to the controllers
        Logger logger = Logger.getInstance();
        userController.addObserver(logger);
        projectController.addObserver(logger);
        taskController.addObserver(logger);
        subTaskController.addObserver(logger);
        tagController.addObserver(logger);
        taskCommentController.addObserver(logger);

        LocalDate today = LocalDate.now();

        // Create and add a new user
        UserDto userDto = userController.create("John Doe", "john@example.com", "password123");
        System.out.println("User created: " + userDto);

        // Log in with the new user
        UserSession userSession = loginController.logIn("john@example.com", "password123");
        if (userSession != null) {
            System.out.println("Login successful for user: " + userSession.getUser().getUsername());
        } else {
            System.out.println("Login failed.");
            return;
        }

        // Create a new project
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Project 1");
        projectDto.setBeginDate(today);
        projectDto.setLimitDate(today.plusDays(30));
        projectDto.setId(UUID.randomUUID());

        projectDto = projectController.createProjectDto(projectDto.getBeginDate(), projectDto.getLimitDate(), projectDto.getName(), userSession);
        System.out.println("Project created: " + projectDto);

        // Add user to project
        UserTask userTask = (UserTask) userSession.getUser();
        projectController.addUserToProject(userSession, projectDto, userTask);
        System.out.println("User added to project: " + projectDto.getName());

        // Create a new task
        TaskDto taskDto = new TaskDto();
        taskDto.setId(UUID.randomUUID());
        taskDto.setName("Task 1");
        taskDto.setDescription("Description 1");
        taskDto.setDeadline(today.plusDays(10));
        taskDto.setGravity(5);
        taskDto.setUrgency(3);
        taskDto.setTrend(2);
        taskDto.setPriority(2*3*5);

        taskDto = taskController.create(ProjectMapper.fromDto(projectDto), userSession, taskDto);
        System.out.println("Task created: " + taskDto);

        // Create a new subtask
        ArrayList<String> steps = new ArrayList<>();
        steps.add("Step 1");
        steps.add("Step 2");
        steps.add("Step 3");

        ArrayList<Boolean> stepsStatus = new ArrayList<>();
        stepsStatus.add(false);
        stepsStatus.add(true);
        stepsStatus.add(false);

        SubTaskDto subTaskDto = new SubTaskDto();
        subTaskDto.setId(UUID.randomUUID());
        subTaskDto.setName("SubTask 1");
        subTaskDto.setDescription("Description 1");
        subTaskDto.setDeadline(today.plusDays(5));
        subTaskDto.setGravity(3);
        subTaskDto.setUrgency(2);
        subTaskDto.setTrend(1);
        subTaskDto.setPriority(3*2*1);
        subTaskDto.setSteps(steps);
        subTaskDto.setStepsStatus(stepsStatus);

        subTaskDto = subTaskController.createSubTask(TaskMapper.fromDto(taskDto), subTaskDto, userSession);
        System.out.println("SubTask created: " + subTaskDto);

        // // Add a comment to the task
        // TaskCommentDto commentDto = new TaskCommentDto();
        // commentDto.setId(UUID.randomUUID());
        // commentDto.setTaskId(taskDto.getId());
        // commentDto.setText("This is a comment.");
        // commentDto.setAuthorId(userSession.getUser().getId());

        // commentDto = taskCommentController.addComment(userSession, TaskMapper.fromDto(taskDto), commentDto);
        // System.out.println("Comment added: " + commentDto);

        // Add a tag to the task
        // TagDto tagDto = new TagDto();
        // tagDto.setId(UUID.randomUUID());
        // tagDto.setDescription("Tag 1");
        // tagDto.setProjectId(projectDto.getId());

        // tagDto = tagController.create(userSession, tagDto);
        // System.out.println("Tag created: " + tagDto);

        // Update user email
        userController.updateUserEmail(userSession, "john.new@example.com");
        System.out.println("User email updated to: " + userSession.getUser().getUserEmail());

        // Remove the subtask
        subTaskController.removeSubTask(TaskMapper.fromDto(taskDto), subTaskDto, userSession);
        System.out.println("SubTask removed: " + subTaskDto);

        // // Remove the task
        // taskController.removeTask(ProjectMapper.fromDto(projectDto), userSession, TaskMapper.fromDto(taskDto));
        // System.out.println("Task removed: " + taskDto);

        // Remove the project
        projectController.removeProject(userSession, projectDto);
        System.out.println("Project removed: " + projectDto);

        // Remove the user
        userController.removeUser(userSession);
        System.out.println("User removed: " + userDto);

        // Output the logs
        System.out.println("\n\n\n");
        System.out.println(logger.getLogsAsString());
    }
}
