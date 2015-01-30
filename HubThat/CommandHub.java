package HubThat;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.util.Timer;
import java.util.TimerTask;

public class CommandHub implements CommandExecutor{
	YamlConfiguration s;
	
	Spawn plugin;
	public CommandHub(Spawn plugin){
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args){
		if(!(sender instanceof Player)){
			String onlyplayers = plugin.getConfig().getString("hub.ONLY_PLAYERS");
			sender.sendMessage(ChatColor.DARK_RED + onlyplayers);
			return true;
		}
		final Player player = (Player) sender;
		if(CommandLabel.equalsIgnoreCase("hub")){
			if(sender.hasPermission(new permission().Hub)){
				
			File hub = new File(plugin.getDataFolder() + File.separator + "hub.yml");
			if(!hub.exists()){
				String not_set = plugin.getConfig().getString("hub.HUB_NOT_SET");
		        player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.RED + not_set);
		        return true;
			} if (!sender.hasPermission(new permission().HubDelayBypass)){
			Long hubdelay = plugin.getConfig().getLong("hub.delay");
			String hubdelaytextwait = plugin.getConfig().getString("hub.DELAY_TEXT_WAIT");
			String hubdelaytextseconds = plugin.getConfig().getString("hub.DELAY_TEXT_SECONDS");
	        player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GREEN + hubdelaytextwait + " " + hubdelay / 1000 + ChatColor.GREEN + " " + hubdelaytextseconds);
	        
			s = YamlConfiguration.loadConfiguration(hub);
			
	        String world = s.getString("hub.world");
			double x = this.s.getDouble("hub.x");
			double y = this.s.getDouble("hub.y");
			double z = this.s.getDouble("hub.z");
			final double yaw = this.s.getDouble("hub.yaw");
			final double pitch = this.s.getDouble("hub.pitch");
			Timer timer = new Timer();
			  final Location loc = new Location(Bukkit.getWorld(world), x, y, z);
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
				        loc.setYaw((float)yaw);
				        loc.setPitch((float)pitch);
				        player.teleport(loc);
						String hubteleported = plugin.getConfig().getString("hub.TELEPORTED");
						player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GREEN + hubteleported);
				  
				  }
				}, hubdelay);
			} else if (sender.hasPermission(new permission().HubDelayBypass)){
				
				s = YamlConfiguration.loadConfiguration(hub);
				
		        String world = s.getString("hub.world");;
				double x = this.s.getDouble("hub.x");
				double y = this.s.getDouble("hub.y");
				double z = this.s.getDouble("hub.z");
				final double yaw = this.s.getDouble("hub.yaw");
				final double pitch = this.s.getDouble("hub.pitch");
				  final Location loc = new Location(Bukkit.getWorld(world), x, y, z);
					        loc.setYaw((float)yaw);
					        loc.setPitch((float)pitch);
					        player.teleport(loc);
							String hubteleported = plugin.getConfig().getString("hub.TELEPORTED");
							player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GREEN + hubteleported);
					  
				}
		}else{
			String hubnoperm = plugin.getConfig().getString("hub.NO_PERMISSIONS");
	          player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.RED +  hubnoperm);
		}
		}
		return false;
	}
	
}
