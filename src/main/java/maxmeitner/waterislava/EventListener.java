package maxmeitner.waterislava;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;
import java.util.TreeSet;

public class EventListener implements Listener {
    private static Set<String> playersInWater = new TreeSet();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (event.getFrom().getBlock().getType()==Material.WATER) {
            player.setFireTicks(100);
            if (!playersInWater.contains(player.getName())) {
                playersInWater.add(player.getName());
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (playersInWater.contains(player.getName())) {player.damage(1);}
                        else {cancel();}
                    }
                }.runTaskTimer(WaterIsLava.getPlugin(), 0, 20);
            }
        } else if (playersInWater.contains(player.getName())) {playersInWater.remove(player.getName());}
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            if (event.getCause()==EntityDamageEvent.DamageCause.LAVA) {
                event.setCancelled(true);
                entity.setFireTicks(0);
            }
        }
    }
}
