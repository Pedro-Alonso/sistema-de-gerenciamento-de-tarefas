package Classes.Persistency;

import java.util.UUID;
import java.util.Vector;

import Classes.Model.Comment;

public class CommentDatabase {

    private static CommentDatabase instance = null;

    private Vector<Comment> comments;

    private CommentDatabase() {
        comments = new Vector<>();
    }

    /**
     * Returns the singleton instance of CommentDatabase.
     * @return the singleton instance of CommentDatabase
     */
    public static synchronized CommentDatabase getInstance() {
        if (instance == null) {
            instance = new CommentDatabase();
        }
        return instance;
    }

    /**
     * Adds a comment to the database.
     * @param comment the comment to be added
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    /**
     * Returns the list of comments in the database.
     * @return the list of comments
     */
    public Vector<Comment> getComments() {
        return comments;
    }

    /**
     * Updates a comment in the database by replacing the existing comment with the new comment.
     * @param newComment the new comment to replace the existing comment
     * @return true if the comment was updated, false otherwise
     */
    public boolean updateComment(Comment newComment) {
        UUID id = newComment.getId();
        if (removeComment(id)) {
            addComment(newComment);
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the comments in the database.
     * @return a string representation of the comments
     */
    @Override
    public String toString() {
        if (comments.isEmpty()) {
            return "Nenhum comentário cadastrado.";
        }

        StringBuilder builder = new StringBuilder("Lista de Comentários:\n");
        for (Comment comment : comments) {
            builder.append(comment).append("\n");
        }
        return builder.toString();
    }

    /**
     * Removes a comment from the database.
     * @param id the ID of the comment to be removed
     * @return true if the comment was removed, false otherwise
     */
    public boolean removeComment(UUID id) {
        return comments.removeIf(comment -> comment.getId().equals(id));
    }

    /**
     * Clears all comments from the database.
     */
    public void clearComments() {
        comments.clear();
    }
}