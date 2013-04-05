package net.canarymod.commandsys.commands;


import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;
import net.canarymod.warp.Warp;


public class WarpCommand extends CanaryCommand {

    public WarpCommand() {
        super("canary.command.warp", Translator.translate("warp info"), Translator.translateAndFormat("usage", "/warp <warp name>"), 2);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console((Server) caller, parameters);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void console(Server caller, String[] args) {
        caller.notice(Translator.translate("warp console"));
    }

    private void player(Player player, String[] args) {
        Warp target = Canary.warps().getWarp(args[1]);

        if (target != null) {
            if (target.warp(player)) {
                player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("warp success", target.getName()));
                return;
            } else {
                player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("warp not allowed", target.getName()));
                return;
            }
        }
        player.notice(Translator.translateAndFormat("warp unknown", args[1]));
    }

}
