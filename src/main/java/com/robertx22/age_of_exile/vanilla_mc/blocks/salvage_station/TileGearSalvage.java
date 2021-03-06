package com.robertx22.age_of_exile.vanilla_mc.blocks.salvage_station;

import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import com.robertx22.age_of_exile.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.age_of_exile.uncommon.interfaces.data_items.ISalvagable;
import com.robertx22.library_of_exile.packets.particles.ParticleEnum;
import com.robertx22.library_of_exile.packets.particles.ParticlePacketData;
import com.robertx22.library_of_exile.tile_bases.BaseTile;
import com.robertx22.library_of_exile.tile_bases.NonFullBlock;
import com.robertx22.library_of_exile.utils.CLOC;
import com.robertx22.library_of_exile.utils.SoundUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Arrays;
import java.util.List;

public class TileGearSalvage extends BaseTile {

    public static List<Integer> FUEL_SLOTS = Arrays.asList(0);
    public static List<Integer> INPUT_SLOTS = Arrays.asList(1, 2, 3, 4, 5);
    public static List<Integer> OUTPUT_SLOTS = Arrays.asList(6, 7, 8, 9, 10);
    public static int TOTAL_SLOTS_COUNT = FUEL_SLOTS.size() + INPUT_SLOTS.size() + OUTPUT_SLOTS.size();

    @Override
    public List<Integer> inputSlots() {
        return INPUT_SLOTS;
    }

    @Override
    public int getCookTime() {

        return COOK_TIME_FOR_COMPLETION;
    }

    @Override
    public boolean isAutomatable() {
        return true;
    }

    @Override
    public boolean isOutputSlot(int slot) {
        return OUTPUT_SLOTS.contains(slot);
    }

    public static List<ItemStack> getSmeltingResultForItem(ItemStack st) {

        ICommonDataItem data = ICommonDataItem.load(st);

        if (data != null) {
            if (data.isSalvagable(ISalvagable.SalvageContext.SALVAGE_STATION)) {
                return data.getSalvageResult(0);
            }
        } else {

            Item item = st.getItem();
            if (item instanceof ISalvagable) {
                ISalvagable sal = (ISalvagable) item;
                if (sal.isSalvagable(ISalvagable.SalvageContext.SALVAGE_STATION)) {
                    return sal.getSalvageResult(0);
                }
            }
        }

        return Arrays.asList();

    }

    private static final short COOK_TIME_FOR_COMPLETION = 200; // vanilla value is 200 = 10 seconds

    public TileGearSalvage() {
        super(ModRegistry.BLOCK_ENTITIES.GEAR_SALVAGE);
        itemStacks = new ItemStack[TOTAL_SLOTS_COUNT];
        clear();
    }

    /**
     * Returns the amount of cook time completed on the currently cooking item.
     *
     * @return fraction remaining, between 0 - 1
     */
    public double fractionOfCookTimeComplete() {
        double fraction = cookTime / (double) getCookTime();
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    @Override
    public int ticksRequired() {
        return getCookTime();
    }

    @Override
    public void finishCooking() {
        this.smeltItem();

        SoundUtils.playSound(world, pos, SoundEvents.BLOCK_ANVIL_USE, 0.3F, 1);

        ParticleEnum.sendToClients(
            pos.up(), world, new ParticlePacketData(pos.up(), ParticleEnum.AOE).radius(0.5F)
                .type(ParticleTypes.DUST)
                .amount(15));

        ParticleEnum.sendToClients(
            pos.up(), world, new ParticlePacketData(pos.up(), ParticleEnum.AOE).radius(0.5F)
                .type(ParticleTypes.FLAME)
                .motion(new Vec3d(0, 0, 0))
                .amount(15));

    }

    @Override
    public boolean isCooking() {
        return canSmelt();
    }

    @Override
    public int tickRate() {
        return 10;
    }

    @Override
    public void doActionEveryTime() {

    }

    private boolean canSmelt() {
        return smeltItem(false);
    }

    boolean outputsAreEmpty() {
        for (int slot : OUTPUT_SLOTS) {
            if (!itemStacks[slot].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Smelt an input item into an output slot, if possible
     */
    private void smeltItem() {
        smeltItem(true);
    }

    private boolean smeltItem(boolean performSmelt) {

        try {
            List<ItemStack> results;

            for (int inputSlot : INPUT_SLOTS) {
                if (!itemStacks[inputSlot].isEmpty()) {
                    results = getSmeltingResultForItem(itemStacks[inputSlot]);

                    if (!results.isEmpty()) {

                        if (!performSmelt) {
                            return true;
                        }

                        itemStacks[inputSlot] = ItemStack.EMPTY;

                        if (outputsAreEmpty()) {
                            for (int slot : OUTPUT_SLOTS) {
                                if (!results.isEmpty()) {
                                    itemStacks[slot] = results.get(0);
                                    results.remove(0);
                                }
                            }
                        } else {

                            Vec3d itempos = new Vec3d(pos.getX(), pos.getY(), pos.getZ());

                            BlockState block = world.getBlockState(pos);

                            Direction dir = block.get(NonFullBlock.direction);

                            itempos = itempos.add(dir.getVector()
                                .getX(), 0, dir.getVector()
                                .getZ());

                            for (ItemStack x : results) {
                                ItemEntity itemEntity = new ItemEntity(
                                    this.world, itempos.getX(), itempos.getY(), itempos.getZ(), x);
                                itemEntity.setToDefaultPickupDelay();
                                this.world.spawnEntity(itemEntity);
                            }
                        }
                        return true;

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean isItemValidInput(ItemStack stack) {
        return this.getSmeltingResultForItem(stack)
            .stream()
            .anyMatch(x -> !x.isEmpty());

    }

    @Override
    public MutableText getDisplayName() {
        return CLOC.blank("block.mmorpg.salvage_station");
    }

    @Override
    public ScreenHandler createMenu(int num, PlayerInventory inventory, PlayerEntity player) {
        return new ContainerGearSalvage(num, inventory, this, this.getPos());
    }
}