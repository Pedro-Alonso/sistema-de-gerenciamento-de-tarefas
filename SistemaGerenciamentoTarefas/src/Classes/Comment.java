package SistemaGerenciamentoTarefas.src.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Comment {
    // Attributes
    private UUID id;
    private String author;
    private String text;
    private String date;
    private List<String> topics;

    /**
     * Constructor for the Comment class
     * Initializes the author, text, and date attributes, and creates an empty list of topics
     *
     * @param author The author of the comment
     * @param text The text of the comment
     * @param date The date of the comment
     */
    public Comment(String author, String text, String date) {
        this.id = UUID.randomUUID();
        this.author = author;
        this.text = text;
        this.date = date;
        this.topics = new ArrayList<>();
    }

    // Getters and setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        this.date = date;
    }

    public boolean addTopic(String topic) {
        if (topic == null || topic.isEmpty()) {
            throw new IllegalArgumentException("Topic cannot be null or empty");
        }
        topics.add(topic);
        return true;
    }

    public boolean updateTopic(int index, String newTopic) {
        if (newTopic == null || newTopic.isEmpty()) {
            throw new IllegalArgumentException("New topic cannot be null or empty");
        }
        if (index >= 0 && index < topics.size()) {
            topics.set(index, newTopic);
            return true;
        }
        return false;
    }

    public boolean removeTopic(int index) {
        if (index >= 0 && index < topics.size()) {
            topics.remove(index);
            return true;
        }
        return false;
    }

    public UUID getId() {
        return id;
    }

    protected void displayComment() {
        System.out.println("Author: " + author);
        System.out.println("Comment: " + text);
        System.out.println("Date: " + date);
        System.out.println("Topics: " + String.join(", ", topics));
    }
}
