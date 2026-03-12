package net.raccoon.will.winestead.common.misc;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public enum Ripeness {
    UNRIPE,
    RIPE,
    OVERRIPE;

    public static final Codec<Ripeness> CODEC =
            Codec.STRING.xmap(Ripeness::valueOf, Ripeness::name);

    public static final StreamCodec<ByteBuf, Ripeness> STREAM_CODEC =
            ByteBufCodecs.STRING_UTF8.map(Ripeness::valueOf, Ripeness::name);
}
