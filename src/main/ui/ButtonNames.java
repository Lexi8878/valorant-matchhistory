package ui;

public enum ButtonNames {
    ADD("Add match"),
    WINRATE("Calculate win rate"),
    GO_TO_MATCH_HISTORY("Current Match History"),
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
