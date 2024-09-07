package SistemaGerenciamentoTarefas.src.Classes;

import java.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;

public class Project {

    private UUID id;
    private int taskCount;
    private LocalDate beginDate;
    private LocalDate limitDate;
    private int max;
    private String name;
    private ArrayList<Task> taskArray;

    //constructor method
    public Project(LocalDate beginDate, LocalDate limitDate, String name)
    {
        id = UUID.randomUUID();  
        this.beginDate = beginDate;
        this.limitDate = limitDate;
        taskCount = 0; //esse é só pra esse objeto, ou para toda a classe?
        max = 50;
        this.name = name;
        taskArray = new ArrayList<Task>();
    }

    //method to search tasks of the project
    public int searchTask (UUID id)
    {
        for (int i = 0; i < taskArray.size(); i++)
        {
            if (taskArray.get(i).getId().equals(id))
            {
                return i;
            }
        }

        return -1;
    }

    //methods to add and remove tasks from the array
    public void addTask (Task t)
    {
        if(taskCount < max)
        {
            taskArray.add(t);
            taskCount++;
        }
    }

    public void removeTask (UUID id)
    {
        int i = searchTask(id);

        if(i != -1)
        {
            taskArray.remove(i);
        }
    }



    //getters e setters

    public UUID getId()
    {
        return id;
    }

    public LocalDate getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate)
    {
        this.beginDate = beginDate;
    }

    public LocalDate getLimitDate()
    {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate)
    {
        this.limitDate = limitDate;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public ArrayList<Task> getTaskArray()
    {
        return taskArray;
    }

}
