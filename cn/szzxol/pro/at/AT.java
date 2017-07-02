package cn.szzxol.pro.at;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class AT extends JavaPlugin {

    public static FileConfiguration DefaultConfig;
    public static String version;

    @Override
    public void onEnable() {
        getLogger().info("插件正在加载...");
        getServer().getPluginManager().registerEvents(new PlayerChat(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatTabComplete(), this);
        this.saveDefaultConfig();
        DefaultConfig = this.getConfig();
        version = DefaultConfig.getString("Version");
        getLogger().info("插件加载完成...");
    }

    @Override
    public void onDisable() {
        getLogger().info("插件卸载完成...");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("at")) {
            //<editor-fold defaultstate="collapsed" desc="帮助">
            if (args.length < 1 || !args[0].equalsIgnoreCase("reload")) {
                //<editor-fold defaultstate="collapsed" desc="帮助信息">
                sender.sendMessage((new StringBuilder()).append(ChatColor.WHITE).append(ChatColor.BOLD).append("##########插件信息##########").toString());
                sender.sendMessage((new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("### 插件： PRO-@").toString());
                sender.sendMessage((new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("### 作者： I_promise").toString());
                sender.sendMessage((new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("### 版本： ").append(version).toString());
                sender.sendMessage((new StringBuilder()).append(ChatColor.WHITE).append(ChatColor.BOLD).append("##########插件指令##########").toString());
                sender.sendMessage((new StringBuilder()).append(ChatColor.YELLOW).append(ChatColor.BOLD).append("/rpg reload 重载配置文件").toString());
                sender.sendMessage((new StringBuilder()).append(ChatColor.WHITE).append(ChatColor.BOLD).append("##########插件指令##########").toString());
                //</editor-fold>
                return true;
            }
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="重载">
            if (args[0].equalsIgnoreCase("reload")) {
                DefaultConfig = AT.instance.getConfig();
                sender.sendMessage((new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("【Pro-@】").append(ChatColor.GREEN).append(ChatColor.BOLD).append("重载完成！").toString());
                return true;
            }
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="提示输入帮助">
            sender.sendMessage((new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("【Pro-@】").append(ChatColor.RED).append(ChatColor.BOLD).append("不存在的指令！").toString());
            return true;
            //</editor-fold>
        }
        return true;
    }

    public static AT instance;

    public AT() {
        instance = this;
    }
}
