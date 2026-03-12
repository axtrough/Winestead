package net.raccoon.will.winestead.core.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.raccoon.will.winestead.core.network.packets.ItemBarrelPacket;

public class NetworkHandler {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(
                ItemBarrelPacket.TYPE,
                ItemBarrelPacket.STREAM_CODEC,
                ItemBarrelPacket::handle
        );
    }
}
