package net.raccoon.will.winestead.core.mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Entity.class)
public abstract class EntityMixin {
//
//    @Shadow
//    private float xRot;
//
//    @Shadow
//    private float yRot;
//
//
//    /**
//     * @author
//     * @reason
//     */
//    @Overwrite
//    public void setXRot(float xRot) {
//        if (!Float.isFinite(xRot)) {
//            Util.logAndPauseIfInIde("Invalid entity rotation: " + xRot + ", discarding.");
//        }
//        if ((Entity) (Object) this instanceof Player player) {
//            this.xRot = xRot % 360F;
//        } else {
//            this.xRot = Math.clamp(xRot % 360.0F, -90.0F, 90.0F);
//        }
//    }
//    @Shadow
//    public float getXRot() {
//        return this.xRot;
//    }
//
//    @Shadow
//    public float getYRot() {
//        return this.yRot;
//    }
//
//    @Shadow
//    public float xRotO;
//
//    @Shadow
//    public float yRotO;
//
//    @Inject(method = "turn", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(FFF)F", ordinal = 0), cancellable = true)
//    private void infinitePitchTurn(double xo, double yo, CallbackInfo ci) {
//        float xDelta = (float) yo * 0.15F;
//        float yDelta = (float) xo * 0.15F;
//
//        this.setXRot(this.getXRot() + xDelta);
//        this.setYRot(this.getYRot() + yDelta);
//
//        this.xRotO += xDelta;
//        this.yRotO += yDelta;
//
//        ci.cancel();
//    }
//
//    @Shadow
//    public void setYRot(float yRot) {
//        if (!Float.isFinite(yRot)) {
//            Util.logAndPauseIfInIde("Invalid entity rotation: " + yRot + ", discarding.");
//        } else {
//            this.yRot = yRot;
//        }
//
//    }
}


