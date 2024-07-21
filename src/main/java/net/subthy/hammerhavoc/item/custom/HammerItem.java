package net.subthy.hammerhavoc.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.ForgeMod;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class HammerItem extends DiggerItem implements Vanishable {

    private final ImmutableMultimap<Attribute, AttributeModifier> defaultAttributes;
    private final static UUID ATTACK_REACH = UUID.randomUUID();

    public HammerItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pAttackDamageModifier, pAttackSpeedModifier, pTier, BlockTags.MINEABLE_WITH_PICKAXE, pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(ATTACK_REACH, "attack_reach", 0d, AttributeModifier.Operation.ADDITION));
        defaultAttributes = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        if (pEquipmentSlot != EquipmentSlot.MAINHAND) {
            return super.getDefaultAttributeModifiers(pEquipmentSlot);
        }

        Multimap<Attribute, AttributeModifier> existingAttributes = super.getDefaultAttributeModifiers(pEquipmentSlot);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(existingAttributes);
        builder.putAll(defaultAttributes);
        return builder.build();
    }
    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return super.canDisableShield(stack, shield, entity, attacker);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            BlockHitResult result = getPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);
            BlockPos pos = result.getBlockPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            if (block == Blocks.STONE) {
                world.setBlockAndUpdate(pos, Blocks.COBBLESTONE.defaultBlockState());
            } else if (block == Blocks.COBBLESTONE) {
                world.setBlockAndUpdate(pos, Blocks.GRAVEL.defaultBlockState());
            } else if (block == Blocks.GRAVEL) {
                world.setBlockAndUpdate(pos, Blocks.SAND.defaultBlockState());
            }
        }

        if (!player.isCreative()) {
            itemStack.hurtAndBreak(1, player, (p) -> {
                p.broadcastBreakEvent(hand);
            });
        }

        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if(entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1), player);
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        // Check if the enchantment is applicable to both hoe and sword
        return super.canApplyAtEnchantingTable(stack, enchantment) ||
                enchantment.category == EnchantmentCategory.WEAPON
                || isSwordEnchantment(enchantment);
    }

    public EnchantmentCategory getCategory() {
        return EnchantmentCategory.WEAPON;
    }

    // Check if the enchantment is a sword enchantment
    private boolean isSwordEnchantment(Enchantment enchantment) {
        // List of sword enchantments you want to allow
        Enchantment[] swordEnchantments = {
                Enchantments.SHARPNESS,
                Enchantments.SMITE,
                Enchantments.BANE_OF_ARTHROPODS,
                Enchantments.MOB_LOOTING,
                Enchantments.SWEEPING_EDGE,
                Enchantments.FIRE_ASPECT,
                // Add more sword enchantments here as needed
        };

        // Check if the given enchantment is in the list of sword enchantments
        for (Enchantment swordEnchantment : swordEnchantments) {
            if (enchantment.equals(swordEnchantment)) {
                return true;
            }
        }
        return false;
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initalBlockPos, ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();

        BlockHitResult traceResult = player.level().clip(new ClipContext(player.getEyePosition(1f),
                (player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f))),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        if(traceResult.getType() == HitResult.Type.MISS) {
            return positions;
        }

        if(traceResult.getDirection() == Direction.DOWN || traceResult.getDirection() == Direction.UP) {
            for(int x = -range; x <= range; x++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY(), initalBlockPos.getZ() + y));
                }
            }
        }

        if(traceResult.getDirection() == Direction.NORTH || traceResult.getDirection() == Direction.SOUTH) {
            for(int x = -range; x <= range; x++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY() + y, initalBlockPos.getZ()));
                }
            }
        }

        if(traceResult.getDirection() == Direction.EAST || traceResult.getDirection() == Direction.WEST) {
            for(int x = -range; x <= range; x++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX(), initalBlockPos.getY() + y, initalBlockPos.getZ() + x));
                }
            }
        }
        return positions;
    }
}
