package plannertracker.model;

/**
 * Highlight
 */
public class Highlight {
    private String message;

    public Highlight() {
        this.message = "";
    }

    public Highlight(String highlight) {
        this.message = highlight;
    }

    public String getHighlight() {return this.message;}

    public void setMessage(String message) {
        this.message = message;
    }
}