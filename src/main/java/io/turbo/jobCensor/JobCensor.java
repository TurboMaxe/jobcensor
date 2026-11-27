package io.turbo.jobCensor;

import io.turbo.jobCensor.util.Text;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class JobCensor extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(
            Text.text(List.of(
                "",
                "Loaded JobCensor",
                ""
            )));
        Bukkit.getPluginManager().registerEvents(CensorService.i(), this);
    }
}
