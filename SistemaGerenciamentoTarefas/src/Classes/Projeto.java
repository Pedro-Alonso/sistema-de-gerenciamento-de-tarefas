package SistemaGerenciamentoTarefas.src.Classes;

import java.time.LocalDate;

public class Projeto {
    
    protected int taskCount;
    protected LocalDate beginDate;
    protected LocalDate limitDate;
    protected int max;
    protected String name;
    protected Tarefa arrayTask[];

    //metodo construtor
    public Projeto(String name)
    {
        taskCount = 0; //esse é só pra esse objeto, ou para toda a classe?
        max = 50;
        this.name = name;
        arrayTask = new Tarefa[max];
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
