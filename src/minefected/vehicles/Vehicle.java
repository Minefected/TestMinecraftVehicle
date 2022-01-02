package minefected.vehicles;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String owner;
    private ArmorStand main;
    private List<ArmorStand> seats = new ArrayList<>();
    private TestMinecraftVehicle plugin;

    public Vehicle(Player player, TestMinecraftVehicle plugin){
        this.plugin = plugin;
        this.owner = player.getName();
        this.main = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        seats.add((ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(1,0,0),EntityType.ARMOR_STAND));
        main.setCustomName(owner+"."+main);

        seats.get(0).setCustomName(owner+"."+0);
        seats.get(0).setMetadata(owner+".0", new FixedMetadataValue(plugin, "yes!"));
    }

    public void move(Player player){
        main.teleport(main.getLocation().add(player.getVelocity()));
        seats.get(0).teleport(seats.get(0).getLocation().add(player.getVelocity()));

    }

    public void mount(Player player){
        seats.get(0).addPassenger(player);

    }

}