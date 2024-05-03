package com.janjidan.danfelytrafly.command;

import com.janjidan.danfelytrafly.config.LoadData;
import com.janjidan.danfelytrafly.config.LoadLang;
import com.janjidan.danfelytrafly.util.applyUpwardForce;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

import static com.janjidan.danfelytrafly.util.Elytra.sendelytraFlyEffect;

public class OnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (args.length >= 3) {
            Player player = Bukkit.getPlayer(args[0]);
            String num = args[1];
            String num2 = args[2];
            boolean isNumber = num.matches("\\d+");
            boolean isNumber2 = num2.matches("^-?\\d+(\\.\\d+)?$");
            if (!(player == null)) {
                if (isNumber) {
                    if(isNumber2){
                        try {
                            LoadData.setDataFileTrue(player);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        applyUpwardForce.applyUpwardForceToPlayer(player, Double.parseDouble(args[1]), Double.parseDouble(args[2]));
                        //sendFakeElytra(player);
                        sendelytraFlyEffect(player);
                        //player.sendMessage("调试语句1");
                        return true;
                    }else {
                        sender.sendMessage(LoadLang.perfix+LoadLang.elytrError1);
                        return true;
                    }
                } else {
                    sender.sendMessage(LoadLang.perfix+LoadLang.elytrError2);
                    return true;
                }

            } else {
                sender.sendMessage(LoadLang.perfix+LoadLang.elytrNoFindPlayer);
                return true;
            }
        }
        if (args.length == 0) {

            List<?> help = LoadLang.elytraHelp;
            for (int i = 0; i < help.size(); i++) {
                sender.sendMessage(String.valueOf(help.get(i)));
            }
            //sender.sendMessage(LoadLang.perfix+LoadLang.elytraHelp);
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
            if(!sender.hasPermission("danelytrafly.reload")){
                return true;
            }

            LoadLang.reloadLangFile();
            sender.sendMessage(LoadLang.perfix+LoadLang.elytrreload);

            return true;
        }
        return false;
    }
}
