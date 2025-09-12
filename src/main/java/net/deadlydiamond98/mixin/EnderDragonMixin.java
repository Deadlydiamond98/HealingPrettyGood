package net.deadlydiamond98.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.deadlydiamond98.entities.HeartPickupEntity;
import net.deadlydiamond98.misc.HealPGoodConfig;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderDragonEntity.class)
public class EnderDragonMixin {

    @Inject(method = "updatePostDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ExperienceOrbEntity;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;I)V", ordinal = 1))
    private void healpgood$updatePostDeath(CallbackInfo ci) {
        if (HealPGoodConfig.Main.dragonDrops) {
            EnderDragonEntity dragon = (EnderDragonEntity) (Object) this;
            HeartPickupEntity.spawn((ServerWorld)dragon.getWorld(), dragon.getPos(), dragon.getRandom().nextBetween(10, 15), true);
        }
    }

}
