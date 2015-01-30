package HubThat;

import org.bukkit.permissions.Permission;

public class permission {
	public Permission Spawn;
	public Permission SetSpawn;
	public Permission SetHub;
	public Permission Hub;
	public Permission HubDelayBypass;
	public Permission SpawnDelayBypass;
	public permission()
	{
		Spawn = new Permission ("hubthat.spawn");
		SetSpawn = new Permission ("hubthat.setspawn");
        SetHub = new Permission ("hubthat.sethub");
        Hub = new Permission ("hubthat.hub");
        SpawnDelayBypass = new Permission ("hubthat.nospawndelay");
        HubDelayBypass = new Permission ("hubthat.nohubdelay");
		
	}
}
