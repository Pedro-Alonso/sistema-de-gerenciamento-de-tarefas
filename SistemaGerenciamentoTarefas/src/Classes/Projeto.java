package SistemaGerenciamentoTarefas.src.Classes;
public class Projeto {
    
    protected int countTask;
    protected int max;
    protected String name;
    protected Tarefa arrayTask[];

    //metodo construtor
    public Projeto(String name)
    {
        countTask = 0; //esse é só pra esse objeto, ou para toda a classe?
        max = 50;
        this.name = name;
        arrayTask = new Tarefa[max];
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
