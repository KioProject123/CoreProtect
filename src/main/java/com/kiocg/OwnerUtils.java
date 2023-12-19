package com.kiocg;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class OwnerUtils {
    private static final NamespacedKey ownerUUID = new NamespacedKey("KioCG-ContainerOwner".toLowerCase(), "OwnerUUID".toLowerCase());
    private static final NamespacedKey timeMillis = new NamespacedKey("KioCG-ContainerOwner".toLowerCase(), "TimeMillis".toLowerCase());

    public static boolean isOwner(final @NotNull Container container, final @NotNull Player player) {
        return player.hasPermission("coreprotect.teleport") || player.getUniqueId().equals(getOwner(container));
    }

    public static @Nullable UUID getOwner(final @NotNull PersistentDataHolder holder) {
        final String uuidString = holder.getPersistentDataContainer().get(ownerUUID, PersistentDataType.STRING);
        return uuidString != null ? UUID.fromString(uuidString) : null;
    }

    public static boolean hasOwner(final @NotNull PersistentDataHolder holder) {
        return holder.getPersistentDataContainer().has(ownerUUID);
    }

    public static long getTimeMillis(final @NotNull PersistentDataHolder holder) {
        final Long time = holder.getPersistentDataContainer().get(timeMillis, PersistentDataType.LONG);
        return time != null ? time : 0L;
    }

    public static Chest doubleChest(final @NotNull DoubleChest doubleChest) {
        final Chest leftSide = (Chest) doubleChest.getLeftSide(false);
        final Chest rightSide = (Chest) doubleChest.getRightSide(false);
        final long leftTimeMillis = leftSide != null ? getTimeMillis(leftSide) : 0L;
        final long rightTimeMillis = rightSide != null ? getTimeMillis(rightSide) : 0L;
        if (leftTimeMillis != 0L && rightTimeMillis != 0L) {
            return leftTimeMillis > rightTimeMillis ? rightSide : leftSide;
        } else if (leftTimeMillis == 0L) {
            return rightSide;
        } else {
            return leftSide;
        }
    }
}
