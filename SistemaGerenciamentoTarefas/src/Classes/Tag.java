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
    private String priority;
    private String userId;

    public Tag(String description, String priority, String userId) {
        this.description = description;
        this.priority = priority;
        this.userId = userId;
        this.id = UUID.randomUUID();
    }
    
    public String getuserID(){
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    
}
