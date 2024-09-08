package cn.ksmcbrigade.dr.mixin;

import cn.ksmcbrigade.dr.DampReborn;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Creeper.class)
public abstract class CreeperMixin extends LivingEntity {
    @Shadow
    private int maxSwell;

    @Shadow
    private int swell;

    protected CreeperMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        if (!DampReborn.creeperDampness.get()) return;
        if (!this.isAlive()) return;
        if (getPersistentData().getBoolean("no_dampness")) return;
        if (!isInWaterOrRain()) {
            if (maxSwell <= 30 || (random.nextInt(2) & 1) == 0) return;
            if (maxSwell - swell > 30) maxSwell--;
            return;
        }
        if (maxSwell < DampReborn.creeperMaxSwell.get()) maxSwell++;
        if (swell % 120 == 119) swell = 0;
    }
}
