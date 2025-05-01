package com.ricky.terrariamod.entity.monster.flying_type;

import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class TrackPlayerGoal extends Goal {
        private final FlyingMob crimera;
        private Player target;

        public TrackPlayerGoal(FlyingMob crimera) {
            this.crimera = crimera;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            this.target = this.crimera.level().getNearestPlayer(this.crimera, 15.0);
            return this.target != null && !this.target.isCreative() && !this.target.isSpectator();
        }

        @Override
        public void start() {
            this.crimera.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.5);
        }

        @Override
        public void tick() {
            if (this.target != null) {
                this.crimera.getLookControl().setLookAt(target, 30.0F, 30.0F);
                double distance = this.crimera.distanceToSqr(target);
                if (distance < 4.0) {
                    this.crimera.doHurtTarget(target);
                } else {
                    this.crimera.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.5);
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            return this.target != null && this.target.isAlive()
                    && !this.target.isCreative() && !this.target.isSpectator()
                    && this.crimera.distanceToSqr(target) < 225.0;
        }
    }