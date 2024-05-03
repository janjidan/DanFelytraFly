package com.janjidan.danfelytrafly;

import com.janjidan.danfelytrafly.command.OnCommand;
import com.janjidan.danfelytrafly.config.LoadLang;
import com.janjidan.danfelytrafly.listener.PlayerMove;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DanFelytraFly extends JavaPlugin {
    public static DanFelytraFly instance;
    public static boolean pro = false;



    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginCommand("danfelytrafly").setExecutor(new OnCommand());
        LoadLang.reloadLangFile();
        Bukkit.getPluginManager().registerEvents(new PlayerMove(),this);


        if(Bukkit.getPluginManager().getPlugin("ProtocolLib") != null){
            Bukkit.getLogger().info("[DanFelytraFly] ┃━━━━━━━━━━━━━━━━━━━━━━━━┃");
            Bukkit.getLogger().info("[DanFelytraFly] ┃ 检测到发包插件         ┃");
            Bukkit.getLogger().info("[DanFelytraFly] ┗━━━━━━━━━━━━━━━━━━━━━━━━┛");
            pro = true;
        }else {
            Bukkit.getLogger().info("[DanFelytraFly] ┃━━━━━━━━━━━━━━━━━━━━━━━━┃");
            Bukkit.getLogger().info("[DanFelytraFly] ┃未检测到发包插件        ┃");
            Bukkit.getLogger().info("[DanFelytraFly] ┗━━━━━━━━━━━━━━━━━━━━━━━━┛");
        }
        // Plugin startup logic
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
