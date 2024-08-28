package SistemaGerenciamentoTarefas.src.Classes;
public class Projeto {
    
    protected int contTarefas;
    protected int max;
    protected String nomeProjeto;
    protected Tarefa vetorTarefas[];

    //metodo construtor
    public Projeto(String nomeProjeto)
    {
        contTarefas = 0; //esse é só pra esse objeto, ou para toda a classe?
        max = 50;
        this.nomeProjeto = nomeProjeto;
        vetorTarefas = new Tarefa[max];
    }

    //getters e setters
    public String getNomeProjeto ()
    {
        return nomeProjeto;
    }

    public void setNomeProjeto (String nomeProjeto)
    {
        this.nomeProjeto = nomeProjeto;
    }

}
