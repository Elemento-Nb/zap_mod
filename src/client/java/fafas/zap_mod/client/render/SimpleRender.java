package fafas.zap_mod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import fafas.zap_mod.ZapMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleRender extends RenderLayer<AvatarRenderState, PlayerModel>{

    private static final Identifier TRECO = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "textures/particle/aura_1.png");
    private static final Logger log = LoggerFactory.getLogger(SimpleRender.class);

    public SimpleRender(RenderLayerParent<?, ?> renderer) {
        super((RenderLayerParent<AvatarRenderState, PlayerModel>) renderer);
    }
    @Override
    public void submit(PoseStack matrices, SubmitNodeCollector collector, int lightCoords, AvatarRenderState state, float yRot, float xRot) {
        if (collector == null) return;

        Minecraft client = Minecraft.getInstance();
        if (client.level == null) return;

        matrices.pushPose();


        int framesTotais = 4;
        int frameTime = 3;

        long gameTime = client.level.getGameTime();
        int frameAtual = (int) ((gameTime/frameTime)% framesTotais);

        float alturaDoFrame = 1.0f / framesTotais; // seciona a imagem e mostra frame a frame
        float minV = frameAtual * alturaDoFrame;
        float maxV = minV + alturaDoFrame;

        RenderType renderType = RenderTypes.entityTranslucent(TRECO);

        collector.submitCustomGeometry(matrices, renderType, (pose, consumer) -> {

            consumer.addVertex(pose, (float) (-1.3), (float) (0.0 - 2.0), (float) (0.0))
                    .setColor(255, 255, 255, 155)
                    .setUv(0.0f, minV)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(15728880)
                    .setNormal(0.0f, 0.0f, 1.0f);

            consumer.addVertex(pose, (float) (-1.3), (float) (1.5), (float) (0.0))
                    .setColor(255, 255, 255, 155)
                    .setUv(0.0f, maxV)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(15728880)
                    .setNormal(0.0f, 0.0f, 1.0f);

            consumer.addVertex(pose, (float) (1.3), (float) (1.5), (float) (0.0))
                    .setColor(255, 255, 255, 155)
                    .setUv(1.0f, maxV)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(15728880)
                    .setNormal(0.0f, 0.0f, 1.0f);

            consumer.addVertex(pose, (float) (1.3), (float) (0.0 - 2.0), (float) (0.0))
                    .setColor(255, 255, 255, 155)
                    .setUv(1.0f, minV)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(15728880)
                    .setNormal(0.0f, 0.0f, 1.0f);


        }

  );
        matrices.popPose();


    }


}
