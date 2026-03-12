package net.raccoon.will.winestead.common.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record GrapesData(String status) {

    public static final Codec<GrapesData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("status").forGetter(GrapesData::status)
            ).apply(instance, GrapesData::new));

    public static final StreamCodec<ByteBuf, GrapesData> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);

}
