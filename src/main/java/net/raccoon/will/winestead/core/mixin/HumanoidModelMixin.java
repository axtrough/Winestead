package net.raccoon.will.winestead.core.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin<T extends HumanoidRenderState> {
//
//    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V", at = @At("RETURN"))
//        public void setupAnim(T state, CallbackInfo ci) {
//        HumanoidModel<?> model = (HumanoidModel<?>) (Object) this;
//        Vector3f crouchOffset = new Vector3f(0, -1, 0);
//        AvatarRenderState avatar = new AvatarRenderState();
//
//        model.head.xScale = 0.5f;
//        model.head.zScale = 0.5f;
//        model.head.yScale = 0.5f;
//        model.head.setInitialPose(PartPose.offset(0.0f, 11, -0.5f));
//        model.body.skipDraw = true;
//        model.body.setPos(0, 12, 0);
//        avatar.showJacket = false;
//        avatar.eyeHeight = -6;
//
//        model.leftArm.setPos(5, 12, 0);
//        model.rightArm.setPos(-5, 12, 0);
//
//
//        if (state.isCrouching) {
//            model.head.offsetPos(crouchOffset);
//
//        }
//
//        model.leftArm.setPos(6f, 1, 0);
//        model.rightArm.setPos(-6f, 1, 0);
//        model.body.setPos(0, 0, -1f);
//        model.body.zScale = 2.0f;
//        model.body.xScale = 1.5f;
//    }
}
