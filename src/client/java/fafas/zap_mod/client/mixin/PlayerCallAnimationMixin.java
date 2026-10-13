package fafas.zap_mod.client.mixin;


import net.fabricmc.fabric.mixin.client.rendering.ModelPartAccessor;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.model.HumanoidModel;

@Mixin(HumanoidModel.class)
public abstract class PlayerCallAnimationMixin<T extends HumanoidRenderState> extends EntityModel<T> implements ArmedModel<T>, HeadedModel {

    @Shadow public ModelPart rightArm;

    protected PlayerCallAnimationMixin(ModelPart root) {
        super(root);
    }


    @Inject(method = "setupAnim", at = @At("TAIL"))
    private void modifyPlayerAngles(T state, CallbackInfo info){
        boolean isAnimating = false;
        if (isAnimating){
            this.rightArm.xRot = -1.5f;
        }
    }
}
