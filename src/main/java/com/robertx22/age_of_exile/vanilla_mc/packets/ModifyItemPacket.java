package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.vanilla_mc.blocks.item_modify_station.TileGearModify;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModifyItemPacket extends MyPacket<ModifyItemPacket> {

    public static ModifyItemPacket EMPTY = new ModifyItemPacket();

    BlockPos pos;

    public ModifyItemPacket() {

    }

    public ModifyItemPacket(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void loadFromData(PacketByteBuf buf) {
        this.pos = buf.readBlockPos();
    }

    @Override
    public void saveToData(PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    @Override
    public void onReceived(PacketContext ctx) {

        try {
            TileGearModify modify = (TileGearModify) ctx.getPlayer().world.getBlockEntity(pos);
            modify.modifyItem();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public MyPacket<ModifyItemPacket> newInstance() {
        return new ModifyItemPacket();
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "modifyitem");
    }

}