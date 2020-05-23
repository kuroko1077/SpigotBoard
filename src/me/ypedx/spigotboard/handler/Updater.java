package me.ypedx.spigotboard.handler;

import me.ypedx.spigotboard.SpigotBoard;
import me.ypedx.spigotboard.util.StringUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Updater {

    private SpigotBoard instance;
    private String currentVersion, fetchedVersion;

    public Updater(SpigotBoard instance) {
        this.instance = instance;
        this.currentVersion = instance.getDescription().getVersion();
        new UpdaterTask(instance).runTaskTimer(instance, 0, 12000);
    }

    public void checkForUpdates(){
        try {
            URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=47497");
            URLConnection connection = url.openConnection();

            fetchedVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
        } catch (Exception e){
            instance.getLogger().warning("Failed to connect to remote host to check for updates.");
        }
    }

    public boolean updateAvailable(){
        return !currentVersion.equals(fetchedVersion);
    }

    public TextComponent[] constructNewUpdateMessage(){
        TextComponent component = new TextComponent(StringUtils.color("&cSpigot&7Board &8» &7New update found: &c" + fetchedVersion + " &8(current: &8" + currentVersion + ")"));

        TextComponent component2 = new TextComponent(StringUtils.color("&cSpigot&7Board &8» &cClick here to download."));
        component2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/47497"));
        component2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(StringUtils.color("&7Click to go to the download page"))).create()));

        return new TextComponent[] { component, component2 };
    }
}
