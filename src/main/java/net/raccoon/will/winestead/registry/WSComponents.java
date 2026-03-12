package net.raccoon.will.winestead.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.winestead.Winestead;
import net.raccoon.will.winestead.common.data.GrapesData;
import net.raccoon.will.winestead.common.data.WineItemData;
import net.raccoon.will.winestead.common.misc.Ripeness;

import java.util.function.Supplier;

public class WSComponents {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Winestead.MODID);

    public static final Supplier<DataComponentType<WineItemData>> WINE_ITEM_DATA = register("wine_item_data"
            ,WineItemData.CODEC, WineItemData.STREAM_CODEC);

    public static final Supplier<DataComponentType<GrapesData>> GRAPES_DATA = register("grapes_data"
            ,GrapesData.CODEC, GrapesData.STREAM_CODEC);

    public static final Supplier<DataComponentType<Ripeness>> RIPENESS = register("ripeness",
            Ripeness.CODEC, Ripeness.STREAM_CODEC);


    private static <T> Supplier<DataComponentType<T>> register(String name, Codec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        return COMPONENTS.register(name, () ->
                DataComponentType.<T>builder()
                        .persistent(codec)
                        .networkSynchronized(streamCodec)
                        .build()
        );
    }
    public static void register(IEventBus bus) {
        COMPONENTS.register(bus);
    }
}