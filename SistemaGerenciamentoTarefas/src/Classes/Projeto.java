package SistemaGerenciamentoTarefas.src.Classes;

import java.time.LocalDate;

public class Projeto {
    
    protected int taskCount;
    protected LocalDate beginDate;
    protected LocalDate limitDate;
    protected int max;
    protected String name;
    protected Task taskArray[];

    //constructor method
    public Projeto(LocalDate beginDate, LocalDate limitDate, String name)
    {
        this.beginDate = beginDate;
        this.limitDate = limitDate;
        taskCount = 0; //esse é só pra esse objeto, ou para toda a classe?
        max = 50;
        this.name = name;
        taskArray = new Task[max];
    }

    //method to search tasks of the project
    public int searchTask (String id)
    {
        for (int i = 0; i < taskCount; i++)
        {
            if (taskArray[i].getId().equals(id))
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
            taskArray[taskCount] = t;
            taskCount++;
        }
    }

    public void removeTask (String id)
    {
        int i = searchTask(id);

        if(i != -1)
        {
            if (i < taskCount)
            {
                int j = 0;
                for (j = i; j < taskCount; j++)
                {
                    taskArray[j] = taskArray[j+1];

                }

                taskArray[taskCount - 1] = null;
                taskCount--;
            }
            else //if i == taskCount means that you found the task in the last array's position
            {
                taskArray[i] = null;
                taskCount--;
            }
        }
    }



    //getters e setters
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

}
