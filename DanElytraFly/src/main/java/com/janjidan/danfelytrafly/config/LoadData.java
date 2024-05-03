package com.janjidan.danfelytrafly.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static com.janjidan.danfelytrafly.DanFelytraFly.instance;

public class LoadData {

    static final String path = "data.yml";
    static final File dataFile = new File(instance.getDataFolder(), path);
    static final YamlConfiguration dataConfig = YamlConfiguration.loadConfiguration(dataFile);

    public static String getDataFile(Player player){
        if (!dataFile.exists()) {
            instance.saveResource(path, true);
        }
        return dataConfig.getString("isElytra."+player.getName());
    }
    public static void setDataFileTrue(Player player) throws IOException {
        if (!dataFile.exists()) {
            instance.saveResource(path, true);
        }
        dataConfig.set("isElytra."+player.getName(),"true");
        dataConfig.save(dataFile);
    }
    public static void setDataFileFalse(Player player) throws IOException {
        if (!dataFile.exists()) {
            instance.saveResource(path, false);
        }
        dataConfig.set("isElytra."+player.getName(),"false");
        dataConfig.save(dataFile);
    }


}
