package com.lemonbunzz.apikinesismod.commands;

import com.lemonbunzz.apikinesismod.capabilities.Apikinetic.ApikineticCapability;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ApikineticDebug {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> command = dispatcher.register(
                Commands.literal("apikinetic")
                .requires((p) -> p.hasPermission(0))

                 //Command: /apikinetic debug
                 .then(Commands.literal("debug")
                        .executes(ApikineticDebug::debug))

                //Command: /apikinetic set_energy <value>
                .then(Commands.literal("set_energy")
                        .then(Commands.argument("value", IntegerArgumentType.integer()))
                            .executes(ApikineticDebug::setEnergyPoint))

                //Command: /apikinetic toggle
                .then(Commands.literal("toggle")
                         .executes(ApikineticDebug::toggle))
        );
    }

    private static int debug(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = context.getSource();
        Player player = source.getPlayerOrException();
        player.getCapability(ApikineticCapability.APIKINETIC).ifPresent(data -> {
            source.sendSuccess(
                    () -> Component.literal("[DEBUG] isApikinetic=" + data.isApikinetic()
                            + "  energyPower=" + data.getEnergyPoint()),
                    false
            );
        });
        return Command.SINGLE_SUCCESS;
    }

    private static int setEnergyPoint(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = context.getSource();
        Player player = source.getPlayerOrException();
        int newValue = IntegerArgumentType.getInteger(context, "value");

        player.getCapability(ApikineticCapability.APIKINETIC).ifPresent(data -> {
            data.setEnergyPoint(newValue);
            source.sendSuccess(
                    () -> Component.literal("Set Energy Point to" + data.getEnergyPoint()),
                    false
            );
        });
        return Command.SINGLE_SUCCESS;
    }

    private static int toggle(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = context.getSource();
        Player player = source.getPlayerOrException();
        player.getCapability(ApikineticCapability.APIKINETIC).ifPresent(data -> {
            data.setApikinetic(!data.isApikinetic());
            source.sendSuccess(
                    () -> Component.literal("[DEBUG] isApikinetic=" + data.isApikinetic()
                            + "  energyPower=" + data.getEnergyPoint()),
                    false
            );
        });
        return Command.SINGLE_SUCCESS;
    }
}
