package me.ypedx.spigotboard.scoreboard;

import org.bukkit.scoreboard.DisplaySlot;

import java.util.List;

public class ScoreboardSettings {

    private String displayName;
    private List<String> lines;
    private double update_interval;
    private DisplaySlot displaySlot;

    public ScoreboardSettings(String displayName, List<String> lines, double update_interval, DisplaySlot displaySlot) {
        this.displayName = displayName;
        this.lines = lines;
        this.update_interval = update_interval;
        this.displaySlot = displaySlot;
    }

    public String getDisplayName(){
        return displayName;
    }

    public List<String> getLines(){
        return lines;
    }

    public double getUpdateInterval(){
        return update_interval;
    }

    public DisplaySlot getDisplaySlot() {
        return displaySlot;
    }
}
