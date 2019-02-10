package io.github.teambanhammer;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class atSigns implements Listener {

    @EventHandler
    public void atSigns (PlayerInteractEvent Sign){
        if (Sign.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = Sign.getPlayer();
            Block clickedBlock = Sign.getClickedBlock();
            BlockState state = clickedBlock.getState();
            if (state instanceof org.bukkit.block.Sign) {
                Sign sign = (Sign) state;
                String line1 = sign.getLine(0);
                if (line1.equalsIgnoreCase("[RandomTP]")){
                    player.performCommand("tpr");
                }
            }
        }
    }

    @EventHandler
    public void placeSign (SignChangeEvent Place){
        Block placeBlock = Place.getBlock();
        BlockState state = placeBlock.getState();
        Player placer = Place.getPlayer();
        if (state instanceof Sign) {
            Sign sign = (Sign) state;
            if (Place.getLine(0).equalsIgnoreCase("[RandomTP]")) {
                if (!placer.hasPermission("tbh.tp.admin.tprsign")){
                    placer.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR:" + ChatColor.RED + " You do not have permission to make this sign!");
                    Place.setCancelled(true);
                } else {

                    Place.setLine(0, ChatColor.BLUE + "" + ChatColor.BOLD + "[RandomTP]");
                    Place.setLine(1, ChatColor.ITALIC + "Click me!");
                    placer.sendMessage(ChatColor.GREEN + "Successfully created the RandomTP sign!");
                }
            }
        }
    }
}
