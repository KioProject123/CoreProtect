package net.coreprotect;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ItemCN {
    private static final Map<String, String> itemCN = new HashMap<>();

    public int loadItemCN(final @NotNull FileConfiguration config) {
        config.getValues(false).forEach((material, itemCN) -> ItemCN.itemCN.put(material, (String) itemCN));
        return itemCN.size();
    }

    // 获取物品的中文名
    public static @NotNull String getItemCN(final @NotNull String string) {
        return itemCN.containsKey(string) ? itemCN.get(string) + "(" + string + ")" : string;
    }
}
