package net.darkunscripted.DarkGenerators.commands;

import net.darkunscripted.DarkGenerators.domain.Generator;
import net.darkunscripted.DarkGenerators.domain.PlayerProfile;
import net.darkunscripted.DarkGenerators.managers.GeneratorManager;
import net.darkunscripted.DarkGenerators.managers.ProfileManager;
import net.darkunscripted.DarkGenerators.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.jvm.hotspot.types.WrongTypeException;

public class GeneratorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        // /generator give (name) (generator)
        // /generator setmax (name) (amount)
        // /generator setmax (amount)
        if(s instanceof Player){
            Player p = (Player) s;
        }else{
            if(args.length < 2 || args.length > 3){
                s.sendMessage(Utils.chat("&b------&e&lGenerator&b------"));
                s.sendMessage(Utils.chat("&e- /generator give (name) (generator)"));
                s.sendMessage(Utils.chat("&e- /generator setmax (name) (amount)"));
                s.sendMessage(Utils.chat("&e- /generator setmax (amount)"));
            }else if(args.length == 2){
                if(args[0].equalsIgnoreCase("setmax")){
                    try{
                        int amount = Integer.parseInt(args[1]);
                        for(PlayerProfile profile : ProfileManager.getInstance().getProfiles()){
                            profile.setMaxGens(amount);
                        }
                    }catch (WrongTypeException e){
                        s.sendMessage(Utils.chat("&c[ERROR] " + args[1] + " is not a number"));
                    }
                }else{
                    s.sendMessage(Utils.chat("&b------&e&lGenerator&b------"));
                    s.sendMessage(Utils.chat("&e- /generator give (name) (generator)"));
                    s.sendMessage(Utils.chat("&e- /generator setmax (name) (amount)"));
                    s.sendMessage(Utils.chat("&e- /generator setmax (amount)"));
                }
            }else if(args.length == 3){
                if(args[0].equalsIgnoreCase("setmax")){
                    try {
                        int amount = Integer.parseInt(args[2]);
                        for (PlayerProfile playerProfile : ProfileManager.getInstance().getProfiles()) {
                            for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
                                if (player.getUniqueId().equals(playerProfile.getPlayer())) {
                                    playerProfile.setMaxGens(amount);
                                }
                            }
                        }
                    }catch (WrongTypeException e){
                        s.sendMessage(Utils.chat("&c[ERROR] " + args[1] + " is not a number!"));
                    }
                }else if(args[0].equalsIgnoreCase("give")){
                    OfflinePlayer OfflinePlayer = Bukkit.getOfflinePlayer(args[1]);
                    if(OfflinePlayer != null){
                        if(OfflinePlayer.isOnline()){
                            Player player = (Player) OfflinePlayer;
                            boolean check = true;
                            for(Generator generator : GeneratorManager.getInstance().getGenerators()){
                                if(generator.getName().equalsIgnoreCase(args[2])){
                                    GeneratorManager.getInstance().giveGenerator(generator, player);
                                }
                            }
                        }else{
                            s.sendMessage(Utils.chat("&c[ERROR] player is not online!"));
                        }
                    }else{
                        s.sendMessage(Utils.chat("&c[ERROR] That player has never joined the server"));
                    }
                }else{
                    s.sendMessage(Utils.chat("&b------&e&lGenerator&b------"));
                    s.sendMessage(Utils.chat("&e- /generator give (name) (generator)"));
                    s.sendMessage(Utils.chat("&e- /generator setmax (name) (amount)"));
                    s.sendMessage(Utils.chat("&e- /generator setmax (amount)"));
                }
            }
        }
        return false;
    }

}
