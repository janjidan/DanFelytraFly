package com.janjidan.danfelytrafly.config;

import com.janjidan.danfelytrafly.util.Color;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

import static com.janjidan.danfelytrafly.DanFelytraFly.instance;

public class LoadLang {
    public static String perfix;
    public static List<?> elytraHelp;
    public static String elytrreload;
    public static String elytrError1;
    public static String elytrError2;
    public static String elytrNoFindPlayer;



    public static void reloadLangFile(){
        final String path = "lang.yml";
        final File file = new File(instance.getDataFolder(), path);
        if (!file.exists()) {
            instance.saveResource(path, true);
        }
        final YamlConfiguration configLang = YamlConfiguration.loadConfiguration(file);

        try {

            perfix = Color.stringColorTranslate(configLang.getString("perfix"));
            elytrreload = Color.stringColorTranslate(configLang.getString("felytrareload"));
            elytraHelp = Color.listColorTranslate((List<String>) configLang.getList("felytrahelp"));
            elytrError1 = Color.stringColorTranslate(configLang.getString("felytr_error_statement_1"));
            elytrError2 = Color.stringColorTranslate(configLang.getString("felytr_error_statement_2"));
            elytrNoFindPlayer = Color.stringColorTranslate(configLang.getString("felytr_nofindplayer"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
