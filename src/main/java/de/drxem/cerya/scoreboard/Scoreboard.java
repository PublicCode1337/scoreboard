package de.drxem.cerya.scoreboard;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.List;

public class Scoreboard extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        startScoreboardUpdater();
    }

    @Override
    public void onDisable() {
    }

    private void startScoreboardUpdater() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> createScoreboard(player));
            }
        }.runTaskTimer(this, 0L, 40L);
    }

    private void createScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) {
            getLogger().severe("Fehler: ScoreboardManager konnte nicht gefunden werden!");
            return;
        }

        org.bukkit.scoreboard.Scoreboard scoreboard = manager.getNewScoreboard();

        String title = getConfig().getString("scoreboard.title");
        if (title == null) {
            getLogger().severe("Fehler: Kein Titel für das Scoreboard in der Config gefunden.");
            return;
        }

        Objective objective = scoreboard.registerNewObjective("example", "dummy", formatColors(applyPlaceholders(player, title)));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        List<String> scores = getConfig().getStringList("scoreboard.scores");

        for (String entry : scores) {
            String[] parts = entry.split(",", 2);
            if (parts.length == 2) {
                String scoreText = parts[0].trim();
                int scoreValue;
                try {
                    scoreValue = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    getLogger().severe("Ungültiges Format in der Config: " + entry);
                    continue;
                }

                String processedText = formatColors(applyPlaceholders(player, scoreText));

                Score scoreItem = objective.getScore(processedText);
                scoreItem.setScore(scoreValue);
            } else {
                getLogger().severe("Ungültiges Format in der Config: " + entry);
            }
        }

        player.setScoreboard(scoreboard);
    }

    private String formatColors(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private String applyPlaceholders(Player player, String text) {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            return PlaceholderAPI.setPlaceholders(player, text);
        }
        return text;
    }
}