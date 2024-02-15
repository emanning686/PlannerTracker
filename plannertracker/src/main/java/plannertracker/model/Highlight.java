package plannertracker.model;

/**
 * Highlight
 */
public class Highlight {
    private final int DAYS;
    private String highlight;

    public Highlight(int DAYS) {
        this.DAYS = DAYS;
        this.highlight = "";
    }

    public Highlight(int DAYS, String highlight) {
        this.DAYS = DAYS;
        this.highlight = highlight;
    }

    public String getHighlight() {return this.highlight;}

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }
}