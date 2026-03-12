package net.raccoon.will.winestead.common.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record WineItemData(int ticks_aged) {

    public static final Codec<WineItemData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("ticks_aged").forGetter(WineItemData::ticks_aged)
            ).apply(instance, WineItemData::new));

    public static final StreamCodec<ByteBuf, WineItemData> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);
}