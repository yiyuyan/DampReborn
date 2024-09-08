package cn.ksmcbrigade.dr.mixin;

import cn.ksmcbrigade.dr.DampReborn;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PrimedTnt.class)
public abstract class TntMixin extends Entity {
    @Shadow public abstract void setFuse(int p_32086_);

    @Shadow public abstract int getFuse();

    public TntMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        if (!DampReborn.tntDampness.get()) return;
        if (getPersistentData().getBoolean("no_dampness")) return;
        final int fuse = getFuse();
        if (!isInWaterOrRain()) {
            if (fuse <= 80 || (random.nextInt(2) & 1) == 0) return;
            setFuse(fuse - 1);
            return;
        }
        if (fuse < DampReborn.tntMaxFuse.get()) setFuse(fuse + 1);
    }
}
