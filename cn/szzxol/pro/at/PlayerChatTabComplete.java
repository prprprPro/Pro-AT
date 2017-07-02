package cn.szzxol.pro.at;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

/**
 *
 * @author I_promise
 */
public class PlayerChatTabComplete implements Listener {

    @EventHandler
    public void PlayerChatTabComplete(PlayerChatTabCompleteEvent event) {
        String msg = event.getChatMessage();
        if (msg.contains("@") && !msg.endsWith(" ")) {
            List<Player> AllPlayers = new ArrayList<>();
            AllPlayers.addAll(Bukkit.getServer().getOnlinePlayers());
            String str = msg.substring(msg.lastIndexOf("@") + 1);
            List AllFitPlayers = new ArrayList();
            for (Player player : AllPlayers) {
                if (player.getName().toLowerCase().startsWith(str.toLowerCase())) {
                    if (msg.contains(" ")) {
                        AllFitPlayers.add((new StringBuilder()).append(msg.substring(msg.lastIndexOf(" ") + 1, msg.lastIndexOf("@"))).append("@").append(player.getName()).toString());
                    } else {
                        AllFitPlayers.add((new StringBuilder()).append(msg.substring(0, msg.lastIndexOf("@"))).append("@").append(player.getName()).toString());
                    }
                }
                event.getTabCompletions().clear();
                event.getTabCompletions().addAll(AllFitPlayers);
            }
        }
    }
}
