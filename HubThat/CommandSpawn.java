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
import java.util.TimerTask;
import java.util.Timer;

public class CommandSpawn implements CommandExecutor{
	YamlConfiguration s;
	
	Spawn plugin;
	public CommandSpawn(Spawn plugin){
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args){
		if(!(sender instanceof Player)){
			String ONLY_PLAYERS = plugin.getConfig().getString("spawn.ONLY_PLAYERS");
			sender.sendMessage(ChatColor.DARK_RED + ONLY_PLAYERS);
			return true;
		}
		final Player player = (Player) sender;
		if(CommandLabel.equalsIgnoreCase("spawn")){
			if(sender.hasPermission(new permission().Spawn)){
			
			File spawn = new File(plugin.getDataFolder() + File.separator + "spawn.yml");
			if(!spawn.exists()){
				String SPAWN_NOT_SET = plugin.getConfig().getString("spawn.SPAWN_NOT_SET");
		        player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.RED + SPAWN_NOT_SET);
		        return true;
			} if(!sender.hasPermission(new permission().SpawnDelayBypass)){
				
				s = YamlConfiguration.loadConfiguration(spawn);
				if(s.getDouble("spawn.x." + player.getWorld().getName()) != s.getDouble("spawn.x." + player.getWorld().getName())){
					
					String NOTSET = plugin.getConfig().getString("spawn.SPAWN_NOT_SET");
					player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GREEN + NOTSET);
				} else if (s.getDouble("spawn.x." + player.getWorld().getName()) == s.getDouble("spawn.x." + player.getWorld().getName())){
				
				if(s.getDouble("spawn.version." + player.getWorld().getName()) != Spawn.version)
		        {
		        	String outdatedspawn = plugin.getConfig().getString("spawn.outdated-spawn");
		        	player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GREEN + outdatedspawn);
		        }
				

	        
			Long spawndelay = plugin.getConfig().getLong("spawn.delay");
			String DELAY_TEXT_WAIT = plugin.getConfig().getString("spawn.DELAY_TEXT_WAIT");
			String DELAY_TEXT_SECONDS = plugin.getConfig().getString("spawn.DELAY_TEXT_SECONDS");
			player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GREEN + DELAY_TEXT_WAIT + " " + spawndelay / 1000 + ChatColor.GREEN + " " + DELAY_TEXT_SECONDS);
			Timer timer2 = new Timer();
			timer2.schedule(new TimerTask() {
				  @Override
				  public void run() {
					  	player.teleport(player.getWorld().getSpawnLocation());
					  	final double yaw = s.getDouble("spawn.yaw." + player.getWorld().getName());
				        final double pitch = s.getDouble("spawn.pitch." + player.getWorld().getName());
				        String world = s.getString("spawn.world." + player.getWorld().getName());;
						double x = s.getDouble("spawn.x." + player.getWorld().getName());
						double y = s.getDouble("spawn.y." + player.getWorld().getName());
						double z = s.getDouble("spawn.z." + player.getWorld().getName());
				        final Location loc = new Location(Bukkit.getWorld(world), x, y, z);
				        loc.setYaw((float)yaw);
				        loc.setPitch((float)pitch);
				        player.teleport(loc);
				        
						String TELEPORTED = plugin.getConfig().getString("spawn.TELEPORTED");
						player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GREEN + TELEPORTED);
					
				  }
				}, spawndelay);
			}
			} else if(sender.hasPermission(new permission().SpawnDelayBypass)){
				
				s = YamlConfiguration.loadConfiguration(spawn);
				if(s.getDouble("spawn.x." + player.getWorld().getName()) != s.getDouble("spawn.x." + player.getWorld().getName())){
					
					String NOTSET = plugin.getConfig().getString("spawn.SPAWN_NOT_SET");
					player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GREEN + NOTSET);
				}
				else if (s.getDouble("spawn.x." + player.getWorld().getName()) == s.getDouble("spawn.x." + player.getWorld().getName())){
					
					if(s.getDouble("spawn.version." + player.getWorld().getName()) != Spawn.version)
			        {
			        	String outdatedspawn = plugin.getConfig().getString("spawn.outdated-spawn");
			        	player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GREEN + outdatedspawn);
			        }
					player.teleport(player.getWorld().getSpawnLocation());
				  	final double yaw = s.getDouble("spawn.yaw." + player.getWorld().getName());
			        final double pitch = s.getDouble("spawn.pitch." + player.getWorld().getName());
			        String world = s.getString("spawn.world." + player.getWorld().getName());
					double x = s.getDouble("spawn.x." + player.getWorld().getName());
					double y = s.getDouble("spawn.y." + player.getWorld().getName());
					double z = s.getDouble("spawn.z." + player.getWorld().getName());
			        final Location loc = new Location(Bukkit.getWorld(world), x, y, z);
			        loc.setYaw((float)yaw);
			        loc.setPitch((float)pitch);
			        player.teleport(loc);
							String TELEPORTED = plugin.getConfig().getString("spawn.TELEPORTED");
							player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GREEN + TELEPORTED);
				}
			}
		}else{

			String NO_PERMISSIONS = plugin.getConfig().getString("spawn.NO_PERMISSIONS");
	          player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.RED +  NO_PERMISSIONS);
		}
		}
		return false;
	}
	
}
