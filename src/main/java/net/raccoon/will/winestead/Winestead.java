package net.raccoon.will.winestead;


import com.mojang.logging.LogUtils;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.raccoon.will.winestead.core.network.NetworkHandler;
import net.raccoon.will.winestead.registry.*;
import org.slf4j.Logger;

@Mod(Winestead.MODID)
public class Winestead {
    public static final String MODID = "winestead";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static Identifier resLoc(String name) {
        return Identifier.fromNamespaceAndPath(Winestead.MODID, name);
    }

    public Winestead(IEventBus modEventBus, ModContainer modContainer) {
        WSBlockEntities.register(modEventBus);
        WSCreativeTab.register(modEventBus);
        WSComponents.register(modEventBus);
        WSBlocks.register(modEventBus);
        WSSounds.register(modEventBus);
        WSItems.register(modEventBus);
        WSMenus.register(modEventBus);

        modEventBus.addListener(NetworkHandler::registerPayloads);
    }
}
