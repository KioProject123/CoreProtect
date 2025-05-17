package com.kiocg;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public class OwnerUtils {
    private static final NamespacedKey ownerUUID = NamespacedKey.kiocg("owner");
    private static final NamespacedKey placeTime = NamespacedKey.kiocg("place_time");

    public static boolean isOwner(final @NotNull Container container, final @NotNull Player player) {
        return player.hasPermission("coreprotect.teleport") || player.getUniqueId().equals(getOwner(container));
    }

    public static @Nullable UUID getOwner(final @NotNull PersistentDataHolder holder) {
        //TODO CoreProtect未记录实体上的容器
        if (holder instanceof Tameable) {
            return ((Tameable) holder).getOwnerUniqueId();
        }
        return holder.getPersistentDataContainer().get(ownerUUID, PersistentDataType.UUID);
    }

    public static boolean hasOwner(final @NotNull PersistentDataHolder holder) {
        return holder.getPersistentDataContainer().has(ownerUUID);
    }

    public static long getPlaceTime(final @NotNull PersistentDataHolder holder) {
        return holder.getPersistentDataContainer().getOrDefault(placeTime, PersistentDataType.LONG, 0L);
    }

    public static @NotNull Chest getDoubleChestSide(final @NotNull DoubleChest doubleChest) {
        final Chest leftSide = Objects.requireNonNull((Chest) doubleChest.getLeftSide(false));
        final Chest rightSide = Objects.requireNonNull((Chest) doubleChest.getRightSide(false));
        if (leftSide.hasLootTable()) {
            return leftSide;
        } else if (rightSide.hasLootTable()) {
            return rightSide;
        }

        final long leftPlaceTime = OwnerUtils.getPlaceTime(leftSide);
        final long rightPlaceTime = OwnerUtils.getPlaceTime(rightSide);
        if (rightPlaceTime == 0L) {
            return leftSide;
        } else if (leftPlaceTime == 0L) {
            return rightSide;
        }

        // 大箱子左侧优先
        return leftPlaceTime <= rightPlaceTime ? leftSide : rightSide;
    }
}
