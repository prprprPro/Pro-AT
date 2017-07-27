package cn.szzxol.pro.at;

import static cn.szzxol.pro.at.AT.DefaultConfig;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author I_promise
 */
public class PlayerChat implements Listener {

    @EventHandler
    public void PlayerChat(AsyncPlayerChatEvent event) {
        String msg = event.getMessage();
        Player sender = event.getPlayer();
        if (msg.contains("@")) {
            List<Player> AllPlayers = new ArrayList<>();
            AllPlayers.addAll(Bukkit.getServer().getOnlinePlayers());
            if (msg.contains("@全体玩家")) {
                msg = msg.replaceAll("@全体玩家", (new StringBuilder()).append(ChatColor.AQUA).append("@全体玩家").append(ChatColor.RESET).toString());
                for (Player target : AllPlayers) {
                    PlaySound(sender, target);
                }
            } else {
                for (Player target : AllPlayers) {
                    if (msg.contains("@" + target.getName())) {
                        msg = msg.replaceAll("@" + target.getName(), (new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("@").append(target.getName()).append(ChatColor.RESET).toString());
                        PlaySound(sender, target);
                    }
                }
            }
            event.setMessage(msg);
        }
    }

    private static void PlaySound(Player sender, Player target) {
        int SoundType = DefaultConfig.getInt("Sound");
        switch (SoundType) {
            case 1:
                target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                sender.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                target.sendMessage((new StringBuilder()).append(ChatColor.WHITE).append(ChatColor.BOLD).append("[@] ").append(ChatColor.AQUA).append(sender.getName()).append(ChatColor.WHITE).append(" @了你").toString());
                break;

            default:
                target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                sender.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                target.sendMessage((new StringBuilder()).append(ChatColor.WHITE).append(ChatColor.BOLD).append("[@] ").append(ChatColor.AQUA).append(sender.getName()).append(ChatColor.WHITE).append(" @了你").toString());
                sender.sendMessage((new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("【Pro-@】").append(ChatColor.RED).append(ChatColor.BOLD).append("插件配置有误！").toString());
                break;
        }
    }

}
