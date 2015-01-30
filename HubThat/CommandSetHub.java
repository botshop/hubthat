package HubThat;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandSetHub implements CommandExecutor{
	Spawn plugin;
	YamlConfiguration s;
	
	public CommandSetHub(Spawn plugin){
		this.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args){
		if(!(sender instanceof Player)){

			String ONLY_PLAYERS = plugin.getConfig().getString("sethub.ONLY_PLAYERS");
			sender.sendMessage(ChatColor.DARK_RED + ONLY_PLAYERS);
			return true;
		}
		Player player = (Player) sender;
		if(CommandLabel.equalsIgnoreCase("sethub"))
		{if(sender.hasPermission(new permission().SetHub))
			{
			File hub = new File(plugin.getDataFolder() + File.separator + "hub.yml");
			if(!hub.exists())
					{try{
						hub.createNewFile();
						
						
					}catch(Exception e){
						e.printStackTrace();

						String SET_ERROR = plugin.getConfig().getString("sethub.SET_ERROR");
						player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + SET_ERROR);
						return true;
					}
				
					}
			s = YamlConfiguration.loadConfiguration(hub);
			s.set("hub.world", player.getLocation().getWorld().getName());
			s.set("hub.x", player.getLocation().getX());
			s.set("hub.y", player.getLocation().getY());
			s.set("hub.z", player.getLocation().getZ());
			s.set("hub.yaw", player.getLocation().getYaw());
			s.set("hub.pitch", player.getLocation().getPitch());
			
			try{
				s.save(hub);
			}catch (Exception e){

				String SET_ERROR = plugin.getConfig().getString("sethub.SET_ERROR");
				player.sendMessage(ChatColor.RED + SET_ERROR);
				return true;
			}

			String HUB_SUCCESS = plugin.getConfig().getString("sethub.HUB_SUCCESS_1");
			String HUB_SUCCESS_2 = plugin.getConfig().getString("sethub.HUB_SUCCESS_2");
	        player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + HUB_SUCCESS + ChatColor.GREEN  + " " + ChatColor.GOLD + player.getLocation().getWorld().getName() + ChatColor.GREEN + HUB_SUCCESS_2);
			
			}else{
				String NO_PERMISSIONS = plugin.getConfig().getString("sethub.NO_PERMISSIONS");
		          player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.RED +  NO_PERMISSIONS);
			}
	
		}
		
		return false;
	}

}
