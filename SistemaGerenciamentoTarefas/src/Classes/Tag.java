package SistemaGerenciamentoTarefas.src.Classes;

import java.util.UUID;
/**
 *
 * @author Gabrielly
 */
public class Tag {
    
    private UUID id;
    private String description;
    private UUID userId;

    public Tag(String description, UUID userId) {
        this.description = description;
        this.userId = userId;
        this.id = UUID.randomUUID();
    }
    
    public UUID getUserID(){
        return userId;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
