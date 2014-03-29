package Lihad.BeyondTab;

import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class BeyondTab extends JavaPlugin implements Listener {
	protected static String PLUGIN_NAME = "BeyondTab";
	protected static String header = "[" + PLUGIN_NAME + "] ";
	public static Permission permission = null;
	private static Logger log = Logger.getLogger("Minecraft");

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		setupPermissions();
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(permission.playerInGroup(event.getPlayer(), "Moderator"))event.getPlayer().setPlayerListName(ChatColor.AQUA+event.getPlayer().getName());
		if(permission.playerInGroup(event.getPlayer(), "GM"))event.getPlayer().setPlayerListName(ChatColor.DARK_GREEN+event.getPlayer().getName());
		if(permission.playerInGroup(event.getPlayer(), "Admin"))event.getPlayer().setPlayerListName(ChatColor.DARK_RED+event.getPlayer().getName());
		if(permission.playerInGroup(event.getPlayer(), "ServerAdmin"))event.getPlayer().setPlayerListName(ChatColor.DARK_PURPLE+event.getPlayer().getName());
	}
	@EventHandler
	public void onPluginEnable(PluginEnableEvent event){
		if((event.getPlugin().getDescription().getName().equals("Vault"))) setupPermissions();
	}
	private boolean setupPermissions(){
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
	public static void info(String message){ 
		log.info(header + ChatColor.WHITE + message);
	}
	public static void severe(String message){
		log.severe(header + ChatColor.RED + message);
	}
	public static void warning(String message){
		log.warning(header + ChatColor.YELLOW + message);
	}
	public static void log(java.util.logging.Level level, String message){
		log.log(level, header + message);
	}
}
