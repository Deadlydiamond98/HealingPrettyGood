package net.deadlydiamond98.mixin;

import net.deadlydiamond98.misc.HealPGoodAdvancements;
import net.deadlydiamond98.util.IPlayerHealth;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements IPlayerHealth {

    @Unique
    private int tempHealth, permHealth;

    @Inject(method = "tick", at = @At("HEAD"))
    public void healpgood$tick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (tempHealth == 20 && permHealth == 20 && !player.getWorld().isClient()) {
            HealPGoodAdvancements.MAX_HEALTH.trigger((ServerPlayerEntity) player);
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void healpgood$writeCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
        nbt.putInt("tempHealth", tempHealth);
        nbt.putInt("permHealth", permHealth);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void healpgood$readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
        this.tempHealth = nbt.getInt("tempHealth");
        this.permHealth = nbt.getInt("permHealth");
    }

    @Override
    public void healpgood$setTempHealth(int value) {
        this.tempHealth = value;
    }

    @Override
    public int healpgood$getTempHealth() {
        return this.tempHealth;
    }

    @Override
    public void healpgood$setPermHealth(int value) {
        this.permHealth = value;
    }

    @Override
    public int healpgood$getPermHealth() {
        return this.permHealth;
    }
}
