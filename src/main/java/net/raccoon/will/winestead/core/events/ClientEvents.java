package net.raccoon.will.winestead.core.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.raccoon.will.winestead.Winestead;
import net.raccoon.will.winestead.client.screens.WineBarrelScreen;
import net.raccoon.will.winestead.registry.WSMenus;

@EventBusSubscriber(modid = Winestead.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(WSMenus.WINE_BARREL.get(), WineBarrelScreen::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void registerBE(EntityRenderersEvent.RegisterRenderers event) {

    }

    @SubscribeEvent
    public static void registerParticleFactory(RegisterParticleProvidersEvent event) {

    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {

    }
}