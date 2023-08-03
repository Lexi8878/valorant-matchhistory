package ui;

// enum of button names
public enum ButtonNames {
    ADD("Add"),
    WINRATE("Calculate win rate"),
    AGENT("See number of games on agents"),
    GO_TO_ADD("Add a match"),
    GO_TO_MATCH_HISTORY("Current Match History"),
    GO_TO_WINRATE("Calculate stats"),
    GENERATE_MATCH_HISTORY("Match History Status"),
    SAVE("Save match history"),
    LOAD("Load match history");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    //EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
