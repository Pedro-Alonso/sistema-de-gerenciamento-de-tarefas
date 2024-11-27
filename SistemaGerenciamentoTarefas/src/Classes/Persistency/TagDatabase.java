package Classes.Persistency;

import java.util.UUID;
import java.util.Vector;

import Classes.Model.Tag;

public class TagDatabase {

    private static TagDatabase instance = null;

    private Vector<Tag> tags;

    private TagDatabase() {
        tags = new Vector<>();
    }

    /**
     * Returns the singleton instance of TagDatabase.
     * @return the singleton instance of TagDatabase
     */
    public static synchronized TagDatabase getInstance() {
        if (instance == null) {
            instance = new TagDatabase();
        }
        return instance;
    }

    /**
     * Adds a tag to the database.
     * @param tag the tag to be added
     */
    public void addTag(Tag tag) {
        tags.add(tag);
    }

    /**
     * Returns the list of tags in the database.
     * @return the list of tags
     */
    public Vector<Tag> getTags() {
        return tags;
    }

    /**
     * Updates a tag in the database by replacing the existing tag with the new tag.
     * @param newTag the new tag to replace the existing tag
     * @return true if the tag was updated, false otherwise
     */
    public boolean updateTag(Tag newTag) {
        UUID id = newTag.getId();
        if (removeTag(id)) {
            addTag(newTag);
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the tags in the database.
     * @return a string representation of the tags
     */
    @Override
    public String toString() {
        if (tags.isEmpty()) {
            return "Nenhuma tag cadastrada.";
        }

        StringBuilder builder = new StringBuilder("Lista de Tags:\n");
        for (Tag tag : tags) {
            builder.append(tag).append("\n");
        }
        return builder.toString();
    }

    /**
     * Removes a tag from the database.
     * @param id the ID of the tag to be removed
     * @return true if the tag was removed, false otherwise
     */
    public boolean removeTag(UUID id) {
        return tags.removeIf(tag -> tag.getId().equals(id));
    }

    /**
     * Clears all tags from the database.
     */
    public void clearTags() {
        tags.clear();
    }
}