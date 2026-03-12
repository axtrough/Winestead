package net.raccoon.will.winestead.core.mixin;

import net.minecraft.client.model.object.equipment.ElytraModel;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ElytraModel.class)
public class ElytraModelMixin {
//    @Shadow
//    private final ModelPart rightWing;
//
//    @Shadow
//    private final ModelPart leftWing;
//
//    public ElytraModelMixin(ModelPart rightWing, ModelPart leftWing) {
//        this.rightWing = rightWing;
//        this.leftWing = leftWing;
//    }
//
//    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V", at = @At("RETURN"))
//    public void setupAnim(HumanoidRenderState state, CallbackInfo ci) {
//        this.leftWing.setPos(0, 24, 0);
//        this.leftWing.y = state.isCrouching ? 3.0F : 0.0F;
//        this.rightWing.y = this.leftWing.y;
//    }

}
