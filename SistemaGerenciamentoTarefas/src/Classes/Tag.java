/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Tag;

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
    
    public UUID getuserID(){
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
