package HubThat;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import HubThat.Updater.UpdateResult;
import HubThat.Updater.UpdateType;
import HubThat.permission;

public class Spawn extends JavaPlugin implements Listener{
	
private Updater updater;
public static Spawn plugin;
public static double version = 2.5;
YamlConfiguration s;
public final Logger logger = Logger.getLogger("Minecraft");
/**/protected UpdateChecker updateChecker;
protected Logger log;
/**/

@EventHandler
public void playerJoin(PlayerJoinEvent e){
	/*if(e.getPlayer().isOp() && updater.getResult() == UpdateResult.SUCCESS)
	{
		e.getPlayer().sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GREEN + "New Update Installed: " + ChatColor.GOLD + updater.getLatestName() + ChatColor.GREEN + "!");
	      	
	}*/
	
	if(this.getConfig().getBoolean("updates.update-notify")){
		if(this.updateChecker.updateNeeded()){
			if(e.getPlayer().isOp()){
			e.getPlayer().sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HubThat" + ChatColor.BLACK + "] " +ChatColor.GREEN + "A new version is out: " + this.updateChecker.getVersion() + "!");
			e.getPlayer().sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HubThat" + ChatColor.BLACK + "] " +ChatColor.GREEN + "Download: " + this.updateChecker.getLink());
		}}
		} else if(!this.getConfig().getBoolean("updates.update-notify")){
			if(e.getPlayer().isOp()){
				e.getPlayer().sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HubThat" + ChatColor.BLACK + "] " + ChatColor.RED + "Update Checking Disabled!");
			}
		} else {
		}
	
}
@Override
public void onEnable(){
	updater = new Updater(this, 84588, getFile(), UpdateType.DEFAULT, this.getConfig().getBoolean("auto-update"));
	Bukkit.getPluginManager().registerEvents(this, this);
/**/	this.log = this.getLogger();
	this.updateChecker = new UpdateChecker(this, "http://dev.bukkit.org/bukkit-plugins/hubthat/files.rss");
	if(this.getConfig().getBoolean("updates.update-notify")){
	if(this.updateChecker.updateNeeded()){
		this.log.info(ChatColor.GREEN + "A new version is out: " + this.updateChecker.getVersion() + "!");
		this.log.info(ChatColor.GREEN + "Download: " + this.updateChecker.getLink());
	}
	} else if(!this.getConfig().getBoolean("updates.update-notify")){

		this.log.info(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.RED + "Update Checking Disabled!");
		
	} else {
		
	}
/**/	File plg = new File(this.getDataFolder() + "");
	if (!plg.exists()){
		plg.mkdir();
		
	}
	File config = new File(this.getDataFolder() + File.separator + "config.yml");
	if(!config.exists())
	{
		this.saveDefaultConfig();
	}
	this.getCommand("hub").setExecutor(new CommandHub(this));
	this.getCommand("sethub").setExecutor(new CommandSetHub(this));
	this.getCommand("setspawn").setExecutor(new CommandSetSpawn(this));
	this.getCommand("spawn").setExecutor(new CommandSpawn(this));
}
public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
      Player player = (Player)sender;
      if (command.getName().equalsIgnoreCase("hubthat")) {
          player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GRAY + "HubThat Version " + ChatColor.GOLD + version + ChatColor.GRAY +  " for SpigotMC/CraftBukkit " + ChatColor.GOLD + "1.7" + ChatColor.GRAY + "-" + ChatColor.GOLD + "1.8" + ChatColor.GRAY + ".");
          player.sendMessage(ChatColor.BLACK + "[" + ChatColor.GOLD + "HT" + ChatColor.BLACK + "] " + ChatColor.GRAY + "Coded by " + ChatColor.GOLD + "lol7344" + ChatColor.GRAY + " under the " + ChatColor.GOLD + "Gnu GPL v3 License" + ChatColor.GRAY + ".");
          
      }

    
    return false;
  }




}
