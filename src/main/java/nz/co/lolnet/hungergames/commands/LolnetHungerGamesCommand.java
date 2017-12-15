package nz.co.lolnet.hungergames.commands;

import net.md_5.bungee.api.ChatColor;
import nz.co.lolnet.hungergames.HungerGames;
import nz.co.lolnet.hungergames.util.HungerGamesUtil;
import nz.co.lolnet.hungergames.util.Reference;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LolnetHungerGamesCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase(Reference.PLUGIN_ID)) {
            return false;
        }
        
        if (args.length == 0) {
            HungerGamesUtil.sendMessage(sender, HungerGamesUtil.getPluginInformation().create());
            return true;
        }
        
        if (args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission(Reference.PLUGIN_NAME + ".Reload")) {
            HungerGames.getInstance().getConfiguration().loadConfig();
            HungerGamesUtil.sendMessage(sender, HungerGamesUtil.getTextPrefix().append("Configuration Reloaded.").color(ChatColor.GREEN).create());
            return true;
        }
        
        HungerGamesUtil.sendMessage(sender, HungerGamesUtil.getTextPrefix().append("Invalid Arguments!").color(ChatColor.RED).create());
        return true;
    }
}