//package com.ricky.terrariamod.item.weapon.gun;
//
//import com.google.common.collect.Lists;
//import com.ricky.terrariamod.entity.projectile.ammo.musket_ball.MusketBallEntity;
//import com.ricky.terrariamod.item.ModItems;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.nbt.ListTag;
//import net.minecraft.network.chat.Component;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.stats.Stats;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResultHolder;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.entity.projectile.AbstractArrow;
//import net.minecraft.world.entity.projectile.Projectile;
//import net.minecraft.world.item.*;
//import net.minecraft.world.item.enchantment.EnchantmentHelper;
//import net.minecraft.world.item.enchantment.Enchantments;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.phys.Vec3;
//
//import org.joml.Quaternionf;
//import org.joml.Vector3f;
//
//import javax.annotation.Nullable;
//import java.util.List;
//import java.util.function.Predicate;
//
//import static net.minecraft.world.item.ProjectileWeaponItem.ARROW_ONLY;
//
//public class ShotgunItem extends RangedWeaponItem implements Vanishable {
//
//    private boolean charged = false;
//    private boolean loaded = false;
//
//    public ShotgunItem(Item.Properties properties) {
//        super(properties);
//    }
//
//    public Predicate<ItemStack> getHeldProjectiles() {
//        return ARROW_ONLY;
//    }
//
//    public Predicate<ItemStack> getAllSupportedProjectiles() {
//        return ARROW_ONLY;
//    }
//
//    @Override
//    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
//        ItemStack stack = player.getItemInHand(hand);
//        player.startUsingItem(hand);
//        return InteractionResultHolder.fail(stack);
//    }
//
//    public void attack(Level world, Player player, InteractionHand hand) {
//        ItemStack stack = player.getItemInHand(hand);
//        if (isCharged(stack)) {
//            shootAll(world, player, hand, stack, getSpeed(stack), 1.0F);
//            setCharged(stack, false);
//        }
//    }
//
//    public void reload(Player player, InteractionHand hand) {
//        ItemStack stack = player.getItemInHand(hand);
//        Level world = player.level();
//
//        if (loadProjectiles(player, stack)) {
//            setCharged(stack, true);
//            world.playSound(null, player.getX(), player.getY(), player.getZ(),
//                    SoundEvents.ARMOR_EQUIP_NETHERITE, SoundSource.PLAYERS, 1.0F, 1.0F);
//        } else {
//            player.displayClientMessage(Component.literal("No ammo to reload!"), true);
//        }
//    }
//
//    private static boolean loadProjectiles(LivingEntity shooter, ItemStack gun) {
//        int count = 9;
//        boolean isCreative = shooter instanceof Player player && player.getAbilities().instabuild;
//        ItemStack ammo = shooter instanceof Player player ? findMusketBall(player) : ItemStack.EMPTY;
//
//        if (ammo == null) ammo = ItemStack.EMPTY;
//        ItemStack copy = ammo.copy();
//
//        for (int i = 0; i < count; i++) {
//            if (i > 0) ammo = copy.copy();
//            if (ammo.isEmpty() && isCreative) {
//                ammo = new ItemStack(ModItems.MUSKET_BALL.get());
//                copy = ammo.copy();
//            }
//
//            if (!loadProjectile(shooter, gun, ammo, i > 0, isCreative)) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    private static boolean loadProjectile(LivingEntity shooter, ItemStack weapon, ItemStack ammo, boolean simulated, boolean creative) {
//        if (ammo.isEmpty()) return false;
//
//        boolean copy = creative && ammo.getItem() instanceof ArrowItem;
//        ItemStack toStore;
//
//        if (!copy && !creative && !simulated) {
//            toStore = ammo.split(1);
//            if (ammo.isEmpty() && shooter instanceof Player player) {
//                player.getInventory().removeItem(ammo);
//            }
//        } else {
//            toStore = ammo.copy();
//        }
//
//        putProjectile(weapon, toStore);
//        return true;
//    }
//
//    public static boolean isCharged(ItemStack stack) {
//        CompoundTag tag = stack.getTag();
//        return tag != null && tag.getBoolean("Charged");
//    }
//
//    public static void setCharged(ItemStack stack, boolean charged) {
//        CompoundTag tag = stack.getOrCreateTag();
//        tag.putBoolean("Charged", charged);
//    }
//
//    private static void putProjectile(ItemStack weapon, ItemStack projectile) {
//        CompoundTag tag = weapon.getOrCreateTag();
//        ListTag list = tag.contains("ChargedProjectiles", 9) ? tag.getList("ChargedProjectiles", 10) : new ListTag();
//
//        CompoundTag projectileTag = new CompoundTag();
//        projectile.save(projectileTag);
//        list.add(projectileTag);
//        tag.put("ChargedProjectiles", list);
//    }
//
//    private static List<ItemStack> getProjectiles(ItemStack weapon) {
//        List<ItemStack> result = Lists.newArrayList();
//        CompoundTag tag = weapon.getTag();
//        if (tag != null && tag.contains("ChargedProjectiles", 9)) {
//            ListTag list = tag.getList("ChargedProjectiles", 10);
//            for (int i = 0; i < list.size(); i++) {
//                result.add(ItemStack.of(list.getCompound(i)));
//            }
//        }
//        return result;
//    }
//
//    private static void clearProjectiles(ItemStack weapon) {
//        CompoundTag tag = weapon.getOrCreateTag();
//        tag.put("ChargedProjectiles", new ListTag());
//    }
//
//    private static boolean hasProjectile(ItemStack weapon, Item item) {
//        return getProjectiles(weapon).stream().anyMatch(s -> s.is(item));
//    }
//
//    private static float getSpeed(ItemStack stack) {
//        return hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
//    }
//
//    private static Projectile createMusketBall(Level world, LivingEntity shooter) {
//        MusketBallEntity entity = new MusketBallEntity(world, shooter);
//        entity.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, 3.15F, 1.0F);
//        entity.setCritArrow(false);
//        entity.setPierceLevel((byte) 0);
//        entity.pickup = AbstractArrow.Pickup.DISALLOWED;
//        return entity;
//    }
//
//    private static void shoot(Level world, LivingEntity shooter, InteractionHand hand, ItemStack gun, float soundPitch, float speed, float divergence, float hSpread, float vSpread) {
//        if (!world.isClientSide) {
//            Projectile proj = createMusketBall(world, shooter);
//
//            Vec3 dir = shooter.getLookAngle();
//            Vector3f vec = new Vector3f((float) dir.x, (float) dir.y, (float) dir.z);
//            vec.rotate(new Quaternionf().rotateY((float) Math.toRadians(hSpread)));
//            vec.rotate(new Quaternionf().rotateX((float) Math.toRadians(vSpread)));
//
//            proj.shoot(vec.x(), vec.y(), vec.z(), speed, divergence);
//            gun.hurtAndBreak(1, shooter, (e) -> e.broadcastBreakEvent(hand));
//            world.addFreshEntity(proj);
//            world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.6F, soundPitch);
//        }
//    }
//
//    public static void shootAll(Level world, LivingEntity shooter, InteractionHand hand, ItemStack weapon, float speed, float divergence) {
//        List<ItemStack> projectiles = getProjectiles(weapon);
//        float[] pitches = getSoundPitches(world.random);
//        int hShots = 3, vShots = 3, idx = 0;
//
//        for (int v = 0; v < vShots; v++) {
//            for (int h = 0; h < hShots; h++) {
//                if (idx >= projectiles.size()) break;
//                ItemStack ammo = projectiles.get(idx++);
//                if (!ammo.isEmpty()) {
//                    float hSpread = (h - 1) * 3.0F;
//                    float vSpread = (v - 1) * 3.0F;
//                    shoot(world, shooter, hand, weapon, pitches[idx % pitches.length], speed, divergence, hSpread, vSpread);
//                }
//            }
//        }
//
//        postShoot(world, shooter, weapon);
//    }
//
//    private static float[] getSoundPitches(RandomSource random) {
//        boolean bl = random.nextBoolean();
//        return new float[]{
//                1.0F,
//                getSoundPitch(bl, random),
//                getSoundPitch(!bl, random)
//        };
//    }
//
//    private static float getSoundPitch(boolean flag, RandomSource random) {
//        float f = flag ? 0.63F : 0.43F;
//        return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + f;
//    }
//
//    private static void postShoot(Level world, LivingEntity shooter, ItemStack stack) {
//        if (shooter instanceof ServerPlayer player) {
//            player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
//        }
//        clearProjectiles(stack);
//    }
//
//    @Override
//    public void onUseTick(Level world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
//        if (!world.isClientSide) {
//            int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
//            float f = (float)(stack.getUseDuration() - remainingUseTicks) / getPullTime(stack);
//
//            if (f < 0.2F) {
//                this.charged = false;
//                this.loaded = false;
//            }
//
//            if (f >= 0.2F && !this.charged) {
//                this.charged = true;
//                world.playSound(null, user.getX(), user.getY(), user.getZ(), getQuickChargeSound(i), SoundSource.PLAYERS, 0.5F, 1.0F);
//            }
//
//            if (f >= 0.5F && !this.loaded) {
//                this.loaded = true;
//                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.CROSSBOW_LOADING_MIDDLE, SoundSource.PLAYERS, 0.5F, 1.0F);
//            }
//        }
//    }
//
//    private static int getPullTime(ItemStack stack) {
//        int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
//        return 25 - (level * 5);
//    }
//
//    @Override
//    public int getUseDuration(ItemStack stack) {
//        return 7200;
//    }
//
//    @Override
//    public UseAnim getUseAnimation(ItemStack stack) {
//        return UseAnim.BOW;
//    }
//
//    private SoundEvent getQuickChargeSound(int stage) {
//        return switch (stage) {
//            case 1 -> SoundEvents.CROSSBOW_QUICK_CHARGE_1;
//            case 2 -> SoundEvents.CROSSBOW_QUICK_CHARGE_2;
//            case 3 -> SoundEvents.CROSSBOW_QUICK_CHARGE_3;
//            default -> SoundEvents.CROSSBOW_LOADING_START;
//        };
//    }
//
//    @Override
//    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
//        tooltip.add(Component.translatable("tooltip.terrariamod.gun.tooltip"));
//        super.appendHoverText(stack, world, tooltip, flag);
//    }
//
//    private static ItemStack findMusketBall(Player player) {
//        for (ItemStack stack : player.getInventory().items) {
//            if (stack.is(ModItems.MUSKET_BALL.get())) {
//                return stack;
//            }
//        }
//        return ItemStack.EMPTY;
//    }
//}
