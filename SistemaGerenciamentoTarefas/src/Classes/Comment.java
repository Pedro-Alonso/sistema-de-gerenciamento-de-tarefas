package Classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import Classes.user.UserTask;

public class Comment {
    private final UUID id = UUID.randomUUID();
    private UserTask author;
    private String text;
    private ArrayList<String> topics;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    /**
     * Constructor for the Comment class
     * Initializes the author, text, and date attributes, and creates an empty list of topics
     *
     * @param author The author of the comment -> {@link UserTask}
     * @param text The text of the comment -> {@link String}
     */
    public Comment(UserTask author, String text) {
        this.author = author;
        this.text = text;
        this.topics = new ArrayList<>();
    }

    /**
     * {@return the creation date of the comment as a {@link LocalDateTime}}
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * {@return the last update date of the comment as a {@link LocalDateTime}}
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * {@return the author of the comment as a {@link UserTask}}
     */
    public UserTask getAuthor() {
        return author;
    }

    /**
     * Setter for the author of the comment
     * @param author The new author for the comment -> {@link UserTask}
     * @throws IllegalArgumentException if the author is null
     */
    public void setAuthor(UserTask author) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        this.author = author;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * {@return the text of the comment as a {@link String}}
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for the text of the comment
     * @param text The new text for the comment -> {@link String}
     * @throws IllegalArgumentException if the text is null or empty
     */
    public void setText(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
        this.text = text;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Method to add a topic to the comment
     * @param topic The topic to be added -> {@link String}
     * @return {@code true} if the topic was added successfully
     * @throws IllegalArgumentException if the topic is null or empty
     */
    public boolean addTopic(String topic) {
        if (topic == null || topic.isEmpty()) {
            throw new IllegalArgumentException("Topic cannot be null or empty");
        }
        topics.add(topic);
        this.updatedAt = LocalDateTime.now();
        return true;
    }

    /**
     * Method to update a topic in the comment
     * @param index The index of the topic to be updated -> {@code int}
     * @param newTopic The new topic -> {@link String}
     * @return {@code true} if the topic was updated successfully
     * @throws IllegalArgumentException if the new topic is null or empty
     */
    public boolean updateTopic(int index, String newTopic) {
        if (newTopic == null || newTopic.isEmpty()) {
            throw new IllegalArgumentException("New topic cannot be null or empty");
        }
        if (index >= 0 && index < topics.size()) {
            topics.set(index, newTopic);
            this.updatedAt = LocalDateTime.now();   
            return true;
        }
        return false;
    }

    /**
     * Method to remove a topic from the comment
     * @param index The index of the topic to be removed -> {@code int}
     * @return {@code true} if the topic was removed successfully
     */
    public boolean removeTopic(int index) {
        if (index >= 0 && index < topics.size()) {
            topics.remove(index);
            this.updatedAt = LocalDateTime.now();       
            return true;
        }
        return false;
    }

    /**
     * {@return the list of topics as an {@link ArrayList} of {@link String}}
     */
    public ArrayList<String> getTopics() {
        return topics;
    }

    /**
     * {@return the id of the comment as a {@link UUID}}
     */
    public UUID getId() {
        return id;
    }

    /**
     * Method to display the comment details
     * @return {@link String} with the comment details
     */
    protected String displayComment() {
        String topics = Utils.getListString(this.topics, String::toString);
        return String.format(
            """
            Autor: %s
            Comentário: %s
            Tópicos: %s
            Atualizado em: %s
            """, 
            author, text, topics, updatedAt
        );
    }
}
