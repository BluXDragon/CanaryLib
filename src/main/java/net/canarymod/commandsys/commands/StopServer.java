package net.canarymod.commandsys.commands;


import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;


public class StopServer extends CanaryCommand {

    public StopServer() {
        super("*", Translator.translate("stop info"), Translator.translateAndFormat("usage", "/stop"), 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            caller.notice(Translator.translateAndFormat("stop console", caller.getName()));
            ((Server) caller).initiateShutdown();
        } else if (caller instanceof Player) {
            caller.notice(Translator.translate("stop player"));
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }
}
