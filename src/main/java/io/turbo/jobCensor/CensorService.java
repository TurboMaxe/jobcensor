package io.turbo.jobCensor;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import io.turbo.jobCensor.util.Text;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CensorService implements ChatRenderer, Listener {
    private static CensorService instance = null;


    private final Component censorMessage =
            Text.text(List.of(
                    "<gray><bold> ----------------------------</bold></gray>",
                    "<dark_gray>[</dark_gray><red><bold>✘</bold></red><dark_gray>]</dark_gray>  <#FFFFFF>Please censor the word</#FFFFFF> <#E90800><italic>'job'</italic></#E90800>",
                    "<#B4B4B4><italic>Do not mention empl*yment.</italic></#B4B4B4>",
                    "<gray> ----------------------------</gray>"
            ));
    private static final Pattern PATTERN = Pattern.compile("(?i)\\bjob\\b");

    public static CensorService i() {
        if (instance == null) {
            instance = new CensorService();
        }
        return instance;
    }
    
    @EventHandler
    public void on(AsyncChatEvent event) {
        if (!PATTERN.matcher(Text.plain.serialize(event.message())).find()) return;

        event.getPlayer().sendMessage(censorMessage);
        event.setCancelled(true);
    }

    @Override
    public @NotNull Component render(@NotNull Player player, Component component, Component component1, @NotNull Audience audience) {
      final Component component2 = component1.replaceText(b -> b.match(PATTERN).replacement("***"));
      return component
              .append(Component.text(": "))
              .append(component2);
    }
}
