package com.wayysss.crystal.mixin;

import com.wayysss.crystal.cfg;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class mcclmixin {

    @Shadow
    private int itemUseCooldown;

    @Shadow
    public int attackCooldown;

    @Shadow
    public ClientPlayerEntity player;

    @Shadow
    public ClientWorld world;
    @Shadow
    public net.minecraft.client.network.ClientPlayerInteractionManager interactionManager;

    @Shadow
    public net.minecraft.util.hit.HitResult crosshairTarget;

    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    private void atk(org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable<Boolean> c) {
        if (!cfg.on) return;
        if (this.player != null && this.world != null) {
            if (this.crosshairTarget != null && this.crosshairTarget.getType() == net.minecraft.util.hit.HitResult.Type.BLOCK) {
                net.minecraft.util.math.Vec3d cam = this.player.getCameraPosVec(1.0F);
                net.minecraft.util.math.Vec3d dir = this.player.getRotationVec(1.0F);
                net.minecraft.util.math.Vec3d end = cam.add(dir.x * 4.5, dir.y * 4.5, dir.z * 4.5);
                net.minecraft.util.math.Box bx = new net.minecraft.util.math.Box(cam, end).expand(1.0);
                for (net.minecraft.entity.Entity e : this.world.getOtherEntities(this.player, bx, e -> e.getClass().getSimpleName().contains("EndCrystalEntity"))) {
                    java.util.Optional<net.minecraft.util.math.Vec3d> hit = e.getBoundingBox().expand(0.2).raycast(cam, end);
                    if (hit.isPresent()) {
                        this.interactionManager.attackEntity(this.player, e);
                        this.player.swingHand(net.minecraft.util.Hand.MAIN_HAND);
                        c.setReturnValue(true);
                        return;
                    }
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tck(CallbackInfo c) {
        if (!cfg.on) return;
        if (this.player != null && this.world != null) {
            boolean b = false;
            Item m = this.player.getMainHandStack().getItem();
            if (m != null && m.getClass().getSimpleName().contains("EndCrystalItem")) {
                b = true;
            }
            Item o = this.player.getOffHandStack().getItem();
            if (o != null && o.getClass().getSimpleName().contains("EndCrystalItem")) {
                b = true;
            }
            if (!b) return;

            if (this.itemUseCooldown > 0) {
                int t = com.wayysss.crystal.cfg.min + (com.wayysss.crystal.cfg.max > com.wayysss.crystal.cfg.min ? (int)(Math.random() * ((com.wayysss.crystal.cfg.max - com.wayysss.crystal.cfg.min) + 1)) : 0);
                if (this.itemUseCooldown > t) {
                    this.itemUseCooldown = t;
                }
            }
            if (this.attackCooldown > 0) {
                this.attackCooldown = 0;
            }
        }
    }
}



