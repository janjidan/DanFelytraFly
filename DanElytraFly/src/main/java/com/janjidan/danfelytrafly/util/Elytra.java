package com.janjidan.danfelytrafly.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import com.comphenix.protocol.wrappers.WrappedDataValue;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Elytra {
    public static void sendelytraFlyEffect(Player player) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        byte elytraFlag = (byte) 0x80;
        packet.getIntegers().write(0, player.getEntityId());
        WrappedDataValue wrapped = new WrappedDataValue(0, WrappedDataWatcher.Registry.get(Byte.class), elytraFlag);
        StructureModifier<List<WrappedDataValue>> data = packet.getDataValueCollectionModifier();
        data.write(0, Lists.newArrayList(wrapped));

        PacketContainer fakeElytraPacket = protocolManager.createPacket(PacketType.Play.Server.ENTITY_EQUIPMENT);
        fakeElytraPacket.getIntegers().write(0, player.getEntityId()); // 玩家的实体ID
        // 创建一个假的鞘翅物品堆栈
        ItemStack fakeElytra = new ItemStack(Material.ELYTRA);
        // 将这个假的鞘翅设置到背部装备槽
        Pair<EnumWrappers.ItemSlot, ItemStack> pair = new Pair<>(EnumWrappers.ItemSlot.CHEST, fakeElytra);
        // 使用1.16+的方法设置装备
        StructureModifier<List<Pair<EnumWrappers.ItemSlot, ItemStack>>> fakedata = fakeElytraPacket.getSlotStackPairLists();
        fakedata.write(0, Lists.newArrayList(pair));

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            try {
                protocolManager.sendServerPacket(player1, packet);
                protocolManager.sendServerPacket(player1, fakeElytraPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopElytraFlyEffect(Player player) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        byte elytraFlag = 0; // 设置为0来清除飞行效果
        packet.getIntegers().write(0, player.getEntityId());
        WrappedDataValue wrapped = new WrappedDataValue(0, WrappedDataWatcher.Registry.get(Byte.class), elytraFlag);
        StructureModifier<List<WrappedDataValue>> data = packet.getDataValueCollectionModifier();
        data.write(0, Lists.newArrayList(wrapped));
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            try {
                protocolManager.sendServerPacket(player1, packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
