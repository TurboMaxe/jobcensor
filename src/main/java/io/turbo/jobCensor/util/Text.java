package io.turbo.jobCensor.util;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@UtilityClass
public class Text {
    public static final MiniMessage mini = MiniMessage.miniMessage();
    public static final PlainTextComponentSerializer plain = PlainTextComponentSerializer.plainText();

    public Component text(@NotNull List<String> strings) {
        return mini.deserialize(String.join("\n",strings));
    }

}
