package net.coreprotect;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ItemCN {
    private static final Map<String, String> itemCN = new HashMap<>();
    private static final Map<String, String> entityCN = new HashMap<>();

    public void loadItemCN(final @NotNull FileConfiguration config) {
        Objects.requireNonNull(config.getConfigurationSection("item")).getValues(false)
               .forEach((material, itemCN) -> ItemCN.itemCN.put(material, (String) itemCN));

        Objects.requireNonNull(config.getConfigurationSection("entity")).getValues(false)
               .forEach((material, entityCN) -> ItemCN.entityCN.put(material, (String) entityCN));
    }

    // 获取物品的中文名
    public static @NotNull String getItemCN(final @NotNull String string) {
        return itemCN.containsKey(string) ? itemCN.get(string) + "(" + string + ")" : string;
    }

    // 获取生物的中文名
    public static @NotNull String getEntityCN(final @NotNull String string) {
        return entityCN.containsKey(string) ? entityCN.get(string) + "(" + string + ")" : string;
    }
}
