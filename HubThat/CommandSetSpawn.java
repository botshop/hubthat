package HubThat;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandSetSpawn implements CommandExecutor{
	Spawn plugin;
	YamlConfiguration s;
	
	public CommandSetSpawn(Spawn plugin){
		this.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args){
		if(!(sender instanceof Player)){
			String ONLY_PLAYERS = plugin.getConfig().getString("setspawn.ONLY_PLAYERS");
			sender.sendMessage(ChatColor.DARK_RED + ONLY_PLAYERS);
			return true;
		}
		Player player = (Player) sender;
		if(CommandLabel.equalsIgnoreCase("setspawn"))
		{if(sender.hasPermission(new permission().SetSpawn))
			{
			File spawn = new File(plugin.getDataFolder() + File.separator + "spawn.yml");
	        if (!spawn.exists()) {
	          try {
	            spawn.createNewFile();
	          }
	          catch (Exception e)
	          {
	            e.printStackTrace();

				String SET_ERROR = plugin.getConfig().getString("setspawn.SET_ERROR");
	            player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.RED + SET_ERROR);
	            return true;
	          }
	        }

	        s = YamlConfiguration.loadConfiguration(spawn);
	        
			player.getWorld().setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
	        s.set("spawn.world." + player.getWorld().getName(), player.getWorld().getName());
	        s.set("spawn.x." + player.getWorld().getName(), player.getLocation().getX());
	        s.set("spawn.y." + player.getWorld().getName(), player.getLocation().getY());
	        s.set("spawn.z." + player.getWorld().getName(), player.getLocation().getZ());
	        s.set("spawn.yaw." + player.getWorld().getName(), Float.valueOf(player.getLocation().getYaw()));
	        s.set("spawn.pitch." + player.getWorld().getName(), Float.valueOf(player.getLocation().getPitch()));
	        s.set("spawn.version." + player.getWorld().getName(), Spawn.version);
	        try {
	          s.save(spawn);
	        } catch (Exception e) {
				String SET_ERROR = plugin.getConfig().getString("setspawn.SET_ERROR");
	          player.sendMessage(ChatColor.RED + SET_ERROR);
	          return true;
	        }
	        player.getWorld().setSpawnLocation(player.getLocation().getBlockX(), 
	        player.getLocation().getBlockY(), player.getLocation().getBlockZ());

			String SPAWN_SUCCESS_1 = plugin.getConfig().getString("setspawn.SPAWN_SUCCESS_1");
			String SPAWN_SUCCESS_2 = plugin.getConfig().getString("setspawn.SPAWN_SUCCESS_2");
	        player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GREEN  + SPAWN_SUCCESS_1 + " " + player.getLocation().getWorld().getName() + ChatColor.GREEN + SPAWN_SUCCESS_2);
			}else{

				String NO_PERMISSIONS = plugin.getConfig().getString("setspawn.NO_PERMISSIONS");
		          player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.RED +  NO_PERMISSIONS);
			}
		}
		return false;
	}
}
