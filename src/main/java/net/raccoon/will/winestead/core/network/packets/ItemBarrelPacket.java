package net.raccoon.will.winestead.core.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.raccoon.will.winestead.Winestead;
import net.raccoon.will.winestead.common.blocks.blockentity.WineBarrelBlockEntity;

public record ItemBarrelPacket(BlockPos pos, ItemStack stack) implements CustomPacketPayload {

    public static final Type<ItemBarrelPacket> TYPE = new Type<>(Winestead.resLoc("item_barrel_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemBarrelPacket> STREAM_CODEC = StreamCodec.composite(
                    BlockPos.STREAM_CODEC, ItemBarrelPacket::pos, ItemStack.STREAM_CODEC, ItemBarrelPacket::stack,
            ItemBarrelPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ItemBarrelPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            var player = ctx.player();
            var level = player.level();

            if (level.getBlockEntity(packet.pos()) instanceof WineBarrelBlockEntity barrel) {
                ItemStack carried = player.containerMenu.getCarried();

                if (!carried.isEmpty() && ItemStack.isSameItemSameComponents(carried, packet.stack())) {
                    ItemStack toInsert = carried.copyWithCount(1);
                    boolean accepted = barrel.insertItem(toInsert, player);

                    if (accepted) {
                        carried.shrink(1);
                        player.containerMenu.broadcastChanges();
                    }
                }
            }
        });
    }
}
