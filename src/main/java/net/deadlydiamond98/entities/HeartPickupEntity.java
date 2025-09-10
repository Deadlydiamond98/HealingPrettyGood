package net.deadlydiamond98.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.ItemPickupAnimationS2CPacket;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class HeartPickupEntity extends Entity {

    private static final TrackedData<Integer> HEAL_AMOUNT = DataTracker.registerData(HeartPickupEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private PlayerEntity target;
    private int health = 5;
    private int heartage;

    public HeartPickupEntity(World world, double x, double y, double z) {
        this(HealPGoodEntities.HEART_PICKUP_ENTITY, world);
        this.setPosition(x, y, z);
        this.setYaw((float)(this.random.nextDouble() * 360.0));
        this.setVelocity((this.random.nextDouble() * (double)0.2f - (double)0.1f) * 2.0, this.random.nextDouble() * 0.2 * 2.0, (this.random.nextDouble() * (double)0.2f - (double)0.1f) * 2.0);
    }

    public HeartPickupEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Entity.MoveEffect getMoveEffect() {
        return Entity.MoveEffect.NONE;
    }

    public int getHealAmount() {
        return Math.min(this.dataTracker.get(HEAL_AMOUNT), 9);
    }

    @Override
    public void tick() {
        Vec3d vec3d;
        double d;
        super.tick();
        this.prevX = this.getX();
        this.prevY = this.getY();
        this.prevZ = this.getZ();
        if (this.isSubmergedIn(FluidTags.WATER)) {
            this.applyWaterMovement();
        } else if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.03, 0.0));
        }
        if (this.getWorld().getFluidState(this.getBlockPos()).isIn(FluidTags.LAVA)) {
            this.setVelocity((this.random.nextFloat() - this.random.nextFloat()) * 0.2f, 0.2f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f);
        }
        if (!this.getWorld().isSpaceEmpty(this.getBoundingBox())) {
            this.pushOutOfBlocks(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0, this.getZ());
        }
        if (this.age % 20 == 5) {
            this.expensiveUpdate();
        }
        if (this.target != null && (this.target.isSpectator() || this.target.isDead())) {
            this.target = null;
        }
        if (this.target != null && (d = (vec3d = new Vec3d(this.target.getX() - this.getX(), this.target.getY() + (double)this.target.getStandingEyeHeight() / 2.0 - this.getY(), this.target.getZ() - this.getZ())).lengthSquared()) < 64.0) {
            double e = 1.0 - Math.sqrt(d) / 8.0;
            this.setVelocity(this.getVelocity().add(vec3d.normalize().multiply(e * e * 0.1)));
        }
        this.move(MovementType.SELF, this.getVelocity());
        float f = 0.98f;
        if (this.isOnGround()) {
            f = this.getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getSlipperiness() * 0.98f;
        }
        this.setVelocity(this.getVelocity().multiply(f, 0.98, f));
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(1.0, -0.9, 1.0));
        }
        ++this.heartage;
        if (this.heartage >= 6000) {
            this.discard();
        }
    }

    private void expensiveUpdate() {
        if (this.target == null || this.target.squaredDistanceTo(this) > 64.0) {
            this.target = this.getWorld().getClosestPlayer(this, 8.0);
        }
        if (this.getWorld() instanceof ServerWorld) {
            List<HeartPickupEntity> list = this.getWorld().getEntitiesByType(TypeFilter.instanceOf(HeartPickupEntity.class), this.getBoundingBox().expand(0.5), this::isMergeable);
            for (HeartPickupEntity heartPickupEntity : list) {
                this.merge(heartPickupEntity);
            }
        }
    }

    public static void spawn(ServerWorld world, Vec3d pos, int amount) {
        while (amount > 0) {
            amount -= 1;
            world.spawnEntity(new HeartPickupEntity(world, pos.getX(), pos.getY(), pos.getZ()));
        }
    }

    private boolean isMergeable(HeartPickupEntity other) {
        return other != this;
    }

    private void merge(HeartPickupEntity other) {
        this.dataTracker.set(HEAL_AMOUNT, this.getHealAmount() + other.getHealAmount());
        other.discard();
    }

    private void applyWaterMovement() {
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.x * (double)0.99f, Math.min(vec3d.y + (double)5.0E-4f, (double)0.06f), vec3d.z * (double)0.99f);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        if (this.getWorld().isClient) {
            return true;
        }
        this.scheduleVelocityUpdate();
        this.health = (int)((float)this.health - amount);
        if (this.health <= 0) {
            this.discard();
        }
        return true;
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(HEAL_AMOUNT, 1);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.health = nbt.getShort("Health");
        this.heartage = nbt.getShort("Age");
        this.dataTracker.set(HEAL_AMOUNT, nbt.getInt("Value"));
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putShort("Health", (short)this.health);
        nbt.putShort("Age", (short)this.heartage);
        nbt.putInt("Value", this.dataTracker.get(HEAL_AMOUNT));
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (this.getWorld().isClient) {
            return;
        }

        if (player.experiencePickUpDelay == 0) {
            player.experiencePickUpDelay = 2;
            mimicPickup(player);
            player.heal(getHealAmount() * 2);
            this.discard();
        }
    }

    private void mimicPickup(PlayerEntity player) {
        ((ServerWorld) this.getWorld()).getChunkManager().sendToNearbyPlayers(this, new ItemPickupAnimationS2CPacket(this.getId(), player.getId(), 1));
    }

    @Override
    public boolean isAttackable() {
        return false;
    }
}
