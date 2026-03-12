package net.raccoon.will.winestead.core.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.raccoon.will.winestead.Winestead;

@EventBusSubscriber(modid = Winestead.MODID)
public class CommonEvents {

    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event) {

    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {

    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {

    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        }
    }