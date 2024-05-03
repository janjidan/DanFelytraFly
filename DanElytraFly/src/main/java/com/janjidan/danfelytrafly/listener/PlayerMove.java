package com.janjidan.danfelytrafly.listener;

import com.janjidan.danfelytrafly.config.LoadData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

import static com.janjidan.danfelytrafly.util.Elytra.stopElytraFlyEffect;

public class PlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) throws IOException {
        if (LoadData.getDataFile(event.getPlayer()).equals("true")) {
            Player player = event.getPlayer();
            Block block = player.getLocation().getBlock();
            // 检查玩家脚下的方块是否是水或者藤蔓
            if (player.isOnGround() || block.getType() == Material.WATER || block.getType() == Material.VINE || block.getType() == Material.LADDER) {
                stopElytraFlyEffect(player);
                //removeFakeElytra(player);
                player.setFallDistance(0.0F);
                player.updateInventory();
                LoadData.setDataFileFalse(player);
/*                config.set("isElytra."+player.getName(),"false");
                config.save(file);*/
                //this.saveConfig();
                //isElytra = false;
                //player.sendMessage("7");
            }

        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) throws IOException {
        // 获取退出玩家的名称
        Player player = event.getPlayer();
        if(LoadData.getDataFile(player).equals("true")){
            stopElytraFlyEffect(player);
            player.updateInventory();
            LoadData.setDataFileFalse(player);
//            config.set("isElytra."+player.getName(),"false");
//            config.save(file);
            //this.saveConfig();
            //isElytra = false;
        }
    }
}
