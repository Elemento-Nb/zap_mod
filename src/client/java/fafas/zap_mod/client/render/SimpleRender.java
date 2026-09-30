package fafas.zap_mod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fafas.zap_mod.ZapMod;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleRender {

    private static final Identifier TRECO = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "textures/particle/aura_1.png");
    private static final Logger log = LoggerFactory.getLogger(SimpleRender.class);


    public static void initialize() {
        LevelRenderEvents.AFTER_TRANSLUCENT_TERRAIN.register(SimpleRender::render);
    }

    private static void render(LevelRenderContext context) {
        Minecraft client = Minecraft.getInstance();
        Vec3 camera = context.levelState().cameraRenderState.pos;
        PoseStack matrices = context.poseStack();

        Player player = client.player;

        SubmitNodeCollector collector = context.submitNodeCollector();
        if (collector == null) return;

        float delta = client.getDeltaTracker().getGameTimeDeltaPartialTick(false);
        double playerX = Mth.lerp(delta, player.xo, player.getX());
        double playerY = Mth.lerp(delta, player.yo, player.getY());
        double playerZ = Mth.lerp(delta, player.zo, player.getZ());

        float relx = (float) (playerX - camera.x);
        float rely = (float) (playerY- camera.y);
        float relz = (float) (playerZ - camera.z);

        float yaw = Mth.lerp(delta, player.yRotO, player.getYRot());

        matrices.pushPose();
        matrices.translate(relx,rely,relz);
        matrices.mulPose(Axis.YN.rotationDegrees(yaw + 90));

        int framesTotais = 4;
        int frameTime = 3;

        long gameTime = client.level.getGameTime();
        int frameAtual = (int) ((gameTime/frameTime)% framesTotais);

        float alturaDoFrame = 1.0f / framesTotais; // seciona a imagem e mostra frame a frame
        float minV = frameAtual * alturaDoFrame;
        float maxV = minV + alturaDoFrame;

        RenderType renderType = RenderTypes.entityTranslucent(TRECO);

        collector.submitCustomGeometry(matrices, renderType, (pose, consumer) -> {

            consumer.addVertex(pose, (float) (0.0), (float) (0.0 + 3.0), (float) (0.0 + 1.0f))
                    .setColor(255, 255, 255, 255)
                    .setUv(0.0f, minV)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(15728880)
                    .setNormal(0.0f, 0.0f, 1.0f);

            consumer.addVertex(pose, (float) (0.0), (float) (0.0), (float) (0.0 + 1.0f))
                    .setColor(255, 255, 255, 255)
                    .setUv(0.0f, maxV)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(15728880)
                    .setNormal(0.0f, 0.0f, 1.0f);

            consumer.addVertex(pose, (float) (0.0), (float) (0.0), (float) (0.0 - 1.0f))
                    .setColor(255, 255, 255, 255)
                    .setUv(1.0f, maxV)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(15728880)
                    .setNormal(0.0f, 0.0f, 1.0f);

            consumer.addVertex(pose, (float) (0.0), (float) (0.0 + 3.0), (float) (0.0 - 1.0f))
                    .setColor(255, 255, 255, 255)
                    .setUv(1.0f, minV)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(15728880)
                    .setNormal(0.0f, 0.0f, 1.0f);


        });
        matrices.popPose();


    }

}
