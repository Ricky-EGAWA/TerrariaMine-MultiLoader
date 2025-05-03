package com.ricky.terrariamod.item.projectile;


import com.ricky.terrariamod.entity.projectile.ammo.musket_ball.MusketBallEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MusketBallItem extends Item {
    public MusketBallItem(Properties pProperties) {
        super(pProperties);
    }

    public Projectile createMusketBall(Level level, ItemStack stack, LivingEntity shooter) {
        MusketBallEntity entity = new MusketBallEntity(level, shooter);
        // 必要に応じて初期設定を追加（例：ダメージ量や色など）
        return entity;
    }
}
