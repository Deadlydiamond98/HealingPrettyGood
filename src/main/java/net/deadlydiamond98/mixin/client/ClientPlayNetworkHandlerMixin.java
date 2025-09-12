package net.deadlydiamond98.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.deadlydiamond98.client.renderer.HeartPickupEntityRenderer;
import net.deadlydiamond98.entities.HeartPickupEntity;
import net.deadlydiamond98.koalalib.common.misc.ModSharedSounds;
import net.deadlydiamond98.misc.HealPGoodConfig;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow @Final private Random random;

    @ModifyArgs(method = "onItemPickupAnimation", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V", ordinal = 1))
    private void healpgood$onItemPickupAnimation(Args args, @Local() Entity entity) {
        if (entity instanceof HeartPickupEntity) {
            if (HealPGoodConfig.Main.seasonalHearts.canChange() && HeartPickupEntityRenderer.isApril) {
                args.set(3, ModSharedSounds.BAGEL);
            } else {
                args.set(3, HealPGoodConfig.Main.seasonalHearts.soundEvent);
            }
            args.set(5, 0.35f);
            args.set(6, this.random.nextFloat() * 0.6f + 0.75f);
        }
    }
}
